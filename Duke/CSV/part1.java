package CSV;
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part1 {
    
     public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String currentCountry = record.get("Country");
            if (currentCountry.equalsIgnoreCase(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return country + ": " + exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String exports =record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
        
    } 
    
    public int numberOfExporters(CSVParser parser, String exportItem){
      int count = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
             count++;   
            }
         
        }
        return count;
    }
    
    void bigExporters(CSVParser parser, String amount) {
    // Get the length of the amount string
    int length = amount.length();
    System.out.println("print length: " + length);

    // Loop through each record in the parser
     for (CSVRecord record : parser) {
      // Get the Value (dollars) string for the current record
      String value = record.get("Value (dollars)");

      // Check if the length of the value string is greater than the length of the amount string
       if (value.length() > length) {
         // If so, print the name of the country and its value
         System.out.println(record.get("Country") + ": " + value);
       }
     }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
       // System.out.println(countryInfo(parser,"Nauru"));
        
       // parser = fr.getCSVParser();
       // listExportersTwoProducts(parser,"cotton","flowers");
        
        //parser = fr.getCSVParser();
       // System.out.println(numberOfExporters(parser,"cocoa"));
        
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }

}
