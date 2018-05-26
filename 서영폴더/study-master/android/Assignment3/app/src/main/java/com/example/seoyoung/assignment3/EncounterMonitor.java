package com.example.seoyoung.assignment3;


import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Intent.getIntent;


public class EncounterMonitor extends Service {
    private static final String TAG = "EncounterMonitor";
    final static int MAX = 4;
    WifiManager wm;
    IntentFilter filter;
    List<ScanResult> scanList;

    Timer timer = new Timer();
    TimerTask timerTask = null;

    ArrayList<place> place_list ;
    ScanResult result;
    place result_place;
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

    private BroadcastReceiver mWifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
                // 검색이 되었을 때 기준으로 값을 출력하기 위해 사용하였다.
                now = System.currentTimeMillis();
                date = new Date(now);
                now_date = mFo.format(date);
                scanList = wm.getScanResults();


                // 저장된 장소의 상위 두개의 ap값과 같은 값을 갖으면 장소가 맞다고 하고 시각과 장소를 기록한다.
                if(scanList != null) {
                    int place_num = MAX + 1;
                    for(int i = 1; i < MAX; i++) {
                        result_place = place_list.get(i);
                        result = scanList.get(0);
                        if ( result.SSID.equals(result_place.ap1_SSRD)&& result.level > result_place.ap1_RSSI - 20){
                            mFileMgr.save(now_date +" " + result.SSID.equals(result_place.ap1_SSRD) +" " + result.SSID + "\n");
                            result = scanList.get(1);
                            if (result.SSID.equals(result_place.ap2_SSRD) && result.level > result_place.ap2_RSSI - 20){
                                mFileMgr.save(now_date + " " + result.SSID.equals(result_place.ap2_SSRD) + " " + result.SSID + "\n");
                                place_num = i;
                                mFileMgr.save(now_date + " " +result_place.p_name  + "\n");}
                            }
                    }
                    // 등록된 장소가 아니라고 판단되면 unknown을 기록한다.
                    if(place_num == MAX + 1){
                        mFileMgr.save(now_date + " unknown"  + "\n");
                    }
                }

            }
        }
    };


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);

        mFileMgr = new TextFileManager();
        now = System.currentTimeMillis();
        date = new Date(now);
        now_date = mFo.format(date);


        if(wm.isWifiEnabled() == false)
            wm.setWifiEnabled(true);

        //모니터링 시작을 나타낸다.
        mFileMgr.save("모니터링 시작 - " + now_date +  "\n");
        // BroadcastReceiver 등록
        registerReceiver(mWifiReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "EncounterMonitor 시작", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStartCommand()");

        place_list = intent.getParcelableArrayListExtra("place");

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
        unregisterReceiver(mWifiReceiver);
    }

    private void startTimerTask() {
        // TimerTask 생성한다
        timerTask = new TimerTask() {
            @Override
            public void run() {
                wm.startScan();
            }
        };

        // TimerTask를 Timer를 통해 실행시킨다
        // 1초 후에 타이머를 구동하고 60초마다 반복한다
        timer.schedule(timerTask, 1000, 60000);

    }

    private void stopTimerTask() {
        // 1. 모든 태스크를 중단한다
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }
}
