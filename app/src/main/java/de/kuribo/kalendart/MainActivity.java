package de.kuribo.kalendart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    //ATTRIBUTE
    private static final String TAG = "MainActivity"; //TAG boiis

    public static String Eventname = "Terminname.txt";

    private ImageButton btnAddEvent;
    private ImageButton btnAddSharedEvent;
    private Button btnGoToMainEvent;
    private ImageButton btnAdd;
    private boolean visible = false;
   // private FloatingActionButton fab;


    //CODE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); //Layout verknüpfen
        btnAddEvent = (ImageButton) findViewById(R.id.btnAddEvent); //Button mit Variable verknüpfen
        btnGoToMainEvent = (Button) findViewById(R.id.btnGoToMainEvent); //Button mit Variable verknüpfen
        btnAddSharedEvent = (ImageButton) findViewById(R.id.btnAddSharedEvent); //Button mit Variable verknüpfen
        btnAdd = (ImageButton) findViewById(R.id.btnAdd); //Button mit Variable verknüpfen

        //Ereignis hinzufügen geklickt
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        //+ geklickt
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visible==false){
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right); //animation setzen
                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left); //animation setzen
                    btnAddEvent.setVisibility(View.VISIBLE); //Sichtbarkeit des Buttons ändern
                    btnAddSharedEvent.setVisibility(View.VISIBLE); //Sichtbarkeit des Buttons ändern
                    btnAddEvent.startAnimation(animation); //animation starten
                    btnAddSharedEvent.startAnimation(animation); //animation starten
                    btnAdd.startAnimation(animation2); //animation starten
                    visible = true;

                } else {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right); //animation setzen
                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right); //animation setzen
                    btnAddEvent.setVisibility(View.INVISIBLE); //Sichtbarkeit des Buttons ändern
                    btnAddSharedEvent.setVisibility(View.INVISIBLE); //Sichtbarkeit des Buttons ändern
                    btnAddEvent.startAnimation(animation); //animation starten
                    btnAddSharedEvent.startAnimation(animation); //animation starten
                    btnAdd.startAnimation(animation2); //animation starten
                    visible = false;

                }


            }
        });

        //Zur Ereignisansicht geklickt
        btnGoToMainEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainEventActivity.class); //Activity auswählen
                startActivity(intent); //Activity starten
                overridePendingTransition(R.anim.slide_in_right_medium, R.anim.slide_out_left_medium); //animation setzen
            }
        });

        //Gemeinsames Ereignis Hinzufügen geklickt
        btnAddSharedEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddSharedEventActivity.class);  //Activity auswählen
                startActivity(intent); //Activity starten
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