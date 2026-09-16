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

        public void unionBySize(int u, int v) {

            int ultimateParentU = findUltimateParent(u);
            int ultimateParentV = findUltimateParent(v);

            if (ultimateParentU == ultimateParentV)
                return;

            if (size.get(ultimateParentU)
                    < size.get(ultimateParentV)) {

                parent.set(ultimateParentU, ultimateParentV);

                size.set(
                    ultimateParentV,
                    size.get(ultimateParentV)
                        + size.get(ultimateParentU)
                );

            } else {

                parent.set(ultimateParentV, ultimateParentU);

                size.set(
                    ultimateParentU,
                    size.get(ultimateParentU)
                        + size.get(ultimateParentV)
                );
            }
        }

        public void unionByRank(int u, int v) {

            int ultimateParentU = findUltimateParent(u);
            int ultimateParentV = findUltimateParent(v);

            if (ultimateParentU == ultimateParentV)
                return;

            if (rank.get(ultimateParentU)
                    < rank.get(ultimateParentV)) {

                parent.set(ultimateParentU, ultimateParentV);

            } else if (rank.get(ultimateParentU)
                    > rank.get(ultimateParentV)) {

                parent.set(ultimateParentV, ultimateParentU);

            } else {

                parent.set(ultimateParentV, ultimateParentU);

                rank.set(
                    ultimateParentU,
                    rank.get(ultimateParentU) + 1
                );
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int V = isConnected.length;
        DisjointSet ds = new DisjointSet(V);

        for (int i=0; i<V; i++){
            for (int j=0; j<V; j++){
                if (isConnected[i][j]==1 && i!=j){
                    ds.unionBySize(i, j);
                }
            }
        }

        int count = 0;
        for (int i=0; i<V; i++){
            if (ds.findUltimateParent(i) == i){
                count++;
            }
        }

        return count;
    }
}