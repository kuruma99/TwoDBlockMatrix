import java.text.DecimalFormat;
import java.io.*;
public class TwoDBlockMatrix {
	
	float[][] matrix;
	int row =0, col = 0;
	
	static TwoDBlockMatrix buildTwoDBlockMatrix() throws IOException {
		
		File doc = new File("/Users/mridul/eclipse-workspace/Assignment1/src/file"); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(doc)); 
		  
		  TwoDBlockMatrix obj = new TwoDBlockMatrix();
		  
		  //Reading the file
		  String st; 
		  int rowMax = 0, colMax = 0;
		  String hash = "#";
		  int currRow = 0, currCol = 0;
		  while ((st = br.readLine()) != null) {
			  
		      int n = st.length();
			  int y = 0;
			  
			  if(st.charAt(n-1) != ';' && !st.equals(hash)) {
				  
				  currRow = Character.getNumericValue(st.charAt(0));
				  currCol = Character.getNumericValue(st.charAt(2));
				  //System.out.println("currRow: " + currRow + "currCol: " + currCol);
			  }
			  
			  else if(st.charAt(n-1) == ';'){
				  currRow++; 
				  String[] tokens = st.split(" ");
				  y = tokens.length;
			  }
			  
			  else {
				  currRow--;
				  currCol += y;
				  if(currRow > rowMax)
					  rowMax = currRow;
				  if(currCol > colMax)
					  colMax = currCol;
 			  }		  
		  } 
		  obj.row = rowMax;
		  obj.col = colMax;
		  		  
		  obj.matrix = new float[rowMax][colMax];

		  
		  br = new BufferedReader(new FileReader(doc));
		  
		  while ((st = br.readLine()) != null) {
			  
			  int n = st.length();
			  
			  if(st.charAt(n-1) != ';' && !st.equals(hash)) {
				  currRow = Character.getNumericValue(st.charAt(0)) - 1;
				  currCol = Character.getNumericValue(st.charAt(2)) - 1;
			  }
			  
			  else if(st.charAt(n-1) == ';') {
				  String[] tokens = st.split("[ ;]");
				  DecimalFormat format2dec = new DecimalFormat("0.00");
				  for(int j = 0; j < tokens.length; ++j) {
					  obj.matrix[currRow][currCol + j] = Float.valueOf(format2dec.format(Float.valueOf(tokens[j])));
				  }
				  currRow++;
			  }
			  
		  }
		  

		  return obj;
		  
	}
	
	public TwoDBlockMatrix() {
		
	}
	
	public TwoDBlockMatrix(float[][] array) {
		this.matrix = array;
	}
	
	public TwoDBlockMatrix (int nrow, int ncol) {
		this.matrix = new float[nrow][ncol];
		this.row = nrow;
		this.col = ncol;
	}
	
	public TwoDBlockMatrix transpose() {
		
		
		int nrow = this.col;
		int ncol = this.row;
		TwoDBlockMatrix obj = new TwoDBlockMatrix(nrow, ncol);
		
		for(int i = 0; i < obj.row; i++) 
		{
			for(int j = 0; j < obj.col; j++) 
			{
				obj.matrix[j][i] =  this.matrix[i][j];
			}
		}
		
		return obj;
	}
	
	public TwoDBlockMatrix multiply(TwoDBlockMatrix other) throws IncompatibleDimensionException 
	{	
		try {
			if(other.row!=this.col) {
				throw new IncompatibleDimensionException("Matrix not of compatible dimension");
			}
		}
		catch(IncompatibleDimensionException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
		int nrow = this.row;
		int ncol = other.col;
		TwoDBlockMatrix obj = new TwoDBlockMatrix(nrow, ncol);
		

	for(int i=0;i< obj.row;i++)
		for(int j=0;j< obj.col;j++)
			for(int k=0;k<this.col;k++) {
				DecimalFormat format2dec = new DecimalFormat("0.00");
				obj.matrix[i][j] =Float.valueOf(format2dec.format(obj.matrix[i][j]+ this.matrix[i][k]*other.matrix[k][j])); 
				
			}
		

		return obj;
	}
	
	public TwoDBlockMatrix  getSubBlock(int row_start, int col_start, int row_end, int col_end) throws SubBlockNotFoundException
	{
		
		int nrow = row_end - row_start ;
		int ncol = col_end - col_start ;
		TwoDBlockMatrix obj = new TwoDBlockMatrix(nrow, ncol);
		
		
		
		try {
			if(row_start < 1 || row_start > this.row || col_start < 1 || col_end > this.col || row_end < row_start || row_end > this.row || col_end < col_start || col_end > this.col) {
				throw new SubBlockNotFoundException("Sub Block Creation not possible");
			}
		}
		catch(SubBlockNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
		

		for(int i= 0;i < obj.row;i++)
			for(int j=0; j < obj.col;j++)	
				obj.matrix[i][j] =this.matrix[i + row_start-1][j + col_start-1];
		


		
		return obj;
		
		
	}
}
