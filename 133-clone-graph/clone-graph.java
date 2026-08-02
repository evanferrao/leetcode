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


    public Node dfs(Node node, HashMap<Node,Node> map){

        if (map.containsKey(node)){
            return map.get(node); // if a node is already added in the list before, this is to prevent cycles from forming
            // assume a graph of 1<->2. in this graph 1 will call 2, then 2 will call 1, then 1 will call 2. this happens forever
        }

        Node cloneNode = new Node(node.val);
        map.put(node, cloneNode);


        for (Node neighbor: node.neighbors){
            cloneNode.neighbors.add(dfs(neighbor, map));
        }

        return cloneNode;
    }

    public Node cloneGraph(Node node) {

        HashMap<Node,Node> map = new HashMap<>();
        if (node == null) return null;

        return dfs(node, map);
    }
}