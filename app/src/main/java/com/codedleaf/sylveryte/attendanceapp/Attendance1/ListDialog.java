package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codedleaf.sylveryte.attendanceapp.Attendance1.AdditionActivity;
import com.codedleaf.sylveryte.attendanceapp.Attendance1.Attendance;
import com.codedleaf.sylveryte.attendanceapp.Attendance1.AttendanceLab;
import com.codedleaf.sylveryte.attendanceapp.Attendance1.Lecture;
import com.codedleaf.sylveryte.attendanceapp.Attendance1.LectureLab;
import com.codedleaf.sylveryte.attendanceapp.R;

import java.util.List;

/**
 * Created by sylveryte on 21/2/16.
 */
public class ListDialog extends DialogFragment {

    private RecyclerView mLectuersList;
    private List<Lecture> mLectures;
    private LayoutInflater mLayoutInflater;
    public static final String ATTENDANCECODE="this is a code";

    /*
     */



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mLayoutInflater=LayoutInflater.from(getActivity());

        View v= mLayoutInflater.inflate(R.layout.lectures_list, null);

        mLectuersList=(RecyclerView)v.findViewById(R.id.list_layout_container_lectures);

        mLectuersList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLectures= LectureLab.get().getLectures();

        LectureAdapter adapter=new LectureAdapter(mLectures);
        mLectuersList.setAdapter(adapter);


        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.selectLecture)
                .setView(v)
                .create();
    }

    private class LectureHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextView;
        public TextView mSubTextView;
        private Lecture mLecture;

        public LectureHolder(View itemView)
        {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.klass_list_text_klass_name);
            mSubTextView=(TextView)itemView.findViewById(R.id.klass_list_text_extra_info);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent= AdditionActivity.fetchIntent(getActivity(), AdditionActivity.TAKEATTENDANCE);
                    Attendance attendance=new Attendance(mLecture.getId());
                    AttendanceLab.get().addAttendance(attendance);
                    intent.putExtra(ATTENDANCECODE,attendance.getId());
                    startActivity(intent);
                }
            });

        }

        public void bindLecture(Lecture lecture)
        {
            mLecture=lecture;
            mTextView.setText(mLecture.getLectureName());
            mSubTextView.setText(mLecture.getExtraInfo());
        }
    }


    //adapter to manage
    private class LectureAdapter extends RecyclerView.Adapter<LectureHolder>
    {
        private List<Lecture> mLectures;

        public LectureAdapter(List<Lecture> lectures)
        {
            mLectures =lectures;
        }

        @Override
        public LectureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.klass_list_layout, parent, false);
            return new LectureHolder(view);
        }



        @Override
        public void onBindViewHolder(LectureHolder holder, int position) {
            Lecture lecture = mLectures.get(position);
            holder.bindLecture(lecture);
        }

        @Override
        public int getItemCount() {
            return mLectures.size();
        }
    }




}
