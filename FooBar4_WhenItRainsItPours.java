package foobar4_WhenItRainsItPours;

public class FooBar4_WhenItRainsItPours {

    public static void main(String[] args) {
        System.out.println(answer(new int[]{1, 4, 2, 5, 1, 2, 3}));
        System.out.println(answer(new int[]{1, 2, 3, 2, 1}));
        System.out.println(answer(new int[]{2, 5, 1, 2, 3, 4, 7, 7, 6}));
        System.out.println(answer(new int[]{2, 5, 1, 3, 1, 2, 1, 7, 7, 6}));
        System.out.println(answer(new int[]{2, 7, 2, 7, 4, 7, 1, 7, 3, 7}));
        System.out.println(answer(new int[]{6, 7, 7, 4, 3, 2, 1, 5, 2}));
        System.out.println(answer(new int[]{2, 5, 1, 2, 3, 4, 7, 7, 6, 2, 7, 1, 2, 3, 4, 5, 5, 4}));
    }

    public static int answer(int[] heights) {
        //find the highest stack of hutches:
        int highest = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] > highest) {
                highest = heights[i];
            }
        }

        //pouring water from the top (highest) to the ground
        int totalTrappedWater = 0;
        for (int i = highest; i > 0; i--) {
            //trapped water at current level supposing no water were able to escape
            int levelTrappedWater = 0;
            for (int j = 0; j < heights.length; j++) {
                if (heights[j] < i) {
                    levelTrappedWater++;
                }
            }

            //water escaping from left side
            for (int j = 0; j < heights.length; j++) {
                if (heights[j] >= i) {
                    break;
                }
                levelTrappedWater--;
            }
            //water escaping from right side
            for (int j = heights.length - 1; j >= 0; j--) {
                if (heights[j] >= i) {
                    break;
                }
                levelTrappedWater--;
            }

            //add trapped water to total water:
            totalTrappedWater += levelTrappedWater;
        }

        return totalTrappedWater;
    }

}
