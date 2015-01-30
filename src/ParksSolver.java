/**
 * Created by ben on 1/11/15.
 */
public class ParksSolver {


    Park park;

    public ParksSolver(String[][] park){
        this.park = new Park(park);
    }

    public ParksSolver(){

    }

    private String solve(){
        return park.solve();
    }

    private String solve(String park){

        String[] rowArray = park.split("\n");
        String[][] parkArray = new String[rowArray.length][rowArray[0].split(" ").length];
        int i=0;
        for(String row : rowArray){
            parkArray[i] = row.split(" ");
            i++;
        }
        this.park = new Park(parkArray);
        return solve();
    }



    public static void main(String args[]){
        String park =
                "1 1 2 2 3 3 3 3 3 3 3 4" + '\n' +
                "1 2 2 2 3 3 3 3 3 3 3 4" + '\n' +
                "1 2 2 2 2 3 3 3 3 3 3 4" + '\n' +
                "1 1 1 2 2 3 5 3 5 5 5 4" + '\n' +
                "1 1 1 2 2 2 5 5 5 4 4 4" + '\n' +
                "7 7 1 1 1 1 1 5 4 4 6 6" + '\n' +
                "7 7 7 8 8 8 1 4 4 6 6 6" + '\n' +
                "7 7 7 8 8 8 8 4 6 6 6 6" + '\n' +
                "9 7 7 7 7 10 8 11 11 11 6 11" + '\n' +
                "9 9 12 12 12 10 8 11 8 11 11 11" + '\n' +
                "9 9 12 12 12 10 8 8 8 11 11 10" + '\n' +
                "9 9 12 12 10 10 10 10 10 10 10 10";

//        String park =
//                "1 1 2 2 3 3 3 3 3 3 3 3" + '\n' +
//                "1 1 2 2 3 3 3 3 3 3 3 5" + '\n' +
//                "1 1 2 2 2 4 3 3 3 5 5 5" + '\n' +
//                "1 1 2 2 2 4 3 3 5 5 5 5" + '\n' +
//                "1 1 2 2 2 4 4 5 5 5 5 5" + '\n' +
//                "1 6 6 7 7 7 7 7 5 8 5 5" + '\n' +
//                "6 6 6 7 7 7 7 8 8 8 8 8" + '\n' +
//                "6 6 6 6 7 7 7 8 8 8 8 9" + '\n' +
//                "6 6 6 6 10 10 8 8 11 11 11 9" + '\n' +
//                "6 6 6 10 10 10 11 11 11 11 9 9" + '\n' +
//                "6 6 6 6 10 10 11 11 11 11 9 9" + '\n' +
//                "6 6 6 10 10 10 10 12 12 12 9 9";

//        String park =
//                "1 1 1 1 1 1 1 1 1" + '\n' +
//                "2 2 1 1 3 1 3 3 4" + '\n' +
//                "2 2 2 3 3 3 3 4 4" + '\n' +
//                "2 2 2 5 5 6 4 4 4" + '\n' +
//                "2 2 2 2 5 6 6 6 4" + '\n' +
//                "2 8 5 5 5 7 6 6 4" + '\n' +
//                "8 8 8 7 7 7 6 6 4" + '\n' +
//                "8 8 9 9 9 7 7 6 6" + '\n' +
//                "8 8 9 9 9 9 7 6 6";


        final long startTime = System.currentTimeMillis();

        ParksSolver ps = new ParksSolver();

        System.out.println(ps.solve(park));

        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime) );
        //System.out.println(test());

    }

    public static String test(){
        int i = 0;
        while(true){
            if (i == 10){
                return "dicks";
            }
            i++;
        }
    }

}
