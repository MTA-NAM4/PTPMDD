package com.itproject.hoadt.b3liststudent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by HoaDT on 11/9/2018.
 */
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("DetailActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Student student = (Student) getIntent().getSerializableExtra("student");

        TextView textName, textAddress, textDateOfBirth;
        textName = findViewById(R.id.textName);
        textAddress = findViewById(R.id.textAddress);
        textDateOfBirth = findViewById(R.id.textDateOfBirth);

        if (student != null) {
            textName.setText("Họ tên: " + student.getName());
            textAddress.setText("Địa chỉ: " + student.getAddress());
            textDateOfBirth.setText("Ngày sinh: " + student.getDateOfBirth());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
