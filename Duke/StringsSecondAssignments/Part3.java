package StringsSecondAssignments;


/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    public String findGene(String dna ){
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
    
    public int countGenes(String dna){
        int count = 0;
        int startIndex = 0;
        int firstOccurrence = dna.indexOf(findAllGenes(dna,startIndex));
        String gene = findAllGenes(dna,firstOccurrence);
        System.out.println(dna);
        
        if(firstOccurrence != -1){
           // count = count +1 ;
            //System.out.println(count);
             System.out.println("First gene: " + firstOccurrence + " length is: " + gene.length());
             System.out.println("Complet gen: " + gene);
            //while(dna.indexOf(gene, firstOccurrence) != -1 && firstOccurrence != -1){
              while(gene.length() != 0 ){
             count = count + 1;   
             //System.out.println("antes " + dna.indexOf(gene, firstOccurrence));               
             firstOccurrence = dna.indexOf(gene, firstOccurrence) + gene.length();
                          
             gene = findAllGenes(dna,firstOccurrence) ;
             //System.out.println("despues " +  dna.indexOf(gene, firstOccurrence));  
              if (gene.isEmpty()){
                            break;
              }
                      
             System.out.println("next gene: " + firstOccurrence + " length is: " + gene.length());
             System.out.println("Complet gen: " + gene);
            }
            
        } else{
            count = 0;
        }
        
        return count;
        
    }
    
    public void testCountGene(){
          String dna= "AATGCTAACTAGCTGACTAAT";
         int count = countGenes(dna);
        System.out.println("I should find " + count + "  and found these amount of genes: " + count);
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
    
    public void printAllGenes(String dna){
            int startIndex = 0;
            int count = 0;
            while (true) {
                //Encuentra el siguiente gen
                // count = count + 1;
                // if(count > 5){
                //     break;}
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
}
