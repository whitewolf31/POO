package problema5;

import java.io.File;

public class Test {
    public static void main (String[] args) {
        File currentDir = new File(".");
        Director director = new Director(currentDir.getName());
        File[] children = currentDir.listFiles();
        for (int i = 0; i < children.length; i++) {
            if (children[i].isDirectory()) director.addRepo(new Director(children[i].getName()));
            else director.addRepo(new Fisier(children[i].getName(), children[i].getAbsolutePath()));
        }
        Cat cat = new Cat();
        Ls ls = new Ls();
        director.accept(ls);
        System.out.println();
        for (Repository repo: director.getList()) {
            repo.accept(cat);
        }
    }
}
