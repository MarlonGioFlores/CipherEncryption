package StringsThirdAssignments;
import edu.duke.*;
import java.io.*;


/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
        int num = 0;
        while (true) {
            //Encuentra el siguiente gen
             //count = count + 1;
            //num = num + 1;
            //System.out.println("numero de vueltas " + count);
            String currentGene = findAllGenes(dna,startIndex);
            //Sino encuentra gen debe salir del loop
            if (currentGene.isEmpty()) {
                break;
            }
            
            //System.out.println("DNA IndexOF " + dna.indexOf(currentGene, startIndex));
                                    
            //Imprime el gen encontrado
            //System.out.println("Current Gene Length" + currentGene.length());
           // System.out.println(currentGene);
            //Store gene
            geneList.add(currentGene);
            
            //Actualizamos la variable startIndex 
            startIndex = dna.indexOf(currentGene, startIndex) + 
                                    currentGene.length();
          
        }   
        //System.out.println("geneList " + geneList);
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
           // (dna.indexOf("C", ocurrC) != -1 && ocurrC != -1) &&
           // (dna.indexOf("G", ocurrG) != -1 && ocurrG != -1)
            while(  ocurrG != -1 ){
                count = count +1;
                 //System.out.println("count del while " + count);
                
                ocurrG = dna.indexOf(stringB, ocurrG + stringB.length());
                
            }
            while ( ocurrC != -1){
                count = count +1;
               // System.out.println("count del while " + count);
                ocurrC = dna.indexOf(stringA, ocurrC + stringA.length());
                // System.out.println("C " + ocurrC);
            }
            
         }
         av = count / dna.length();
               
        return av;
    }
    
    public int howMany (String stringa, String stringb){
       int count = 0;
        int firstOccur = stringb.indexOf(stringa);
        if (firstOccur != -1) {
            //System.out.println(count);
            count = count + 1;
        
        
        while (stringb.indexOf(stringa, firstOccur) != -1 && firstOccur != -1) {
            count = count + 1;
            
            firstOccur = stringb.indexOf(stringa, firstOccur + stringa.length());
        }
       // System.out.println(count);
        count = count - 1;
       // System.out.println(count);
        }
        else {
            count= 0;
        }
        return count;
    }
    
     public StorageResource srIsFile(String a){
        FileResource fr = new FileResource(a);
        String Newdna = fr.asString();
        StorageResource sr = new StorageResource();
        sr.add(Newdna);
        StorageResource sr2 = new StorageResource();
      
        
        for(String s : sr.data()){
            System.out.println("this is my list of genes: " + s);
            //System.out.println("sr2 genes: " + sr2);
          sr2 = getAllGenes(s);
          
          for(String s2 : sr.data()){
              System.out.println("this is S2: " + s2);
              
            
            }
           //System.out.println("sr2s genes: " + sr2);
           
        }
        
        
        return sr2;
    }
    
    public void  processGenes(StorageResource sr){
        int count = 0 ;
        int counting  = 0;
        int contadorGenes = 0;
        for(String s1 : sr.data()){
         System.out.println("numero de genes: " + s1);   
         contadorGenes = contadorGenes + 1;
         System.out.println("Contador de genes: " + contadorGenes);
        }
        
        for(String s : sr.data()){            
           // System.out.println("this is my list of genes: " + s);
           if(s.length() > 6){                
               System.out.println("S length " + s.length());
               System.out.println("Longer than 9 " + s);
               //System.out.println("Number of genes longer than 9 " + count);
            }
        }
        
        for (String over9 : sr.data()){
         if(over9.length() > 6){
             count = count + 1;
            }
        }
        System.out.println("Number of genes longer than 9 " + count);
        
        for(String cgRat : sr.data()){
            cgRatio(cgRat);
         if(cgRatio(cgRat) > 0.35 ){
             System.out.println("Genes with C-G Ratio higher than 0.35 = " + cgRat + " and the ratio is= " + cgRatio(cgRat)) ;
            }
        }
        
        for (String overCGRat : sr.data()){
            cgRatio(overCGRat);
           if(cgRatio(overCGRat) > 0.35){
               //count = count + 1;
               //System.out.println("Numer of Genes with ratio over 0.35= " + count);
            }
        }
        System.out.println("Numer of Genes with ratio over 0.35= " + count);
        
        for (String longest : sr.data()){
            if(longest.length() > counting){
            counting = longest.length();
            }
        }
        System.out.println("Length of the longest gene= " + counting);
        
       // for (String howMany2 : sr.data()){
        // howMany2 = howMany(sr,"CTG");   
        //}
   
    }
    
    
    
    
     public String returnName(){
        String name = "GRch38dnapart.fa";
        return name;
        
    }
    
    public void testProcessGenes(){
        //StorageResource sr = new StorageResource();
       // sr.add("ATGCCCCGGTAA");
       // sr.add("ATGCCCGGGTTTTTTTTTTTTTTTTAA");
       // sr.add("ATGCCCTTTTTTTTTTTTTAA");
       // sr.add("ATGTAA");
        
        processGenes(srIsFile(returnName()));
        
    }
    
        public void testOn(String dna){
     //System.out.println("Testing printAllGenes on " + dna);
     // printAllGenes(dna);
    // StorageResource genes = getAllGenes(dna);
     //StorageResource genes = cgRatio(dna);
     //System.out.println(genes);
     // for (String g: genes.data()) {
     //  System.out.println(g);
     //}
  // System.out.println("Result Average= " + cgRatio(dna));
}
    
    public void test(){
    //String dna = ("ATGATCTAATTTATGCTGCAACGGTGAAGA");
   // printAllGenes(dna);
      testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
      testOn("");
      
      //testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
