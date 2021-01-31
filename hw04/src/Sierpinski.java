/*  Name: Allison Chen
 *  PennKey: allchen
 *  Recitation: 209
 *
 *  Execution: java Sierpinski recursion level
 *  
 *  Takes in one positive int command line argument
 *  that will tell it the recursion level to 
 *  recursively draw a Sierpinski carpet of that order.
 */         
public class Sierpinski {
    public static void main(String[] args) {

        String num = args [0];
        int numLevels = Integer.parseInt(num);

        sierpinski(numLevels, 1.0 / 6.0, 0.5, 0.5);
    }
    
    /*
     * Description: recursive function that 
     *              will draw a Sierpinski carpet.
     * Input:   numLevels is the order of Sierpinski carpet
     *          half the length of the square
     *          the location of the square
     * Output:  void method so no return; 
     *          generates recursive drawing of a Siepinski Carpet
     */
    public static void  sierpinski(int numLevels, double halfSideLength, 
                                    double x, double y)  {
        //System.out.print(numLevels);
        //System.out.println(" " + halfSideLength);
        PennDraw.filledSquare(x, y, halfSideLength);

        if (numLevels <= 1) {
            return;
        } else {
             sierpinski(numLevels - 1, halfSideLength / 3,
                        x + 2 * halfSideLength, y + 2 * halfSideLength);
             sierpinski(numLevels - 1, halfSideLength / 3,
                        x, y + 2 * halfSideLength);
             sierpinski(numLevels - 1, halfSideLength / 3,
                        x - 2 * halfSideLength, y + 2 * halfSideLength);
             sierpinski(numLevels - 1, halfSideLength / 3,
                        x - 2 * halfSideLength, y);
             sierpinski(numLevels - 1, halfSideLength / 3,
                        x - 2 * halfSideLength , y - 2 * halfSideLength);
             sierpinski(numLevels - 1, halfSideLength / 3,
                        x, y - 2 * halfSideLength);
             sierpinski(numLevels - 1, halfSideLength / 3,
                        x + 2 * halfSideLength, y - 2 * halfSideLength);
             sierpinski(numLevels - 1, halfSideLength / 3,
                        x + 2 * halfSideLength, y);
        }

        PennDraw.enableAnimation(60);
        PennDraw.advance();
    }
}   