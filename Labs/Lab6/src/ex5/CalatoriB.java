package ex5;

public class CalatoriB extends Vagon{
    private int colete = 400;
    private int pasageri = 50;

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
        System.out.println("Deschide usile la Vagon de tip CalatoriB");
    }

    @Override
    public void inchideUsile() {
        System.out.println("Inchide usile la Vagon de tip CalatoriB");
    }

    @Override
    public void blocheazaGeamurile() {
        System.out.println("Blocheaza geamurile la Vagon de tip CalatoriB");
    }

    @Override
    public String toString() {
        return "Vagon CalatoriB";
    }
}
