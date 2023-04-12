package com.aphrodite.entities;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Post {
    private int id ;
    private int author_id ;
    private String title ;
    private String author_name ;
    private String slug ;
    private String summary ;
    private String content ;
    private Timestamp published_at ;

    public Post(int id) {
        this.id = id;
    }

    public Post(String title, String slug, String summary, String content, Timestamp published_at) {
        this.title = title;
        this.slug = slug;
        this.summary = summary;
        this.content = content;
        this.published_at = published_at;
    }

    public Post(int author_id, String title, String slug, String summary, String content, Timestamp published_at) {
        this.author_id = author_id;
        this.title = title;
        this.slug = slug;
        this.summary = summary;
        this.content = content;
        this.published_at = published_at;
    }

    public Post(int id, int author_id, String title, String slug, String summary, String content, Timestamp published_at) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.slug = slug;
        this.summary = summary;
        this.content = content;
        this.published_at = published_at;
    }
    public Post(int id, int author_id,String title, String slug, String summary, String content, Timestamp published_at ,String author_name) {
        this.id = id;
        this.author_id = author_id;
        this.author_name= author_name;
        this.title = title;
        this.slug = slug;
        this.summary = summary;
        this.content = content;
        this.published_at = published_at;
    }
    public Post() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getauthor_id() {
        return author_id;
    }

    public void setauthor_id(int author_id) {
        this.author_id = author_id;
    }
    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.title = author_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
        return "Post{" +
                "id=" + id +
                ", author_id=" + author_id +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", published_at='" + published_at + '\'' +
                '}';
    }
}
