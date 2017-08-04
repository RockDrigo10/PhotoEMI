package com.example.admin.photoemi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class EmiActivity extends AppCompatActivity {
    SeekBar sb;
    TextView tv_sb_val,amount_borrowed,result_field,year_payment,result_field_year;
    EditText etDuration,amount_borrowed_field;
    Button button;
    double amount,rate,emi,anual;
    int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);
        //Displaying the Progress Value of the Seek Bar
        button = (Button) findViewById(R.id.button);
        sb = (SeekBar) findViewById(R.id.seek_bar);
        tv_sb_val = (TextView) findViewById(R.id.seek_bar_val);
        amount_borrowed = (TextView) findViewById(R.id.amount_borrowed);
        amount_borrowed_field = (EditText) findViewById(R.id.amount_borrowed_field);
        etDuration = (EditText) findViewById(R.id.etDuration);
        result_field = (TextView) findViewById(R.id.result_field);
        year_payment = (TextView) findViewById(R.id.year_payment);
        result_field_year = (TextView) findViewById(R.id.result_field_year);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double progress_value = (double) progress;
                rate = progress;
                String temp1 = getResources().getString(R.string.seek_bar_value);
                //String temp2 = "" ;//if(temp1.contains(" ")){temp1 = temp1.substring(0,temp1.indexOf(" "));//}
                tv_sb_val.setText(temp1+progress_value);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
    public void calculateMonthlyPayment(View view) {
        try {
            amount = Double.parseDouble(amount_borrowed_field.getText().toString());
            year = Integer.parseInt(etDuration.getText().toString());
            rate=rate/(12*100);
            year = year * 12;
            emi= (amount*rate*Math.pow(1+rate,year))/(Math.pow(1+rate,year)-1);
            //converts to string it was a double
            result_field.setText("$"+ emi + "");
            anual = emi * year;
            result_field_year.setText("$" +anual + "");
            sb.setProgress(0);
            amount_borrowed_field.setText("");
            etDuration.setText("");
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Missing Values", Toast.LENGTH_SHORT).show();
        }




    }
}
