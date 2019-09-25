package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codedleaf.sylveryte.attendanceapp.Attendance1.DatabaseSchemas.AttendanceTable.Cols;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sylveryte on 13/2/16.
 */
public class AttendanceLab {
    private static AttendanceLab mAttendanceLab;
    private  List<Attendance> mAttendances;
    private SQLiteDatabase mDatabase;

    public static AttendanceLab get()
    {

        if(mAttendanceLab==null)
        {
            mAttendanceLab=new AttendanceLab();
        }
        return mAttendanceLab;
    }

    private  AttendanceLab()
    {

        mDatabase= DatabaseLab.getDatabase();

        mAttendances=new ArrayList<>();
        readDb();

    }

    private void readDb()
    {
        AttendanceCursorWrapper cursor=queryAttendance(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                mAttendances.add(cursor.getAttendance());
                cursor.moveToNext();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            cursor.close();
        }
    }

    private AttendanceCursorWrapper queryAttendance(String whereClause, String[] wherArgs)
    {
        Cursor cursor=mDatabase.query(
                DatabaseSchemas.AttendanceTable.NAME,
                null, //null selects all column
                whereClause,
                wherArgs,
                null,
                null,
                null
        );

        return new AttendanceCursorWrapper(cursor);
    }

    public void addAttendance(Attendance attendance)
    {

        mAttendances.add(attendance);
        mDatabase.insert(DatabaseSchemas.AttendanceTable.NAME,null,getContentValues(attendance));
    }

    public void updateDatabaseOfAttendance(Attendance attendance)
    {
        mDatabase.update(DatabaseSchemas.AttendanceTable.NAME,
                getContentValues(attendance),
                Cols.ID + " =?",
                new String[]{attendance.getId().toString()});
    }

    public void deleteAttendance(Attendance attendance)
    {
        mAttendances.remove(attendance);
        mDatabase.delete(DatabaseSchemas.AttendanceTable.NAME,
                Cols.ID + " =?",
                new String[]{attendance.getId().toString()});
    }

    public Attendance getAttendanceById(UUID uuid)
    {
        for (Attendance attendance: mAttendances) {
            if (uuid.equals(attendance.getId()))
            {
                return attendance;
            }
        }
        return null;
    }

    public float getPresentyPercentage(List<Attendance> attendances,int pos) {

        float total=attendances.size();
        int present=0;
        for (Attendance attendance : attendances) {
            if(attendance.getStudents().get(pos).isPresent())
            {
                present++;
            }
        }
        return (present/total)*100;
    }

    public List<Attendance> getAttendances()
    {
        return mAttendances;
    }

    private static ContentValues getContentValues(Attendance attendance) {
        ContentValues values = new ContentValues();

        values.put(Cols.ID,attendance.getId().toString());
        values.put(Cols.LECTURE_ID,attendance.getLectureId().toString());
        values.put(Cols.DATE,attendance.getDateString());
        values.put(Cols.PRESENT, SylveryteJoinSplit.getString(attendance.getStudents()));

        return values;
    }

}
