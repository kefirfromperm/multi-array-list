package org.kefirsf.list;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class DoubleArrayTest {

    public static final int SIZE = 10000;

    @Test
    public void test(){
        List<Integer> list = new DoubleArrayList<Integer>();

        for(int i=0; i< SIZE; i++){
            list.add(Integer.valueOf(i));
            Assert.assertEquals(i+1, list.size());
            Assert.assertEquals(Integer.valueOf(i), list.get(i));
        }

        for(int i=0; i< SIZE; i++){
            Assert.assertEquals(Integer.valueOf(i), list.get(i));
        }

        list.remove(5);
        Assert.assertEquals(SIZE - 1, list.size());
        Assert.assertEquals(Integer.valueOf(6), list.get(5));

        list.remove(0);
        Assert.assertEquals(SIZE-2, list.size());
        Assert.assertEquals(Integer.valueOf(7), list.get(5));
    }

    @Test
    public void testIterator(){
        List<Integer> doubleArrayList = new DoubleArrayList<Integer>();

        for(int i = 0; i<SIZE; i++){
            doubleArrayList.add(i);
        }

        for(Iterator<Integer> i = doubleArrayList.iterator(); i.hasNext();){
            int val = i.next();
            if(val%2==0){
                i.remove();
            }
        }

        List<Integer> linkedList = new LinkedList<Integer>();

        for(int i = 0; i<SIZE; i++){
            linkedList.add(i);
        }

        for(Iterator<Integer> i = linkedList.iterator(); i.hasNext();){
            int val = i.next();
            if(val%2==0){
                i.remove();
            }
        }

        Assert.assertEquals(linkedList.size(), doubleArrayList.size());

        for(int i = 0; i<linkedList.size();i++){
            Assert.assertEquals(linkedList.get(i), doubleArrayList.get(i));
        }
    }
}
