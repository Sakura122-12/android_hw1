package ru.goncharova.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.TEXT;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT = "EXPRESSION";

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

        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null){
            //return;
        }
        else {
            String intentText = bundle.getString(TEXT);

            TextView text = (TextView) findViewById(R.id.textView);
            text.setText(intentText);
        }

        Switch s = (Switch) findViewById(R.id.switch1);

        s.setOnCheckedChangeListener(this::onCheckedChanged);

        Button buttonSettings = (Button)findViewById(R.id.buttonSettings);

        ArrayList<Button> allButtons = new ArrayList<>();

        allButtons.add((Button)findViewById(R.id.buttonBracket_1));
        allButtons.add((Button)findViewById(R.id.buttonBracket_2));
        allButtons.add((Button)findViewById(R.id.buttonPlus));
        allButtons.add((Button)findViewById(R.id.buttonMinus));
        allButtons.add((Button)findViewById(R.id.buttonSplit));
        allButtons.add((Button)findViewById(R.id.buttonEq));
        allButtons.add((Button)findViewById(R.id.buttonMul));
        allButtons.add((Button)findViewById(R.id.buttonDel));
        allButtons.add((Button)findViewById(R.id.button1));
        allButtons.add((Button)findViewById(R.id.button2));
        allButtons.add((Button)findViewById(R.id.button3));
        allButtons.add((Button)findViewById(R.id.button4));
        allButtons.add((Button)findViewById(R.id.button5));
        allButtons.add((Button)findViewById(R.id.button6));
        allButtons.add((Button)findViewById(R.id.button7));
        allButtons.add((Button)findViewById(R.id.button8));
        allButtons.add((Button)findViewById(R.id.button9));
        allButtons.add((Button)findViewById(R.id.button0));

        for(int i = 0; i < allButtons.size();i++) {


            Button button = allButtons.get(i);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Button bt = (Button) v;

                    TextView text = (TextView) findViewById(R.id.textView);
                    TextView history = (TextView) findViewById(R.id.editTextTextMultiLine);

                    if(bt.getText().equals("Del")) {

                        if(text.getText().length() == 0)
                            return;

                        text.setText(text.getText().subSequence(0, text.getText().length() - 1));
                        return;
                    }

                    if(bt.getText().equals("=")) {
                        ExpressionParser parser = new ExpressionParser();

                        String expression = text.getText().toString();

                        double res = parser.calculate(parser.parse(expression));
                        history.append(text.getText().toString() + "=" + Double.toString(res) + "\n");


                        text.setText("");
                        return;
                    }


                    text.append(bt.getText());
                }
            });
        }

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent settings = new Intent(MainActivity.this, CalcSettings.class);
                // Метод стартует активити, указанную в интенте
                startActivity(settings);
            }
        });
    }




}