package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codedleaf.sylveryte.attendanceapp.R;

public class parcentanceeeeeeeeeee extends AppCompatActivity {

    private EditText editText1, editText2;

    private Button button;

    private TextView textView1,textView2,textView3;




    Animation moja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcentanceeeeeeeeeee);
        editText1 = (EditText) findViewById(R.id.present);

        editText2 = (EditText) findViewById(R.id.totalclass);

        button = (Button) findViewById(R.id.calculate);

        textView1 = (TextView) findViewById(R.id.result);



        textView2= (TextView) findViewById(R.id.what);

        textView3= (TextView) findViewById(R.id.offf);



        moja= AnimationUtils.loadAnimation(this,R.anim.animation);


        textView1.setAnimation(moja);
        textView2.setAnimation(moja);
        textView3.setAnimation(moja);


        editText1.setAnimation(moja);
        editText2.setAnimation(moja);

        button.setAnimation(moja);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                float present = Float.parseFloat(editText1.getText().toString());


                float multi = present * 100;


                float totalclass = Float.parseFloat(editText2.getText().toString());


                float num = multi / totalclass;


                textView1.setText("Result: " + Float.toString(num));


            }

        });





    }
}