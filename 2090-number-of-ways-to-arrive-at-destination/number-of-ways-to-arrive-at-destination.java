class Solution {

    static class Pair {
        int node;
        long distance;

        Pair(int node, long distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public int countPaths(int n, int[][] roads) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < roads.length; i++) {
            adj.get(roads[i][0]).add(
                new Pair(roads[i][1], roads[i][2])
            );
            adj.get(roads[i][1]).add(
                new Pair(roads[i][0], roads[i][2])
            );
        }

        PriorityQueue<Pair> pq =
            new PriorityQueue<>((x, y) -> Long.compare(x.distance, y.distance));

        long[] dist = new long[n];
        int[] ways = new int[n];

        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(ways, 0);

        dist[0] = 0;
        ways[0] = 1;

        pq.add(new Pair(0, 0));

        int mod = (int) 1e9 + 7;

        while (!pq.isEmpty()) {

            Pair pair = pq.poll();

            int node = pair.node;
            long distance = pair.distance;

            for (Pair it : adj.get(node)) {

                int adjNode = it.node;
                long adjDistance = it.distance;

                if (distance + adjDistance < dist[adjNode]) {

                    dist[adjNode] = distance + adjDistance;
                    pq.add(new Pair(adjNode, dist[adjNode]));

                    ways[adjNode] = ways[node];

                } else if (distance + adjDistance == dist[adjNode]) {

                    ways[adjNode] =
                        (int) (((long) ways[adjNode] + ways[node]) % mod);
                }
            }
        }

        return ways[n - 1];
    }
}