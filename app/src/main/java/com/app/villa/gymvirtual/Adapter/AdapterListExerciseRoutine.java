package com.app.villa.gymvirtual.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.villa.gymvirtual.Class.ExerciseRoutine;
import com.app.villa.gymvirtual.R;

import java.util.ArrayList;

/**
 * Created by Freddy on 28/5/2017.
 */

public class AdapterListExerciseRoutine extends BaseAdapter implements Filterable {

    protected Activity activity;
    private static LayoutInflater inflater = null;

    protected ArrayList<ExerciseRoutine> originalItems;
    protected ArrayList<ExerciseRoutine> filteredItems;

    public AdapterListExerciseRoutine(Activity activity, ArrayList<ExerciseRoutine> items) {
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.originalItems = items;
        this.filteredItems = items;
    }

    @Override
    public int getCount() {
        return filteredItems.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            v = inflater.inflate(R.layout.items_list_exercises, null);
        }
        final ExerciseRoutine dir = filteredItems.get(position);
        //GridLayout gridLayout = (GridLayout) v.findViewById(R.id.grid1);
        //gridLayout.setVisibility(View.INVISIBLE);
        TextView name = (TextView) v.findViewById(R.id.textDescription);
        name.setText(dir.getDescription());
        TextView repetition = (TextView) v.findViewById(R.id.textRepetition);
        repetition.setText("Repetitions: "+String.valueOf(dir.getRepetitions()));
        TextView duration = (TextView) v.findViewById(R.id.textDuration);
        duration.setText("Duration: "+String.valueOf(dir.getDuration())+" minutes");
        TextView peso = (TextView) v.findViewById(R.id.textPeso);
        peso.setText("Weight: "+String.valueOf(dir.getWeight()));
        TextView series = (TextView) v.findViewById(R.id.textSeries);
        series.setText("Series: "+String.valueOf(dir.getSeries()));
        ImageView image = (ImageView) v.findViewById(R.id.iconExercise);
        if(dir.getImage().equals("barbell_hack_squat")) {
            image.setImageResource(R.drawable.barbell_hack_squat);
        }else if(dir.getImage().equals("bublin_press")) {
            image.setImageResource(R.drawable.bublin_press);
        }else if(dir.getImage().equals("deadlift")) {
            image.setImageResource(R.drawable.deadlift);
        }else if(dir.getImage().equals("deep_squat")) {
            image.setImageResource(R.drawable.deep_squat);
        }else if(dir.getImage().equals("dumbbell_front_raise")) {
            image.setImageResource(R.drawable.dumbbell_front_raise);
        }else if(dir.getImage().equals("floor_barbell_calf_raise")) {
            image.setImageResource(R.drawable.floor_barbell_calf_raise);
        }else if(dir.getImage().equals("military_press")) {
            image.setImageResource(R.drawable.military_press);
        }else if(dir.getImage().equals("narrow_hack_squat")) {
            image.setImageResource(R.drawable.narrow_hack_squat);
        }else if(dir.getImage().equals("narrow_squat")) {
            image.setImageResource(R.drawable.narrow_squat);
        }else if(dir.getImage().equals("pec_dec")) {
            image.setImageResource(R.drawable.pec_dec);
        }else if(dir.getImage().equals("prone_incline_hammer_curl")) {
            image.setImageResource(R.drawable.prone_incline_hammer_curl);
        }else if(dir.getImage().equals("reverse_grip_incline_bench_press")) {
            image.setImageResource(R.drawable.reverse_grip_incline_bench_press);
        }else if(dir.getImage().equals("reverse_grip_skullcrusher")) {
            image.setImageResource(R.drawable.reverse_grip_skullcrusher);
        }else if(dir.getImage().equals("seated_barbell_press")) {
            image.setImageResource(R.drawable.seated_barbell_press);
        }else if(dir.getImage().equals("smith_machine_squat")) {
            image.setImageResource(R.drawable.smith_machine_squat);
        }else if(dir.getImage().equals("spider_curl")) {
            image.setImageResource(R.drawable.spider_curl);
        }else if(dir.getImage().equals("wide_grip_bench_press")) {
            image.setImageResource(R.drawable.wide_grip_bench_press);
        }else if(dir.getImage().equals("wide_grip_cable_curl")) {
            image.setImageResource(R.drawable.wide_grip_cable_curl);
        }
        //v.setPadding(25,25,25,25);
        return v;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0){
                    results.values = originalItems;
                    results.count = originalItems.size();
                }
                else{
                    String filterString = constraint.toString().toLowerCase();

                    ArrayList<ExerciseRoutine> filterResultsData = new ArrayList<>();
                    for (ExerciseRoutine data : originalItems){
                        if (data.getDescription().toLowerCase().contains(filterString)){
                            filterResultsData.add(data);
                        }
                    }
                    results.values = filterResultsData;
                    results.count = filterResultsData.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItems = (ArrayList<ExerciseRoutine>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}