package cn.xiit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {

    int snakeLen;
    int[] snakeX = new int[600];
    int[] snakeY = new int[600];
    String direction;   //方向
    boolean startStatus = false; //游戏状态
    boolean isFail = false; //是否失败

    int foodX,foodY;
    Random random = new Random();

    //设置重复执行定时器
    Timer timer = new Timer(100, (ActionListener) this);

    public SnakePanel(){
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void init(){
        snakeLen = 3;
        direction = "right";
        snakeX[0] = 100;snakeY[0] = 100;
        snakeX[1] = 75;snakeY[1] = 100;
        snakeX[2] = 50;snakeY[2] = 100;
        //随机初始化一个食物
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.DARK_GRAY);

        //画横幅
        SnakeData.header.paintIcon(this, g,11 , 2);
        g.fillRect(12,75, 875,600);

        //分数
        g.setColor(Color.RED);
        g.setFont(new Font("Default",Font.BOLD,14));
        g.drawString("长度："+ snakeLen, 700, 30);
        g.drawString("分数："+ snakeLen * 10, 700, 60);


        //画蛇头
        switch (direction){
            case "left":
                SnakeData.left.paintIcon(this, g , snakeX[0], snakeY[0]);
                break;
            case "up":
                SnakeData.up.paintIcon(this, g , snakeX[0], snakeY[0]);
                break;
            case "down":
                SnakeData.down.paintIcon(this, g , snakeX[0], snakeY[0]);
                break;
            default:
                SnakeData.right.paintIcon(this, g , snakeX[0], snakeY[0]);
        }

        //画身体
        for (int i = 1; i < snakeLen; i++) {
            SnakeData.body.paintIcon(this, g, snakeX[i],snakeY[i]);
        }
        //画食物
        SnakeData.food.paintIcon(this, g, foodX, foodY);

        //开始提示
        if (startStatus == false) {
            g.setColor(Color.PINK);
            g.setFont(new Font("Default",Font.BOLD, 30));
            g.drawString("空格开始/停止游戏",300 ,400);
        }
        //游戏结束提示
        if(isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("Default",Font.BOLD, 30));
            g.drawString("游戏结束，空格重新开始",300 ,400);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (startStatus && !isFail) {

            if (direction.equals("right") && snakeX[0] >= 850){
                isFail = true;//snakeX[0] = 25;
            }

            if (direction.equals("left") && snakeX[0] <= 25){
                isFail = true;//snakeX[0] = 850;
            }
            if (direction.equals("up") && snakeY[0] <= 75){
                isFail = true;//snakeY[0] = 650;
            }
            if (direction.equals("down") && snakeY[0] >= 650) {
                isFail = true;//snakeY[0] = 75;
            };

            if (!isFail) {
                for (int i = snakeLen - 1; i > 0; i--) {
                    snakeX[i]  = snakeX[i-1];
                    snakeY[i]  = snakeY[i-1];
                }

                //向右
                if (direction.equals("right")) {
                    snakeX[0]  = snakeX[0] + 25;
                }

                //向左
                if (direction.equals("left")) {
                    snakeX[0]  = snakeX[0] - 25;
                }

                //向上
                if (direction.equals("up")) {
                    snakeY[0]  = snakeY[0] - 25;
                }

                //向上
                if (direction.equals("down")) {
                    snakeY[0]  = snakeY[0] + 25;
                }

                //重新生成食物
                if(snakeX[0] == foodX && snakeY[0] == foodY) {
                    snakeLen++;
                    foodX = 25 + 25 * random.nextInt(34);
                    foodY = 75 + 25 * random.nextInt(24);
                }

                //蛇头与身体坐标重复游戏结束
                for (int i = 1; i < snakeLen; i++) {
                    if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                        isFail = true;
                    }
                }
            }

            repaint();
        }


        timer.start();
    }

    //按下
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if(isFail) {
                isFail = false;
                init(); //游戏重新初始化
            }else {
                startStatus = !startStatus;
            }
            repaint();//刷新
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            direction = "left";
        }else if(keyCode == KeyEvent.VK_RIGHT){
            direction = "right";
        }else if(keyCode == KeyEvent.VK_UP){
            direction = "up";
        }else if(keyCode == KeyEvent.VK_DOWN){
            direction = "down";
        }

    }





    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下起来
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
