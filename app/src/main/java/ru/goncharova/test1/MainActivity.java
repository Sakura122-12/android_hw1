package ru.goncharova.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonBack;
        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener((View.OnClickListener) this);
        int a = 10;
        getSupportActionBar().hide();
    }

    @Override
    public void onClick(View view) {
        Intent i;
        i = new Intent(this, BackActivity.class);
        startActivity(i);
    }


}