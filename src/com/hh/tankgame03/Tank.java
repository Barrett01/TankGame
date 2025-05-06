package com.hh.tankgame03;

public class Tank {
    private int x;
    private int y;
    private int direct; //坦克方向 0 上 ;1 右; 2 下; 3 左
    private int speed = 5;//速度



    //上下左右的移动
    public void moveUp() {
        if (getY() > 0){
            y -= speed;
        }
    }

    public void moveDown() {
        if (getY() +60< 750){
            y += speed;
        }

    }

    public void moveLeft() {
        if (getX() > 0){
            x -= speed;
        }

    }

    public void moveRight() {
        if (getX() +60 < 1000){
            x += speed;
        }

    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
