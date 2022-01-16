package problema5;

import java.util.ArrayList;
import java.util.List;

public class Director extends Repository{
    List<Repository> children;

    public Director(String name) {
        super(name);
        children = new ArrayList<Repository>();
    }

    public void addRepo(Repository repo) {
        children.add(repo);
    }

    public List<Repository> getList() { return children; }

    public String listFiles() {
        StringBuilder sb = new StringBuilder();
        for (Repository repo: children) {
            sb.append(repo.getName()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
