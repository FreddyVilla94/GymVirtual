package com.app.villa.gymvirtual.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.villa.gymvirtual.Class.Comunicador;
import com.app.villa.gymvirtual.Class.User;
import com.app.villa.gymvirtual.R;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private EditText editUserName, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUserName = (EditText) findViewById(R.id.editUserName);
                editPassword = (EditText) findViewById(R.id.editPassword);
                String userName = editUserName.getText().toString();
                String password = editPassword.getText().toString();
                leerPersona(userName,password);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegister();
            }
        });

    }
    private void callRegister(){
        Intent register = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(register);
    }
    private void leerPersona(String userName,String password){
        if(userName.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(),"Some data is empy",Toast.LENGTH_SHORT).show();
        }else {
            User user = new User();
            user.leer(getApplicationContext(), userName);
            if (user.getUserName() == null && user.getPassword() == null) {
                Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
            } else {
                if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                    editUserName.setText("");
                    editPassword.setText("");
                    Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                    Comunicador.setObjeto(user);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
                }
            }
        }
        /*
        Toast.makeText(getApplicationContext(),
                "See User: " + user.getUserName() +
                        " Password: " + user.getPassword() +
                        " Name: " + user.getName() +
                        " Last Name: " + user.getLastName() +
                        " Age: " + user.getAge() +
                        " Weigth: " + user.getWeight() +
                        " Heigth: " + user.getHeight() +
                        " Photo: " + user.getPhoto(),
                Toast.LENGTH_LONG).show();*/
    }
}
