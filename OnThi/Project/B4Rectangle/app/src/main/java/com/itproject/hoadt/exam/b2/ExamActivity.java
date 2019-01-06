package com.itproject.hoadt.exam.b2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itproject.hoadt.b4rectangle.R;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtNum1, edtNum2;
    TextView txtResult;
    Button btnDiv, btnMul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        getSupportActionBar().setTitle("Bai 2");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNum1 = findViewById(R.id.edtNum1);
        edtNum2 = findViewById(R.id.edtNum2);
        txtResult = findViewById(R.id.txtResult);
        btnDiv = findViewById(R.id.btnDiv);
        btnMul = findViewById(R.id.btnMul);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (edtNum1.getText().equals("") || edtNum2.getText().equals("")) {
            txtResult.setText("Không để dữ liệu trống!");
            return;
        } else {
            double num1 = 0, num2 = 0;
            try {
                num1 = Double.parseDouble(edtNum1.getText().toString());
            } catch (NumberFormatException e) {
                txtResult.setText("Dữ liệu nhập vào phải là số nguyên");
                return;
            }
            try {
                num2 = Double.parseDouble(edtNum2.getText().toString());
            } catch (NumberFormatException e) {
                txtResult.setText("Dữ liệu nhập vào phải là số nguyên");
                return;
            }
            if (v.getId() == R.id.btnDiv) {
                //phép chia
                if (num2 != 0) txtResult.setText(num1 / num2 + "");
                else txtResult.setText("INVALID VALUE NUM2  = 0");

            } else if (v.getId() == R.id.btnMul) {
                //phép nhân
                txtResult.setText(num1 * num2 + "");
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
