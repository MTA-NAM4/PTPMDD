package com.itproject.hoadt.b8JsonAdd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoaDT on 1/4/2019.
 */
public class HocSinh {
    @SerializedName("id")
    @Expose
    private String maSV;
    @SerializedName("name")
    @Expose
    private String tenSV;
    @SerializedName("address")
    @Expose
    private String lopSV;

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getLopSV() {
        return lopSV;
    }

    public void setLopSV(String lopSV) {
        this.lopSV = lopSV;
    }
}
