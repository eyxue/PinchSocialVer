package socialVerification;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;


public class UserTests {
    
    private static User pooh;
    private static User piglet;
    private static User chris;
    private static User tigger;
    private static User kanga;
    private static User roo;
    private static User goku;
    private static User krillin;
    private static User bulma;
    private static User vegeta;
    private static User trunks;
    private static User goten;
    private static User pan;
    
    private static Map<User, Relation> poohsFriends;
    private static Map<User, Relation> kangasFriends;
    private static Map<User, Relation> roosFriends;
    private static Map<User, Relation> chrissFriends;
    private static Map<User, Relation> gokusFriends;
    private static Map<User, Relation> krillinsFriends;
    private static Map<User, Relation> bulmaFriends;
    private static Map<User, Relation> vegetaFriends;
    private static Map<User, Relation> trunksFriends;
    private static Map<User, Relation> gotenFriends;
    private static Map<User, Relation> panFriends;
    
    @BeforeClass
    public static void setUpBeforeClass() {

        pooh = new User("Winnie the Pooh");
        piglet = new User("Piglet");
        chris = new User("Christopher Robin");
        tigger = new User("Tigger");
        kanga = new User("Kanga");
        roo = new User("Roo");
        
        goku = new User("Goku Son");
        krillin = new User("Krillin");
        bulma = new User("Bulma");
        vegeta = new User("Vegeta");
        trunks = new User("Trunks");
        goten = new User("Goten");
        pan = new User("pan");
        
        poohsFriends = new HashMap<User, Relation>();
        kangasFriends = new HashMap<User, Relation>();
        roosFriends = new HashMap<User, Relation>();
        chrissFriends = new HashMap<User, Relation>();
        
        krillinsFriends = new HashMap<User, Relation>();
        gokusFriends = new HashMap<User, Relation>();
        bulmaFriends = new HashMap<User, Relation>();
        vegetaFriends = new HashMap<User, Relation>();
        trunksFriends = new HashMap<User, Relation>();
        gotenFriends = new HashMap<User, Relation>();
        panFriends = new HashMap<User, Relation>();
        
        poohsFriends.put(piglet, Relation.BESTFRIEND);
        poohsFriends.put(chris, Relation.BESTFRIEND);
        poohsFriends.put(tigger, Relation.BESTFRIEND);
        poohsFriends.put(kanga, Relation.FRIEND);
        poohsFriends.put(roo, Relation.FRIEND);
        kangasFriends.put(roo, Relation.CHILD);
        kangasFriends.put(piglet, Relation.FRIEND);
        roosFriends.put(pooh, Relation.BESTFRIEND);
        roosFriends.put(chris, Relation.FRIEND);
        roosFriends.put(kanga, Relation.PARENT);
        chrissFriends.put(pooh, Relation.BESTFRIEND);
        chrissFriends.put(piglet, Relation.BESTFRIEND);
        
        krillinsFriends.put(goku, Relation.FRIEND); //BESTFRIEND
        gokusFriends.put(bulma, Relation.FRIEND);
        bulmaFriends.put(vegeta, Relation.FRIEND); //BESTFRIEND
        vegetaFriends.put(trunks, Relation.FRIEND); //CHILD
        trunksFriends.put(goten, Relation.FRIEND); //BESTFRIEND
        gotenFriends.put(pan, Relation.FRIEND);
        gotenFriends.put(trunks, Relation.BESTFRIEND);
        panFriends.put(goten, Relation.FRIEND);
        
  
        pooh.setFriends(poohsFriends); //{piglet=BESTFRIEND, chris=BESTFRIEND, tigger=BESTFRIEND, kanga=FRIEND , roo=FRIEND}
        kanga.setFriends(kangasFriends); //{roo=CHILD, piglet=FRIEND}
        roo.setFriends(roosFriends); //{pooh=BESTFRIEND, chris=FRIEND}
        chris.setFriends(chrissFriends); //{piglet=BESTFRIEND, pooh=BESTFRIEND, kanga=PARENT}  
        
        pan.setFriends(panFriends); //{goten=FRIEND}
        goten.setFriends(gotenFriends); //{krillin=SIBLING}
        krillin.setFriends(krillinsFriends); //{goku=FRIEND}
        goku.setFriends(gokusFriends); //{bulma=FRIEND}
        bulma.setFriends(bulmaFriends); //{vegeta=FRIEND}
        vegeta.setFriends(vegetaFriends); //{trunks=FRIEND}
        trunks.setFriends(trunksFriends); //{goten=BESTFRIEND}
        goten.setFriends(gotenFriends); //{pan=FRIEND, trunks=BESTFRIEND}
        pan.setFriends(panFriends); //{goten=FRIEND}
    }
    
    @Test
    public void testGetSocialVerificationNodeMultiple() {

        List<SearchNode> roonodes = roo.getSocialVerificationNode(piglet);
        System.out.println(roonodes);
        assertEquals(3,roonodes.size());
    }
    
    @Test
    public void testGetSocialVerificationPathsMultiple() {

        List<List<User>> roopaths = roo.getSocialVerificationPaths(piglet);
        System.out.println(roopaths);
        assertEquals(3,roopaths.size());

        for (List<User> paths : roopaths) {
            assertEquals(3,paths.size());
        }
    }
    
    @Test
    public void testGetSocialVerificationPathsMultipleSizes() {
        
        List<List<User>> poohpaths = pooh.getSocialVerificationPaths(piglet);
        System.out.println(poohpaths);
        assertEquals(3,poohpaths.size());

        
    }
    
    @Test
    public void testGetSocialVerificationScoreMoreThanOnePath() {

        int rooscore = roo.getSocialVerificationScore(piglet);
        System.out.println(rooscore);
        assertTrue(roo.socialVerification(piglet));
        roo.setSocialCutoff(2000);
        assertFalse(roo.socialVerification(piglet));
        roo.setSocialCutoff(100);
        
    }
    
    
    
    @Test
    public void testGetSocialVerificationTwoDegrees() {

        boolean twoDegrees = goku.socialVerification(vegeta);
        System.out.println(goku.getSocialVerificationScore(vegeta));
        assertTrue(twoDegrees);
    }
    
    @Test
    public void testGetSocialVerificationFiveDegrees() {

        boolean fiveDegrees = krillin.socialVerification(trunks);
        System.out.println("krillin - trunks score: " + krillin.getSocialVerificationScore(goten));
        System.out.println("krillin - trunks path: " + krillin.getSocialVerificationPaths(goten));
        assertEquals(0, krillin.getSocialVerificationScore(goten));
        assertFalse(fiveDegrees);
    }
    
    @Test
    public void testGetSocialVerificationPathsTooSeparated() {

        List<List<User>> panpaths = pan.getSocialVerificationPaths(krillin);
        System.out.println(panpaths);
        assertEquals(Collections.EMPTY_LIST,panpaths);
    }
    
    @Test
    public void testGetSocialVerificationPathsNoConnection() {

        List<List<User>> nopaths = tigger.getSocialVerificationPaths(goku);
        
        System.out.println(nopaths);
        
        assertEquals(Collections.EMPTY_LIST,nopaths);
    }
}
