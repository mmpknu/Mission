package com.example.mission;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class typing2 extends AppCompatActivity {
    //counter
    public int counter = 180;
    public int minute;
    public int second;
    public String minute_str;
    public String second_str;
    public String rest_time;

    public String TAG = "TYP";
    TextView count_txt;
    TextView finish_count_txt;
    ArrayList<TextView> tvList;
    ArrayList<EditText> etList;
    int cnt;

    private static final int MILLISINFUTURE = 180*1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;
    //scroll view
    public ScrollView scrollView = null;
    public LinearLayout linearView = null;

    private static final float FONT_SIZE = 20;
    public String test = "aa bbc cc\n"+
            "어제는 하루종일 비가 내렸어\n" +
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

        finish_count_txt = (TextView) findViewById(R.id.typing2_textview2);
        count_txt = (TextView) findViewById(R.id.timer);
        scrollView = (ScrollView)findViewById(R.id.text_scroll);
        linearView = (LinearLayout)findViewById(R.id.lyric_linear);

        new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL){
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
                finish_count_txt.setText("");
                count_txt.setText("FINISH!!");
            }
        }.start();

        String[] lyrics = test.split("\n");
        int line_cnt = lyrics.length;
        tvList = new ArrayList<TextView>();
        etList = new ArrayList<EditText>();

        cnt = 0;
        for(String elem : lyrics){
            creat_textview(elem);
            creat_edit(elem, cnt);
            cnt++;
        }
    }

    public void creat_textview(String text){
        TextView textView = new TextView(this);
        tvList.add(textView);
        textView.setText(text);
        textView.setTextSize(FONT_SIZE);
        textView.setTextColor(Color.BLACK);
        linearView.addView(textView);
    }

    public void creat_edit(final String text, final int num){
        EditText edit = new EditText(this);
        etList.add(edit);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /*
                This method is called to notify you that, within charSequence, the count characters beginning
                at start have just replaced old text that had length before. It is an error to
                attempt to make changes to s from this callback.
            */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // typo check
                Log.i("typed string length", "len: "+s.length());

                SpannableString spannableString = new SpannableString(text);
                if(text.length() >= s.length()) {
                    Log.i("string1", text.substring(0, s.length()));
                    Log.i("string2", s.toString());
                    if (text.substring(0, s.length()).equals(s.toString())) {
                        // correct
                        Log.i("if", "correct");
                        int start = 0;
                        int end = s.length();
                        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#4287f5")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spannableString.setSpan(new RelativeSizeSpan(1.3f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Log.i("numcheck", "text.len "+text.length());
                        Log.i("numcheck", "num+1 "+(num+1));
                        Log.i("numcheck", "cnt "+cnt);
                        if(text.length() == s.length() && (num+1) < cnt)
                            etList.get(num+1).requestFocus();
                    } else {
                        Log.i("if", "wrong");
                        // wrong
                        int start = s.length()-1;
                        int end = s.length();
                        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6702")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spannableString.setSpan(new RelativeSizeSpan(1.3f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }

                tvList.get(num).setText(spannableString);
            }
        });
        edit.setTextColor(Color.BLACK);
        linearView.addView(edit);
    }
}
