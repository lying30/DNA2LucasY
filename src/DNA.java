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

    // Radix for ASCII values
    private static final int R = 256;
    // Large prime number
    private static final int P = 1000000007;

    public static int STRCount(String sequence, String STR) {

        int strLength = STR.length();
        int sequenceLength = sequence.length();
        int maxCount = 0;

        // If the sequence is shorter than the STR, no matches are possible
        if (sequenceLength < strLength) {
            return 0;
        }

        // Convert both to uppercase for case-sensitive matching
        sequence = sequence.toUpperCase();
        STR = STR.toUpperCase();

        // Get hash for the STR and first hash in sequence
        long strHash = hash(STR, strLength);
        long currentHash = hash(sequence, strLength);

        // Precompute the highest power of the radix (R^(strLength-1) % P) to efficiently adjust the rolling hash
        long highestPower = 1;
        for (int i = 0; i < strLength - 1; i++) {
            highestPower = (highestPower * R) % P;
        }

        // Traverse the sequence and compare the rolling hashes
        for (int i = 0; i <= sequenceLength - strLength; i++) {
            // If the current hash matches the STR's hash, start counting consecutive repeats
            if (strHash == currentHash) {
                int currentCount = 0;

                // Count consecutive matches of the STR using hash comparisons
                while (i + currentCount * strLength <= sequenceLength - strLength) {
                    // Get hash of the next blocking
                    long nextHash = hash(sequence.substring(i + currentCount * strLength, i + (currentCount + 1) * strLength), strLength);

                    // If the hashes match increase the count
                    if (nextHash == strHash) {
                        currentCount++;
                    } else {
                        // Stop counting if they don't match
                        break;
                    }
                }
                // Update max count if that consecutive run of STRs is larger than the previous largest count
                maxCount = Math.max(maxCount, currentCount);
                // Move the index forward by the length of the matched STR block to avoid overlap
                i += (currentCount - 1) * strLength;
            }
            // Update the rolling hash for the next window to compare as long as it is not at the end of the sequence
            if (i < sequenceLength - strLength) {
                currentHash = updateHash(sequence, i, currentHash, strLength, highestPower);
            }

        }
        return maxCount;

    }

    // Computes the polynomial rolling hash for a given string of a given length
    private static long hash(String str, int length) {
        long hashValue = 0;
        // Use a rolling polynomial hash to compute hash value
        for (int i = 0; i < length; i++) {
            hashValue = (R * hashValue + str.charAt(i)) % P;
        }
        return hashValue;
    }

    // UPDATED UPDATE HASH
    private static long updateHash(String sequence, int i, long currentHash, int length, long highestPower) {

        // Remove the leading character from the calculating the current hash
        currentHash = (currentHash - sequence.charAt(i) * highestPower % P + P) % P;

        // Add the next character's and getting the new hash
        currentHash = (currentHash * R + sequence.charAt(i + length)) % P;

        // Return the updated hash value for the new window to look at
        return currentHash;
    }

}
