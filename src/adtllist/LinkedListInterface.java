
package adtllist;
//Author: Low Kah Yee

public interface LinkedListInterface <T>{
  public boolean add(T newEntry);
  public T remove(int givenPosition);
  public T getEntry(int givenPosition);
  public int getLength();
  public boolean isEmpty();
}
