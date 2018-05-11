package de.kuribo.kalendart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddSharedEventActivity extends AppCompatActivity{

    private static final String TAG = "AddSharedEventActivity"; //TAG boiis

    private Button btnDiscard;

    private Button btnFinalAddSharedEvent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shared_event); //Layout verknüpfen
        btnDiscard = (Button) findViewById(R.id.btnDiscard);
        btnFinalAddSharedEvent = (Button) findViewById(R.id.btnFinalAddSharedEvent);

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




    }
}
