package com.itproject.hoadt.exam.b3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoaDT on 1/4/2019.
 */
public class Product {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idPro")
    @Expose
    private String idPro;
    @SerializedName("namePro")
    @Expose
    private String name;
    @SerializedName("pricePro")
    @Expose
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIdPro() {
        return idPro;
    }

    public void setIdPro(String idPro) {
        this.idPro = idPro;
    }
}
