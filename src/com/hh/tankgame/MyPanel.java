package com.hh.tankgame;

import javax.swing.*;
import java.awt.*;

//坦克大战的绘图区域
public class MyPanel extends JPanel {
    //定义自己的坦克
    Hero hero = null;

    public MyPanel() {
         hero= new Hero(100,100);//初始化自己的坦克
    }
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形 默认黑色

        //画出坦克-方法
        drawTank(hero.getX(),hero.getY(),g,3,0);
    }

    //编写方法，画出坦克
    /*
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int dirent,int type){
        switch(type){
            case 0:  //我们自己的坦克
                g.setColor(Color.cyan);//青色
                break;
            case 1:
                g.setColor(Color.yellow);//黄色
                break;
        }
        //根据坦克方法，来绘制坦克
        switch (dirent){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);//左轮子
                g.fill3DRect(x+30,y,10,60,false);//右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//盖子
                g.fillOval(x+10,y+10,20,20);//圆形盖子
                g.drawLine(x+20,y,x+20,y+30);//炮筒
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);//左轮子
                g.fill3DRect(x,y+30,60,10,false);//右轮子
                g.fill3DRect(x+10,y+10,40,20,false);//盖子
                g.fillOval(x+20,y+10,20,20);//圆形盖子
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);//左轮子
                g.fill3DRect(x+30,y,10,60,false);//右轮子
                g.fill3DRect(x+10,y+10,20,40,false);//盖子
                g.fillOval(x+10,y+20,20,20);//圆形盖子
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://向左
                g.fill3DRect(x,y,60,10,false);//左轮子
                g.fill3DRect(x,y+30,60,10,false);//右轮子
                g.fill3DRect(x+10,y+10,40,20,false);//盖子
                g.fillOval(x+20,y+10,20,20);//圆形盖子
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }
}
