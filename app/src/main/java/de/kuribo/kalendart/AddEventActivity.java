package de.kuribo.kalendart;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private static final String TAG = "AddEventActivity"; //TAG boiis

    private Button btnFinalAddEvent;

    public String datum = "Nicht definiert";

    public String uhrzeit = "Nicht definiert";

    public String name = "Nicht definiert";

    public String beschreibung = "Nicht definiert";

    private Button btnDiscard;

    int hour;

    Calendar calendar;

    int minute;

    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent_layout); //Layout verknüpfen
        btnFinalAddEvent = (Button) findViewById(R.id.btnFinalAddEvent);
        btnDiscard = (Button) findViewById(R.id.btnDiscard);
        Button txtUhrzeit = (Button) findViewById(R.id.txtUhrzeit);
        Button txtDatum = (Button) findViewById(R.id.txtDatum);

        //Ereignis hinzufügen geklickt
        btnFinalAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast msg = Toast.makeText(getBaseContext(), "Variablen Gespeichert", Toast.LENGTH_LONG);
                msg.show();
                AddEventActivity.super.onBackPressed();
            }
        });

        //Ereignis Verwerfen geklickt
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddEventActivity.super.onBackPressed();
            }
        });

        //Uhrzeit geklickt --> TIMEPICKER
        txtUhrzeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });

        //Datum geklickt --> DATEPICKER
        txtDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int currentHour, int currentMinute) {
        TextView txtUhrzeitShow = (TextView) findViewById(R.id.txtUhrzeitShow);
        if(currentHour<10) {        //Für 02:02 statt 2:2 sorgen
            if(currentMinute<10) {
                txtUhrzeitShow.setText("0" + currentHour + ":0" + currentMinute);
            } else {
                txtUhrzeitShow.setText("0" + currentHour + ":" + currentMinute);
            }
        } else {
            if(currentMinute<10) {
                txtUhrzeitShow.setText(currentHour + ":0" + currentMinute);
            } else {
                txtUhrzeitShow.setText(currentHour + ":" + currentMinute);
            }

        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int currentYear, int currentMonth, int currentDay) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, currentYear);
        c.set(Calendar.MONTH, currentMonth);
        c.set(Calendar.DAY_OF_MONTH, currentDay);
        String currentDateString = java.text.DateFormat.getDateInstance().format(c.getTime()); //Datumanzeige an Sprache anpassen

        TextView txtDatumShow = (TextView) findViewById(R.id.txtDatumShow);
        txtDatumShow.setText(currentDateString);
    }

    //GETTER + SETTER
    public String getName(){
        return this.name;
    }

    public String getDatum(){
        return this.datum;
    }

    public String getUhrzeit(){
        return this.uhrzeit;
    }

    public String getBeschreibung(){
        return this.beschreibung;
    }

    public void setName(String pName){
        this.name = pName;
    }

    public void setDatum(String pDatum){
        this.datum = pDatum;
    }

    public void setUhrzeit(String pUhrzeit){
        this.uhrzeit = pUhrzeit;
    }

    public void setBeschreibung(String pBeschreibung){
        this.beschreibung = pBeschreibung;
    }
}