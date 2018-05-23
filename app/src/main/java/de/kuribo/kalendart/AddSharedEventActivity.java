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
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddSharedEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = "AddSharedEventActivity"; //TAG boiis

    private Button btnDiscard;

    private Button btnFinalAddSharedEvent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shared_event); //Layout verknüpfen
        btnDiscard = (Button) findViewById(R.id.btnDiscard);
        btnFinalAddSharedEvent = (Button) findViewById(R.id.btnFinalAddSharedEvent);
        final Button txtUhrzeit = (Button) findViewById(R.id.txtZeit);
        final TextView txtUhrzeitShow = (TextView) findViewById(R.id.txtUhrzeitShow);
        final Button txtDatum = (Button) findViewById(R.id.txtDatum);
        final TextView txtDatumShow = (TextView) findViewById(R.id.txtDatumShow);
        final TextView txtName = (TextView) findViewById(R.id.txtName);
        final EditText etxtName = (EditText) findViewById(R.id.txtName);

        //Ereignis Teilen Mit... geklickt
        btnFinalAddSharedEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etxtName.length() == 0) {
                    IsNull("Namens");
                } else {
                    if (txtDatumShow.length() == 5) {
                        IsNull("Datums");
                    } else {
                        if (txtUhrzeitShow.length() == 7) {
                            IsNull("Uhrzeit");
                        } else {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            String shareBody = "http://kalendart.com/event/name=" +txtName +"&datum=" +txtDatum +"&Uhrzeit=" +txtUhrzeit +"versionalpha/";
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

    public void IsNull(String pIssue) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setMessage("Das " + pIssue + "feld darf nicht leer sein");
        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int currentHour, int currentMinute) {
        TextView txtUhrzeitShow = (TextView) findViewById(R.id.txtUhrzeitShow);
        if (currentHour < 10) {        //Für 02:02 statt 2:2 sorgen
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
