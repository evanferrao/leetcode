class Solution {
    public int trap(int[] height) {
        int lMax = 0;
        int rMax = 0;

        int total = 0;
        int l = 0;
        int r = height.length -1;
        while (l<r){
            if (height[l] <= height[r]){
                lMax = Math.max(lMax, height[l]); // move l ahead and update lmax if needed
                total += lMax - height[l];
                l++;
            } else if (height[r] < height[l]){
                rMax = Math.max(rMax, height[r]);
                total += rMax - height[r];
                r--;
            }
        }

        return total;
    }
}