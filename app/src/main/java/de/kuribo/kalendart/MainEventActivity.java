package de.kuribo.kalendart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainEventActivity extends AppCompatActivity {


    //ATTRIBUTE
    private static final String TAG = "MainEventActivity";

    private Button btnGoToMain;
    private TextView txtEvent;

    //CODE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_event); //Layout verknüpfen

        btnGoToMain = (Button) findViewById(R.id.btnGoToMain);
        txtEvent = (TextView) findViewById(R.id.txtEvent);


        //Zur Kalenderansicht geklickt
        btnGoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainEventActivity.super.onBackPressed(); //Zurücktaste
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_medium, R.anim.slide_out_right_medium);
    }


}
