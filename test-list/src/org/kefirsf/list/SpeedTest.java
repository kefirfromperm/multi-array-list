package org.kefirsf.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class SpeedTest {
    public static void main(String[] args){
        test(new ArrayList<Integer>());
        test(new LinkedList<Integer>());
    }

    public static void test(List<Integer> list){
        list.clear();
        prepare(list);
        System.out.println();
        System.out.println("Collection: " + list.getClass().getName());
        long begin = System.currentTimeMillis();
        filtration(list);
        long end = System.currentTimeMillis();
        System.out.println("Time: "+(end-begin)+"ms");
    }

    private static void prepare(List<Integer> list){
        for(int i = 0; i<100000; i++){
            list.add(i);
        }
    }

    private static void filtration(List<Integer> list){
        for(Iterator<Integer> i = list.iterator(); i.hasNext();){
            int val = i.next();
            if(val%2==0){
                i.remove();
            }
        }
    }
}
