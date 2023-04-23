package tankgame02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

/**
 * @author 禹治东
 * @version 1.0
 * 绘图区域
 */
//监听键盘事件，实现KeyListener
public class MyPanel extends JPanel implements KeyListener ,Runnable {
    //定义自己的坦克
    Hero hero = null;
    //定义敌人的坦克
    Vector<Enemy> enemies = new Vector<>();
    //定义一个保存node的Vector
    Vector<Node> nodes = new Vector<>();
    //定义炸弹
    //当子弹击中时，加入一个Bomb对象到Bombs
    Vector<Bomb> bombs = new Vector<>();
    //定义三张图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    //进行初始化
    public MyPanel(String key) {
        File file = new File(Recorder.getRecordFile());
        if (file.exists()){
            nodes = Recorder.getinfo();
        }else {
            System.out.println("暂无游戏记录，重新开始游戏");
            key = "1";
        }
        //将MyPanel对象的enemies设置给Recorder中的enemies
        Recorder.setEnemies(enemies);
        //初始化自己坦克
        hero = new Hero(500,500);
        hero.setSpeed(5);
        //添加到enemies中判断是否相撞
        hero.setEnemies(enemies);
        switch (key){
            case "1":
                //初始化敌人坦克
                int enemySize = 3;
                for (int i = 0; i < enemySize; i++) {
                    //创建敌人坦克
                    Enemy enemy = new Enemy(100 * (i + 1), 0);
                    enemy.setEnemies(enemies);
                    //初始化方向
                    enemy.setSpeed((int)(Math.random()*4));
                    //初始化速度
                    enemy.setSpeed(2);
                    //添加敌人坦克
                    enemies.add(enemy);
                    //启动敌人坦克
                    new Thread(enemy).start();
                }
                break;
            case "2":
                //继续上局游戏
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建敌人坦克
                    Enemy enemy = new Enemy(node.getX(),node.getY());
                    enemy.setEnemies(enemies);
                    //初始化方向
                    enemy.setSpeed(node.getDirect());
                    //初始化速度
                    enemy.setSpeed(2);
                    //添加敌人坦克
                    enemies.add(enemy);
                    //启动敌人坦克
                    new Thread(enemy).start();
                }
                break;
            default:
                System.out.println("您的输入有误请重新输入");
        }
        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("Bomb1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("Bomb2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("Bomb3.png"));
        PlayAudio playAudio = new PlayAudio("src\\1.wav");
        playAudio.start();
    }
    public  void hitEnemyTank(Shot shot, Enemy tank){
        //判断子弹是否击中坦克
        switch (tank.getDriect()){
            case 0://向上
            case 2://向下
                if (shot.x > tank.getX() && shot.x < tank.getX()+40
                && shot.y > tank.getY() && shot.y < tank.getY() +60){
                    shot.isLive = false;
                    tank.isLive = false;
                    Recorder.addAllEnemyNum();
                }
                break;
            case 1://向右
            case 3://向左
                if (shot.x > tank.getX() && shot.x < tank.getX()+60
                && shot.y > tank.getY() && shot.y < tank.getY() +40){
                    shot.isLive = false;
                    tank.isLive = false;
                    Recorder.addAllEnemyNum();
                }
                break;
        }
    }
    public  void hitMyTank(Shot shot, Hero tank){
        //判断子弹是否击中坦克
        switch (tank.getDriect()){
            case 0://向上
            case 2://向下
                if (shot.x > tank.getX() && shot.x < tank.getX()+40
                        && shot.y > tank.getY() && shot.y < tank.getY() +60){
                    shot.isLive = false;
                    tank.isLive = false;
                }
                break;
            case 1://向右
            case 3://向左
                if (shot.x > tank.getX() && shot.x < tank.getX()+60
                        && shot.y > tank.getY() && shot.y < tank.getY() +40){
                    shot.isLive = false;
                    tank.isLive = false;
                }
                break;
        }
    }
    public  void hitHero(){//判断敌方坦克是否击中我方
        //遍历敌方所有坦克
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            //遍历敌方所有子弹
            for (int j = 0; j < enemy.shots.size(); j++) {
                Shot shot = enemy.shots.get(j);
                //进行判断
                if (hero.isLive && shot.isLive){
                    hitMyTank(shot,hero);
                }
                if (hero != null && hero.isLive == false){
                    //添加爆炸
                    bombs.add(new Bomb(hero.getX(), hero.getY()));
                }
            }
        }
    }
    //编写方法记录我方击毁敌方坦克的信息
    public void showInfo(Graphics g){
        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累计击毁敌方坦克",1020,30);
        //画出敌方坦克图标
        drawTank(1020,60,g,0,1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyNum()+"",1080,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色
        showInfo(g);
        if (hero.isLive)
        drawTank(hero.getX(),hero.getY(),g,hero.getDriect(),0);
        //画出敌人的坦克
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.isLive){
            drawTank(enemy.getX(),enemy.getY(),g,enemy.getDriect(),1);
            for (int j = 0; j < enemy.shots.size(); j++) {
                //取出子弹
                Shot shot = enemy.shots.get(j);
                //绘制子弹
                if (shot.isLive) {
                    g.setColor(Color.RED);
                    g.draw3DRect(shot.x, shot.y - 1, 1, 1, false);
                } else {
                    enemy.shots.remove(shot);
                }
            }
            }

        }
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot !=null && shot.isLive){
                g.setColor(Color.GREEN);
                g.draw3DRect(shot.x,shot.y-1,1,1,false);
            }else {
                hero.shots.remove(shot);
            }
        }
        //如果Bombs对象中有炸弹，就画出
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6){
                g.drawImage(image3, bomb.x, bomb.y, 61,61,this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 61,61,this);
            }else {
                g.drawImage(image1, bomb.x, bomb.y, 61,61,this);
            }
            bomb.lifeDown();
            //判断生命值是否为零
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }
    }
    //编写方法，绘制坦克

    /**
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direct 坦克方向（上下左右）
     * @param type 坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        //根据类型设置颜色
        switch (type){
            case 0://自己的坦克
                g.setColor(Color.green);
                break;
            case 1://敌人的坦克
                g.setColor(Color.red);
                break;
        }
        //根据方向来绘制坦克
        switch (direct){
            //direct 表示方向（0:上 1:右 2:下 3:左）
            case 0://表示向上
                g.fill3DRect(x,y,10,60,false);//画出左边轮子
                g.fill3DRect(x+30,y,10,60,false);//画出右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出中间矩形
                g.fillOval(x+10,y+20,20,20);//画出盖子
                g.fillRect(x+17,y,6,30);//画出炮筒
                break;
            case 1://表示向右
                g.fill3DRect(x,y,60,10,false);//画出左边轮子
                g.fill3DRect(x,y+30,60,10,false);//画出右边轮子
                g.fill3DRect(x+10,y+10,40,20,false);//画出中间矩形
                g.fillOval(x+20,y+10,20,20);//画出盖子
                g.fillRect(x+30,y+17,30,6);//画出炮筒
                break;
            case 2://表示向下
                g.fill3DRect(x,y,10,60,false);//画出左边轮子
                g.fill3DRect(x+30,y,10,60,false);//画出右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出中间矩形
                g.fillOval(x+10,y+20,20,20);//画出盖子
                g.fillRect(x+17,y+30,6,30);//画出炮筒
                break;
            case 3://表示向左
                g.fill3DRect(x,y,60,10,false);//画出左边轮子
                g.fill3DRect(x,y+30,60,10,false);//画出右边轮子
                g.fill3DRect(x+10,y+10,40,20,false);//画出中间矩形
                g.fillOval(x+20,y+10,20,20);//画出盖子
                g.fillRect(x,y+17,30,6);//画出炮筒
                break;
            default:
                System.out.println("暂不处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理按下w,a,s,d键的变化
    @Override
    public void keyPressed(KeyEvent e) {
        if (hero.isLive){
            if(e.getKeyCode()==KeyEvent.VK_W){//按下w键
                hero.setDriect(0);
                hero.moveUp();
            } else if (e.getKeyCode()==KeyEvent.VK_D) {//按下a键
                hero.setDriect(1);
                hero.moveRight();
            } else if (e.getKeyCode()==KeyEvent.VK_S) {//按下s键
                hero.setDriect(2);
                hero.moveDown();
            } else if (e.getKeyCode()==KeyEvent.VK_A) {//按下d键
                hero.setDriect(3);
                hero.moveLeft();
            }
            if (e.getKeyCode()==KeyEvent.VK_J){//当用户按下J,就发射
                    hero.shotEnemyTank();
            }
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //判断是否击中敌人坦克
            for (int i = 0; i < hero.shots.size(); i++) {
                Shot shot = hero.shots.get(i);
                if (shot !=null && shot.isLive){//判断子弹是否存在
                    //遍历敌人所有坦克
                    for (int j = 0; j < enemies.size(); j++) {
                        //进行判断
                        Enemy enemy = enemies.get(j);
                        hitEnemyTank(shot,enemy);
                        if (enemy.isLive == false){
                            //添加爆炸
                            bombs.add(new Bomb(enemy.getX(), enemy.getY()));
                            //移除死亡坦克
                            enemies.remove(enemy);
                        }
                    }
                }
            }
            //判断是否击中我方坦克
            if(hero.isLive)
            hitHero();
            //重绘
            this.repaint();
        }
    }
}
