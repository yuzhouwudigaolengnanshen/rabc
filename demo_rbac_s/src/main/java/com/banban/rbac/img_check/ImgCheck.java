package com.banban.rbac.img_check;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


@WebServlet(urlPatterns = "/img_check1")
public class ImgCheck extends HttpServlet {
    //设置图片宽高
    private static final int width = 200;
    private static final int height = 200;
    //设置字符 数字
    private char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ2345678".toCharArray();
    //创建一个随机对象
    Random random = new Random();
    //随机颜色
    private Color setRandomColor(int start,int end)
    {
        int r,g,b;
        if (start>255){
            start=255;
        }
        if (end<255){
            end=255;
        }
        r=start+random.nextInt(end-start);
        g=start+random.nextInt(end-start);
        b=start+random.nextInt(end-start);
        return new Color(r,g,b);
    }
    //设置背景颜色
    private void setBackGroud(Graphics2D g){
        g.setColor(setRandomColor(157,255));
        //填充矩形
        g.fillRect(0,0,width,height);
    }
    //边框
    private void drawBorder(Graphics2D g){
        g.setColor(setRandomColor(0,156));
        g.drawRect(0,0,width-1,height-1);
    }
    //
    private void drawRandomLine(Graphics2D g){
        for (int i = 0; i < 6; i++) {
            g.setColor(setRandomColor(0,156));
            int x1 = random.nextInt(150);
            int x2 = random.nextInt(150);
            int y1 = random.nextInt(90);
            int y2 = random.nextInt(90);
            g.drawLine(x1,y1,x2,y2);
        }
    }
    //画雪花
    private void drawRandomSnow(Graphics2D g){
        for (int i = 0; i < 20; i++) {
            g.setColor(setRandomColor(157,255));
            int x = random.nextInt(200);
            int y = random.nextInt(100);
            g.drawString("*",x,y);
        }

    }
    //生成随机字符串
    private String drawRandomCode(Graphics2D g){
        int x = width/4;
        int index;
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = random.nextInt(chars.length);
            g.setColor(setRandomColor(0,156));

            g.setFont(new Font("黑体", Font.BOLD,25));

            double angle = (random.nextInt()%30)*Math.PI/180;

            g.rotate(angle,(i*x)+8,random.nextInt(10)+25);
            g.drawString(chars[index]+"",(i*x)+8,random.nextInt(10)+25);
            g.rotate(-angle,(i*x)+8,random.nextInt(10)+25);
            s.append(chars[index]);
        }
        return s.toString();
    }



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置不缓存图片
        resp.setHeader("Pargma","No-cache");
        resp.setHeader("Cache-Control","No-cache");
        resp.setDateHeader("Expires",0);

        // 图片验证码
        // 1. 创建图片对象， 构造参数分别代表 宽、高、图片格式
        BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
        // 2. 创建画布对象
        Graphics2D g = image.createGraphics();
        // 3.背景
        setBackGroud(g);
        // 4.边框
        drawBorder(g);
        //5.边线
        drawRandomLine(g);
        //6.雪花
        drawRandomSnow(g);
        //7.随机代码
        String code = drawRandomCode(g);
        //响应格式
        resp.setContentType("image/png");
        // 参数1 图片对象， 参数2 图片压缩格式 png|jpeg， 参数3: 输出字节流
        ImageIO.write(image, "png", resp.getOutputStream());
        //关闭
        g.dispose();
    }
}
