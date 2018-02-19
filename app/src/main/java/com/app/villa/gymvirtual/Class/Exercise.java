package com.app.villa.gymvirtual.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.villa.gymvirtual.DataBase.DataBaseContract;
import com.app.villa.gymvirtual.DataBase.DataBaseHelper;

import java.util.ArrayList;

public class Exercise {

    private int id;
    private String description;
    private String image;
    private int duration;
    private int repetitions;
    private int weight;
    private String type;
    private int series;
    private int mark;

    public Exercise() {
    }

    public Exercise(int id, String description, String image, int duration, int repetitions, int weight, String type, int series, int mark) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.duration = duration;
        this.repetitions = repetitions;
        this.weight = weight;
        this.type = type;
        this.series = series;
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", duration=" + duration +
                ", repetitions=" + repetitions +
                ", weight=" + weight +
                ", type='" + type + '\'' +
                ", series=" + series +
                '}';
    }

    public long insertar(Context context) {
        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();

        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry._ID, getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPTION, getDescription());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_IMAGE, getImage());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DURATION, getDuration());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_REPETITIONS, getRepetitions());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH, getWeight());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SERIES, getSeries());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_TYPE, getType());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MARK, getMark());
        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_EXERCISE, null, values);
    }

    public ArrayList<Exercise> leer (Context context){
        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = DatabaseHelper.getReadableDatabase();

        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPTION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_IMAGE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_DURATION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_REPETITIONS,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_SERIES,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_TYPE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MARK,
        };

        //String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_TYPE+ " = ?";
        //String[] selectionArgs = {type};

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_EXERCISE, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        // recorrer los resultados y asignarlos a la clase // aca podria implementarse un ciclo si es necesario
        final ArrayList<Exercise> misListas = new ArrayList<>();
        //System.out.println(String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()) {
            do {
                Exercise miLista = new Exercise();
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
                miLista.setType(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_TYPE)));
                miLista.setSeries(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_SERIES)));
                miLista.setMark(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_MARK)));
                misListas.add(miLista);
            } while (cursor.moveToNext());
        }
        return misListas;
    }



}
