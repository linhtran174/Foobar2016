package foobar9_liningupthecaptives;

import java.math.BigInteger;
import java.util.HashSet;
public class FooBar9_LiningUpTheCaptives {

    public static void main(String[] args) throws Exception{
        //testCase5: 10,10,40
        //testCase4: 4,5,10
        System.out.println(answer(2, 2, 3));
        System.out.println(answer(1, 2, 6));
        System.out.println(answer(2, 3, 6));
        System.out.println(answer(4, 5, 10));
        System.out.println(answer(10, 10, 40));
        System.out.println(arrange(2, 2));
        
    }
    
    //memoization for arrage(int x,int n)
    public static BigInteger[][] arrangeMem;
    
    public static int testCase = 0;
    public static String answer(int x, int y, int n) throws Exception {
        testCase++;
        if(testCase ==1){
            arrangeMem = new BigInteger[40][40];
        }
        //because n>=3, x,y >=1, so x=y=1 means there is no possible arragement
        if ((x + y) == 2) {
            return "0";
        }
        if (x == 1 || y==1) {
            //place the tallest at the east-end, arrange the rest
            return arrange(y-1,n - 1).toString();
        }
        
        //possible positions for the tallest rabbit in line: ( x -> n-y+1)
        //this rabbit is always seen from both side
        int tallest;
        BigInteger possibleArrangement = BigInteger.ZERO;
        for (tallest = x; tallest <= n - y + 1; tallest++) {

            //foreach position of the tallest rabbit
            possibleArrangement = possibleArrangement.add(
                    //combinations of allocating remaining rabbits to 2 sides
                    nCr(n - 1, tallest - 1)
                    //number of arrangements of the left sub-line
                    .multiply(arrange(x - 1, tallest - 1))
                    //number of arrangements of the right sub-line
                    .multiply(arrange(y - 1, n - tallest)));
        }
        return possibleArrangement.toString();
    }

    //possible arrangements for a line of size n 
    //with x rabbit visible looking from one side.
    public static BigInteger arrange(int x, int n) {
        if (n <= 2) return BigInteger.ONE;
        
        //permutations of the remaining rabbits after placing the tallest to one edge
        if (x == 1) return fact(n - 1);
        
        //memoization
        if(arrangeMem[x][n]!=null) return arrangeMem[x][n];
        
        //possible positions for the tallest
        BigInteger possibleArrangement = BigInteger.ZERO;
        for (int tallest = x; tallest <= n; tallest++) {
            possibleArrangement = possibleArrangement.add(
                    nCr(n - 1, tallest - 1)
                    .multiply(arrange(x - 1, tallest - 1))
                    .multiply(fact(n - tallest))
            );
        }
        if(arrangeMem[x][n]==null) arrangeMem[x][n] = possibleArrangement;
        return possibleArrangement;
    }

    //N choose R combinatorial function
    public static BigInteger nCr(int n, int r) {
        BigInteger temp = BigInteger.ONE;
        for (int i = 0; i < r; i++) {
            temp = temp.multiply(BigInteger.valueOf(n - i)).divide(BigInteger.valueOf(i + 1));
        }
        return temp;
    }

    //factorial function
    public static BigInteger fact(long n) {
        if (n == 1) {
            return BigInteger.ONE;
        }
        BigInteger fact = BigInteger.ONE;
        for (long i = 1; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
    
    
}
