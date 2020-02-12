package com.shivam.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.akaita.android.circularseekbar.CircularSeekBar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button rotate, stop;
    ImageView iv;
    CircularSeekBar circularSeekBar;
    RotateAnimation rotate1;
    Animation aniRotateClk;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotate=(Button) findViewById(R.id.rotbtn);
        stop = (Button) findViewById(R.id.buttonstop);
        iv=(ImageView) findViewById(R.id.rotiv);
        circularSeekBar=(CircularSeekBar)findViewById(R.id.skc);
        circularSeekBar.setProgressTextFormat(new DecimalFormat("###,###,##0"));

        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aniRotateClk = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                aniRotateClk.setDuration(5000);
                iv.startAnimation(aniRotateClk);
                circularSeekBar.setProgress(10);

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotate.setEnabled(false);
                rotate.setBackgroundResource(R.drawable.btnstyle2);
                rotate.setElevation((float)0.0);

                value = (int)circularSeekBar.getProgress();
                Toast.makeText(getApplicationContext(),"Current speed:"+value,Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            if(value>60)
                            {
                                value-=10;
                                circularSeekBar.setProgress(value);
                            }
                            else if(value>20)
                            {
                                value-=8;
                                circularSeekBar.setProgress(value);
                            }
                            else if(value>3){
                                value -= 4;
                                circularSeekBar.setProgress(value);
                            }
                            else {
                                value -=1;
                                circularSeekBar.setProgress(value);
                            }


                           // Toast.makeText(getApplicationContext(),"Current speed:"+value,Toast.LENGTH_SHORT).show();
                            if(value!=0)
                                stop.performClick();

                            if(value==0) {
                                rotate.setEnabled(true);
                                rotate.setBackgroundResource(R.drawable.btnstyle);
                                rotate.setElevation((float)80.0);
                            }

                    }
                }, 3000);



            }
        });

        circularSeekBar.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {


                    /*rotate1 = new RotateAnimation(
                            0, 36 * progress,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f
                    );

                    rotate1.setDuration(5000);
                    rotate1.setRepeatCount(Animation.INFINITE);
                    iv.startAnimation(rotate1);*/
                    aniRotateClk = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                    aniRotateClk.setDuration((long) (10000/progress));
                    iv.startAnimation(aniRotateClk);

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }
        });

    }
}
