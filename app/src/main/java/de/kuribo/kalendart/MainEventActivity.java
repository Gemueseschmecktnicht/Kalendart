package de.kuribo.kalendart;

import android.os.Bundle;
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

    private ArrayList<Event> mEventListe;
    private TextView ueberschrift0; //später in Arrays umwandeln
    private TextView datum0;
    private TextView uhrzeit0;
    private TextView beschreibung0;
    private Button btnGoToMain;
    private TextView txtEvent;

    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;

    private Button btnInsert;
    private Button btnRemove;
    private EditText etInsert;
    private EditText etRemove;

    //CODE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_event); //Layout verknüpfen

        createEventListe();
        buildRecyclerView();

        btnGoToMain = (Button) findViewById(R.id.btnGoToMain); //Button mit Variable verknüpfen
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        etInsert = (EditText) findViewById(R.id.etInsert);
        etRemove = (EditText) findViewById(R.id.etRemove);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(etInsert.getText().toString());
                InsertEvent(position);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(etRemove.getText().toString());
                RemoveEvent(position);
            }
        });










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
    }

    public void InsertEvent(int pPosition){
        mEventListe.add(pPosition, new Event("Name", "Datum", "Uhrzeit", "Beschreibung"));
        mAdapter.notifyItemInserted(pPosition);
    }

    public void RemoveEvent(int pPosition){
        mEventListe.remove(pPosition);
        mAdapter.notifyItemRemoved(pPosition);
    }

    public void createEventListe(){
        mEventListe = new ArrayList<>();
        mEventListe.add(new Event("Line1", "Line2", "Line3", "Line4"));
        mEventListe.add(new Event("Line1", "Line2", "Line3", "Line4"));
        mEventListe.add(new Event("Line1", "Line2", "Line3", "Line4"));
    }

    public void buildRecyclerView(){
        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(this);
        mAdapter = new EventAdapter(mEventListe);

        mRecyclerview.setLayoutManager(mLayoutmanager);
        mRecyclerview.setAdapter(mAdapter);
    }
}
