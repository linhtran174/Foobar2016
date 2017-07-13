package foobar12.dontmindthemap;

import java.io.PrintStream;
import java.util.*;

public class FooBar12DontMindTheMap {

    public static void main(String[] args) throws Exception {
        int[][] input = {
{2 , 3},
{2 , 4},
{0 , 5},
{4 , 0},
{5 , 1},
{3 , 2},

    };
        long start = System.currentTimeMillis();
        System.out.println(answer(input));
        long end = System.currentTimeMillis();
        
        System.out.println(end-start);

    }

    public static int answer(int[][] subway) {
        for (int station = -1; station < subway.length; station++) {
            //station denotes the closed station
            //station = -1 means no station is closed
            if (hasMeetingPath(subway, station)) {
                return station;
            }
        }
        return -2;
    }

    public static boolean hasMeetingPath(int subway[][], int closedStation) {
        System.out.println("closeStation: " + closedStation);
        //A state is a HashSet of Integers representing the stations that everybody is on.
        //Our missions is to find whether there is a path 
        //from the initial state - where people are distributed among all stations
        //to an end state - where people are all at only 1 station
        HashSet<HashSet<Integer>> visitedStates = new HashSet<>();
        HashSet<Integer> initialState = new HashSet<>();
        LinkedList<HashSet<Integer>> stateStack = new LinkedList<>();

        for (int s = 0; s < subway.length; s++) {
            if (s != closedStation) {
                initialState.add(s);
            }
        }
        stateStack.push(initialState);

        HashSet<Integer> currentState, transitionState;

        while (stateStack.size() != 0) {
            System.out.println("stack: " + stateStack);
            currentState = stateStack.pop();
            visitedStates.add(currentState);

            for (int line = 0; line < subway[0].length; line++) {
                transitionState = new HashSet<>();
                for (Integer station : currentState) {
                    int nextStation = subway[station][line];
                    if (nextStation != closedStation) {
                        transitionState.add(nextStation);
                    } else if (nextStation == subway[nextStation][line]) {
                        transitionState.add(station);
                    } else {
                        transitionState.add(subway[nextStation][line]);
                    }

                }

                if (transitionState.size() == 1) {
                    return true;
                }

                if (!visitedStates.contains(transitionState)) {
//                    System.out.println("new state: " + transitionState);
                    stateStack.push(transitionState);
                }
            }

            //trimState will check if one candidate state is less optimized than another and trim it out of the state tree.
            //For example: {1,2,3} is less optimised than {1,2} and will be removed 
            trimState(stateStack);
        }

        return false;
    }

    public static void trimState(LinkedList<HashSet<Integer>> candidates) {
        //Marking all to-be-trimmed candidates by emptying them and adding -1
        HashSet<Integer> a, b;
        for (int i = 0; i < candidates.size(); i++) {
            a = candidates.get(i);
            for (int j = i + 1; j < candidates.size(); j++) {
                b = candidates.get(j);
                if (a.size() > b.size()) {
                    if (a.containsAll(b)) {
                        a.clear();
                        a.add(-1);
                    }
                } else if (b.containsAll(a)) {
                    b.clear();
                    b.add(-1);
                }
            }
        }

        //Remove all set with size == 1
        //We dont need to check value -1 because all candidates have size > 1. If the size is 1, it would have been returned instead of being added to candidates list 
        for (Iterator<HashSet<Integer>> i = candidates.iterator(); i.hasNext();) {
            if (i.next().size() == 1) {
                i.remove();
            }
        }
    }

    public static int minState(int[][] subway) {
        HashSet<Integer> before = new HashSet<>();
        HashSet<Integer> after = new HashSet<>();
        HashSet<Integer> temp;

        int line = 0;
        for (int s = 0; s < subway.length; s++) {
            before.add(s);
            after.add(subway[s][line]);
        }

        while (before.size() != after.size()) {
            //switch before and after, and clear after.
            temp = before;
            before = after;
            after = temp;
            after.clear();

            for (Integer s : before) {
                after.add(subway[s][line]);
            }
        }

        return before.size();
    }

}
