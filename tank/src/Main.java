import javax.swing.*;
import java.awt.*;

/**
 * @author 禹治东
 * @version 1.0
 */
public class Main extends JFrame {//JFrame对应一个窗口
    private Mypanel mp = null;
    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        //初始化面板
        mp = new Mypanel();
        //把面板放入窗口
        this.add(mp);
        //设置窗口大小
        this.setSize(400,300);
        //当点击窗口的小x程序退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
class Mypanel extends JPanel {
    //1. Mypanel是一个画板
    //2. Graphics是一个画笔
    //3. Graphics提供了许多画图方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);//调用父类的方法完成初始化
        //画圆形drawOval
        g.drawOval(10,10,100,100);
        //画直线 drawLine
        g.drawLine(10,10,100,100);
        //画矩形边框 drawRect
        g.drawRect(10,10,50,50);
        //设置画笔颜色 setColor
        g.setColor(Color.green);
        //填充矩形 fillRect
        g.fillRect(10,10,50,50);
        //填充椭圆 fillOval
        g.fillOval(10,10,100,100);
        //画图片 drawImage
        //1.获取图片资源
        Image image = Toolkit.getDefaultToolkit().getImage(Mypanel.class.getResource("Snipaste_2022-11-04_17-36-21.png"));
        g.drawImage(image,10,10,252,168,this);
        //画字符串 drawString
        g.setColor(Color.BLUE);
        g.setFont(new Font("隶书",Font.BOLD,50));
        g.drawString("中国",100,100);
    }
}