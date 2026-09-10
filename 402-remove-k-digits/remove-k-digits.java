class Solution {
    public String removeKdigits(String num, int k) {

        int n = num.length();
        if ( n == k ) return "0";

        ArrayDeque<Character> st = new ArrayDeque<>();

        for (int i=0; i<n; i++){
            while (!st.isEmpty() && k > 0 && st.peek() > num.charAt(i)){
                st.pop();
                k = k - 1;
            }

            st.push(num.charAt(i));
        }

        while (k > 0){
            st.pop();
            k--;
        }

        StringBuilder result = new StringBuilder();

        while (!st.isEmpty()){
            result.append(st.pop());
        }

        result.reverse();
        int i=0;
        while (i<result.length() - 1 && result.charAt(i) == '0'){
            i++;
        }

        String finalAnswer = result.substring(i);
        return finalAnswer;
    }
}