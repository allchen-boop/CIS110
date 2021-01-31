/**********************************************************************
 *  readme template                                                   
 *  Traveling Salesperson Problem
 **********************************************************************/

Name: Allison Chen
PennKey: allchen
Recitation: 209

Hours to complete assignment (optional):


/**********************************************************************
 *  Have you entered all help, collaboration, and outside resources
 *  in your help log?  If not, do so now.  (And in future, make sure
 *  you make your help log entries as you go, not at the end!)
 *
 *  If you did not get any help in outside of TA or instructor office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA or instructor office hours.
 *  I did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/

 "I did not receive any help outside of TA or instructor office hours.
  I did not collaborate with anyone, and I did not use any
  resources beyond the standard course materials."



/**********************************************************************
 *  Explain how you implemented the nearest insertion heuristic.
 **********************************************************************/
 I created a new reference to a Node representing the current Node and had it refer to the head Node.
 Then to account for an empty Tour, I checked if this current node was null.
 If it was, then I created two nodes that each contained a reference to the same Point. 
 If it wasn't empty, then I created a variable representing the minimum distance,
 and I set it equal to the max value of a double. I then created a reference to a Node representing 
 the Node with the closest Point and had it point to the head Node. I then looped through the 
 linked list, stopping before it reached the last Node. In the loop, I calculated the 
 distance between the Point p and each Point already in the Tour. If this distance value was
 less than the min value I initialized out of the loop, then I set it as the new
 min value. I also take the closest Node reference I initialized outside the loop and point it
 this current Node. Thus, we are keeping track of which Node it is that has a reference
 to the closest Point. After we loop through the linked list, we will know the Node
 which points to the closest Point, and I create a new Node with Point p where the next Node
 in the list is the Node after our closest Node. I then set this Node to point to this new Node.
  

/**********************************************************************
 *  Explain how you implemented the smallest insertion heuristic.
 *  It's sufficient to list only the differences between this
 *  heuristic and the nearest insertion heuristic.
 **********************************************************************/
I did the same thing except I calculated the distance differently.
In this method I calculated the distance by summing the distance
from the Point of the current Node to the Point p and the Point p to the
the Point of the following Node. I then subtracted from this the distance from
the Point of the current Node to the Point of the next Node following this current Node.



/**********************************************************************
 *  Fill in the distances computed by your heuristics.                
 **********************************************************************/

data file        order     nearest     smallest      extra credit
-----------------------------------------------------------------------
tsp10.txt		2586.7		1566.1		1655.7
tsp100.txt		25547		7389.9		4887.2
tsp1000.txt		3.2769e+05	27869		17266
usa13509.txt	3.9108e+06	77450		45075




 
 /**********************************************************************
 *  Why is it a good idea to repeat the first Node at the end of the
 *  Tour?
 **********************************************************************/
Because we are representing the traveling salesman problem and the salesman 
needs to return back to where he started.
 

 
/**********************************************************************
 *  If you did the extra credit, explain your heuristic, and how
 *  you went about implementing it.
 **********************************************************************/


 
 
 
/**********************************************************************
 *  If you did the extra credit, give instructions here for 
 *  running it.
 **********************************************************************/



 
/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
In general just starting the program and having to figure out linked lists was confusing.



/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

 
 
 
