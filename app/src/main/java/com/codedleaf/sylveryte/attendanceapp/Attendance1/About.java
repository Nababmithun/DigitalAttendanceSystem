package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.codedleaf.sylveryte.attendanceapp.R;

public class About extends AppCompatActivity {


    private TextView textView;

    Animation mithun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        textView= (TextView) findViewById(R.id.about);

        mithun= AnimationUtils.loadAnimation(this,R.anim.animation);

        textView.setAnimation(mithun);





    }
}
