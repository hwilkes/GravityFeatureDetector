package transforms;

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
		
		float sqrt = (float) Math.sqrt(originalPixels.length*originalPixels[0].length);
		
		float[][] inverse = ft.inverse().pixels;
		float[][] originalMagnitude = ft.getMagnitude().pixels;
		
		
		return new FImage(newPixels);
	}
	
//	public FImage getTransform(){
//		float[][] originalPixels = original.pixels;
//		float[][] newPixels = new float[originalPixels.length][originalPixels[0].length];
//		
//		FImage fourierMagnitude = ft.getMagnitude();
//		FImage fourierInverse = ft.inverse();
//		
//		for(int x = 1; x < originalPixels.length; x++){
//			for(int y = 1; y < originalPixels[0].length; y++)
//			{
//				float originalIntensity = originalPixels[x][y];
//				float newValue = 0;
//				
//				for(int xOther = 1; xOther < originalPixels.length; xOther++){
//					for(int yOther = 1; yOther < originalPixels[0].length; yOther++)
//					{
//						if((xOther != x) && (yOther != y)){
//							float otherIntensity = originalPixels[xOther][yOther];
//							float distance = (float) Math.sqrt(Math.pow(yOther - y, 2) + Math.pow(xOther - x, 2));
//							
//							//float one = (float) ((xOther-x)/Math.pow(Math.abs(xOther - x),3));
//							//float two = (float) ((yOther-y)/Math.pow(Math.abs(yOther - y),3));
//							newValue = (float) (otherIntensity*(1/Math.sqrt(distance)));
//							//newValue = newValue + (otherIntensity*one*two);
//						}
//					}
//				}
//				
//				newPixels[x][y] = newValue*originalPixels[x][y];
//			}
//		}
//		return new FImage(newPixels);
//	}
}
