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

        MultiArrayListPart<E> part = findPart(index);

        if (part != null) {
            return part.get(index);
        } else {
            throw new IllegalStateException("Something wrong!");
        }
    }

    private MultiArrayListPart<E> findPart(int index) {
        int partIndex = findPartIndex(index);
        if (partIndex >= 0) {
            return parts.get(partIndex);
        } else {
            return null;
        }
    }

    private int findPartIndex(int index) {
        int partIndex = -1;
        for (int i = 0; i < parts.size(); i++) {
            MultiArrayListPart<E> part = parts.get(i);
            if (part.inBounds(index)) {
                partIndex = i;
                break;
            }
        }
        return partIndex;
    }

    @Override
    public int size() {
        return 0;
    }
}
