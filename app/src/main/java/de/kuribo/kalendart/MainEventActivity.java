package de.kuribo.kalendart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainEventActivity extends AppCompatActivity {


    //ATTRIBUTE
    private static final String TAG = "MainEventActivity";

    private TextView ueberschrift0; //später in Arrays umwandeln
    private TextView datum0;
    private TextView uhrzeit0;
    private TextView beschreibung0;
    private Button btnGoToMain;
    private TextView txtEvent;
    private EventAdapter eAdapter = null;

    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutmanager;


    //CODE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_event); //Layout verknüpfen
        eAdapter = new EventAdapter(this.getApplicationContext());
        buildRecyclerView();

        btnGoToMain = (Button) findViewById(R.id.btnGoToMain); //Button mit Variable verknüpfen






        //Zur Kalenderansicht geklickt
        btnGoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainEventActivity.super.onBackPressed(); //Zurücktaste / schnellster und einfachster Weg ;)
            }
        });
    }

    @Override
    public void finish() {
        super.finish(); //Activity beenden
        overridePendingTransition(R.anim.slide_in_left_medium, R.anim.slide_out_right_medium); //animation festlegen
    }

    /*
    //Methode zum Laden
    public void load(String pEvent, TextView pTxt){
        FileInputStream fis = null;

        try {
            fis = openFileInput(pEvent);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }

            pTxt.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } */

    public void InsertEvent(int pPosition){
        eAdapter.getEventListe().add(pPosition, new Event("Name", "Datum", "Uhrzeit", "Beschreibung"));
        eAdapter.notifyItemInserted(pPosition);
    }

    public void RemoveEvent(int pPosition){
        eAdapter.getEventListe().remove(pPosition);
        eAdapter.notifyItemRemoved(pPosition);
        eAdapter.save();
    }

    public void createEventListe(){
        eAdapter.setEventListe(new ArrayList<Event>());
        eAdapter.getEventListe().add(new Event("Line1", "Line2", "Line3", "Line4"));
        eAdapter.getEventListe().add(new Event("Line1", "Line2", "Line3", "Line4"));
        eAdapter.getEventListe().add(new Event("Line1", "Line2", "Line3", "Line4"));
    }

    public void buildRecyclerView(){
        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutmanager);
        mRecyclerview.setAdapter(eAdapter);

        eAdapter.SetOnEventDeleteListener(new EventAdapter.OnDeleteClickListener() {
            @Override
            public void OnEventDelete(int pPosition) {
                eAdapter.getEventListe().remove(pPosition);
                eAdapter.notifyItemRemoved(pPosition);
                eAdapter.save();
            }
        });
    }


}

