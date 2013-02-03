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
        System.out.println("Warming");
        test(new ArrayList<Integer>(), 1000);
        test(new DoubleArrayList<Integer>(), 1000);
        test(new LinkedList<Integer>(), 1000);

        System.out.println();
        System.out.println("DoubleArrayList");

        testRead(new DoubleArrayList<Integer>(), 1000000);
        testRead(new DoubleArrayList<Integer>(), 2000000);
        testRead(new DoubleArrayList<Integer>(), 3000000);
        testRead(new DoubleArrayList<Integer>(), 4000000);
        testRead(new DoubleArrayList<Integer>(), 5000000);
        testRead(new DoubleArrayList<Integer>(), 6000000);
        testRead(new DoubleArrayList<Integer>(), 7000000);
        testRead(new DoubleArrayList<Integer>(), 8000000);
        testRead(new DoubleArrayList<Integer>(), 9000000);
        testRead(new DoubleArrayList<Integer>(), 10000000);

        System.out.println();
        System.out.println("ArrayList");

        testRead(new ArrayList<Integer>(), 1000000);
        testRead(new ArrayList<Integer>(), 2000000);
        testRead(new ArrayList<Integer>(), 3000000);
        testRead(new ArrayList<Integer>(), 4000000);
        testRead(new ArrayList<Integer>(), 5000000);
        testRead(new ArrayList<Integer>(), 6000000);
        testRead(new ArrayList<Integer>(), 7000000);
        testRead(new ArrayList<Integer>(), 8000000);
        testRead(new ArrayList<Integer>(), 9000000);
        testRead(new ArrayList<Integer>(), 10000000);
    }

    private static void testFiltrations() {
        System.out.println();
        System.out.println("DoubleArrayList");

        testFiltration(new DoubleArrayList<Integer>(), 1000000);
        testFiltration(new DoubleArrayList<Integer>(), 2000000);
        testFiltration(new DoubleArrayList<Integer>(), 3000000);
        testFiltration(new DoubleArrayList<Integer>(), 4000000);
        testFiltration(new DoubleArrayList<Integer>(), 5000000);
        testFiltration(new DoubleArrayList<Integer>(), 6000000);
        testFiltration(new DoubleArrayList<Integer>(), 7000000);
        testFiltration(new DoubleArrayList<Integer>(), 8000000);
        testFiltration(new DoubleArrayList<Integer>(), 9000000);
        testFiltration(new DoubleArrayList<Integer>(), 10000000);

        System.out.println();
        System.out.println("LinkedList");

        testFiltration(new LinkedList<Integer>(), 1000000);
        testFiltration(new LinkedList<Integer>(), 2000000);
        testFiltration(new LinkedList<Integer>(), 3000000);
        testFiltration(new LinkedList<Integer>(), 4000000);
        testFiltration(new LinkedList<Integer>(), 5000000);
        testFiltration(new LinkedList<Integer>(), 6000000);
        testFiltration(new LinkedList<Integer>(), 7000000);
        testFiltration(new LinkedList<Integer>(), 8000000);
        testFiltration(new LinkedList<Integer>(), 9000000);
        testFiltration(new LinkedList<Integer>(), 10000000);
    }

    private static void fullTest() {
        System.out.println();
        System.out.println("ArrayList");

        test(new ArrayList<Integer>(), 20000);
        test(new ArrayList<Integer>(), 40000);
        test(new ArrayList<Integer>(), 60000);
        test(new ArrayList<Integer>(), 80000);
        test(new ArrayList<Integer>(), 100000);
        test(new ArrayList<Integer>(), 120000);
        test(new ArrayList<Integer>(), 140000);
        test(new ArrayList<Integer>(), 160000);
        test(new ArrayList<Integer>(), 180000);
        test(new ArrayList<Integer>(), 200000);

        System.out.println();
        System.out.println("DoubleArrayList");

        test(new DoubleArrayList<Integer>(), 20000);
        test(new DoubleArrayList<Integer>(), 40000);
        test(new DoubleArrayList<Integer>(), 60000);
        test(new DoubleArrayList<Integer>(), 80000);
        test(new DoubleArrayList<Integer>(), 100000);
        test(new DoubleArrayList<Integer>(), 120000);
        test(new DoubleArrayList<Integer>(), 140000);
        test(new DoubleArrayList<Integer>(), 160000);
        test(new DoubleArrayList<Integer>(), 180000);
        test(new DoubleArrayList<Integer>(), 200000);

        System.out.println();
        System.out.println("LinkedList");

        test(new LinkedList<Integer>(), 20000);
        test(new LinkedList<Integer>(), 40000);
        test(new LinkedList<Integer>(), 60000);
        test(new LinkedList<Integer>(), 80000);
        test(new LinkedList<Integer>(), 100000);
        test(new LinkedList<Integer>(), 120000);
        test(new LinkedList<Integer>(), 140000);
        test(new LinkedList<Integer>(), 160000);
        test(new LinkedList<Integer>(), 180000);
        test(new LinkedList<Integer>(), 200000);
    }

    private static void testRead(List<Integer> list, int size) {
        System.out.print(size);
        System.out.print(" ");

        long begin = System.currentTimeMillis();
        fill(list, size);
        long end = System.currentTimeMillis();
        System.out.print(end-begin);
        System.out.print(" ");

        begin = System.currentTimeMillis();
        indexRead(list);
        end = System.currentTimeMillis();
        System.out.print(end - begin);
        System.out.print(" ");

        System.out.println();
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
        System.out.print(end - begin);
        System.out.print(" ");

        begin = System.currentTimeMillis();
        filtration(list);
        end = System.currentTimeMillis();
        System.out.print(end - begin);
        System.out.print(" ");

        begin = System.currentTimeMillis();
        removeFirst(list);
        end = System.currentTimeMillis();
        System.out.print(end - begin);

        System.out.println();
    }

    private static void testFiltration(List<Integer> list, int size) {
        System.out.print(size);
        System.out.print(" ");

        long begin = System.currentTimeMillis();
        fill(list, size);
        long end = System.currentTimeMillis();
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
