package de.kuribo.kalendart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VariableDisplay extends AppCompatActivity{

    private AddEventActivity aea = new AddEventActivity();

    private static final String TAG = "VariableDisplay"; //TAG boiis

    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.variable_output); //Layout verknüpfen

        TextView txtName = (TextView) findViewById(R.id.txtName);
        Button btnAktualisieren = (Button) findViewById(R.id.btnAktualisieren);



       /* btnAktualisieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtName.setText(AddEventActivity.getName());
            }
        });*/




    }

  /*  public TextView getTxtName(){
        return this.txtName;
    }

    public void setTxtName(String pName){
        this.txtName = (TextView) pName;
    }*/
}
