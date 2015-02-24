package socialVerification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queue {
    
    private List<SearchNode> data;
    
    public Queue() {
        this.data = new ArrayList<SearchNode>();
    }
    
    public void push(SearchNode item) {
        this.data.add(item);
    }
    
    public SearchNode pop() {
        SearchNode poppedItem = this.data.get(0);
        this.data.remove(0);
        return poppedItem;
    }
    
    public boolean isEmpty() {
        return this.data.size() == 0;
    }
    
    public void sort() {
        Map<Double, SearchNode> costMapUnsorted = new HashMap<Double, SearchNode>();
        List<SearchNode> sortedNodes = new ArrayList<SearchNode>();
        for (SearchNode node : this.data) {
            costMapUnsorted.put(node.getCost(), node);
        }
        List<Double> sortedCosts = new ArrayList<Double>(costMapUnsorted.keySet());
        Collections.sort(sortedCosts);
        for (double cost : sortedCosts) {
            sortedNodes.add(costMapUnsorted.get(cost));
        }
        this.data = sortedNodes;
    }
    
    public String toString() {
        return this.data.toString();
    }
        

}
