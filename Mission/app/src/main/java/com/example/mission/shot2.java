package com.example.mission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class shot2 extends AppCompatActivity {

    Button btn01,btn02;
    public int status = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot2);

        Button.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    //인증샷올리기 누르면 팝업창이 뜨게 됨. 업로드 부분 추가해야함.
                    case R.id.button1:
                        Intent intent = new Intent(shot2.this, popup.class);
                        intent.putExtra("next",2);
                        startActivityForResult(intent, 1);

                        break;
                    //촬영 다시하기 부분
                    case R.id.button2:
                        Intent intent2 = new Intent(shot2.this, popup.class);
                        startActivityForResult(intent2, 1);

                        break;

                }
            }
        };
        btn01 = (Button) findViewById(R.id.button1);
        btn01.setOnClickListener(btnListener);

        btn02 = (Button) findViewById(R.id.button2);
        btn02.setOnClickListener(btnListener);
    }






}


