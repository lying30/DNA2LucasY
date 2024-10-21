import java.util.HashMap;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [Lucas Y]
 *</p>
 */

public class DNA {

    private static final int R = 256;
    private static final int P = 1000000007;

    public static int STRCount(String sequence, String STR) {

        int strLength = STR.length();
        int sequenceLength = sequence.length();
        int maxCount = 0;

        sequence = sequence.toUpperCase();
        STR = STR.toUpperCase();

        int strHash = hash(STR, strLength);
        int firstHash = hash(sequence, strLength);

        // highest power
        int highestPower = 1;
        for (int i = 0; i < strLength-1; i++) {
            highestPower = (highestPower * R) % P
        }

//        HashMap<Character, Integer> letterMap = new HashMap<>();
//        letterMap.put('A', 0);
//        letterMap.put('G', 1);
//        letterMap.put('T', 2);
//        letterMap.put('C', 3);
//        letterMap.put('N', -1);


        //CHANGE ALL OF THIS FOR THE POLY ROLLY HASH FUNC >>>
//PSUEDOCODE
        // For the rest of the sequence (sequencelenght - strlength)
        // If the current hash equals to the str hash:
        //  while current hash substring starting from i + currentcount + strlength equals hash
        //     increment currentcount
        //  set max count = to max of max count and currentcount
        //  move i forward by the (currentcount - 1) * strlength (skips the consecutve matches)
        // if i is not at the last possible hash to compare in the sequence,
        //    updatehash by removing the leftmost character and adding the new rightmost character
        // return maxcount

        // >>>>>

        int[] numericSTR = new int[strLength];
        for (int i = 0; i < strLength; i++){
            if (letterMap.containsKey(STR.charAt(i)) && letterMap.get(STR.charAt(i)) != -1) {
                numericSTR[i] = letterMap.get(STR.charAt(i));
            }
        }

        for (int i = 0; i < sequenceLength - strLength; i++) {
            int currentCount = 0;

            while (i+strLength <= sequenceLength && matches(sequence, numericSTR, i, letterMap)) {
                currentCount++;
                i += strLength;
            }

            if (currentCount > maxCount) {
                maxCount = currentCount;
            }
        }
        return maxCount;

    }

    // <<<<

    // FINISHED HASH
    private static int hash(String str, int length) {
        int hashValue = 0;
        for (int i = 0; i < length; i++) {
            hashValue = (R * hashValue + str.charAt(i)) % P;
        }
        return hashValue;
    }

    // UPDATED UPDATE HASH
    private static int updateHash (String sequence, int i, int currentHash, int length, int highestPower){
        currentHash = (currentHash - sequence.charAt(i) * highestPower % P + P) % P;
        currentHash = (currentHash * R + sequence.charAt(i + length)) % P;
        return currentHash;
    }


//    // UPDATED MATCHES but dont need it bc it is monte carlo
//    private static boolean matches (String sequence, String STR, int index) {
//        for (int i = 0; i < STR.length(); i++) {
//            if (sequence.charAt(index+i) != STR.charAt(i)) {
//                return false;
//            }
//        }
//        return true;
//    }
}
