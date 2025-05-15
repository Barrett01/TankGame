package com.hh.tankgame03;

import java.io.*;
import java.util.Vector;

//记录
public class Recorder {
    //定义变量，记录我方击毁敌军坦克数
    private static int allEnemyTankNum = 0;
    //定义IO对象，准备写数据到文件中
    private static BufferedReader bur =null;
    private static BufferedWriter bw = null;
    private static String recordFile = "D:\\text\\myRecord.txt";
    //定义一个Node的Vector 用于保存敌人的信息node
    private static Vector<Node> nodes = new Vector<>();
    //定义一个Vector，指向MyPanel对象的 敌人坦克 Vector
    private static Vector<EnemyTank> enemyTanks = null;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks){
        Recorder.enemyTanks = enemyTanks;
    }
    //增加一个方法 用于读取recordFile 恢复相关信息
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
           bur  = new BufferedReader(new FileReader(recordFile));
           allEnemyTankNum = Integer.parseInt(bur.readLine());//先那第一行的数字
            //循环读取，生成nodes集合
            String line = "";
            while ((line = bur.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bur != null) {
                try {
                    bur.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }

    //增加一个方法，退出时候，存盘
    //保存敌人坦克的坐标和方便
    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum+"\r\n");
            //遍历敌人坦克 然后根据是否存活来保存
            //oop，定义一个属性，然后通过setxx的到敌人坦克Vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                //为了保险期间 再判断一下
                if (enemyTank.isLive){
                    //保存enemyTank信息
                    String record = enemyTank.getX()+" " + enemyTank.getY() +" " +enemyTank.getDirect();
                    //写入到文件
                    bw.write(record+"\r\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //击毁地方坦克 做记录{加的一个动作}
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
