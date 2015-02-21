package socialVerification;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    
    private List<SearchNode> data;
    
    public Stack() {
        this.data = new ArrayList<SearchNode>();
    }
    
    public void push(SearchNode item) {
        this.data.add(item);
    }
    
    
    public SearchNode pop() {
        SearchNode poppedItem = this.data.get(this.data.size()-1);
        this.data.remove(this.data.size()-1);
        return poppedItem;
    }
    
    public boolean isEmpty() {
        return this.data.size() == 0;
    }
}
