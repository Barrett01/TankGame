package com.hh.tankgame;

import javax.swing.*;

public class HHTankGame01 extends JFrame {
    //定义一个Mypanel
    MyPanel mp  = null;
    public static void main(String[] args) {
    new HHTankGame01();
    }
    public HHTankGame01() {
        mp = new MyPanel();//初始化
        this.add(mp);//把面板添加进去
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }
}
