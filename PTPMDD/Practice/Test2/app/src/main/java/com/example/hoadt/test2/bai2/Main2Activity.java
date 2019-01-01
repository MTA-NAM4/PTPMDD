package com.example.hoadt.test2.bai2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.example.hoadt.test2.R;

import java.util.ArrayList;

/**
 * Created by HoaDT on 11/9/2018.
 */
public class Main2Activity extends AppCompatActivity implements IItemClick {
    ArrayList<Student> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("Danh sách sinh viên");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));

        ArrayList<Student> arrayList = new ArrayList<>();
        arrayList.add(new Student("Nguyễn Văn A", "TP Hà Nội", "1/1/1994"));
        arrayList.add(new Student("Nguyễn Văn B", "TP Hồ Chí Minh", "2/2/1994"));
        arrayList.add(new Student("Nguyễn Văn C", "TP Đà Nẵng", "3/3/1994"));

        recyclerView.setAdapter(new StudentAdapter(arrayList, this, this));

    }

    @Override
    public void onClick(Student student) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("student", student);
        startActivity(intent);
    }
}
