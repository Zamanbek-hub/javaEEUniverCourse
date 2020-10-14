package db.classes.helpers;

import db.classes.Chat;
import db.classes.FriendRequest;
import db.classes.User;

public class UserFR {
    private User user;
    private FriendRequest friendRequest;
    private Chat chat;
    private boolean isChat;
    private boolean isFriend;

    public UserFR() {
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void defineIsFriend(boolean isFriend){
        this.isFriend = isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public boolean isChat() {
        return isChat;
    }



    public void setChat(boolean chat) {
        isChat = chat;
    }

    public UserFR(User user, FriendRequest friendRequest) {
        this.user = user;
        this.friendRequest = friendRequest;
    }

    public UserFR(User user, Chat chat, boolean isChat) {
        this.user = user;
        this.chat = chat;
        this.isChat = isChat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
