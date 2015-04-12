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
        //String content = "After the day I got here, I was mesmerized by the loyalty of their children. As the children came to me, I found that" +
        //                 "I was also mesmerized by their sister whom I loved very much. I anticipate no struggle as we battle for the " +
        //                 "loyalty of our brothers and sister. The kind of loyalty about which you can survive. mesmerized";
        Set<String> ignore = new HashSet<>(Arrays.asList(new String[]{
            "after", "which", "later", "other", "during", "their", "about",}));

        Map<String, Integer> words = new HashMap<>();
        String word_pattern = "[A-Za-z]{5,}";
        int total = 0;
        int uniqueCount = 0;
        
        Matcher match = Pattern.compile(word_pattern).matcher(content);

        while (match.find()) {
            
            String word = match.group().toLowerCase();

            if (ignore.contains(word.toLowerCase())) {
                continue;
            }
            ++total;
            Integer currentCount = words.get(word);   
            if(currentCount == null){                   
                words.put(word, 1);
                ++uniqueCount;
            }
            else{
                words.put(word, ++currentCount);
            }            
            
        }

        /**
         * ADD CODE Iterate through words and store the Map entry pairs into
         * your array/list structure of WordFrequency (or Map.Entry) objects.
        
        */
        
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : words.entrySet()){
            entries.add(entry);
        }
        
        /**
         * ADD CODE Create a comparator for WordFrequency (or Map.Entry) objects
         * which compares by count. Then sort your array/list using this
         * comparator.
         */
        Comparator<Map.Entry<String, Integer>> comp = new Comparator(){

            @Override
            public int compare(Object t, Object t1) {
                Map.Entry<String, Integer> lhs = (Map.Entry<String, Integer>)t;
                Map.Entry<String, Integer> rhs = (Map.Entry<String, Integer>)t1;
                
                return rhs.getValue().compareTo(lhs.getValue());
            }            
        };
        
        //System.out.println("");
        
        
//        for(int i = entries.size() - 1; i >=0; --i){
//            System.out.println(entries.get(i).getKey() + " : " + entries.get(i).getValue());
//        }
        
        //System.out.println("");
        entries.sort(comp);
        /**
         * ADD CODE Print: total words entered into words (counting duplicates)
         * number of different words (ignore duplicates) up to maxPairs "word:
         * count" having the LARGEST count values.
         */
        System.out.println(total);
        System.out.println(uniqueCount);
        
        int printed = 0;
        for(Map.Entry<String, Integer> entry : entries){
            ++printed;
            System.out.println(entry.getKey() + " : " + entry.getValue());
            if(printed == numPairs){break;}
        }
    }

}
