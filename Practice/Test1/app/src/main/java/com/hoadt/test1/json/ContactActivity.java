package com.hoadt.test1.json;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hoadt.test1.R;

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

public class ContactActivity extends AppCompatActivity {
    String dataContact = "[" +
            "{" + "id:1,name:\"iOS\",status:\"Độc thân\",url:\"avatar1.png\"},"
            + "{" + "id:2,name:\"Android\",status:\"Đã kết hôn\",url:\"avatar2.png\"},"
            + "{" + "id:3,name:\"React native\",status:\"Đang trong mối quan hệ phức tạp\",url:\"avatar3.png\"},"
            + "{" + "id:4,name:\"Xamarin\",status:\"Đang trong mối quan hệ phức tạp\",url:\"avatar4.png\"}"
            + "]";

    String aContact = "{\"id\": 11,\"name\": \"John\",\"url\": \"avatar1.png\"}";

    List<ContactInfo> contactInfoList = new ArrayList<>();
    ListView listViewContact;
    Context context;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        context = this;
        listViewContact = findViewById(R.id.contactListview);

        //chuyển stringJson -> object
//        processJsonString(dataContact);
//        listViewContact.setAdapter(new ContactAdapter(this, contactInfoList));
//        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(ContactActivity.this, DetailActivity.class);
//                intent.putExtra("contact", contactInfoList.get(position));
//                startActivity(intent);
//            }
//        });

        //đọc từ file
//        new ContactInfoProcessor().execute("contactData.txt");
//        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(ContactActivity.this, DetailActivity.class);
//                intent.putExtra("contact", contactInfoList.get(position));
//                startActivity(intent);
//            }
//        });

//        đọc từ server
        ContactInfoProcessor contactInfoProcessor = new ContactInfoProcessor();
        Log.d("ContactActivity", "onCreate: " + Constants.server + " " + Constants.JsonURL);
        //đọc dữ liệu từ server
        //contactInfoProcessor.execute(Constants.server + Constants.JsonURL);

        // thêm 1 phần tử vào server
        contactInfoProcessor.execute(Constants.server + Constants.JsonURL, aContact);

    }

    public void processJsonString(String data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        contactInfoList = Arrays.asList(gson.fromJson(data, ContactInfo[].class));
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
    private class ContactInfoProcessor extends AsyncTask<String, Void, Integer> {
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
            contactAdapter = new ContactAdapter(context, contactInfoList);
            listViewContact.setAdapter(contactAdapter);
        }

        @Override
        protected Integer doInBackground(String... strings) {
            //đọc dữ liệu từ file
            /**InputStream inputStream;
             BufferedReader reader;
             try {
             inputStream = context.getAssets().open(strings[0]);
             reader = new BufferedReader(new InputStreamReader(inputStream));
             String readLine;
             StringBuffer buffer = new StringBuffer();
             while ((readLine = reader.readLine()) != null) {
             buffer.append(readLine);
             }
             String contactString = buffer.toString();
             processJsonString(contactString);
             if (reader != null)
             reader.close();

             } catch (IOException e) {
             e.printStackTrace();
             return 0;
             }
             */
            //đọc dữ liệu từ server
            InputStream inputStream;
            BufferedReader reader;
//            try {
//                inputStream = context.getAssets().open(strings[0]);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return 0;
//            }
            //------------------------------------------------------------------------
            //đọc dữ liệu từ server
//            try {
//                URL url = new URL(strings[0]);
//                try {
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
//                    httpURLConnection.setRequestProperty("Accept", "application/json");
//                    httpURLConnection.setRequestMethod("GET");
//                    int code = httpURLConnection.getResponseCode();
//                    if (code == 200) {
//                        inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
//                        String str = ConvertStreamToString(inputStream);
//                        processJsonString(str);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
            //------------------------------------------------------------------------
            //thêm 1 phần tử vào server
            try {
                URL url = new URL(strings[0]);
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    httpURLConnection.setRequestProperty("Accept", "application/json");
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8")
                    );
                    writer.write(strings[1]);
                    writer.flush();
                    writer.close();
                    httpURLConnection.getResponseCode();
                    httpURLConnection.disconnect();

                    httpURLConnection = (HttpURLConnection) url.openConnection();
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
            //------------------------------------------------------------------------
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
}
