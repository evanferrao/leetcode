class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int INF = (int) 1e8;

        // Create distance matrix
        int[][] cost = new int[n][n];

        // Initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    cost[i][j] = 0;
                } else {
                    cost[i][j] = INF;
                }
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            cost[u][v] = w;
            cost[v][u] = w;
        }

        // Floyd-Warshall
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (cost[i][via] != INF &&
                        cost[via][j] != INF) {

                        cost[i][j] = Math.min(
                            cost[i][j],
                            cost[i][via] + cost[via][j]
                        );
                    }
                }
            }
        }

        // Find city with fewest reachable cities
        int count = Integer.MAX_VALUE;
        int city = -1;

        for (int i = 0; i < n; i++) {

            int localCount = 0;

            for (int j = 0; j < n; j++) {
                if (cost[i][j] <= distanceThreshold) {
                    localCount++;
                }
            }

            if (localCount <= count) {
                count = localCount;
                city = i;
            }
        }

        return city;
    }
}