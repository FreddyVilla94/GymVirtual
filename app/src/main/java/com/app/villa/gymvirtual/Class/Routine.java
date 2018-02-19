package com.app.villa.gymvirtual.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.villa.gymvirtual.DataBase.DataBaseContract;
import com.app.villa.gymvirtual.DataBase.DataBaseHelper;

import java.util.ArrayList;

public class Routine {

    private int id;
    private String name;
    private String userName;
    private ArrayList<Exercise> listExercise;

    public Routine() {
    }

    public Routine(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Exercise> getListExercise() {
        return listExercise;
    }

    public void setListExercise(ArrayList<Exercise> listExercise) {
        this.listExercise = listExercise;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", listExercise=" + listExercise +
                '}';
    }

    public long insertar(Context context) {

        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();

        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        //values.put(DataBaseContract.DataBaseEntry._ID, getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME, getName());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME, getUserName());

        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ROUTINE, null, values);
    }

    public void leer (Context context, String identificacion){
        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);

        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = DatabaseHelper.getReadableDatabase();

        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME,
        };

        // Filtro para el WHERE
        //String selection = DatabaseContract.DataBaseEntry._ID + " = ?";
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = {identificacion};

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ROUTINE, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        if(cursor.moveToFirst() && cursor.getCount() > 0) {
            this.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry._ID)));
            this.setName(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME)));
            this.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME)));
        }
        db.close();
    }
    // eliminar un estudiante desde la base de datos
    public void eliminar (Context context, String identificacion) {
        // usar la clase DataBaseHelper para realizar la operacion de eliminar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Define el where para el borrado
        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {identificacion};
        // Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_ROUTINE, selection, selectionArgs);
        // eliminar la persona despues del estudiante
        eliminarRoutineExer(context,identificacion);
    }

    private void eliminarRoutineExer(Context context, String identificacion) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Define el where para el borrado
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_ROUTINE + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {identificacion};
        // Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_EXERCISE_ROUTINE, selection, selectionArgs);
    }
}
