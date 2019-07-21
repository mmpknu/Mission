package com.example.mission;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class typing2 extends AppCompatActivity {
    //counter
    public int counter = 180;
    public int minute;
    public int second;
    public String minute_str;
    public String second_str;
    public String rest_time;
    TextView count_txt;
    private static final int MILLISINFUTURE = 11*1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;
    //scroll view
    public ScrollView scrollView = null;
    private static final float FONT_SIZE = 10;
    public String test = "어제는 하루종일 비가 내렸어\n" +
            "자욱하게 내려앉은 먼지 사이로...\n"+
            "귓가에 은은하게 울려 퍼지는\n"+
            "그대 음성 빗속으로 사라져버려...\n" +
            "때론 눈물도 흐르겠지 그리움으로~\n"+
            "때론 가슴도 저리겠지 외로움으로~\n"+
            "사랑했지만...그대를 사랑했지만\n"+
            "그저 이렇게 멀리서 바라볼 뿐 다가 설 수 없어\n"+
            "지친 그대곁에 머물고 싶지만 떠날 수 밖에\n"+
            "그대를 사랑했지만...\n"+
            "때론 눈물도 흐르겠지 그리움으로~\n"+
            "때론 가슴도 저리겠지 외로움으로~\n"+
            "사랑했지만...그대를 사랑했지만\n"+
            "그저 이렇게 멀리서 바라볼 뿐 다가 설 수 없어\n"+
            "지친 그대곁에 머물고 싶지만 떠날 수 밖에\n"+
            "그대를 사랑했지만...\n"+
            "그대를 사랑했지만...\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing2);
        count_txt = (TextView) findViewById(R.id.timer);
        scrollView = (ScrollView)findViewById(R.id.text_scroll);
        new CountDownTimer(MILLISINFUTURE,COUNT_DOWN_INTERVAL){
            @Override
            public void onTick(long l) {
                minute = counter / 60;
                second = counter % 60;
                minute_str = String.format("%02d",minute);
                second_str = String.format("%02d",second);
                rest_time = minute_str + " : " + second_str;
                count_txt.setText(String.valueOf(rest_time));
                counter--;
            }

            @Override
            public void onFinish() {
                count_txt.setText("FINISH!!");
            }
        }.start();
    }
    public void creat_textview(String new_text){
        TextView textView = new TextView(this);
        textView.setText(new_text);
        textView.setTextSize(FONT_SIZE);
        textView.setTextColor(Color.BLACK);
        scrollView.addView(textView);
    }
}
