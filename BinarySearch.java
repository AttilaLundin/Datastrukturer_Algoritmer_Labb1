import java.util.Comparator;

public class BinarySearch {

  // Common description of the below functions:
  // * Precondition: `a` is sorted according to the given comparator.
  // * Precondition: all arguments are non-null (no need to check).
  // * Required complexity: O(log(n)) comparisons where n is the length of `a`.

  // Check if the array `a` contains the given search key.
  public static <T> boolean contains(T[] a, T key, Comparator<T> comparator) {
    return binarySearchContains(a, key, comparator, a.length-1, 0) != -1;
  }

  private static <T> int binarySearchContains(T[] a, T key, Comparator<T> comparator, int high, int low){
    if(low <= high){
      int middlePosition = (high + low)/2;
      if(comparator.compare(a[middlePosition], key) == 0) return middlePosition;
      else if (comparator.compare(a[middlePosition], key) < 0) return binarySearchContains(a, key, comparator, high, middlePosition + 1);
      else return binarySearchContains(a, key, comparator, middlePosition - 1, low);
    }
    return -1;
  }

  // Return the *first position* of `key` in `a`, or -1 if `key` does not occur.
  public static <T> int firstIndexOf(T[] a, T key, Comparator<T> comparator) {
    int indexOfKey = binarySearchContains(a, key, comparator, a.length-1, 0);
    while(indexOfKey >= 1 && a[indexOfKey - 1] == key) indexOfKey--;
    return indexOfKey;
  }

  // Versions of the above functions that use the natural ordering of the type T.
  // T needs to be "comparable" (i.e., implement the interface Comparable).
  // Examples: Integer, String (the alphabetic ordering)

  public static <T extends Comparable<? super T>> boolean contains(T[] a, T key) {
    return contains(a, key, Comparator.naturalOrder());
  }

  public static <T extends Comparable<? super T>> int firstIndexOf(T[] a, T key) {
    return firstIndexOf(a, key, Comparator.naturalOrder());
  }

  // Your tests.

  public static void main(String[] args) {
    Integer[] a = { 1, 3, 5, 7, 9 };
    assert (contains(a, 1));
    assert !contains(a, 4);
    assert contains(a, 7);

    String[] b = { "cat", "cat", "cat", "dog", "turtle", "turtle" };
    assert firstIndexOf(b, "cat") == 0;
    assert firstIndexOf(b, "dog") == 3;
    assert firstIndexOf(b, "turtle") == 4;
    assert firstIndexOf(b, "zebra") == -1;
    assert firstIndexOf(b, "bee") == -1;
  }

}
