package com.itproject.hoadt.b6SaveFileJson;

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

import java.util.List;

/**
 * Created by HoaDT on 1/3/2019.
 */
public class PersonAdapter extends ArrayAdapter<Person> {
    private List<Person > personList;
    private Context context;

    public PersonAdapter(@NonNull Context context, List<Person> personList) {
        super(context, R.layout.item_person6, personList);
        this.personList = personList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position,   @Nullable View convertView,  @NonNull ViewGroup parent) {
        View contactView;
//        contactView = LayoutInflater.from(context).inflate(R.layout.item_contact_listview, parent, false);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contactView = inflater.inflate(R.layout.item_person6, parent, false);
        TextView txtName = contactView.findViewById(R.id.txtName);
        TextView txtStatus = contactView.findViewById(R.id.txtStatus);
        ImageView imageView = contactView.findViewById(R.id.imgAvatar);

        txtName.setText(personList.get(position).getNamePerson());
        txtStatus.setText(personList.get(position).getStatusPerson());
        Picasso.with(context)
                .load("file:///android_asset/" + personList.get(position).getImgUrlPerson())
                .into(imageView);
        return contactView;
    }
}
