package com.hoadt.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.editTextName)).getText().toString();
                String pass = ((EditText) findViewById(R.id.editTextPass)).getText().toString();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                ExIntent: start 1 activity xác định
//                imIntent: 1 activity ko xác định: vd link web
                intent.putExtra("NAME", name);
                intent.putExtra("PASS", pass);


                startActivity(intent);
            }
        });
    }
}
