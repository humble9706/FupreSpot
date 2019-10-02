package com.toborehumble.fuprespot.pojos;

import java.util.ArrayList;

public class UserObject {
    private ProfileObject profile;
    private ArrayList<String> posts;
    private FollowObject followers;
    private FollowingObject followings;
    private FriendRequestObject friendRequestObjects;

    public UserObject() {}

    public UserObject(ProfileObject profile, ArrayList<String> posts, FollowObject followers,
                      FollowingObject followings, FriendRequestObject friendRequestObjects) {
        this.friendRequestObjects = friendRequestObjects;
        this.profile = profile;
        this.posts = posts;
        this.followers = followers;
        this.followings = followings;
    }

    public ProfileObject getProfile() {
        return profile;
    }

    public void setProfile(ProfileObject profile) {
        this.profile = profile;
    }

    public ArrayList<String> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<String> posts) {
        this.posts = posts;
    }

    public FollowObject getFollowers() {
        return followers;
    }

    public void setFollowers(FollowObject followers) {
        this.followers = followers;
    }

    public FollowingObject getFollowings() {
        return followings;
    }

    public void setFollowings(FollowingObject followings) {
        this.followings = followings;
    }

    public FriendRequestObject getFriendRequestObjects() {
        return friendRequestObjects;
    }

    public void setFriendRequestObjects(FriendRequestObject friendRequestObjects) {
        this.friendRequestObjects = friendRequestObjects;
    }
}
