package com.itproject.hoadt.b8JsonAdd;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itproject.hoadt.b4rectangle.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main8Activity extends AppCompatActivity implements View.OnClickListener {
    private final String JSON_URL = "http://10.0.2.2:3000/" + "hocsinh";
    HocSinhAdapter adapter;
    List<HocSinh> studentList = new ArrayList<>();
    Context context;

    ListView listView;
    Button btnAdd;
    EditText edtTenSV, edtLopSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        context = this;

        listView = findViewById(R.id.listview);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        edtTenSV = findViewById(R.id.edtTenSV);
        edtLopSV = findViewById(R.id.edtLopSV);
//        đọc từ server
        ContactInfoProcessor contactInfoProcessor = new ContactInfoProcessor();
        contactInfoProcessor.execute(JSON_URL);

    }

    public void processJsonString(String data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        studentList = Arrays.asList(gson.fromJson(data, HocSinh[].class));
    }

    private String ConvertStreamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String readLine;
        StringBuffer buffer = new StringBuffer();
        while ((readLine = reader.readLine()) != null) {
            buffer.append(readLine);
        }
        String contactString = buffer.toString();
        if (reader != null)
            reader.close();
        return contactString;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            String ten = edtTenSV.getText().toString();
            String lop = edtLopSV.getText().toString();
            if (ten.equals("") || lop.equals("")) {
                Toast.makeText(context, "Phải nhập đầy đủ trường!", Toast.LENGTH_SHORT).show();
            } else {
                String aStudent = "{\"id\":" + (studentList.size() + 1) + ", \"name\":\"" + ten + "\", \"address\":\"" + lop + "\"}";
                // thêm 1 phần tử vào server

                edtTenSV.setText("");
                edtLopSV.setText("");
                new ContactInfoProcessor().execute(JSON_URL, aStudent);
            }

        }
    }

    /**
     * tham số 1: tham số đầu vào
     * tham số 2:
     * tham số 3: tham số đầu ra: đọc file thành công hay ko
     */
    private class ContactInfoProcessor extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            adapter = new HocSinhAdapter(context, studentList);
            listView.setAdapter(adapter);
        }

        @Override
        protected Integer doInBackground(String... strings) {
            //them 1 phan tu
            if (strings.length >= 2 && strings[0] != null && strings[1] != null) {
                addItem(strings[0], strings[1]);
            }
            readData(strings[0]);
            return 1;
        }
    }

    private void addItem(String str0, String str1) {
        InputStream inputStream;
        //thêm 1 phần tử vào server
        try {
            URL url = new URL(str0);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8")
                );
                //thêm
                writer.write(str1);
                writer.flush();
                writer.close();
                httpURLConnection.getResponseCode();
                httpURLConnection.disconnect();
//                //Lấy lại danh sách
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestProperty("Content-Type", "application/json");
//                httpURLConnection.setRequestProperty("Accept", "application/json");
//                httpURLConnection.setRequestMethod("GET");
//                int code = httpURLConnection.getResponseCode();
//                if (code == 200) {
//                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
//                    String str = ConvertStreamToString(inputStream);
//                    processJsonString(str);
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void readData(String str0) {
        InputStream inputStream;
        //đọc dữ liệu từ server
        try {
            URL url = new URL(str0);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setRequestMethod("GET");
                int code = httpURLConnection.getResponseCode();
                if (code == 200) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    String str = ConvertStreamToString(inputStream);
                    processJsonString(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
