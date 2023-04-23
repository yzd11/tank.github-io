package tankgame02;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author 禹治东
 * @version 1.0
 * 坦克游戏
 */
public class TankGame02 extends JFrame {
    MyPanel mp = null;
    Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        TankGame02 tankGame02 = new TankGame02();
    }
    public TankGame02(){
        System.out.println("请选择");
        System.out.println("1 新游戏 \n2 继续上局");
        String key = in.nextLine();
        mp = new MyPanel(key);
        new Thread(mp).start();
        //把面板放入窗口
        this.add(mp);
        //设置窗口大小
        this.setSize(1400,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //在JFrame增加关闭窗口的操作
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
