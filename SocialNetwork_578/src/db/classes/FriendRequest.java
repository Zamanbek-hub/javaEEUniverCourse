package db.classes;

import java.sql.Date;

public class FriendRequest {
    private Long id;
    private User user;
    private User request_sender;
    private Date sent_time;
    private String type;

    public FriendRequest() {
    }

    public FriendRequest(Long id) {
        this.id = id;
    }

    public FriendRequest(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public FriendRequest(User user, User request_sender, Date sent_time) {
        this.user = user;
        this.request_sender = request_sender;
        this.sent_time = sent_time;
    }

    public FriendRequest(Long id, User user, User request_sender, Date sent_time) {
        this.id = id;
        this.user = user;
        this.request_sender = request_sender;
        this.sent_time = sent_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
