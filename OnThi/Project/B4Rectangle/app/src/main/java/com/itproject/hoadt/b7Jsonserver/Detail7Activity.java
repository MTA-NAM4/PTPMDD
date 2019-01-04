package com.itproject.hoadt.b7Jsonserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.itproject.hoadt.b4rectangle.R;

public class Detail7Activity extends AppCompatActivity {
    TextView txtName, txtAddress, txtDateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail7);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth);
        Student student = (Student) getIntent().getSerializableExtra("student");
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtDateOfBirth.setText(student.getDateOfBirth());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
