package foobar3_accessCodes;



public class FooBar3_AccessCodes {   
    //Uses of collections are avoided intentionally
    
    public static int answer(String[] x) throws Exception {
        
        //x is treated as a set, removal of element by setting a predefined value: "A"
        
        //BubbleSort style search: remove all identical codes from the set
        for (int i = 0; i < x.length - 1; i++) {
            //skip removed element
            if (x[i].equals("A")) continue;
            for (int j = i + 1; j < x.length; j++) {
                //if(identical)
                if (!x[j].equals("A") && x[i].equals(x[j])) {
                    x[j] = "A"; //remove element
                }
                //if(backward identical)
                else if(!x[j].equals("A") && x[i].equals(reverse(x[j])))
                {
                    x[j] = "A";
                }
            }
        }
        
        int distinctCodes = 0;
        //Count remaining elements in set
        for (int i = 0; i < x.length; i++) {
            if (!x[i].equals("A")) {
                distinctCodes++;
            }
        }
        
        return distinctCodes;
    }

    public static String reverse(String input) {
        char[] in = input.toCharArray();
        int begin = 0;
        int end = in.length - 1;
        char temp;
        while (end > begin) {
            temp = in[begin];
            in[begin] = in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }
    
}