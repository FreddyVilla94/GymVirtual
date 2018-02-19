package com.app.villa.gymvirtual.GUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.app.villa.gymvirtual.Class.User;
import com.app.villa.gymvirtual.R;

/**
 * Created by Freddy on 20/5/2017.
 */

public class RegisterActivity extends AppCompatActivity{

    private EditText editTextName, editTextLastName, editTextUserName1, editTextPassword1, editTextAge, editTextWeigth, editTextHeigth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        editTextName = (EditText) findViewById(R.id.editName);
        editTextLastName = (EditText) findViewById(R.id.editLastName);
        editTextUserName1 =  (EditText) findViewById(R.id.editUserName1);
        editTextPassword1 = (EditText) findViewById(R.id.editPass);
        editTextAge = (EditText) findViewById(R.id.editAge);
        editTextWeigth = (EditText) findViewById(R.id.editWeigth);
        editTextHeigth = (EditText) findViewById(R.id.editHeigth);

        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.register_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSave:
                insertUser();
                //Toast.makeText(getApplicationContext(),"Data verification",Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void insertUser() {
        if(editTextName.getText().toString().equals("") || editTextLastName.getText().toString().equals("") ||
                editTextUserName1.getText().toString().equals("") || editTextPassword1.getText().toString().equals("") ||
                editTextAge.getText().toString().equals("") || editTextWeigth.getText().toString().equals("") ||
                editTextHeigth.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Some data is empy",Toast.LENGTH_SHORT).show();

        }
        else{
            String name = editTextName.getText().toString();
            String lastName = editTextLastName.getText().toString();
            String userName = editTextUserName1.getText().toString();
            String password = editTextPassword1.getText().toString();
            String ageText = editTextAge.getText().toString();
            int age = Integer.parseInt(ageText);
            String weightText = editTextWeigth.getText().toString();
            double weight = Double.parseDouble(weightText);
            String heightText = editTextHeigth.getText().toString();
            double height = Double.parseDouble(heightText);

            /*User user = new User();
            user.leer(getApplicationContext(),userName);

            if(user.getUserName().equals(userName)){
                Toast.makeText(getApplicationContext(),"The user name already exists!",Toast.LENGTH_SHORT).show();
            }else {*/

            User newUser = new User(name, lastName, userName, password, age, weight, height);
            long newRowID = newUser.insertar(getApplicationContext());
            Toast.makeText(getApplicationContext(), "The user is inserted correctly", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
            editTextLastName.setText("");
            editTextUserName1.setText("");
            editTextPassword1.setText("");
            editTextAge.setText("");
            editTextWeigth.setText("");
            editTextHeigth.setText("");
            //}
        }
    }
}
