package BabyNames;

import edu.duke.*;
import java.io.*;


/**
 * Write a description of GrayScale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrayScale {
    
    public ImageResource makeGray(ImageResource inImage) {
        //Made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //For each pixel in outImage
        for(Pixel pixel : outImage.pixels()) {
            //look at te corresponding pixel in inImage
         Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY()); 
         //compute inPixel`s red + inPixel`s blue + inPixel`s green
         //divide that sum by 3 (call it average)
         int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
         //set pixel`s red to average
         pixel.setRed(average);
         //set pixel`s green to average
         pixel.setGreen(average);
         //set pixel`s blue to average
         pixel.setBlue(average);
        }
        
        return outImage;
    }
    
    public ImageResource invertPixels(ImageResource inImage){        
        //make a blank image the size of inImage
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());        
        //for each pixel in the inImage, invert the RGB values 255-x        
        for(Pixel pixel : outImage.pixels()){            
            //check the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());            
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());            
            }
        
        return outImage;
        
    }   
    
    public void selectAndInvert(){		
		//select the folder with images
		DirectoryResource dr = new DirectoryResource();		
		//for loop iterate all selected files/images
		for(File inFile : dr.selectedFiles()){			
			//turn the selected file into image
			ImageResource inImage = new ImageResource(inFile);			
			//pick the original name add "inver-" in front, which is the new name
			String oriName = inImage.getFileName();
			String newName = "Inverted-" + oriName;
					
			ImageResource outImage = invertPixels(inImage);			
			//set new name, draw the new image, and save
			outImage.setFileName(newName);
			outImage.draw();			
			outImage.save();
			//outImage.saveAs();
			
		}//end for loop;
		
	}
	
    public void testGray() {
     DirectoryResource dr =new DirectoryResource();
         for (File f : dr.selectedFiles()){
             ImageResource image = new ImageResource(f);
             ImageResource gray = makeGray(image);
             String fname = image.getFileName();
             String newName = "gray-" + fname;
             gray.setFileName(newName);
             gray.draw();
             gray.save();
         }
    }
    
    public void testInvert(){        
        //user pick the image 
        ImageResource oriImage = new ImageResource();
        ImageResource invImage = invertPixels(oriImage);        
        invImage.draw();
        invImage.saveAs();
        
    }

}
