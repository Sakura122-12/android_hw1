package ru.goncharova.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = (Button)findViewById(R.id.button);
        Button buttonJunior = (Button)findViewById(R.id.buttonJunior);
        Button buttonBack = (Button)findViewById(R.id.buttonBack);

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
                Intent mainPage = new Intent(MainActivity2.this, MainActivity.class);
                // Метод стартует активити, указанную в интенте
                startActivity(mainPage);
            }
        });

    }
}