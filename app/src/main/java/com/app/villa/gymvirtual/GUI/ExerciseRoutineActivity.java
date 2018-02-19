package com.app.villa.gymvirtual.GUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.villa.gymvirtual.Adapter.AdapterListExerciseRoutine;
import com.app.villa.gymvirtual.Class.ExerciseRoutine;
import com.app.villa.gymvirtual.R;

import java.util.ArrayList;

import static com.app.villa.gymvirtual.R.id.listExerciseRoutine;

/**
 * Created by Freddy on 27/5/2017.
 */

public class ExerciseRoutineActivity extends AppCompatActivity{

    private ListView listE;
    private AdapterListExerciseRoutine adapterListExercise;
    private ArrayList<ExerciseRoutine> exerciseArrayList = new ArrayList<>();
    private EditText editRepetitions, editDuration, editSeries, editWeight;
    private RelativeLayout relativeLayout;
    private GridLayout gridLayout;
    private ExerciseRoutine exerciseSelect;
    private  Menu menu;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_routine_activity);

        getSupportActionBar().setTitle("Exercises");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        relativeLayout  =(RelativeLayout) findViewById(R.id.relative1);
        gridLayout = (GridLayout) findViewById(R.id.grid1);
        gridLayout.setVisibility(View.INVISIBLE);
        editRepetitions = (EditText) findViewById(R.id.editNewRepetitions);
        editDuration = (EditText) findViewById(R.id.editNewDuration);
        editSeries = (EditText) findViewById(R.id.editNewSeries);
        editWeight = (EditText) findViewById(R.id.editNewWeight);

        int idRoutine = getIntent().getExtras().getInt("idRoutine");
        loadExercise(idRoutine);
    }

    private void loadExercise(int ID) {

        ExerciseRoutine exercises = new ExerciseRoutine();
        exerciseArrayList= exercises.leerExercisesRoutines(getApplicationContext(),String.valueOf(ID));
        adapterListExercise = new AdapterListExerciseRoutine(this, exerciseArrayList);
        listE = (ListView) findViewById(listExerciseRoutine);
        listE.setAdapter(adapterListExercise);
        listE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                exerciseSelect =  (ExerciseRoutine) parent.getAdapter().getItem(position);
                opcionesUpdate(exerciseSelect);
            }
        });

    }

    private void opcionesUpdate(final ExerciseRoutine exerciseRoutine) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update")
                .setMessage("Want to update performance in the exercise")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listE.setVisibility(View.INVISIBLE);
                        gridLayout.setVisibility(View.INVISIBLE);
                        editRepetitions.setText(String.valueOf(exerciseRoutine.getRepetitions()));
                        editDuration.setText(String.valueOf(exerciseRoutine.getDuration()));
                        editSeries.setText(String.valueOf(exerciseRoutine.getSeries()));
                        editWeight.setText(String.valueOf(exerciseRoutine.getWeight()));
                        menu.findItem(R.id.itemSave2).setVisible(true);
                        relativeLayout.setVisibility(View.VISIBLE);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.routine_exercise_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.itemSave2:
                if(editRepetitions.getText().toString().equals("") || editDuration.getText().toString().equals("")
                        || editSeries.getText().toString().equals("") || editWeight.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Invalid data",Toast.LENGTH_SHORT).show();
                }else {
                    int repe = Integer.parseInt(editRepetitions.getText().toString());
                    int dura = Integer.parseInt(editDuration.getText().toString());
                    int seri = Integer.parseInt(editSeries.getText().toString());
                    int weig = Integer.parseInt(editWeight.getText().toString());
                    int mejoroRepe = 0;
                    int mejoroDura = 0;
                    int mejoroSeri = 0;
                    int mejoroWeig = 0;
                    if (repe > exerciseSelect.getRepetitions()){
                        mejoroRepe = 1;
                    }
                    if(dura < exerciseSelect.getDuration()){
                        mejoroDura = 1;
                    }
                    if(seri > exerciseSelect.getSeries()){
                        mejoroSeri = 1;
                    }
                    if(weig > exerciseSelect.getWeight()){
                        mejoroWeig = 1;
                    }
                    exerciseSelect.setRepetitions(repe);
                    exerciseSelect.setDuration(dura);
                    exerciseSelect.setSeries(seri);
                    exerciseSelect.setWeight(weig);
                    exerciseSelect.actualizarExercise(getApplicationContext());
                    adapterListExercise.notifyDataSetChanged();
                    editRepetitions.setText("");
                    editDuration.setText("");
                    editSeries.setText("");
                    editWeight.setText("");
                    menu.findItem(R.id.itemSave2).setVisible(false);
                    listE.setVisibility(View.VISIBLE);
                    gridLayout.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.INVISIBLE);
                    if(mejoroRepe == 1 || mejoroDura == 1
                            || mejoroSeri == 1 || mejoroWeig == 1) {
                        Intent intent = new Intent(this, NotificationActivity.class);
                        intent.putExtra("mejorRepe", mejoroRepe);
                        intent.putExtra("mejorDura", mejoroDura);
                        intent.putExtra("mejorSeri", mejoroSeri);
                        intent.putExtra("mejorWeig", mejoroWeig);
                        startActivity(intent);
                    }
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
