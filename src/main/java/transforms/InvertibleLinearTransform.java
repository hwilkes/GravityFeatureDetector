package transforms;

import org.openimaj.image.FImage;
import org.openimaj.image.processing.algorithm.FourierTransform;

import utilities.MatrixOperations;

public class InvertibleLinearTransform {
	private FImage original;
	private float[] pixelIntensities;
	
	public InvertibleLinearTransform(FImage original){
		this.original = original;
		
		pixelIntensities = new float[original.height*original.width];
		int index = 0;
		for(int x = 0; x < original.pixels.length; x++){
			for(int y = 0; y < original.pixels[0].length; y++){
				pixelIntensities[index] = original.pixels[x][y];
				index++;
			}
		}
	}
	
	public FImage getOriginal(){
		return original;
	}
	
	public FImage getTransform(){
		System.out.println(pixelIntensities.length);
		System.out.println(pixelIntensities.length*pixelIntensities.length);
		float[][] coefficients = new float[pixelIntensities.length][pixelIntensities.length];
		
		int row = 0;
		
		for(int x = 0; x < original.pixels.length; x++){
			for(int y = 0; y < original.pixels[0].length; y++){
				int column = 0;
				
				for(int xOther = 0; xOther < original.pixels.length; xOther++){
					for(int yOther = 0; yOther < original.pixels[0].length; yOther++){
						if(xOther == x && yOther == y){
							coefficients[row][column] = 0;
						}else{
							coefficients[row][column] = (float) (1/(Math.sqrt(Math.pow((xOther-x),2)-Math.pow((yOther-y),2))));
						}
						column++;
						
					}
				}
				row++;
			}
		}
		
		float[] newIntensities = MatrixOperations.multiplyMatrixByVector(coefficients,pixelIntensities);
		float[][] intensitiesMatrix = new float[original.pixels.length][original.pixels[0].length];
		int index = 0;
		for(int x = 0; x < original.pixels.length; x++){
			for(int y = 0; y < original.pixels[0].length; y++){
				intensitiesMatrix[x][y] = newIntensities[index];
				index++;
			}
		}
		
		return new FImage(intensitiesMatrix);
	}
	
	
	
}
