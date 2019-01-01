package com.hoadt.test1.json;

import java.io.Serializable;

/**
 * Created by HoaDT on 11/16/2018.
 */
public class ContactInfo implements Serializable {
    private String id;
    private String name;
    private String status;
    private String url;

    public ContactInfo(String id, String name, String status, String url) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.url = url;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
