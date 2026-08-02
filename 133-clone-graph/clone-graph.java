/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {

        if (node == null) return null;

        HashMap<Node, Node> map = new HashMap<>();
        Node clone = new Node(node.val);
        map.put(node, clone);

        
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while (!q.isEmpty()){
            Node current = q.poll();
            for (Node neighbor: current.neighbors){

                // if map doesn't already contain it, add it
                if (!map.containsKey(neighbor)){
                    Node newClone = new Node(neighbor.val);
                    map.put(neighbor, newClone);
                    q.add(neighbor);
                }
                // at this stage, the neighbours have their own clones linked in map


                map.get(current).neighbors.add(map.get(neighbor)); // now link the current node's CLONE's neighbours to the neighbors clone that we've either generated in the above if statement, or have already been generated if there's a cycle somewhere
            }
        }

        return map.get(node);
    }
}