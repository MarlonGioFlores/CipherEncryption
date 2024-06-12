package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
          int numPoints = 0;
          for(Point a : s.getPoints()){
              //Cuenta la cantidad de veces que entro el punto
              numPoints = numPoints + 1;
              
            }
        
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double avLen = length / points;
        
        
        return avLen;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double longDistance = 0;
        Point previousPt = s.getLastPoint();
        //For each point currentPoint in the shape
        for(Point currPt : s.getPoints()){
            //Find distance from previous point to current point
            double currentDist = previousPt.distance(currPt);
            if(currentDist > longDistance){
                longDistance = currentDist;
                
            }
            previousPt = currPt;
        }
        
        return longDistance;
    }

    public double getLargestX(Shape s) {
        // Put code here
        
        Point previousPt = s.getLastPoint();
        int lastPointX = previousPt.getX();
        double largestX = lastPointX;
        //For each point currentPoint in the shape
        for(Point p : s.getPoints()){
         int newX = p.getX();
         if(newX > largestX){
             largestX = newX;
            }
        }
       
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
       double largestPerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource file = new FileResource(f);
            Shape s = new Shape(file);
            double perim = getPerimeter(s);
            if(perim>largestPerim){
                largestPerim = perim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File largestFile = null;  
        DirectoryResource dr = new DirectoryResource();
       double largestPerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource file = new FileResource(f);
            Shape s = new Shape(file);
            double perim = getPerimeter(s);
            if(perim>largestPerim){
                largestPerim = perim;
                largestFile = f;
            }
        }
        return largestFile.getName(); 
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int totalPoints = getNumPoints(s);
        System.out.println("total points = " + totalPoints);
        double resultLen = getAverageLength(s);
        System.out.println("Average Length = " + resultLen);
        double longDistance = getLargestSide(s);
        System.out.println("Largest Side = " + longDistance);
        double largestX = getLargestX(s);
        System.out.println("Largest X = " + largestX);
       // testPerimeterMultipleFiles();
       // testFileWithLargestPerimeter();
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largest  = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is: " + largest);
       
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String file = getFileWithLargestPerimeter();
        System.out.println("Largest perimeter file is: " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
