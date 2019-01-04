package com.itproject.hoadt.b7Jsonserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.itproject.hoadt.b4rectangle.R;
import com.itproject.hoadt.b6SaveFileJson.Person;
import com.squareup.picasso.Picasso;

public class Detail7Activity extends AppCompatActivity {
    TextView txtName, txtStatus, txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail7);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtName = findViewById(R.id.txtName);
        txtStatus = findViewById(R.id.txtStatus);
        txtAddress = findViewById(R.id.txtAddress);
        Student student = (Student) getIntent().getSerializableExtra("student");
        txtName.setText(student.getName());
        txtStatus.setText(student.getStatus());
        txtAddress.setText(student.getAddress());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
