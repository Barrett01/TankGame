package com.hh.tankgame03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//坦克大战的绘图区域
/*
为了监听键盘事件 实现KeyLister
 */
public class MyPanel extends JPanel implements KeyListener ,Runnable {
    //定义自己的坦克
    Hero hero = null;
    //定义敌军的坦克 由于比较多,所以要用数组{介于要使用线程这里使用Vector}
    Vector<EnemyTank> enemyTanks = new Vector();
    int enemyTanksSize = 3;

    //初始化自己的坦克
    public MyPanel() {
        hero = new Hero(100, 100);
        hero.setSpeed(8);

        //初始化敌人的坦克
        for (int i = 0; i < enemyTanksSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
//            enemyTank.s

        }
    }

    //绘图
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形 默认黑色

        //画出坦克-方法
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        //画出敌军坦克-方法
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌军坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
        }

        //画出子弹
        if (hero.shot != null && hero.shot.isLive == true){
            g.draw3DRect(hero.shot.x,hero.shot.y,2,2,false);
        }
    }

    //编写方法，画出坦克
    /*
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int dirent, int type) {
        switch (type) {
            case 0:  //我们自己的坦克
                g.setColor(Color.cyan);//青色
                break;
            case 1:
                g.setColor(Color.yellow);//黄色
                break;
        }
        //根据坦克方法，来绘制坦克
        switch (dirent) {
            case 0://向上
                g.fill3DRect(x, y, 10, 60, false);//左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//盖子
                g.fillOval(x + 10, y + 10, 20, 20);//圆形盖子
                g.drawLine(x + 20, y, x + 20, y + 30);//炮筒
                break;
            case 1://向右
                g.fill3DRect(x, y, 60, 10, false);//左轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//右轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//盖子
                g.fillOval(x + 20, y + 10, 20, 20);//圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2://向下
                g.fill3DRect(x, y, 10, 60, false);//左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//盖子
                g.fillOval(x + 10, y + 20, 20, 20);//圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3://向左
                g.fill3DRect(x, y, 60, 10, false);//左轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//右轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//盖子
                g.fillOval(x + 20, y + 10, 20, 20);//圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    //监听事件1
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //监听事件2:键盘触发得以响应
    @Override
    public void keyPressed(KeyEvent e) {
        //改变坦克方向{移动}
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        }

        //按下j 发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J){
//            if (hero.shot = null && !hero.shot.isLive){
//                hero.shot.isLive = false;
//            }
            hero.shotEnemyTank();
        }

        //重绘
        this.repaint();
    }

    //监听事件3
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}
