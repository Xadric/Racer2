import com.cs.engine.cell.Color;
import com.cs.engine.cell.Game;

public class FinalLine {
    public int x;
    public int y;
    public int[][] skin;
    private final int width = (Main.RIGHT_WALL - Main.LEFT_WALL)*2;
    private final int height = 6;

    public FinalLine() {
        this.x = Main.LEFT_WALL;
        this.y = -6;
    }

    public void draw(Game game) {
        for (int j = 0; j < height/2; j+=2) {
            for (int i = 0; i < width / 2; i += 2) {
                for (int k = 0; k < 2; k++) {
                    game.setCellColor(x + i+k, y + j, Color.values()[k+1]);
                }

            }
            for (int i = 0; i < width / 2; i += 2) {
                for (int k = 2; k >0; k--) {
                    game.setCellColor(x + i+k, y + j+1, Color.values()[k]);
                }

            }
        }
    }

    public void move() {
        y++;
    }
}
