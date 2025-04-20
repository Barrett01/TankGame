package com.hh.event_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
通过演示小球通过键盘控制上下移动->讲解java时间控制
 */
public class BallMove extends JFrame {
    MyPanel panel = null;
    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }
    public BallMove() {
         panel = new MyPanel();
         this.add(panel);
         this.addKeyListener(panel);
         this.setSize(400,300);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
    }

}
class MyPanel extends JPanel implements KeyListener {
    int x=10;
    int y =10;
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }
//监听键盘字符输出时,该方法触发
    @Override
    public void keyTyped(KeyEvent e) {

    }
//当某个键按下 触发
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println((char)(e.getKeyChar())+"被按下");
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y++;
        }else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y--;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x--;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x++;
        }
        this.repaint();
    }

//当某个键释放的时候 触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}