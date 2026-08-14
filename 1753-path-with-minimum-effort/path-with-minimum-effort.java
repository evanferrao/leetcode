class Solution {

    // solutions imported from gfg https://www.geeksforgeeks.org/problems/path-with-minimum-effort/1
    static class Tuple{
        int difference;
        int row; 
        int col;
        public Tuple(int difference, int row, int col){
            this.difference = difference;
            this.row = row;
            this.col = col;
        }
    }
    
    public int minCostPath(int[][] mat) {
        // code here
        
        PriorityQueue<Tuple> pq = new PriorityQueue<>((x,y) -> x.difference - y.difference);
        
        int n = mat.length;
        int m = mat[0].length;
        int dist[][] = new int[n][m];
        
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dist[0][0] = 0;
        pq.add(new Tuple(0,0,0));
        int dr[] = {-1,0,1,0};
        int dc[] = {0,1,0,-1};
        
        while(!pq.isEmpty()){
            Tuple tuple = pq.poll();
            int difference = tuple.difference;
            int row = tuple.row; 
            int col = tuple.col;
            
            //if (row == n-1 && col == m-1) return difference; // early exit. if we don't want, we can always return dist[n-1][m-1] later on
            
            for (int i=0; i<4; i++){
                int newRow = row + dr[i];
                int newCol = col + dc[i];
                
                if (newRow >=0 && newCol >=0 && newRow <n && newCol <m){
                    int currentDifference = Math.abs(mat[row][col] - mat[newRow][newCol]);
                    int newEffort = Math.max(currentDifference,difference);
                    if (newEffort < dist[newRow][newCol]){
                        dist[newRow][newCol] = newEffort;
                        pq.add(new Tuple(newEffort, newRow, newCol));
                    }
                }
            }
        }
        
        return dist[n-1][m-1];
        
    }

    public int minimumEffortPath(int[][] heights) {
        return minCostPath(heights); // reuse gfg solution. I couldn't be bothered to write again
    }
}