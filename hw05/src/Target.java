 /*  Name: Allison
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  A class that represents a target in Irate Avians.
  *  Update its own position based on velocity and time.
  */


public class Target {

    // variables for width and height of screen (10, 5)
    private double width, height;

    // position and radius
    private double xPos, yPos, radius;

    // velocity components
    private double xVel, yVel;

    private int hitPoints;

    // to track if target has been hit this shot
    private boolean hitThisShot;

   /*
    * Description: constructor method to construct a Target
    * Input:       parameters of a Target including:
    *              width, height, x position, y position,
    *              radius, velocity components, and number
    *              of hit points of the target.
    * Output:      n/a constructor method                  
    */
    public Target(double width, double height, double xPos, double yPos, 
                  double radius, double xVel, double yVel, int hitPoints) {
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.xVel = xVel;
        this.yVel = yVel;
        this.hitPoints = hitPoints;
        hitThisShot = false;
    }

   /*
    * Description: draws the target if it has not
    *              been completely hit (more than 0 hit points).
    *              target will look like a bullseye.
    * Input:       none
    * Output:      n/a void method               
    */
    public void draw() {

        if (hitPoints > 0) { 
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.filledCircle(xPos, yPos, radius);

            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledCircle(xPos, yPos, radius * .75);

            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.filledCircle(xPos, yPos, radius * .5);

            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledCircle(xPos, yPos, radius * .25);

            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.text(xPos, yPos, hitPoints + "");
        }
   }

   /*
    * Description: method to update the target's position
    *              based on its x and y velocity.
    *              when the target moves offscreen
    *              it will wrap around to the opposite side.
    * Input:       double value elapsed time for each update
    * Output:      n/a void method  
    */
    public void update(double timeStep) {
        //updating the x and y position
        xPos += xVel * timeStep;
        yPos += yVel * timeStep;

        //check if target is completely off screen to the right or left
        if (xPos + radius < 0 || xPos - radius > width) {
            xPos = -(xPos - width);
        }

        //check if target is completely off screen to top or bottom
        if (yPos + radius < 0 || yPos - radius > height) {
            yPos = -(yPos - height);
        }
    }

   /*
    * Description: every time the target is hit
    *              its hit points will decrease by
    *              the magnitude of the target's
    *              velocity multiplied by a factor of 10
    *              faster moving targets are harder to hit so 
    *              when hit, it we decreas by more HP.
    * Input:       none
    * Output:      n/a void method  
    */
    public void decreaseHP() {
        radius += .05;
        hitPoints -= 10 * Math.sqrt((xVel * xVel) + (yVel * yVel));
    }

   /*
    * Description: setter function for
    *              whether or not target hit this round.
    * Input:       boolean value to pass to 
    *              boolean tracking if target
    *              has been hit this shot.
    * Output:      n/a void method                
    */
    public void setHitThisShot(boolean hit) {
        hitThisShot = hit;
    }

   /*
    * Description: will tell whether or not
    *              this target is hit this round.
    *              whether or not target hit this round.
    * Input:       none
    * Output:      true if target was hit;
    *              false otherwise
    */
    public boolean isHit() {
        return hitThisShot;
    }

   /*
    * Description: setter functions that sets
    *              the velocity of the target
    *              to parameters
    * Input:       double x and y velocity components
    *              we want to set target velocity as
    * Output:      n/a void method  
    */
    public void setVel(double xVel, double yVel) {
        this.xVel = xVel; 
        this.yVel = yVel;
    }

   /*
    * Description: getter functions for:
    *              target hit points
    *              target x position
    *              target y position
    *              target radius
    * Input:       none
    * Output:      will return copy of 
    *              indicated member variable
    */
    public int getHitPoints() { 
        return hitPoints; 
    }
    public double getXpos() { 
        return xPos;
    }
    public double getYpos() {
        return yPos;
    }
    public double getRadius() { 
        return radius; 
    }
}