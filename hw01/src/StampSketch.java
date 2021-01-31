/*  Name: Allison Chen
 *  PennKey: allchen
 *  Recitation: 209
 *
 *  Execution: java StampSketch
 *
 *  Randomized sketch of images scaled in size according to distance from horizon
 *
 */
public class StampSketch {
    public static void main(String[] args) {
    	
    	PennDraw.clear(PennDraw.BLACK); 
    	PennDraw.setPenColor(181, 226, 242);
        PennDraw.filledRectangle(0.5, 0.25, 0.5, 0.25);

        int stars = 0;
        int waves = 0;
		
        // drawing random colored, sized, and located stars in the sky
        while (stars < 100) {
        	
        	double starLocationX = Math.random();
            double starLocationY = (.5 * Math.random())+ .5;
            double starSize = (Math.random() + .1) * .01;
            
            int red = (int)(256 * Math.random());
    		int green = (int) (256 * Math.random());
    		int blue = (int) (256 * Math.random());
    		
        	PennDraw.setPenColor(red, green, blue);
        	PennDraw.filledSquare(starLocationX, starLocationY, starSize/2, 30);
        	
        	stars++;
        }
       
        //drawing randomly located waves
        while (waves < 100) {
        	
        	double waveLocationX = Math.random();
            double waveLocationY = (.5 * Math.random()) - .05;

            PennDraw.setPenColor (173, 220, 236);
            PennDraw.filledArc(waveLocationX, waveLocationY, .05, 10, 170);
            
            waves++;
        }
        
        
        // is this the first time we are drawing the background?
        boolean firstTime = true;

        //resets picture and randomizes again when key is pressed
        while (true) {
            // if a key is pressed, redraw background
        	if (PennDraw.hasNextKeyTyped()) {
        		PennDraw.clear(PennDraw.BLACK); 
        		PennDraw.setPenColor(181, 226, 242);
        		PennDraw.filledRectangle(0.5, 0.25, 0.5, 0.25);
        		
        		stars = 0;
        		
                while (stars < 100) {
                	
                	double starLocationX = Math.random();
                    double starLocationY = (.5 * Math.random())+ .5;
                    double starSize = (Math.random() + .1) * .01;
                    
                    int red = (int)(256 * Math.random());
            		int green = (int) (256 * Math.random());
            		int blue = (int) (256 * Math.random());
     
                	PennDraw.setPenColor(red, green, blue);
                	PennDraw.filledSquare(starLocationX, starLocationY, starSize/2, 30);
                	
                	stars++;               	
                }
                
                waves = 0;
                
                while (waves < 100) {
                	
                	double waveLocationX = Math.random();
                    double waveLocationY = (.5 * Math.random()) - .05;

                    PennDraw.setPenColor (173, 220, 236);
                    PennDraw.filledArc(waveLocationX, waveLocationY, .05, 10, 170);
                    
                    waves++;
                }
                
                PennDraw.nextKeyTyped();
                firstTime = false;
           }

           // if the mouse is clicked
           if (PennDraw.mousePressed()) {
                // get the coordinates of the mouse cursor
                double x = PennDraw.mouseX();
                double y = PennDraw.mouseY();
                
                //factor we multiply the original size of images to be
                double pictureSizeFactor = (.5 - y);
                
                //check which region the mouse click was in
                if (y ==.5) {
                	PennDraw.picture(x, y, "lightning.png", 7, 8);
                	PennDraw.picture(x, y, "boat.png", 7, 8);
                	
                } else if (y > .5) {
                	PennDraw.picture(x, y, "lightning.png", 800 * (-pictureSizeFactor), 900 * (-pictureSizeFactor));
                	PennDraw.picture(x, 1 - y, "boat.png", 800 * (-pictureSizeFactor), 900 * (-pictureSizeFactor));
                
                } else {
                	PennDraw.picture(x, 1 - y, "lightning.png", 800 * (pictureSizeFactor), 900 * (pictureSizeFactor));
                	PennDraw.picture(x, y, "boat.png", 800 * (pictureSizeFactor), 900 * (pictureSizeFactor));
                }
          }      
       }
	}
    
}
    