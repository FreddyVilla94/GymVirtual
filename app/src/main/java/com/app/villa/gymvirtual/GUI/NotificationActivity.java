package com.app.villa.gymvirtual.GUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.villa.gymvirtual.R;

import static com.app.villa.gymvirtual.R.id.textMejorDura;
import static com.app.villa.gymvirtual.R.id.textMejorRepe;
import static com.app.villa.gymvirtual.R.id.textMejorSeri;
import static com.app.villa.gymvirtual.R.id.textMejorWeig;

/**
 * Created by Freddy on 28/5/2017.
 */

public class NotificationActivity extends AppCompatActivity{

    private int mejorRepe, mejorDura, mejorSeri, mejorWeig;
    private TextView textMejorRepe1, textMejorDura1, textMejorSeri1, textMejorWeig1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mejorRepe = getIntent().getExtras().getInt("mejorRepe");
        mejorDura = getIntent().getExtras().getInt("mejorDura");
        mejorSeri = getIntent().getExtras().getInt("mejorSeri");
        mejorWeig = getIntent().getExtras().getInt("mejorWeig");

        textMejorRepe1 = (TextView) findViewById(textMejorRepe);
        textMejorDura1 = (TextView) findViewById(textMejorDura);
        textMejorSeri1 = (TextView) findViewById(textMejorSeri);
        textMejorWeig1 = (TextView) findViewById(textMejorWeig);
        loadLayout();

    }

    private void loadLayout() {
        if(mejorRepe == 1){
            textMejorRepe1.setVisibility(View.VISIBLE);
        }
        if(mejorDura == 1){
            textMejorDura1.setVisibility(View.VISIBLE);
        }
        if (mejorSeri == 1){
            textMejorSeri1.setVisibility(View.VISIBLE);
        }
        if(mejorWeig == 1){
            textMejorWeig1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
