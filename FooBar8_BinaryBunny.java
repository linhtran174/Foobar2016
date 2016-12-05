package foobar8;

import java.math.BigInteger;

public class FooBar8_BinaryBunny {

    public static void main(String[] args) {
        System.out.println(answer(new int[]{5, 2, 9, 1, 3, 8, 10}));
        System.out.println(answer(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        System.out.println(answer(new int[]{12,6,18,3,9,15,21,1,4,7,10,14,16,20,23,0,2,5,8,11,13,17,19,22,24}));
        System.out.println(
        nCr(24,12));//*nCr(11,6)*nCr(5,3)*nCr(4,2)*2*nCr(11,6)*nCr(5,3)*nCr(4,2)*2) ;
        
    }

    public static class Node {
        Node left;
        Node right;
        int value;
        int numOfChildren;
        public Node(int value) {
            this.value = value;
            this.numOfChildren = 0;
        }
    }

    public static String answer(int[] input) {
        //testCase 4:
        //root.numOfChildren ==24;
        
        //tree building:
        Node root = new Node(input[0]); //rootNode;
        for (int i = 1; i < input.length; i++) {
            add(root, new Node(input[i]));
        }
        
        return permutation(root).toString();
    }
    
    //return the possible permutations for a tree/sub-tree
    public static BigInteger permutation(Node root) {
        if((root ==null) ||(root.numOfChildren < 2)) return BigInteger.ONE;
        if(root.left==null) return permutation(root.right);
        return nCr(root.numOfChildren,root.left.numOfChildren + 1).multiply(permutation(root.left)).multiply(permutation(root.right));
    }

    //N choose R combinatorial function
    public static BigInteger nCr(int n,int r) {
    BigInteger temp = BigInteger.ONE;
    for (int i = 0; i < r; i++) {
        temp = temp.multiply(BigInteger.valueOf(n-i)).divide(BigInteger.valueOf(i+1));
    }
    return temp;
}

    //tree building helper-function (add node to root)
    public static int add(Node root, Node newNode) {
        if (newNode.value < root.value) {
            if ((root.left == null)) {
                root.left = newNode;
            } else {
                add(root.left, newNode);
            }
        }
        if (newNode.value > root.value) {
            if (root.right == null) {
                root.right = newNode;
            } else {
                add(root.right, newNode);
            }
        }
        root.numOfChildren++;
        return 0;
    }

}
