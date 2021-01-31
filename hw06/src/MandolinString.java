 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  Class that implements the Karplus-Strong algorithm.
  *  Represents the string of the mandolin.
  *
  */

public class MandolinString {

    private RingBuffer buffer; // ring buffer
    private static double SAMPLING_RATE = 44100;
    private static double ENERGY_DECAY_FACTOR = 0.991;
    int numSamples;
    int ticCalls = 0;

    //Constructor that creates a mandolin string of the given frequency
    public MandolinString(double frequency) {
        numSamples = (int) Math.ceil(SAMPLING_RATE / frequency);
        buffer = new RingBuffer(numSamples);

        while (!buffer.isFull()) {
            buffer.enqueue(0);
        }
     }

   /*
    * Description: replaces all the numSample
    *              items in the ring buffer with
    *              random values from [-0.5, 0.5)
    * Input:   n/a
    * Output:  n/a
    */
    public void pluck() {
        for (int i = 0; i < numSamples; i++) {
            buffer.dequeue();
            buffer.enqueue((Math.random()) - 0.5);
        }
    }

   /*
    * Description: applies the Karplus-Strong update.
    *              deletes sample at front of ring
    *              buffer and adds new sample to end.
    * Input:   n/a
    * Output:  n/a
    */
    public void tic() {
        double first = buffer.dequeue(); //System.out.println("first" + first);
        double second = sample();
        double ksUpdate = ((first + second) / 2) * ENERGY_DECAY_FACTOR;
        buffer.enqueue(ksUpdate);

        ticCalls++;
    }

   /*
    * Description: returns value of item at
    *              the front of the ring buffer.
    * Input:   n/a
    * Output:  double value of item at front of the ring buffer.
    */
    public double sample() {
        return buffer.peek();
    }

   /*
    * Description: returns the total number of
    *              times tic() was called.
    * Input:   n/a
    * Output:  int times tic() was called
    */
    public int time() {
        return ticCalls;
    }

    // MandolinString checkpoint test
    public static void main(String[] args) {
        // how many samples should we "play"
        int numSamplesToPlay = Integer.parseInt(args[0]);

        // a starting set of samples; it's pretty easy to calculate
        // the new samples that will get generated with a calculator
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  

        // create a mandolin string to test with exactly samples.length,
        // this looks a little funny because the HarpString constructor
        // expects a frequency, not a number of elements
        MandolinString testString = new MandolinString(44100.0 / samples.length);

        // at this point the RingBuffer underlying testString should have
        // a capacity of samples.length and should be full
        System.out.println("testString.buffer.isEmpty(): " + 
                            testString.buffer.isEmpty());
        System.out.println("testString.buffer.isFull():  " + 
                            testString.buffer.isFull());

        // replace all the zeroes with the starting samples
        for (int i = 0; i < samples.length; i++) {
            testString.buffer.dequeue();
            testString.buffer.enqueue(samples[i]);
        }

        // "play" for numSamples samples; printing each one for inspection
        for (int i = 0; i < numSamplesToPlay; i++) {
            int t = testString.time();
            double sample = testString.sample();

            // this statement prints the time t, padded to 6 digits wide
            // and the value of sample padded to a total of 8 characters
            // including the decimal point and any - sign, and rounded
            // to four decimal places
            System.out.printf("%6d %8.4f\n", t, sample);

            testString.tic(); // advance to next sample
        }
    }
}
