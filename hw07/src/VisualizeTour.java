/*************************************************************************
  *  YOU DO NOT NEED TO MODIFY THIS FILE
  *
  *  Compilation:  javac VisualizeTour.java
  *  Execution:    java VisualizeTour file.txt
  *  Dependencies: Tour.java Point.java In.java PennDraw.java
  *
  *  Constructs a Tour from a file, inserting Points in order, using the
  *  nearest insertion heuristic, or the smallest insertion heuristic,
  *  or asks the user for each Point.
  *  Displays the Tour, optionally animating its construction process.
  *
  *************************************************************************/

public class VisualizeTour {
    private static int w, h;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java VisualizeTour [filename]");
            return;
        }
            
        // open input file
        In inFile = new In(args[0]);
        
        // get dimensions
        w = inFile.readInt();
        h = inFile.readInt();
        PennDraw.setCanvasSize(w, (int) (h * 1.1));
        PennDraw.setXscale(0, w);
        PennDraw.setYscale(-.1 * h, h);

        char mode = getMode(args[0]);
        PennDraw.clear();
        
        boolean animate = true;
        if (mode == 'i') displayInteractiveModeInstructions();
        else  animate = getAnimationMode();
        PennDraw.clear();

        // create empty Tour
        TourInterface tour = new Tour();
        
        if (animate) PennDraw.enableAnimation(1);
        else         PennDraw.enableAnimation(0);
        
        try {
            // iterate through all Points in file
            while (!inFile.isEmpty()) {
                double x = inFile.readDouble();
                double y = inFile.readDouble();
                Point p = new Point(x, y);
                
                char nextPointMode = mode;
                
                // if interactive mode, prompt for insertion mode
                if (mode == 'i') nextPointMode = promptForInsertionMode(x, y);
                
                // insert next Point with the specified mode
                if (nextPointMode == 'o')      tour.insertInOrder(p);
                else if (nextPointMode == 'n') tour.insertNearest(p);
                else if (nextPointMode == 's') tour.insertSmallest(p);


                // draw to standard draw
                if (animate || mode == 'i') {
                    drawTour(tour, p);

                    String msg = "Inserted " + x + ", " + y + "using ";
                    if (nextPointMode == 'o') {
                        msgr(-1 / 20.0, msg + "insertInOrder()");
                    } else if (nextPointMode == 'n') {
                        msgr(-1 / 20.0, msg + "insertNearest()");
                    } else if (nextPointMode == 's') {
                        msgr(-1 / 20.0, msg + "insertSmallest()");
                    } else if (nextPointMode == 'p') {
                        msgr(-1 / 20.0, "Skipped " + x + ", " + y);
                    }

                    PennDraw.advance();
                }
            }
            
            // draw to standard draw
            drawTour(tour, null);
            msgr(-1 / 20.0, "No more Points in file " + args[0]);
            msgr(-1 / 10.0, "Press p to print Tour to standard output");
            PennDraw.advance();
            
            while (true) {
                if (PennDraw.hasNextKeyTyped()) {
                    char typedKey = PennDraw.nextKeyTyped();
                    if (typedKey == 'p') {
                        System.out.println(tour);
                        System.out.println();
                    }
                }
            }
        } catch (RuntimeException e) {
            handleException(e);
        }
    }

    private static void msgl(double pos, String msg) {
        PennDraw.textLeft(0, h * pos, msg);
    }

    private static void msgr(double pos, String msg) {
        PennDraw.textRight(w, h * pos, msg);
    }

    private static char getMode(String filename) {
        
        // prompt for mode
        msgl(19.0/20, "Visualizing tour generation from file " + filename);
        msgl(17.0/20, "Press");
        msgl(16.0/20, "o to add all Points using your insertInOrder()");
        msgl(15.0/20, "n to add all Points using your insertNearest()");
        msgl(14.0/20, "s to add all Points using your insertSmallest()");
        msgl(13.0/20, "i to ask for each Point");
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                char mode = PennDraw.nextKeyTyped();
                
                if ("onsi".indexOf(mode) != -1) {
                    return mode;
                }
            }
        }
    }
    
    private static boolean getAnimationMode() {
        msgl(19.0/20, "Press");
        msgl(18.0/20, "y to animate the insertion of each Point;");
        msgl(17.0/20, "n to see only the final Tour");
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                char typedKey = PennDraw.nextKeyTyped();
                if (typedKey == 'y')       return true;
                else if (typedKey == 'n')  return false;
            }
        }
    }

    private static void displayInteractiveModeInstructions() {
        // display interactive mode instructions
        msgl(19.0/20, "Interactive mode. Press");
        msgl(18.0/20, "o to add the next Point using your insertInOrder()");
        msgl(17.0/20, "n to add the next Point using your insertNearest()");
        msgl(16.0/20, "s to add the next Point using your insertSmallest()");
        msgl(15.0/20, "p to skip the next Point");
        msgl(14.0/20, "Press any key to continue");

        while (true)
            if (PennDraw.hasNextKeyTyped())
                break;
    }

    private static char promptForInsertionMode(double x, double y) {
        msgr(-1 / 10.0, "Inserting " + x + ", " + y + " (choose o, n, s, p)");
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                char nextPointMode = PennDraw.nextKeyTyped();
                if ("onsp".indexOf(nextPointMode) != -1)
                    return nextPointMode;
            }
        }
    }
    
    public static void drawTour(TourInterface tour, Point p) {
        PennDraw.clear();
        PennDraw.setPenColor(PennDraw.BLACK);
        tour.draw(p);
        PennDraw.setPenColor(PennDraw.BLACK);
        msgl(-2 / 20.0, "size: " + tour.size());
        msgl(-1 / 20.0, "distance: " +
             String.format("%.5g", tour.distance()));
    }
    
    public static void handleException(RuntimeException e) {
        PennDraw.clear();
        PennDraw.setPenColor(PennDraw.BLACK);
        msgl(h * 19.0/20,
                         "Your code threw an exception:");

        for (StackTraceElement ste : e.getStackTrace()) {
            if (ste.getClassName().equals("Tour")) {
                msgl(18.0/20, ste.getMethodName());
                msgl(17.0/20, "Line " + ste.getLineNumber());
                break;
            }
        }

        msgl(16.0/20, "Check the console for the error message");
        throw e;
    }
}
