public class Skins {
    private static final int[][] PLAYER_SKIN = {
            {1, 1, 1, 0, 1, 1, 1, 0,1},
            {0, 1, 0, 0, 0, 1, 0, 0,1},
            {7, 7, 7, 7, 7, 7, 7, 7,1},
            {7, 7, 7, 7, 7, 7, 7, 7,1},
            {0, 1, 0, 0, 0, 1, 0, 0,1},
            {1, 1, 1, 0, 1, 1, 1, 0,1}
    };
    private static final int[][] BUSH_SKIN={
            {0, 0, 6, 0, 0},
            {0, 6, 6, 6, 0},
            {6, 6, 6, 6, 6},
            {6, 6, 6, 6, 6},
            {0, 6, 6, 6, 0},
            {0, 0, 6, 0, 0}
    };
    private static final int[][] TRUCK_SKIN = new int[][]{

            {0, 1, 1, 1, 0,0, 1, 1, 1, 0,0,0,0},
            {4, 4, 4, 4, 4,4, 4, 4, 4, 4,5,5,5},
            {1, 4, 4, 4, 4,4, 4, 4, 4, 4,5,5,5},
            {1, 4, 4, 4, 4,4, 4, 4, 4, 4,5,5,5},
            {4, 4, 4, 4, 4,4, 4, 4, 4, 4,5,5,5},
            {0, 1, 1, 1, 0,0, 1, 1, 1, 0,0,0,0}
    };
    private static final int[][] DEFAULT = {{1}, {1}};

    public static int[][] getSkin(GameObjectType type) {
        switch (type){
            case PLAYER :return PLAYER_SKIN;
            case BUSH:return BUSH_SKIN;
            case TRUCK:return TRUCK_SKIN;
            default:return DEFAULT;
        }
    }
}
