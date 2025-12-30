package com.controller;

import com.service.QaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class CaptchaController {
    private static final String CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int LENGTH = 4;
    private final Random random = new Random();

    @Autowired // 自动注入Service
    private QaService qaService;

    // 路径与原有一致：/captcha
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }

        StringBuilder captcha = new StringBuilder();
        g.setFont(new Font("Arial", Font.BOLD, 28));
        for (int i = 0; i < LENGTH; i++) {
            char c = CHAR_SET.charAt(random.nextInt(CHAR_SET.length()));
            captcha.append(c);
            g.setColor(new Color(random.nextInt(100), random.nextInt(100), random.nextInt(255)));
            g.drawString(String.valueOf(c), 20 + i * 20, 30 + random.nextInt(10));
        }

        HttpSession session = req.getSession();
        qaService.saveCaptcha(session.getId(), captcha.toString()); // 替换为Service调用

        resp.setContentType("image/png");
        javax.imageio.ImageIO.write(image, "png", resp.getOutputStream());
        g.dispose();
    }

}
