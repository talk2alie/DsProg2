/**
 * @author PUT YOUR NAME HERE
 */
package dsprog2;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.WebDoc;

/**
 *
 * @author talk2_000
 */
public class DsProg2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String url;
        url = "http://en.wikipedia.org/wiki/Jimi_Hendrix";
        // try other url's as well
        final int numPairs = 30; // maximum number of pairs to print

        String content = WebDoc.getBodyContent(url); // get body of the web document

        Set<String> ignore = new HashSet<>(Arrays.asList(new String[]{
            "after", "which", "later", "other", "during", "their", "about",}));

        Map<String, Integer> words = new HashMap<>();
        String word_pattern = "[A-Za-z]{5,}";
        int total = 0;

        Matcher match = Pattern.compile(word_pattern).matcher(content);

        while (match.find()) {

            String word = match.group().toLowerCase();

            if (ignore.contains(word)) {
                continue;
            }

            if(words.containsKey(word)){
                
                Integer currentCount = words.get(word);
                words.put(word, ++currentCount);
            }
            else{
                words.put(word, 1);
            } 
            
            System.out.println(word);
        }

        class WordCount {

            String word;
            Integer count;

            WordCount(String word, Integer count) {
                this.word = word;
                this.count = count;
            }
        }

        /**
         * ADD CODE Iterate through words and store the Map entry pairs into
         * your array/list structure of WordFrequency (or Map.Entry) objects.
         */
        /**
         * ADD CODE Create a comparator for WordFrequency (or Map.Entry) objects
         * which compares by count. Then sort your array/list using this
         * comparator.
         */
        /**
         * ADD CODE Print: total words entered into words (counting duplicates)
         * number of different words (ignore duplicates) up to maxPairs "word:
         * count" having the LARGEST count values.
         */
    }

}
