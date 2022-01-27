import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        List<HashMap<String, String>> people = new ArrayList<>();

        HashMap<String, String> person1 = new HashMap<>();
        person1.put("name","Angel");
        person1.put("age","27");

        HashMap<String, String> person2 = new HashMap<>();
        person2.put("name","Aguilar");
        person2.put("age","30");

        people.add(person1);
        people.add(person2);

        for(Map.Entry data : person1.entrySet()){
            System.out.println(data.getKey() + " " + data.getValue());
        }

        /*python
        people = [
            {"name":"Angel","age":"27"},
            {"name":"Aguilar","age":"30"}
        ]

        print(people)
         */


    }
}
