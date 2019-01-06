package com.itproject.hoadt.exam.b3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.itproject.hoadt.b4rectangle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoaDT on 1/4/2019.
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    List<Product> productList = new ArrayList<>();
    Context context;

    public ProductAdapter(@NonNull Context context, List<Product> productList) {
        super(context, R.layout.item_product, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View contactView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contactView = inflater.inflate(R.layout.item_product, parent, false);
        TextView txtIdPro = contactView.findViewById(R.id.txtIdPro);
        TextView txtNamePro = contactView.findViewById(R.id.txtNamePro);
        TextView txtPricePro = contactView.findViewById(R.id.txtPricePro);

        txtIdPro.setText(productList.get(position).getIdPro());
        txtNamePro.setText(productList.get(position).getName());
        txtPricePro.setText(productList.get(position).getPrice());

        return contactView;
    }
}
