/*
 * Mounika Muga for UnifyID Random.org Full stack Developer Challenge
 */
package ImageGenerator;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;

public class GenerateImages {
	public static void main(String[] args) throws Exception
	   {
		  int Imgwidth = 128; //Image Width
	      int Imgheight = 128; //Image Height
	      BufferedImage img = new BufferedImage(Imgwidth, Imgheight, BufferedImage.TYPE_INT_ARGB);
	      File f = null;
	      String j;
		  StringBuilder result = new StringBuilder();
		  /* The URL contains the request for 1400 random values within the range of 0-256.
		   * This is the RGB range for generating the Image.
		   * Through the HttpURLConnection, we ar esetting the connection and issuing the GET request to the Random.org API
		   */
	      URL url = new URL("https://www.random.org/integers/?num=1400&min=0&max=256&col=1&base=10&format=plain&rnd=new");
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      ArrayList<Integer> ar=new ArrayList<>();
	      while ((j = rd.readLine()) != null) {
	         ar.add(Integer.parseInt(j));
	    	  result.append(j);
	      }
	      int count = ar.size();
	      System.out.println("Count: " + count);
	      rd.close();
	      int i=0;
	      /*
	       * At this point, pixel values are being assigned to create the bitmap image,
	       * Once the counter i reaches value 1400, we reset it to 0, so that the rest of the image is built
	       * When a larger amount of numbers are being accessed, the Random org API throws a 503 error due to 
	       * which I have chosen this approach
	       */
	      for(int y = 0; y < Imgheight; y++){
	        for(int x = 0; x < Imgwidth; x++){
	          int alpha = ar.get(i); 
	          i++;
	          int red = ar.get(i); 
	          i++;
	          int green = ar.get(i); 
	          i++;
	          int blue = ar.get(i); 
	          i++;
	          int p = (alpha<<24) | (red<<16) | (green<<8) | blue; 
	          img.setRGB(x, y, p);
	          if(i==1400)
	          {
	        	  i=0;
	          }
	        }
	      }
	      /*
	       * Now, this image is being created in the 128 * 128 size
	       */
	      try{
	        f = new File("MyImage.png");
	        ImageIO.write(img, "png", f);
	      }catch(IOException e){
	        System.out.println("Error: " + e);
	      }
	      
	   }
}