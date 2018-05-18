package com.example.seoyoung.assiment_1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity implements LocationListener {

    EditText e_name, e_lat, e_long, e_rad;
    LocationManager locManager;
    TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        e_name = (EditText) findViewById(R.id.edit_name);
        e_lat = (EditText) findViewById(R.id.edit_latitude);
        e_long = (EditText) findViewById(R.id.edit_longitude);
        e_rad = (EditText) findViewById(R.id.edit_radius);
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); //위치정보를 사용하기 위해 사용
        locationText = (TextView) findViewById(R.id.location);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }// permission확인
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this); //위치정보 받아오기

        Button addB = (Button) findViewById(R.id.add_button);
        addB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent in = new Intent();

                //4개의 데이터 중 하나라도 입력이 되지 않았다면 오류로 간주 오류처리한다.
                if((e_name.getText().toString() == "")||(e_lat.getText().toString() == "")||(e_long.getText().toString() == "")||(e_rad.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "모든 데이터를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, in);
                    finish();
                }
                else { //각각의 입력된 정보를 intent에 저장한다.
                    in.putExtra("INPUT_NAME", e_name.getText().toString());
                    in.putExtra("INPUT_LAT", Double.parseDouble(e_lat.getText().toString()));
                    in.putExtra("INPUT_LONG", Double.parseDouble(e_long.getText().toString()));
                    in.putExtra("INPUT_RAD", Integer.parseInt(e_rad.getText().toString()));

                    //위도 값이 범위를 벗어났을때의 에러처리
                    if ((-90.0 > in.getDoubleExtra("INPUT_LAT", 0)) || (in.getDoubleExtra("INPUT_LAT", 0) > 90.0)) {
                        Toast.makeText(getApplicationContext(), "위도입력에러", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED, in);
                        finish();
                    }// 경도 값이 범위를 벗어났을때의 에러처리
                    else if ((-180.0 >= in.getDoubleExtra("INPUT_LONG", 0)) || (in.getDoubleExtra("INPUT_LONG", 0) > 180.0)) {
                        Toast.makeText(getApplicationContext(), "경도입력에러", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED, in);
                        finish();
                    }// 반경 값이 범위를 벗어났을때의 에러처리
                    else if ((0 > in.getIntExtra("INPUT_RAD", 0))) {
                        Toast.makeText(getApplicationContext(), "반경입력에러", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED, in);
                        finish();
                    }
                    //모든게 다 만족했을때 값 출력
                    setResult(RESULT_OK, in);
                    finish();
                }
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) { //AddActivity에서도 위치 정보값을 얻기위해서 사용
        locationText.setText("위도 : " + location.getLatitude()
                + " 경도 : " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
