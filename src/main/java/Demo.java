

/**
 * Created by gnupinguin on 24.11.15.
 */
public class Demo {

    public static void main(String[] args) {
        Integer INF = null;
        /*Double[][] matrix = {
                {INF, 5d, 11d, 9d},
                {10d, INF, 8d, 7d},
                {7d, 14d, INF, 8d},
                {12d, 6d, 15d, INF}};*/
        Integer[][] matrix = {
                {INF, 68, 73, 24, 70, 9},
                {58, INF, 16, 44, 11, 92},
                {63, 9, INF, 86, 13, 18},
                {17, 34, 76, INF, 52, 70},
                {60, 18, 3, 45, INF, 58},
                {16, 82, 11, 60, 48, INF}
        };
        Salesman1 salesman = new Salesman1(matrix);
        salesman.getWay();
        //BorderSquareMatrix a = new BorderSquareMatrix(matrix);
        //a.removeRowAndColumn(2, 2);
        //a.print(null);
    }

}
