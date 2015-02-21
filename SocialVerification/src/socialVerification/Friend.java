package socialVerification;

public class Friend {
    
    private User friendID;
    private String friendRelation;
    
    public Friend(User friendID, String relation) {
        this.friendID = friendID;
        this.friendRelation = relation;
    }
    
    public User getFriendID() {
        return this.friendID;
    }
    
    public String getFriendRelation() {
        return this.friendRelation;
    }

}
