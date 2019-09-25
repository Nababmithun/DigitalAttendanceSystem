package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.codedleaf.sylveryte.attendanceapp.Attendance1.DatabaseSchemas.LectureTable.Cols;
import com.codedleaf.sylveryte.attendanceapp.Attendance1.KlassLab;
import com.codedleaf.sylveryte.attendanceapp.Attendance1.Lecture;

import java.util.UUID;

/**
 * Created by sylveryte on 22/2/16.
 */
public class LectureCursorWrapper extends CursorWrapper {

    public LectureCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Lecture getLecture(){

        String uuidString=getString(getColumnIndex(Cols.ID));
        String lecturename=getString(getColumnIndex(Cols.LECTURE_NAME));
        String klassID=getString(getColumnIndex(Cols.KLASS_ID));
        int Sroll=getInt(getColumnIndex(Cols.STARTING_ROLL_NO));
        int lastRoll=getInt(getColumnIndex(Cols.LAST_ROLL_NO));
        String remarks=getString(getColumnIndex(Cols.REMARKS));

        return new Lecture(lecturename,
                KlassLab.get().getKlassById(UUID.fromString(klassID)),
                Sroll,lastRoll,
                remarks,
                UUID.fromString(uuidString));
    }
}

