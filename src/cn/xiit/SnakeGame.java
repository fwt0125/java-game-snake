package cn.xiit;

import javax.swing.*;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("贪吃蛇小游戏-xiit.cn");
        jFrame.setBounds(300,100, 900,720);
        jFrame.setResizable(false); //大小不可改变
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //可点按钮关闭

        jFrame.add(new SnakePanel());

        jFrame.setVisible(true);
    }

}
