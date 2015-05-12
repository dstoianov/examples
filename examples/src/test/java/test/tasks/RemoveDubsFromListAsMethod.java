package test.tasks;

import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by Denis Stoianov on 31/07/2014, 5:23 PM
 * E-mail: denis@revimedia.com
 */
public class RemoveDubsFromListAsMethod {

    private List<String> dubList = Arrays.asList("step2", "step3", "step2", "step5", "step4", "step7", "step2", "step9", "step19", "step2", "step11", "step11", "step18", "step15", "step2", "step17", "step18", "step19", "step18");

    //Order is not preserved
    public static <T> void removeDuplicate1(List<T> list) {
        HashSet<T> h = new HashSet<T>(list);
        list.clear();
        list.addAll(h);
    }

    @SuppressWarnings("unchecked") //Order is preserved
    public static <T> void removeDuplicate2(List<T> list) {
        Set<T> set = new HashSet<T>();
        List<T> newList = new ArrayList<T>();
        for (Iterator<T> iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add((T) element)) {
                newList.add((T) element);
            }
        }
        list.clear();
        list.addAll(newList);
    }


    @Test //Order is not preserved
    public void testName1() throws Exception {
        List<String> list = new ArrayList<String>(dubList);
        System.out.println(list);
        removeDuplicate1(list);
        System.out.println(list);
    }


    @Test //Order is preserved
    public void testName2() throws Exception {
        List<String> list = new ArrayList<String>(dubList);
        System.out.println(list);
        removeDuplicate2(list);
        System.out.println(list);
    }


}
