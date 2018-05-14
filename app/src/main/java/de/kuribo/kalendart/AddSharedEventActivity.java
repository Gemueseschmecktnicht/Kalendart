package de.kuribo.kalendart;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

public class AddSharedEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private static final String TAG = "AddSharedEventActivity"; //TAG boiis

    private Button btnDiscard;

    private Button btnFinalAddSharedEvent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shared_event); //Layout verknüpfen
        btnDiscard = (Button) findViewById(R.id.btnDiscard);
        btnFinalAddSharedEvent = (Button) findViewById(R.id.btnFinalAddSharedEvent);
        Button txtUhrzeit = (Button) findViewById(R.id.txtUhrzeit);
        Button txtDatum = (Button) findViewById(R.id.txtDatum);

        //Ereignis Verwerfen geklickt
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddSharedEventActivity.super.onBackPressed(); //Zurücktaste
            }
        });

        //Ereignis Teilen Mit... geklickt
        btnFinalAddSharedEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "Diese Nachricht kam von Kalendart";
                String shareSub = "Subject";
                intent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(intent, "Teilen über"));

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
                txtUhrzeitShow.setText(currentHour + ":" + currentMinute);
            } else {
                txtUhrzeitShow.setText(currentHour + ":0" + currentMinute);
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

}
