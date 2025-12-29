# 论坛讨论系统

*基于 Spring MVC + JSP 开发的后端论坛项目*

## 一、项目概述

### 1.1 项目定位

一款轻量、易扩展的基础论坛系统，覆盖用户登陆注册、帖子互动全流程。

### 1.2 核心特性

- 功能完整：包含用户登录注册、帖子发布/回复核心模块

- 易部署：支持 Maven 一键打包，服务器 Tomcat 直接部署

## 二、功能详情

### 2.1 用户管理模块

- 账号注册：支持账号、密码信息提交，前后端双重数据校验

- 登录登出：基于 Session 管理登录状态，验证通过后绑定用户身份，登出时销毁会话

### 2.2 帖子互动模块

- 帖子列表：展示所有帖子的标题、发布人、发布时间、回复数，支持快速预览核心信息

- 帖子发布：登录用户可提交标题+正文内容，自动关联发布用户与时间戳

- 详情查看：点击帖子标题进入详情页，展示完整内容及所有回复记录

### 2.3 基础能力支撑

- 数据校验：前端 JS 预校验减少无效请求，后端通过 Hibernate Validator 强化校验逻辑

- 页面渲染：基于 JSTL 实现动态数据展示，路由跳转流畅，无冗余请求

- 异常处理：对非法操作（未登录发布、空内容提交等）给出友好提示，提升用户体验

## 三、技术栈说明

### 3.1 后端技术

|技术/框架|版本|核心作用|
|---|---|---|
|Spring MVC|6.1.13|使用 @Controller @GetMapping @PostMapping @RequestMapping 实现请求分发，通过 @Autowired 实现依赖注入，负责视图解析（返回 JSP 页面路径）|
|Jakarta Servlet|6.1.0|使用 HttpServletRequest HttpSession 等 Servlet API，处理请求参数、会话管理（如存储登录用户信息），适配 Web 容器运行|
|Java 基础类库||使用 java.util 包下的 List ArrayList Date stream 等类 / API，实现数据存储（模拟列表）、日期处理、集合操作|
|JSP||作为视图层技术，控制器通过 return "thread/list" 等语句跳转至 JSP 页面（如 thread/list.jsp register.jsp），用于页面渲染|
### 3.2 前端技术

- JSP：页面模板载体，嵌入 Java 代码实现动态内容生成

- HTML/CSS：负责页面布局与样式美化，优化视觉体验

- JavaScript：实现前端表单校验、登录状态判断等交互逻辑

### 3.3 构建与运行环境

- 构建工具：Maven 3.8+

- 运行环境：JDK 21.0.9

- 部署容器：Tomcat 11.0.11

## 四、项目目录结构

```plain text

discuss2/
├── src/                      # 核心源码目录
│  └── main/
│      ├── java/             # Java 后端代码
│      │   └── com/
│      │       ├── controller/  # 控制层：处理HTTP请求
│      │       ├── dao/         # 数据层：模拟数据存储
│      │       ├── service/     # 业务层：封装核心业务逻辑
│      │       └── model/
│      ├── resources/         # 配置文件目录
│      │   └── spring-mvc.xml  # Spring MVC 核心配置
│      └── webapp/            # 前端资源目录
│          ├── WEB-INF/
│          │   ├── views/       # JSP 页面文件
│          │   └── web.xml    # Web 应用配置
│          └── static/        # 静态资源（CSS）                    
├── pom.xml                   # Maven 依赖配置文件
├── README.md                  # 项目说明文档
└── .gitignore                 # Git 忽略规则文件
```

## 五、项目访问地址
- http://10.100.164.19:8080/discuss2/login
