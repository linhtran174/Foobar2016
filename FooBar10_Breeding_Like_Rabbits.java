package foobar10_breeding_like_rabbits;

import java.util.HashMap;
import java.math.BigInteger;

public class FooBar10_Breeding_Like_Rabbits {

    public static void main(String[] args) {
        System.out.println(answer("7"));
        System.out.println(answer("22"));
        System.out.println(answer("100"));
        
    }

    //    We can easily prove that R(2n+1) and R(2n) is always increasing
    //    with respect to n (but R(n) is not) by recursive method.
    //    This increase seems close to linear rate as R(n) is the addition of
    //    3 approximately half-smaller components
    //    R(2n+1) is also provable to be smaller than R(2n) for the same n.
    //    This method implement binary search starting from n = str_S
    public static String answer(String str_S) {
        BigInteger zombits = new BigInteger(str_S);
        
        BigInteger currentRN, currentN;
        BigInteger lowerBound = zero, upperBound = zero;
        BigInteger temp;
        int isBigger = 0;
        
        //supposing the time is odd, perform binary search for increasing R(2n+1) sequence
        //choose a reasonable starting point
        currentN = (zombits.mod(two).compareTo(zero)==0)
            ?zombits.add(one):zombits;
        currentRN = R(currentN);
        
        //while current R(n) value different from str_S
        while((isBigger = currentRN.compareTo(zombits))!=0){
            if(isBigger>0) upperBound = currentN;    
            else lowerBound = currentN;
            
            if(upperBound.subtract(lowerBound).compareTo(two)<=0) break;
            
            if(upperBound.compareTo(zero)!=0){
                temp = upperBound.add(lowerBound).divide(two);
                //currentN is ensured to be odd
                currentN = (temp.mod(two).compareTo(zero)==0)?temp.add(one):temp;
                currentRN = R(currentN);
            }
            else{
                currentN = currentN.multiply(two).add(one);
                currentRN = R(currentN);
            }
            
        }
        if(isBigger==0) return currentN.toString();
        isBigger = 0;
        upperBound = lowerBound = zero;
        
        //supposing the time is even, perform binary search for increasing R(2n) sequence
        //choose a reasonable starting point
        currentN = (zombits.mod(two).compareTo(zero)!=0)
            ?zombits.add(one):zombits;
        currentRN = R(currentN);
        
        //while current R(n) value different from str_S
        while((isBigger = currentRN.compareTo(zombits))!=0){
            if(isBigger>0) upperBound = currentN;    
            else lowerBound = currentN;
            
            if(upperBound.subtract(lowerBound).compareTo(two)<=0) break;
            
            if(upperBound.compareTo(zero)!=0){
                temp = upperBound.add(lowerBound).divide(two);
                //currentN is ensured to be even
                currentN = (temp.mod(two).compareTo(zero)!=0)?temp.add(one):temp;
                currentRN = R(currentN);
            }
            else{
                currentN = currentN.multiply(two);
                currentRN = R(currentN);
            }
            
        }
        if(isBigger==0) return currentN.toString();
        
        return "None";
    }
    
    //better code reading:
    public static BigInteger two = BigInteger.valueOf(2);
    public static BigInteger one = BigInteger.ONE;
    public static BigInteger zero = BigInteger.ZERO;
    
    //Memoization for R(n)
    public static HashMap<BigInteger, BigInteger> R_MEM = new HashMap<BigInteger, BigInteger>();
    public static BigInteger R(BigInteger n) {
        if (n.compareTo(two)<0) {
            return one;
        }
        if (n.compareTo(two)==0) {
            return two;
        }
        if (R_MEM.containsKey(n)) {
            return R_MEM.get(n);
        }
        if (n.mod(two)==zero){
            R_MEM.put(n, R(n.divide(two))
                    .add(R(n.divide(two).add(one)))
                    .add(n.divide(two))
            );
            return R_MEM.get(n);
        } else {
            R_MEM.put(n, R( n.add(BigInteger.valueOf(-3)).divide(two ))
                    .add(R( n.add(BigInteger.valueOf(-1)).divide(two) ))
                    .add(one));
            return R_MEM.get(n);
        }
    }

}
