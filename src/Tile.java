import java.util.ArrayList;

/**
 * Created by ben on 1/11/15.
 */
public class Tile {

    private int group;
    private int row;
    private int col;
    private boolean tree;



    public Tile(int group, int row, int col){
        this.group = group;
        this.row = row;
        this.col = col;
        tree = false;

    }

    public boolean borders(Tile tile){
        return Math.abs(this.row - tile.row) <= 1 && Math.abs(this.col - tile.col) <= 1;
    }

    public void putTree(){
        tree = true;
    }

    public void clearTree(){
        tree = false;
    }

    public boolean hasTree(){
        return tree;
    }

    public int getRow(){return row;}

    public int getCol(){return col;}

    public boolean bordersTree(Tile[][] grid){
        boolean treeFound = false;

        int rowStart  = Math.max( row - 1, 0   );
        int rowFinish = Math.min( row + 1, grid.length - 1 );
        int colStart  = Math.max( col - 1, 0   );
        int colFinish = Math.min( col + 1, grid[0].length - 1 );

        for ( int curRow = rowStart; curRow <= rowFinish; curRow++ ) {
            for ( int curCol = colStart; curCol <= colFinish; curCol++ ) {
                if (curCol != col || curRow != row) {
                    treeFound |= grid[curRow][curCol].hasTree();
                }
            }
        }

        return treeFound;
    }

    public boolean thirdInColumn(Tile[][] grid){
        int treesInColumn = 0;
        for (int i=0;i<grid.length;i++){
           if (grid[i][col].hasTree()){
               treesInColumn++;
           }
        }
        return treesInColumn > 2;
    }

    public boolean thirdInRow(Tile[][] grid){
        int treesInRow = 0;
        for (int i=0;i<grid[0].length;i++){
            if (grid[row][i].hasTree()){
                treesInRow++;
            }
        }
        return treesInRow > 2;
    }

}
