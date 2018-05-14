package de.kuribo.kalendart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainEventActivity extends AppCompatActivity {

    private static final String TAG = "MainEventActivity";

    private Button btnGoToMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_event); //Layout verknüpfen

        btnGoToMain = (Button) findViewById(R.id.btnGoToMain);

        //Zur Kalenderansicht geklickt
        btnGoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainEventActivity.super.onBackPressed(); //Zurücktaste
            }
        });
    }
}
