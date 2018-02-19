package com.app.villa.gymvirtual.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.villa.gymvirtual.DataBase.DataBaseContract;
import com.app.villa.gymvirtual.DataBase.DataBaseHelper;

import java.util.ArrayList;

public class User {

    private String name;
    private String lastName;
    private String userName;
    private String password;
    private int age;
    private double weight;
    private double height;
    private ArrayList<Routine> listRoutines;

    public User() {
    }

    public User(String name, String lastName, String userName, String password, int age, double weight, double height) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public ArrayList<Routine> getListRoutines() {
        return listRoutines;
    }

    public void setListRoutines(ArrayList<Routine> listRoutines) {
        this.listRoutines = listRoutines;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

    /**
     * Función que inserta un usuario en la base de datos
     */
    public long insertar(Context context) {
        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();

        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME, getUserName());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_PASSWORD, getPassword());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME, getName());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_LAST_NAME, getLastName());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_AGE, getAge());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH, getWeight());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_HEIGTH, getHeight());

        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_USER, null, values);
    }

    /**
     * Leer una persona desde la base de datos
     */
    public void leer (Context context, String identificacion){
        DataBaseHelper DatabaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = DatabaseHelper.getReadableDatabase();

        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_PASSWORD,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_LAST_NAME,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_AGE,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_HEIGTH,
        };

        // Filtro para el WHERE
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME + " = ?";
        String[] selectionArgs = {identificacion};

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_USER, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        // recorrer los resultados y asignarlos a la clase // aca podria implementarse un ciclo si es necesario
        System.out.println(String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst() && cursor.getCount() > 0) {
            this.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME)));
            this.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_PASSWORD)));
            this.setName(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME)));
            this.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_LAST_NAME)));
            this.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_AGE)));
            this.setWeight(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_WEIGTH)));
            this.setHeight(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_HEIGTH)));
        }
        this.setListRoutines(leerRoutineUser(context,identificacion
        ));
    }
    public ArrayList<Routine> leerRoutineUser (Context context, String usuario){
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
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME+ " = ?";
        String[] selectionArgs = {usuario};

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

        final ArrayList<Routine> misListas = new ArrayList<>();
        System.out.println(String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()) {
            do {
                Routine miLista = new Routine();
                miLista.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry._ID)));
                miLista.setName(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME)));
                miLista.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(
                        DataBaseContract.DataBaseEntry.COLUMN_NAME_USER_NAME)));
                misListas.add(miLista);
            } while (cursor.moveToNext());
        }
        return misListas;
    }
}
