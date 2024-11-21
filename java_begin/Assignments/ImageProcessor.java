import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 

public class ImageProcessor {
   static int processPixel(int p, String type) {
      
      // add your code here
      // unpack values from the pixel
      // process pixel based on type
      // pack values back into the pixel
      
      return p;
   }
   
   static void processImage(String inFilename, String outFilename, String type) {
      BufferedImage img = null;
      File f = null;
      
      // load image
      try {
         f = new File(inFilename);
         img = ImageIO.read(f);
      }
      catch(IOException e) {
         System.out.println(e);
      }
      
      // get image width and height
      int width = img.getWidth();
      int height = img.getHeight();
      
      // process all pixels in the image
      for(int x = 0; x < width; x++) {
         for(int y = 0; y < height; y++) {
            int p = img.getRGB(x,y);
            p = processPixel(p, type);
            img.setRGB(x, y, p);
         }
      }
      
      // write image to file
      try {
         f = new File(outFilename);
         ImageIO.write(img, "jpg", f);
      }
      catch(IOException e) {
         System.out.println(e);
      }
   }

   public static void main(String args[]) {
      processImage("Taj.jpg", "Taj_negative.jpg", "negative");
      processImage("Taj.jpg", "Taj_sepia.jpg", "sepia");
      processImage("Taj.jpg", "Taj_grayscale.jpg", "grayscale");
   }
}
