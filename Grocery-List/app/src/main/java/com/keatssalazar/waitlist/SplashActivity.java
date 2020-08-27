package com.keatssalazar.waitlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.keatssalazar.waitlist.MainActivity;
import com.keatssalazar.waitlist.R;

public class SplashActivity extends AppCompatActivity {

    private TextView mKeats;
    private ImageView mFire;
    private Animation mAnim1,mAnim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        mKeats =findViewById(R.id.keats);
        mFire = findViewById(R.id.fire);



        new CountDownTimer(3000, 1000) {

            /** This method will be invoked on finishing or expiring the timer */
            @Override
            public void onFinish() {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            /** This method will be invoked in every 1000 milli seconds until
             * this timer is expired.Because we specified 1000 as tick time
             * while creating this CountDownTimer
             */
            @Override
            public void onTick(long millisUntilFinished) {

                mAnim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translation2);
                mAnim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translation);
                mFire.startAnimation(mAnim1);
                mKeats.startAnimation(mAnim2);



            }
        }.start();

    }
}