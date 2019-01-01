package com.itproject.hoadt.b3liststudent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HoaDT on 11/9/2018.
 */
public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private ArrayList<Student> studentList;
    private Context context;
    private IItemClick iItemClick;

    public StudentAdapter(ArrayList<Student> studentList, Context context, IItemClick iItemClick) {
        this.studentList = studentList;
        this.context = context;
        this.iItemClick = iItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        return new StudentHolder(inflater.inflate(R.layout.item_student_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((StudentHolder) viewHolder).textIndex.setText((i + 1) + "");
        ((StudentHolder) viewHolder).textName.setText(studentList.get(i).getName());
        ((StudentHolder) viewHolder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClick.onClick(studentList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder {
        TextView textIndex;
        TextView textName;
        LinearLayout linearLayout;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            textIndex = itemView.findViewById(R.id.textIndex);
            textName = itemView.findViewById(R.id.textName);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
