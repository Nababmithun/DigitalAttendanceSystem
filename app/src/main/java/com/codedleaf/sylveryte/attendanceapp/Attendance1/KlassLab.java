package com.codedleaf.sylveryte.attendanceapp.Attendance1;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codedleaf.sylveryte.attendanceapp.Attendance1.DatabaseSchemas.KlassTable;
import com.codedleaf.sylveryte.attendanceapp.Attendance1.DatabaseSchemas.KlassTable.Cols;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sylveryte on 13/2/16.
 */
public class KlassLab {

    private static KlassLab sKlassLab;
    private  List<Klass> mKlasses;
    private SQLiteDatabase mDatabase;

    public static KlassLab get()
    {
        if(sKlassLab ==null)
        {
            sKlassLab =new KlassLab();
        }
        return sKlassLab;

        //cleanup
    }

    private KlassLab()
    {
        mDatabase= DatabaseLab.getDatabase();

        mKlasses =new ArrayList<>();
        readDb();

    }

    private void readDb()
    {

        KlassCursorWrapper cursor=queryKlass(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                mKlasses.add(cursor.getKlass());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
    }

    private KlassCursorWrapper queryKlass(String whereClause, String[] wherArgs)
    {
        Cursor cursor=mDatabase.query(
                DatabaseSchemas.KlassTable.NAME,
                null, //null selects all column
                whereClause,
                wherArgs,
                null,
                null,
                null
        );

        return new KlassCursorWrapper(cursor);
    }


    private static ContentValues getContentValues(Klass klass) {
        ContentValues values=new ContentValues();

        values.put(Cols.KLASS_NAME,klass.getKlassName());
        values.put(Cols.ID,klass.getId().toString());
        values.put(Cols.NUM_OF_STUDENTS,klass.getNumOfStudents());

        return values;
    }

    public Klass getKlassById(UUID uuid)
    {
        for (Klass klass: mKlasses) {
            if (uuid.equals(klass.getId()))
            {
                return klass;
            }
        }
        return null;
    }

    public void addKlass(String klassName, int num) {
        Klass klass=new Klass(klassName,num);
        mKlasses.add(klass);

        mDatabase.insert(KlassTable.NAME,null,getContentValues(klass));
    }

    public void deleteKlass(Klass klass)
    {
        for (Lecture lecture: klass.getLectures())
        {
            LectureLab.get().deleteLecture(lecture);
        }
//        LectureFragment lf=(LectureFragment)MainActivity.sFragments.get(1);
//        lf.updateState();

        mKlasses.remove(klass);
        mDatabase.delete(DatabaseSchemas.KlassTable.NAME,
                Cols.ID + " =?",
                new String[]{klass.getId().toString()});
    }

    public List<Klass> getKlasses() {
        return mKlasses;
    }
}
