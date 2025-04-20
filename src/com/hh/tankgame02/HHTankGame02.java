package com.hh.tankgame02;

import javax.swing.*;
import java.awt.event.KeyListener;

public class HHTankGame02 extends JFrame {
    //定义一个Mypanel
    MyPanel mp  = null;
    public static void main(String[] args) {
        HHTankGame02 hhTankGame02 = new HHTankGame02();


    }
    public HHTankGame02() {
        mp = new MyPanel();//初始化
        this.add(mp);//把面板添加进去
        this.setSize(1000,750);
        this.addKeyListener(mp);//监听事件
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }
}
