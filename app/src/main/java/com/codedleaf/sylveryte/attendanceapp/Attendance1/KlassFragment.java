package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.DialogInterface;
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
 *
 */



public class KlassFragment extends Fragment {

    private RecyclerView mKlassRecyclerView;
    private KlassAdapter mKlassAdapter;


    public KlassFragment() {

    }

    public static KlassFragment newInstance()
    {
        return new KlassFragment();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.list_fragment_layout,container,false);

        mKlassRecyclerView =(RecyclerView)view.findViewById(R.id.list_layout_container_recycler_view);
        mKlassRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI()
    {
        KlassLab klassLab = KlassLab.get();
        List<Klass> klasses = klassLab.getKlasses();
        mKlassAdapter =new KlassAdapter(klasses);
        mKlassRecyclerView.setAdapter(mKlassAdapter);
    }




    //view holder to hold views
    private class KlassHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextView;
        public TextView mSubTextView;
        private Button deleteButton;
        private Klass mKlass;


        public KlassHolder(View itemView)
        {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.klass_list_text_klass_name);
            mSubTextView=(TextView)itemView.findViewById(R.id.klass_list_text_extra_info);
            deleteButton=(Button)itemView.findViewById(R.id.delete);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete "+mKlass.getKlassName()+"?")
                            .setMessage("Are you sure you want to delete "+mKlass.getKlassName()+
                                    "\nAll lectures & attendance of this klass will also be deleted.")
                            .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //delete
                                    KlassLab.get().deleteKlass(mKlass);
                                    mKlassAdapter.notifyDataSetChanged();
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
        }



        public void bind(Klass klass)
        {
            mKlass=klass;
            mTextView.setText(klass.getKlassName());
            mSubTextView.setText(String.format("%d ", klass.getNumOfStudents()));
        }

    }


    //adapter to manage
    private class KlassAdapter extends RecyclerView.Adapter<KlassHolder>
    {
        private List<Klass> mKlasses;

        public KlassAdapter(List<Klass> klasses)
        {
            mKlasses =klasses;
        }

        @Override
        public KlassHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.klass_list_layout,parent,false);
            return new KlassHolder(view);
        }



        @Override
        public void onBindViewHolder(KlassHolder holder, int position) {
            Klass klass = mKlasses.get(position);
            holder.bind(klass);
            holder.deleteButton.setVisibility(Button.GONE);
        }

        @Override
        public int getItemCount() {
            return mKlasses.size();
        }
    }
}
