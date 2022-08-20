package cn.xiit;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SnakeData {
   public static URL headerUrl = SnakeData.class.getResource("/statics/img.png");
   public static ImageIcon header = new ImageIcon(headerUrl);

   public static URL upUrl = SnakeData.class.getResource("/statics/up.png");
   public static URL downUrl = SnakeData.class.getResource("/statics/down.png");
   public static URL leftUrl = SnakeData.class.getResource("/statics/left.png");
   public static URL rightUrl = SnakeData.class.getResource("/statics/right.png");
   public static URL bodyUrl = SnakeData.class.getResource("/statics/body.png");
   public static URL foodUrl = SnakeData.class.getResource("/statics/food.png");


   public static ImageIcon up = new ImageIcon(upUrl);
   public static ImageIcon down = new ImageIcon(downUrl);
   public static ImageIcon left = new ImageIcon(leftUrl);
   public static ImageIcon right = new ImageIcon(rightUrl);
   public static ImageIcon body = new ImageIcon(bodyUrl);
   public static ImageIcon food = new ImageIcon(foodUrl);



}
