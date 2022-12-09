import com.cs.engine.cell.Color;
import com.cs.engine.cell.Game;

public class ScoreLine {
    public void drawScoreLine(int score, Game game){
        int y = settings.HEIGHT/2 - (settings.WIN_NUMBER/2);
        for (int i = 0; i < settings.WIN_NUMBER; i++) {
            if (i<settings.WIN_NUMBER-score) game.setCellColor(settings.WIDTH-5,y+i, Color.GRAY);
            else if (i==settings.WIN_NUMBER-score) game.setCellColor(settings.WIDTH-5,y+i, Color.BLUE);
            else game.setCellColor(settings.WIDTH-5,y+i, Color.WHITE);
        }
    }
}
