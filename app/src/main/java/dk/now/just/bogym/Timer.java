package dk.now.just.bogym;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Timer extends MainActivity {
    ScrollView scrollView;
    Button btnStart,btnPause,btnLap,btnStop;
    TextView txtTimer;
    int sum = 0;
    Handler customHandler = new Handler();
    LinearLayout containerT;
    long startTime = 0L, timeInMilliseconds=0L,timeSwapBuff=0L, updateTime=0L;
    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int)(updateTime/1000);
            int mins = secs/60;
            secs%=60;
            int miliseconds = (int)(updateTime%1000);
            txtTimer.setText(""+mins+":"+String.format("%2d", secs)+":" + String.format("%3d",miliseconds));
            customHandler.postDelayed(this,0);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



        btnStart = (Button)findViewById(R.id.btnStart);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnLap = (Button)findViewById(R.id.btnLap);
        btnStop = (Button)findViewById(R.id.btnStop);
        txtTimer = (TextView) findViewById(R.id.timerValue);
        containerT = (LinearLayout)findViewById(R.id.containerT);
        scrollView = findViewById(R.id.timerScroll);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime= SystemClock.uptimeMillis();

                customHandler.postDelayed(updateTimeThread,0);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSwapBuff+=timeInMilliseconds;
                customHandler.removeCallbacks(updateTimeThread);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSwapBuff=0;
                customHandler.removeCallbacks(updateTimeThread);
                txtTimer.setText("0:00:000");
                startTime = SystemClock.currentThreadTimeMillis();
                containerT.removeAllViews();
                sum=0;


            }
        });


        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sum++;
                LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.trow, null);
                TextView txtValue = addView.findViewById(R.id.txtContent);
                txtValue.setText(sum+ ":   "+ txtTimer.getText());
                containerT.addView(addView);
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
