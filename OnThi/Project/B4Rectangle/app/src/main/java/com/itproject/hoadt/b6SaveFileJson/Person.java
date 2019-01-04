package com.itproject.hoadt.b6SaveFileJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HoaDT on 1/3/2019.
 */
public class Person implements Serializable {
    @SerializedName("id")
    @Expose
    private String idPerson;
    @SerializedName("name")
    @Expose
    private String namePerson;
    @SerializedName("address")
    @Expose
    private String addressPerson;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrlPerson;
    @SerializedName("status")
    @Expose
    private String statusPerson;

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getAddressPerson() {
        return addressPerson;
    }

    public void setAddressPerson(String addressPerson) {
        this.addressPerson = addressPerson;
    }

    public String getImgUrlPerson() {
        return imgUrlPerson;
    }

    public void setImgUrlPerson(String imgUrlPerson) {
        this.imgUrlPerson = imgUrlPerson;
    }

    public String getStatusPerson() {
        return statusPerson;
    }

    public void setStatusPerson(String statusPerson) {
        this.statusPerson = statusPerson;
    }
}
