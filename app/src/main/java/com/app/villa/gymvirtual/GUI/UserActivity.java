package com.app.villa.gymvirtual.GUI;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.app.villa.gymvirtual.Adapter.AdapterListRoutines;
import com.app.villa.gymvirtual.Class.Comunicador;
import com.app.villa.gymvirtual.Class.Routine;
import com.app.villa.gymvirtual.Class.User;
import com.app.villa.gymvirtual.R;

import java.util.ArrayList;

/**
 * Created by Freddy on 24/5/2017.
 */

public class UserActivity extends AppCompatActivity{


    private TextView textName,textAge,textWeight,textHieght,textNameRoutine;
    private EditText editNameRoutine;
    private ListView listR;
    public ArrayList<Routine> routineArrayList = new ArrayList<>();
    private AdapterListRoutines adapter;
    private  Menu menu;
    private final User user = Comunicador.getObjeto();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Resources res = getResources();

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("",
                res.getDrawable(R.drawable.icon_routine));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("",
                res.getDrawable(R.drawable.icon_profile));
        tabs.addTab(spec);

        tabs.setCurrentTab(1);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("mitab1")){
                    getSupportActionBar().setTitle("Routines");
                    menu.findItem(R.id.itemAdd).setVisible(true);
                    textNameRoutine.setVisibility(View.INVISIBLE);
                    editNameRoutine.setVisibility(View.INVISIBLE);
                    //listR.setVisibility(View.VISIBLE);
                    //menu.findItem(R.id.itemSave1).setVisible(true);
                    loadRoutines();
                }else if(tabId.equals("mitab2")){
                    menu.findItem(R.id.itemAdd).setVisible(false);
                    menu.findItem(R.id.itemSave1).setVisible(false);
                    getSupportActionBar().setTitle("Profile");
                }
            }
        });

        textName = (TextView) findViewById(R.id.textViewName);
        textAge = (TextView) findViewById(R.id.textViewAge);
        textWeight = (TextView) findViewById(R.id.textViewWeight);
        textHieght = (TextView) findViewById(R.id.textViewHeight);
        textName.setText(user.getName()+" "+user.getLastName());
        textNameRoutine = (TextView) findViewById(R.id.textNameRoutineE);
        editNameRoutine = (EditText) findViewById(R.id.editNameRoutine);
        int age = user.getAge();
        double weight = user.getWeight();
        double height = user.getHeight();
        textAge.setText(String.valueOf(age));
        textWeight.setText(String.valueOf(weight));
        textHieght.setText(String.valueOf(height));


    }

    private void loadRoutines() {
        //Toast.makeText(getApplicationContext(),user.getListRoutines().toString(), Toast.LENGTH_SHORT).show();

        routineArrayList = user.getListRoutines();
        adapter = new AdapterListRoutines(this, routineArrayList);
        listR = (ListView) findViewById(R.id.listRoutines);
        listR.setAdapter(adapter);
        listR.setVisibility(View.VISIBLE);
        listR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Routine routine = (Routine) parent.getAdapter().getItem(position);
                Intent intent = new Intent(UserActivity.this,ExerciseRoutineActivity.class);
                intent.putExtra("idRoutine",routine.getId());
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.routine_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.itemAdd:
                menu.findItem(R.id.itemSave1).setVisible(true);
                menu.findItem(R.id.itemAdd).setVisible(false);
                listR.setVisibility(View.INVISIBLE);
                textNameRoutine.setVisibility(View.VISIBLE);
                editNameRoutine.setVisibility(View.VISIBLE);
                return true;
            case R.id.itemSave1:
                String nameRoutine = editNameRoutine.getText().toString();
                if(nameRoutine.equals("")){
                    Toast.makeText(getApplicationContext(),"Invalid Data",Toast.LENGTH_SHORT).show();
                }else {
                    Routine routine = new Routine();
                    routine.setName(nameRoutine);
                    routine.setUserName(user.getUserName());
                    routine.insertar(getApplicationContext());
                    menu.findItem(R.id.itemAdd).setVisible(true);
                    menu.findItem(R.id.itemSave1).setVisible(false);
                    listR.setVisibility(View.VISIBLE);
                    editNameRoutine.setText("");
                    textNameRoutine.setVisibility(View.INVISIBLE);
                    editNameRoutine.setVisibility(View.INVISIBLE);
                    routineArrayList.add(routine);
                    adapter.notifyDataSetChanged();
                    routine.leer(getApplicationContext(),nameRoutine);
                    Intent intent = new Intent(UserActivity.this, Exercise_Activity.class);
                    intent.putExtra("idRoutine",routine.getId());
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
