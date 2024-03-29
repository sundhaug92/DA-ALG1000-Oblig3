/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package da.alg1000.oblig3;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 *
 * @param <TKey>
 * @param <TValue>
 * @author Martin
 */
public class HashTable<TKey, TValue> {

    ArrayList<LinkedList<AbstractMap.SimpleEntry<TKey, TValue>>> buckets;
    AccessOptimizationPolicy OptimizationPolicy;

    /**
     *
     * @param numberOfBuckets
     */
    public HashTable(int numberOfBuckets, AccessOptimizationPolicy OptimizationPolicy) {
        buckets = new ArrayList<>();
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new LinkedList<AbstractMap.SimpleEntry<TKey, TValue>>());
        }
    }

    /**
     *
     * @param Key
     * @return value of element if it exists, or null
     */
    TValue get(TKey Key) {
        if (size() != 0 && contains(Key)) {
            int index = getIndexFor(Key);
            TValue Value = getBucketFor(Key).get(index).getValue();

            if (OptimizationPolicy != AccessOptimizationPolicy.None && ((index != 0 && OptimizationPolicy == AccessOptimizationPolicy.MostRecentlyUsed || (index != getBucketFor(Key).size() - 1 && OptimizationPolicy == AccessOptimizationPolicy.LeastRecentlyUsed)))) {
                remove(Key);
                add(Key, Value);
            }

            return Value;
        } else {
            return null;
        }
    }

    /**
     *
     * @param Key key of the element
     * @param value new value of element
     * @return old value of element
     */
    TValue set(TKey Key, TValue Value) {
        if (contains(Key)) {
            TValue oldValue = get(Key);
            int index = getIndexFor(Key);
            if (OptimizationPolicy != AccessOptimizationPolicy.None && ((index != 0 && OptimizationPolicy == AccessOptimizationPolicy.MostRecentlyUsed || (index != getBucketFor(Key).size() - 1 && OptimizationPolicy == AccessOptimizationPolicy.LeastRecentlyUsed)))) {
                remove(Key);
                add(Key, Value);
            } else {
                getBucketFor(Key).get(getIndexFor(Key)).setValue(Value);
            }
            return oldValue;
        } else {
            return null;
        }
    }

    /**
     *
     * @param Key
     * @return
     */
    LinkedList<AbstractMap.SimpleEntry<TKey, TValue>> getBucketFor(TKey Key) {
        return buckets.get(getBucketIdFor(Key));
    }

    /**
     *
     * @param Key
     * @return index of key, relative to the containing bucket
     */
    int getIndexFor(TKey Key) {
        LinkedList<AbstractMap.SimpleEntry<TKey, TValue>> bucket = getBucketFor(Key);
        int i = 0;
        for (Object o : bucket.toArray()) {
            AbstractMap.SimpleEntry<TKey, TValue> entry = (AbstractMap.SimpleEntry<TKey, TValue>) o;
            if (entry.getKey().equals(Key)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     *
     * @param Key
     * @return The hashed key
     */
    String hash(TKey Key) {
        String s = "";
        byte[] buf = Key.toString().getBytes();
        for (byte b : buf) {
            s += toHex(b);
        }
        return s;
    }

    /**
     *
     * @param b byte to convert
     * @return
     */
    private static String toHex(byte b) {
        String s = "";
        int n = (b > 0) ? b : 256 + b;
        s += halfToHex(n >> 4);
        s += halfToHex(n & 15);
        return s;
    }

    private static char halfToHex(int i) {
        if (i < 10) {
            return (char) ('0' + i);
        } else {
            return (char) ('A' + (i - 10));
        }
    }

    /**
     *
     * @param Key
     * @return
     */
    int getBucketIdFor(TKey Key) {
        int i;
        String h = hash(Key).substring(0, Key.toString().length() < 4 ? Key.toString().length() : 7);
        i = Integer.parseInt(h.toLowerCase(), 16);
        return i % buckets.size();
    }

    /**
     *
     * @return Total number of elements
     */
    int size() {
        int r = 0;
        for (LinkedList<AbstractMap.SimpleEntry<TKey, TValue>> bucket : buckets) {
            r += bucket.size();
        }
        return r;
    }

    /**
     *
     * @return
     */
    double calculateLoadFactor() {
        return (double) size() / (double) buckets.size();
    }

    /**
     * @param Key
     * @param value
     */
    void add(TKey Key, TValue value) {
        LinkedList<AbstractMap.SimpleEntry<TKey, TValue>> bucket = this.getBucketFor(Key);
        if (!contains(Key)) {
            if (OptimizationPolicy != AccessOptimizationPolicy.LeastRecentlyUsed) {
                bucket.add(new AbstractMap.SimpleEntry<>(Key, value));
            } else {
                bucket.addLast(new AbstractMap.SimpleEntry<>(Key, value));
            }
        }
    }

    /**
     *
     * @param Key
     */
    void remove(TKey Key) {
        int i = 0;
        LinkedList<AbstractMap.SimpleEntry<TKey, TValue>> bucket = getBucketFor(Key);
        for (AbstractMap.SimpleEntry<TKey, TValue> se : bucket) {
            if (se.getKey().equals(Key)) {
                bucket.remove(i);
                break;
            }
            i++;
        }
    }

    /**
     *
     * @return table as array
     */
    Object[] toArray() {
        ArrayList<AbstractMap.SimpleEntry<TKey, TValue>> list = new ArrayList<>();

        for (LinkedList<AbstractMap.SimpleEntry<TKey, TValue>> bucket : buckets) {
            for (Object o : bucket.toArray()) {
                list.add((AbstractMap.SimpleEntry<TKey, TValue>) o);
            }
        }

        return list.toArray();
    }

    /**
     *
     * @param Key
     * @return
     */
    public boolean contains(TKey Key) {
        for (LinkedList<AbstractMap.SimpleEntry<TKey, TValue>> bucket : buckets) {
            for (Object o : bucket.toArray()) {
                if (((AbstractMap.SimpleEntry<TKey, TValue>) o).getKey().equals(Key)) {
                    return true;
                }
            }
        }
        return false;
    }
}
