# ADTs and Implementations in Java

This is a custom implementations of the following ADTs.
- List 
- Hashing
- Stack 
- Queue 
- Priority Queue 
- Graph 
- Disjoint Set

### Project Setup
    Language: Java
    Build system: Maven
    JDK version: 18

## Implementations

### 1. The Linked List ADT.
The following operations make linked lists an ADT:

| Operations   | Description                                                       | Big-O |
|--------------|-------------------------------------------------------------------|-------|
| Insert:      | inserts an element into the list.                                 | O(n)  |
| Delete:      | removes and returns the specified position element from the list. | O(n)  |
| Delete List: | removes all elements of the list (disposes the list).             | O(1)  |
| Count:       | returns the number of elements in the list.                       | O(n)  |
| Find nth:    | Find nth node from the end of the list.                           | O(n)  |



### 2. Hash Table implementation with custom ArrayMap Class.
    Usage: Map<K, V> map = new ArrayMap<>();
An implementation of the Map interface.
It internally keeps track of its key-value pairs by using an array of <b>SimpleEntry<K, V> </b>objects.</p>

It uses the SimpleEntry key to generate a hash code that maps to an index of the Hash Table (Array)</p>
<h3>Collisions handling strategy</h3>
---
 
 *     Uses separate chaining method with a doubly-linked list to handle collisions
 *     Doubles the size of the table to the nearest prime number once the table is filled to 75%, and the existing entries are re-hashed

| Signatures                       | Description                                                                                                  | Big-O                                                      |
|----------------------------------|--------------------------------------------------------------------------------------------------------------|------------------------------------------------------------|
| V get(Object key)                | Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key. | O(1)                                                       |
| V put(K key, V value)            | Associates the specified value with the specified key in this map.                                           | O(1) except during resizing of the buckets that takes O(n) |
| V remove(Object key)             | Removes the mapping for a key from this map if it is present.                                                | O(1)                                                       |
| void clear()                     | Removes all of the mappings from this map.                                                                   | O(n)                                                       |
| boolean containsKey(Object key)  | Returns true if this map contains a mapping for the specified key.                                           | O(1)                                                       |
| int size()                       | Returns the number of key-value mappings in this map.                                                        | O(1)                                                       |
| Iterator<Entry<K, V>> iterator() | Returns an iterator that, when used, will yield all key-value mappings contained within this map.            | O(n)                                                       |



### 2. Priority Queue implementation with AVL and Heap.

## Basic Definitions
***

### Abstract Data Types:
- An abstract definition for expected operations and behavior
- Defines the input and outputs, not the implementations
- They are represented as Interfaces in Java.
---

### Interface:
- A construct in Java that defines a set of methods that a class promises to implement.
- It gives an is-a relationship without code sharing. Eg.
  - A Rectangle object can be treated as a Shape but inherits no code. So Rectangle Class implements the Shape Class.
---
### Data Structure:
A way of organizing and storing data.
Examples: arrays, linked lists, stacks, queues, trees
---
### Algorithm:
* A series of precise instructions to produce to a specific outcome
* Examples: binary search, merge sort, recursive backtracking.
---