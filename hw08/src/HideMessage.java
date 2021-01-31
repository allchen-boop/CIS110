 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  A class that will encode a message into binary,
  *  encrypt the message using an LSFR,
  *  then hiding the message in the least significant bits 
  *  in the png image.
  *  it will accept four command line arguments.
  */

public class HideMessage {
    public static void main(String[] args) {
        String imageFile = args[0];
        String textFile = args [1];

        int [][] image = ImageData.load(imageFile);
        In in = new In(textFile);

        //adding the NULL character to end the message
        String message = in.readAll() + "\0";

        int [] textArr = Codec.encode(message);

        if (message.length() > image.length * image[0].length) {
            throw new RuntimeException("ERROR: message is too long for image");
        }

        if (args.length == 4) {
            String seed = args [2];
            int tapPos = Integer.parseInt(args [3]);
            Codec.encrypt(textArr, seed, tapPos);
        }

        int i = 0;
        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[row].length; col++) {

                //if the LSB in the image is different from binary representation
                //of message than we change the LSB in the image to embed
                if (image[row][col] % 2 != textArr [i]) {
                    image[row][col] += 1;
                }
                i++; 
                if (i >= message.length()) {
                    break;
                }
            }
            if (i >= message.length()) {
                break;
            }
       }
       ImageData.show(image);
    }
}