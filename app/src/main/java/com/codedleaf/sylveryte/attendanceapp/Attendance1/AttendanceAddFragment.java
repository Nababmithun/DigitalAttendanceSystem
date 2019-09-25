package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.codedleaf.sylveryte.attendanceapp.R;

import java.util.List;
import java.util.UUID;

/**
 * Created by sylveryte on 21/2/16.
 */
public class AttendanceAddFragment extends Fragment {


    private RecyclerView mAttendanceRecyclerView;
    private RollAdapter mRollAdapter;
    private Lecture mLecture;

    private Attendance mAttendance;
    private List<Student> mStudents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_attendance_fragment, container, false);

        mAttendanceRecyclerView=(RecyclerView)view.findViewById(R.id.attendance_container_recycler_view);
        mAttendanceRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        getActivity().setTitle("Attendance");

        Intent intent=getActivity().getIntent();
        UUID uuid=(UUID)intent.getSerializableExtra(ListDialog.ATTENDANCECODE);

        mAttendance= AttendanceLab.get().getAttendanceById(uuid);

        mStudents=mAttendance.getStudents();
        mRollAdapter=new RollAdapter();

        mAttendanceRecyclerView.setAdapter(mRollAdapter);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        AttendanceLab.get().updateDatabaseOfAttendance(mAttendance);
    }

    private class RollHolder extends RecyclerView.ViewHolder
    {
        private Student mStudent;
        public Button mButton;

        public RollHolder(View itemView) {
            super(itemView);
            mButton=(Button)itemView.findViewById(R.id.roll_button_bro);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mStudent.setIsPresent(!mStudent.isPresent());

                    updateUI();
                }
            });
        }

        public void bind(Student student)
        {
            mStudent=student;
            mButton.setText(String.format("%d ", student.getRollNo()));
            if (!mStudent.isPresent())
            {
                mButton.setBackgroundResource(R.drawable.absent);
            }
            else {
                mButton.setBackgroundResource(R.drawable.present);
            }
        }

    }
    private void updateUI()
    {
        if (mRollAdapter!=null)
        {
            mRollAdapter.notifyDataSetChanged();
        }
    }

    private class RollAdapter extends RecyclerView.Adapter<RollHolder>
    {
        @Override
        public RollHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.roll_button_attendance,parent,false);
            return new RollHolder(view);
        }

        @Override
        public void onBindViewHolder(RollHolder holder, int position) {
            holder.bind(mStudents.get(position));
        }


        @Override
        public int getItemCount() {
            return mStudents.size();
        }


    }


}

