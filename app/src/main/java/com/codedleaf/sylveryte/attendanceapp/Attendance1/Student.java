package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import java.util.UUID;

/**
 * Created by sylveryte on 13/2/16.
 */
public class Student {
    private String mName;
    private UUID mId;
    private int mRollNo;
    private boolean isPresent;

    public Student(int rollNo)
    {
        mId=UUID.randomUUID();
        mRollNo=rollNo;
        isPresent=true;
    }

    public int getRollNo() {
        return mRollNo;
    }

    public void setRollNo(int rollNo) {
        mRollNo = rollNo;
    }

    public UUID getId() {
        return mId;
    }


    public String getName() {
        return mName;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public void setName(String name) {
        mName = name;
    }
}
