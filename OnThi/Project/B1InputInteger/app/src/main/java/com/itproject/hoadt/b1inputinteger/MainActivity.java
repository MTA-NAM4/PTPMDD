package com.itproject.hoadt.b1inputinteger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        editText3 = findViewById(R.id.edittext3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().equals("") || editText2.getText().equals("")) {
                    Toast.makeText(MainActivity.this, "Bạn phải nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
                } else {

                    int edit1 = 0, edit2 = 0;
                    try {
                        edit1 = Integer.parseInt(String.valueOf(editText1.getText()));
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Dữ liệu nhập vào phải là số nguyên", Toast.LENGTH_SHORT).show();
                    }
                    try {
                        edit2 = Integer.parseInt(String.valueOf(editText2.getText()));
                    }catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "Dữ liệu nhập vào phải là số nguyên", Toast.LENGTH_SHORT).show();
                    }

                    editText3.setText((edit1 + edit2) + "");

                }
            }
        });

    }
}
