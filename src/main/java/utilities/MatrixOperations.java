package utilities;

public class MatrixOperations {
	public static float[] multiplyMatrixByVector(float[][] matrix, float[] vector){
		if(matrix[0].length != vector.length){
			throw new IllegalArgumentException("Dimensions do not match");
		}
		
		float[] result = new float[matrix.length];
		int index = 0;
		for(int x = 0; x < matrix.length; x++){
			float value = 0;
			for(int y = 0; y < matrix[x].length; y++){
				value = value + (matrix[x][y]*vector[y]);
			}
			result[index] = value;
			index++;
		}
		return result;
	}
	
	public static float[][] multiplyMatrixByMatrix(float[][] one, float[][] two){
		if(one[0].length != two.length){
			throw new IllegalArgumentException("Dimensions do not match");
		}

		int aRows = one.length;
        int aColumns = one[0].length;
        int bRows = two.length;
        int bColumns = two[0].length;
		
		float[][] result = new float[one.length][two[0].length];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
            	result[i][j] = 0.0f;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    result[i][j] += one[i][k] * two[k][j];
                }
            }
        }

		
		return result;
	}
	
	public static void main(String args[]){
		float[][] one = new float[2][3];
		one[0][0] = 1.0f;
		one[0][1] = 2.0f;
		one[0][2] = 3.0f;
		one[1][0] = 4.0f;
		one[1][1] = 5.0f;
		one[1][2] = 6.0f;
		
		float[][] two = new float[3][2];
		two[0][0] = 7.0f;
		two[0][1] = 8.0f;
		two[1][0] = 9.0f;
		two[1][1] = 10.0f;
		two[2][0] = 11.0f;
		two[2][1] = 12.0f;
				
		float[][] answer = multiplyMatrixByMatrix(one,two);
		
	}
	
}
