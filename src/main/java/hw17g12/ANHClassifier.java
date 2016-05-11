package hw17g12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openimaj.feature.ByteFVComparison;
import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.image.FImage;
import org.openimaj.image.feature.local.engine.DoGSIFTEngine;
import org.openimaj.image.feature.local.keypoints.Keypoint;

public class ANHClassifier extends EarClassifier{

	private DoGSIFTEngine engine;
	private final Integer epochs = 5;
	
	private Map<String,Model> learnedModels;
	
	public ANHClassifier(int kfolds) {
		super(kfolds);
		engine = new DoGSIFTEngine();	
	}
	
	protected void generateFeature(FImage image){
		LocalFeatureList<Keypoint> siftKeypoints = engine.findFeatures(image);
		
	}

	@Override
	public Double calculateMeanAbsoluteError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Double> classify(FImage earImage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void buildModel(FImage[] classImages, String className) {
		
		if(!learnedModels.containsKey(className)){
			Model m = new Model();
			learnedModels.put(className,m);
		}
		Model m = learnedModels.get(className);
		
		for(int i = 0; i < epochs; i++){
			double distancemean = 0;
			double descriptionmean = 0;
			
			int numberOfKeypoints = 0;
			
			for(FImage f : classImages){
				LocalFeatureList<Keypoint> siftKeypoints = engine.findFeatures(f);
				
				for(Keypoint newPoint : siftKeypoints){
					for(Keypoint originalKeypoint : m.keypoints){
						Double descriptionDistance = newPoint.getFeatureVector().compare(originalKeypoint.getFeatureVector(), ByteFVComparison.EUCLIDEAN);
						Double locationDistance = Math.sqrt(Math.pow(newPoint.x - originalKeypoint.x,2)+Math.pow(newPoint.y - originalKeypoint.y,2));
						
						distancemean = distancemean + locationDistance;
						descriptionmean = descriptionmean + descriptionDistance;
						numberOfKeypoints++;
					}
				}
			}
			
			distancemean = distancemean/numberOfKeypoints;
			descriptionmean = descriptionmean/numberOfKeypoints;
			
			m.descriptionMean = descriptionmean;
			m.distanceMean = distancemean;
		}
	}
	
//	private Double distanceBetweenKeypoints(Keypoint one, Keypoint two){
//		byte descriptionDifference = 0;
//		for(int i = 0; i < one.ivec.length; i++){
//			descriptionDifference = Math.abs(one.ivec[i] - two.ivec[i]);
//		}
//		
//		return Math.abs(one.ivec-two.ivec)
//	}
	
	private void setup(){
		String[] classes = loader.getClasses();
		
		for(String c : classes){
			FImage[] images = loader.getClassImages(c);
			buildModel(images,c);
		}
	}
	
	private class Model{
		final List<Keypoint> keypoints; 
		Double distanceMeasure;
		
		double distanceMean;
		double descriptionMean;
		
		public Model(){
			keypoints = new ArrayList<Keypoint>();
		}
	}
}
