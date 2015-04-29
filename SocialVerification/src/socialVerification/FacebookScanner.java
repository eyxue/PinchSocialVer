package socialVerification;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//{0,1,48,53}
public class FacebookScanner {
    
    private Map<String, User> userTable = new HashMap<String, User>();

  public static void main(String... aArgs) throws IOException {
    FacebookScanner parser = new FacebookScanner("test1.txt");
    parser.processLineByLine();
    System.out.println(parser.userTable.get("1").getSocialVerificationPaths(parser.userTable.get("4")));
    System.out.println(parser.userTable.get("1").getSocialVerificationScore(parser.userTable.get("4")));
    System.out.println(parser.userTable.get("4").getSocialVerificationPaths(parser.userTable.get("1")));
    System.out.println(parser.userTable.get("4").getSocialVerificationScore(parser.userTable.get("1")));
    System.out.println("Done.");
  }
  
  /**
   Constructor.
   @param aFileName full name of an existing, readable file.
  */
  public FacebookScanner(String aFileName){
    fFilePath = Paths.get(aFileName);
  }
  
  
  /** Template method that calls {@link #processLine(String)}.  */
  public final void processLineByLine() throws IOException {
    try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
      while (scanner.hasNextLine()){
        processLine(scanner.nextLine());
      }      
    }
  }
  
  /** 
   Overridable method for processing lines in different ways.
    
   <P>This simple default implementation expects simple name-value pairs, separated by a whitespace. 
   Examples of valid input: 
   <tt>40 2357</tt>
   <tt>372 1023</tt>
   <tt>123 456</tt>
   <tt>user1 user2</tt>
  */
  protected void processLine(String aLine){
    //use a second Scanner to parse the content of each line 
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter(" ");
    while (scanner.hasNext()){
      //assumes the line has a certain structure
      String user1id = scanner.next();
      String user2id = scanner.next();
      User user1 = new User(user1id);
      User user2 = new User(user2id);
      if (userTable.containsKey(user1id)) {
          user1 = userTable.get(user1id);
      }
      if (!userTable.containsKey(user2id)) {
          userTable.put(user2id, user2);
      }
      user1.addFriend(userTable.get(user2id), Relation.FRIEND);
      userTable.put(user1id, user1);
      if (userTable.containsKey(user2id)) {
          user2 = userTable.get(user2id);
      }
      if (!userTable.containsKey(user1id)) {
          userTable.put(user1id, user1);
      }
      else {
          user2.addFriend(userTable.get(user1id), Relation.FRIEND);
      }
      userTable.put(user2id, user2);
    }
//    else {
//      log("Empty or invalid line. Unable to process.");
//    }
  }
  
  // PRIVATE 
  private final Path fFilePath;
  private final static Charset ENCODING = StandardCharsets.UTF_8;  
  
  private static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
  private String quote(String aText){
    String QUOTE = "'";
    return QUOTE + aText + QUOTE;
  }
} 
