 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  * 
  *  Class that represents the mandolin.
  *  
  */
public class Mandolin {

    private static String NOTE_MAPPING = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static double FREQUENCY_VALUE = 436.0;

    public static void main(String[] args) {

        MandolinString [] mandolinArr = new MandolinString [NOTE_MAPPING.length()];

        for (int i = 0; i < mandolinArr.length; i++) {
            double frequency = FREQUENCY_VALUE * Math.pow(2, (i - 24.0) / 12.0);

            MandolinString mandolinString = new MandolinString(frequency);
            mandolinArr[i] = mandolinString;
         }
            //infinite loop
            while (true) {
                // checks if the the user has typed a key; processes it if pressed   
                if (PennDraw.hasNextKeyTyped()) {
                    char key = PennDraw.nextKeyTyped(); 

                    //if the key played is one of our notes
                    if (NOTE_MAPPING.indexOf(key) >= 0) {
                        mandolinArr[NOTE_MAPPING.indexOf(key)].pluck();
                    }
                }

                double sample = 0;

                for (int i = 0; i < mandolinArr.length; i++) {
                    sample += mandolinArr[i].sample();
                }         

                StdAudio.play(sample);

                for (int i = 0; i < mandolinArr.length; i++) {
                    mandolinArr[i].tic();
                }
            }
     }
}