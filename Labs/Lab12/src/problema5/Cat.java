package problema5;

import java.io.FileReader;

public class Cat implements Visitor{
    @Override
    public void visit(Director directory) {
        System.out.println(directory.getName() + " is a directory!");
    }

    @Override
    public void visit(Fisier file) {
        try {
            FileReader fr = new FileReader(file.getFullPath());
            int content;
            while ((content = fr.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
