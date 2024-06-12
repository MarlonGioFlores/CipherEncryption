package StringsThirdAssignments;
import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

public String mystery(String dna) {
  int pos = dna.indexOf("T");
  int count = 0;
  int startPos = 0;
  String newDna = "";
  if (pos == -1) {
    return dna;
  }
  while (count < 3) {
    count += 1;
    newDna = newDna + dna.substring(startPos,pos);
    startPos = pos+1;
    pos = dna.indexOf("T", startPos);
    if (pos == -1) {
      break;
    }
  }
  newDna = newDna + dna.substring(startPos);
  return newDna;
}

public void testMystery(){
 String dna ="";
 mystery(dna);
 System.out.println("Return myster = " + mystery(dna));
}

    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            } else{
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
   }
    
    public String findGene(String dna){
     int startIndex = dna.indexOf("ATG");
     
       if (startIndex == -1){
         return "No start index found";
       } else {
            
                int stopCodonTAA = findStopCodon(dna,startIndex,"TAA");
                int stopCodonTAG = findStopCodon(dna,startIndex,"TAG");
                int stopCodonTGA = findStopCodon(dna,startIndex,"TGA");
                //int temp = Math.min(stopCodonTAA, stopCodonTAG);
                //int minIndex = Math.min(temp, stopCodonTGA); 
                int minIndex = Math.min(stopCodonTAA, Math.min(stopCodonTAG, stopCodonTGA));
                if(minIndex == dna.length()){
                 return "No Stop Codon found";   
                }
                
            
            return dna.substring(startIndex, minIndex + 3);
       }
       
   }
   
   public String findAllGenes(String dna, int where){
       int startIndex = dna.indexOf("ATG", where);
     //System.out.println(where);
       if (startIndex == -1){
         return "";
       } else {
            
                int stopCodonTAA = findStopCodon(dna,startIndex,"TAA");
                int stopCodonTAG = findStopCodon(dna,startIndex,"TAG");
                int stopCodonTGA = findStopCodon(dna,startIndex,"TGA");
                //int temp = Math.min(stopCodonTAA, stopCodonTAG);
                //int minIndex = Math.min(temp, stopCodonTGA); 
                int minIndex = Math.min(stopCodonTAA, Math.min(stopCodonTAG, stopCodonTGA));
                if(minIndex == dna.length()){
                 return "";   
                }
                //Es una forma distinta de hacer el calculo matematico utilzando condiciones
              // int minIndex = 0;
              //  if(stopCodonTAA == -1 || (stopCodonTAG != -1 && stopCodonTGA< stopCodonTAA)){
              //      minIndex = stopCodonTGA;
              //  }
                
            
            return dna.substring(startIndex, minIndex + 3);
       }
    }

    public void testFindStopCodon(){
        //           01234567890123456789
        String dna ="zzzyyyvvvTAAaaabbbdddTAAaa";
        System.out.println(findStopCodon(dna,0,"TAA"));
        dna ="zzzTGAvvvTAAaaabbbdddTAAaa";
        System.out.println(findStopCodon(dna,0,"TAA"));
        dna ="zzzTGAvvvAAAaaabbbdddAAAaa";
        System.out.println(findStopCodon(dna,0,"TAA"));
       // int dex = findStopCodon(dna,0,"TAA");
       // if (dex != 9) System.out.println("error on 9");
       // System.out.println("tests finished");
    }
    
    public void testFindGene(){
        String dna ="zzzyyyvvvATGaaabbbdddTAAaa";
        System.out.println(findGene(dna));
        dna ="zzzyyyvvvTAAaaabbbdddTAAaa";
        System.out.println(findGene(dna));
        dna ="zzzyyyvvvATGaaabbbTGATAAaa";
        System.out.println(findGene(dna));
        dna ="zzzyyyvvvATGaaabbbTGATAAaaaATG";
        System.out.println(findGene(dna));
        dna ="zzzyyyvvvATGaaabbbbbbcccaaaTAG";
        System.out.println(findGene(dna));
        dna ="zzzyyyvvvATGaaabbbbbbcccaaaATG";
        System.out.println(findGene(dna));
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while (true) {
            //Encuentra el siguiente gen
            // count = count + 1;
           
            String currentGene = findAllGenes(dna,startIndex);
            //Sino encuentra gen debe salir del loop
            if (currentGene.isEmpty()) {
                break;
            }
            //System.out.println("Start Index ");
            //System.out.println(startIndex);
            //System.out.println("DNA IndexOF " + dna.indexOf(currentGene, startIndex));
                                    
            //Imprime el gen encontrado
            //System.out.println("Current Gene Length" + currentGene.length());
            System.out.println(currentGene);
            
            //Actualizamos la variable startIndex 
            startIndex = dna.indexOf(currentGene, startIndex) + 
                                    currentGene.length();
          // System.out.println(startIndex);
          //System.out.println(dna.indexOf(currentGene, startIndex) + currentGene.length());
          // System.out.println(currentGene);
          //System.out.println(currentGene.length());
        }
    
    }
    
    public StorageResource getAllGenes(String dna){
        //create an empty StorageResource.
        StorageResource geneList = new StorageResource();        
        int startIndex = 0;
        int count = 0;
        while (true) {
            //Encuentra el siguiente gen
            // count = count + 1;
           
            String currentGene = findAllGenes(dna,startIndex);
            //Sino encuentra gen debe salir del loop
            if (currentGene.isEmpty()) {
                break;
            }
            //System.out.println("Start Index ");
            //System.out.println(startIndex);
            //System.out.println("DNA IndexOF " + dna.indexOf(currentGene, startIndex));
                                    
            //Imprime el gen encontrado
            //System.out.println("Current Gene Length" + currentGene.length());
            System.out.println(currentGene);
            //Store gene
            geneList.add(currentGene);
            
            //Actualizamos la variable startIndex 
            startIndex = dna.indexOf(currentGene, startIndex) + 
                                    currentGene.length();
          // System.out.println(startIndex);
          //System.out.println(dna.indexOf(currentGene, startIndex) + currentGene.length());
          // System.out.println(currentGene);
          //System.out.println(currentGene.length());
        }   
        return geneList;
    }
    
    public double cgRatio (String dna){
       // StorageResource geneList = new StorageResource();
        int startIndex = 0;
        String stringA = "C";
        String stringB = "G";
        int ocurrC = dna.indexOf(stringA);
        int ocurrG = dna.indexOf(stringB);
        double count = 0.0;
        double av = 0.0;
        //int findC = dna.indexOf("C");
        //int findG = dna.indexOf("G");
        //System.out.println("C " + findC);
        //System.out.println("G " + findG);
        
         if(ocurrC != -1 || ocurrG != -1 ){
                    
            //count = count + 1;
           // System.out.println(count);
            //ocurrC = dna.indexOf("C" + 1 );
            //System.out.println("C " + ocurrC);
            //System.out.println("G " + ocurrG);
           // (dna.indexOf("C", ocurrC) != -1 && ocurrC != -1) &&
           // (dna.indexOf("G", ocurrG) != -1 && ocurrG != -1)
            while(  ocurrG != -1 ){
                count = count +1;
                 System.out.println("count del while " + count);
                
                ocurrG = dna.indexOf(stringB, ocurrG + stringB.length());
                
               // System.out.println("G " + ocurrG);
            }
            while ( ocurrC != -1){
                count = count +1;
                System.out.println("count del while " + count);
                ocurrC = dna.indexOf(stringA, ocurrC + stringA.length());
                // System.out.println("C " + ocurrC);
            }
            
         }
           
         //System.out.println("average1 " + av);
          av = count / dna.length();
         //System.out.println("average2 " + av);
       
        return av;
    }
    
    public void  processGenes(StorageResource sr){
        StorageResource printGenes = new StorageResource(sr);
        while (true){
            System.out.println("sr " + sr);
        }
      
    }
    public void testProcessGenes(){
         FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
    }
    public void testOn(String dna){
     //System.out.println("Testing printAllGenes on " + dna);
     // printAllGenes(dna);
     //StorageResource genes = getAllGenes(dna);
     //StorageResource genes = cgRatio(dna);
     //System.out.println(genes);
     // for (String g: genes.data()) {
     //  System.out.println(g);
     //}
   System.out.println("Result Average= " + cgRatio(dna));
}
    
    public void test(){
     //String dna = ("ATGATCTAATTTATGCTGCAACGGTGAAGA");
     // printAllGenes(dna);
      testOn("ATGCCATAG");
      testOn("");
      //testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
   }
}


