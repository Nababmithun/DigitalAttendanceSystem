package com.codedleaf.sylveryte.attendanceapp.Attendance1;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;


import com.codedleaf.sylveryte.attendanceapp.R;

import java.util.List;
import java.util.UUID;

public class AttendanceSummaryFragment extends Fragment {

    private Attendance mAttendance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.summary_layout,container,false);

    //getting attendance
        Intent i=getActivity().getIntent();
        mAttendance= AttendanceLab.
                get().
                getAttendanceById(
                        (UUID) i.getSerializableExtra(SummaryActivity.FRAGMENT_CODE)
                );
        List<Student> list=mAttendance.getStudents();

        //set the view
        TableLayout table=(TableLayout)view.findViewById(R.id.theSummaryTable);


        //absents
        TextView absent=new TextView(getActivity());
        absent.setText("Absents:");
        absent.setTextSize(25);
        table.addView(absent);
        StringBuilder sb=new StringBuilder();
        for (Student student:list) {
            if (!student.isPresent()) {
                sb.append(student.getRollNo()+" ");
            }}

        TextView tv = new TextView(getActivity());
        tv.setText(String.format(sb.toString()));
        tv.setTextSize(25);
        tv.setTextColor(Color.parseColor("#FF0716E7"));
        table.addView(tv);


        //presents
        StringBuilder sp=new StringBuilder();
        TextView present=new TextView(getActivity());
        present.setText("Presents:");
        present.setTextSize(25);
        table.addView(present);
        for (Student student:list) {
            if (student.isPresent()) {
                sp.append(student.getRollNo()+"  ");
            }
        }
        TextView tvp = new TextView(getActivity());
        tvp.setTextColor(Color.parseColor("#FF0716E7"));
        tvp.setTextSize(25);
        tvp.setText(sp.toString());
        table.addView(tvp);




        return view;
    }



}