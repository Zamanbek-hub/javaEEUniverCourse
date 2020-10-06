package db.classes;

import java.sql.Date;

public class Post {
    private Long id;
    private String title;
    private String short_content;
    private String content;
    private Date post_date;
    private User author;

    public Post() {
    }

    public Post(String title, String short_content, String content, Date post_date, User author) {
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
        this.author = author;
    }

    public Post(String title, String short_content, String content, User author) {
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.author = author;
    }

    public Post(Long id, String title, String short_content, String content, User author) {
        this.id = id;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.author = author;
    }

    public Post(Long id, String title, String short_content, String content, Date post_date, User author) {
        this.id = id;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_content() {
        return short_content;
    }

    public void setShort_content(String short_content) {
        this.short_content = short_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", short_content='" + short_content + '\'' +
                ", content='" + content + '\'' +
                ", post_date=" + post_date +
                ", author=" + author +
                '}';
    }
}
