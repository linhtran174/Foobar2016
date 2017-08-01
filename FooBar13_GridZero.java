package com.google.challenges; 

import java.util.*;

public class Answer {   
    public static int answer(int[][] matrix) throws Exception {
        int N = matrix.length;
        if(N % 2 == 0){
            int[][] solutionMatrix = evenMatrixSolve(matrix);
            int result = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    result += solutionMatrix[i][j];
                }
            }
            return result;
        }
        else{
            int[] xorRow = new int[N];
            int[] xorCol = new int[N];
            for(int i =0; i < N; i++){
                for(int j = 0; j < N; j++){
                    xorRow[i] ^= matrix[i][j];
                    xorCol[j] ^= matrix[i][j];
                }
            }
            int commonXor = xorRow[0];
            for(int i = 0; i < N; i++){
                if(xorRow[i] !=  commonXor) return -1;
                if(xorCol[i] !=  commonXor) return -1;
            }
            
            //Try all ways to click the top row, and for each way, 
            //find the unique way to click left-most column so that the remaining 
            //even matrix has fewest lights to be clicked. Each permutation
            //of the top row then is associated with a number of clicks
            //The final solution is the one with minimum clicks among them.
            int[][] remainingMatrix = new int[N-1][N-1];
            int[][] solutionMatrix;
            int candidateCount, minCount = Integer.MAX_VALUE;
            
            //iterating through the ways 
            int numOfP = (int) Math.pow(2,(N-1)) - 1;
            for(int permutation = 0; permutation < numOfP; permutation++){
                candidateCount = 0;
                
                for(int i = 0; i < N-1; i++){
                    for(int j = 0; j < N-1; j++){
                        remainingMatrix[i][j] = matrix[i+1][j+1];
                    }
                }
                
                //apply row switches to the candidate remaining matrix
                int light;
                int countTop = 0;
                for(int i = 0; i < N-1; i++){
                    light = (permutation & (1 << i)) >> i;
                    countTop += light;
                    if(light == 1){
                        for(int j = 0; j < N-1; j++){
                            remainingMatrix[j][i] ^= 1;
                        }
                    }
                }
                candidateCount += countTop;
                
                //solve the remaining matrix
                solutionMatrix = evenMatrixSolve(remainingMatrix);
                
                
                int countSwitch = 0;
                int[] rowSum = new int[N-1];
                for(int i = 0; i < N-1; i++){
                    for(int j = 0; j < N-1; j++){
                        countSwitch += solutionMatrix[i][j];
                        rowSum[i] += solutionMatrix[i][j];
                    }
                }
                Arrays.sort(rowSum);
                
                //minClicks at index i represents the number of clicks needed to solve the
                //remaining square matrix if i rows are to be switched (by clicking the left-most column)
                int[] minClicks = new int[N];
                minClicks[0] = countSwitch;
                for(int i = 1; i < N; i++){
                    minClicks[i] = minClicks[i-1] + (N - 1 - 2*rowSum[N-1-i]);
                }
                
                //To make sure that the top-left light is off, the number of swiches in the 
                //left-most column plus in the top row must be even/odd if the top-left
                //light is off/on
                boolean columnSumOdd = ((matrix[0][0] + countTop) & 1) == 1;
                int minRemainingMatrix = Integer.MAX_VALUE;
                int remainingMatrixCount;
                if(columnSumOdd){
                    for(int numCol = 1; numCol < N-1; numCol += 2){
                        remainingMatrixCount = minClicks[numCol] + (N-1-numCol);
                        minRemainingMatrix = Math.min(minRemainingMatrix, remainingMatrixCount);
                    }
                }
                else{
                    for(int numCol = 0; numCol <= N-1; numCol += 2){
                        remainingMatrixCount = minClicks[numCol] + numCol;
                        minRemainingMatrix = Math.min(minRemainingMatrix, remainingMatrixCount);
                    }
                }
                
                candidateCount += minRemainingMatrix;
                minCount = Math.min(minCount, candidateCount);
            }
            
            return minCount;
        }
    } 
    
    //This function provides the unique and obviously optimal solution for
    //solving a matrix with both dimensions are even numbers.
    public static int[][] evenMatrixSolve(int[][] matrix){
        int N = matrix.length;
        int[][] solution = new int[N][N];
        int[] xorRow = new int[N];
        int[] xorCol = new int[N];
        
        for(int i =0; i < N; i++){
            for(int j = 0; j < N; j++){
                xorRow[i] ^= matrix[i][j];
                xorCol[j] ^= matrix[i][j];
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                solution[i][j] = matrix[i][j] ^ xorRow[i] ^ xorCol[j];
            }
        }
        return solution;
    }
}