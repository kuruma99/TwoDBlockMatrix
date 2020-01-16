import java.io.IOException;

public class ass1 {
	
	public static void main(String[] args) throws IOException, SubBlockNotFoundException, IncompatibleDimensionException {
		TwoDBlockMatrix a = TwoDBlockMatrix.buildTwoDBlockMatrix();
		
		TwoDBlockMatrix b = TwoDBlockMatrix.buildTwoDBlockMatrix();
		
		TwoDBlockMatrix c = a.multiply(b);
		
		for(int i = 0; i < c.row; i++) {
			for(int j = 0; j < c.col; j++) {
				System.out.print(c.matrix[i][j]);
			}
			System.out.println();
		}
		
		
	}
}
