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

class Nodeimpl<T> implements Node{

	private T wert;
	private Node next;
	private Node previous;

	@Override
	public Object getWert() {
		return wert;
	}

	@Override
	public void setWert(Object wert) {
		this.wert = (T) wert;
	}

	@Override
	public Node getNext() {
		return next;
	}

	@Override
	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public Node getPrevious() {
		return previous;
	}

	@Override
	public void setPrevious(Node prev) {
		this.previous = prev;
	}
}

class Uebung {
	public static void main(String[] args) {
		Nodeimpl<String> node1 = new Nodeimpl<>();
		Nodeimpl<String> node2 = new Nodeimpl<>();

		node1.setWert("text1");
		node1.setNext(node2);

		node2.setWert("text2");
		node2.setPrevious(node1);

		System.out.println(node1.getWert());
		System.out.println(node1.getNext().getWert());
		System.out.println(node2.getPrevious().getWert());
	}
}