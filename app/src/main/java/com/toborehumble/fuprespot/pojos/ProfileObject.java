package com.toborehumble.fuprespot.pojos;

public class ProfileObject {
    private String userName;
    private String birthDay;
    private String hobby;
    private String department;
    private String tribe;
    private String profileQuote;
    private int followersCount;
    private int followingCount;
    private String uid;

    public ProfileObject(){}

    public ProfileObject(String userName, String birthDay, String hobby,
                         String department,
                         String tribe, String profileQuote,
                         int followersCount,
                         int followingCount) {
        this.userName = userName;
        this.birthDay = birthDay;
        this.hobby = hobby;
        this.department = department;
        this.tribe = tribe;
        this.profileQuote = profileQuote;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
    }

    public String getProfileQuote() {
        if(this.profileQuote == null){
            return "@feeling_great";
        }else {
            return this.profileQuote;
        }
    }

    public void setProfileQuote(String profileQuote) {
        this.profileQuote = profileQuote;
    }

    @Override
    public String toString() {
        return "ProfileObject{" +
                "userName='" + userName + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", hobby='" + hobby + '\'' +
                ", department='" + department + '\'' +
                ", tribe='" + tribe + '\'' +
                ", profileQuote='" + profileQuote + '\'' +
                '}';
    }
}
