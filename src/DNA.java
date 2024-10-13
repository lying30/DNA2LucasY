import java.util.HashMap;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [YOUR NAME HERE]
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        // either clean to be all uppercase or lowercase, but worried about runtime of cleaning
        // map all the indexes of the STR
        // largest number of indexes next to each other in a row
        //


        // Hash system that allows STR to have a number system associated with it
        //
        // go to the index of STR, then if current = sequence.substring(sequence.indexOf(STR)) points to 1
        // if first substring points to 1 then add 1 to a tally, and then move forward the length of the STR
        // if there is another STR at that new index after the length of the first STR add another 1 to tally
        // Then continue until it doesn't point to 1 and points to a null value instead
        // When it points to the null value, set the tally equal to Largest tally, and then set tally = 0
        // Repeat method while tracking the new largest tally, and then continuing at the new index of where it is in the sequence

        // iterative starting at 0: placeInString


        HashMap<Character, Integer> letterMap = new HashMap<>();
        letterMap.put('A', 0);
        letterMap.put('G', 1);
        letterMap.put('T', 2);
        letterMap.put('C', 3);


        return 0;
    }
}
