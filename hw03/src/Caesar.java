/*  Name: Allison Chen
 *  PennKey: allchen
 *  Recitation: 209
 *
 *  Execution: java Caesar encrypt plaintext.txt key
 *             java Caesar decrypt cipher.txt key
 *             java crack cipher.txt english.txt
 *  
 *  Takes in command line arguments that will tell to:
 *  encrypt a message using a particular key,
 *  decrypt a message using a particular key, or
 *  crack an encrypted message, giving us back the secret key
 *  The message will be stored in a file,
 *  which will be read by the program.
 */
             
public class Caesar {
    public static void main(String[] args) {
        String filename = args[1];
         
        //creates a variable inStream to read from the file
        In inStream = new In(filename); 
        String code = inStream.readAll();
        
        if (args[0].equals("crack")) {
            System.out.println(crack(code, args[2]));
        }
       
        //takes command line argument of char key
        char letter = args[2].charAt(0);
        int intRepresentation = (int) letter - 'A'; 
            
        if (args[0].equals("encrypt")) {
            System.out.println(encrypt(code, intRepresentation));
        }
        
        if (args[0].equals("decrypt")) {
            System.out.println(decrypt(code, intRepresentation));
        }
    }

    /*
     * Description: converts a string to a symbol array,
     *              where each element of the array is an
     *              integer encoding of the corresponding
     *              element of the string.
     * Input:  the message text to be converted
     * Output: integer encoding of the message
     */
     public static int[] stringToSymbolArray(String str) { 
        int [] encodeArray = new int [str.length()]; 
        for (int i = 0; i < encodeArray.length; i++) {
            str = str.toUpperCase();
            char letter = str.charAt(i);
            //cast letter to an integer as encoded by our representation
            int intRepresentation = (int) letter - 'A'; 
            encodeArray [i] = intRepresentation;
        }
        return encodeArray;
     }
      
    /*
     * Description: converts an array of symbols to a string,
     *              where each element of the array is an
     *              integer encoding of the corresponding
     *              element of the string.
     * Input:  integer encoding of the message
     * Output: the message text
     */
     public static String symbolArrayToString(int[] symbols) { 
        String messageText = "";
        char letter = 0;
        for (int i = 0; i < symbols.length; i++) {
            int symbolRepresentation = symbols[i];
            int intCode = symbolRepresentation;
            symbols[i] = intCode; 
            //recover original character
            letter = (char) (symbols[i] + 'A');
            messageText += letter + "";
        }
        return messageText; 
     }

    /*
     * Description: symbol, an english letter,
     *              is shifted down the alphabet 
     *              by an offset amount.
     * Input:   symbol (english letter)
     *          offset is the amount it 
     *          should be shifted down by
     * Output:  integer representing the newly shifted letter
     */
     public static int shift(int symbol, int offset) {
        if (symbol >= 0 && symbol <= 25 && offset >= 0 && offset <= 25) {
            symbol = symbol + offset;
            //checks to wrap symbols
            if (symbol % 26 >= 0) {
                symbol = symbol % 26;
            return symbol; 
            }
        return symbol;
        } 
        //punctuation not encoded
        return symbol; 
     }

    /*
     * Description: where each element of the array is an
     *              integer encoding of the corresponding
     *              element of the string.
     * Input:   symbol representing an english letter
     *          offset representing the the amount it 
     *          should be shifted down by
     * Output: the new integer representing the newly shifted letter
     */
     public static int unshift(int symbol, int offset) {
        if (symbol >= 0 && symbol <= 25 && offset >= 0 && offset <= 25) {
            //can unshift without worry about wrapping
            if (symbol >= offset) {
                //diff of symbol and offset is the int representing unshifted letter
                symbol = shift(0, symbol - offset);
            return symbol;
            } else symbol = 26 - (offset - symbol);
                return symbol;
        } else 
            return symbol;
      } 

    /*
     * Description: convert the message to an array of symbols.
     *              for each alphabetic symbol in the array,
     *              shift it by the given key
     * Input:   message to be converted and key to be shifted by
     * Output:  String version of the encrypted symbol array
     */
     public static String encrypt(String message, int key) {
        int [] messageArray = stringToSymbolArray(message);
          
        for (int i = 0; i < messageArray.length; i++) {
            messageArray [i] = shift(messageArray [i], key);
        }
        
        String encryptedString = symbolArrayToString(messageArray);
        return encryptedString;
     }
      
