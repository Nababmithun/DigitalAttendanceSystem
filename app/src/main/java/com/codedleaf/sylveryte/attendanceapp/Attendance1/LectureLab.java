package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codedleaf.sylveryte.attendanceapp.Attendance1.DatabaseSchemas.LectureTable.Cols;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sylveryte on 13/2/16.
 */
public class LectureLab {
    private static LectureLab mLectureLab;
    private  List<Lecture> mLectures;
    private SQLiteDatabase mDatabase;

    public static LectureLab get()
    {
        if(mLectureLab==null)
        {
            mLectureLab=new LectureLab();
        }
        return mLectureLab;
    }

    private  LectureLab()
    {
        mDatabase= DatabaseLab.getDatabase();
        //delete it later
        mLectures=new ArrayList<>();
        readDb();

    }

    private void readDb()
    {
        LectureCursorWrapper cursor=queryLectures(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                mLectures.add(cursor.getLecture());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
    }

    private LectureCursorWrapper queryLectures(String whereClause, String[] wherArgs)
    {
        Cursor cursor=mDatabase.query(
                DatabaseSchemas.LectureTable.NAME,
                null, //null selects all column
                whereClause,
                wherArgs,
                null,
                null,
                null
        );

        return new LectureCursorWrapper(cursor);
    }

    public void add(String name, Klass klass, int startRollNO, int lastRollNo, String remarks) {
        Lecture lecture=new Lecture(name,klass,startRollNO,lastRollNo,remarks);
        mLectures.add(lecture);
        mDatabase.insert(DatabaseSchemas.LectureTable.NAME,null,getContentValues(lecture));
    }

    public void deleteLecture(Lecture lecture)
    {
        for (Attendance attendance:lecture.getAttendances())
        {
            AttendanceLab.get().deleteAttendance(attendance);
        }

//        AttendanceFragment af=(AttendanceFragment)MainActivity.sFragments.get(0);
//        af.updateState();

        mLectures.remove(lecture);
        mDatabase.delete(DatabaseSchemas.LectureTable.NAME,
                Cols.ID + " =?",
                new String[]{lecture.getId().toString()});
    }


    public Lecture getLectureById(UUID uuid)
    {
        for (Lecture lecture: mLectures) {
            if (uuid.equals(lecture.getId()))
            {
                return lecture;
            }
        }
        return null;
    }

    private static ContentValues getContentValues(Lecture lecture) {
        ContentValues values = new ContentValues();

        values.put(Cols.ID,lecture.getId().toString());
        values.put(Cols.LECTURE_NAME,lecture.getLectureName());
        values.put(Cols.KLASS_ID,lecture.getKlassId().toString());
        values.put(Cols.STARTING_ROLL_NO,lecture.getStudentStartingRollNo());
        values.put(Cols.LAST_ROLL_NO,lecture.getStudentLastRollNo());
        values.put(Cols.REMARKS,lecture.getExtraInfo());

        return values;
    }

        public List<Lecture> getLectures()
    {
        return mLectures;
    }

}
