package ex5;

public class Marfa extends Vagon {
    private int colete = 400;

    @Override
    public int getColete() {
        return colete;
    }

    @Override
    public int getPasageri() {
        return 0;
    }

    @Override
    public void deschideUsile() {
        System.out.println("Vagonul marfa nu permite sa se deschida usile manual");
    }

    @Override
    public void inchideUsile() {
        System.out.println("Vagonul marfa nu permite sa se inchida usile automat");
    }

    @Override
    public void blocheazaGeamurile() {
        System.out.println("Vagonul marfa nu permite blocarea geamurilor automat");
    }

    @Override
    public String toString() {
        return "Vagon Marfa";
    }
}
