package de.kuribo.kalendart;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    private static final String TAG = "AddEventActivity"; //TAG boiis

    private Button btnFinalAddEvent;

    private Button btnDiscard;

    private EditText txtDatum;

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
        txtDatum = (EditText) findViewById(R.id.txtDatum);

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

        //Datum geklickt
       /* txtDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);


                timePickerDialog = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timepicker, int hours, int minutes) {
                        txtDatum.setText(hour + ":" + minute);
                    }
                }, hour, minute);

                timePickerDialog.show();

            }
        });*/


    }
}
