package com.aphrodite.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Comment {
    private int id;
    private int post_id;
    private int author_id;
    private String content;
    private Timestamp published_at;

    public Comment(int id) {
        this.id = id;
    }

    public Comment(int post_id, int author_id, String content, Timestamp published_at) {
        this.post_id = post_id;
        this.author_id = author_id;
        this.content = content;
        this.published_at = published_at;
    }

    public Comment(int id, int post_id, int author_id, String content, Timestamp published_at) {
        this.id = id;
        this.post_id = post_id;
        this.author_id = author_id;
        this.content = content;
        this.published_at = published_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpost_id() {
        return post_id;
    }

    public void setpost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getauthor_id() {
        return author_id;
    }

    public void setauthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getpublished_at() {
        return published_at;
    }

    public void setpublished_at(Timestamp published_at) {
        this.published_at = published_at;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post_id=" + post_id +
                ", author_id=" + author_id +
                ", content='" + content + '\'' +
                ", published_at='" + published_at + '\'' +
                '}';
    }
}
