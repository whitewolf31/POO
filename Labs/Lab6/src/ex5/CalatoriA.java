package ex5;

public class CalatoriA extends Vagon {
    private int colete = 300;
    private int pasageri = 40;

    @Override
    public int getColete() {
        return colete;
    }

    @Override
    public int getPasageri() {
        return pasageri;
    }

    @Override
    public void deschideUsile() {
        System.out.println("Deschide usile la Vagon de tip CalatoriA");
    }

    @Override
    public void inchideUsile() {
        System.out.println("Inchide usile la Vagon de tip CalatoriA");
    }

    @Override
    public void blocheazaGeamurile() {
        System.out.println("Geamurile nu se pot bloca automat la vagon de tip CalatoriA");
    }

    @Override
    public String toString() {
        return "Vagon CalatoriA";
    }
}
