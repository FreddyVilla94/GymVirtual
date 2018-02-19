package com.app.villa.gymvirtual.DataBase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Freddy on 19/5/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gymvirtual.db";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,
                          DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseContract.SQL_CREATE_USER);
        db.execSQL(DataBaseContract.SQL_CREATE_EXERCISE);
        db.execSQL(DataBaseContract.SQL_CREATE_ROUTINES);
        db.execSQL(DataBaseContract.SQL_CREATE_EXERCISE_ROUTINE);
        db.execSQL("insert into user (name,lastName,nameUser,password,age,weight,height) values('Freddy','Villalobos Gonzalez','fevig','123',22,87,175)");

        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Barbell Hack Squat','barbell_hack_squat',5,10,20,'I',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Bublin Press','bublin_press',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Deadlift','deadlift',5,10,20,'I',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Deep Squat','deep_squat',5,10,20,'I',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Dumbbell Font Raise','dumbbell_front_raise',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Floor Barbell Calf Raise','floor_barbell_calf_raise',5,10,20,'I',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Military Press','military_press',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Narrow Hack Squat','narrow_hack_squat',5,10,20,'I',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Narrow Squat','narrow_squat',5,10,20,'I',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Pec Dec','pec_dec',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Prone Incline Hammer Curl','prone_incline_hammer_curl',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Reverse Grip Incline Bench Press','reverse_grip_incline_bench_press',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Reverse Grip Skullcrusher','reverse_grip_skullcrusher',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Seated Barbell Press','seated_barbell_press',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Smith Machine Squat','smith_machine_squat',5,10,20,'I',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Spider Curl','spider_curl',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Wide Grip Bench Press','wide_grip_bench_press',5,10,20,'S',4,0)");
        db.execSQL("insert into exercise (description,image,duration,repetitions,weight,type,series,mark) values('Wide Grip Cable Curl','wide_grip_cable_curl',5,10,20,'S',4,0)");

        db.execSQL("insert into routine  (name,        nameUser                   ) values('Routine Break Press','fevig')");
        db.execSQL("insert into routine  (name,        nameUser                   ) values('Routine Break legs','fevig')");

        db.execSQL("insert into exerciseRoutine  (idRoutine,idExercise,description,image,duration,repetitions,series,weight) values(1,1,'Barbell Hack Squat','barbell_hack_squat',5,10,4,20)");
        db.execSQL("insert into exerciseRoutine  (idRoutine,idExercise,description,image,duration,repetitions,series,weight) values(1,2,'Bublin Press','bublin_press',5,10,4,20)");
        db.execSQL("insert into exerciseRoutine  (idRoutine,idExercise,description,image,duration,repetitions,series,weight) values(1,3,'Deadlift','deadlift',5,10,4,20)");
        db.execSQL("insert into exerciseRoutine  (idRoutine,idExercise,description,image,duration,repetitions,series,weight) values(2,1,'Deep Squat','deep_squat',5,10,4,20)");
        db.execSQL("insert into exerciseRoutine  (idRoutine,idExercise,description,image,duration,repetitions,series,weight) values(2,2,'Dumbbell Font Raise','dumbbell_front_raise',5,10,4,20)");
        db.execSQL("insert into exerciseRoutine  (idRoutine,idExercise,description,image,duration,repetitions,series,weight) values(2,3,'Floor Barbell Calf Raise','floor_barbell_calf_raise',5,10,4,20)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseContract.SQL_DELETE_USER);
        db.execSQL(DataBaseContract.SQL_DELETE_EXERCISE);
        db.execSQL(DataBaseContract.SQL_DELETE_ROUTINE);
        db.execSQL(DataBaseContract.SQL_DELETE_EXERCISE_ROUTINE);
        onCreate(db);
    }
}
