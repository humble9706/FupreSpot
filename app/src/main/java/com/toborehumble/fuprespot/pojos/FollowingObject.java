package com.toborehumble.fuprespot.pojos;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FollowingObject {
    private String following;

    public FollowingObject(){}

    public FollowingObject(String following) {
        this.following = following;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("following", following);
        return hashMap;
    }
}
