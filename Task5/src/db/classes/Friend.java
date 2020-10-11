package db.classes;

import java.sql.Date;

public class Friend {
    private int id;
    private User user;
    private User friend;
    private Date added_time;

    public Friend() {
    }

    public Friend(User user, User friend, Date added_time) {
        this.user = user;
        this.friend = friend;
        this.added_time = added_time;
    }

    public Friend(int id, User user, User friend, Date added_time) {
        this.id = id;
        this.user = user;
        this.friend = friend;
        this.added_time = added_time;
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

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public Date getAdded_time() {
        return added_time;
    }

    public void setAdded_time(Date added_time) {
        this.added_time = added_time;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", user=" + user +
                ", friend=" + friend +
                ", added_time=" + added_time +
                '}';
    }
}
