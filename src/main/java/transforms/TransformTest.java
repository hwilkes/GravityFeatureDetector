package transforms;

import java.io.IOException;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;

import hw17g12.EarImageLoader;

public class TransformTest {
	public static void main(String args[]){
		EarImageLoader loader;
		try {
			loader = new EarImageLoader();
			String[] classes = loader.getClasses();
			FImage img = loader.getClassImages(classes[0])[0];
			float[][] brighter = new float[img.pixels.length][img.pixels[0].length];
			for(int x = 0; x < brighter.length; x++){
				for(int y = 0; y < brighter[0].length; y++){
					brighter[x][y] = img.pixels[x][y]*1.5f;
				}
			}
			FImage newImg = new FImage(brighter);
			//ForceFieldTransform fft = new ForceFieldTransform(newImg);
			InvertibleLinearTransform il = new InvertibleLinearTransform(newImg);
			FImage transformed = il.getTransform();
			
			DisplayUtilities.display(newImg);
			DisplayUtilities.display(transformed);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
