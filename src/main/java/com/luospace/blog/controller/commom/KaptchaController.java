package com.luospace.blog.controller.commom;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.luospace.blog.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("/common")
public class KaptchaController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/captcha")
    public void getKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        ServletOutputStream responseOutputStream =
                response.getOutputStream();
        final HttpSession httpSession=request.getSession();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpSession.setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }finally {
            responseOutputStream.close();
        }
    }
}
