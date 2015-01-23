import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by ben on 1/11/15.
 */
public class Park {

    Tile[][] tiles;
    HashMap<Integer, Group> groups;
    ArrayList<Group> rows;
    ArrayList<Group> cols;

    String curstate = "";

    public Park(String[][] park){

        ArrayList<Integer> usedIds = new ArrayList();

        tiles = new Tile[park.length][park[0].length];

        groups = new HashMap();
        rows = new ArrayList<Group>();
        for (int i=0;i<park.length;i++){
            Group row = new Group(-1);
            rows.add(row);
        }

        cols = new ArrayList<Group>();
        for (int i=0;i<park[0].length;i++){
            Group col = new Group(-2);
            cols.add(col);
        }

        // Create tile grid
        for (int i=0;i<park.length;i++){
            for (int j=0;j<park[0].length;j++){


                Tile tile = new Tile(-3, i, j);

                // Skip black spaces
                if (!park[i][j].equals("*")){
                    int groupId = Integer.parseInt(park[i][j]);


                    tile = new Tile(groupId, i, j);
                    if (!usedIds.contains(groupId)) {
                        groups.put(groupId, new Group(groupId));
                        usedIds.add(groupId);
                    }

                    groups.get(groupId).addTile(tile);
                }



                rows.get(i).addTile(tile);
                cols.get(j).addTile(tile);
                tiles[i][j] = tile;
            }
        }
    }

    public String solve(){

        Group[] groupArray = new Group[groups.values().size()];

        int i=0;
        for(Group group : groups.values()) {
            group.initTrial();
            groupArray[i] = group;
            i++;
        }

        if (solved()){
            return parkString();
        }

        return runAllPossible(groupArray, 0);

    }

    private String parkString(){

        String parkString = " ";

        for (Tile[] rowTiles : tiles){
            for (Tile tile : rowTiles) {
                if (tile.hasTree()){
                    parkString += "1  ";
                }
                else{
                    parkString += "O  ";
                }
            }
            parkString += "\n ";
        }

        return parkString;
    }

    private boolean solved(){

        for (Tile[] tileRow : tiles){
            for (Tile tile : tileRow){
                if (tile.hasTree() && tile.bordersTree(tiles)){
                    return false;
                }
            }
        }

        for (Group row : rows){
            if (row.numTrees() != 2){
                return false;
            }
        }

        for (Group col : cols){
            if (col.numTrees() != 2){
                return false;
            }
        }

        return true;
    }

    private String runAllPossible(Group[] groupArray, int depth){

        while (true) {

            if (depth == groupArray.length - 1) {
                while (groupArray[depth].nextPossibility(tiles)) {
                    if (solved()) {
                        return parkString();
                    }
                }
                groupArray[depth].initTrial();
                depth--;
            } else if (depth == -1) {
                return "unsolvable";
            } else {
                if (groupArray[depth].nextPossibility(tiles)) {
                    if (possibilitiesRemain(groupArray, depth)){
                        depth++;
                        System.out.println("more possibs");
                    }
                    else{
                        System.out.println("staying");
                    }
                }
                else {
                    groupArray[depth].initTrial();
                    depth--;
                    System.out.println("dead end");
                }
            }
            if (curstate.equals(parkString())){
                System.out.println("no change");
            }
            curstate = parkString();
            System.out.println(parkString());
            int k = 0;
        }

    }

    public boolean possibilitiesRemain(Group[] group, int depth) {
        boolean morePossibilities = true;
        for (int i=depth+1; i<group.length; i++){
            morePossibilities &= group[i].twoTreesPossible(tiles);
        }

        return morePossibilities;
    }


}
