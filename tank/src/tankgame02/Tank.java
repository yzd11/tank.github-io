package tankgame02;

import java.util.Vector;

/**
 * @author 禹治东
 * @version 1.0
 * 坦克
 */
public class Tank {
    private int x;
    private int y;
    private int driect;//坦克方向 0 1 2 3
    private int speed ;
    boolean isLive = true;
    Vector<Enemy> enemies = new Vector<>();

    public void setEnemies(Vector<Enemy> enemies) {
        this.enemies = enemies;
    }
    public boolean isTouchEnemy(){
        //判断当前坦克位置
        switch (this.getDriect()){
            case 0://上
                //将当前坦克对每个坦克进行比较
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //除去自己
                    if (enemy != this){
                        //当对方向上或向下移动时
                        if (enemy.getDriect() == 0 || enemy.getDriect() == 2){
                            //(x,y)点
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX()+40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY()+60){
                                return true;
                            }
                            //(x+40,y)点
                            if (this.getX()+40 >= enemy.getX()
                                    && this.getX()+40 <= enemy.getX()+40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY()+60){
                                return true;
                            }
                        }
                        //当对方向左或向右移动时
                        if (enemy.getDriect() == 1 || enemy.getDriect() == 3){
                            //(x,y)点
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX()+60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY()+40){
                                return true;
                            }
                            //(x+40,y)点
                            if (this.getX()+40 >= enemy.getX()
                                    && this.getX()+40 <= enemy.getX()+60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://右
                //将当前坦克对每个坦克进行比较
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //除去自己
                    if (enemy != this){
                        //当对方向上或向下移动时
                        if (enemy.getDriect() == 0 || enemy.getDriect() == 2){
                            //(x+60,y)点
                            if (this.getX()+60 >= enemy.getX()
                                    && this.getX()+60 <= enemy.getX()+40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY()+60){
                                return true;
                            }
                            //(x+60,y+40)点
                            if (this.getX()+60 >= enemy.getX()
                                    && this.getX()+60 <= enemy.getX()+40
                                    && this.getY()+40 >= enemy.getY()
                                    && this.getY()+40 <= enemy.getY()+60){
                                return true;
                            }
                        }
                        //当对方向左或向右移动时
                        if (enemy.getDriect() == 1 || enemy.getDriect() == 3){
                            //(x+60,y)点
                            if (this.getX()+60 >= enemy.getX()
                                    && this.getX()+60 <= enemy.getX()+60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY()+40){
                                return true;
                            }
                            //(x+60,y+40)点
                            if (this.getX()+60 >= enemy.getX()
                                    && this.getX()+60 <= enemy.getX()+60
                                    && this.getY()+40 >= enemy.getY()
                                    && this.getY()+40 <= enemy.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://下
                //将当前坦克对每个坦克进行比较
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //除去自己
                    if (enemy != this){
                        //当对方向上或向下移动时
                        if (enemy.getDriect() == 0 || enemy.getDriect() == 2){
                            //(x,y+60)点
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX()+40
                                    && this.getY()+60 >= enemy.getY()
                                    && this.getY()+60 <= enemy.getY()+60){
                                return true;
                            }
                            //(x+40,y+60)点
                            if (this.getX()+40 >= enemy.getX()
                                    && this.getX()+40 <= enemy.getX()+40
                                    && this.getY()+60 >= enemy.getY()
                                    && this.getY()+60 <= enemy.getY()+60){
                                return true;
                            }
                        }
                        //当对方向左或向右移动时
                        if (enemy.getDriect() == 1 || enemy.getDriect() == 3){
                            //(x,y+60)点
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX()+60
                                    && this.getY()+60 >= enemy.getY()
                                    && this.getY()+60 <= enemy.getY()+40){
                                return true;
                            }
                            //(x+40,y+60)点
                            if (this.getX()+40 >= enemy.getX()
                                    && this.getX()+40 <= enemy.getX()+60
                                    && this.getY()+60 >= enemy.getY()
                                    && this.getY()+60 <= enemy.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://左
                //将当前坦克对每个坦克进行比较
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    //除去自己
                    if (enemy != this){
                        //当对方向上或向下移动时
                        if (enemy.getDriect() == 0 || enemy.getDriect() == 2){
                            //(x,y)点
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX()+40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY()+60){
                                return true;
                            }
                            //(x,y+40)点
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX()+40
                                    && this.getY()+40 >= enemy.getY()
                                    && this.getY()+40 <= enemy.getY()+60){
                                return true;
                            }
                        }
                        //当对方向左或向右移动时
                        if (enemy.getDriect() == 1 || enemy.getDriect() == 3){
                            //(x,y)点
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX()+60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY()+40){
                                return true;
                            }
                            //(x,y+40)点
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX()+60
                                    && this.getY()+40 >= enemy.getY()
                                    && this.getY()+40 <= enemy.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }
    public int getDriect() {
        return driect;
    }

    public void setDriect(int driect) {
        this.driect = driect;
    }

    public Tank(int x, int y) {
        this.x = x;//坦克的横坐标
        this.y = y;//坦克的纵坐标
    }
    //坦克移动方法
    public void moveUp(){//向上移动
        if (getY() > 0 && !this.isTouchEnemy())
        y = y-speed;
    }
    public void moveDown(){//向下移动
        if (getY() + 60 < 750 && !this.isTouchEnemy())
        y = y+speed;
    }
    public void moveRight(){//向右移动
        if (getX() + 60 <1000 && !this.isTouchEnemy())
        x = x+speed;
    }
    public void moveLeft(){//向左移动
        if (getX() > 0 && !this.isTouchEnemy())
            x = x-speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
}
