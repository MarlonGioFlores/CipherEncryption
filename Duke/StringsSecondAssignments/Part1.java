package StringsSecondAssignments;


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
    
    public void testOn(String dna){
     System.out.println("Testing printAllGenes on " + dna);
     printAllGenes(dna);
    }
    
    public void test(){
    //String dna = ("ATGATCTAATTTATGCTGCAACGGTGAAGA");
   // printAllGenes(dna);
      testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
      testOn("");
      testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
