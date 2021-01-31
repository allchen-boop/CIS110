// TODO: WRITE Mandolin.java BASED ON THIS PROGRAM
public class MiniMandolin {
    public static void main(String[] args) {
        // create two mandolin strings, for concert A and C
        double concertA = 435.0;
        double concertC = concertA * Math.pow(2, 3.0/12.0);  
        MandolinString stringA = new MandolinString(concertA);
        MandolinString stringC = new MandolinString(concertC);

        // infinite loop to check if a key is pressed and play the associated note
        while (true) {
            // check if the user has typed a key; if so, process it   
            if (PennDraw.hasNextKeyTyped()) {
                char key = PennDraw.nextKeyTyped();  // which key was pressed?
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                }
            }

            // compute the combined sound of all mandolin strings
            double sample = stringA.sample() + stringC.sample();
  
            // play the sample on standard audio
            StdAudio.play(sample);
  
            // advance the simulation of each mandolin string by one step   
            stringA.tic();
            stringC.tic();
        }
    }
}
