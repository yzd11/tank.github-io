package tankgame02;

/**
 * @author 禹治东
 * @version 1.0
 * 子弹
 */
public class Shot implements Runnable{
    int x;//子弹x坐标
    int y;//子弹y坐标
    int direct;//子弹方向
    int speed = 5;//子弹速度
    boolean isLive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击
        while (true) {
            try {//休眠50ms
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct) {
                case 0://向上
                    y = y - speed;
                    break;
                case 1://向右
                    x = x + speed;
                    break;
                case 2://向下
                    y = y + speed;
                    break;
                case 3://向右
                    x = x - speed;
                    break;
            }
            //当碰到面板时
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                isLive = false;
                break;
            }
        }
    }
}
