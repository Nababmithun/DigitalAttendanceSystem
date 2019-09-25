package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.codedleaf.sylveryte.attendanceapp.R;

import java.util.List;

/**
 * Created by sylveryte on 13/2/16.
 */
public class LectureAddFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_lecture_fragment,container,false);
        final EditText editTextLectureName=(EditText)view.findViewById(R.id.editTextLectureName);
        final EditText editTextStartingRoll=(EditText)view.findViewById(R.id.editTextStartingRoll);
        final EditText editTextLastRoll=(EditText)view.findViewById(R.id.editTextLastRoll);
        final EditText editTextRemarks=(EditText)view.findViewById(R.id.editTextRemarks);
        final Button button=(Button)view.findViewById(R.id.addKlassButton);
        final Spinner spinner=(Spinner)view.findViewById(R.id.spinner);


        getActivity().setTitle("Add Lecture");

        ArrayAdapter arrayAdapter=new CustomArrayAdapter(
                getActivity(),
                R.layout.klass_list_layout,
                KlassLab.get().getKlasses());
        spinner.setAdapter(arrayAdapter);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Klass klass=(Klass)spinner.getSelectedItem();
                String name=editTextLectureName.getText().toString();
                String remarks=editTextRemarks.getText().toString();
                int startingRoll=Integer.parseInt(editTextStartingRoll.getText().toString());
                int lastRoll=Integer.parseInt(editTextLastRoll.getText().toString());

               LectureLab.get().add(name,klass,startingRoll,lastRoll,remarks);
                getActivity().finish();
            }
        });

        return view;
    }





    private class CustomArrayAdapter extends ArrayAdapter
    {
        private List<Klass> mKlasses;
        private LayoutInflater mInflater;
        private Klass mKlass;

        public CustomArrayAdapter(Context context,int layoutResId,List<Klass> arrayList)
        {
            super(context, layoutResId,arrayList);
            mKlasses=arrayList;
            mInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, parent);
        }

        private View getCustomView(int pos, ViewGroup viewGroup) {
            View v = mInflater.inflate(R.layout.klass_list_layout, viewGroup, false);
            mKlass = mKlasses.get(pos);

            TextView mTextView = (TextView) v.findViewById(R.id.klass_list_text_klass_name);
            TextView mSubTextView = (TextView) v.findViewById(R.id.klass_list_text_extra_info);

                mTextView.setText(mKlass.getKlassName());
                mSubTextView.setText("Students : " + mKlass.getNumOfStudents());
            return v;
        }
    }

}

