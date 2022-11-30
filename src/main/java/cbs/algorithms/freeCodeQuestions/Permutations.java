package cbs.algorithms.freeCodeQuestions;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    /*
     * 
     * Return the number of total permutations of the provided string that don't
     * have repeated consecutive letters. Assume that all characters in the provided
     * string are each unique.
     * 
     * For example, aab should return 2 because it has 6 total permutations (aab,
     * aab, aba, aba, baa, baa), but only 2 of them (aba and aba) don't have the
     * same letter (in this case a) repeating.
     * 
     */

    /**
     * Does a list of permutations
     * 
     * @param word
     * @return
     */
    public static List<String> getPermutations(final String word) {
        return doPermutations(word, 0, word.length());
    }

    private static List<String> doPermutations(final String str, int init, int end) {
        List<String> permutations = new ArrayList<>();
        
        if (init == end - 1) {
            permutations.add(str);
            return permutations;
        }
        String permutted;
        String toPermut = str;;
        for (int i = init; i < end; i++) {
            permutted = swap(toPermut, init, i);
            permutations.addAll(doPermutations(permutted, init + 1, end));
            toPermut = (swap(permutted, init, i));
        }
        
        return permutations;        
    }

    private static String swap(final String str, int a, int b) {
        char[] chars = str.toCharArray();
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
        return new String(chars); 
    }

    public static List<String> noConsecutiveRepetitions(final List<String> toCheck) {
        return toCheck.stream().filter(w -> consecutiveLetters(w) == false).toList();
    }

    private static boolean consecutiveLetters(final String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) == word.charAt(i + 1)) {
                return true;
            }
        }

        return false;
    }
}
