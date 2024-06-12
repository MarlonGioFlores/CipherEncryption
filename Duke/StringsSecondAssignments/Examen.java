package StringsSecondAssignments;


/**
 * Write a description of Examen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Examen {
    public void findAbc(String input) {
    int index = input.indexOf("abc");
   // System.out.println("b " + index);
        while (true) {
           if (index == -1) {
            break;
           }
        if(index > input.length() - 3){
    
        String found = input.substring(index+1, index+4);
        //System.out.println("e " + (index +1));
        //System.out.println("e " + (index +4));
        //System.out.println("a " + input.indexOf((index+1), (index+4)));
        //System.out.println("a " + found);
        System.out.println(found);
        index = input.indexOf("abc", index + 4);
       
       // System.out.println("f " + index);
        //System.out.println("f " + (index+4));
       // System.out.println("c " + input.indexOf((index+1), (index+4)));
        
        }
       }
   }
   
   public void test() {
    //no code yet
    //findAbc("abcd");
    findAbc("abcbbbabcdddabc");
   }
}
