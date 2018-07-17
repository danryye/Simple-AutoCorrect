import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class AutoCorrection {
	
	public static void main(String[] args){
		/* Path to the desired text file. In this case, the file
		   was named sample.txt and was located on my Desktop.*/
		String filePath = "C:\\Users\\dan\\Desktop\\sample.txt";
		
		
		System.out.println("Reading from file");
		List<String> l = readFromFile(filePath);
		
		
		System.out.println("Finished reading from file");
		//Using the Iterator to print the contents from the raw
		//text file
		Iterator<String> itr = l.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		
		
		System.out.println("Applying Corrections.");
		String text = doCorrections(l);
		overwrite(text, filePath);
		
		System.out.println("Printing Corrected Text.");
		System.out.println(text);
		
		
		System.out.println("Finished");
	}
	
	/*
	 * Overwrites the text file at the file path provided with the
	 * corrected text. Uses FileWriter to replace its content
	 * @static
	 * @param (String) text corrected text to overwrite the text file with
	 * @param 
	 */
	private static void overwrite(String correctedText, String filePath) {
		try {
			FileWriter fw = new FileWriter(new File(filePath), false);
			fw.write(correctedText);
			fw.close();
		} catch (IOException e) { //otherwise print error
			e.printStackTrace();
		}
		
	}

	/*
	 * Takes in the path of a text file and returns a list of 
	 * type String. The contents of the text file will be in 
	 * the zeroth index
	 * 
	 */
	public static List<String> readFromFile(String filePath){
		
		List<String> list = Collections.emptyList(); //Creates empty list of type String
		
		//tries to retrieve the contents of the file from the attacked path
		//using the Files class at java.nio.file.Files
		try{
			list = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8); //readAllLines(Paths, StandardCharsets)
		}catch(IOException e){ //otherwise print the error
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/*
	 *Takes in a List of type String and applies corrections to the contents.
	 *Returns a String containing the corrected content.
	 *Currently, capitalizes the start of a sentence
	 *
	 *@static
	 *@param (List<String>) list list containing the contents of the text file
	 *@returns String of corrected text
	 */
	public static String doCorrections(List<String> list){
		String text = list.get(0);
		text = text.substring(0, 1).toUpperCase() + text.substring(1); //capitalizes first character
		for(int i = 1; i < text.length(); i++){ //loop to go through entire content
			if(text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?'){ //indicators of the end of a sentence
				if(i + 1 < text.length() && i + 2 < text.length() && text.charAt(i+1) == ' '){
					text = text.substring(0, i + 2) + text.substring(i + 2, i + 3).toUpperCase() + text.substring(i + 3);
				}
			}
		}
		
		
		return text;
		
	}
}
