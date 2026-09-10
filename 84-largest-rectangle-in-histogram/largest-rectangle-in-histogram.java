class Solution {
    public int largestRectangleArea(int[] arr) {
        int n = arr.length;
        ArrayDeque<Integer> st = new ArrayDeque<>();
        int maxArea = 0;
        int nse, pse;

        for (int i=0; i<n; i++){
            while (!st.isEmpty() && arr[st.peek()] > arr[i]){
                int element = st.pop();
                nse = i;
                pse = st.isEmpty() ? -1 : st.peek(); 
                int newArea = arr[element] * (nse - pse - 1);
                maxArea = Math.max(maxArea, newArea);
            }

            st.push(i);
        }

        while (!st.isEmpty()){
            nse = n;
            int element = st.pop();
            pse = st.isEmpty() ? -1 : st.peek();
            int newArea = arr[element] * (nse - pse - 1);
            maxArea = Math.max(maxArea, newArea);
        }

        return maxArea;
    }
}
