package com.hh.tankgame03;

import java.util.Vector;

//敌军坦克
public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            //限制敌方坦克至少发射一颗子弹
            if (isLive && shots.size() == 0) {
                Shot s = null;
                //根据不同的方向，创建对应的子弹
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s = new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s = new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s = new Shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }
            //根据坦克方向来继续移动
            switch (getDirect()) {
                case 0:
                    //让坦克一个方向走30步
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //休眠50秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //随机改变方向
            setDirect((int) (Math.random() * 4));
            /*
            写并发程序，一定要考虑清楚线程什么时候结束
             */
            if (!isLive) {
                break;
            }
        }
    }
}
