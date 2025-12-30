package com.dao;

import com.model.DiscussionThread;
import com.model.Reply;
import com.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service; 

@Service
public class DataService {
  
    private final Map<String, User> userMap = new ConcurrentHashMap<>();
    private final Map<Long, DiscussionThread> threadMap = new ConcurrentHashMap<>();
    private final Map<Long, List<Reply>> replyMap = new ConcurrentHashMap<>();
    private final Map<String, String> captchaMap = new ConcurrentHashMap<>();
    private final AtomicLong threadIdGenerator = new AtomicLong(1);
    private final AtomicLong replyIdGenerator = new AtomicLong(1);


    private DataService() {
        userMap.put("admin", new User("admin", "123456"));
        userMap.put("user1", new User("user1", "123456"));
    }

    public User getUserByUsername(String username) {
        return userMap.get(username);
    }

    public boolean registerUser(String username, String password) {
        if (userMap.containsKey(username)) {
            return false;
        }
        userMap.put(username, new User(username, password));
        return true;
    }

    public void saveCaptcha(String sessionId, String captcha) {
        captchaMap.put(sessionId, captcha);
    }

    public String getCaptcha(String sessionId) {
        return captchaMap.get(sessionId);
    }

    public void removeCaptcha(String sessionId) {
        captchaMap.remove(sessionId);
    }

    public void saveThread(DiscussionThread thread) {
        thread.setId(threadIdGenerator.getAndIncrement());
        thread.setCreateTime(new Date());
        threadMap.put(thread.getId(), thread);
        replyMap.putIfAbsent(thread.getId(), new CopyOnWriteArrayList<>());
    }

    public List<DiscussionThread> getAllThreads() {
        return new ArrayList<>(threadMap.values());
    }

    public DiscussionThread getThreadById(Long id) {
        DiscussionThread thread = threadMap.get(id);
        if (thread != null) {
            thread.setReplies(replyMap.get(id));
        }
        return thread;
    }

    public void saveReply(Reply reply) {
        reply.setId(replyIdGenerator.getAndIncrement());
        reply.setCreateTime(new Date());
        replyMap.get(reply.getThreadId()).add(reply);
    }

    public List<Reply> getRepliesByThreadId(Long threadId) {
        return replyMap.getOrDefault(threadId, new ArrayList<>());
    }

}
