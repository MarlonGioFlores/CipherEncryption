package StringsSecondAssignments;


/**
 * Write a description of debbug2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class debbug2 {
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           String found = input.substring(index+1, index+4);
           System.out.println("before " + index);
           System.out.println(found);
           System.out.println("index after updating " + index);
           index = input.indexOf("abc",index+3);
           System.out.println("index after updating " + index);
       }
   }

   public void test(){
       //findAbc("abcd");
       findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       //findAbc("abcabcabcabca");
      // findAbc("ttabcesoeiabco");
       
   }
}
