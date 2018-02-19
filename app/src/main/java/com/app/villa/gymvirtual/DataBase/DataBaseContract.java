package com.app.villa.gymvirtual.DataBase;

import android.provider.BaseColumns;

/**
 * Created by Freddy on 19/5/2017.
 */

public class DataBaseContract {

    public static class DataBaseEntry implements BaseColumns{

        //Dates of columns for the table User
        public static final String TABLE_NAME_USER = "user";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LAST_NAME = "lastName";
        public static final String COLUMN_NAME_USER_NAME = "nameUser";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_WEIGTH = "weight";
        public static final String COLUMN_NAME_HEIGTH = "height";

        //Dates of columns for the table Routine
        public static final String TABLE_NAME_ROUTINE = "routine";
        //COLUMN_NAME_NAME -> have used of the column name existent
        public static final String COLUMN_NAME_TYPE = "type";

        //Dates of columns for the table Exercise
        public static final String TABLE_NAME_EXERCISE = "exercise";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_REPETITIONS = "repetitions";
        public static final String COLUMN_NAME_SERIES = "series";
        public static final String COLUMN_NAME_MARK= "mark";

        //Dates of columns for the table RoutineType
        public static final String TABLE_NAME_EXERCISE_TYPE = "exerciseType";
        //public static final String COLUMN_NAME_TYPE = "type";


        //Dates of columns for the table ExerciseRoutine
        public static final String TABLE_NAME_EXERCISE_ROUTINE = "exerciseRoutine";
        public static final String COLUMN_NAME_ID_ROUTINE = "idRoutine";
        public static final String COLUMN_NAME_ID_EXERCISE= "idExercise";

    }

    //Types of date of the tables
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    /**
     * Creation and deletes of tables
     */

    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_USER + " (" +
                    DataBaseEntry.COLUMN_NAME_USER_NAME + TEXT_TYPE + " PRIMARY KEY," +
                    DataBaseEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_AGE + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_WEIGTH + REAL_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_HEIGTH + REAL_TYPE + " )";

    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " +DataBaseEntry.TABLE_NAME_USER;

    public static final String SQL_CREATE_EXERCISE =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_EXERCISE + " (" +
                    DataBaseEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    DataBaseEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_DURATION + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_WEIGTH + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_SERIES + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_MARK + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_REPETITIONS + INTEGER_TYPE + " )";

    public static final String SQL_DELETE_EXERCISE =
            "DROP TABLE IF EXISTS " +DataBaseEntry.TABLE_NAME_EXERCISE;


    public static final String SQL_CREATE_ROUTINES =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ROUTINE + " (" +
                    DataBaseEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    DataBaseEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + DataBaseEntry.COLUMN_NAME_USER_NAME +
                    ") REFERENCES " + DataBaseEntry.TABLE_NAME_USER + "(" + DataBaseEntry._ID + ") )";

    public static final String SQL_DELETE_ROUTINE =
            "DROP TABLE IF EXISTS " +DataBaseEntry.TABLE_NAME_ROUTINE;

    public static final String SQL_CREATE_EXERCISE_ROUTINE =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_EXERCISE_ROUTINE + " (" +
                    DataBaseEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    DataBaseEntry.COLUMN_NAME_ID_ROUTINE + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ID_EXERCISE + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_DURATION + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_WEIGTH + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_SERIES + INTEGER_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_REPETITIONS + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + DataBaseEntry.COLUMN_NAME_ID_EXERCISE +
                    ") REFERENCES " + DataBaseEntry.TABLE_NAME_EXERCISE + "(" + DataBaseEntry._ID + ")" + COMMA_SEP+
                    " FOREIGN KEY (" + DataBaseEntry.COLUMN_NAME_ID_ROUTINE +
                    ") REFERENCES " + DataBaseEntry.TABLE_NAME_ROUTINE + "(" + DataBaseEntry._ID + "))";

    public static final String SQL_DELETE_EXERCISE_ROUTINE =
            "DROP TABLE IF EXISTS " +DataBaseEntry.TABLE_NAME_EXERCISE_ROUTINE;

}
