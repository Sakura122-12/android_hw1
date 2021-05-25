package ru.goncharova.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class CalcSettings extends AppCompatActivity {

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Button button = (Button)findViewById(R.id.basic);
        Button buttonJunior = (Button)findViewById(R.id.junior);
        Button buttonBack = (Button)findViewById(R.id.back);

        Switch s = (Switch) findViewById(R.id.dayNight);

        s.setOnCheckedChangeListener(this::onCheckedChanged);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTheme(R.style.Theme_MyCalculator);
                //setContentView(R.layout.activity_main2);
                //recreate();
            }
        });

        buttonJunior.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setTheme(R.style.Theme_Junior);
                //recreate();
                //setContentView(R.layout.activity_main2);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mainPage = new Intent(CalcSettings.this, MainActivity.class);
                // Метод стартует активити, указанную в интенте
                startActivity(mainPage);
            }
        });

    }
}