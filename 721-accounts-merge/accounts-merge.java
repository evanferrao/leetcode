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
            if (rank.get(ultimateParentU) < rank.get(ultimateParentV)) {
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

    public List<List<String>> accountsMerge(List<List<String>> details) {
        int n = details.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mapMailNode = new HashMap<String, Integer>();

        for (int i=0; i<n; i++){
            for (int j=1; j<details.get(i).size(); j++){
                String mail = details.get(i).get(j);
                if (mapMailNode.containsKey(mail) == false){
                    mapMailNode.put(mail, i);
                } else {
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        List<List<String>> mergedMail = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            mergedMail.add(new ArrayList<>());
        }

        for (String mail: mapMailNode.keySet()){
            int node = mapMailNode.get(mail);
            int ultimateParentNode = ds.findUltimateParent(node);
            mergedMail.get(ultimateParentNode).add(mail);
        }

        List<List<String>> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail.get(i).size() == 0) continue;
            
            List<String> emails = mergedMail.get(i);
            Collections.sort(emails);

            List<String> currentAccount = new ArrayList<>();
            currentAccount.add(details.get(i).get(0)); // Name
            currentAccount.addAll(emails);

            answer.add(currentAccount);
        }

        return answer;
    }
}