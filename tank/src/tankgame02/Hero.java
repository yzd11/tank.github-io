package tankgame02;

import java.util.Vector;

/**
 * @author 禹治东
 * @version 1.0
 * 自己的坦克
 */
public class Hero extends Tank {
    //定义我方子弹
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }
    //攻击敌方坦克
    public void shotEnemyTank(){
        //创建子弹
        switch (getDriect()){
            case 0://向上
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1://向右
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2://向下
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3://向左
                shot = new Shot(getX(),getY()+20,3);
                break;
        }
        //添加子弹
        shots.add(shot);
        //启动射击线程
        new Thread(shot).start();
    }
}
