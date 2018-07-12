import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class AutoCorrection {
	
	public static void main(String[] args){
		
		String filePath = "C:\\Users\\dan\\Desktop\\sample.txt";
		
		List<String> l = readFromFile(filePath);
		
		System.out.println("Read from file");
		
		Iterator<String> itr = l.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		
		System.out.println("Applying Corrections.");
		
		String text = doCorrections(l);
		
		System.out.println("Printing Corrected Text.");
		System.out.println(text);
		
		System.out.println("Finished");
	}
	
	public static List<String> readFromFile(String filePath){
		
		List<String> list = Collections.emptyList();
		
		try{
			list = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public static String doCorrections(List<String> list){
		String text = list.get(0);
		text = text.substring(0, 1).toUpperCase() + text.substring(1);
		for(int i = 1; i < text.length(); i++){
			if(text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?'){
				if(text.charAt(i+1) == ' '){
					text = text.substring(0, i + 2) + text.substring(i + 2, i + 3).toUpperCase() + text.substring(i + 3);
				}
			}
		}
		
		return text;
		
	}
}
