package transforms;

import org.openimaj.image.FImage;

public class CelestialEdgeDectection {

	private FImage original;
	
	final int windowX = 3;
	final int windowY = 3;
	
	final float gravityCoefficient = 1f;
	
	public CelestialEdgeDectection(FImage original){
		this.original = original;
	}
	
	public FImage getOriginal(){
		return original;
	}
	
	public FImage getTransform(){
		float[][] newPixels = new float[original.pixels.length][original.pixels[0].length];
		float[][] originalPixels = original.pixels;
		
		for(int x = 0; x < newPixels.length; x++){
			for(int y = 0; y < newPixels[0].length; y++){
				double forcex = 0.0f;
				double forcey = 0.0f;
				float currentIntensity = originalPixels[x][y];
						
				if(x == 0 && y == 0){
					
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x+1][y])/Math.sqrt(1) 
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y+1])/Math.sqrt(2) ;
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y+1])/Math.sqrt(1) 
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y+1])/Math.sqrt(2);
					
					
				}else if(x == (newPixels.length-1) && y == (newPixels[0].length-1)){
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x-1][y]) /Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y-1])/Math.sqrt(2);
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y-1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y-1])/Math.sqrt(2);
				}else if(x == 0 && y == (newPixels[0].length-1)){
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x+1][y])/Math.sqrt(1) 
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y-1])/Math.sqrt(2);
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y-1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y-1])/Math.sqrt(2);
					
				}else if(x == (newPixels.length-1) && y == 0){
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x-1][y])/Math.sqrt(1) 
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y+1])/Math.sqrt(2);
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y+1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y+1])/Math.sqrt(2);
				}
				else if(x == 0){
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x+1][y])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y+1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y-1])/Math.sqrt(2);
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y+1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x][y-1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y+1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y-1])/Math.sqrt(2);
					
				}else if(y == 0){
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x-1][y])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y+1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y+1])/Math.sqrt(2);
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y+1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y+1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y+1])/Math.sqrt(2);
					
				}else if(x == (newPixels.length-1)){
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x-1][y+1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y-1])/Math.sqrt(2);
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y+1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y+1])/Math.sqrt(3)
							+ (gravityCoefficient*currentIntensity*originalPixels[x][y-1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y-1])/Math.sqrt(3);
							
				}else if(y == (newPixels[0].length-1)){
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x-1][y])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y-1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y-1])/Math.sqrt(2);
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y-1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y-1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y-1])/Math.sqrt(2);
				}else{
					forcex = (gravityCoefficient*currentIntensity*originalPixels[x-1][y])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y-1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y+1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y-1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y+1])/Math.sqrt(2);
					
					forcey = (gravityCoefficient*currentIntensity*originalPixels[x][y+1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x][y-1])/Math.sqrt(1)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y-1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x-1][y+1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y-1])/Math.sqrt(2)
							+ (gravityCoefficient*currentIntensity*originalPixels[x+1][y+1])/Math.sqrt(2);
				}
				
				float force = (float) Math.sqrt(Math.pow(forcex,2) + Math.pow(forcey,2));
				newPixels[x][y] = force;
			}
		}
		
		return new FImage(newPixels);
	}
}
