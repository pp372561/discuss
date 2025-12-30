package com.controller;

import com.model.DiscussionThread;
import com.model.Reply; // 确保Reply实体也存在
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ThreadAndReplyController {

    private static List<DiscussionThread> threadList = new ArrayList<>();
    private static Long nextThreadId = 1L; // 自增ID

    @GetMapping("/thread/list")
    public String threadList(HttpServletRequest request) {

        request.setAttribute("threads", threadList);
        return "thread/list";
    }

    @GetMapping("/thread/detail")
    public String threadDetail(@RequestParam("id") Long id, HttpServletRequest request) {

        DiscussionThread thread = threadList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        request.setAttribute("thread", thread); // 传给详情页
        return "thread/detail";
    }

    // ===== 展示发布页面 =====
    @GetMapping("/thread/publish")
    public String showPublishThreadPage() {
        return "thread/publish";
    }

    // ===== 发布帖子 =====
    @PostMapping("/thread/publish")
    public String publishThread(
            @RequestParam("title") String title,  // 接收表单title参数
            @RequestParam("content") String content, // 接收表单content参数
            HttpSession session
    ) {
        // 1. 获取登录用户
        String loginUser = (String) session.getAttribute("loginUser");

        // 2. 创建帖子对象
        DiscussionThread newThread = new DiscussionThread();
        newThread.setId(nextThreadId++); // 自增ID
        newThread.setTitle(title);
        newThread.setContent(content);
        newThread.setAuthor(loginUser);
        newThread.setCreateTime(new Date()); // 设置创建时间
        newThread.setReplies(new ArrayList<>()); // 初始化回复列表

        // 3. 保存到全局列表
        threadList.add(newThread);


        return "redirect:/thread/list";
    }

    // ===== 回复发布 =====
    @PostMapping("/reply/publish")
    public String publishReply(
            @RequestParam("threadId") Long threadId,
            @RequestParam("content") String content,
            HttpSession session
    ) {
        // 1. 找到对应帖子
        DiscussionThread thread = threadList.stream()
                .filter(t -> t.getId().equals(threadId))
                .findFirst()
                .orElse(null);

        if (thread != null) {
            // 2. 创建回复
            Reply newReply = new Reply();
            newReply.setId(System.currentTimeMillis()); // 临时ID
            newReply.setContent(content);
            newReply.setAuthor((String) session.getAttribute("loginUser"));
            newReply.setCreateTime(new Date());

            // 3. 添加到帖子的回复列表
            thread.getReplies().add(newReply);
        }

        return "redirect:/thread/detail?id=" + threadId;
    }

}
