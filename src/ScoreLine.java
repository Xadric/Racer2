import com.cs.engine.cell.Color;
import com.cs.engine.cell.Game;

public class ScoreLine {
    public void drawScoreLine(int score, Game game){
        for (int i = 0; i < settings.WIN_NUMBER; i++) {
            if (i<settings.WIN_NUMBER-score) game.setCellColor(settings.WIDTH-5,10+i, Color.GRAY);
            else game.setCellColor(settings.WIDTH-5,10+i, Color.WHITE);
        }
    }
}
