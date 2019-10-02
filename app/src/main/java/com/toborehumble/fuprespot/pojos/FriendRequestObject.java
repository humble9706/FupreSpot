package com.toborehumble.fuprespot.pojos;

public class FriendRequestObject {
    private String requestFrom;
    private String requestTo;
    private long timeStamp;
    private boolean requestAccepted;

    public FriendRequestObject(){}

    public FriendRequestObject(String requestFrom, String requestTo, long timeStamp,
                               boolean requestAccepted) {
        this.requestTo = requestTo;
        this.requestFrom = requestFrom;
        this.timeStamp = timeStamp;
        this.requestAccepted = requestAccepted;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public boolean isRequestAccepted() {
        return requestAccepted;
    }

    public void setRequestAccepted(boolean requestAccepted) {
        this.requestAccepted = requestAccepted;
    }
}
