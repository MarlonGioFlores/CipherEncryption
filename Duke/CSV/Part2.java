package CSV;
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
    // Initialize the coldest temperature to the highest possible temperature
    // so that any temperature in the file will be less than this value
    double coldestTemp = Double.MAX_VALUE;
    CSVRecord coldestRecord = null;

    // Iterate over all the rows in the file
    for (CSVRecord record : parser) {
        // Get the temperature value from the current row
        double currentTemp = Double.parseDouble(record.get("TemperatureF"));

        // Check if the current temperature is lower than the coldest temperature
        // and if the current temperature is not a bogus value (-9999)
        if (currentTemp < coldestTemp && currentTemp != -9999) {
            // If so, update the coldest temperature and the coldest record
            coldestTemp = currentTemp;
            coldestRecord = record;
        }
    }

    // Return the CSVRecord with the coldest temperature
    return coldestRecord;
}

public File fileWithColdestTemperature() {
    // Initialize the coldestTemperature to the highest possible value
    // to make sure the first temperature in the files will be considered
    // as the coldest temperature by default
     double coldestTemperature = Double.MAX_VALUE;
     File coldestFilename = null;

     // Create a directory resource to access the selected files
     DirectoryResource dr = new DirectoryResource();

    // Iterate over the selected files
    for (File file : dr.selectedFiles()) {
        // Create a file resource for the current file
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();

        // Get the coldest temperature in the current file
        CSVRecord coldestRecord = coldestHourInFile(parser);
        double currentTemperature = Double.parseDouble(coldestRecord.get("TemperatureF"));

        // If the coldest temperature in the current file is colder than
        // the current coldest temperature, update the coldest temperature
        // and the filename of the coldest temperature
        if (currentTemperature < coldestTemperature) {
            coldestTemperature = currentTemperature;
            coldestFilename = file;
        }
        
        
    }
     

    // Return the filename of the coldest temperature
    return coldestFilename;
}

 public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        double lowest = 0;
        double current = 0;
        for (CSVRecord record: parser) {
            if (lowestSoFar == null) lowestSoFar = record;
            if (record.get("Humidity").equals("N/A")) {current = -999;}
            else {current = Double.parseDouble(record.get("Humidity"));}
            
            if (lowestSoFar.get("Humidity").equals("N/A")) {lowest = -999;}
            else {lowest = Double.parseDouble(lowestSoFar.get("Humidity"));}
            
            if (current < lowest && current != -999) lowestSoFar = record;
        }
        return lowestSoFar;
    }
    
    public File lowestHumidityInManyFiles() {
        DirectoryResource dr =  new DirectoryResource();
        CSVRecord lowest = null;
        String filename = null;
        File file = null;
        for (File f: dr.selectedFiles()) {
            //System.out.println("Contador= " + 1 );
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (lowest == null) lowest = current;
            double currentHum = Double.parseDouble(current.get("Humidity"));
            double lowestHum = Double.parseDouble(lowest.get("Humidity"));
            if (currentHum < lowestHum) {
                lowest = current;
                file = f ;
            }
        }
        return file;
    }
    
    public double AvTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int number = 0;
        for (CSVRecord record:parser) {
            double current = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + current;
            number = number + 1;
        }
        sum = sum / number;
        return sum;
    }
    
     public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        double number = 0;
        double humidity = 0;
        for (CSVRecord record: parser) {
            if (record.get("Humidity").equals("N/A")) humidity = -999;
            else humidity = Double.parseDouble(record.get("Humidity"));
            if (humidity >= value) {
                number = number + 1;
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
            }
        }
       
        return sum/number;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();        
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (average == 0) System.out.println("No temperatures with that humidity");
        else {
            System.out.print("Average temperature with high Humidity is ");
            System.out.println(average);
        }
    }
    
    public void testAvTemperatureInFile() {
        FileResource fr = new FileResource();
        double average = AvTemperatureInFile(fr.getCSVParser());
        System.out.print("Average temperature in file is ");
        System.out.println(average);
    }
    
    public void testLowestHumidityInManyFile() {
        File filename = lowestHumidityInManyFiles();
        //System.out.print("Day with lowest humidity was in file ");
        //System.out.println(filename);
        FileResource fr = new FileResource(filename);
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.print("Lowest Humidity was ");
         System.out.print(lowest.get("Humidity"));
         System.out.print(" at ");
         System.out.println(lowest.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord result = lowestHumidityInFile(fr.getCSVParser());
        System.out.print("Lowest Humidity was ");
        System.out.print(result.get("Humidity"));
        System.out.print(" at ");
        System.out.println(result.get("DateUTC"));
    }

public void testFileWithColdestTemperature() {
    // Get the filename of the file with the coldest temperature
    File filename = fileWithColdestTemperature();
    //String filename = file.getName();
    FileResource fr = new FileResource(filename);
    CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
    
    // Print the filename of the file with the coldest temperature
    System.out.println("File with the coldest temperature: " + filename);
    System.out.print("The coldest temperature on that day was ");
    System.out.println(coldest.get("TemperatureF"));
    System.out.println("All the temperatures on the coldest day were");
        for (CSVRecord record:fr.getCSVParser()) {
            System.out.print(record.get("DateUTC"));
            System.out.print(" ");
            System.out.println(record.get("TemperatureF"));
        }
}



public void testColdestHourInFile() {
    // Create a CSVParser for the file
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();

    // Get the CSVRecord with the coldest temperature
    CSVRecord coldestRecord = coldestHourInFile(parser);

    // Print the coldest temperature and the time of its occurrence
    System.out.println("Coldest temperature: " + coldestRecord.get("TemperatureF"));
    System.out.println("Time of occurrence: " + coldestRecord.get("TimeEST"));
}


}
