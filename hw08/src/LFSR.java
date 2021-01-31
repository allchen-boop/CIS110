 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  A class that models a linear feedback shift. 
  *  An LFSR is a structure that can produce a stream of pseudo-random bits
  *  which can be used in cryptography.
  */

public class LFSR {

    private int [] shiftRegister;
    private String seed;
    private int tapPosition;

   /*
    * Description: constructor for LSFR class.
    *              takes a String parameter whose characters
    *              are a sequence of 0s and 1s and an
    *              int parameter specifying which position in the
    *              register to use as the tap. 
    *
    *              will throw a RuntimeException if:
    *                   seed contains any characters other than 0 or 1
    *                   tapPosition refers to an impossible position in the register
    *                   seed is null
    * Input:       String seed
    *              int tapPositon
    * Output:      n/a
    */
    public LFSR(String seed, int tapPosition) {

        this.seed = seed;
        this.tapPosition = tapPosition;
        shiftRegister = new int [seed.length()];

        for (int i = 0; i < seed.length(); i++) {
            shiftRegister [i] = seed.charAt(i) - '0'; 
        }

        if (seed.charAt(tapPosition) != '0' &&
            seed.charAt(tapPosition) != '1') {
            throw new RuntimeException("ERROR: seed contains" + 
                                       "characters other than 0 or 1");
        }

        if (tapPosition < 0) {
            throw new RuntimeException("ERROR: tapPosition refers to" +
                                       "an impossible position in register");
        }

        if (seed.equals(null)) {
            throw new RuntimeException("ERROR: seed is null");
        }
    }

   /*
    * Description: constructor for LSFR class.
    *              takes parameter seedLength
    *              and generates a random seed
    *              such as a random string of 0s and 1s
    *              of length seedLength.
    *
    *              will a RuntimeException if:
    *                   seedLength is not positive
    *                   tapPosition is an impossible position in the register.
    * Input:       int seedLength
    *              int tapPositon
    * Output:      n/a
    */
    public LFSR(int seedLength, int tapPosition) {
        String randomSeed = "";

        if (seedLength < 0 || tapPosition < 0) {
            throw new RuntimeException("ERROR: seedLength is negative");
        }

        if (tapPosition < 0) {
            throw new RuntimeException("ERROR: tapPosition refers" +
                                       "to an impossible position");
        }
        else {
            //concatenating random string
            for (int i = 0; i < seedLength; i++) {
                randomSeed += StdRandom.uniform();
            }
        }
    }

   /*
    * Description: returns the current bit sequence
    *              in the shift register as a String of 1s and 0s.
    * Input:       n/a
    * Output:      String representing bit sequence
    */
    public String toString() {
        String sequence = "";
        for (int i = 0; i < shiftRegister.length; i++) {
            sequence += shiftRegister[i];
        }
        return sequence;
    }

   /*
    * Description: getter method that returns the tap position
    *               as given by the constructor.
    * Input:       n/a
    * Output:      the tapPosition
    */
    public int getTapPosition() {
        return tapPosition;
    }

   /*
    * Description: performs one step of the LFSR
    *              and returns the least significant bit
    *              (the rightmost bit) in the shift register
    *              after the step has been performed as an
    *              int with the value 0 or 1
    * Input:       n/a
    * Output:      the least significant bit
    */
    public int nextBit() {
        int leastSignifigant;

        //XORing (the seed indices are from 10 to 0)
        leastSignifigant = shiftRegister [0] ^
                          (shiftRegister[(seed.length() - 1) - getTapPosition()]);

        for (int i = 0; i < seed.length() - 1; i++) {
            shiftRegister[i] = shiftRegister [i + 1];
        }

        shiftRegister [seed.length() - 1] = leastSignifigant;
        return leastSignifigant;
     }

     public static void main(String[] args) {

     }
}