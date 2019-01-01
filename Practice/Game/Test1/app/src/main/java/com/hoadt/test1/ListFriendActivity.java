package com.hoadt.test1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFriendActivity extends ListActivity {

    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        names = getResources().getStringArray(R.array.list_name);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.item_friend, names));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent( this, MainActivity.class);
        intent.putExtra("name", names[position]);
        startActivity(intent);
    }
}
