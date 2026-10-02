import java.util.ArrayList;
import java.util.List;

/**
 * Recommendation: Return an empty array or collection instead of a null value for methods that return an array or collection
 *
 * @author Kimlay Neng
 */
public class MET55 {

    public static List<String> findNames(List<String> names, String letter) {
        List<String> matches = new ArrayList<>();

        for(String name : names) {
            if(name.startsWith(letter)){
                matches.add(name);
            }
        }
        //return an empty list if there are no matches
        return matches;
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("James");
        names.add("Joe");

        List<String> results = findNames(names, "x");
        System.out.println("Matches: " + results.size());
    }
}