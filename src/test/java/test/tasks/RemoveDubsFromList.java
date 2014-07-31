package test.tasks;

import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by Denis Stoianov on 31/07/2014, 5:23 PM
 * E-mail: denis@revimedia.com
 */
public class RemoveDubsFromList {

    private List<String> dubList = Arrays.asList("step2", "step3", "step2", "step5", "step4", "step7", "step2", "step9", "step19", "step2", "step11", "step11", "step18", "step15", "step2", "step17", "step18", "step19", "step18");

    @Test
    public void testName() throws Exception {
        //Variant 1, new list
        List<String> dubListNoDub = new ArrayList<>(new LinkedHashSet<>(dubList));

        //Variant 2
        Set<String> noDups = new HashSet<>();
        noDups.addAll(dubList);
        List<String> dubListNoDub2 = new ArrayList<>(noDups);

        System.out.println(String.format("Original size of list: %s", dubList.size()));
        System.out.println(String.format("Variant 1 size of list: %s", dubListNoDub.size()));
        System.out.println(String.format("Variant 2 size of list: %s", dubListNoDub2.size()));
    }


    @Test
    public void testName2() throws Exception {
        ArrayList<String> hoHoList = new ArrayList<>(dubList);
        System.out.println("Duplicates List " + hoHoList);
        Object[] st = hoHoList.toArray();
        for (Object s : st) {
            if (hoHoList.indexOf(s) != hoHoList.lastIndexOf(s)) {
                hoHoList.remove(hoHoList.lastIndexOf(s));
            }
        }
        System.out.println("Distinct List " + hoHoList);
    }


}
