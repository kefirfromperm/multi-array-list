package org.kefirsf.list;

import java.util.AbstractList;

/**
 * List based on two classes.
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class DoubleArrayList<E> extends AbstractList<E> {
    private static final int INITIAL_CAPACITY = 16;

    private Object[] leftData;
    private int leftOffset;
    private int leftSize;

    private Object[] rightData;
    private int rightOffset;
    private int rightSize;

    public DoubleArrayList() {
        leftData = new Object[INITIAL_CAPACITY];
        leftOffset = INITIAL_CAPACITY / 2;
        leftSize = 0;

        rightData = new Object[INITIAL_CAPACITY];
        rightOffset = INITIAL_CAPACITY / 2;
        rightSize = 0;
    }

    private void ensureCapacity() {
        modCount++;

        int size = size();
        int newCapacity = size * 2;

        Object[] oldLeftData = leftData;
        int oldLeftOffset = leftOffset;
        int oldLeftSize = leftSize;

        Object[] oldRightData = rightData;
        int oldRightOffset = rightOffset;
        int oldRightSize = rightSize;

        leftData = new Object[newCapacity];
        leftOffset = newCapacity * 3 / 8;
        leftSize = size / 2 + size % 2;

        rightData = new Object[newCapacity];
        rightOffset = newCapacity * 3 / 8;
        rightSize = size / 2;

        if (oldLeftSize == leftSize) {
            System.arraycopy(oldLeftData, oldLeftOffset, leftData, leftOffset, leftSize);
            System.arraycopy(oldRightData, oldRightOffset, rightData, rightOffset, rightSize);
        } else if (oldLeftSize < leftSize) {
            int sub = leftSize - oldLeftSize;
            System.arraycopy(oldLeftData, oldLeftOffset, leftData, leftOffset, oldLeftSize);
            System.arraycopy(oldRightData, oldRightOffset, leftData, leftOffset + oldLeftSize, sub);
            System.arraycopy(oldRightData, oldRightOffset + sub, rightData, rightOffset, rightSize);
        } else if (oldLeftSize > leftSize) {
            int sub = oldLeftSize - leftSize;
            System.arraycopy(oldLeftData, oldLeftOffset, leftData, leftOffset, leftSize);
            System.arraycopy(oldLeftData, oldLeftOffset + leftSize, rightData, rightOffset, sub);
            System.arraycopy(oldRightData, oldRightOffset, rightData, rightOffset + sub, oldRightSize);
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < leftSize) {
            E previous = (E) leftData[leftOffset + index];
            leftData[leftOffset + index] = element;
            return previous;
        } else {
            E previous = (E) rightData[rightOffset + index - leftSize];
            rightData[rightOffset + index - leftSize] = element;
            return previous;
        }
    }

    @Override
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        modCount++;

        if (index == 0) {
            addFirst(element);
        } else if (index == size()) {
            addLast(element);
        } else {
            addMiddle(index, element);
        }
    }

    private void addMiddle(int index, E element) {
        if (index < leftSize) {
            if (leftSize - index > rightOffset) {
                ensureCapacity();
            }
        } else if (index > leftSize) {
            if (index - leftSize > leftData.length - leftSize - leftOffset) {
                ensureCapacity();
            }
        } else {
            if (leftOffset + leftSize == leftData.length && rightOffset == 0) {
                ensureCapacity();
            }
        }

        if (index < leftSize) {
            int count = leftSize - index;

            rightOffset = rightOffset - count;
            rightSize = rightSize + count;
            System.arraycopy(leftData, leftOffset + index, rightData, rightOffset, count);
            leftSize = leftSize - count + 1;
            leftData[leftSize - 1] = element;
        } else if (index > leftSize) {
            int count = index - leftSize;

            leftSize = leftSize + count;
            System.arraycopy(rightData, rightOffset, leftData, leftOffset + leftSize - count, count);
            rightOffset = rightOffset + count - 1;
            rightSize = rightSize - count + 1;
            rightData[rightOffset] = element;
        } else {
            if (leftData.length - leftOffset + leftSize > rightOffset) {
                leftData[leftSize] = element;
                leftSize++;
            } else {
                rightOffset--;
                rightData[rightOffset] = element;
                rightSize++;
            }
        }
    }

    private void addLast(E element) {
        if (rightOffset + rightSize >= rightData.length) {
            ensureCapacity();
        }

        rightSize++;
        rightData[rightOffset + rightSize - 1] = element;
    }

    private void addFirst(E element) {
        if (leftOffset == 0) {
            ensureCapacity();
        }

        leftOffset--;
        leftSize++;
        leftData[leftOffset] = element;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        modCount++;

        E e = get(index);

        if (index == leftSize - 1) {
            leftSize--;
        } else if (index == leftSize) {
            rightOffset++;
            rightSize--;
        } else if (index == 0) {
            leftOffset++;
            leftSize--;
        } else if (index == size() - 1) {
            rightSize--;
        } else {
            int count = Math.abs(index - leftSize);
            if((index<leftSize && count-1>rightOffset) || (index>leftSize && count>leftData.length-leftOffset-leftSize)){
                ensureCapacity();
            }

            if(index<leftSize){
                count = leftSize - index - 1;
                rightOffset = rightOffset - count;
                rightSize = rightSize + count;
                System.arraycopy(leftData, leftOffset+index+1, rightData, rightOffset, count);
                leftSize = index;
            } else {
                count = index-leftSize;

                leftSize = leftSize + count;
                System.arraycopy(rightData, rightOffset, leftData, leftOffset + leftSize - count, count);
                rightOffset = rightOffset + count + 1;
                rightSize = rightSize - count - 1;
            }
        }

        return e;
    }

    @Override
    public E get(int index) {
        if (index < leftSize) {
            return (E) leftData[leftOffset + index];
        } else {
            return (E) rightData[rightOffset + index - leftSize];
        }
    }

    @Override
    public int size() {
        return leftSize + rightSize;
    }
}
