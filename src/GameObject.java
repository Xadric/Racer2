import com.cs.engine.cell.Color;
import com.cs.engine.cell.Game;

public class GameObject {
    public int x;
    public int y;
    public int width;
    public int height;
    public GameObjectType type;
    public int[][]skin;
    public int speed;
    public boolean isAlive;
    public int crazyCarDX;


    public GameObject(int x, int y, GameObjectType type) {
        this.x = x;
        this.y = y;
        this.skin=Skins.getSkin(type);
        this.width = skin.length;
        this.height = skin[0].length;
        this.type = type;
        this.speed=getSpeed(type);
        this.isAlive=true;
        if (type==GameObjectType.CRAZY_DRIVER){
            crazyCarDX=0;
        }
    }

    private int getSpeed(GameObjectType type) {
        if (type==GameObjectType.BUSH)return 1;
        if (type==GameObjectType.TRUCK)return 2;
        if (type==GameObjectType.CRAZY_DRIVER)return 1;
        return 0;
    }

    public void draw(Game game){
        if (isAlive) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    game.setCellColor(x + i, y + j, Color.values()[skin[i][j]]);
                }
            }
        }else {
            this.x=-100;
        }
    }

    public void move() {
        y += speed;
    }
}
