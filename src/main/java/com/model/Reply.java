package com.model;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {
    private Long id;            // 回复ID
    private String content;     // 回复内容
    private String author;      // 回复作者
    private Date createTime;    // 创建时间
    private Long threadId;      // 关联帖子ID

    // 构造器、getter、setter
    public Reply() {}
    public Reply(Long id, String content, String author, Date createTime, Long threadId) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createTime = createTime;
        this.threadId = threadId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Long getThreadId() { return threadId; }
    public void setThreadId(Long threadId) { this.threadId = threadId; }
}