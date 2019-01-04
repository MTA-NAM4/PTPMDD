package com.itproject.hoadt.b6SaveFileJson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itproject.hoadt.b4rectangle.R;
import com.squareup.picasso.Picasso;

import static com.itproject.hoadt.b6SaveFileJson.Main6Activity.REQUEST_UPDATE;

public class Detail6Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgAvatar;
    EditText txtName, txtStatus, txtAddress;
    Button btnSave, btnCancel;

    Person person = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail6);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAvatar = findViewById(R.id.imgAvatar);
        txtName = findViewById(R.id.edtName);
        txtStatus = findViewById(R.id.edtStatus);
        txtAddress = findViewById(R.id.edtAddress);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        person = (Person) getIntent().getSerializableExtra("personList");
        Picasso.with(this).load("file:///android_asset/" + person.getImgUrlPerson()).into(imgAvatar);
        txtName.setText(person.getNamePerson());
        txtStatus.setText(person.getStatusPerson());
        txtAddress.setText(person.getAddressPerson());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnSave: {
                person.setAddressPerson(txtAddress.getText().toString());
                person.setNamePerson(txtName.getText().toString());
                person.setStatusPerson(txtStatus.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("newPerson", person);
                setResult(REQUEST_UPDATE, intent);
                finish();
                break;
            }
            default:
                break;
        }
    }
}
