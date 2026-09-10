import java.util.*;
class Solution {
    

    public int[] asteroidCollision(int[] asteroids) {

        int n = asteroids.length;
        Deque<Integer> st = new ArrayDeque<>();

        for (int i=0; i<n; i++){
            if (asteroids[i] > 0){
                st.push(asteroids[i]);
            } else {
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(asteroids[i])){
                    st.pop();
                } 

                if (!st.isEmpty() && st.peek() > 0 && st.peek() == Math.abs(asteroids[i])){
                    st.pop();
                } else if (st.isEmpty() || st.peek() < 0){
                    st.push(asteroids[i]);
                }
            }
        }

        // Convert stack to array 
        int size = st.size(); 
        int[] result = new int[size]; 
        for (int i = size - 1; i >= 0; i--) { 
            result[i] = st.pop(); 
        } 
        return result;
    }       
}