package hw17g12;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openimaj.image.FImage;

public abstract class EarClassifier {
	protected EarImageLoader loader;
	protected int kfolds;
	
	public EarClassifier(int kfolds){
		try {
			loader = new EarImageLoader();
			if((loader.getNumberOfImages() % kfolds) == 0){
				throw new IllegalArgumentException();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("IOException caused by image loader");
		}
	}
	
	protected abstract void buildModel(FImage[] classImages,String className);
	
	//Run k fold validation over dataset to find MAE
	public abstract Double calculateMeanAbsoluteError();
	
	//Returns predicted classes along with liklihoods as doubles
	public abstract Map<String,Double> classify(FImage earImage);
}
