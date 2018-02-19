package com.app.villa.gymvirtual.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.villa.gymvirtual.Class.Routine;
import com.app.villa.gymvirtual.R;

import java.util.ArrayList;

/**
 * Created by Freddy on 25/5/2017.
 */

public class AdapterListRoutines extends BaseAdapter implements Filterable {

    protected Activity activity;
    private static LayoutInflater inflater = null;

    protected ArrayList<Routine> originalItems;
    protected ArrayList<Routine> filteredItems;

    public AdapterListRoutines(Activity activity, ArrayList<Routine> items) {
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
            v = inflater.inflate(R.layout.items_list_routine, null);
        }
        final Routine dir = filteredItems.get(position);

        TextView name = (TextView) v.findViewById(R.id.textNameRoutine);
        name.setText(dir.getName());
        ImageView deleteIco = (ImageView) v.findViewById(R.id.deleteIco);
        deleteIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(dir.getName(),dir.getId(), position);
            }
        });

        v.setPadding(50,50,50,50);
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

                    ArrayList<Routine> filterResultsData = new ArrayList<>();
                    for (Routine data : originalItems){
                        if (data.getName().toLowerCase().contains(filterString)){
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
                filteredItems = (ArrayList<Routine>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    public AlertDialog confirmDelete(final String speechText,final int id, final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Eliminar")
                .setMessage(speechText)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filteredItems.remove(position);
                        Routine routine = new Routine();
                        routine.eliminar(activity,String.valueOf(id));
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return builder.show();

    }
}