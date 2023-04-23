package tankgame02;

import java.io.*;
import java.util.Vector;

/**
 * @author 禹治东
 * @version 1.0
 */
public class Recorder {
    //定义变量，记录我方击毁坦克数量
    private  static int allEnemyNum = 0;
    //定义IO对象，准备写数据到文件中
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myRecord.txt";
    //定义Vector指向Mypanel 对象的敌人坦克 Vector
    private static Vector<Enemy> enemies = null;
    //定义一个Node的Vector用来存储敌人信息
    private static Vector<Node> nodes = new Vector<>();
    //添加方法用来恢复信息
    public static Vector<Node> getinfo(){
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyNum = Integer.parseInt(br.readLine());
            String line = " ";
            while((line = br.readLine())!=null){
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]),Integer.parseInt(xyd[1]),Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

    public static void setEnemies(Vector<Enemy> enemies) {
        Recorder.enemies = enemies;
    }

    public static int getAllEnemyNum() {
        return allEnemyNum;
    }
    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyNum+"");
            bw.newLine();
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if(enemy.isLive){
                    String record = enemy.getX()+" "+enemy.getY()+" "+enemy.getDriect();
                    bw.write(record);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void setAllEnemyNum(int allEnemyNum) {
        Recorder.allEnemyNum = allEnemyNum;
    }
    //当我方坦克击毁一辆敌人坦克,allEnemyNum++
    public static void addAllEnemyNum(){
        Recorder.allEnemyNum++;
    }

    public static String getRecordFile() {
        return recordFile;
    }
}
