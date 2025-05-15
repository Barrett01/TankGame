package com.hh.tankgame03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//记录
public class Recorder {
    //定义变量，记录我方击毁敌军坦克数
    private static int allEnemyTankNum = 0;
    //定义IO对象，准备写数据到文件中
    private static BufferedWriter bw = null;
    private static String recordFile = "D:\\text\\myRecord.txt";

    //增加一个方法，退出时候，存盘
    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum+"\r\n");

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
