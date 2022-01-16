package problema5;

public class Fisier extends Repository{

    private String fullPath;

    public Fisier(String name, String fullPath) {
        super(name);
        this.fullPath = fullPath;
    }

    public String getFullPath() { return fullPath; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
