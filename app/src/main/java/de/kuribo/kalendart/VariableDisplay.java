package de.kuribo.kalendart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VariableDisplay extends AppCompatActivity{

    private AddEventActivity aea = new AddEventActivity(); //weil Variablen innerhalb einer Activity irgendwie nicht gespeichert werden...

    private static final String TAG = "VariableDisplay"; //TAG boiis

    //String name = aea.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.variable_output); //Layout verknüpfen

        final TextView txtName = (TextView) findViewById(R.id.txtName);
        final TextView txtDatum = (TextView) findViewById(R.id.txtDatum);
        final TextView txtZeit = (TextView) findViewById(R.id.txtZeit);
        final TextView txtBeschreibung = (TextView) findViewById(R.id.txtBeschreibung);
        Button btnAktualisieren = (Button) findViewById(R.id.btnAktualisieren);



        btnAktualisieren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtName.setText(aea.getName().toString());
                txtDatum.setText(aea.getName().toString());
                txtZeit.setText(aea.getName().toString());
                txtBeschreibung.setText(aea.getName().toString());
            }
        });




    }

  /*  public TextView getTxtName(){
        return this.txtName;
    }

    public void setTxtName(String pName){
        this.txtName = (TextView) pName;
    }*/
}
