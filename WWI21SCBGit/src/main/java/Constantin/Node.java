package Constantin;

public interface Node<T> {

	public T getWert();
	public void setWert(T wert);
	public Node<T> getNext();
	public void setNext(Node<T> next);
	public Node<T> getPrevious();
	public void setPrevious(Node<T> prev);
	public String toString();
	
}
