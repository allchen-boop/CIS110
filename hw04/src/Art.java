/*  Name: Allison Chen
 *  PennKey: allchen
 *  Recitation: 209
 *
 *  Execution: java Art int
 *  
 *  Takes one integer command-line argument n
 *  to control the depth of the recursion to produce
 *  a recursive pattern of bubbles on
 *  a soap bar.
 */
public class Art {
    public static void main(String[] args) {

        String recursiveDepth = args [1];
        int n = Integer.parseInt(recursiveDepth);

        PennDraw.picture(0.5, 0.5, "soap.png");

        bubbles(n, 0.1, 0.5, .7);
    }

    /*
     * Description: recursive function that 
     *              will simulate bubbles on a
     *              soap bar. Recursive call draws progressively
     *              smaller bubbles touching the larger bubble
     *              to try to realistically simulate
     *              how bubbles stick together. 
     * Input:   n as depth recursive call should go
     *          radius of bubble
     *          x and y coordinates of bubble
     * Output:  void method so no return; 
     *          generates drawing of bubbles on soap bar
     */
     public static void  bubbles(int n, double radius, double x, double y) {
        //different shades of blue for bubbles
        int green = (int) (55 * Math.random()) + 200;
        PennDraw.setPenColor(200, green, 255);

        //bubble can't go off screen
        if (x > radius && x < 1.0 - radius && y > radius && y < 1.0 - radius) {
            PennDraw.filledCircle(x, y, radius);
            PennDraw.filledCircle(x, y - .35, radius);

            if (n <= 1) {
               return;
            } else {
                //each call generates two progressively smaller bubbles "stuck"
                //on sides of the larger bubble.
                bubbles(n - 1, radius / 1.75, x - radius, 
                        y - (x - radius * Math.random()) + (x - radius));
                bubbles(n - 1, radius / 1.75, x + radius, 
                        y - (x - radius * Math.random()) + (x - radius));
                //generates more generally randomized bubbles in soap bar area
                bubbles(n - 1, radius / 2, (.35 * Math.random()) + .35,
                       (.15 * Math.random()) + .65);
            }
         }

         PennDraw.enableAnimation(10);
         PennDraw.advance();
   }
}
