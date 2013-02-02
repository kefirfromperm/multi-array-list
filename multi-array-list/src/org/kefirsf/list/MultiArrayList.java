package org.kefirsf.list;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class MultiArrayList<E> extends AbstractList<E> {
    private List<MultiArrayListPart<E>> parts = new ArrayList<MultiArrayListPart<E>>();
    int size = 0;

    public MultiArrayList() {
        parts.add(new MultiArrayListPart<E>(MultiArrayListPart.MIN_PART_SIZE, 0, MultiArrayListPart.MIN_PART_SIZE / 2));
    }

    @Override
    public boolean add(E e) {
        if (parts.get(parts.size() - 1).addLast(e)) {
            size++;
            return true;
        } else {
            int partSize = Math.max(MultiArrayListPart.MIN_PART_SIZE, size / 2);
            MultiArrayListPart<E> part = new MultiArrayListPart<E>(partSize, size, 0);
            parts.add(part);
            part.addLast(e);
            size++;
            return true;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        for (MultiArrayListPart<E> part : parts) {
            if (part.inBounds(index)) {
                return part.get(index);
            }
        }

        throw new IllegalStateException("Something wrong!");
    }

    @Override
    public int size() {
        return 0;
    }
}
