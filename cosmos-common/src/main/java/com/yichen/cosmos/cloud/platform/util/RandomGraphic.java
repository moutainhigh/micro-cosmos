package com.yichen.cosmos.cloud.platform.util;

import org.apache.commons.lang.math.RandomUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Lizhengxian on 2017/6/19.
 */

public class RandomGraphic {
    private static final String CONTENT_TYPE = "text/html; charset=gb2312";
    //设置字母的大小,大小
    private Font mFont = new Font("Menlo", Font.PLAIN, 20);

    public static void main(String[] args) throws IOException {
        int i = RandomUtils.nextInt(10);
        System.out.println(i);
        System.out.println(new RandomGraphic().randomCodeToBase64("FUCK"));
    }

    public String randomCodeToBase64(String code) throws IOException {
        int width = 80, height = 26;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(Color.WHITE);
        g.fillRect(1, 1, width - 1, height - 1);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);
        g.setFont(mFont);


        //g.setColor(getRandColor(160,200));

        //画随机线
//        for (int i=0;i<155;i++)
//        {
//            int x = random.nextInt(width - 1);
//            int y = random.nextInt(height - 1);
//            int xl = random.nextInt(6) + 1;
//            int yl = random.nextInt(12) + 1;
//            g.drawLine(x,y,x + xl,y + yl);
//        }
//
//
//        //从另一方向画随机线
//        for (int i = 0;i < 70;i++)
//        {
//            int x = random.nextInt(width - 1);
//            int y = random.nextInt(height - 1);
//            int xl = random.nextInt(12) + 1;
//            int yl = random.nextInt(6) + 1;
//            g.drawLine(x,y,x - xl,y - yl);
//        }

        g.setColor(Color.lightGray);
        for (int i = 0; i < 10; i++) {
            int s = i * 10;
            g.drawLine(s + 10, 0, s, height);
        }


        g.setColor(Color.WHITE);
        g.fillRect(10, 4, 58, 16);


        g.setColor(Color.BLACK);
        //生成随机数,并将随机数字转换为字母
        for (int i = 0; i < 4; i++) {
            g.drawString(String.valueOf(code.charAt(i)), 15 * i + 10, 20);
        }
        //todo 上面这个循环第一次运行的时候需要1秒多，待优化
        g.dispose();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", byteArrayOutputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(byteArrayOutputStream.toByteArray()).replaceAll("\r|\n", "");
    }

    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}