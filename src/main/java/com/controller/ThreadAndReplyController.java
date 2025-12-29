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
    // ===== 模拟存储（如果是数据库，替换为Service/DAO调用即可） =====
    // 全局帖子列表，匹配你的DiscussionThread实体
    private static List<DiscussionThread> threadList = new ArrayList<>();
    private static Long nextThreadId = 1L; // 自增ID

    // ===== 帖子列表页（修复：传递帖子列表到页面） =====
    @GetMapping("/thread/list")
    public String threadList(HttpServletRequest request) {
        // 把全局帖子列表传给页面，匹配你list.jsp里的request.getAttribute("threads")
        request.setAttribute("threads", threadList);
        return "thread/list";
    }

    // ===== 帖子详情页（保留你的逻辑，补充查询帖子） =====
    @GetMapping("/thread/detail")
    public String threadDetail(@RequestParam("id") Long id, HttpServletRequest request) {
        // 根据ID查询帖子，匹配你的实体
        DiscussionThread thread = threadList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        request.setAttribute("thread", thread); // 传给详情页
        return "thread/detail";
    }

    // ===== 展示发布页面（解决405的核心，保留） =====
    @GetMapping("/thread/publish")
    public String showPublishThreadPage() {
        return "thread/publish";
    }

    // ===== 发布帖子（核心修复：保存帖子到列表） =====
    @PostMapping("/thread/publish")
    public String publishThread(
            @RequestParam("title") String title,  // 接收表单title参数
            @RequestParam("content") String content, // 接收表单content参数
            HttpSession session
    ) {
        // 1. 获取登录用户（匹配你list.jsp里的session.getAttribute("loginUser")）
        String loginUser = (String) session.getAttribute("loginUser");

        // 2. 创建帖子对象（完全匹配你的DiscussionThread构造器和setter）
        DiscussionThread newThread = new DiscussionThread();
        newThread.setId(nextThreadId++); // 自增ID
        newThread.setTitle(title);
        newThread.setContent(content);
        newThread.setAuthor(loginUser);
        newThread.setCreateTime(new Date()); // 设置创建时间
        newThread.setReplies(new ArrayList<>()); // 初始化回复列表（匹配你的实体）

        // 3. 保存到全局列表（发布的核心：让列表页能查到）
        threadList.add(newThread);

        // 4. 原有跳转逻辑不变
        return "redirect:/thread/list";
    }

    // ===== 回复发布（保留你的逻辑，补充回复保存） =====
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
            // 2. 创建回复（需确保你有Reply实体，结构如下）
            Reply newReply = new Reply();
            newReply.setId(System.currentTimeMillis()); // 临时ID
            newReply.setContent(content);
            newReply.setAuthor((String) session.getAttribute("loginUser"));
            newReply.setCreateTime(new Date());

            // 3. 添加到帖子的回复列表（匹配你的实体）
            thread.getReplies().add(newReply);
        }

        // 原有跳转逻辑不变
        return "redirect:/thread/detail?id=" + threadId;
    }
}