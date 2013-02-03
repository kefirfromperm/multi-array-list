package org.kefirsf.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class SpeedTest {
    public static void main(String[] args) {
        test(new ArrayList<Integer>());
        test(new DoubleArrayList<Integer>());
        test(new LinkedList<Integer>());
    }

    public static void test(List<Integer> list) {
        list.clear();
        System.out.println();
        System.out.println("Collection: " + list.getClass().getName());

        test(list, 1000);
        test(list, 10000);
        test(list, 50000);
        test(list, 100000);
        test(list, 200000);
        test(list, 300000);
        test(list, 400000);
        test(list, 500000);
        test(list, 600000);
        test(list, 700000);
        test(list, 800000);
        test(list, 900000);
        test(list, 1000000);
    }

    private static void test(List<Integer> list, int size) {
        System.out.print(size);
        System.out.print(" ");

        long begin = System.currentTimeMillis();
        fill(list, size);
        long end = System.currentTimeMillis();
        System.out.print(end-begin);
        System.out.print(" ");

        begin = System.currentTimeMillis();
        sequenceRead(list);
        end = System.currentTimeMillis();
        System.out.print(end-begin);
        System.out.print(" ");

        begin = System.currentTimeMillis();
        indexRead(list);
        end = System.currentTimeMillis();
        System.out.print(end-begin);
        System.out.print(" ");

        begin = System.currentTimeMillis();
        filtration(list);
        end = System.currentTimeMillis();
        System.out.print(end - begin);
        System.out.print(" ");

        begin = System.currentTimeMillis();
        removeFirst(list);
        end = System.currentTimeMillis();
        System.out.print(end-begin);

        System.out.println();
    }

    private static void fill(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    private static void removeFirst(List<Integer> list){
        while (!list.isEmpty()){
            list.remove(0);
        }
    }

    private static void indexRead(List<Integer> list){
        for(int i=0; i<list.size(); i++){
            list.get(i);
        }
    }

    private static void sequenceRead(List<Integer> list){
        for(Iterator<Integer> i = list.iterator(); i.hasNext();){
            i.next();
        }
    }

    private static void filtration(List<Integer> list) {
        for (Iterator<Integer> i = list.iterator(); i.hasNext(); ) {
            int val = i.next();
            if (val % 2 == 0) {
                i.remove();
            }
        }
    }
}
