import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertToNinjaFormat {

	public static void main(String[] args) throws IOException {
		
		String path = "C:\\Users\\user\\Desktop\\Stock1MinDataZip\\NIFTY50_APR2017";
		String pathOut = "C:\\Users\\user\\Desktop\\NinjaDataOut\\";
		Path dir = Paths.get(path);
		DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
    	

        for(Path file : stream){        
        	File fileOut = new File(pathOut+file.getFileName().toString().split("\\.")[0]+".Last");
        	if(!file.getFileName().toString().contains("F1"))
        	System.out.println(file.getFileName());
            fileOut.createNewFile();
            FileWriter fw = new FileWriter(fileOut);
            BufferedWriter bw = new BufferedWriter(fw);
		if(Files.exists(file) && Files.isReadable(file)) {
		    try {
	        // File reader
		        BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());

		        String line;
		        // read each line
		        while((line = reader.readLine()) != null) {
		            //System.out.println(line);

		            String[] lineData = line.split(",");		            
//		            bw.write(lineData[1]+" "+
//          		          lineData[2].replace(":", "")+"00"+";"+
//          		          lineData[3]+";"+lineData[4]+";"+lineData[5]+";"+lineData[6]+";"+lineData[7]);
//		            bw.newLine();
		        }
		        reader.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }finally{
		    	bw.flush();
		    	bw.close();
		    }
		}
        }

	}

	

}
