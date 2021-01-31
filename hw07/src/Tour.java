 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  A class that implements the Tour Interface.
  *  Class models a tour by a linked list of points.
  *
  */

public class Tour implements TourInterface {

    private Node head;
    private Node lastNode;
   
   /*
    * Description: constructor for Tour class
    *              that creates and empty Tour.
    *              head and lastNode will be null.         
    */
    public Tour() {

    }

   /*
    * Description: creates a String representation
    *              of the Tour
    * Input:       n/a
    * Output:      the String representation of the Tour
    *              where each point is on a new line.
    */
    public String toString() {
        String tour = "";
        Node curr = head;

        if (curr == null) {
            return tour;
        } else {
            for (curr = head; curr != null; curr = curr.next) {
                tour += curr.point.toString() + '\n';
            }
            return tour;
        }
    }

   /*
    * Description: draws the Tour from Point
    *              to Point using PennDraw.
    *              both edges adjacent to Point p
    *              should be drawn in a different color.
    * Input:       n/a
    * Output:      n/a
    */
    public void draw(Point p) {
        Node curr = head;

        if (curr == null) {
            return;
        }

        for (curr = head; curr.next != null; curr = curr.next) {

            if (curr.point.equals(p) || curr.next.point.equals(p)) {
                PennDraw.setPenColor(PennDraw.RED);

            } else
                PennDraw.setPenColor(PennDraw.BLACK);

            curr.point.drawTo(curr.next.point);
        }
    }

   /*
    * Description: returns the number of Points
    *              in the Tour (without including
    *              the first point twice)
    * Input:       n/a
    * Output:      the int number of Points
    */
    public int size() {
        int count = 0;
        Node curr = head;

        if (curr == null) {
            return count;
        } else {
            for (curr = head; curr.next != null; curr = curr.next) {
                count++;
            }
            return count;
        }
    }

   /*
    * Description: returns the total length of 
    *              the Tour from Point to Point.
    * Input:       n/a
    * Output:      the double total length of the Tour.
    *              an empty Tour will return a distance of 0.0
    */
    public double distance() {
        Node curr = head;
        double distance = 0;

        if (curr == null) {
            return distance;
        }

        for (curr = head; curr.next != null; curr = curr.next) {
            distance += curr.point.distanceTo(curr.next.point);
        }
        return distance;
    }

   /*
    * Description: adds Point p as last Point of the Tour.
    *              because lastNode points to the same Point
    *              as head, when we append a Node to the end
    *              of our linked list, it will be placed before
    *              lastNode.
    * Input:       Point p to be added
    * Output:      n/a
    */
    public void insertInOrder(Point p) {

        Node curr = head;

        if (curr == null) {
            lastNode = new Node(null, p);
             head = new Node(lastNode, p);

        } else {
            Node last = new Node(lastNode, p);
            for (curr = head; curr.next != lastNode; curr = curr.next) {
                // iterating through loop to find where we want to insert p
            }

            curr.next = last;
            last.next = lastNode;
       }
    }

   /*
    * Description: adds the Point p to the Tour
    *              after the closest Point already
    *              in the Tour. 
    *              if there are multiple closest
    *              points with equal distances to
    *              Point p, p will be inserted after
    *              the first closest Point.
    * Input:       Point p to be inserted
    * Output:      n/a
    */
    public void insertNearest(Point p) {
        Node curr = head;

        if (curr == null) {
            lastNode = new Node(null, p);
            head = new Node(lastNode, p);
        }
        else {
            double min = Double.MAX_VALUE;
            Node closest = head;

            for (curr = head; curr != lastNode; curr = curr.next) {
                double distance = curr.point.distanceTo(p);

                if (distance < min) {
                    min = distance;
                    closest = curr;
                }
            }

            Node nearest = new Node(closest.next, p);
            closest.next = nearest;
        }
     }

   /*
    * Description: adds Point p to the Tour in 
    *              the position where it would cause
    *              the smallest increase in the 
    *              Tour's distance.
    *              if there are multiple positions for
    *              Point p that cause the same minimal
    *              distance, insert in first position.
    *              any edge starting or ending at
    *              Point p should be in a distinct
    *              color.
    * Input:       Point p to be inserted
    * Output:      n/a
    */
    public void insertSmallest(Point p) {
        Node curr = head;

        if (curr == null) {
            lastNode = new Node(null, p);
            head = new Node(lastNode, p);
        }
        else {
            double min = Double.MAX_VALUE;
            Node closest = head;

            for (curr = head; curr != lastNode; curr = curr.next) {
                double distance = curr.point.distanceTo(p) + 
                       p.distanceTo(curr.next.point) - 
                       curr.point.distanceTo(curr.next.point);

                if (distance < min) {
                    min = distance;
                    closest = curr;
                }
             }

            Node nearest = new Node(closest.next, p);
            closest.next = nearest;
        }
    }

    public static void main(String[] args) {
        TourInterface tour = new Tour();
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(1, 1);
        Point d = new Point(0, 1);

        tour.insertInOrder(a);
        tour.insertInOrder(b);
        tour.insertInOrder(c);
        tour.insertInOrder(d);

        tour.toString();

        tour.draw(a);
    }
}
