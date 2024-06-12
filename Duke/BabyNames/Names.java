package BabyNames;
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

/**
 * Write a description of Names here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Names {
    public void totalBirth (FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
         int numBorn = Integer.parseInt(rec.get(2));
             
         totalBirths += numBorn;
         
            if (rec.get(1).equals("M")) {
             totalBoys += numBorn;   
            }
            else {
                totalGirls += numBorn;
                
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total Girls = " + totalGirls);
        System.out.println("Total Boys = " + totalBoys);
        
    }
    
    public void totalBirths2 (CSVParser parser){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int total = 0;
        int totalUniqueB = 0;
        int totalUniqueG = 0;
        int totalUnique = 0;
        
        for (CSVRecord rec : parser) {
                 
            if (rec.get(1).equals("F")) {
             total = total + 1;
             totalGirls = totalGirls + Integer.parseInt(rec.get(2));
             totalUniqueG = totalUniqueG + 1;
             totalUnique = totalUnique + 1;
            }
            if (rec.get(1).equals("M")) {
               total = total +1;
               totalBoys = totalBoys +Integer.parseInt(rec.get(2));
               totalUniqueB = totalUniqueB + 1;
               totalUnique = totalUnique + 1 ;
                
            }
        }
        System.out.println("Total number boy: "+totalBoys);
        System.out.println("Total number girl: "+totalGirls);
        System.out.println("Total number: "+total);
        System.out.println("Total unique number boy: " + totalUniqueB);
        System.out.println("Total unique number girl: " + totalUniqueG);
        System.out.println("Total unique number: " + totalUnique);
        
    }
    
     public int getRank(CSVParser parser, int year, String name, String gender){
      int rank = 0;
      int number = 0;
      
      for (CSVRecord rec : parser){
        if(rec.get(1).equals(gender)){
            rank = rank + 1 ;
             if(rec.get(0).equals(name)){
                 number = 1;
                 break;
                }
         }
        }
        if (number==1 )         return rank;
        else return -1;
    }
    
    public String getName (int year, int rank, String gender) {
     String na = "us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(na);
        CSVParser parser = fr.getCSVParser(false);
        int number = 0;
        int find = 0;
        String finalname = null;
       
            for (CSVRecord record: parser) {
                if (record.get(1).equals(gender)) {
                    number += 1;
                    if (number == rank) {
                        finalname = record.get(0);
                        find = 1;
                }
            }
        }
            if (find == 1) return finalname;
            else return "NO NAME";
    }
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender){
        String nameyear = "us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(nameyear);
        CSVParser parserold = fr.getCSVParser(false);
        
        String namenewyear = "us_babynames_by_year/yob" + newYear + ".csv";
        FileResource fr1 = new FileResource(namenewyear);
        CSVParser parsernew = fr1.getCSVParser(false);
        
        int rank = 0;
        int find = 0;
        for (CSVRecord record: parserold) {
            if (record.get(1).equals(gender)) {
                rank += 1;
                if (record.get(0).equals(name)) {
                    find = 1;
                    break;
                }
            }
        }
        
        int ranknew = 0;
        int findnew = 0;
        if (find == 0) System.out.println("NO NAME!") ;
        else {
            for (CSVRecord record: parsernew) {
                if (record.get(1).equals(gender)) {
                    ranknew += 1;
                    if (ranknew == rank) {
                        findnew = 1;
                        System.out.println(name + " born in " + year + " would be " + record.get(0) + " if she/he was born in "+ newYear);
                    }
                    
                }
            }
            if (findnew == 0) System.out.println("No such rank in year "+ newYear);
        }
    }
    
    // public int yearOfHighestRank (String name, String gender) {
        // DirectoryResource dr = new DirectoryResource();
        // int ranktonow = 0;
        // int findall = 0;
        // for (File f: dr.selectedFiles()) {
            // int rank = 0;
            // int find = 0;
            // String fname = f.getName();
            // FileResource fr = new FileResource(f);
            // CSVParser parser = fr.getCSVParser(false);
            // for (CSVRecord record: parser) {
                // if (record.get(1).equals(gender)) {
                    // rank += 1;
                    // if (record.get(0).equals(name)) {
                        // find = 1;
                        // break;
                    // }
                // }
            // }
            // if (find == 1) {
                // findall = 1;
                // if (ranktonow == 0) ranktonow = rank;
                // else if (ranktonow > rank) ranktonow = rank;
            // }
             
        // }
        
        // if (findall == 0) return -1;
        // else return ranktonow;
    // }
    
    private int yearOfHighestRank(String name, String gender) {
		// TODO Auto-generated method stub
		
		//initial year and rank;
		int rank = 1000000;
		int yearHigh = 0;
		
		//get the directory:
		DirectoryResource dr = new DirectoryResource();
		
		//get the files
		for(File fi : dr.selectedFiles()){
			
			//get the name of the file, which contains the year
			String fileName = fi.getName();
			
			//get the year integer from the name of the file
			int year = Integer.parseInt(fileName.replaceAll("[\\D]", ""));
			
			//get the FileResource
			FileResource fr = new FileResource(fi);
			int currRank = -1;
			int pivot = 0;
			for(CSVRecord record : fr.getCSVParser(false)){
				
				if(record.get(1).equals(gender)) {
					
					pivot++;
								
					if(record.get(0).equals(name)) {
						currRank = pivot;
						break;
					}
					
				}
				
			}//end for loop;
			
			//int currRank = getRank(year, name, gender);
		//	System.out.println("  At year " + year + " name " + name + " gender " + gender + " ranks " + currRank + ". ");
			
			if(currRank != -1 && currRank < rank){
				rank = currRank;
				yearHigh = year;
			}//end if condition;
		
		}//end for File fi loop;
		
		return yearHigh;
	}

    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double ranktonow = 0;
        int findall = 0;
        for (File f: dr.selectedFiles()) {
            int rank = 0;
            int find = 0;
            String fname = f.getName();
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record: parser) {
                if (record.get(1).equals(gender)) {
                    rank += 1;
                    if (record.get(0).equals(name)) {
                        find = 1;
                        break;
                    }
                }
            }
            if (find == 1) {
                findall += 1;
                ranktonow += rank;
            }
             
        }
        
        if (findall == 0) return -1;
        else return ranktonow/findall;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String nameyear = "us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(nameyear);
        CSVParser parser = fr.getCSVParser(false);
        int find = 0;
        int sum = 0;
        for (CSVRecord record: parser) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    find = 1;
                    break;
                }
                sum += Integer.parseInt(record.get(2));
            }
        }
        if (find == 1) return sum;
        else return -1;
    }
       
    public void testTotalBirths (){
    FileResource fr = new FileResource("us_babynames_test/example-small.csv");
    totalBirth(fr);
    }
    
    public void testtotalBirths2() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        totalBirths2(parser);
    }
    
    public void testgetRank(){
     FileResource fr = new FileResource();
     CSVParser parser = fr.getCSVParser(false);
     int rank = getRank(parser, 1971, "Frank", "M");
     System.out.println(rank);
    }
    
     public void testgetName() {
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    
    public void testwhatisNameInYear() {
        whatIsNameInYear("Susan", 1972, 2014, "F");
    }
    
    public void testyearOfHighestRank() {
        int ranktonow = yearOfHighestRank("Mich", "M");
        System.out.println("Highest rank is "+ ranktonow);
    }
    
    public void testgetAverageRank() {
        double average = getAverageRank("Robert", "M");
        System.out.println("Average rank is "+average);
    }
    
    public void testgetTotalBirthsRankedHigher() {
        int sum = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("The total briths higher is "+sum);
    }
}
