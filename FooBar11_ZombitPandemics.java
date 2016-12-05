package foobar11_zombitpandemics;

import java.math.BigInteger;
import java.util.HashMap;

public class FooBar11_ZombitPandemics {
    
    public static void main(String[] args) {
        System.out.println(answer(50));
    }
    
    //Idea: Imagine we are traversing through all possible "worlds" of (labelled) rabbits
    //(each "world" is a unique arrangement of rabbits)
    //We count the total number of the rabbits in all worlds.
    //We infect the biggest warrens in all worlds and count the total number of infected rabbits
    //The answer is the amount of rabbits infected over the total rabbits.
    
    //Total and infected rabbits across all worlds
    public static BigInteger infectedRabbits;
    public static BigInteger totalRabbits;
    public static int numOfRabbits;
    
    public static String answer(int n) {
        numOfRabbits = n;
        infectedRabbits = zero;
        totalRabbits = pow(n - 1, n);
        
        letsTravelTheWorldsAndInfectSomeRabbits();
        
        BigInteger gcd = infectedRabbits.gcd(totalRabbits);
        return infectedRabbits.divide(gcd).toString() + "/" + totalRabbits.divide(gcd).toString();
    }
    

    //Different partitionings of the group of rabbits is listed.
    //For each partitioning, the infected rabbits will be
    //calculated and added to the total infected rabbits across all worlds
    public static void letsTravelTheWorldsAndInfectSomeRabbits(){
        //Integer sequence A000041 + fastest implementation
        //Sequence: https://oeis.org/A000041
        //Fastest implementation: http://jeromekelleher.net/category/combinatorics.html
        int[] a = new int[numOfRabbits];
        int k = 1,l,x,y = numOfRabbits-1;
        while (k != 0) {
            x = a[k - 1] + 1;
            k -= 1;
            while (x << 1 <= y) {
                a[k] = x;
                y -= x;
                k += 1;}
            l = k + 1;
            while (x <= y) {
                a[k] = x;
                a[l] = y;
                
                //found valid partiion
                if (foundRabbits(a, k + 2)) justDoIt(a,k+2);
                
                x += 1;
                y -= 1;}
            a[k] = x + y;
            y += x - 1;

            //found valid partiion
            if (foundRabbits(a, k + 1)) justDoIt(a,k+1);}
    }
    
    //just do some final stuffs to infect the rabbits in
    //all worlds belonging to each partitions:
    //calculating multiplicities, then the unique formations,
    //and finally maximum infected rabbits, and add the result to the total infected rabbits
    public static void justDoIt(int[] a, int end) {
        BigInteger multiplicities = one, count = one, rabbits = one;
        int biggest = 0, remaining = numOfRabbits;
        for (int i = 0; i < end; i++) {
            if (a[i]>biggest) biggest=a[i];
            if ((i< end-1) && (a[i]==a[i+1])){
                count = count.add(one);
                multiplicities = multiplicities.multiply(count);
            }
            if((i< end-1) &&(a[i]!=a[i+1])) count = one;
            
            rabbits = rabbits.multiply(
                    nCr(remaining,a[i])
                    .multiply(all(a[i]))
            );
            remaining -= a[i];
        }
        rabbits = rabbits.divide(multiplicities)
                .multiply(BigInteger.valueOf(biggest));
        infectedRabbits = infectedRabbits.add(rabbits);
    }

    //check if a partitioning is valid (all partitions > 1)
    public static boolean foundRabbits(int[] a, int end) {
        int i = 0;
        while (a[i] != 0 && i < end) {
            if (a[i] == 1) return false;
            i++;
        }
        return true;
    }

    //Integer sequence A000435 = A001864/n
    //Source: https://oeis.org/A001864
    //This method return the number of formations of a group of 
    //n (labelled) rabbits unite together as one large warren
    public static BigInteger all(int n) {
        //a(n) = sum(k=1, n-1, binomial(n, k)*(n-k)^(n-k)*k^k)/n
        if (all_MEM.containsKey(n)) return all_MEM.get(n);
        BigInteger result = zero;
        for (int i = 1; i <= n - 1; i++) 
            result = result.add(nCr(n, i)
                    .multiply(pow(n - i, n - i))
                    .multiply(pow(i, i))
            );
        result = result.divide(BigInteger.valueOf(n));
        all_MEM.put(n, result);
        return result;
    }

    //power function, taking int as input instead of BigInteger (for performance)
    public static BigInteger pow(int n, int k) {
        if (k == 0) return one;
        if (k == 1) return BigInteger.valueOf(n);
        BigInteger N = BigInteger.valueOf(n), result = N;
        for (int i = 0; i < k - 1; i++)
            result = result.multiply(N);
        return result;
    }

    //N choose R combinatorial function
    public static BigInteger nCr(int n, int r) {
        int key = (n << 6) + r;
        if (nCr_MEM.containsKey(key)) return nCr_MEM.get(key);
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < r; i++)
            result = result.multiply(BigInteger.valueOf(n - i)).divide(BigInteger.valueOf(i + 1));
        nCr_MEM.put(key, result);
        return result;
    }

    //better code reading
    public static BigInteger zero = BigInteger.ZERO;
    public static BigInteger one = BigInteger.ONE;
    public static BigInteger two = BigInteger.valueOf(2);

    //Memoization
    public static HashMap<Integer, BigInteger> all_MEM = new HashMap<Integer, BigInteger>();
    public static HashMap<Integer, BigInteger> nCr_MEM = new HashMap<Integer, BigInteger>();
}
