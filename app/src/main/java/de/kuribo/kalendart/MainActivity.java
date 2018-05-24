package de.kuribo.kalendart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {


    //ATTRIBUTE
    private static final String TAG = "MainActivity"; //TAG boiis

    private Button btnAddEvent;
    private Button btnAddSharedEvent;
    private Button btnVariable;
    private Button btnGoToMainEvent;


    //CODE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Layout verknüpfen
        btnAddEvent = (Button) findViewById(R.id.btnAddEvent);
        btnGoToMainEvent = (Button) findViewById(R.id.btnGoToMainEvent);
        btnAddSharedEvent = (Button) findViewById(R.id.btnAddSharedEvent);
        btnVariable = (Button) findViewById(R.id.btnVariable);

        //Ereignis hinzufügen geklickt
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        //Zur Ereignisansicht geklickt
        btnGoToMainEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainEventActivity.class);
                startActivity(intent);
            }
        });

        //Gemeinsames Ereignis Hinzufügen geklickt
        btnAddSharedEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddSharedEventActivity.class);
                startActivity(intent);
            }
        });

        //Zur Variablenanzeige geklickt
        btnVariable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VariableDisplay.class);
                startActivity(intent);
            }
        });

    }
}
/*ALLE BENUTZTEN QUELLEN
https://developer.android.com/reference/android/widget/Button (10.05.2018)
http://codezentrale.bplaced.net/dcz/?p=3778 (10.05.2018)
https://stackoverflow.com/questions/4038479/android-go-back-to-previous-activity (10.05.2018)
https://www.youtube.com/watch?v=NdtE_1u0cq4 (10.05.2018)
https://stackoverflow.com/questions/3928864/converting-textview-to-string-kind-of-android (15.05.2018)
https://individuapp.com/softwareentwicklung/android-kurs/textview-und-edittext/ (15.05.2018)
 */