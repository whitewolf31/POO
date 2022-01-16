package problema5;

public class Ls implements Visitor{
    @Override
    public void visit(Director directory) {
        System.out.println(directory.listFiles());
    }

    @Override
    public void visit(Fisier file) {
        System.out.println(file.getName() + " is a file!");
    }
}
