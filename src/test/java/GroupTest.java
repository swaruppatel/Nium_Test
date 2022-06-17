import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class GroupTest {
    public static ArrayList<ArrayList<String>> group(ArrayList<String> ip) {

        TreeMap<Integer, ArrayList<String>> tr = new TreeMap();
        ArrayList<ArrayList<String>> al = new ArrayList();
        for (int i = 0; i < ip.size(); i++) {
            String str = ip.get(i);
            ArrayList<String> l = new ArrayList<>();
            if (tr.containsKey(str.length())) {
                l = tr.get(str.length());
            } else {
                al.add(l);
            }
            l.add(str);
            tr.put(str.length(), l);
        }

        return al;
    }

    @Test
    public void groupTest() {

// 1
        ArrayList<String> list = new ArrayList(Arrays.asList("cat", "d", "abc", "tram", "help", "boat", "world", "range", "grail"));
        ArrayList<ArrayList<String>> act1 = group(list);
        ArrayList<ArrayList<String>> exp1 = new ArrayList();
        exp1.add(new ArrayList(Arrays.asList("cat", "abc")));
        exp1.add(new ArrayList(Arrays.asList("d")));
        exp1.add(new ArrayList(Arrays.asList("tram", "help", "boat")));
        exp1.add(new ArrayList(Arrays.asList("world", "range", "grail")));
        Assert.assertEquals(act1, exp1, "Actual result found >>> " + act1 + " Expecting result >>> " + exp1);

//        2
        ArrayList<String> list2 = new ArrayList(Arrays.asList("cat", "cat", "cat"));
        ArrayList<ArrayList<String>> act2 = group(list2);
        ArrayList<ArrayList<String>> exp2 = new ArrayList();
        exp2.add(new ArrayList(Arrays.asList("cat", "cat", "cat")));
        Assert.assertEquals(act2, exp2, "Actual result found >>> " + act2 + " Expecting result >>> " + exp2);

        //        3
        ArrayList<String> list3 = new ArrayList(Arrays.asList("cat"));
        ArrayList<ArrayList<String>> act3 = group(list3);
        ArrayList<ArrayList<String>> exp3 = new ArrayList();
        exp3.add(new ArrayList(Arrays.asList("cat")));
        Assert.assertEquals(act3, exp3, "Actual result found >>> " + act3 + " Expecting result >>> " + exp3);

    }
}
