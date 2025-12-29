package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DiscussionThread implements Serializable {
    private Long id;                // 帖子ID
    private String title;           // 标题
    private String content;         // 内容（支持多行）
    private String author;          // 作者
    private Date createTime;        // 创建时间
    private List<Reply> replies;    // 关联回复

    // 构造器、getter、setter
    public DiscussionThread() {}
    public DiscussionThread(Long id, String title, String content, String author, Date createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createTime = createTime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public List<Reply> getReplies() { return replies; }
    public void setReplies(List<Reply> replies) { this.replies = replies; }
}