package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.codedleaf.sylveryte.attendanceapp.Attendance1.DatabaseSchemas.AttendanceTable;

import java.text.ParseException;
import java.util.UUID;

/**
 * Created by sylveryte on 22/2/16.
 */
public class AttendanceCursorWrapper extends CursorWrapper {

    public AttendanceCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Attendance getAttendance() throws ParseException {
        String uuidString=getString(getColumnIndex(AttendanceTable.Cols.ID));
        String uuidLectureString=getString(getColumnIndex(AttendanceTable.Cols.LECTURE_ID));
        String present=getString(getColumnIndex(AttendanceTable.Cols.PRESENT));
        String date=getString(getColumnIndex(AttendanceTable.Cols.DATE));

        return new Attendance(UUID.fromString(uuidLectureString),
                UUID.fromString(uuidString),
                Attendance.getDateByString(date),present);
    }
}
