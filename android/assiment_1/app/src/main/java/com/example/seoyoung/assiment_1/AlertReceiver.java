package com.example.seoyoung.assiment_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class AlertReceiver extends BroadcastReceiver {
    Intent intent;
    AlertReceiver(Intent in){
        intent = in;
    }// 생성자를 통해 각각의 위치의 이름정보를 사용하기 위해 사용

    @Override
    public void onReceive(Context context, Intent in) {
        boolean isEntering = in.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
        // boolean getBooleanExtra(String name, boolean defaultValue)

        if(isEntering)
            Toast.makeText(context, intent.getStringExtra("INPUT_NAME") + " 목표 지점에 접근중입니다..", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, intent.getStringExtra("INPUT_NAME") + " 목표 지점에서 벗어납니다..", Toast.LENGTH_LONG).show();    }
}