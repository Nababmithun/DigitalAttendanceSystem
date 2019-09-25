package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.codedleaf.sylveryte.attendanceapp.Attendance1.DatabaseSchemas.*;

/**
 * Created by sylveryte on 22/2/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int version=1;
    private static final String databaseName="Attendance.db";
    public DatabaseHelper(Context context)
    {
        super(context,databaseName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ KlassTable.NAME+"("+
                    " _id integer primary key autoincrement, "+
                    KlassTable.Cols.KLASS_NAME+", "+
                    KlassTable.Cols.ID+", "+
                    KlassTable.Cols.NUM_OF_STUDENTS+")"
        );

        db.execSQL("create table "+ LectureTable.NAME+"("+
                        " _id integer primary key autoincrement, "+
                        LectureTable.Cols.LECTURE_NAME+", "+
                        LectureTable.Cols.ID+", "+
                        LectureTable.Cols.KLASS_ID+", "+
                        LectureTable.Cols.STARTING_ROLL_NO+", "+
                        LectureTable.Cols.LAST_ROLL_NO+", "+
                        LectureTable.Cols.REMARKS+")"
        );

        db.execSQL("create table "+ AttendanceTable.NAME+"("+
                        " _id integer primary key autoincrement, "+
                        AttendanceTable.Cols.ID+", "+
                        AttendanceTable.Cols.LECTURE_ID+", "+
                        AttendanceTable.Cols.PRESENT+", "+
                        AttendanceTable.Cols.DATE+")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
