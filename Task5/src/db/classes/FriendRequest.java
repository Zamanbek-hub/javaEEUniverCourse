package db.classes;

import java.sql.Date;

public class FriendRequest {
    private int id;
    private User user;
    private User request_sender;
    private Date sent_time;

    public FriendRequest() {
    }

    public FriendRequest(User user, User request_sender, Date sent_time) {
        this.user = user;
        this.request_sender = request_sender;
        this.sent_time = sent_time;
    }

    public FriendRequest(int id, User user, User request_sender, Date sent_time) {
        this.id = id;
        this.user = user;
        this.request_sender = request_sender;
        this.sent_time = sent_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRequest_sender() {
        return request_sender;
    }

    public void setRequest_sender(User request_sender) {
        this.request_sender = request_sender;
    }

    public Date getSent_time() {
        return sent_time;
    }

    public void setSent_time(Date sent_time) {
        this.sent_time = sent_time;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", user=" + user +
                ", request_sender=" + request_sender +
                ", sent_time=" + sent_time +
                '}';
    }
}
