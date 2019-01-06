package com.itproject.hoadt.exam.b3;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

public class Exam3Activity extends AppCompatActivity {

    final  String JSON_URL = "http://192.168.43.117:12345/product";
    EditText edtIdPro, edtNamePro, edtPricePro;
    Button btnAdd;
    ListView lvProduct;

    ProductAdapter adapter;
    List<Product> productList = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3);
        context = this;

        getSupportActionBar().setTitle("Bai 3");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtIdPro = findViewById(R.id.edtID);
        edtNamePro = findViewById(R.id.edtName);
        edtPricePro = findViewById(R.id.edtPrice);
        btnAdd = findViewById(R.id.btnAdd);
        lvProduct = findViewById(R.id.listview);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtIdPro.getText().toString();
                String name = edtNamePro.getText().toString();
                String price = edtPricePro.getText().toString();
                if (id.equals("") || name.equals("") || price.equals("")) {
                    Toast.makeText(context, "Nhập đủ trường", Toast.LENGTH_SHORT).show();
                } else {
                    String item = "{\"id\":" + (productList.size() + 1)
                            + ", \"idPro\":\"" + id
                            + "\", \"namePro\":\"" + name
                            + "\", \"pricePro\":\"" + price + "\"}";

                    new ProductAsyncTask().execute(JSON_URL, item);
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    edtIdPro.setText("");
                    edtNamePro.setText("");
                    edtPricePro.setText("");
                }
            }
        });
        new ProductAsyncTask().execute(JSON_URL);
    }

    public void processJsonString(String data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        productList = Arrays.asList(gson.fromJson(data, Product[].class));
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

    /**
     * tham số 1: tham số đầu vào
     * tham số 2:
     * tham số 3: tham số đầu ra: đọc file thành công hay ko
     */
    private class ProductAsyncTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            adapter = new ProductAdapter(context, productList);
            lvProduct.setAdapter(adapter);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
