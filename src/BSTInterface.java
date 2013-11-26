import java.util.Iterator;


public interface BSTInterface<T> {

	public void insert(T data);
	public void remove(T toDelete);
	public boolean has(T toSearch) throws NullBinarySearchTreeException;
	public void inOrderTraversal();	
	public Iterator<T> iterator();
	public int size();
	public String toString();
	
}
