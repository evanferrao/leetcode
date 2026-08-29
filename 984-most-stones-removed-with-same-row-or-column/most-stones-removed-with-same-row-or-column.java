class Solution {
    class DisjointSet {
        List<Integer> rank = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i < n; i++) {
                rank.add(0);
                parent.add(i);
                size.add(1);
            }
        }

        public int findUltimateParent(int node) {
            if (parent.get(node) == node) {
                return node;
            } else {
                int ulp = findUltimateParent(parent.get(node));
                parent.set(node, ulp);
                return ulp;
            }
        }
        
        
        public void unionBySize(int u, int v){
            int ultimateParentU = findUltimateParent(u);
            int ultimateParentV = findUltimateParent(v);
            if (ultimateParentU == ultimateParentV) return;
            if (size.get(ultimateParentU) < size.get(ultimateParentV)) {
                parent.set(ultimateParentU, ultimateParentV);
                size.set(ultimateParentV, size.get(ultimateParentV) + size.get(ultimateParentU));
            } else {
                parent.set(ultimateParentV, ultimateParentU);
                size.set(ultimateParentU, size.get(ultimateParentU) + size.get(ultimateParentV));
            }
            
        }

        public void unionByRank(int u, int v) {
            int ultimateParentU = findUltimateParent(u);
            int ultimateParentV = findUltimateParent(v);
            if (ultimateParentU == ultimateParentV) return;
            if (rank.get(ultimateParentU) < rank.get(ultimateParentV)) {
                parent.set(ultimateParentU, ultimateParentV);
            } else if (rank.get(ultimateParentU) > rank.get(ultimateParentV)) {
                parent.set(ultimateParentV, ultimateParentU);
            } else {
                parent.set(ultimateParentV, ultimateParentU);
                rank.set(ultimateParentU, rank.get(ultimateParentU) + 1);
            }
        }
    } 
    public int removeStones(int[][] stones) {
        int n = stones.length;

        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }

        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);

        boolean[] used = new boolean[maxRow + maxCol + 2];

        for (int i = 0; i < n; i++) {
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1;

            ds.unionBySize(nodeRow, nodeCol);

            used[nodeRow] = true;
            used[nodeCol] = true;
        }

        int components = 0;

        for (int i = 0; i < used.length; i++) {
            if (used[i] && ds.findUltimateParent(i) == i) {
                components++;
            }
        }
        System.out.printf("The value of n (stones) is %d\nThe value of components is %d\nThe value of n-components (answer) is %d\n", n,components, n-components);
        return n - components;
    }
}