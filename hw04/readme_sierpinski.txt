/**********************************************************************
 *  readme.txt template                                                   
 *  Recursive Graphics
 **********************************************************************/

Name: Allison Chen
PennKey: allchen
Recitation: 209
Hours to complete assignment (optional):

/**********************************************************************
 *  Have you enter all help, collaboration, and outside resources
 *  in your help log?  If not, do so now.  (And in future, make sure
 *  you make your help log entries as you go, not at the end!)
 *
 *  If you did not get any help in outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/
"I did not receive any help outside of TA office hours.  I
 did not collaborate with anyone, and I did not use any
 resources beyond the standard course materials."


/**********************************************************************
 *  Describe your artistic creation, and how you went about writing   
 *  a program to produce it. If you used any sources such as the book
 *  or booksite, reference them here. 
 **********************************************************************/
 
I created a recursive function that draws a seemingly random collection of soap bubbles in various shades of
blue/blueish purple on a soap bar. A large bubble is drawn on either side of the bar.
On each large bubble, two smaller bubbles are drawn on the edge of the larger bubble.
This simulates how bubbles in reality get stuck together.
This continues on in the recursive calls with progressively smaller bubbles.
The smaller bubbles' locations are randomized on the edge of the larger bubble.
There is also a recursive call to more randomized bubbles that are centered in the area of accumulating bubbles.
They don't necessarily have to stick to the edge of the larger bubble as they 
are supposed to simulate random fly away bubbles in the "foam" that is forming.
(Program looks more realistic with command line argument of at least 5)


/**********************************************************************
 *  Is the following a use of recursion? yes/no 
 *  Recall the meaning of recursion as defined by cis110.
 *  
 * public static void a() {
 *     b(5);
 *     b(2);
 *     b(7);
 *     b(4); 
 * }
 * public static void b(int a) {
 *     a();
 * }
 *
 *  Explain your reasoning in 20 WORDS OR LESS.
 **********************************************************************/
No. Recursive functions include a base case and a recursive step.
There is no base case to stop the recursion. 

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
N/A



/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/