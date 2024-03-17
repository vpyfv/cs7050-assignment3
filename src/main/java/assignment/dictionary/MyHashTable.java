package assignment.dictionary;

/*

 */

//

import java.util.*;
import java.io.*;
import java.util.Dictionary;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.BiFunction;

/**
 *
 */
public class MyHashTable<K, V>
//        
//        
{
    List<Node<K, V>> buckets;
    private int noOfBuckets;
    private int size;

    public MyHashTable() {
        buckets = new ArrayList<>();
        noOfBuckets = 10;
        size = 0;

        for (int i = 0; i < noOfBuckets; i++) {
            buckets.add(null);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets.get(index);
        Node<K, V> prev = null;

        //loop through the node
        while (head != null) {
            // key found
            if (head.key.equals(key)) break;

            //key not found
            prev = head;
            head = head.next;
        }
        if (head == null) return null;

        size--;

        if (prev != null)
            prev.next = head.next;
        else
            buckets.add(index, head.next);

        return head.value;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = buckets.get(bucketIndex);

        // Check if key is already present
        while (head != null) {
            if (head.key.equals(key)) {
                V oldVal = head.value;
                head.value = value;
                return oldVal;
            }
            head = head.next;
        }

        size++;
        int index = getBucketIndex(key);
        head = buckets.get(index);
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next=head;
        buckets.set(index,newNode);

//           if((1.0 * size)/noOfBuckets>=0.7){
//               List<Node<K,V>> tempNode = buckets;
//               buckets = new ArrayList<>();
//               noOfBuckets=2*noOfBuckets;
//               size=0;
//               for(int i=0;i<noOfBuckets;i++){
//                   buckets.add(null);
//               }
//               for(Node<K,V> headNode:tempNode){
//                   while (headNode!=null){
//                       put(headNode.key,headNode.value);
//                       headNode=headNode.next;
//                   }
//               }
//           }
        return null;
    }

    public boolean containsKey(Object key) {
        int bucketIndex = getBucketIndex((K) key);
        Node<K, V> head = buckets.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key))
                return true;
            head = head.next;
        }
        return false;
    }

    public AList<K> keySet() {
        AList<K> keys = new AList<>();
        for (Node<K, V> head : buckets) {
            while (head != null) {
                keys.add(head.key);
                head = head.next;
            }
        }
        return keys;
    }

    public AList<V> values() {
        AList<V> values = new AList<>();
        for (Node<K, V> head : buckets) {
            Node<K, V> currentNode = head;
            while (currentNode != null) {
                values.add(currentNode.value);
                currentNode = currentNode.next;
            }
        }
        return values;
    }

    public void clear() {
        buckets = new ArrayList<>();
        noOfBuckets = 10;
        size = 0;
        for (int i = 0; i < noOfBuckets; i++) {
            buckets.add(null);
        }
    }


    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % noOfBuckets;
        return Math.abs(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean firstBucket = true;
        for (int i = 0; i < buckets.size(); i++) {
            Node<K, V> head = buckets.get(i);
            if (head != null) {
                if (!firstBucket) {
                    sb.append(", ");
                } else {
                    firstBucket = false;
                }
                sb.append("[Bucket ").append(i).append("]: ");
                boolean firstNode = true;
                while (head != null) {
                    if (!firstNode) {
                        sb.append(" -> ");
                    } else {
                        firstNode = false;
                    }
                    sb.append("{").append(head.key).append("=").append(head.value).append("}");
                    head = head.next;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }


    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}


