package com.codedleaf.sylveryte.attendanceapp.Attendance1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by sylveryte on 22/2/16.
 */


public class DatabaseLab{

    private Context mContext;
    private static SQLiteDatabase sDatabase;
    private static DatabaseLab mDatabaseLab;

    private DatabaseLab(Context context)
    {
        mContext=context.getApplicationContext();
        sDatabase =new DatabaseHelper(mContext).getWritableDatabase();
    }

    public static DatabaseLab getInstance(Context context) {

        if (mDatabaseLab==null)
        {
            mDatabaseLab=new DatabaseLab(context);
        }
        return mDatabaseLab;
    }

    public static SQLiteDatabase getDatabase() {
        return sDatabase;
    }
}
