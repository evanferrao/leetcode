class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] result = new int[n - k + 1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        // First k elements
        for (int i = 0; i < k; i++) {

            // Remove smaller elements from the BACK
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.removeLast();
            }

            // Add new index to the BACK
            dq.addLast(i);
        }

        // Maximum is at the FRONT
        result[0] = nums[dq.peekFirst()];

        for (int i = k; i < n; i++) {

            // Remove expired index from the FRONT
            if (!dq.isEmpty() && dq.peekFirst() == i - k) {
                dq.removeFirst();
            }

            // Remove smaller elements from the BACK
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.removeLast();
            }

            // Add new index to the BACK
            dq.addLast(i);

            // Maximum is at the FRONT
            result[i - k + 1] = nums[dq.peekFirst()];
        }

        return result;
    }
}