package socialVerification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    
    public static void main(String args[]) {
        User pooh = new User("Winnie the Pooh");
        User piglet = new User("Piglet");
        User chris = new User("Christopher Robin");
        User tigger = new User("Tigger");
        User kanga = new User("Kanga");
        User roo = new User("Roo");
        
        Map<User, Relation> poohsFriends = new HashMap<User, Relation>();
        Map<User, Relation> kangasFriends = new HashMap<User, Relation>();
        Map<User, Relation> roosFriends = new HashMap<User, Relation>();
        Map<User, Relation> chrissFriends = new HashMap<User, Relation>();
        
        poohsFriends.put(piglet, Relation.FRIEND);
        poohsFriends.put(chris, Relation.BESTFRIEND);
        poohsFriends.put(tigger, Relation.BESTFRIEND);
        poohsFriends.put(kanga, Relation.FRIEND);
        kangasFriends.put(roo, Relation.CHILD);
        roosFriends.put(pooh, Relation.FRIEND);
        roosFriends.put(chris, Relation.BESTFRIEND);
        chrissFriends.put(pooh, Relation.BESTFRIEND);
        chrissFriends.put(piglet, Relation.BESTFRIEND);
        
        pooh.setFriends(poohsFriends);
        kanga.setFriends(kangasFriends);
        roo.setFriends(roosFriends);
        chris.setFriends(chrissFriends);
        
        List<List<User>> paths = roo.getSocialVerificationPaths(piglet);
        List<String> namepath = new ArrayList<String>();
        
//        for (User user : path) {
//            namepath.add(user.getName());
//        }
        
//        SearchNode node1 = new SearchNode(pooh, null, 0);
//        SearchNode node2 = new SearchNode(tigger, node1, 0);
//        SearchNode node3 = new SearchNode(piglet, node2, 0);
//        SearchNode node4 = new SearchNode(roo, node3, 0);
        

//        System.out.println(roo.getSocialVerificationCost(piglet));
        System.out.println(paths);
        
    }
}
