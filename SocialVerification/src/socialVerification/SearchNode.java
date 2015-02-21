package socialVerification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchNode {
    
    private User state;
    private SearchNode parent;
    private double cost;
    
    //public Map<String, Double> relationToCost = new HashMap<String, Double>();
    //relationToCost.put("Parent", 1.5);
    
    public SearchNode(User state, SearchNode parent, double cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
//        String relationToParentNode = parent.state.getFriendsList().get(state);
//        this.cost = relationToCost.get(relationToParentNode);
    }
    
    public List<User> path() {
        List<User> p = new ArrayList<User>();
        SearchNode node = this;
        while (node != null) {
            p.add(node.state);
            node = node.parent;
        }
        if (p.size() > 5) {
            return null;
        }
        Collections.reverse(p);
        return p;
    }
    
    public User getState() {
        return this.state;
    }
    
    public double getCost() {
        return this.cost;
    }

      
    
}
