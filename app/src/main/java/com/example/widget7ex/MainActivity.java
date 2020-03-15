package com.example.widget7ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main";
    private RadioGroup radioGroup;
    private boolean colaFlag, teaFlag, coffeeFlag;
    private CheckBox checkBoxWaffle, checkBoxPancake, checkBoxmuffin;
    private boolean waffleFlag, pancakeFlag, muffinFlag;
    private Button buttonCancel, buttonOK;
    private TextView textViewResult;
    private EditText editTextNDrink, editTextNWaffle, editTextNPancake, editTextNMuffin;
    private final int colaPrice = 50, teaPrice = 60, coffeePrice = 90;
    private final int wafflePrice = 100, pancakePrice = 120, muffinPrice = 150;
    private int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colaFlag = false;
        teaFlag = false;
        coffeeFlag = false;

        // 處理多選一
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_id);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton_cola:
                        colaFlag = true;
                        teaFlag = false;
                        coffeeFlag = false;

                        break;

                    case R.id.radioButton_tea:
                        colaFlag = false;
                        teaFlag = true;
                        coffeeFlag = false;

                        break;

                    case R.id.radioButton_coffee:
                        colaFlag = false;
                        teaFlag = false;
                        coffeeFlag = true;

                        break;
                }

                Log.d(TAG, "colaFlag = " + colaFlag);
                Log.d(TAG, "teaFlag = " + teaFlag);
                Log.d(TAG, "coffeeFlag = " + coffeeFlag);
            }
        });

        // 處理多選 checkBox
        waffleFlag = false;
        pancakeFlag = false;
        muffinFlag = false;

        checkBoxWaffle = (CheckBox) findViewById(R.id.checkBox_waffle);
        checkBoxPancake = (CheckBox) findViewById(R.id.checkBox_pancake);
        checkBoxmuffin = (CheckBox) findViewById(R.id.checkBox_muffin);

        checkBoxWaffle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                waffleFlag = isChecked;
                Log.d(TAG, "waffleFlag = " + waffleFlag);
            }
        });

        checkBoxPancake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pancakeFlag = isChecked;
                Log.d(TAG, "pancakeFlag = " + pancakeFlag);
            }
        });

        checkBoxmuffin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                muffinFlag = isChecked;
                Log.d(TAG, "muffinFlag = " + muffinFlag);
            }
        });


        textViewResult = (TextView) findViewById(R.id.textView_result);
        textViewResult.setText("");

        editTextNDrink = (EditText) findViewById(R.id.editText_n_drink);
        editTextNDrink.setText("");

        editTextNWaffle = (EditText) findViewById(R.id.editText_n_waffle);
        editTextNWaffle.setText("");

        editTextNPancake = (EditText) findViewById(R.id.editText_n_pancake);
        editTextNPancake.setText("");

        editTextNMuffin = (EditText) findViewById(R.id.editText_n_muffin);
        editTextNMuffin.setText("");

        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonOK = (Button) findViewById(R.id.button_ok);

        buttonCancel.setOnClickListener(new MyButton());
        buttonOK.setOnClickListener(new MyButton());


        // 也可以在這裡 Integer.parseIny()
//        int int_numDrink = 0;
//            String str_numDrink = editTextNDrink.getText().toString();
//            if(str_numDrink.length() == 0) {
//                str_numDrink = "0";
//            }else {
//                int_numDrink = Integer.parseInt(str_numDrink);
//        }

    }

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_cancel:
                    radioGroup.clearCheck();
                    waffleFlag = false;
                    pancakeFlag = false;
                    muffinFlag = false;
                    colaFlag = false;
                    teaFlag= false;
                    coffeeFlag = false;
                    checkBoxWaffle.setChecked(false);
                    checkBoxPancake.setChecked(false);
                    checkBoxmuffin.setChecked(false);
                    textViewResult.setText("");
                    editTextNDrink.setText("");
                    editTextNWaffle.setText("");
                    editTextNPancake.setText("");
                    editTextNMuffin.setText("");
                    break;

                case R.id.button_ok:
                    sum = 0;
                    textViewResult.setText("You have ordered : \n");

                    // 計算 Drink
                    int int_numDrink = 0;
                    String str_numDrink = editTextNDrink.getText().toString();
                    if(str_numDrink.length() == 0) {
                        str_numDrink = "0";
                    }else {
                        int_numDrink = Integer.parseInt(str_numDrink);
                    }

                    if (colaFlag) {
                       sum += (colaPrice * int_numDrink);
                       textViewResult.append("Cola :" + colaPrice + " 元 X " + int_numDrink + " 杯 = " + sum + " 元\n");

                    } else if (teaFlag) {
                        sum += (teaPrice * int_numDrink);
                        textViewResult.append("Tea :" + teaPrice + " 元 X " + int_numDrink + " 杯 = " + sum + " 元\n");

                    } else if (coffeeFlag) {
                        sum += (coffeePrice * int_numDrink);
                        textViewResult.append("Coffee :" + coffeePrice + " 元 X " + int_numDrink + " 杯 = " + sum + " 元\n");
                        }

                    // 計算 Waffle
                    int int_numWaffle = 0;
                    String str_numWaffle = editTextNWaffle.getText().toString();
                    if(str_numWaffle.length() == 0) {
                        str_numWaffle = "0";
                    }else {
                        int_numWaffle = Integer.parseInt(str_numWaffle);
                    }

                    if(waffleFlag) {
                        sum += (wafflePrice * int_numWaffle);
                        textViewResult.append("Waffle :" + wafflePrice + " 元 X " + int_numWaffle + " 份 = " + (wafflePrice * int_numWaffle) + " 元\n");
                    }

                    // 計算 Pancake
                    int int_numPancake = 0;
                    String str_numPancake = editTextNPancake.getText().toString();
                    if(str_numPancake.length() == 0) {
                        str_numPancake = "0";
                    }else {
                        int_numPancake = Integer.parseInt(str_numPancake);
                    }

                    if(pancakeFlag) {
                        sum += (pancakePrice * int_numPancake);
                        textViewResult.append("Pancake :" + pancakePrice + " 元 X " + int_numPancake + " 份 = " + (pancakePrice * int_numPancake) + " 元\n");
                    }

                    // 計算 Muffin
                    int int_numMuffin = 0;
                    String str_numMuffin = editTextNMuffin.getText().toString();
                    if(str_numMuffin.length() == 0){
                        str_numMuffin = "0";
                    }else {
                        int_numMuffin = Integer.parseInt(str_numMuffin);
                    }

                    if(muffinFlag) {
                        sum += (muffinPrice * int_numMuffin);
                        textViewResult.append("Muffin :" + muffinPrice + " 元 X " + int_numMuffin + " 份 = " + (muffinPrice * int_numMuffin) + " 元\n");
                    }

                    textViewResult.append("The total fee = " + sum);
                    Log.d(TAG, "sum = " + sum);

                    break;
            }
        }
    }
}
