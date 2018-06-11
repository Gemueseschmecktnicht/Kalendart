package de.kuribo.kalendart;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddSharedEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    //ATTRIBUTE
    private static final String TAG = "AddSharedEventActivity"; //TAG

    private ImageButton btnDiscard;
    private ImageButton btnFinalAddSharedEvent;
    Button txtUhrzeit;
    Button txtDatum;
    TextView txtUhrzeitShow;
    TextView txtDatumShow;
    EditText etxtName;


    //CODE
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shared_event); //Layout verknüpfen
        btnDiscard = (ImageButton) findViewById(R.id.btnDiscard); //Button mit Variable verknüpfen
        btnFinalAddSharedEvent = (ImageButton) findViewById(R.id.btnFinalAddSharedEvent); //Button mit Variable verknüpfen
        txtUhrzeit = (Button) findViewById(R.id.txtZeit); //Button mit Variable verknüpfen
        txtUhrzeitShow = (TextView) findViewById(R.id.txtUhrzeitShow); //TextView mit Variable verknüpfen
        txtDatum = (Button) findViewById(R.id.txtDatum); //Button mit Variable verknüpfen
        txtDatumShow = (TextView) findViewById(R.id.txtDatumShow); //TextView mit Variable verknüpfen
        etxtName = (EditText) findViewById(R.id.txtName); //EditText mit Variable verknüpfen

        //Ereignis Teilen Mit... geklickt
        btnFinalAddSharedEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etxtName.length() == 0) { //Wenn Das Ereignis keinen Namen hat...
                    IsNull("Namens"); //...Operation IsNull aufrufen
                } else {
                    if (txtDatumShow.length() == 5) { //Wenn Das Ereignis kein Datum hat...
                        IsNull("Datums"); //...Operation IsNull aufrufen
                    } else {
                        if (txtUhrzeitShow.length() == 7) { //Wenn Das Ereignis keine Uhrzeit hat...
                            IsNull("Uhrzeit"); //...Operation IsNull aufrufen
                        } else {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            String shareBody = "http://kalendart.com/event/name=" +etxtName +"&datum=" +txtDatum +"&Uhrzeit=" +txtUhrzeit +"versionalpha/"; //Link als String verpacken
                            String shareSub = "Subject";
                            intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(intent, "Teilen über"));
                        }
                    }
                }


            }
        });

        //Ereignis Verwerfen geklickt
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddSharedEventActivity.super.onBackPressed(); //Zurücktaste
            }
        });


        //Uhrzeit geklickt --> TIMEPICKER
        txtUhrzeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment(); //selbsterklärend
                timePicker.show(getSupportFragmentManager(), "time picker"); //selbsterklärend

            }
        });

        //Datum geklickt --> DATEPICKER
        txtDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment(); //selbsterklärend
                datePicker.show(getSupportFragmentManager(), "date picker"); //selbsterklärend
            }
        });

    }

    public void IsNull(String pIssue) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setMessage("Das " + pIssue + "feld darf nicht leer sein");
        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { // OK Knopf erzeugen
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int currentHour, int currentMinute) {
        TextView txtUhrzeitShow = (TextView) findViewById(R.id.txtUhrzeitShow);
        //Für 02:02 statt 2:2 sorgen
        if (currentHour < 10) {
            if (currentMinute < 10) {
                txtUhrzeitShow.setText("0" + currentHour + ":0" + currentMinute);
            } else {
                txtUhrzeitShow.setText("0" + currentHour + ":" + currentMinute);
            }
        } else {
            if (currentMinute < 10) {
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


}
