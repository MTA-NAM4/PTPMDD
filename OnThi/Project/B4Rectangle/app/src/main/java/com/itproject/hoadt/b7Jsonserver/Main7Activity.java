package com.itproject.hoadt.b7Jsonserver;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itproject.hoadt.b4rectangle.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main7Activity extends AppCompatActivity {
    ListView lvStudent;
    List<Student> studentList = new ArrayList<>();
    StudentAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        context = this;
        lvStudent = findViewById(R.id.lvStudent);
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main7Activity.this, Detail7Activity.class);
                intent.putExtra("student", studentList.get(position));
                startActivity(intent);
            }
        });
//        đọc từ server
        StudentProcessorAsync studentProcessorAsync = new StudentProcessorAsync();
        //đọc dữ liệu từ server
        studentProcessorAsync.execute("http://10.0.2.2:3000/" + "student");
    }

    /**
     * tham số 1: tham số đầu vào
     * tham số 2:
     * tham số 3: tham số đầu ra: đọc file thành công hay ko
     */
    private class StudentProcessorAsync extends AsyncTask<String, Void, Integer> {
        /**
         * chuẩn bị làm cv chính
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * sau khi thực hiện công việc chính xong
         *
         * @param integer
         */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            adapter = new StudentAdapter(context, studentList);
            lvStudent.setAdapter(adapter);
        }

        @Override
        protected Integer doInBackground(String... strings) {

            //đọc dữ liệu từ server
            InputStream inputStream;
//            đọc dữ liệu từ server
            try {
                URL url = new URL(strings[0]);
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

            return 1;
        }

        /**
         * update lại giao diện chính
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
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

    public void processJsonString(String data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        studentList = Arrays.asList(gson.fromJson(data, Student[].class));
    }
}
