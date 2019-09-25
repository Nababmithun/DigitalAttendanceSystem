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
public class LectureFragment extends Fragment {

    private RecyclerView mLectureRecyclerView;
    private LectureAdapter mLectureAdapter;

    public LectureFragment() {

    }

    public static LectureFragment newInstance()
    {
        return new LectureFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.list_fragment_layout,container,false);

        mLectureRecyclerView=(RecyclerView)view.findViewById(R.id.list_layout_container_recycler_view);
        mLectureRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    public void updateState() {
        if(mLectureAdapter!=null)
        {
        mLectureAdapter.notifyDataSetChanged();
    }}

    private void updateUI()
    {
        LectureLab lectureLab = LectureLab.get();
        List<Lecture> lectures = lectureLab.getLectures();
        mLectureAdapter=new LectureAdapter(lectures);
        mLectureRecyclerView.setAdapter(mLectureAdapter);
    }




    //view holder to hold views
    private class LectureHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextView;
        public TextView mSubTextView;
        private Lecture mLecture;
        private Button deleteButton;

        public LectureHolder(View itemView)
        {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.klass_list_text_klass_name);
            mSubTextView=(TextView)itemView.findViewById(R.id.klass_list_text_extra_info);
            deleteButton=(Button)itemView.findViewById(R.id.delete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete "+mLecture.getLectureName()+"?")
                            .setMessage("Are you sure you want to delete "+mLecture.getLectureName()+
                                    "\n"+mLecture.getExtraInfo()+
                                    "\nAll attendance of this lecture will also be deleted.")
                            .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //delete
                                    LectureLab.get().deleteLecture(mLecture);
                                    mLectureAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                    deleteButton.setVisibility(Button.GONE);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    deleteButton.setVisibility(Button.VISIBLE);
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= SummaryActivity.fetchIntent(getActivity(),mLecture.getId(),SummaryActivity.MONTH_SUMMARY);
                    startActivityForResult(i,0);
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
            holder.deleteButton.setVisibility(Button.GONE);
            holder.bindLecture(lecture);
        }

        @Override
        public int getItemCount() {
            return mLectures.size();
        }
    }
}
