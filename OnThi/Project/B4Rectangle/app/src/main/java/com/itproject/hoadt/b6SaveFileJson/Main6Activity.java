package com.itproject.hoadt.b6SaveFileJson;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class Main6Activity extends AppCompatActivity {
    public static final int REQUEST_UPDATE = 100;

    PersonAdapter personAdapter;
    List<Person> personList = new ArrayList<>();

    ListView lvPerson;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);  //đọc từ file
        lvPerson = findViewById(R.id.lvPerson);
        context = this;
        new PersonProcessorAsyncTask().execute("data6.txt");
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main6Activity.this, Detail6Activity.class);
                intent.putExtra("personList", personList.get(position));
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_UPDATE) {
            if (data.getSerializableExtra("newPerson") != null) {
                Person person = (Person) data.getSerializableExtra("newPerson");
                updatePerson(person);
            }

        }
    }

    private void updatePerson(Person person) {
        for (int i = 0; i < personList.size(); i++) {
            if (person.getIdPerson().equals(personList.get(i).getIdPerson())) {
                personList.get(i).setNamePerson(person.getNamePerson());
                personList.get(i).setAddressPerson(person.getAddressPerson());
                personList.get(i).setStatusPerson(person.getStatusPerson());
                personAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    /**
     * tham số 1: tham số đầu vào
     * tham số 2:
     * tham số 3: tham số đầu ra: đọc file thành công hay ko
     */
    private class PersonProcessorAsyncTask extends AsyncTask<String, Void, Integer> {
        /**
         * sau khi thực hiện công việc chính xong
         *
         * @param integer
         */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            personAdapter = new PersonAdapter(Main6Activity.this, personList);
            lvPerson.setAdapter(personAdapter);
        }

        @Override
        protected Integer doInBackground(String... strings) {
            //đọc dữ liệu từ file
            InputStream inputStream;
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

    public void processJsonString(String data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        personList = Arrays.asList(gson.fromJson(data, Person[].class));
    }
}
