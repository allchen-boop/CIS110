 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  A class representing the arena Irate Avians takes place in. 
  *  Keeps track of the game's Bird and Targets and 
  *  receives user's input to control the bird.
  *
  */

public class Arena {
    //width and height of the PennDraw screen (10, 5)
    private int width, height;

    //array of all the targets in the Arena
    private Target[] targets;

    //one bird in the game
    private Bird bird;

   /*
    * whether the game is currently listening for
    * the player's mouse input or letting the bird fly.
    */
    private boolean mouseListeningMode;

   /*
    * tells if user was pressing
    * the mouse in the previous update call.
    * enables the game to transition from the mouse listening
    * to bird flight state.
    */
    private boolean mouseWasPressedLastUpdate;

   /*
    * Description: constructor that takes in file
    *              and initializes all member variables of Arena.
    *              soap bar. Recursive call draws progressively
    * Input:       String name of txt file
    */
    public Arena(String filename) {
        In in = new In(filename);

        mouseListeningMode = true;
        mouseWasPressedLastUpdate = false;

        int numTargets = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
        int numThrows = in.readInt(); 

        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);

        bird = new Bird(1, 1, .25, numThrows);

        targets = new Target [numTargets];

        for (int i = 0; i < numTargets; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double radius = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            int hitPoints = in.readInt() * 10;

            targets [i] = new Target(width, height, xPos, yPos,
                                     radius, xVel, yVel, hitPoints);
        }
        in.close();
    }

   /*
    * Description: method to see if player won game.
    *              player wins game when all targets
    *              hit points are 0 (have been hit).
    * Input:       none
    * Output:      boolean value; true if player wins.
    */
    private boolean didPlayerWin() {
        for (int i = 0; i < targets.length; i++) {
             if (targets[i].getHitPoints() != 0) {
                 return false;
             }
        }
        return true;
    }

   /*
    * Description: method to see if player lost game.
    *              player loses when bird has no
    *              remaining throw counts.
    * Input:       none 
    * Output:      boolean value; true if player loses.
    */
    private boolean didPlayerLose() {
        return mouseListeningMode && bird.getNumThrowsRemaining() == 0;
    }

   /*
    * Description: method to see if either the win or lose
    *              condition is fulfilled.
    *              win: all targets' hit points = 0
    *              lose: bird has no remaining throw counts. 
    * Input:       none          
    * Output:      boolean value; true if either player wins or loses
    */
    public boolean gameOver() {
        return didPlayerLose() || didPlayerWin();
    }

   /*
    * Description: updates each target and bird in the arena.
    * Input:       double time between frames
    * Output:      n/a void method  
    */
    public void update(double timeStep) {
        for (int i = 0; i < targets.length; i++) {
            targets[i].update(timeStep);
        }

        //when the program is in mouseListeningMode...
        if (mouseListeningMode) {

            // if the mouse is currently pressed
            if (PennDraw.mousePressed()) { 
                mouseWasPressedLastUpdate = true;
                bird.setVelocityFromMousePos();
            }

            //when the player just released the mouse button
            if (PennDraw.mousePressed() == false &&
                mouseWasPressedLastUpdate == true) {
                //game transition from mouse-listening mode to bird-flight mode
                mouseWasPressedLastUpdate = false;
                mouseListeningMode = false;
                bird.decrementThrows();
            }
        }

        else {
           bird.update(timeStep);

            //bird checks if it overlaps each target
            for (int i = 0; i < targets.length; i++) {
                bird.testAndHandleCollision(targets[i]);
            }

            //if target is hit in round its health decreases
            if (birdIsOffscreen()) {
                for (int i = 0; i < targets.length; i++) {
                    if (targets[i].isHit()) {
                        targets[i].decreaseHP();
                        targets[i].setHitThisShot(false);
                    }
                }
                //game is back to mouse listening mode when bird flies offscreen
                bird.reset();
                mouseListeningMode = true;
            }
        }
    }

   /*
    * Description: helper method that lets
    *              Arena class know when to reset
    *              bird's position and velocity along
    *              with game state. 
    *              bird is allowed to go above the
    *              top of the screen without resetting.
    * Input:       none
    * Output:      boolean value returning true when:
    *              bird is offscreen to the left, right, or bottom
    */
    private boolean birdIsOffscreen() {
        return (bird.getYpos() + bird.getRadius() < 0) ||
               (bird.getXpos() + bird.getRadius() < 0) ||
               (bird.getXpos() - bird.getRadius() > width);
    }

   /*
    * Description: draw function that draws
    *              each target and the bird.
    *              can draw the bird's velocity
    *              as a line.
    * Input:       none
    * Output:      n/a void method  
    */              
    public void draw() {
        PennDraw.clear();

        for (int i = 0; i < targets.length; i++) {
            targets[i].draw();
        }

        bird.draw(); 

        if (mouseWasPressedLastUpdate) {
            bird.drawVelocity();
        }

        PennDraw.advance();
    }

   /*
    * Description: draws the victory or loss screen.
    *              win: if all the targets have 0 hit points
    *              otherwise they have lost
    * Input:       none
    * Output:      n/a void method  
    */  
    public void drawGameCompleteScreen() {
        PennDraw.clear();

        if (didPlayerWin()) {
            PennDraw.text(width / 2, height / 2, "You Win!");
        } else if (didPlayerLose()) {
            PennDraw.text(width / 2, height / 2, "You have lost...");
        }

        PennDraw.advance();
    }
}
