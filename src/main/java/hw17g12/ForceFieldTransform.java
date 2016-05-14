package hw17g12;

import org.openimaj.image.FImage;
import org.openimaj.image.processing.algorithm.FourierTransform;

public class ForceFieldTransform {
	private FImage original;
	private FourierTransform ft;
	
	public ForceFieldTransform(FImage original){
		this.original = original;
		ft = new FourierTransform(original,true);
	}
	
	public FImage getOriginal(){
		return original;
	}
	
	public FImage getTransform(){
		float[][] originalPixels = original.pixels;
		float[][] newPixels = new float[originalPixels.length][originalPixels[0].length];
		
		FImage fourierMagnitude = ft.getMagnitude();
		FImage fourierInverse = ft.inverse();
		
		for(int x = 1; x < originalPixels.length; x++){
			for(int y = 1; y < originalPixels[0].length; y++)
			{
				float originalIntensity = originalPixels[x][y];
				float newValue = 0;
				
				for(int xOther = 1; xOther < originalPixels.length; xOther++){
					for(int yOther = 1; yOther < originalPixels[0].length; yOther++)
					{
						if((xOther != x) && (yOther != y)){
							float otherIntensity = originalPixels[xOther][yOther];
							float one = (float) ((xOther-x)/Math.pow(Math.abs(xOther - x),3));
							float two = (float) ((yOther-y)/Math.pow(Math.abs(yOther - y),3));
							
							newValue = newValue + (otherIntensity*one*two);
						}
					}
				}
				
				newPixels[x][y] = newValue;
			}
		}
		return new FImage(newPixels);
	}
}
