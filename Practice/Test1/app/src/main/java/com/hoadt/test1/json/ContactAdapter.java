package com.hoadt.test1.json;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoadt.test1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HoaDT on 11/16/2018.
 */
public class ContactAdapter extends ArrayAdapter<ContactInfo> {
    private List<ContactInfo> contactInfoArrayList;
    private Context context;

    public ContactAdapter(@NonNull Context context, List<ContactInfo> contactInfos) {
        super(context, R.layout.item_contact_listview, contactInfos);
        this.context = context;
        this.contactInfoArrayList = contactInfos;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View contactView;
//        contactView = LayoutInflater.from(context).inflate(R.layout.item_contact_listview, parent, false);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contactView = inflater.inflate(R.layout.item_contact_listview, parent, false);
        TextView txtName = contactView.findViewById(R.id.txtName);
        TextView txtStatus = contactView.findViewById(R.id.txtStatus);
        ImageView imageView = contactView.findViewById(R.id.imgAvatar);

        txtName.setText(contactInfoArrayList.get(position).getName());
        txtStatus.setText(contactInfoArrayList.get(position).getStatus());
//        Picasso.with(context)
//                .load("file:///android_asset/" + contactInfoArrayList.get(position).getUrl())
//                .into(imageView);
        Picasso.with(context)
                .load(Constants.server + contactInfoArrayList.get(position).getUrl())
                .into(imageView);

        return contactView;
    }
}
