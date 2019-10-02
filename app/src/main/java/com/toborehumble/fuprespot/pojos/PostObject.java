package com.toborehumble.fuprespot.pojos;

public class PostObject {
    private String title;
    private String body;
    private String writer;
    private String date;
    private String time;
    private String bannerUrl;

    public PostObject() {}

    public PostObject(String title, String body, String writer, String date,
                      String time, String bannerUrl) {
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.date = date;
        this.time = time;
        this.bannerUrl = bannerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    @Override
    public String toString() {
        return "PostObject{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", writer='" + writer + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                '}';
    }
}
