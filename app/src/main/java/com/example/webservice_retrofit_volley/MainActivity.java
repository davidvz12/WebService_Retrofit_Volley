package com.example.webservice_retrofit_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void btnSearch (View view){
        Intent intent = new Intent(this, acResult.class);
        EditText txtnumber = (EditText)findViewById(R.id.txtnumber);
        Toast toast = Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        RadioButton rbRetrofit = (RadioButton) findViewById(R.id.rbRetrofit);
        RadioButton rbVolley = (RadioButton) findViewById(R.id.rbVolley);
        int option;
        if(rbRetrofit.isChecked())
            option = 0;
        else
            option = 1;

        if (txtnumber.getText().length() > 0)
        {
            Bundle b = new Bundle();
            b.putInt("number", Integer.parseInt(txtnumber.getText().toString()));
            b.putInt("option", option);
            intent.putExtras(b);
            startActivity(intent);
        }
        else toast.show();
    }
}