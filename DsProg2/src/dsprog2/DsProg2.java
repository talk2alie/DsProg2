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

        // get body of the web document
        String content = WebDoc.getBodyContent(url);  
        Set<String> ignore = new HashSet<>(Arrays.asList(new String[]{
            "after", "which", "later", "other", "during", "their", "about",}));

        Map<String, Integer> words = new HashMap<>();
        String word_pattern = "[A-Za-z]{5,}";
        int total = 0;
        
        Matcher match = Pattern.compile(word_pattern).matcher(content);

        while (match.find()) {
            
            String word = match.group().toLowerCase();

            /**ADD CODE
             * ignore any word in the "skip set, otherwise add
             * one more occurrence of key, word, into the words map
             */
            if (ignore.contains(word.toLowerCase())) {
                continue;
            }
            
            ++total;
            Integer currentCount = words.get(word);   
            if(currentCount == null){                   
                words.put(word, 1);
            }
            else{
                words.put(word, ++currentCount);
            }   
        }

        /**ADD CODE 
         * Iterate through words and store the Map entry pairs into
         * your array/list structure of WordFrequency (or Map.Entry) objects.
        
        */        
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : words.entrySet()){
            entries.add(entry);
        }
        
        /**ADD CODE 
         * Create a comparator for WordFrequency (or Map.Entry) objects
         * which compares by count. Then sort your array/list using this
         * comparator.
         */
        Comparator<Map.Entry<String, Integer>> comp = new Comparator(){

            @Override
            public int compare(Object item1, Object item2) {
                Map.Entry<String, Integer> lhs = (Map.Entry<String, Integer>)item1;
                Map.Entry<String, Integer> rhs = (Map.Entry<String, Integer>)item2;
                
                return rhs.getValue().compareTo(lhs.getValue());
            }            
        };
        entries.sort(comp);
        
        /**ADD CODE 
         * Print: 
         * total words entered into words (counting duplicates)
         * number of different words (ignore duplicates) up to maxPairs "word:
         * count" having the LARGEST count values.
         */
        System.out.printf("Words processed (with duplicates):\t%d\n", total);
        System.out.printf("Words processed (no duplicates):\t%d\n", 
                words.size());
        System.out.println("---------------------------------------------");
        for(int i = 0; i < numPairs; ++i){
            System.out.println(entries.get(i).getKey() + " : " + 
                    entries.get(i).getValue());
        }
    }
}
