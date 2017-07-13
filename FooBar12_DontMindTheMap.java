package com.google.challenges; 

import java.util.*;

public class Answer {
    
    // public static int testCase = 0;
    public static int answer(int[][] subway) {
        if(hasMeetingPath(subway, -1)) return -1;
        for (int station = 0; station < subway.length; station++) {
            //station denotes the closed station
            //station = -1 means no station is closed
            if (hasMeetingPath(closeStation(subway, station), -1)){
                return station;
            }
        }
        return -2;
    }

    public static boolean hasMeetingPath(int subway[][]) {

        int numStation = subway.length;
        int numLine = subway[0].length;
        
        List<List<Set<Integer>>> rSubway = buildReversedSubway(subway);
        
        //init starting states
        LinkedList<Set<Integer>> stateStack = new LinkedList<>();
        for(List<Set<Integer>> byStation : rSubway){
            for(Set<Integer> byLine : byStation){
                if(byLine.size() > 1) 
                stateStack.add(byLine);
            }
        }
        
        Set<Set<Integer>> visitedStates = new HashSet<>();
        Set<Integer> currentState, nextState;
        while(stateStack.size() != 0){
//            System.out.println("stateStack: " + stateStack);
//            System.out.println("visitedStates: " + visitedStates);
            
            currentState = stateStack.pop();
            visitedStates.add(currentState);
            
            for (int line = 0; line < subway[0].length; line++) {
                nextState = new HashSet<>();
                
                boolean isStateReachable = true;
                for (Integer station : currentState) {
                    if(rSubway.get(station).get(line).isEmpty()){
                        isStateReachable = false;
                        break;
                    }
                    nextState.addAll(
                        rSubway.get(station).get(line)
                    );
                }
                if(!isStateReachable) continue;
                
                if (nextState.size() == numStation) {
                    return true;
                }
                if (!visitedStates.contains(nextState)) {
//                    System.out.println("new state: " + nextState);
                    stateStack.push(nextState);
                }
            }
            
            // trimState(stateStack);
        }
        
        return false;
    }
    
    public static int[][] closeStation(int[][] subway, int closedStation){
        for(int i = 0; i < subway.length; i++){
            for(int j = 0; j < subway[0].length; j++){
                if(subway[i][j] == closedStation){
                    if(subway[closedStation][j] == closedStation){
                        subway[i][j] = i;
                    }
                    else{
                        subway[i][j] = subway[closedStation][j];
                    }
                }
            }
        }
        
        int[][] newSubway = new int[subway.length - 1][subway[0].length];
        int newIdx = 0;
        for(int i = 0; i < subway.length; i++){
            if(i == closedStation) continue;
            for(int j = 0; j < subway[0].length; j++){
                newSubway[newIdx][j] = (subway[i][j] > closedStation)?
                        subway[i][j] - 1 : subway[i][j];
            }
            newIdx++;
        }
        
        return newSubway;
    }
    
    public static List<List<Set<Integer>>> buildReversedSubway(int[][] subway, int closedStation){
        List<List<Set<Integer>>> rSubway = new ArrayList<>();
        // s - station
        // l - line
        for(int s = 0; s < subway.length; s++){
            List<Set<Integer>> list = new ArrayList<>();
            for(int l = 0; l < subway[0].length; l++){
                list.add(new HashSet<Integer>());
            }
            rSubway.add(s, list);
        }

        for(int l = 0; l < subway[0].length; l++){
            for(int s = 0; s < subway.length; s++){
                int destination = subway[s][l];
                rSubway
                    .get(destination)
                    .get(l)
                    .add(s);
            }
        }
        
        return rSubway;
    }
}