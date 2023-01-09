package HashTable;

import java.util.Iterator;
import java.util.Map;

public interface EntryIteratorInterface<K, V> extends Iterator<Map.Entry<K, V>> {

    Map.Entry<K, V> getPrevious();
}
