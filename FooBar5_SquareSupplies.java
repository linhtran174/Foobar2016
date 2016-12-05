
package foobar5_squareSupplies;

public class FooBar5_SquareSupplies {

    public static void main(String[] args) {
        System.out.println(answer(24));
        System.out.println(answer(160));
    }
    
    public static int answer(int n){
        //base cases => performance gain
        if(n==0) return 0;
        if(n==1) return 1;
        if(n==2) return 2;
        if(n==3) return 3;
        if(n==4) return 1;
        
        //find and buy the biggest possible square and then recursively do it again
        //int biggest = (int)Math.sqrt((double)n);
        return 1 + answer(n-(int)Math.sqrt((double)n)*(int)Math.sqrt((double)n));
    }
    
    
}
