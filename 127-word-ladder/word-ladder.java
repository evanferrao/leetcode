class Solution {
    static class Pair{
        String string;
        int level;
        Pair(String string, int level){
            this.string = string;
            this.level = level;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for (String word: wordList){
            set.add(word);
        }
        int maxLevel = Integer.MAX_VALUE;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(beginWord, 1));
        set.remove(beginWord);
        while (!q.isEmpty()){
            
            Pair pair = q.poll();
            String word = pair.string;
            int level = pair.level;

            if (word.equals(endWord)){
                maxLevel = Math.min(maxLevel, level);
            }

            for (int i=0; i<word.length(); i++){
                char replacedCharArray[] = word.toCharArray();
                for (char ch='a'; ch<='z'; ch++){
                    replacedCharArray[i] = ch;
                    String newTestWord = new String(replacedCharArray);
                    if (set.contains(newTestWord)){
                        set.remove(newTestWord);
                        q.add(new Pair(newTestWord, level+1));
                    }
                    replacedCharArray[i] = word.charAt(i); // replace the word for the next iteration
                }
            }
        }

        return maxLevel == Integer.MAX_VALUE ? 0 : maxLevel;

    }
}