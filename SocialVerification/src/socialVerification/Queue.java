package socialVerification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    
    
    
    class ValueComparator implements Comparator<SearchNode> {
    
        Map<SearchNode, Double> base;
        public ValueComparator(Map<SearchNode, Double> map) {
            this.base = map;
        }
    
        // Note: this comparator imposes orderings that are inconsistent with equals.    
        public int compare(SearchNode a, SearchNode b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
    
    public void sort() {
        Map<SearchNode,Double> costmap = new HashMap<SearchNode,Double>();
        ValueComparator bvc =  new ValueComparator(costmap);
        TreeMap<SearchNode,Double> sorted_map = new TreeMap<SearchNode,Double>(bvc);
        
        for (SearchNode node : this.data) {
            costmap.put(node,node.getCost());
        }
        sorted_map.putAll(costmap);
        List<SearchNode> sortedNodes = new ArrayList<SearchNode>(sorted_map.keySet());
        Collections.reverse(sortedNodes);
        this.data = sortedNodes;
    }
    
    public String toString() {
        return this.data.toString();
    }
        

}
