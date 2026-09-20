class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        for (int i=0; i<s.length(); i++){
            Character ch = s.charAt(i);
            int reversedIndex = 'z' - ch + 1;
            int index = i+1;
            int product = reversedIndex * index;
            sum += product;
        }

        return sum;
    }
}