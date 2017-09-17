import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class OpenAndMergeZipFiles {
	public static void main(String[] args) throws IOException{
	String destination ="C:\\Users\\user\\Desktop\\Stock1MinDataZip\\AUG";
	//String source ="C:\\Users\\user\\Downloads\\2017-20170625T024821Z-001\\2017\\JUN\\";
	String source ="C:\\Users\\user\\Desktop\\Stock1MinDataZip\\AUG\\";
	List<String> destFileList= getDestinaionFileList(source);	
	for(String file : destFileList){
		System.out.println(source+file);
		unzip(new File(source+file),destination);
	}
		
	}
public static void unzip(File source, String out) throws IOException {
    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {

        ZipEntry entry = zis.getNextEntry();

        while (entry != null) {

            File file = new File(out, entry.getName());

            if (entry.isDirectory()) {
                file.mkdirs();
            } else {
                File parent = file.getParentFile();

                if (!parent.exists()) {
                    parent.mkdirs();
                }

                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {

                    byte[] buffer = new byte[Math.toIntExact(entry.getSize())];

                    int location;

                    while ((location = zis.read(buffer)) != -1) {
                        bos.write(buffer, 0, location);
                    }
                }
            }
            entry = zis.getNextEntry();
        }
    }
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