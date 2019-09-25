package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import com.codedleaf.sylveryte.attendanceapp.Attendance1.Attendance;
import com.codedleaf.sylveryte.attendanceapp.Attendance1.Klass;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sylveryte on 13/2/16.
 */
public class Lecture {
    private String mLectureName;
    private int mStudentStartingRollNo;
    private int mStudentLastRollNo;
    private Klass mKlass;
    private List<Attendance> mAttendances;
    private String mRemarks;
    private UUID mId;


    public Lecture(String lectureName,Klass klass,int studentStartingRollNo,int studentLastRollNo,String remarks)
    {
        this(lectureName,klass,studentStartingRollNo,studentLastRollNo,remarks,UUID.randomUUID());
    }


    public Lecture(String lectureName,Klass klass,int studentStartingRollNo,
                   int studentLastRollNo,String remarks,UUID id) {
        mKlass = klass;
        mStudentStartingRollNo = studentStartingRollNo;
        mStudentLastRollNo = studentLastRollNo;
        mLectureName = lectureName;
        mRemarks = remarks;
        mId=id;
        mAttendances=new ArrayList<>();
        klass.addLecture(this);
    }

    public List<Attendance> getAttendances() {
        return mAttendances;
    }

    public String getLectureName() {
        return mLectureName;
    }

    public void addAttendance(Attendance attendance)
    {
        mAttendances.add(attendance);
    }

    public Klass getKlass() {
        return mKlass;
    }

    public UUID getId() {
        return mId;
    }
    public UUID getKlassId() {
        return mKlass.getId();
    }

    public String getExtraInfo(){

        return String.format("%s\n%s", getKlass().getKlassName(), mRemarks);


    }



    public int getStudentStartingRollNo() {
        return mStudentStartingRollNo;
    }


    public int getStudentLastRollNo() {
        return mStudentLastRollNo;
    }
}
