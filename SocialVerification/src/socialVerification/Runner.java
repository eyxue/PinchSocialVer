package socialVerification;

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
        
        User naruto = new User("Naruto Uzumaki");
        User sasuke = new User("Sasuke Uchiha");
        User sakura = new User("Sakura Haruno");
        User kakashi = new User("Kakashi Hatake");
        User jiraiya = new User("Jiraiya");
        User itachi = new User("Itachi Uchiha");
        User sasori = new User("Sasori");
        
        
        Map<User, Relation> poohsFriends = new HashMap<User, Relation>();
        Map<User, Relation> kangasFriends = new HashMap<User, Relation>();
        Map<User, Relation> roosFriends = new HashMap<User, Relation>();
        Map<User, Relation> chrissFriends = new HashMap<User, Relation>();
        
        Map<User, Relation> narutosFriends = new HashMap<User, Relation>();
        Map<User, Relation> sasukesFriends = new HashMap<User, Relation>();
        Map<User, Relation> sakuraFriends = new HashMap<User, Relation>();
        Map<User, Relation> kakashiFriends = new HashMap<User, Relation>();
        Map<User, Relation> jiraiyaFriends = new HashMap<User, Relation>();
        Map<User, Relation> itachiFriends = new HashMap<User, Relation>();
        Map<User, Relation> sasoriFriends = new HashMap<User, Relation>();
        
        poohsFriends.put(piglet, Relation.BESTFRIEND);
        poohsFriends.put(chris, Relation.BESTFRIEND);
        poohsFriends.put(tigger, Relation.BESTFRIEND);
        poohsFriends.put(kanga, Relation.FRIEND);
        kangasFriends.put(roo, Relation.CHILD);
        roosFriends.put(pooh, Relation.BESTFRIEND);
        roosFriends.put(chris, Relation.FRIEND);
        chrissFriends.put(pooh, Relation.BESTFRIEND);
        chrissFriends.put(piglet, Relation.BESTFRIEND);
        
        sasoriFriends.put(itachi, Relation.FRIEND);
        itachiFriends.put(sasuke, Relation.SIBLING);
        sasukesFriends.put(naruto, Relation.FRIEND);
        narutosFriends.put(sakura, Relation.FRIEND);
        sakuraFriends.put(kakashi, Relation.FRIEND);
        kakashiFriends.put(jiraiya, Relation.FRIEND);
        jiraiyaFriends.put(kakashi, Relation.FRIEND);
        
        
        pooh.setFriends(poohsFriends); //{piglet=BESTFRIEND, chris=BESTFRIEND, tigger=BESTFRIEND, kanga=FRIEND}
        kanga.setFriends(kangasFriends); //{roo=CHILD}
        roo.setFriends(roosFriends); //{pooh=BESTFRIEND, chris=FRIEND}
        chris.setFriends(chrissFriends); //{piglet=BESTFRIEND, pooh=BESTFRIEND}
        
        sasori.setFriends(sasoriFriends); //{itachi=FRIEND}
        itachi.setFriends(itachiFriends); //{sasuke=SIBLING}
        sasuke.setFriends(sasukesFriends); //{naruto=FRIEND}
        naruto.setFriends(narutosFriends); //{piglet=BESTFRIEND, pooh=BESTFRIEND}
        sakura.setFriends(sakuraFriends); //{kakashi=FRIEND}
        kakashi.setFriends(kakashiFriends); //{jiraiya=FRIEND}
        jiraiya.setFriends(jiraiyaFriends); //{kakashi=FRIEND}
        
        
        List<List<User>> paths = roo.getSocialVerificationPaths(piglet);
        
        System.out.println(paths);
        System.out.println(roo.getSocialVerificationCost(piglet));
        System.out.println(roo.getSocialVerificationScore(piglet));
        System.out.println(roo.socialVerification(piglet));
        System.out.println(sasori.getSocialVerificationNode(jiraiya));
        System.out.println(sasori.getSocialVerificationCost(jiraiya));
        System.out.println(sasori.getSocialVerificationScore(jiraiya));
        System.out.println(sasori.socialVerification(jiraiya));
        System.out.println(sasori.getSocialVerificationPaths(tigger));

        
    }
}
