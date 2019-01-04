package com.itproject.hoadt.b8JsonAdd;

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
import com.itproject.hoadt.b7Jsonserver.Student;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoaDT on 1/4/2019.
 */
public class HocSinhAdapter extends ArrayAdapter<HocSinh> {
    List<HocSinh> studentList = new ArrayList<>();
    Context context; public HocSinhAdapter(@NonNull Context context,List<HocSinh> studentList) {
        super(context, R.layout.item_student8, studentList);
        this.context = context;
        this.studentList =studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View contactView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contactView = inflater.inflate(R.layout.item_student8, parent, false);
        TextView txtMaSV = contactView.findViewById(R.id.txtMaSV);
        TextView txtTenSV= contactView.findViewById(R.id.txtTenSV);
        TextView txtLopSV= contactView.findViewById(R.id.txtLopSV);

        txtMaSV.setText(studentList.get(position).getMaSV());
        txtTenSV.setText(studentList.get(position).getTenSV());
        txtLopSV.setText(studentList.get(position).getLopSV());

        return contactView;
    }
}
