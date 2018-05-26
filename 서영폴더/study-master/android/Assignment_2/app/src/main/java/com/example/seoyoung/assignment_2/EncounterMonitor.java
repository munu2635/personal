package com.example.seoyoung.assignment_2;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EncounterMonitor extends Service {
    private static final String TAG = "EncounterMonitor";

    BluetoothAdapter mBTAdapter;
    String btName1, btName2, btName3;
    String userName1, userName2, userName3;

    Timer timer = new Timer();
    TimerTask timerTask = null;


    // 현재 시간을 출력하기 위해 사용하였다.
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat mFo = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //출력의 모습을 설정
    String now_date = mFo.format(date);

    // 파일 입출력과 관련하여 사용
    TextFileManager mFileMgr;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
  /*  private String getData(){ return mFileMgr }*/

    // BT 검색과 관련한 broadcast를 받을 BroadcastReceiver 객체 정의
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                // discovery 시작
            } else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                // discovery 종료
            } else if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                // Bluetooth device가 검색 됨
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                // 검색이 되었을 때 기준으로 값을 출력하기 위해 사용하였다.
                now = System.currentTimeMillis();
                date = new Date(now);
                now_date = mFo.format(date);

                if (btName1.equals(device.getName())) {
                    // 검색된 디바이스 이름이 등록된 디바이스 이름과 같으면 검색 시간과 이름을 저장 한다.
                    mFileMgr.save( now_date + " " + userName1 + "\n");
                }
               else if (( btName2 != null)&&(btName2.equals(device.getName()))) { // 널값인경우는 값이 저장되지 않은 경우를 나타낸다.
                    // 검색된 디바이스 이름이 등록된 디바이스 이름과 같으면 검색 시간과 이름을 저장 한다.
                    mFileMgr.save( now_date + " " + userName2 + "\n");
                }
                else if (( btName2 != null)&&(btName3.equals(device.getName()))) {
                    // 검색된 디바이스 이름이 등록된 디바이스 이름과 같으면 검색 시간과 이름을 저장 한다.
                    mFileMgr.save( now_date + " " + userName3 + "\n");
                }
            }
        }
    };


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");

        mBTAdapter = BluetoothAdapter.getDefaultAdapter();

        // BT 디바이스 검색 관련하여 어떤 종류의 broadcast를 받을 것인지 IntentFilter로 설정
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        mFileMgr = new TextFileManager();

        now = System.currentTimeMillis();
        date = new Date(now);
        now_date = mFo.format(date);

        //모니터링 시작을 나타낸다.
        mFileMgr.save("모니터링 시작 - " + now_date +  "\n");

        // BroadcastReceiver 등록
        registerReceiver(mReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // intent: startService() 호출 시 넘기는 intent 객체
        // flags: service start 요청에 대한 부가 정보. 0, START_FLAG_REDELIVERY, START_FLAG_RETRY
        // startId: start 요청을 나타내는 unique integer id

        Toast.makeText(this, "EncounterMonitor 시작", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStartCommand()");

        // MainActivity에서 Service를 시작할 때 사용한 intent에 담겨진 BT 디바이스와 사용자 이름 얻음

            btName1 = intent.getStringExtra("BTName1");
            userName1 = intent.getStringExtra("UserName1");
            btName2 = intent.getStringExtra("BTName2");
            userName2 = intent.getStringExtra("UserName2");
            btName3 = intent.getStringExtra("BTName3");
            userName3 = intent.getStringExtra("UserName3");

        // 주기적으로 BT discovery 수행하기 위한 timer 가동
        startTimerTask();

        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        Toast.makeText(this, "EncounterMonitor 중지", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy()");

    //끝나는 시간을 file 전송
        now = System.currentTimeMillis();
        date = new Date(now);
        now_date = mFo.format(date);

        mFileMgr.save("모니터링 종료 - " +  now_date + "\n");

        stopTimerTask();
        unregisterReceiver(mReceiver);
    }

    private void startTimerTask() {
        // TimerTask 생성한다
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mBTAdapter.startDiscovery();
            }
        };

        // TimerTask를 Timer를 통해 실행시킨다
        // 1초 후에 타이머를 구동하고 30초마다 반복한다
        timer.schedule(timerTask, 1000, 30000);
        //*** Timer 클래스 메소드 이용법 참고 ***//
        // 	schedule(TimerTask task, long delay, long period)
        // http://developer.android.com/intl/ko/reference/java/util/Timer.html
        //***********************************//
    }

    private void stopTimerTask() {
        // 1. 모든 태스크를 중단한다
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }
}
