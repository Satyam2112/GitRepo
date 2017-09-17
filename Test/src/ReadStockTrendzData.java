import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadStockTrendzData {

    public static void main(String[] args) throws IOException {
        String fileName = "StockTrendzData.txt";
        File file = new File(fileName);
        Path path =Paths.get(file.getAbsolutePath());
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());

        String line;
        int lineNo=1,stockNo=0,stockName=0,buyAbove=0,sellBelow=0;
        StringBuilder stockNameSb = new StringBuilder();
        StringBuilder buyAboveSb = new StringBuilder();
        StringBuilder sellBelowSb = new StringBuilder();
        StringBuilder tradeDoneSb=new StringBuilder();
        String constName ="Add(\"XXXXXX\",PeriodType.Minute, 1);";
        		
        // read each line
        while((line = reader.readLine()) != null) {
        	//System.out.println(lineNo+"-"+stockNo);
        	stockName=stockNo*10+2;
        	buyAbove=stockNo*10+9;
        	sellBelow=stockNo*10+10;
        	//System.out.println(stockName+"-"+buyAbove+"-"+sellBelow);
        	if(lineNo==stockName){
        		//System.out.println(line);
        		stockNameSb.append(constName.replace("XXXXXX", line)).append("\n");
        		tradeDoneSb.append("0").append(",");
        	}
        	if(lineNo==buyAbove){
        		//System.out.println(line);
        		buyAboveSb.append(line).append(",");
        	}
        	if(lineNo==sellBelow){
        		//System.out.println(line);
        		sellBelowSb.append(line).append(",");
        	}
        	
        	
        	
        	if(lineNo%10==0)
        		stockNo++;
        	
        	 lineNo++;
        }
        System.out.println("StockName :"+stockNameSb.toString());
        System.out.println("buyAboveSb :"+buyAboveSb.toString());
        System.out.println("sellBelowSb :"+sellBelowSb.toString());
        System.out.println("tradeDoneSb :"+tradeDoneSb.toString());
           
    }


}
