package db.classes;

import java.sql.Date;

public class Chat {
    private Long id;
    private User user;
    private User opponent_user;
    private Date created_date;
    private String latest_message_text;
    private Date latest_message_time;
    private boolean read_by_receiver;

    public boolean isRead_by_receiver() {
        return read_by_receiver;
    }

    public void setRead_by_receiver(boolean read_by_receiver) {
        this.read_by_receiver = read_by_receiver;
    }

    public Chat() {
    }

    public Chat(Long id, String latest_message_text, Date latest_message_time) {
        this.id = id;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = latest_message_time;
    }

    public Chat(Long id, User opponent_user, Date created_date, String latest_message_text, Date latest_message_time, boolean read_by_receiver) {
        this.id = id;
        this.opponent_user = opponent_user;
        this.created_date = created_date;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = latest_message_time;
        this.read_by_receiver = read_by_receiver;
    }



    public Chat(Long id, User user, User opponent_user, Date created_date, String latest_message_text, Date latest_message_time) {
        this.id = id;
        this.user = user;
        this.opponent_user = opponent_user;
        this.created_date = created_date;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = latest_message_time;
    }

    public Chat(Long id, User user, User opponent_user, Date created_date, String latest_message_text, Date latest_message_time, boolean read_by_receiver) {
        this.id = id;
        this.user = user;
        this.opponent_user = opponent_user;
        this.created_date = created_date;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = latest_message_time;
        this.read_by_receiver = read_by_receiver;
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

    public User getOpponent_user() {
        return opponent_user;
    }

    public void setOpponent_user(User opponent_user) {
        this.opponent_user = opponent_user;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getLatest_message_text() {
        return latest_message_text;
    }

    public void setLatest_message_text(String latest_message_text) {
        this.latest_message_text = latest_message_text;
    }

    public Date getLatest_message_time() {
        return latest_message_time;
    }

    public void setLatest_message_time(Date latest_message_time) {
        this.latest_message_time = latest_message_time;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", user=" + user +
                ", opponent_user=" + opponent_user +
                ", created_date=" + created_date +
                ", latest_message_text='" + latest_message_text + '\'' +
                ", latest_message_time=" + latest_message_time +
                ",\n read_by_receiver=" + read_by_receiver +
                '}';
    }
}
