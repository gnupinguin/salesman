import javax.swing.border.Border;

/**
 * Created by gnupinguin on 24.11.15.
 */
public class BorderSquareMatrix implements Cloneable{
    private Integer[][] matrix;

    public BorderSquareMatrix(Integer[][] matrix){
        this.matrix = new Integer[matrix.length+1][matrix.length+1];
        for (int i = 0; i < matrix.length; i++){
            System.arraycopy(matrix[i], 0, this.matrix[i+1], 1, matrix[i].length);
            this.matrix[0][i+1] = this.matrix[i+1][0] = i+1;
        }
        matrix[0][0] = 0;
    }
    public void set(int i, int j, Integer val){
        matrix[i+1][j+1] = val;
    }
    public Integer get (int i, int j){
        return matrix[i+1][j+1];
    }
    public void removeRowAndColumn(int rowPosition, int columnPosition){
        for (int i = rowPosition; i < matrix.length-1; i++  )
            matrix[i] = matrix[i+1];

        //delete column
        Integer[][] ans = new Integer[matrix.length-1][matrix.length-1];
        for (int i = 0; i < ans.length; i++){
            for (int j = 0; j < columnPosition; j++){
                ans[i][j] = matrix[i][j];
            }
            for (int j = columnPosition; j < ans.length ; j++){
                ans[i][j] = matrix[i][j+1];
            }
        }
        matrix = ans;
    }
    public int getDimension(){//without border
        return matrix.length-1;
    }
    public int getVerticalBorderValue(int i){
        return this.matrix[i+1][0];
    }
    public int getHorizintalBorderValue(int i){
        return this.matrix[0][i+1];
    }

    public void print( String message){
        System.out.println(message +"\n--------------");

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        System.out.println("--------------");
    }

    @Override
    protected BorderSquareMatrix clone() {
        Integer[][] ans = new Integer[getDimension()][getDimension()];
        for (int i = 0; i < getDimension(); i++)
            System.arraycopy(matrix[i + 1], 1, ans[i], 0, getDimension());
        return new BorderSquareMatrix(ans);
    }
}
