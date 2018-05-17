package com.example.seoyoung.assiment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class RemoveActivity extends AppCompatActivity {

    EditText e_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_activity);

        e_remove = (EditText) findViewById(R.id.edit_remove);

        Button removeB = (Button) findViewById(R.id.button_remove);
        removeB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                // 입력된값 입력
                intent.putExtra("INPUT_RENAME", e_remove.getText().toString());
                //만약 값이 입력되지 않았다면 오류로 출력, 성공시 RESULT_OK 출력
                if(intent.getStringExtra("INPUT_RENAME") != null) setResult(RESULT_OK, intent);
                else setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
