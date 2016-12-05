package foobar7_hashitout;

public class FooBar7_HashItOut {

    public static void main(String[] args) {

        System.out.println(answer(
                new int[]{0, 129, 3, 129, 7, 129, 3, 129, 15, 129, 3, 129, 7, 129, 3, 129})
                .toString()
        );
        
        System.out.println(answer(
                new int[]{0, 129, 5, 141, 25, 137, 61, 149, 113, 145, 53, 157, 233, 185, 109, 165})
                .toString()
        );
        

    public static int[] answer(int digest[]) {
        int[] message = new int[16];
        int afterMultiply;
        for (int i = 0; i < 16; i++) {
            afterMultiply = (i==0)?digest[i]:(digest[i] ^ message[i - 1]);
            if ((afterMultiply % 2) == 0) {
                message[i] = afterMultiply;
            } else {
                if (afterMultiply>>7==1){
                    message[i] = afterMultiply - 128;
                }
                else{
                    message[i] = afterMultiply + 128;
                }
            }
        }
        return message;

    }

}
