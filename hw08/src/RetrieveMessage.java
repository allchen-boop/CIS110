 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  A class that will retrieve a message from 
  *  a png image. it can accept three command line arguments.
  */

public class RetrieveMessage {

    public static void main(String[] args) {
        String imageFile = args [0];

        //loads the image and stores in a 2D array
        int [][] pixels = ImageData.load(imageFile);

        //total number of pixels in the image
        int area = pixels.length * pixels[0].length;

        //size will the max amount of pixels we can read (max multiple of 7)
        int size = area / 7 * 7;

        String extract = "";
        int count = 0;

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                extract += pixels[row][col] % 2;
                count++;  

                //to ignore the extra pixels when not a multiple of 7
                if (count >= size) {
                    break;
                }
            }
                if (count >= size) {
                    break;
                }
        }

        int [] extractArr = new int [extract.length()];

        if (args.length == 3) {
            for (int i = 0; i < extractArr.length; i++) {
                extractArr[i] = (int) extract.charAt(i) - '0';
            }

            Codec.decrypt(extractArr, args[1], Integer.parseInt(args [2]));
            String decoded = Codec.decode(extractArr);
            int nullIndex = decoded.indexOf('\0');

            //to only print out character up to the NULL character
            String extractString = decoded.substring(0, nullIndex);
            System.out.println(extractString);

        } else if (args.length == 1) {
            for (int i = 0; i < extractArr.length; i++) {
                extractArr[i] = (int) extract.charAt(i) - '0';
                System.out.println(extractArr[i]);
            } 
        }
    }
}