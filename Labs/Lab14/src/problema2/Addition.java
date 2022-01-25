package problema2;

public interface Addition<T extends Number> {
    public T zero();

    public T add(T a, T b);
}
