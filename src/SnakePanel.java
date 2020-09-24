import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.function.Predicate;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {
    ImageIcon up = new ImageIcon("up.png");
    ImageIcon down = new ImageIcon("down.png");
    ImageIcon left = new ImageIcon("left.png");
    ImageIcon right = new ImageIcon("right.png");
    ImageIcon food = new ImageIcon("food.png");
    ImageIcon title = new ImageIcon("title.jpg");
    ImageIcon body = new ImageIcon("body.png");

    int[] snakex = new int[500];
    int[] snakey = new int[500];
    int len = 3;
    int score = 0;
    String direction = "R";
    boolean isFailed = false;
    boolean isStarted = false;
    Random rand = new Random();
    int foodx = rand.nextInt(19)*25 + 10;
    int foody = rand.nextInt(11)*25 + 75;
    Timer timer = new Timer(160,this);

    //事件响应
    public SnakePanel(){
        this.setFocusable(true);
        this.addKeyListener(this);
        init();
        timer.start();
    }
    public  void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.clearRect(0,0,getWidth(),getHeight());
        g.setColor(Color.BLACK);
        this.setBackground(Color.BLACK);
        title.paintIcon(this,g,-150,10);
        g.fillRect(10,75,475,350);

        if (direction.equals("R")){
            right.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (direction.equals("L")){
            left.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (direction.equals("U")){
            up.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (direction.equals("D")){
            down.paintIcon(this,g,snakex[0],snakey[0]);
        }
        for (int i = 1;i<len;i++){
            body.paintIcon(this,g,snakex[i],snakey[i]);
        }
        if (!isStarted){
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体",Font.BOLD,30));
            g.drawString("游戏暂停,按空格键开始游戏!",60,240);
        }
        food.paintIcon(this,g,foodx,foody);
        if (isFailed){
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体",Font.BOLD,30));
            g.drawString("游戏结束,按空格键开始游戏",60,200);
        }
    }
    public void init(){
        isStarted = false;
        isFailed = false;
        score = 0;
        len = 3;
        snakex[0] = 60; snakex[1] = 35; snakex[2] = 10;
        snakey[0] = 75; snakey[1] = 75; snakey[2] = 75;
        direction = "R";
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFailed) {
                init();
            } else {
                isStarted = !isStarted;
            }
        } else {
            if (!isFailed && isStarted) {
                if (keyCode == KeyEvent.VK_RIGHT && direction != "L") {
                    direction = "R";
                } else if (keyCode == KeyEvent.VK_LEFT && direction != "R") {
                    direction = "L";
                } else if (keyCode == KeyEvent.VK_UP && direction != "D") {
                    direction = "U";
                } else if (keyCode == KeyEvent.VK_DOWN && direction != "U") {
                    direction = "D";
                }
            }
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (isStarted && !isFailed){

            for (int i = len; i>0;i--){
                snakex[i] = snakex[i-1];
                snakey[i] = snakey[i-1];
            }
            int jl = 25;
            if (direction.equals("R")) {
                snakex[0] = snakex[0] + jl;
                if(snakex[0] >= 485){
                    snakex[0] = 10;
                }
            }else if (direction.equals("L")) {
                snakex[0] = snakex[0] - jl;
                if (snakex[0] < 10){
                    snakex[0] = 460;
                }
            }else if (direction.equals("U")) {
                snakey[0] = snakey[0] - jl;
                if (snakey[0] < 75){
                    snakey[0] = 400;
                }
            }else if (direction.equals("D")) {
                snakey[0] = snakey[0] + jl;
                if (snakey[0] >= 425){
                    snakey[0] = 75;
                }
            }

            for (int i = 1;len>i;i++){
                if (snakex[0] == snakex[i] && snakey[0] == snakey[i]){
                    isFailed = true;
                }
            }
            if (snakex[0] == foodx && snakey[0] == foody){
                len++;
                score++;
                boolean overlap = false;
                do {
                    foodx = rand.nextInt(19)*25 + 10;
                    foody = rand.nextInt(11)*25 + 75;
//                    foodx = 5*25+10;
//                    foody = 75;

                    for (int i = 0; len > i; i++) {
                        if (foodx == snakex[i] && foody == snakey[i]) {
                            overlap = true;
                        }
                    }
                }while (overlap);
            }
            repaint();
        }
    }
}
