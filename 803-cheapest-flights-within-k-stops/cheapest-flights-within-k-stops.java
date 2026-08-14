class Solution {

    // solution imported from gfg https://www.geeksforgeeks.org/problems/shortest-path-with-at-most-k-nodes/1
    static class Pair{
        int destination;
        int cost;
        Pair(int destination, int cost){
            this.destination = destination;
            this.cost = cost;
        }
    }
    
    static class Tuple{
        int stops;
        int node;
        int distance;
        Tuple(int stops, int node, int distance){
            this.stops=stops;
            this.node=node;
            this.distance=distance;
        }
    }
    
    public static int findCheapestCost(int n, int[][] edges, int src, int dst, int k) {
        // code here
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        int m = edges.length;
        for (int i=0; i<m; i++){
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
        }
        
        Queue<Tuple> q = new ArrayDeque<>();
        q.add(new Tuple(0,src,0));
        
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[src] = 0;
        
        while (!q.isEmpty()){
            Tuple tuple = q.poll();
            int stops = tuple.stops;
            int node = tuple.node;
            int distance = tuple.distance;
            
            if (stops > k) continue; // elegant way to use the next element.
            
            for (Pair pair : adj.get(node)){
                int adjNode = pair.destination;
                int cost = pair.cost;
                
                if (distance + cost < dist[adjNode] && stops <= k){
                    dist[adjNode] = distance + cost;
                    q.add(new Tuple(stops+1, adjNode, distance+cost));
                }
            }
        }
        
        if (dist[dst] == Integer.MAX_VALUE) return -1;
        return dist[dst];
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        return findCheapestCost(n, flights, src, dst, k);
    }
}