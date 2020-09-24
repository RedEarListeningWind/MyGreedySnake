

public class SnakeInfo {
    private int len;
    private int score;
    private String direction;;
    private int snakex[];
    private int snakey[];
    private int foodx;
    private int foody;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int[] getSnakex() {
        return snakex;
    }

    public void setSnakex(int[] snakex) {
        this.snakex = snakex;
    }

    public int[] getSnakey() {
        return snakey;
    }

    public void setSnakey(int[] snakey) {
        this.snakey = snakey;
    }

    public int getFoodx() {
        return foodx;
    }

    public void setFoodx(int foodx) {
        this.foodx = foodx;
    }

    public int getFoody() {
        return foody;
    }

    public void setFoody(int foody) {
        this.foody = foody;
    }
}
