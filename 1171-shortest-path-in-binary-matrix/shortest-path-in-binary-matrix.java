class Solution {

    static class Tuple{
        int distance;
        int row;
        int col;
        Tuple(int distance, int row, int col){
            this.distance = distance;
            this.row = row;
            this.col = col;
        }
    }

    public int shortestPathBinaryMatrix(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        int distance[][] = new int[n][m];
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        int sourceRow = 0;
        int sourceCol = 0;
        int destinationRow = n - 1;
        int destinationCol = m - 1;
        

        if (mat[sourceRow][sourceCol] == 1 || mat[destinationRow][destinationCol] == 1){
            return -1; // if start and end are blocked, its impossible to find a path
        }

        if (sourceRow == destinationRow && sourceCol == destinationCol){
            return 1;
        }
        
        
            
        
        distance[sourceRow][sourceCol] = 1;
        
        Queue<Tuple> q = new ArrayDeque<>();
        q.add(new Tuple(1, sourceRow, sourceCol));
        
        while (!q.isEmpty()){
            Tuple tuple = q.poll();
            int dist = tuple.distance;
            int row = tuple.row;
            int col = tuple.col;
            
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {

                    if (dr == 0 && dc == 0)
                        continue; // not needed, completely optional optimization. if we dont do this, the next loop will still handle it properly as (distance[newRow][newCol] > dist + 1) will return true since distance[newRow][newCol] is equal to dist already.

                    int newRow = row + dr;
                    int newCol = col + dc;

                    if (newRow >= 0 && newCol >= 0 &&
                        newRow < n && newCol < m &&
                        mat[newRow][newCol] == 0 &&
                        distance[newRow][newCol] > dist + 1) {

                        distance[newRow][newCol] = dist + 1;

                        if (newRow == destinationRow && newCol == destinationCol)
                            return dist + 1;

                        q.add(new Tuple(dist + 1, newRow, newCol));
                    }
                }
            }
        }
        
        return -1;
    }
}