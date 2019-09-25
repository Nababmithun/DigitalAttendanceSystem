package com.codedleaf.sylveryte.attendanceapp.Attendance1;

/**
 * Created by sylveryte on 18/2/16.
 */
public  class DatabaseSchemas {


    public final static class KlassTable
    {
        public static final String NAME="KLASSTABLE";
        public static final class Cols
        {

            public static final String KLASS_NAME="KLASSNAME";
            public static final String NUM_OF_STUDENTS="NUMOFSTUDENTS";
            public static final String ID="ID";
        }
    }


    public final static class LectureTable
    {
        public static final String NAME="LECTURESTABLE";
       public static final class Cols
        {
            public static final String LECTURE_NAME="LECTURENAME";
            public static final String KLASS_ID="KLASSID";
            public static final String STARTING_ROLL_NO="STARTINGROLLNO";
            public static final String LAST_ROLL_NO="LASTROLLNO";
            public static final String REMARKS="REMARKS";
            public static final String ID="ID";
        }
    }


    public final static class AttendanceTable
    {
        public static final String NAME="ATTENDANCETABLE";
        public static final class Cols
        {

            public static final String ID="ID";
            public static final String LECTURE_ID="LECTUREID";
            public static final String DATE="DATE";
            public static final String PRESENT="PRESENT";
        }
    }
}