    /*
     * Description: convert the message to an array of symbols.
     *              for each alphabetic symbol in the array,
     *              unshift it by the given key
     * Input:   message to be converted and key to be unshifted by
     * Output:  String version of the encrypted symbol array
     */
     public static String decrypt(String cipher, int key)  {
        int [] cipherArray = stringToSymbolArray(cipher);
         
        for (int i = 0; i < cipherArray.length; i++) {
            cipherArray [i] = unshift(cipherArray [i], key);
        }

        String decryptedString = symbolArrayToString(cipherArray);
        return decryptedString;
     }

    /*
     * Description: reads a txt file of frequencies
     *              of characters in english and 
     *              returns values in an array.
     * Input:   name of txt file
     * Output:  double array with frequencies
     */
     public static double [] getDictionaryFrequencies(String filename) {
        In inStream = new In(filename); 
        double [] frequencyArray = new double [26];

        for (int i = 0; i < frequencyArray.length; i++) {
            frequencyArray [i] = inStream.readDouble();
        }
        return frequencyArray;
     }

    /*
     * Description: find the frequencies of each letter
     *              A-Z in the ciphertext
     *              and returns the frequencies in an array.
     * Input:   integer array of symbols
     * Output:  double array of each symbol's frequencies
     */
     public static double [] findFrequencies(int [] symbolArray) {
       //array to carry frequencies
        double [] symbolFrequencyArray = new double [26];
        int symbolCount = 0;
        //to account for only letters
        int totalSymbols = 0;

        for (int i = 0; i < symbolFrequencyArray.length; i++) {
            //reset values once exit of "j" for loop
            totalSymbols = 0;
            symbolCount = 0;

            for (int j = 0; j < symbolArray.length; j++) {

                if (symbolArray[j] >= 0 && symbolArray[j] <= 25) {
                    totalSymbols++;

                    if (i == symbolArray [j]) {
                        symbolCount++;
                    }
                }
            }
            symbolFrequencyArray [i] = (double) symbolCount / totalSymbols;
        }
        return symbolFrequencyArray;
     }

    /*
     * Description:  calculates a score that tells us how
     *               well the letter frequency in the
     *               message matches what we would
     *               expect if the message were in English.
     * Input:   double array of ciphertext frequencies
     *          double array of english frequencies
     * Output:  the difference between each value pair in the arrays 
     */
     public static double scoreFrequencies(double [] freqs, double [] englishFreqs) {
         double totalScore = 0;

         for (int i = 0; i < 26; i++) {
             totalScore += Math.abs(freqs[i] - englishFreqs[i]);
         }
         return totalScore;
     }

    /*
     * Description:  when key is unknown, decrypts cipher
     *               with all possible keys. the message 
     *               using the key with the lowest possible
     *               score should be the original message
     * Input:   String file name of encrypted message
     *          String file name of english frequencies
     * Output:  the difference between each value pair in the arrays 
     */
     public static String crack(String cipherFile, String englishFile) {
         double [] scoreFreqArray = new double [26];
         String decryptedMessage = "";

         for (int i = 0; i < scoreFreqArray.length; i++) {
             decryptedMessage = decrypt(cipherFile, i);
             int [] decryptedSymbolArray = stringToSymbolArray(decryptedMessage);
             double [] dictionaryFreq = getDictionaryFrequencies(englishFile);
             double [] freqs = findFrequencies(decryptedSymbolArray);
             scoreFreqArray [i] = scoreFrequencies(freqs, dictionaryFreq);
         }
        //finds minimum score frequency
        double min = scoreFreqArray [0];
        for (int i = 0; i < scoreFreqArray.length; i++) {
            if (scoreFreqArray [i] < min) {
                min = scoreFreqArray [i];
            }
            //gets key integer of minimum score frequency
            if (scoreFreqArray [i] == min) {
               decryptedMessage = decrypt(cipherFile, i);
            }
        } 
        return decryptedMessage;
     }
}


