package com.hh.tankgame03;

import java.util.Vector;

//自己的坦克
public class Hero extends Tank {
    Shot shot = null;//定义一个Shot对象，表示一个射程（线程）


    //可以发射多个子弹,创建的数组
   Vector<Shot> shots = new  Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {
        //限制子弹的个数不能超过五个
        if (shots.size() >= 5){
            return;
        }
        //创建Shot对象，根据当前Hero的位置和方向来创建
        switch (getDirect()) {
            case 0://上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1://右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2://下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3://左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        //把创建的子弹添加到数组中
        shots.add(shot);
        //开启线程
        new Thread(shot).start();
    }

}
