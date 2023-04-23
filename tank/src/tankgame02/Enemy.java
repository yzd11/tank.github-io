package tankgame02;

import java.util.Vector;

/**
 * @author 禹治东
 * @version 1.0
 * 敌方坦克
 */
public class Enemy extends Tank implements Runnable{
    boolean isLive = true;
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();

    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true){
            //通过shots.size()可调整子弹个数
            if (isLive && shots.size() < 1){
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
            //根据坦克的方向移动
            switch (getDriect()){
                case 0:
                    for (int i = 0; i < 30; i++) {
//                        if (getY() > 0 && !isTouchEnemy())
                            moveUp();
                        //休眠50ms
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
//                        if (getX() + 60 < 1000 && !isTouchEnemy())
                            moveRight();
                        //休眠50ms
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
//                        if (getY() + 60 < 750 && !isTouchEnemy())
                            moveDown();
                        //休眠50ms
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
//                        if (getX() > 0 && isTouchEnemy())
                            moveLeft();
                        //休眠50ms
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            //随机改变方向
            setDriect((int) (Math.random()*4));
            if (!isLive){
                break;
            }
        }
    }
}
