package socialVerification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class User {
    
    private String userID;
    private String name;
    private Map<User, Relation> friendsList;
    private int socialCutoff;
    
    /**
     * initializes new User. Only parameter needed is the user's id.
     * @param userID
     * 
     * userID = user's unique ID string
     * name = user's name associated with ID
     * friendsList = user's friends and relations
     * socialCutoff = value at which another user passes/fails verification
     */
    public User(String userID){
        this.userID = userID;
        this.name = userID;
        this.friendsList = new HashMap<User, Relation>();
        this.socialCutoff = 100;
    }
    
    public String getUserID() {
        return this.userID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Map<User, Relation> getFriendsList() {
        return this.friendsList;
    }
    
    public int getSocialCutoff() {
        return this.socialCutoff;
    }
    
    /**
     * sets the social verification cutoff value.
     * default cutoff value is 100
     * 
     * @param newNumber, integer value
     */ 
    public void setSocialCutoff(int newNumber) {
        this.socialCutoff = newNumber;
    }
    
    /**
     * sets a map of friends and their relations to the user
     * @param listOfFriends
     */
    public void setFriends(Map<User, Relation> listOfFriends) {
        this.friendsList = listOfFriends;
    }
    
    
    /**
     * gets the pathfinding cost to a friend
     * @param another User (friend on friendlist)
     * @return pathfinding cost to that friend
     */
    public double getFriendCost(User friend) {
        Relation relationToFriend = this.getFriendsList().get(friend);
        double costAmount = 0;
        switch (relationToFriend) {
        case BESTFRIEND:
            costAmount = 0.1;
            break;
        case CHILD:
            costAmount = 0.3;
            break;
        case SIBLING:
            costAmount = 0.3;
            break;
        case PARENT:
            costAmount = 0.5;
            break;
        case GRANDPARENT:
            costAmount = 0.5;
            break;
        default:
            costAmount = 1.0;
            break;
        }
        return costAmount;
    }
    
    /**
     * pathfinding successor function
     * @return next states (User) possible for a given node (User)
     */
    public Set<User> userSuccessors() {
        Map<User, Relation> stateFriends = this.getFriendsList();
        return stateFriends.keySet();
    }
    
    /**
     * Cost-based pathfinding algorithm
     * (May change if we decide to incorporate heuristics)
     * @param targetRunner
     * @return final SearchNode reached by the pathfinding algorithm
     */
    public List<SearchNode> getSocialVerificationNode(User targetRunner) {
        List<SearchNode> listOfPaths = new ArrayList<SearchNode>();
        while (listOfPaths.size() < 10) {
            List<User> friendPath = new ArrayList<User>();
            if (this == targetRunner) {
                friendPath.add(this);
                List<SearchNode> startIsGoal = new ArrayList<SearchNode>();
                startIsGoal.add(new SearchNode(this, null, 0.0));
                return startIsGoal;
            }
            SearchNode startNode = new SearchNode(this, null, 0.0);
            Queue agenda = new Queue();
            agenda.push(startNode);
            Set<User> expanded = new HashSet<User>();
            while (!agenda.isEmpty()) {
                agenda.sort();
                SearchNode parent = agenda.pop();
                if (!expanded.contains(parent.getState())) {
                    expanded.add(parent.getState());
                    if (parent.getState() == targetRunner) {
                        listOfPaths.add(parent);
                    }
                    for (User childState : parent.getState().userSuccessors()) {
                        double childCost = parent.getCost() + parent.getState().getFriendCost(childState);
                        SearchNode child = new SearchNode(childState, parent, childCost);
                        if (!expanded.contains(childState)) {
                            agenda.push(child);
                        }
                    }
                }
            }
        }
        if (listOfPaths.size() > 0) {
            return listOfPaths;
        }
        throw new NullPointerException("No connection to this person.");
    }
    
    /**
     * Converts pathfinding result into a list of visited nodes (User)
     * @param targetRunner
     * @return list of visited Users to get to targetRunner
     */
    public List<List<User>> getSocialVerificationPath(User targetRunner) {
        try {
            List<List<User>> listOfPaths = new ArrayList<List<User>>();
            for (SearchNode node : getSocialVerificationNode(targetRunner)) {
                listOfPaths.add(node.path());
            }
            return listOfPaths;
        }
        catch (NullPointerException e){
            throw new NullPointerException("No connection to this person.");
        }
    }
    
    /**
     * Converts pathfinding result into a pathfinding cost
     * @param targetRunner
     * @return pathfinding cost
     */
    public double getSocialVerificationCost(User targetRunner) {
        try {
            SearchNode parent = getSocialVerificationNode(targetRunner);
            return parent.getCost();
        }
        catch (NullPointerException e){
            throw new NullPointerException("No connection to this person.");
        }
    }
    
    /**
     * Converts pathfinding cost into a social verification score
     * @param targetRunner
     * @return social verification score
     */
    public int getSocialVerificationScore(User targetRunner) {
        double searchCost = getSocialVerificationCost(targetRunner);
        return (int) ((int) 200/searchCost);
    }
    
    /**
     * Determines if target user passes social verification test
     * @param targetRunner
     * @return boolean
     */
    public boolean socialVerification(User targetRunner) {
        try {
            return getSocialVerificationScore(targetRunner) >= this.getSocialCutoff();
        }
        catch (Exception e) {
            return false;
        }
    }
      
}



