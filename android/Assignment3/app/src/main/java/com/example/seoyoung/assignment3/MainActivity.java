package com.example.seoyoung.assignment3;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    boolean isPermitted_1 = false;
    boolean isPermitted_2 = false;
    final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;

    static final int REQUEST_ENABLE_AP = 1;
    static final int REQUEST_ENABLE_DISCOVER = 2;
    TextView logText; //각각 로그와 저장된 이름데이터를 출력할 텍스트 뷰

    List<place> place_list;
    final static int MAX = 3;

    TextFileManager mFileMgr; //파일 입출력


    // placeclass를 초기화 해주는 함수 과제에서 주어진 총 3개의 위치를 나타낸다.
    void init_place(){
        for(int i = 0; i < MAX; i++){
            place null_place = new place();
            place_list.add(null_place);
        }
    }
    //테스트 하는데 사용되는 기본데이터가 저장된 부분
    void set_place(){
        place_list.get(0).set_data("집","11","hy1738");

        //place_list.get(0).set_data("4층 엘리베이터 앞","","");
        place_list.get(1).set_data("408호 계단앞 ", "", "");
        place_list.get(2).set_data("401호 계단 앞", "", "");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestRuntimePermission();

        place_list = new ArrayList<place>();
        init_place(); //place를 저장할 부분을 초기화하는 부분
        set_place(); //테스트 하는데 사용되는 기본적으로 저장하는 부분

        logText = (TextView)findViewById(R.id.logText); // 이것을 사용해서 모니터링 된 데이터를 입력 작성
        logText.setMovementMethod(new ScrollingMovementMethod());// 텍스트 뷰 스크롤 사용하기 위한 부분

        mFileMgr = new TextFileManager();
        mFileMgr.delete(); // 처음 시작할때 저장되어있는 값을 삭제하고 시작한다.

    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        // 요청 코드에 따라 처리할 루틴을 구분해줌
        switch(requestCode) {
            case REQUEST_ENABLE_AP:
                if(responseCode == RESULT_OK) {
                    // 사용자가 활성화 상태로 변경하는 것을 허용하였음
                } else if(responseCode == RESULT_CANCELED) {
                    // 사용자가 활성화 상태로 변경하는 것을 허용하지 않음
                    // 블루투스를 사용할 수 없으므로 애플리케이션 종료
                    finish();
                }
                break;
            case REQUEST_ENABLE_DISCOVER:
                if(responseCode == RESULT_CANCELED) {
                    // 사용자가 DISCOVERABLE 허용하지 않음 (다이얼로그 화면에서 거부를 선택한 경우)
                    Toast.makeText(this, "사용자가 discoverable을 허용하지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void onClick(View view) {
         if(view.getId() == R.id.startMonitorBtn) {
                Intent intent = new Intent(this, EncounterMonitor.class);
                intent.putParcelableArrayListExtra("place", (ArrayList<? extends Parcelable>) place_list);
                startService(intent); //모든 저장 정보 intent 및 서비스 시작
        } else if(view.getId() == R.id.stopMonitorBtn) {
           stopService(new Intent(this, EncounterMonitor.class));  //서비스 끝
        } else if(view.getId() == R.id.EncounterMonitorBtn) {
            logText.setText(mFileMgr.load());
        }
    }

    public void onClickDiscover(View view) {
        Intent discoverIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
        startActivityForResult(discoverIntent, REQUEST_ENABLE_DISCOVER);
    }
    private void requestRuntimePermission() {
        //*******************************************************************
        // Runtime permission check
        //*******************************************************************
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
            }
        } else {
            // ACCESS_COARSE_LOCATION 권한이 있는 것
            isPermitted_1 = true;
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            // WRITE_EXTERNAL_STORAGE 권한이 있는 것
            isPermitted_2 = true;
        }
        //*********************************************************************
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // read_external_storage-related task you need to do.

                    // ACCESS_COARSE_LOCATION 권한을 얻음
                    isPermitted_1 = true;

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    // 권한을 얻지 못 하였으므로 location 요청 작업을 수행할 수 없다
                    // 적절히 대처한다
                    isPermitted_1 = false;

                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // read_external_storage-related task you need to do.

                    // WRITE_EXTERNAL_STORAGE 권한을 얻음
                    isPermitted_2 = true;

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    // 권한을 얻지 못 하였으므로 location 요청 작업을 수행할 수 없다
                    // 적절히 대처한다
                    isPermitted_2 = false;

                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    @Override // 액티비티를 벗어나고 다시 액티비티로 돌아왔을때 로그를 출력해주는 부분
    protected void onStart() {
        logText.setText(mFileMgr.load());
        super.onStart();
    }
}

