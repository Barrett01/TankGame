package com.hh.tankgame03;
/*
炸弹
 */
public class Bomb {
    int x ,y;//炸弹的坐标
    int life = 9;//炸弹的生命周期
    boolean isLive = true;//是否还存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void  lifeDown(){
        if (life > 0){
            life--;//让三张图片渐进展现，看出爆炸效果
        }else {
            isLive = false;
        }
    }
}
