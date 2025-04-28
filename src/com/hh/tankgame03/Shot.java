package com.hh.tankgame03;

public class Shot implements Runnable {
    int x;
    int y;
    int direction; //子弹的方向
    int speed = 5;//子弹的速度
    boolean isLive = true;//子弹是否还存活

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {//改变横纵坐标，让子弹的动起来
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向改变x,y坐标
            switch (direction) {
                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }
            //测试一下
            System.out.println(x + " " + y);
            //超过边界就销毁
            if (!(x>=0 && x <= 1000 && y>=0 && y <= 750)) {
                isLive = false;
                System.out.println("子弹线程结束~");
                break;
            }
        }

    }
}
