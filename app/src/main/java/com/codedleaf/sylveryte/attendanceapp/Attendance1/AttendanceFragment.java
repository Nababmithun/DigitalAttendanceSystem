package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codedleaf.sylveryte.attendanceapp.R;

import java.util.List;

/**
 * Created by sylveryte on 13/2/16.
 */
public class AttendanceFragment extends Fragment {

    private RecyclerView mAttendanceRecyclerView;
    private AttendanceAdapter mAttendanceAdapter;

    public AttendanceFragment() {

    }

    public static AttendanceFragment newInstance()
    {
        return new AttendanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.list_fragment_layout, container, false);

        mAttendanceRecyclerView=(RecyclerView)view.findViewById(R.id.list_layout_container_recycler_view);
        mAttendanceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,true));

        updateUI();

        return view;
    }



    private void updateUI()
    {
        AttendanceLab attendanceLab = AttendanceLab.get();
        List<Attendance> attendances = attendanceLab.getAttendances();
        mAttendanceAdapter =new AttendanceAdapter(attendances);
        mAttendanceRecyclerView.setAdapter(mAttendanceAdapter);
    }




    //view holder to hold views
    private class AttendanceHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextView;
        public TextView mSubTextView;
        private Attendance mAttendance;
        private Button deleteButton;

        public AttendanceHolder(View itemView)
        {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.klass_list_text_klass_name);
            mSubTextView=(TextView)itemView.findViewById(R.id.klass_list_text_extra_info);
            deleteButton=(Button)itemView.findViewById(R.id.delete);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete this entry?")
                            .setMessage("Are you sure you want to delete this Attendance entry?\n" + mAttendance.getExtraInfo())
                            .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //delete
                                    AttendanceLab.get().deleteAttendance(mAttendance);
                                    mAttendanceAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                    deleteButton.setVisibility(Button.GONE);
                                }
                            })
                            .show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    new AlertDialog.Builder(getActivity())
                            .setTitle("Choose")
                            .setPositiveButton(R.string.modifyAttendance, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //
                                    Intent intent = AdditionActivity.fetchIntent(getActivity(), AdditionActivity.TAKEATTENDANCE);
                                    intent.putExtra(ListDialog.ATTENDANCECODE, mAttendance.getId());
                                    startActivityForResult(intent,1);
                                }
                            })
                            .setNegativeButton("Show Summary", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //
                                    Intent i= SummaryActivity.fetchIntent(getActivity(),mAttendance.getId(),SummaryActivity.DAY_SUMMARY);
                                    startActivityForResult(i,1);

                                }
                            })
                            .show();



                }
            });

            deleteButton=(Button)itemView.findViewById(R.id.delete);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    deleteButton.setVisibility(Button.VISIBLE);
                    return true;
                }
            });
        }

        public void bind(Attendance attendance)
        {
            mAttendance=attendance;
            mTextView.setText(attendance.getAttendanceName());
            mSubTextView.setText(attendance.getExtraInfo());
        }
    }


    //adapter to manage
    private class AttendanceAdapter extends RecyclerView.Adapter<AttendanceHolder>
    {
        private List<Attendance> mAttendances;

        public AttendanceAdapter(List<Attendance> attendances)
        {
            mAttendances =attendances;
        }

        @Override
        public AttendanceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.klass_list_layout,parent,false);
            return new AttendanceHolder(view);
        }



        @Override
        public void onBindViewHolder(AttendanceHolder holder, int position) {

            Attendance attendance = mAttendances.get(position);
            holder.bind(attendance);
            holder.deleteButton.setVisibility(Button.GONE);
        }

        @Override
        public int getItemCount() {
            return mAttendances.size();
        }
    }

}
