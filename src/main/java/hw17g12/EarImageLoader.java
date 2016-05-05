package hw17g12;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;

public class EarImageLoader {
	public final static String earFolder = "images";
	
	private File directory;
	private String[] classes;
	private Map<String,List<FImage>> images;
	
	public static void main(String args[]){
		File f = new File(earFolder);
		System.out.println(f.listFiles().length);
		try{
			EarImageLoader eil = new EarImageLoader();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public String[] getClasses(){
		return classes;
	}
	
	public FImage[] getClassImages(String classname){
		if(!images.containsKey(classname)){
			return null;
		}else{
			return images.get(classname).toArray(new FImage[images.get(classname).size()]);
		}
	}
	
	public EarImageLoader() throws IOException{
		directory = new File(earFolder);
		images = new HashMap<String,List<FImage>>();
		
		File[] imageFiles = directory.listFiles();
		Set<String> classnames = new HashSet<String>();
		
		for(File f : imageFiles){
			String name = f.getName();
			if(name.matches("[0-9]+-[0-9].jpg") && !f.isDirectory()){
				String className = name.substring(0, 3);
				Integer imageNum = Integer.parseInt(name.substring(4,5));
				
				classnames.add(className);
				if(!images.containsKey(className)){
					images.put(className, new ArrayList<FImage>());
				}
				String filePath = f.getAbsolutePath();
				InputStream is = new FileInputStream(f);
				FImage img = ImageUtilities.readF(is);
				images.get(className).add(img);
			}
		}
		
		classes = new String[classnames.size()];
		int index = 0;
		for(String c : classnames){
			classes[index] = c;
			index++;
		}
	}
}
