package HashTable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;


class ArrayMapTest {
    Map<String, Integer> m;
    @BeforeEach
    void setUp() {
        m = new ArrayMap<>();
        m.put("Austin", 1);
        m.put("austin", 2);
        m.put("Uche", 3);
        m.put("uche", 4);
        m.put("Nkechi", 5);
        m.put("Baby", 6);
        m.put("Joy", 7);
        m.put("Favor", 8);
        m.put("Precious", 9);
        m.put("Ada", 10);
        m.put("Chinwe", 11);
        m.put("Ngozi", 12);
        m.put("Ogo", 13);
        m.put("Ify", 14);
    }


    @Test
    void containsKey() {
        Assertions.assertTrue(m.containsKey("Ogo"));
        assertThat(m.containsKey("ifY"), is(false));
    }

    @Test
    void containsValue() {
        assertThat(m.containsValue(20), is(false));
        assertThat(m.containsValue(12), is(true));
    }

    @Test
    void get() {
        int size = m.size();
        assertThat(m.get("Uche"), is(equalTo(3)));
        assertThat(m.size(), is(equalTo(size)));
    }

    @Test
    void put() {
        int size = m.size();
        m.put("Testing", 25);

        assertThat(m.size(), is(equalTo(++size)));
        Assertions.assertTrue(m.containsKey("Testing"));

        assertThat(m.put("Testing", 30), is(equalTo(25)));
        assertThat(m.get("Testing"), is(equalTo(30)));
        assertThat(m.size(), is(equalTo(size)));
    }
    @Test
    void iterator() {
        for (Map.Entry<String, Integer> stringIntegerEntry : m.entrySet()) {
            assertThat(stringIntegerEntry, is(notNullValue()));
        }
    }

    @Test
    void clear() {
        assertThat(m.size(), is(greaterThan(0)));
        m.clear();
        assertThat(m.size(), is(equalTo(0)));
    }
}