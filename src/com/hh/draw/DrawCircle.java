package com.hh.draw;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame {//JFrame对应窗口,可以理解成是一个画框
    //定义一个面板
    private MyPanel mp = null;//定义一个面板

    public static void main(String[] args) {
        new DrawCircle();
//        System.out.println("退出程序");
    }

    public DrawCircle() {
        //初始化面板
        mp = new MyPanel();
        //把面板放入到窗口{画框}
        this.add(mp);
        //设置窗口大小
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//关闭之后 程序停止
        this.setVisible(true);//可以现实
    }
}

//1,先定义一个Mypannel 继承JPanel 画图形 就在画板上画
class MyPanel extends JPanel {
    //说明:
    //1. MyPanel 对象就是一个画板
    //2. Graphics g 把 g 理解成一支画笔
    //3. Graphics 提供了很多绘图的方法
    //Graphics g
    public void paint(Graphics g) { //绘图方法
        super.paint(g);//调用父类的方法完成初始化
        System.out.println("paint方法被调用");
        //调用方法来画圆形
//        g.drawOval(10, 10, 100, 100);


        //演示绘制不同的图形。。。
//        1.画直线 drawLine(int x1,int y1,int x2,int y2)
//         g.drawLine(10, 10, 100, 100);
//        2.画矩形边框 drawRect(int x, int y, int width, int height)
//         g.drawRect(10, 10, 100, 100);
//        3.画椭圆边框 drawOval(int x, int y, int width, int height)
//         g.drawOval(100, 100, 100, 50);
//        4.填充矩形 fillRect(int x, int y, int width, int height)
        //先设置颜色
//        g.setColor(Color.blue);
//        g.fillRect(5, 5, 100, 100);
//        5.填充椭圆 fillOval(int x, int y, int width, int height)
//         g.setColor(Color.RED);
//         g.fillOval(50, 50, 100, 50);
//        6.画图片 drawlmage(Image img, int x, int y. ..)
            //1,先获取图片资源
//        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/xmbg.jpg"));
//        g.drawImage(image, 0, 0, 1920, 1200, this);
//        7.画字符串 drawString(String str, int x, int y) 这里是以左下角为参考系的。
            //设置画笔颜色
//        g.setColor(Color.RED);
//        g.setFont(new Font("隶书", Font.ITALIC, 50));
//        g.drawString("北京你好",50,50);
//        8.设置画笔的字体 setFont(Font font)
//        9.设置画笔的颜色 setColor(Color c)


    }
}

