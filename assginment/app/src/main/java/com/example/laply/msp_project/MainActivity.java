package com.example.laply.msp_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// 움직임(1분 이상) + 걸음 수, 체류(5분 이상) + 장소 (지정, 실내외) 표기가 일어날 장소
// 직접적으로 메인 서비스와의 intent를 사용하여 정보를 받을 공간
// 기본구조
// - 오늘의 날자 표기
// - 기록되는 기록 부분 표기
// + subactivity로 넘어가는 부분 포함된다.
// 사용할 기능들의 퍼미션을 받아야하는 공간이기도하다.
//---------------------------------------------/---- 5/31
// 추가사항

public class MainActivity extends AppCompatActivity {

    TextView logText, timeText;
    TextFileManager mFileMgr;


    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat mFo = new SimpleDateFormat("yyyy-MM-dd"); //출력의 모습을 설정
    String now_date = mFo.format(date);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFileMgr = new TextFileManager();

        logText = (TextView) findViewById(R.id.logText);
        logText.setMovementMethod(new ScrollingMovementMethod());
        timeText = (TextView)findViewById(R.id.timeText);
        timeText.setText(now_date);

        Intent intent = new Intent(this, MainService.class);
        startService(intent);
    }


    protected void onStart() {
        logText.setText(mFileMgr.load());
        super.onStart();
    }


}


