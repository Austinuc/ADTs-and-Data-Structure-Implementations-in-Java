package Hashing_with_Map_Implementation;

import java.util.*;
import java.util.function.Consumer;

/**
 * <h1>An implementation of the Map interface</h1>
 * <p>It internally keeps track of its key-value pairs by using an array of <b>SimpleEntry<K, V> </b>objects.</p>
 * <p>It uses the SimpleEntry key to generate a hash code that maps to an index of the Hash Table (Array)</p>
 * <br><hr><br>
 * <h2>Collisions handling</h2>
 *
 * <ul>
 *     <li>Uses separate chaining method with a doubly-linked list to handle collisions</li>
 *     <li>Doubles the size of the table to the nearest prime number once the table is filled to 75%,
 *          and the existing entries are re-hashed</li>
 * </ul>
 * <p></p>
 *
 * @param <K> key-pair:
 * @param <V> value-pair:
 */
public class ArrayMap<K, V> implements Map<K, V> {

    SimpleEntry<K, V>[] table;

    /**
     * The default initial capacity - MUST be a power of two.
     */
    private final int DEFAULT_CAPACITY = 7; //intentionally chose a prime number

    /**
     * Load limit for resizing of the hash table and rehashing of the entries
     */
    private double loadFactor = 0.75; //75% of table capacity
    private int capacity;
    int size;

    /**
     * Constructs a map with default properties
     */
    public ArrayMap() {
        capacity = DEFAULT_CAPACITY;
        table = new SimpleEntry[capacity];
        size = 0;
    }
//    public ArrayMap(int capacity) {
//        this.capacity = capacity;
//        table = new SimpleEntry[capacity];
//        size = 0;
//    }
//    public ArrayMap(int capacity, double loadFactor) {
//        this.loadFactor = loadFactor > 1 ? loadFactor/100 : loadFactor;
//        this.capacity = capacity;
//        table = new SimpleEntry[capacity];
//        size = 0;
//    }



    /**
     * A Doubly-Linked Entry node used for entries
     * @param <K>
     * @param <V>
     */
    static class SimpleEntry<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        SimpleEntry<K, V> next;
        SimpleEntry<K, V> prev;

        SimpleEntry(int hash, K key, V value, SimpleEntry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }
        public final String toString() {
            return key + "=" + value;
        }
        public final V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (this == o) return true;

            return o instanceof Map.Entry<?, ?> node
                    && Objects.equals(key, node.getKey())
                    && Objects.equals(value, node.getValue());
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

    private void resize() {
        int newSize = this.capacity * 2 + 1;

        while(!isPrime(this.capacity = newSize)) //get the nearest prime number for table size
            newSize++;

        SimpleEntry<K, V>[] newTable = new SimpleEntry[newSize];

        for (Map.Entry<K, V> e : entrySet()) { //Copy old table to new table rehashing each entry
            addEntryToTable(e.getKey(), e.getValue(), newTable);
            --size;
        }
        this.table = newTable; //Update hash table
    }

