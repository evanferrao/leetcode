class StockSpanner {

    public class Pair{
        int value;
        int index;

        Pair(int value, int index){
            this.value = value;
            this.index = index;
        }
    }

    int index;

    private ArrayDeque<Pair> st;

    public StockSpanner() {
        st = new ArrayDeque<>();
        index = -1;
    }
    
    // PREVIOUS GREATER ELEMENT (PGE)
    public int next(int price) {
        index = index + 1;

        while (!st.isEmpty() && st.peek().value <= price){
            st.pop();
        }

        int span = index - (st.isEmpty() ? -1 : st.peek().index);
        st.push(new Pair(price, index));

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */