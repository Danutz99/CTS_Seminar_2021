package dan.seminar3;

public class Macara {
    private TipContainer tipContainer;
    private int timpManipulare;

    public Macara(TipContainer tipContainer, int timpManipulare) {
        this.tipContainer = tipContainer;
        this.timpManipulare = timpManipulare;
    }

    public Macara() {
        this.tipContainer = null;
        this.timpManipulare = 0;
    }

    public TipContainer getTipContainer() {
        return tipContainer;
    }

    public void setTipContainer(TipContainer tipContainer) {
        this.tipContainer = tipContainer;
    }

    public int getTimpManipulare() {
        return timpManipulare;
    }

    public void setTimpManipulare(int timpManipulare) {
        this.timpManipulare = timpManipulare;
    }
}
