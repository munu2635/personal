package com.example.seoyoung.assiment_1;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements LocationListener{

    LocationManager locManager;
    AlertReceiver receiver;
    PendingIntent proximityIntent;
    boolean isPermitted = false;
    boolean isLocRequested = false;
    TextView locationText;
    boolean isAlertRegistered = false;
    final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    TextView text1, text2 ,text3;

    static final int GET_ADD = 1;
    static final int GET_REMOVE = 2;

        //저장된 정보의 갯수와 정보의 삭제시 삭제할 정보의 위치를 저장할 변수
    int num = 0, check_rem = 0;
        // 각각의 위치정보를 저장할 intents
    Intent p1, p2, p3 = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView) findViewById(R.id.text_1);
        text2 = (TextView) findViewById(R.id.text_2);
        text3 = (TextView) findViewById(R.id.text_3);
        locationText = (TextView) findViewById(R.id.location);

        // 처음 값을 널값으로 설정한다. 값이 들어있지 않는 인텐스는 모두 null값으로 처리한다.
        p1 = null; p2 = null; p3 = null;

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        requestRuntimePermission();

        //각각의 버튼을 사용해 다른 activity로 이동할 수 있다.
        Button b_add = (Button)findViewById(R.id.enter_add);
        Button b_rmv = (Button)findViewById(R.id.enter_rmv);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 위치와 이동할 위치
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                //값을 받아 올 수 있도록 ForResult를 사용했다. GET_ADD는 AddActivity에서 받아오는 정보와 RemoveActivity에서 받아올 정보를 구별할 수 있도록 사용 되었다.
                startActivityForResult(intent, GET_ADD);
            }
        });
        b_rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RemoveActivity.class);
                startActivityForResult(intent, GET_REMOVE);
            }
        });

        try {
            if(isPermitted) {
                // permission을 확인하여 현재 위치를 받아온다.
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, (LocationListener) this);
                isLocRequested = true;
            }
            else
                Toast.makeText(this, "Permission이 없습니다..", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
    //AddActivity에서 받아온 정보를 처리하는 부분
     if(requestCode == GET_ADD){
         if(resultCode == RESULT_CANCELED) {// 에러발생시 여기로 빠져나간다.
         } else if(resultCode == RESULT_OK) {// 잘 처리되어서 온 값이라면 데이터를 저장한다.
            if(num == 0){ // 지금 저장된 데이터의 수에 따라 각각에 위치에 저장한다.
               text1.setText(data.getStringExtra("INPUT_NAME")); num++; p1 = data; // 먼저 Textview에 장소의 이름을 출력하고, num를 증가시긴다, 후에 p1 intent에 받아온 정보를 저장한다.
                Toast.makeText(getApplicationContext(),"1번 데이터 입력",Toast.LENGTH_SHORT).show();
           }else if( num == 1 ){
               text2.setText(data.getStringExtra("INPUT_NAME")); num++; p2 = data;
                Toast.makeText(getApplicationContext(),"2번 데이터 입력",Toast.LENGTH_SHORT).show();
           }else if( num == 2){
               text3.setText(data.getStringExtra("INPUT_NAME")); num++; p3 = data;
                Toast.makeText(getApplicationContext(),"3번 데이터 입력",Toast.LENGTH_SHORT).show();
           } else if(num == 3){ Toast.makeText(getApplicationContext(),"이미 다 등록되어 있습니다.",Toast.LENGTH_SHORT).show();} // 모든 저장장소가 차서 저장할 수 없을 때 출력한다.
         }
     }
     else if(requestCode == GET_REMOVE){ //RemoveActivity에서 받아온 정보를 처리하는 부분
         if(resultCode == RESULT_OK) { // 잘 처리되어 온 값이라면
             check_rem = set_check_rem(data); // set_check_rem(data) 함수를 이용하여 어느위치의 저장정보가 삭제되어야 할지 선택한다.
             if (num == 1) {// 현재 저장되어있는 갯수를 기반으로 삭제 및 위치변경을 수행한다.
                 if (check_rem == 1) {
                     text1.setText(" ");
                     p1 = null; num--; //삭제된 값은 null입력하여 없는 값으로 변경해주고, 현재 저장되어있는 수를 저장하고 있는 num의 숫자를 감소시킨다.
                     Toast.makeText(getApplicationContext(),"1번 데이터 삭제",Toast.LENGTH_SHORT).show();
                 }
             }else if(num == 2) {
                 if (check_rem == 1) {
                     text1.setText(p2.getStringExtra("INPUT_NAME")); // 선순위 데이터가 삭제되면 데이터를 이동하고 장소의 이름 또한 선순위로 출력할 수 있도록 이동한다.
                     text2.setText(" ");
                     p1 = p2; p2 = null; num--;
                     Toast.makeText(getApplicationContext(),"1번 데이터 삭제",Toast.LENGTH_SHORT).show();
                 } else if (check_rem == 2) {
                     text2.setText(" ");
                     p2 = null; num--;
                     Toast.makeText(getApplicationContext(),"2번 데이터 삭제",Toast.LENGTH_SHORT).show();
                 }
             }else if(num == 3){
                 if (check_rem == 1) {
                     text1.setText(p2.getStringExtra("INPUT_NAME"));
                     text2.setText(p3.getStringExtra("INPUT_NAME"));
                     text3.setText(" ");
                     p1 = p2; p2 = p3; p3 = null; num--;
                     Toast.makeText(getApplicationContext(),"1번 데이터 삭제",Toast.LENGTH_SHORT).show();
                 } else if (check_rem == 2) {
                     text2.setText(p3.getStringExtra("INPUT_NAME"));
                     text3.setText(" ");
                     p2 = p3; p3 = null; num--;
                     Toast.makeText(getApplicationContext(),"2번 데이터 삭제",Toast.LENGTH_SHORT).show();
                 } else if (check_rem == 3) {
                     text3.setText(" ");
                     p3 = null; num--;
                     Toast.makeText(getApplicationContext(),"3번 데이터 삭제",Toast.LENGTH_SHORT).show();
                 }
             }
         }
         else if(resultCode == RESULT_CANCELED) { } //오류발생시 요기로 빠져나간다.
        }
        if(num !=0) { // 데이터가 없지 않다면 수행한다.
            if (p1 != null) { // p1
                receiver = new AlertReceiver(p1); //생성자를 통해 p1을 보내주어 toast 출력시 위치의 이름도 같이 출력 할 수 있도록 한다.
                IntentFilter filter = new IntentFilter("P1");
                registerReceiver(receiver, filter);

                Intent intent = new Intent("P1");

                proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
                try {//각각 받아온 값을 이용하여 위치정보 및 범위를 입력한다.
                    locManager.addProximityAlert(p1.getDoubleExtra("INPUT_LAT", 0), p1.getDoubleExtra("INPUT_LONG", 0), p1.getIntExtra("INPUT_RAD", 0), -1, proximityIntent);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                isAlertRegistered = true;
            }
            if (p2 != null) {
                receiver = new AlertReceiver(p2);
                IntentFilter filter = new IntentFilter("P2");
                //filter.putExtra("INPUT_NAME", p2.getStringExtra("INPUT_NAME"));
                registerReceiver(receiver, filter);

                Intent intent = new Intent("P2");

                proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
                try {
                    locManager.addProximityAlert(p2.getDoubleExtra("INPUT_LAT", 0), p2.getDoubleExtra("INPUT_LONG", 0), p2.getIntExtra("INPUT_RAD", 0), -1, proximityIntent);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                isAlertRegistered = true;
            }
            if (p3 != null) {
                receiver = new AlertReceiver(p3);
                IntentFilter filter = new IntentFilter("P3");
                registerReceiver(receiver, filter);

                Intent intent = new Intent("P3");

                proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
                try {
                    locManager.addProximityAlert(p3.getDoubleExtra("INPUT_LAT", 0), p3.getDoubleExtra("INPUT_LONG", 0), p3.getIntExtra("INPUT_RAD", 0), -1, proximityIntent);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                isAlertRegistered = true;
            }
        }
    }
// 삭제할 데이터의 넘버를 찾는 함수
    private int set_check_rem(Intent intent) {
        String input_texts = intent.getStringExtra("INPUT_RENAME"); //삭제할데이터의 이름
        if(num != 0){
            if(num == 1) { //데이터가 1개라면
                if (input_texts.equals(p1.getStringExtra("INPUT_NAME"))) return 1; //삭제할것과 이름이 같다면 삭제할 위치를 전송
            }
            else if(num == 2){ //데이터가 2개라면
                if (input_texts.equals(p1.getStringExtra("INPUT_NAME"))) return 1;
                else if (input_texts.equals(p2.getStringExtra("INPUT_NAME"))) return 2;
            }
            else if(num == 3){ //데이터가 3개라면
                if (input_texts.equals(p1.getStringExtra("INPUT_NAME"))) return 1;
                else if (input_texts.equals(p2.getStringExtra("INPUT_NAME"))) return 2;
                else if (input_texts.equals(p3.getStringExtra("INPUT_NAME"))) return 3;
            }
        }
        Toast.makeText(getApplicationContext(), "삭제할 입력 데이터 없음", Toast.LENGTH_SHORT).show(); return 0; // 같은 이름이 나타나지 않았다면 출력 및 처리
    }

    private void requestRuntimePermission() {
        //*******************************************************************
        // Runtime permission check
        //*******************************************************************
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        } else {
            // ACCESS_FINE_LOCATION 권한이 있는 것
            isPermitted = true;
        }
        //*********************************************************************
    }

    @Override
    protected void onPause(){
        super.onPause();

        // 자원 사용 해제
        try {
            if(isLocRequested) {
                locManager.removeUpdates(this);
                isLocRequested = false;
            }
            if(isAlertRegistered) {
                locManager.removeProximityAlert(proximityIntent);
                unregisterReceiver(receiver);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,

                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // read_external_storage-related task you need to do.

                    // ACCESS_FINE_LOCATION 권한을 얻음
                    isPermitted = true;

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    // 권한을 얻지 못 하였으므로 location 요청 작업을 수행할 수 없다
                    // 적절히 대처한다
                    isPermitted = false;

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onLocationChanged(Location location) { //현재 위치를 출력할 수 있도록 한다.
        locationText.setText("위도 : " + location.getLatitude()
                + " 경도 : " + location.getLongitude());

    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

}


