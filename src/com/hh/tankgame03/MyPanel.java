package com.hh.tankgame03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//坦克大战的绘图区域
/*
为了监听键盘事件 实现KeyLister
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义自己的坦克
    Hero hero = null;
    //定义一个Node的对象Vector 用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes = new Vector<>();
    //定义敌军的坦克 由于比较多,所以要用数组{介于要使用线程这里使用Vector}
    Vector<EnemyTank> enemyTanks = new Vector();
    int enemyTanksSize = 3;

    //定义一个Vector，用于存放炸弹
    Vector<Bomb> bombs = new Vector<>();
    //定义三张照片,用于现实爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    //绘画
    public MyPanel(String key) {
        //先判断记录的文件是否存在
        //如果不存在 让重新开始
        File file = new File(Recorder.getRecordFile());
        if (file.exists()) {
            nodes = Recorder.getNodesAndEnemyTankRec();
        }else {
            System.out.println("File not found;已经为你开局新的");
            key = "1";
        }

        //将MyPanel对象的enemyTanks设置给Recorder的enemyTanks
        Recorder.setEnemyTanks(enemyTanks);

        //初始化自己的坦克
        hero = new Hero(100, 100);
        hero.setSpeed(8);

        //初始化敌人的坦克
        switch (key) {
            case "1":
                for (int i = 0; i < enemyTanksSize; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(0);//设置方向
                    new Thread(enemyTank).start();//启动敌军坦克线程
                    //加入子弹
                    //  放入集合中  ：   enemyTank.shots.add(new Shot(enemyTank.getX()+20, enemyTank.getY()+60, enemyTank.getDirect()));
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入到enemyTank的Vector成员
                    enemyTank.shots.add(shot);
                    //启动 shot对象
                    new Thread(shot).start();
                    //加入
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(node.getDirect());//设置方向
                    new Thread(enemyTank).start();//启动敌军坦克线程
                    //加入子弹
                    //  放入集合中  ：   enemyTank.shots.add(new Shot(enemyTank.getX()+20, enemyTank.getY()+60, enemyTank.getDirect()));
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入到enemyTank的Vector成员
                    enemyTank.shots.add(shot);
                    //启动 shot对象
                    new Thread(shot).start();
                    //加入
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("输入有误");
        }


        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.jpg"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.jpg"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.jpg"));
    }

    //编写方法，现实我方坦克摧毁地方坦克的信息
    public void showInfo(Graphics g) {
        //画出玩家成家
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累积击毁敌方坦克：", 1020, 30);

        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemyTankNum() + " ", 1080, 100);
    }

    //绘图
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形 默认黑色
        showInfo(g);
        //画出我方坦克-方法
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }

        //画我方坦克的子弹
//        if (hero.shot != null && hero.shot.isLive == true) {
//            g.draw3DRect(hero.shot.x, hero.shot.y, 2, 2, false);
//        }
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            if (shot != null && shot.isLive == true) {
                g.draw3DRect(shot.x, shot.y, 2, 2, false);
            } else {
                hero.shots.remove(shot);
            }
        }

        //如果bombs 集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画出对应的图片
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让这个炸弹的生命值减少
            bomb.lifeDown();
            //如果bomb life 为0, 就从bombs 的集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

        //画出敌军坦克-方法
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌军坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {//判断坦克是否存活
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出所有的子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {//存活就绘画
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {//否者移除shots集合
                        enemyTank.shots.remove(shot);
                    }
                }
            }

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
/*
如果我们的坦克可以发射多个子弹，在判断我方子弹是否击中敌人坦克时，
就需要把我们的子弹集合中所有的子弹都取出来和敌人坦克比较
 */

    //编写敌军坦克击败我军坦克
//    //要点：与下面的代码是不是极其重复 因此我们可以直接使用而不是再写一次
//    public void enemhitTank(Shot s ,Hero hero) {
//        switch (hero.getDirect()){
//            case 0://坦克向上
//            case 2://坦克向下
//                if (s.x > hero.getX() && s.x < hero.getX() + 40
//                        && s.y > hero.getY() && s.y < hero.getY() + 60) {
//                    s.isLive = false;
//                    hero.isLive = false;//当敌方坦克的子弹击中我军坦克，让它消失
//
//
//                    //创建bomb对象，加入到bombs集合
//                    Bomb bomb = new Bomb(hero.getX(), hero.getY());
//                    bombs.add(bomb);
//                }
//                break;
//            case 1://坦克向右
//
//            case 3://坦克向左
//                if (s.x > hero.getX() && s.x < hero.getX() + 60
//                        && s.y > hero.getY() && s.y < hero.getY() + 40) {
//                    s.isLive = false;
//                    hero.isLive = false;//当敌方坦克的子弹击中我军坦克，让它消失
//
//
//
//                    //创建bomb对象，加入到bombs集合
//                    Bomb bomb = new Bomb(hero.getX(), hero.getY());
//                    bombs.add(bomb);
//                }
//                break;
//        }
//    }
//编写敌军坦克击败我军坦克的方法
    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }
            }
        }
    }

    //编写方法：判断我方坦克是否击中 敌人坦克
    public void hitTank(Shot s, Tank tank) {//这里为了方便可以判断敌军坦克击败我军坦克 所以使用Tank
        switch (tank.getDirect()) {
            case 0://坦克向上
            case 2://坦克向下
                if (s.x > tank.getX() && s.x < tank.getX() + 40
                        && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.isLive = false;
                    tank.isLive = false;
                    //当我们的子弹击中敌方坦克，让它从集合中消失
                    if (tank instanceof EnemyTank) {
                        enemyTanks.remove(tank);
                        Recorder.addAllEnemyTankNum();
                    }

                    //创建bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://坦克向右

            case 3://坦克向左
                if (s.x > tank.getX() && s.x < tank.getX() + 60
                        && s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.isLive = false;
                    tank.isLive = false;
                    //当我们的子弹击中敌方坦克，让它从集合中消失

                    if (tank instanceof EnemyTank) {
                        enemyTanks.remove(tank);
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
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
        if (e.getKeyCode() == KeyEvent.VK_J) {
//            if (hero.shot = null && !hero.shot.isLive){
//                hero.shot.isLive = false;
//            }
//            if(hero.shot== null || !hero.shot.isLive){//当子弹为空或者子弹生命周期结束，才能绘制下一颗子弹
//                hero.shotEnemyTank();
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
            //判断是否击中坦克
            for (int j = 0; j < hero.shots.size(); j++) {
                Shot shot = hero.shots.get(j);
                if (shot != null && shot.isLive && hero.isLive) {//如果我的坦克子弹还存活
                    //遍历敌人所有坦克
                    for (int i = 0; i < enemyTanks.size(); i++) {
                        EnemyTank enemyTank = enemyTanks.get(i);
                        hitTank(shot, enemyTank);
                    }
                }
            }
            //判断是否击中我方坦克{这里跟上面做个区别 用这种更简洁}
            hitHero();
            //绘画
            this.repaint();
        }
    }
}
