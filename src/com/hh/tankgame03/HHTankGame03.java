package com.hh.tankgame03;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HHTankGame03 extends JFrame {
    //定义一个Mypanel
    MyPanel mp  = null;
    public static void main(String[] args) {
        HHTankGame03 hhTankGame03 = new HHTankGame03();


    }
    public HHTankGame03() {
        mp = new MyPanel();//初始化
        new Thread(mp).start();
        this.add(mp);//把面板添加进去
        this.setSize(1300,950);
        this.addKeyListener(mp);//监听事件
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
