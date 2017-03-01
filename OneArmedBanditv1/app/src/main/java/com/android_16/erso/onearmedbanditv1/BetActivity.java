package com.android_16.erso.onearmedbanditv1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BetActivity extends Activity {

    private RadioButton betRadio1;
    private RadioButton betRadio2;
    private RadioButton betRadio3;
    private RadioButton betRadio4;
    private RadioButton betRadio5;


    private RadioButton checkedRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        betRadio1 = (RadioButton) findViewById(R.id.radioButton1);
        betRadio2 = (RadioButton) findViewById(R.id.radioButton2);
        betRadio3 = (RadioButton) findViewById(R.id.radioButton3);
        betRadio4 = (RadioButton) findViewById(R.id.radioButton4);
        betRadio5 = (RadioButton) findViewById(R.id.radioButton5);


        checkedRadio = betRadio1;
        checkedRadio.performClick();

    }

    public void onBetRadioClick(View view){
        //RadioButton rb = (RadioButton)view;

        checkedRadio.setTextColor(Color.BLACK);
        checkedRadio = (RadioButton)view;
        checkedRadio.setTextColor(Color.RED);
        int bet;
        if(view == betRadio1){
            bet = 1;
        }
        else if (view == betRadio2){
            bet = 2;
        }
        else if (view == betRadio3){
            bet = 5;
        }
        else if (view == betRadio4){
            bet = 10;
        }
        else{
            bet = 20;
        }

        ((BanditApplication)getApplication()).setBet(bet);
    }


}