    private boolean isPrime(int n) {

        if (n <= 1) return false;

        for (int i = 2; i < n/2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public final int hash(Object key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()*key.toString().length()) % capacity;
    }

    public SimpleEntry<K, V> getNode(Object key) {
        SimpleEntry<K, V>[] t = table;
        SimpleEntry<K, V> first, e; int hash;
        if (t != null && size > 0
                && (first = t[hash = hash(key)]) != null) {
            if (first.hash == hash && first.key == key || (key != null) && key.equals(first.key)) {
                return first;
            }
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash(key) && e.key == key ||
                            (key != null && key.equals(e.key)))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        Collection<V> c = values();
        for (V v : c) {
            if (Objects.equals(v, value))
                return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        return getNode(key).value;
    }

    @Override
    public V put(K key, V value) {
        if (size / (double)capacity >= loadFactor) {
            resize();
        }
        return addEntryToTable(key, value, this.table);
    }

    /**
     *
     * @param key
     * @param value
     * @param table
     * @return previous value, or null if none
     */
    private V addEntryToTable(K key, V value, SimpleEntry<K, V>[] table) {
        int h;
        SimpleEntry<K, V> newSimpleEntry = new SimpleEntry<>(h = hash(key), key, value, null);
        SimpleEntry<K, V> e;
        if ((e = table[h]) == null) {
            table[h] = newSimpleEntry;
        } else {
            SimpleEntry<K, V> k;
            do {
                if (e.key == key) {
                    V oldValue = e.value;
                    e.value = value;
                    return oldValue;
                }
                k = e;
            } while ((e = e.next) != null);
            newSimpleEntry.prev = k;
            k.next = newSimpleEntry;
        }
        this.size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        if (containsKey(key)) {
            SimpleEntry<K, V> n = getNode(key);
            SimpleEntry<K, V>[] tb = table;

            if (n.prev == null) {
                tb[hash(key)] = n.next;
            } else {
                n.prev.next = n.next;
                if (n.next != null) {
                    n.next.prev = n.prev;
                }
            }
            --size;
            return n.value;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }

    }

    @Override
    public void clear() {
        SimpleEntry<K, V>[] tb;
        if ((tb = table) != null && size > 0) {
            Arrays.fill(tb, null);
            size = 0;
        }
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override
    public Collection<V> values() {
        return new Values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }
    @Override
    public String toString() {
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        StringBuilder s = new StringBuilder("{");
        while (it.hasNext()) {
            s.append(it.next());
            s.append(it.hasNext() ? ", " : "");
        }
        return s + "}";
    }

    class EntryIterator implements Iterator<Map.Entry<K, V>> {
        SimpleEntry<K, V> next;
        SimpleEntry<K, V> current;
        int index = 0;

        EntryIterator() {
            next = current = null;
            SimpleEntry<K, V>[] tb = table;
            if (tb != null && size > 0) {
                do {}
                while (index < tb.length && (next = tb[index++]) == null); // advance to the first non-null entry
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public SimpleEntry<K, V> next() {
            SimpleEntry<K, V> c = next;
            SimpleEntry<K, V>[] tb = table;

            if (c == null) return null;
            current = c;
            if (c.next == null && index < tb.length) {

                do {}
                while (index < tb.length && (next = tb[index++]) == null); // advance to the first non-null entry
            } else {
                next = c.next;
            }
            return current;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super Entry<K, V>> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    final class KeySetIterator implements Iterator<K> {
        K key;
        K next;
        int index;

        KeySetIterator() {
            SimpleEntry<K, V>[] tb = table;
            index = 0;
            key = next = null;
            if (tb != null && size > 0) {
                do {}
                while(index < tb.length && (next = (tb[index++] != null) ? tb[index - 1].key : null) == null);
            }

        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public K next() {
            K c = next;
            SimpleEntry<K, V>[] t = table;
            SimpleEntry<K, V> tb = getNode(c);

            if (c == null) return null;
            key = c;
            if (tb.next == null) {
                if (index >= t.length) { //end of table
                    next = null;
                    return key;
                }

                do {}
                while (index < t.length
                        && (next = (t[index++] != null) ? t[index - 1].key : null) == null); // advance to the first non-null entry
            } else {
                next = tb.next.key;
            }
            return key;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super K> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    final class ValuesIterator implements Iterator<V> {
        V value;
        List<V> values;
        V next;
        int index;

        ValuesIterator() {
            SimpleEntry<K ,V>[] tb = table;
            values = new ArrayList<>();
            index = 0;
            value = next = null;
            if (tb != null && size > 0) {
                Iterator<Map.Entry<K, V>> it = new EntryIterator();

                while (it.hasNext()) {
                    values.add(it.next().getValue());
                }
                next = values.get(index++);
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public V next() {
            V c = value = next;

            if (c == null) return null;

            if (index < values().size()) {
                next = values.get(index++);
            } else {
                next = null;
            }
            return value;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super V> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    class KeySet extends AbstractSet<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeySetIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

    class Values extends AbstractCollection<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValuesIterator();
        }

        @Override
        public int size() {
            return size;
        }
    }

}


