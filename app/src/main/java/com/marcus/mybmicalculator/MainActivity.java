package com.marcus.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etWeight;
    EditText etHeight;
    Button btnCalcData;
    Button btnResetData;
    TextView tvDate;
    TextView tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);
        btnCalcData = findViewById(R.id.buttonCalculate);
        btnResetData = findViewById(R.id.buttonReset);
        tvDate = findViewById(R.id.tvLastDate);
        tvBMI = findViewById(R.id.tvLastBMI);

        btnCalcData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                float weight = Float.parseFloat(etWeight.getText().toString());
                float height = Float.parseFloat(etHeight.getText().toString());
                float bmi = weight/(height*height);
                tvBMI.setText("Last Calculated BMI : " + bmi);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putFloat("weight",weight);
                prefEdit.putFloat("height",height);

                prefEdit.commit();
            }
        });

        btnResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        float weight = Float.parseFloat(etWeight.getText().toString());
        float height = Float.parseFloat(etHeight.getText().toString());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putFloat("weight",weight);
        prefEdit.putFloat("height",height);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        float weight = prefs.getFloat("weight",0);
        float height = prefs.getFloat("height",0);
        float bmi = weight/(height*height);

        etWeight.setText(weight + "");
        etHeight.setText(height + "");
        tvDate.setText("Last Calculated Date: ");
        tvBMI.setText("Last Calculated BMI : " + bmi);


    }
}