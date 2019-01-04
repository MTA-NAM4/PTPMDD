package com.itproject.hoadt.b7Jsonserver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itproject.hoadt.b4rectangle.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoaDT on 1/3/2019.
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    List<Student> studentList = new ArrayList<>();
    Context context;

    public StudentAdapter(@NonNull Context context, List<Student> studentList) {
        super(context, R.layout.item_student7, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View contactView;
//        contactView = LayoutInflater.from(context).inflate(R.layout.item_contact_listview, parent, false);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contactView = inflater.inflate(R.layout.item_student7, parent, false);
        TextView txtName = contactView.findViewById(R.id.txtName);
        TextView txtStatus = contactView.findViewById(R.id.txtStatus);
        ImageView imageView = contactView.findViewById(R.id.imgAvatar);

        txtName.setText(studentList.get(position).getName());
        txtStatus.setText(studentList.get(position).getStatus());
        Picasso.with(context)
                .load("file:///android_asset/" + studentList.get(position).getImgUrl())
                .into(imageView);

        return contactView;
    }
}
