package de.kuribo.kalendart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; //TAG boiis

    private Button btnAddEvent;

    private Button btnGoToMainEvent;

    //Layout verknüpfen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddEvent = (Button) findViewById(R.id.btnAddEvent); //Button addEvent hinzufügen
        btnGoToMainEvent = (Button) findViewById(R.id.btnGoToMainEvent);

        //Beim Buttonklick AddEventActivity (addevent_layout.xml) öffnen
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        //Beim Buttonklick MainEventActivity (activity_main_event.xml) öffnen
        btnGoToMainEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainEventActivity.class);
                startActivity(intent);
            }
        });
    }
}
