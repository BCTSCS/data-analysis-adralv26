// import java.io.*;
import java.util.*;

public class Relinq {
    public static void main(String[] args) {
        // File operator
        // ArrayList<String> posts = FileOperator.getStringList("posts.txt");
        // for(String post : posts){
        //     boolean result = post.matches(re);
        // }
        // boolean result = text.matches(re);
        String re="^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$"; // email
        String re2="\\b\\w{10}\\b"; // word of specific length
        String text = "accidental";
        String text2 = "dolores";
        String email = "test@mail.com";
        String email2 = "ravi-fakemail.org";
        boolean result = email.matches(re);
        boolean result2 = email2.matches(re);
        boolean textresult = text.matches(re2);
        boolean textresult2 = text2.matches(re2);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(textresult);
        System.out.println(textresult2);
    }
}