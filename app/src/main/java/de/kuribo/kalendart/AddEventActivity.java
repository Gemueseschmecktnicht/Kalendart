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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.io.BufferedReader;
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

    private static final String Eventname = "Terminname.txt";
    private static final String Eventdatum = "Termindatum.txt";
    private static final String Eventuhrzeit = "Terminzeit.txt";
    private static final String Eventbeschreibung = "Terminbeschreibung.txt";
    private ImageButton btnFinalAddEvent;
    TextView txtUhrzeitShow;
    TextView txtDatumShow;
    EditText etxtName;
    EditText etxtBeschreibung;
    Button txtDatum;
    Button txtUhrzeit;
    private ImageButton btnDiscard;
    int hour;
    Calendar calendar;
    int minute;
    TimePickerDialog timePickerDialog;


    //CODE
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event); //Layout verknüpfen
        btnFinalAddEvent = (ImageButton) findViewById(R.id.btnFinalAddEvent); //Button mit Variable verknüpfen
        btnDiscard = (ImageButton) findViewById(R.id.btnDiscard); //Button mit Variable verknüpfen
        txtUhrzeit = (Button) findViewById(R.id.txtZeit); //Button mit Variable verknüpfen
        txtUhrzeitShow = (TextView) findViewById(R.id.txtUhrzeitShow);
        txtDatum = (Button) findViewById(R.id.txtDatum); //Button mit Variable verknüpfen
        txtDatumShow = (TextView) findViewById(R.id.txtDatumShow);//TextView mit Variable verknüpfen
        etxtName = (EditText) findViewById(R.id.etxtName); //EditText mit Variable verknüpfen
        etxtBeschreibung = (EditText) findViewById(R.id.etxtBeschreibung);

        //Ereignis hinzufügen geklickt
        btnFinalAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etxtName.length() == 0) { //Wenn Das Ereignis keinen Namen hat...
                    IsNull("Namens"); //...Operation IsNull aufrufen
                } else {
                    if (txtDatumShow.length() == 5){ //Wenn Das Ereignis kein Datum hat...
                        IsNull("Datums"); //...Operation IsNull aufrufen
                    } else {
                        if (txtUhrzeitShow.length() == 7) { //Wenn Das Ereignis keine Uhrzeit hat...
                            IsNull("Uhrzeit"); //...Operation IsNull aufrufen
                        } else {
                            save(Eventname, etxtName);
                            save(Eventbeschreibung, etxtBeschreibung);
                           // AddEventActivity.super.onBackPressed();
                        }
                    }
                }
            }
        });

        //Ereignis Verwerfen geklickt
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load(Eventname, etxtName);
                load(Eventbeschreibung, etxtBeschreibung);
                //AddEventActivity.super.onBackPressed(); //Zurücktaste
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

    @Override
    public void onTimeSet(TimePicker timePicker, int currentHour, int currentMinute) {
        TextView txtUhrzeitShow = (TextView) findViewById(R.id.txtUhrzeitShow);
        //Für 02:02 statt 2:2 sorgen
        if(currentHour<10) {
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
    public void save(String pEvent, EditText pTxt){
        String text0 = pTxt.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(pEvent, MODE_PRIVATE);
            fos.write(text0.getBytes());

            Toast msg = Toast.makeText(getBaseContext(), "File " +getFilesDir() +"/" +pEvent +" angelegt", Toast.LENGTH_LONG);
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
    public void load(String pEvent, EditText pTxt){
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

  /*  //testet ob Ereignisname drin steht
    public void Nametest(){
        if(etxtName.getText().toString() == "Ereignisname"){
            etxtName.getText().clear();
        }
    }
    //testet ob Beschreibung drin steht
    public void Beschreibungtest(){
        if(etxtBeschreibung.getText().toString() == "Beschreibung"){
            etxtBeschreibung.getText().clear();
        }
    }*/
    //GETTER + SETTER

}