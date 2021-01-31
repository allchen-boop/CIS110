 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  A class that represents the bird in Irate Avians.
  *  Can update its own position based on velocity and time.
  *  Can check whether it overlaps a given Target.
  *
  */

public class Bird {

    // position, velocity, and radius members of the bird.
    private double xPos, yPos, xVel, yVel, radius;

   /*
    * how many more times the
    * player can throw the bird
    * before losing the game.
    */
    private int numThrowsRemaining;

   /*
    * Description: constructor that initializes
    *              the bird's member variables
    *              bird's velocity initialize to 0.
    *              when the target moves offscreen
    *              it will wrap around to the opposite side.
    * Input:       parameters of bird including:
    *              bird's x and y position
    *              bird's radius
    *              the number of throws the bird has
    * Output:      n/a constructor                
    */
    public Bird(double xPos, double yPos, double radius, int numThrows) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        numThrowsRemaining = numThrows;
        xVel = 0;
        yVel = 0;
    }

   /*
    * Description: draws a blue bird
    *              with a beak and two eyes.
    *              above the birds draws
    *              its remaining throws.
    * Input:       none
    * Output:      n/a void method                  
    */
    public void draw() {

        PennDraw.setPenColor(142, 210, 255);
        PennDraw.filledCircle(xPos, yPos, radius);

        PennDraw.setPenColor(243, 190, 43);
        PennDraw.filledPolygon(xPos + .35, yPos, xPos + .15, yPos - .12,
                               xPos + .15, yPos + .12);

        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.filledCircle(xPos - .09, yPos + .09, .04);
        PennDraw.filledCircle(xPos + .09, yPos + .09, .04);

        PennDraw.text(xPos, yPos + radius + .1, numThrowsRemaining + "");
    }

   /*
    * Description: draws the line representing
    *              the bird's initial velocity
    *              when the player clicks and drags.
    * Input:       none
    * Output:      n/a void method                 
    */
    public void drawVelocity() {
        PennDraw.line(xPos, xPos, xPos + xVel, yPos + yVel);
    }

   /*
    * Description: resets the bird's
    *              x position to 1.0
    *              y position to 0
    *              and it clears the list of
    *              targets hit this launch.
    * Input:       none
    * Output:      n/a void method                  
    */
    public void reset() {
        xPos = yPos = 1.0;
        xVel = yVel = 0.0;
    }

   /*
    * Description: computes the bird's initial velocity.
    *              this is from the player dragging and
    *              this will update the launch velocity.
    * Input:       none
    * Output:      n/a void method              
    */
    public void setVelocityFromMousePos() {
        xVel = xPos - PennDraw.mouseX();
        yVel = yPos - PennDraw.mouseY();
    }

   /*
    * Description: updates the bird's new
    *              position and velocity
    * Input:       the double time for each update
    * Output:      n/a void method
    */
    public void update(double timeStep) {
        xPos += xVel * timeStep;
        yPos += yVel * timeStep;
        //to represent gravity dragging the bird down over time
        yVel -= 0.25 * timeStep;
    }

   /*
    * Description: helper function to find the
    *              distance between two points
    * Input:       the two sets of double x and y coordinates
    * Output:      the double value representing
    *              the distance between these points
    */
    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }

   /*
    * Description: when given a target it determines
    *              if the bird should test for collision.
    *              if it should then it will perform the test.
    *              if the bird collides, the bird and target will
    *              bounce off each other, mimicking a real collision.
    *              additionally, the target's HP will
    *              decrease and this target will be added to 
    *              the bird's list of targets to hit.
    * Input:       a target we are testing for collision with the bird
    * Output:      n/a void method     
    */
    public void testAndHandleCollision(Target t) {
        if (t.getHitPoints() > 0) {

            double dist = distance(xPos, yPos, t.getXpos(), t.getYpos());

            if (dist <= (radius + t.getRadius())) {
                //dot product
                double dot = ((t.getXpos() - xPos) * xVel) +
                             ((t.getYpos() - yPos) * yVel);

                //bird bounce (using vector reflection formula)
                if (dot > 0) {
                    xVel = xVel - 2 * dot * dist;
                    yVel = yVel - 2 * dot * dist;
                }

                //for target to bounce in opposite direction of bird after collision
                t.setVel(-xVel, -yVel); 

                if (t.isHit() == false) {
                    t.setHitThisShot(true);
                }
             }
         }
    }

   /*
    * Description: reduces the number of throws
    *              remaining for the bird by 1.
    * 
    */
    public void decrementThrows() {
        numThrowsRemaining--;
    }

   /*
    * Description: getter functions for:
    *              bird x position
    *              bird y position
    *              bird radius
    *              bird throws remaining
    * Output:      will return copy of 
    *              indicated member variable
    */
    public double getXpos() { 
        return xPos; 
    }
    public double getYpos() { 
        return yPos; 
    }
    public double getRadius() { 
        return radius; 
    }
    public int getNumThrowsRemaining() { 
        return numThrowsRemaining; 
    }
}