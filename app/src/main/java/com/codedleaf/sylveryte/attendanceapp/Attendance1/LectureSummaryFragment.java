package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codedleaf.sylveryte.attendanceapp.R;

import java.util.List;
import java.util.UUID;

/**
 * Created by sylveryte on 2/3/16.
 */
public class LectureSummaryFragment extends Fragment {

    private List<Attendance> mAttendances;
    private List<Student> mStudents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.list_fragment_layout,container,false);

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.list_layout_container_recycler_view);


        Intent i=getActivity().getIntent();
        UUID uuid=(UUID)i.getSerializableExtra(SummaryActivity.FRAGMENT_CODE);
        Lecture lecture = LectureLab.get().getLectureById(uuid);
        mAttendances= lecture.getAttendances();
        if (mAttendances.get(0)!=null)
            mStudents = mAttendances.get(0).getStudents();

        getActivity().setTitle(lecture.getLectureName());
        summaryAdapter sa=new summaryAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(sa);


        return view;
    }


    private class summaryHolder extends RecyclerView.ViewHolder
    {
        TextView mRollText;
        TextView mPer;
        ProgressBar mProgressBar;

        public summaryHolder(View itemView) {
            super(itemView);

            mRollText=(TextView)itemView.findViewById(R.id.rollText);
            mProgressBar=(ProgressBar)itemView.findViewById(R.id.prentProgress);

            mPer=(TextView)itemView.findViewById(R.id.subPer);


        }

        public void bind(int pos)
        {
            float per= AttendanceLab.get().getPresentyPercentage(mAttendances, pos);
            mRollText.setText(String.format("%d ", mStudents.get(pos).getRollNo()));
            mPer.setText(String.format("%.2f %%", per));
            mProgressBar.setProgress((int)per);
            if(per<75) {
                mRollText.setTextColor(Color.parseColor("#FF0000"));
                mProgressBar.setBackgroundColor(Color.parseColor("#770000"));
            }else
            {
                mRollText.setTextColor(Color.parseColor("#119966"));
                mProgressBar.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }

    }



    private class summaryAdapter extends RecyclerView.Adapter<summaryHolder>
    {
        @Override
        public summaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.student_summary_list,parent,false);
            return new summaryHolder(view);
        }

        @Override
        public void onBindViewHolder(summaryHolder holder, int position) {

            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return mStudents.size();
        }
    }
}
