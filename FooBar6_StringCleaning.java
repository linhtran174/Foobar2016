package foobar6_StringCleaning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FooBar6_StringCleaning {
    public static void main(String[] args) throws Exception {
        
        System.out.println(new String(answer("lololololo","lol")));
        System.out.println(new String(answer("goodgooogoogfogoood","goo")));
        System.out.println(new String(answer("xxxxxxxxyyyyyyyyyy","xy")));
        System.out.println(new String(answer("xyxyxyxxyxxyxxyxyxyx","xyxyx")));
        System.out.println(new String("Eo").toCharArray().length);
        
    }
    
    public static String answer(String chunk, String word) throws Exception {
        StringBuilder word1 = new StringBuilder(word);
        if(word.length() ==1){
            return chunk.replace(word,"");
        }
        StringBuilder chunk1 = new StringBuilder(chunk);
        if(!word1.equals(word1.reverse())){
            String newChunk = chunk.replaceFirst(word,"");
            while(!chunk.equals(newChunk)){
                chunk = newChunk;
                newChunk = newChunk.replace(word, "");
            }
            return chunk;
        }
        else return answer(chunk1,word).toString();
    }
    
    public static StringBuilder answer(StringBuilder chunk,String word){
        if(chunk.length()==0) return chunk;
        
        //find all the occurrences
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i < chunk.length()-word.length()+1; i++) {
            if(chunk.substring(i,i+word.length()).equals(word)) 
                occurrences.add(i);
        }
        if(occurrences.isEmpty()) return chunk;
        
        List<String> possibleStrings = new ArrayList<String>();
        StringBuilder buffer;
        for (Integer occurrence : occurrences){
            //try to remove an occurance
            buffer = new StringBuilder(chunk);
            buffer.delete(occurrence, occurrence + word.length());
            possibleStrings.add(answer(buffer,word).toString());
        }
        
        //sort for shortest string
        Iterator<String> i = possibleStrings.iterator();
        String shortest = i.next();
        String current;
        while(i.hasNext()&&possibleStrings.size()>1){
            current = i.next();
            if( current.length() > shortest.length())
                i.remove();
            else if (current.length() == shortest.length()){
                if(current.compareTo(shortest)>0)
                    i.remove();
                else
                    shortest = current;
            }
            else
                shortest = current;
        }
        
        return new StringBuilder(shortest);
    }
   
    
 
}
