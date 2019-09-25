package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codedleaf.sylveryte.attendanceapp.R;

/**
 * Created by sylveryte on 13/2/16.
 */
public class KlassAddFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Add Class");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_klass_fragment,container,false);

        final EditText editTextKlassName=(EditText)view.findViewById(R.id.editTextKlassName);
        final EditText editTextNoOfStudents=(EditText)view.findViewById(R.id.editText_numOfStudents);
        Button buttonKlassAdd=(Button)view.findViewById(R.id.addKlassButton);
        buttonKlassAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KlassLab.get().addKlass(
                        editTextKlassName.getText().toString(),
                        Integer.parseInt(editTextNoOfStudents.getText().toString()));
                getActivity().finish();
            }
        });
        return view;
    }
}
