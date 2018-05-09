package de.kuribo.kalendart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddEventActivity extends AppCompatActivity{

    private static final String TAG = "AddEventActivity"; //TAG boiis




    //Layout verknüpfen
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent_layout);
    }


}
