package Implementation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Cyntwikip
 *
 */
public class InputHandler {
	
	public String[] getInput(String fileName) {
		String[] str;
		String line = null;
		
		try {
			line = readFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(line);
		
		str = stringSeparator(line);
		
		return str;
	}
	

	//from: http://stackoverflow.com/questions/16027229/reading-from-a-text-file-and-storing-in-a-string
	public String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	public String[] stringSeparator(String line) {		
		String[] str;
		
		str = line.split("\n", 0);
		
		return str;
	}
	
}
