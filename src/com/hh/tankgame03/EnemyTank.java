package com.hh.tankgame03;

import java.util.Vector;

//敌军坦克
public class EnemyTank extends Tank {
    Vector<Shot> shots = new Vector();
    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
