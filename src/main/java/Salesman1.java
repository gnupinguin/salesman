/**
 * Created by gnupinguin on 24.11.15.
 */
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;

/**
 * Created by gnupinguin on 24.11.15.
 */
public class Salesman1 {
    public static final Integer INF = null;
    private BorderSquareMatrix matrix = null;
    public Salesman1(Integer[][] matrix){
        if (!isSalesmanMatrix(matrix))
            throw new RuntimeException("Error: No salesman's matrix.");

        this.matrix = new BorderSquareMatrix(matrix);
    }

    public void getWay(){
        while (matrix.getDimension() != 1) {
            if (matrix.getDimension() == 2)
                System.out.println();
            matrix.print("new iteration");
            rowsReduction(minRowsElts());
            columnsReduction(minColumnsElts(matrix));

           matrix.print("after reduction");

            BorderSquareMatrix marksMatrix = matrix.clone();
            ArrayList<Pair<Integer, Integer>> zeroCoordinates = new ArrayList<Pair<Integer, Integer>>();

            //find positions with zero-values
            for (int i = 0; i < matrix.getDimension(); i++) {
                for (int j = 0; j < matrix.getDimension(); j++) {
                    if (matrix.get(i, j) != null && matrix.get(i, j) == 0) {
                        zeroCoordinates.add(new Pair<Integer, Integer>(i, j));
                    }
                }
            }
            //do marks
            for (Pair<Integer, Integer> p : zeroCoordinates) {
                Double aa;
                marksMatrix.set(p.getFirst(), p.getSecond(),
                        add(minColumnElt(matrix, p.getSecond(), p.getFirst()), minRowElt(matrix, p.getFirst(), p.getSecond())));
            }
            marksMatrix.print("final marks!!!!!");
            //find max mark
            Integer maxMark = -1;
            Pair<Integer, Integer> coordinatesMaxMarks = null;
            for (Pair<Integer, Integer> p : zeroCoordinates) {
                if (greater(marksMatrix.get(p.getFirst(), p.getSecond()), maxMark)) {
                    maxMark = marksMatrix.get(p.getFirst(), p.getSecond());
                    coordinatesMaxMarks = p;
                }
            }

            matrix.set(coordinatesMaxMarks.getFirst(), coordinatesMaxMarks.getSecond(), INF);//!!!!!!!!!!!!1
            matrix.set(coordinatesMaxMarks.getSecond(), coordinatesMaxMarks.getFirst(), INF);

            //find place where is two INF-values
            Pair<Integer, Integer> pos = deleteRowAndColumn(coordinatesMaxMarks.getFirst(), coordinatesMaxMarks.getSecond());


            System.out.println(pos.getFirst() + " " + pos.getSecond());
        }
    }

    public static boolean isSalesmanMatrix(Integer[][] m){
        if (m.length != m[0].length)
            return false;
        for (int i = 0; i < m.length; i++){
            if (m[i][i] != INF)
                return false;
        }
        return true;
    }

    //return vector with min elements every row
    private Integer[] minRowsElts(){
        Integer[] ans = new Integer[matrix.getDimension()];
        for(int i = 0; i < matrix.getDimension(); i++) {
            ans[i] = minRowElt(matrix, i);
        }

        return ans;
    }

    private Integer[] minColumnsElts(BorderSquareMatrix m){
        Integer[] ans = new Integer[m.getDimension()];
        for(int j = 0; j < m.getDimension(); j++) {
            ans[j] = minColumnElt(m, j);
        }

        return ans;
    }

    private Integer minColumnElt(BorderSquareMatrix m, int pos, int excludeRowIndex){
        Integer min = INF;
        int i;
        for (i = 0; min == INF && i< m.getDimension(); i++) {
            if (i == excludeRowIndex) continue;
            if (m.get(i,pos) != INF)
                min = m.get(i,pos);
        }

        for (; i < m.getDimension(); i++) {
            if (i == excludeRowIndex) continue;
            if (m.get(i,pos) != INF && m.get(i,pos) < min ) {
                min = m.get(i,pos);
            }
        }
        return min;
    }

    private Integer minColumnElt(BorderSquareMatrix m, int pos){
        return minColumnElt(m, pos, -1);
    }

    private Integer minRowElt(BorderSquareMatrix m, int pos, int excludeColumnIndex){

        Integer min = INF;
        int i;
        for (i = 0; min == INF && i< m.getDimension(); i++) {
            if (i == excludeColumnIndex) continue;
            min = m.get(pos,i);
        }

        for (; i < m.getDimension(); i++) {
            if (i == excludeColumnIndex)
                continue;
            if (m.get(pos,i) != INF && m.get(pos,i) < min) {
                min = m.get(pos,i);
            }
        }
        return min;
    }

    private Integer minRowElt(BorderSquareMatrix m, int pos){
        return minRowElt(m, pos, -1);
    }

    private void rowsReduction( Integer[] column){
        for (int i = 0; i < matrix.getDimension(); i++){
            for(int j = 0; j < matrix.getDimension(); j++)
                if (matrix.get(i,j) != INF && column[i] != INF)
                    matrix.set(i,j, matrix.get(i, j) - column[i]);
        }
    }

    private void columnsReduction(Integer[] row){
        for (int i = 0; i < matrix.getDimension(); i++){
            for(int j = 0; j < matrix.getDimension(); j++)
                if (matrix.get(j,i) != INF && row[i] != INF)
                    matrix.set(j,i, matrix.get(j, i) - row[i]);
        }
    }

    private Pair<Integer, Integer> deleteRowAndColumn(int rowPosition, int columnPosition){
        //delete row
        matrix.removeRowAndColumn(rowPosition, columnPosition);
        matrix.print("end variant");
        Pair<Integer, Integer> segment = new Pair<Integer, Integer>(
        matrix.getVerticalBorderValue(rowPosition), matrix.getHorizintalBorderValue(columnPosition));

        return segment;
    }

    public static void printMatrix(Double[][] m, String message){
        System.out.println(message + "\n--------------");
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[i].length; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
        System.out.println("--------------");
    }

    public static Double[][] copy(Double[][] m){
        Double[][] ans = new Double[m.length][m.length];
        for (int i = 0; i < m.length; i++)
            ans[i] = m[i].clone();
        return ans;
    }

    public Integer add(Integer a, Integer b){
        if (a == INF || b == INF)
            return INF;
        return a+b;
    }
    public boolean greater(Integer a, Integer b) {
        if (a == null)
            return true;
        if (b == null)
            return false;
        return a > b;
    }
}

