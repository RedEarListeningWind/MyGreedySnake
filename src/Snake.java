
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Snake {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container conn = frame.getContentPane();
        conn.setLayout(null);

        SnakePanel panel = new SnakePanel();
        panel.setBounds(0,0,510,430);
        JButton btnSave = new JButton("保存游戏");
        btnSave.setBounds(200,430,100,30);
        JButton btnLoad = new JButton("加载游戏");
        btnLoad.setBounds(300,430,100,30);

        conn.add(panel);
        conn.add(btnLoad);
        conn.add(btnSave);

        frame.setBounds(10,10,510,500);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //frame.add(panel);
        frame.setTitle("贪吃蛇");
        frame.setVisible(true);

        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                SnakeInfo si = new SnakeInfo();
                si.setDirection(panel.direction);
                si.setFoodx(panel.foodx);
                si.setFoody(panel.foody);
                si.setLen(panel.len);
                si.setScore(panel.score);
                si.setSnakex(panel.snakex);
                si.setSnakey(panel.snakey);
                Gson gson = new Gson();
                String snakeStr = gson.toJson(si);
                try (Writer writer = new FileWriter("a.txt")){
                    writer.write(snakeStr);
                    writer.close();
                    panel.requestFocus();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    InputStream is = new FileInputStream("a.txt");
                    int ia = is.available();
                    byte[] bytes = new byte[ia];
                    is.read(bytes);
                    String snakeStr = new String(bytes);
                    Gson gson = new Gson();
                    SnakeInfo si = gson.fromJson(snakeStr, SnakeInfo.class);

                    panel.direction = si.getDirection();
                    panel.foodx = si.getFoodx();
                    panel.foody = si.getFoody();
                    panel.len = si.getLen();
                    panel.score = si.getScore();
                    panel.snakex = si.getSnakex();
                    panel.snakey = si.getSnakey();
                    panel.direction = si.getDirection();
                    panel.isStarted = false;
                    panel.isFailed = false;
                    is.close();
                    panel.repaint();
                    panel.setFocusable(true);
                    //panel.addKeyListener(panel);
                    panel.requestFocus();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
