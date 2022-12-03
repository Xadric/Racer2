import com.cs.engine.cell.Color;
import com.cs.engine.cell.Game;
import com.cs.engine.cell.Key;

import java.util.ArrayList;

public class Main extends Game {
    public static final int WIDTH=settings.WIDTH;
    public static final int HEIGHT=settings.HEIGHT;
    public static final int CENTER=WIDTH/2;
    public static final int RIGHT_WALL=CENTER+(CENTER/2);
    public static final int LEFT_WALL=CENTER-(CENTER/2);
    private GameObject player;
    private ArrayList<GameObject> objects = new ArrayList<>();
    private int speed = settings.BASIC_SPEED;
    private int score;
    private boolean canScoreUp=true;

    @Override
    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        showGrid(false);
        createGame();
    }

    private void createGame() {
        score=0;
        setScore(score);
        setTurnTimer(speed);
        createPlayer();
        objects = new ArrayList<>();
        objects.add(createNewObject());
        objects.add(createNewObject());
        objects.add(createNewObject());
        drawScrene();
    }

    private void createPlayer() {
        player=new GameObject(0,0,GameObjectType.PLAYER);
        player.y=HEIGHT-player.height;
        player.x=CENTER;
    }

    private void drawScrene() {
        drawField();
        for (GameObject object : objects) {
            object.draw(this);
        }
        player.draw(this);
        new ScoreLine().drawScoreLine(score,this);
    }

    private void drawField() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i == CENTER) {
                    setCellColor(i, j, Color.WHITE);
                } else if (i == LEFT_WALL) {
                    setCellColor(i, j, Color.WHITE);
                } else if (i == RIGHT_WALL) {
                    setCellColor(i, j, Color.WHITE);
                } else if (i > LEFT_WALL && i < RIGHT_WALL) {
                    setCellColor(i,j,Color.SLATEGRAY);
                } else setCellColor(i, j, Color.GREEN);
            }
        }
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.LEFT) {
            if (player.x>LEFT_WALL+1) player.x-=2;
        }
        if (key==Key.RIGHT){
            if (player.x+ player.width<RIGHT_WALL)player.x+=2;
        }
        if (key==Key.SPACE){
            createGame();
        }
        drawScrene();
    }

    @Override
    public void onTurn(int step) {
        moveAll();
        tryToMakeNewObject();
        drawScrene();
        cheakWin();
        cheakColision();
    }

    private void cheakColision() {
        for (GameObject object : objects) {
            if (object.x + object.width >= player.x &&object.x <= player.x+player.width && object.y + object.height - 1 >= player.y) {
                showMessageDialog(Color.BLUE,"YOU Fail :(",Color.RED,75);
                createGame();
            }
        }
    }

    private void cheakWin() {
        if (score>settings.WIN_NUMBER){
            showMessageDialog(Color.BLUE,"YOU WIN!!!",Color.RED,75);
            createGame();
        }
    }

    private void tryToMakeNewObject() {
        for (GameObject object : objects) {
            if (!object.isAlive){
                if (getRandomNumber(10)==0){
                    objects.remove(object);
                    objects.add(createNewObject());
                }
            }
        }
        if (score%15==0 &&score!=0){
            if (canScoreUp) {
                objects.add(createNewObject());
                canScoreUp=false;
            }
        }
    }

    private GameObject createNewObject() {
        GameObject o = new GameObject(0,0,GameObjectType.values()[getRandomNumber(settings.GAME_OBJECT_TYPES-2)]);
        if (o.type==GameObjectType.CRAZY_DRIVER){
            o = new GameObject(0,0,GameObjectType.values()[getRandomNumber(settings.GAME_OBJECT_TYPES-2)]);
        }
        o.y=-o.height;
        o.x=getRandomNumber(LEFT_WALL,RIGHT_WALL-o.width);
        return o;
    }

    private void moveAll() {
        int i=0;
        for (GameObject object : objects) {
            object.move();
            if (object.y>HEIGHT){
                objects.get(i).isAlive=false;
                score++;
                setScore(score);
                canScoreUp=true;
                object.y=-1000000000;
            }
            i++;
        }
        moveCrazyDriver();
    }

    private void moveCrazyDriver() {
        for (GameObject object : objects) {
            if(object.type==GameObjectType.CRAZY_DRIVER){
                object.x+= object.crazyCarDX;
                if (object.y>0 && object.crazyCarDX ==0){
                    object.crazyCarDX =1;
                }
                if (object.x<LEFT_WALL+1 || object.x+object.width>=RIGHT_WALL){
                    object.crazyCarDX=-object.crazyCarDX;
                }
                for (GameObject object2 : objects) {
                    if (object!=object2)
                    if ((object.x+object.width==object2.x ||object.x==object2.x+object2.width)&& (object.y>=object2.y &&object.y<=object2.y+object2.height ) ){
                        object.crazyCarDX=-object.crazyCarDX;
                    }
                }
            }
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x>=0 && x<WIDTH && y>=0 && y<HEIGHT)
        super.setCellColor(x, y, color);
    }
}