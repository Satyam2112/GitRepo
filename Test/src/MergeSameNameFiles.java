import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MergeSameNameFiles {

	public static void main(String[] args) throws IOException {
		String destination = "C:\\Users\\user\\Desktop\\Stock1MinDataZip\\AUG17\\";
		//String destination = "C:\\Users\\user\\Desktop\\Stock1MinDataZip\\NIFTY50_JUL2017\\";
		String source ="C:\\Users\\user\\Desktop\\Stock1MinDataZip\\";
//		String[] folderArray = {"01AUG","02AUG","03AUG"
//								,"04AUG","07AUG","08AUG"
//								,"09AUG","10AUG","11AUG"
//								,"14AUG","16AUG","17AUG"};
//								//,"19JUL","20JUL","21JUL"
//								//,"24JUL","25JUL","26JUL","27JUL","28JUL"};
		
//		String[] folderArray = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
		String[] folderArray = {"NIFTY50_AUG2017","01SEP"};
		List<String> destFileList= getDestinaionFileList(destination);
		
		for(String strDate : folderArray){
			//destFileList= getDestinaionFileList(destination);
			String folderName = source+strDate;
			System.out.println("WORKING ON FOLDER ["+folderName+"]");
			File folder = new File(folderName);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
			    if (file.isFile() && isValidFile(file.getName())) {
			    	if(destFileList.contains(file.getName())){
			    		System.out.println("File present in destination open to merge.");
			    		String destFile = destination+file.getName();
			    		Path path =Paths.get(file.getAbsolutePath());
			    		writeToFile(destFile, path);
			    	}else{
			    		System.out.println("Create new file ["+file.getName()+"] in destination.");
			    		String destFile = destination+file.getName();
			    		Path path =Paths.get(file.getAbsolutePath());
			    		writeToFile(destFile, path);

			    	}
			    }
			}
		}
	}

	private static boolean isValidFile(String fileName) {
		
		if(fileName.contains("-") || fileName.contains("_") ||fileName.contains("&"))
			return false;
		else return true;
	}

	private static void writeToFile(String destFile,Path fileToRead) throws IOException{
		//Open Dest file for merge
		FileWriter fw = new FileWriter(destFile, true);
		BufferedWriter bw = new BufferedWriter(fw);

		//Read source file 
	    BufferedReader reader = Files.newBufferedReader(fileToRead, Charset.defaultCharset());
        String line;
        // read each line
        while((line = reader.readLine()) != null) {
            //System.out.println(line);
            String[] lineData = line.split(",");		
            if(lineData.length>7)
            bw.write(lineData[1]+" "+
  		          lineData[2].replace(":", "")+"00"+";"+
  		          lineData[3]+";"+lineData[4]+";"+lineData[5]+";"+lineData[6]+";"+lineData[7]);
            else
            	 bw.write(lineData[1]+" "+
         		          lineData[2].replace(":", "")+"00"+";"+
         		          lineData[3]+";"+lineData[4]+";"+lineData[5]+";"+lineData[6]+";"+"0");
            	
            bw.newLine();
        }
        reader.close();
		//Merge in dest file
        
        //Close all files
	    bw.flush();
	    bw.close();
	    reader.close();
	}
	
	private static List<String> getDestinaionFileList(String destination) {
		List<String> destFileList = new ArrayList<String>();
		File folder = new File(destination);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        destFileList.add(file.getName());
		    }
		}
		return destFileList;
	}

}
