package de.kuribo.kalendart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

/**
 * Created by Millo on 09.05.2018.
 */

public class AddEventActivity extends AppCompatActivity{

    private static final String TAG = "AddEventActivity"; //TAG boiis

    private CalendarView mAddEventActivity;

    //Layout verknüpfen
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent_layout);
        mAddEventActivity = (CalendarView) findViewById(R.id.calendarView);
    }
}
