package socialVerification;

public class Node {
    private String nodeID;
    
    public Node(String id) {
        this.nodeID = id;
    }
    
    public Node(User user) {
        this.nodeID = user.getUserID();
    }
    
    public String getNodeID() {
        return this.nodeID;
    }

}
