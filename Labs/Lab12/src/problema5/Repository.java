package problema5;

public abstract class Repository implements Visitable{
    protected String name;

    public Repository(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public abstract void accept(Visitor visitor);
}
