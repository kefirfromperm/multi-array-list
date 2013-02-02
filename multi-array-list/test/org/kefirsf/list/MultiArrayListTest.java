package org.kefirsf.list;

import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class MultiArrayListTest {
    @Test
    public void testAdd() {
        List<Integer> list = new MultiArrayList<Integer>();
        list.add(1);
        Assert.assertEquals((Integer) 1, list.get(0));
    }
}
