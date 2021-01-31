/*  Name: Allison Chen
 *  PennKey: allchen
 *  Recitation: 209
 *
 *  Execution: java Race
 *
 *  Animates race between Penn and Princeton
 *
 */

public class Race {    
    public static void main(String[] args) {
    	
        boolean pennWins = false;       // has Penn won the race?
        boolean princetonWins = false;  // has Princeton won the race?
        
        // the width of 1 pixel in window coordinates,
        // assuming you do NOT call PennDraw.setXscale()
        double ONE_PIXEL = 1.0 / 512;   

        // set the pennant locations before the starting line
        double startLocation = 0.00;
        
        double pennLocation = startLocation;
        double princetonLocation = startLocation;
    
        // enable animation at 10 frames/second
        PennDraw.enableAnimation(50.0);

        while (!pennWins && !princetonWins) {
        	
        	// clears the screen
        	PennDraw.clear(PennDraw.WHITE);
        	
        	// draw start and end line
            PennDraw.setFontSize(12);
            
            PennDraw.text(0.15, 0.97, "start");
            PennDraw.line(0.15, 0, 0.15, 0.95); 
            
            PennDraw.text(.8, 0.97, "end");
            PennDraw.line(.8, 0, .8, 0.95);
            
            // draw the Penn and Princeton pennants
            PennDraw.picture(pennLocation, 0.2, "penn.png");
            PennDraw.picture(princetonLocation, 0.8, "princeton.png");
        
            // determine whether the Penn pennant position changes
        	if (Math.random() <= .62) {
        		pennLocation += ONE_PIXEL;
        	} else {
        		pennLocation += 0.0;
        	}
        	
        	// determine whether the Princeton pennant position changes
        	if (Math.random() <= .6) {
        		princetonLocation += ONE_PIXEL;
        	} else {
        		princetonLocation += 0.0;
        	}
        	System.out.println ("penn  " + pennLocation);
    		System.out.println ("princeton  " + princetonLocation);

        	// based on current positions, determine if anyone has won.
        	if (pennLocation > .8) {
        		pennWins = true;
        	} else if (princetonLocation > .8) {
        		princetonWins = true;
        	}

            PennDraw.advance(); // show this frame and go on to the next one
        }

        PennDraw.disableAnimation(); // the race is over so turn off animation
        	
        	if (pennWins) {
        		PennDraw.text (.5, .5, "Penn Wins!");
        	} else if (princetonWins) {
        		PennDraw.text (.5, .5, "Princeton Wins!");
        	}
    }
}