package de.kuribo.kalendart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Button;

public class MainEventActivity extends AppCompatActivity {

    private static final String TAG = "MainEventActivity";



    private Button btnGoToMain;

    //Layout verknüpfen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_event);

        btnGoToMain = (Button) findViewById(R.id.btnGoToMainEvent);

      /*  //Beim Buttonklick MainActivity (activity_main_event.xml) öffnen
        btnGoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainEventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/ //FUNKTIONIERT IM MOMENT LEIDER NICHT, KANN DAS BITTE JEMAND BEHEBEN?! DANKE :D
    }
}
