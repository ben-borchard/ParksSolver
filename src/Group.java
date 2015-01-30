import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by ben on 1/11/15.
 */
public class Group {

    int id;
    ArrayList<Tile> tiles;

    Stack<Integer[]> possibilities;

    public Group(int id){
        this.id = id;
        tiles = new ArrayList();
        possibilities = new Stack();
        initTrial();

    }

    public void addTile(Tile tile){
        tiles.add(tile);
    }

    public int numTiles(){
        return this.tiles.size();
    }

    public void initTrial(){

        clearTrees();

        possibilities = new Stack();

        for (int i=0;i<tiles.size();i++){
            for (int j=i+1;j<tiles.size();j++){
                if (!tiles.get(i).borders(tiles.get(j))) {
                    Integer[] possibility = {i, j};
                    possibilities.push(possibility);
                }
            }
        }
    }

    public boolean nextPossibility(Tile[][] grid){
        clearTrees();
        if (possibilities.isEmpty()) {return false;}
        Integer[] possibility = possibilities.pop();

        if (id == 1) {
            System.out.println("Possibilities left: "+possibilities.size());
        }

        tiles.get(possibility[0]).putTree();
        tiles.get(possibility[1]).putTree();

        if (
                tiles.get(possibility[0]).bordersTree(grid)   ||
                tiles.get(possibility[1]).bordersTree(grid)   ||
                tiles.get(possibility[0]).thirdInColumn(grid) ||
                tiles.get(possibility[0]).thirdInRow(grid)    ||
                tiles.get(possibility[1]).thirdInColumn(grid) ||
                tiles.get(possibility[1]).thirdInRow(grid)       ){

            return nextPossibility(grid);
        }

        return true;

    }

    private void clearTrees(){
        for (Tile tile : tiles){
            tile.clearTree();
        }
    }

    public int numTrees(){
        int numTrees = 0;

        for (Tile tile : tiles){
            if (tile.hasTree()){
                numTrees++;
            }
        }

        return numTrees;
    }

    public boolean twoTreesPossible(Tile[][] grid){
        ArrayList<Tile> tilesLeft = new ArrayList();

        int topRow = 0;
        int topCol = 0;

        int botCol = Integer.MAX_VALUE;
        int botRow = Integer.MAX_VALUE;

        int i=0;
        for (Tile tile : tiles){
            if (!tile.bordersTree(grid)) {
                topRow = tile.getRow() > topRow ? tile.getRow() : topRow;
                topCol = tile.getCol() > topCol ? tile.getCol() : topCol;

                botRow = tile.getRow() < botRow ? tile.getRow() : botRow;
                botCol = tile.getCol() < botCol ? tile.getCol() : botCol;

                i++;
            }
        }

        if ((i == 0 || i == 1) ||
                (Math.abs(topRow-botRow) < 2 && Math.abs(topCol-botCol) < 2)){
            return false;
        }

        return true;

    }

}
