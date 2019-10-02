package com.toborehumble.fuprespot.pojos;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class FollowObject {
    private String follower;

    public FollowObject(){}

    public FollowObject(String follower) {
        this.follower = follower;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("follower", follower);
        return hashMap;
    }
}
