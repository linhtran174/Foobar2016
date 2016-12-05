package foobar7_shortencodeforfun;

import java.util.ArrayList;

public class FooBar7_ShortenCodeForFun {

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println((answer(
                new int[]{0, 129, 5, 141, 25, 137, 61, 149, 113, 145, 53, 157, 233, 185, 109, 165})
                )[i]);
        }
        
    }
    
    public static int[] answer(int digest[]) {
        int[] message = new int[16];
        for (int i = 0; i < 16; i++) {
            message[i] = (((i==0)?digest[i]:(digest[i] ^ message[i - 1]))%2==0)?((i==0)?digest[i]:(digest[i] ^ message[i - 1])):((((i==0)?digest[i]:(digest[i] ^ message[i - 1]))>>7==1)?((i==0)?digest[i]:(digest[i] ^ message[i - 1]))-128:((i==0)?digest[i]:(digest[i] ^ message[i - 1]))+128);
        }
        return message;
    }
}
