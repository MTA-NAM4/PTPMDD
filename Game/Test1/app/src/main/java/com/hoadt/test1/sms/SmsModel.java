package com.hoadt.test1.sms;

/**
 * Created by HoaDT on 10/12/2018.
 */
public class SmsModel {
    private String Content;
    private boolean IsFromMe;
    private String Time;

    public SmsModel(String content, boolean isFromMe, String time) {
        Content = content;
        IsFromMe = isFromMe;
        Time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public boolean isFromMe() {
        return IsFromMe;
    }

    public void setFromMe(boolean fromMe) {
        IsFromMe = fromMe;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
