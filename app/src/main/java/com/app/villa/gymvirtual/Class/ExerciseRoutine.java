package com.app.villa.gymvirtual.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.villa.gymvirtual.DataBase.DataBaseContract;
import com.app.villa.gymvirtual.DataBase.DataBaseHelper;

import java.util.ArrayList;

/**
 * Created by Freddy on 28/5/2017.
 */

public class ExerciseRoutine {

    private int id;
    private String description;
    private String image;
    private int duration;
    private int repetitions;
    private int weight;
    private int series;

    public ExerciseRoutine() {
    }

    public ExerciseRoutine(int id, String description, String image, int duration, int repetitions, int weight, int series) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.duration = duration;
        this.repetitions = repetitions;
        this.weight = weight;
        this.series = series;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "ExerciseRoutine{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", duration=" + duration +
                ", repetitions=" + repetitions +
                ", weight=" + weight +
                ", series=" + series +
                '}';
    }

    public long insertarRE(Context context, Exercise exercise, int idR) {

        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();

        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        //values.put(DataBaseContract.DataBaseEntry._ID, getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_EXERCISE, exercise.getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_ROUTINE, idR);
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPTION, exercise.getDescription());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DURATION, exercise.getDuration());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_REPETITIONS, exercise.getRepetitions());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SERIES, exercise.getSeries());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_IMAGE, exercise.getImage());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH, exercise.getWeight());

        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_EXERCISE_ROUTINE, null, values);
    }

    public ArrayList<ExerciseRoutine> leerExercisesRoutines (Context context, String id){
        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = DatabaseHelper.getReadableDatabase();

        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_EXERCISE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_ROUTINE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPTION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_IMAGE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_DURATION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_REPETITIONS,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_SERIES,

        };

        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_ID_ROUTINE+ " = ?";
        String[] selectionArgs = {id};

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_EXERCISE_ROUTINE, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        // recorrer los resultados y asignarlos a la clase // aca podria implementarse un ciclo si es necesario
        final ArrayList<ExerciseRoutine> listasExercisesId = new ArrayList<>();
        //System.out.println(String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()) {
            do {
                ExerciseRoutine miLista = new ExerciseRoutine();
                miLista.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry._ID)));
                miLista.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPTION)));
                miLista.setImage(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_IMAGE)));
                miLista.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_DURATION)));
                miLista.setRepetitions(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_REPETITIONS)));
                miLista.setWeight(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH)));

                miLista.setSeries(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_SERIES)));
                listasExercisesId.add(miLista);
            } while (cursor.moveToNext());
        }
        return listasExercisesId;
    }
    public int actualizarExercise(Context context) {
        // usar la clase DatabaseHelper para realizar la operacion de actualizar
        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos
        SQLiteDatabase db = DatabaseHelper.getReadableDatabase();
        // Crear un mapa de valores para los datos a actualizar
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_REPETITIONS, getRepetitions());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DURATION, getDuration());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SERIES, getSeries());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH, getWeight());
        // Criterio de actualizacion
        String selection = DataBaseContract.DataBaseEntry._ID + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {String.valueOf(getId())};
        // Actualizar la base de datos
        return db.update(DataBaseContract.DataBaseEntry.TABLE_NAME_EXERCISE_ROUTINE, values, selection, selectionArgs);
    }
}
