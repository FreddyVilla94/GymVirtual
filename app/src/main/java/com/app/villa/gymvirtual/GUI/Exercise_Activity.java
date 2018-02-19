package com.app.villa.gymvirtual.GUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.villa.gymvirtual.Adapter.AdapterListExercise;
import com.app.villa.gymvirtual.Class.Exercise;
import com.app.villa.gymvirtual.Class.ExerciseRoutine;
import com.app.villa.gymvirtual.R;

import java.util.ArrayList;

/**
 * Created by Freddy on 27/5/2017.
 */

public class Exercise_Activity extends AppCompatActivity {

    private ListView listExercise;
    private int idRoutine;
    private AdapterListExercise adapterListExercise;
    private ArrayList<Exercise> exerciseArrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_activity);
        idRoutine = getIntent().getExtras().getInt("idRoutine");
        getSupportActionBar().setTitle("Exercises");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadExercise();
    }

    private void loadExercise() {
        Exercise exercise = new Exercise();
        /*ArrayList<Exercise> exercisesS =  exercise.leer(getApplicationContext(),"S");
        ArrayList<Exercise> exercisesI =  exercise.leer(getApplicationContext(),"I");
        for(int i = 0; i < exercisesS.size(); i++){
            //Toast.makeText(getApplicationContext(),exercisesS.get(i).getDescription(), Toast.LENGTH_SHORT).show();
            Exercise exerciseS = exercisesS.get(i);
            exerciseArrayList.add(exerciseS);
        }
        for(int i = 0; i < exercisesI.size(); i++){
            //Toast.makeText(getApplicationContext(),exercisesI.get(i).toString(), Toast.LENGTH_SHORT).show();
            Exercise exerciseI = exercisesI.get(i);
            exerciseArrayList.add(exerciseI);
        }*/
        //Toast.makeText(getApplicationContext(),exerciseArrayList.toString(), Toast.LENGTH_LONG).show();
        exerciseArrayList = exercise.leer(getApplicationContext());
        adapterListExercise = new AdapterListExercise(this, exerciseArrayList);
        listExercise = (ListView) findViewById(R.id.listExercise);
        listExercise.setAdapter(adapterListExercise);
        listExercise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exercise selected = (Exercise) parent.getAdapter().getItem(position);
                if(selected.getMark() == 0){
                    selected.setMark(1);
                }else{
                    selected.setMark(0);
                }
                adapterListExercise.notifyDataSetChanged();
                //Toast.makeText(getApplicationContext(),String.valueOf(selected.getMark()), Toast.LENGTH_SHORT).show();
            }
        });

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
            case android.R.id.home:
                finish();
                return true;
            case R.id.itemSave:
                for(int i = 0; i< exerciseArrayList.size(); i++){
                    Exercise exercise = exerciseArrayList.get(i);
                    if(exercise.getMark() == 1){
                        ExerciseRoutine routine = new ExerciseRoutine();
                        routine.insertarRE(getApplicationContext(),exercise,idRoutine);
                    }
                }
                //Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
