package com.hoadt216.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.edit_text);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Log.w("MainActivity", "onClick: " + hour);
        if (hour >= 6 && hour <= 12) {
            Toast.makeText(this, "Good Morning!", Toast.LENGTH_SHORT).show();
        } else if (hour > 12 && hour <= 18) {
            Toast.makeText(this, "Good Afternoon!", Toast.LENGTH_SHORT).show();
        } else if (hour > 18 && hour <= 22) {
            Toast.makeText(this, "Good Evening!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Good night!", Toast.LENGTH_SHORT).show();
        }
    }
}
