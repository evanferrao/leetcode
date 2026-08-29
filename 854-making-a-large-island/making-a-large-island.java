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

    public boolean isValid(int adjr, int adjc, int n, int m){
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < m;
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        DisjointSet ds = new DisjointSet(n*m);

        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){

                if (grid[i][j] == 0) continue;

                int dr[] = {0,-1, 0, 1};
                int dc[] = {-1, 0, 1, 0};
                for (int idx = 0; idx <4; idx++){
                    int newRow = i + dr[idx];
                    int newCol = j + dc[idx];
                    if (isValid(newRow, newCol, n, m) && grid[newRow][newCol] == 1){
                        int node = i * m + j;
                        int adjNode = newRow * m + newCol;
                        ds.unionBySize(node, adjNode);
                    }
                }
            }
        }
        int maxSize = 0;
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (grid[i][j] == 1) continue;
                int dr[] = {0,-1, 0, 1};
                int dc[] = {-1, 0, 1, 0};
                HashSet<Integer> components = new HashSet<>();
                for (int idx = 0; idx<4; idx++){
                    int newRow = i + dr[idx];
                    int newCol = j + dc[idx];
                    if (isValid(newRow, newCol, n, m) && grid[newRow][newCol] == 1){
                        int adjNode = newRow * m + newCol;
                        int adjUltimateParents = ds.findUltimateParent(adjNode);
                        components.add(adjUltimateParents);
                    }
                }

                int currentSize = 1; // 1 because the current element is flipped from 0 to 1.

                for (int parent: components){
                    currentSize += ds.size.get(parent);
                }

                maxSize = Math.max(currentSize, maxSize);
            }
        }

        // Edge case: grid is already entirely 1s
        for (int node = 0; node < n * m; node++) {
            maxSize = Math.max(maxSize, ds.size.get(node));
        }

        return maxSize;
    }
}