package org.kefirsf.list;

/**
 * @author Vitalii Samolovskikh aka Kefir
 */
public class MultiArrayListPart<E> {
    public static final int MIN_PART_SIZE = 32;

    /**
     * Offset in MultiArrayList
     */
    private int globalOffset;

    /**
     * Offset in local array
     */
    private int localOffset;

    /**
     * Length of part
     */
    private int length;

    private Object[] array;

    public MultiArrayListPart(int partSize, int globalOffset, int localOffset) {
        this.globalOffset = globalOffset;
        this.localOffset = localOffset;
        this.length = 0;
        this.array = new Object[partSize];
    }

    /**
     * @return left part
     */
    public MultiArrayListPart<E> cut(int index){
        MultiArrayListPart<E> left = new MultiArrayListPart<E>(array.length, globalOffset, localOffset);
        left.length = index - globalOffset;
        left.array = new Object[array.length];
        System.arraycopy(this.array, localOffset, left.array, left.localOffset, left.length);

        localOffset = index - globalOffset;
        length = globalOffset+length-index;
        globalOffset = index;

        return left;
    }

    public boolean addFirst(E element) {
        if (localOffset > 0) {
            localOffset--;
            array[localOffset] = element;
            return true;
        } else {
            return false;
        }
    }

    public void removeFirst() {
        localOffset++;
        length--;
    }

    public boolean addLast(E element) {
        int index = localOffset + length;
        if (localOffset + length != array.length) {
            array[index] = element;
            length++;
            return true;
        } else {
            return false;
        }
    }

    public void removeLast() {
        length--;
    }

    public E get(int index) {
        if (!inBounds(index)) {
            throw new IndexOutOfBoundsException();
        }

        return (E) array[index - globalOffset + localOffset];
    }

    public boolean inBounds(int index) {
        return (index >= globalOffset && index <= globalOffset + length);
    }

    public int getLeftSpace(){
        return localOffset;
    }

    public int getRightSpace(){
        return array.length - localOffset - length;
    }

    public void shiftLeft() {
        if (globalOffset <= 0) {
            throw new IllegalStateException("Global offset can't be negative.");
        }

        globalOffset--;
    }

    public void shiftRight() {
        globalOffset++;
    }
}
