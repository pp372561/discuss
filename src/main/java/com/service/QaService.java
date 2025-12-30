package com.service;

import com.dao.DataService;
import com.model.DiscussionThread;
import com.model.Reply;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QaService {
    // Spring自动注入DataService
    @Autowired
    private DataService dataService;

    // ====== 用户相关 ======
    public User getUserByUsername(String username) {
        return dataService.getUserByUsername(username);
    }

    public boolean registerUser(String username, String password) {
        return dataService.registerUser(username, password);
    }

    // ====== 验证码相关 ======
    public void saveCaptcha(String sessionId, String captcha) {
        dataService.saveCaptcha(sessionId, captcha);
    }

    public String getCaptcha(String sessionId) {
        return dataService.getCaptcha(sessionId);
    }

    public void removeCaptcha(String sessionId) {
        dataService.removeCaptcha(sessionId);
    }

    // ====== 帖子相关 ======
    public void saveThread(DiscussionThread thread) {
        dataService.saveThread(thread);
    }

    public List<DiscussionThread> getAllThreads() {
        return dataService.getAllThreads();
    }

    public DiscussionThread getThreadById(Long id) {
        return dataService.getThreadById(id);
    }

    // ====== 回复相关 ======
    public void saveReply(Reply reply) {
        dataService.saveReply(reply);
    }

    public List<Reply> getRepliesByThreadId(Long threadId) {
        return dataService.getRepliesByThreadId(threadId);
    }

}
