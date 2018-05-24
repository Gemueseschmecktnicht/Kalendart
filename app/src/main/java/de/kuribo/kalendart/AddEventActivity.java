package de.kuribo.kalendart;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import static android.app.PendingIntent.getActivity;

public class AddEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{


    //ATTRIBUTE
    private static final String TAG = "AddEventActivity"; //TAG

    private static final String Eventname = "Termin.txt";
    private Button btnFinalAddEvent;
    public String datum = "Nicht definiert";
    public String uhrzeit = "Nicht definiert";
    public String name = "Nicht definiert";
    public String beschreibung = "Nicht definiert";
    TextView txtName;
    TextView txtUhrzeitShow;
    TextView txtDatumShow;
    EditText etxtName;
    Button txtDatum;
    Button txtUhrzeit;
    private Button btnDiscard;
    int hour;
    Calendar calendar;
    int minute;
    TimePickerDialog timePickerDialog;


    //CODE
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent_layout); //Layout verknüpfen
        btnFinalAddEvent = (Button) findViewById(R.id.btnFinalAddEvent);
        btnDiscard = (Button) findViewById(R.id.btnDiscard);
        txtUhrzeit = (Button) findViewById(R.id.txtZeit);
        txtUhrzeitShow = (TextView) findViewById(R.id.txtUhrzeitShow);
        txtDatum = (Button) findViewById(R.id.txtDatum);
        txtDatumShow = (TextView) findViewById(R.id.txtDatumShow);
        txtName = (TextView) findViewById(R.id.txtName);
        etxtName = (EditText) findViewById(R.id.txtName);

        //Ereignis hinzufügen geklickt
        btnFinalAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etxtName.length() == 0) {
                    IsNull("Namens");
                } else {
                    if (txtDatumShow.length() == 5){
                        IsNull("Datums");
                    } else {
                        if (txtUhrzeitShow.length() == 7) {
                            IsNull("Uhrzeit");
                        } else {
                            save();
                            AddEventActivity.super.onBackPressed();
                        }
                    }
                }
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

    public void IsNull(String pIssue){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setMessage("Das " +pIssue +"feld darf nicht leer sein");
        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //Methode zum Speichern
    public void save(){
        String text0 = txtName.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(Eventname, MODE_PRIVATE);
            fos.write(text0.getBytes());

            Toast msg = Toast.makeText(getBaseContext(), "File " +getFilesDir() +"/" +Eventname +" angelegt", Toast.LENGTH_LONG);
            msg.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Methode zum Laden
    public void load(){
        FileInputStream fis = null;

        try {
            fis = openFileInput(Eventname);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }

            txtName.setText(sb.toString());

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