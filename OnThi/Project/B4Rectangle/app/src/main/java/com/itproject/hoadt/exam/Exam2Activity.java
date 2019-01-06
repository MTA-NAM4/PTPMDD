package com.itproject.hoadt.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.itproject.hoadt.b4rectangle.R;
import com.itproject.hoadt.exam.b2.ExamActivity;
import com.itproject.hoadt.exam.b3.Exam3Activity;

public class Exam2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);

        getSupportActionBar().setTitle("Bai kiem tra");

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exam2Activity.this, ExamActivity.class));
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Exam2Activity.this, Exam3Activity.class));
            }
        });
    }
}
