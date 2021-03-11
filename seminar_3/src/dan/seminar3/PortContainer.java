package dan.seminar3;

import java.util.Scanner;

public class PortContainer implements Cloneable, Numarabil {
    private char[] eticheta;
    private TipContainer[] tipContainer;
    private int[] nrContainere;

    public PortContainer(char[] eticheta, TipContainer[] tipContainer, int[] nrContainere) {
        this.eticheta = eticheta;
        this.tipContainer = new TipContainer[tipContainer.length];
        for(int i = 0;i<tipContainer.length;i++)
            this.tipContainer[i] = tipContainer[i];
        this.nrContainere = new int[nrContainere.length];
        for(int i =0;i<nrContainere.length;i++)
        this.nrContainere[i] = nrContainere[i];
    }

    public PortContainer() {
        this.eticheta = null;
        this.tipContainer = null;
        this.nrContainere = null;
    }

    public char[] getEticheta() {
        return eticheta;
    }

    public void setEticheta(char[] eticheta) {
        this.eticheta = eticheta;
    }

    public TipContainer[] getTipContainer() {
        return tipContainer;
    }

    public void setTipContainer(TipContainer[] tipContainer) {
        this.tipContainer = new TipContainer[tipContainer.length];
        for(int i = 0;i<tipContainer.length;i++)
            this.tipContainer[i] = tipContainer[i];    }

    public int[] getNrContainere() {
        return nrContainere;
    }

    public void setNrContainere(int[] nrContainere) {
        this.nrContainere = new int[nrContainere.length];
        for(int i =0;i<nrContainere.length;i++)
            this.nrContainere[i] = nrContainere[i];    }
    public void setNrContainerePerTip(int nrContainerePerTip, TipContainer tipContainer){
        switch (tipContainer) {
            case Mic_10mc:
                this.nrContainere[0] = nrContainerePerTip;
                break;
            case Mediu_25mc:
                this.nrContainere[1] = nrContainerePerTip;
                break;
            case Mare_50mc:
                this.nrContainere[2] = nrContainerePerTip;
                break;
            case Jumbo_100mc:
                this.nrContainere[3] = nrContainerePerTip;
                break;
            default:
                throw new RuntimeException("Nu stiu sa setez nr de containere pentru PortContainerul " +
                        this.getEticheta().toString());

        }
    }
    public int getNrContainerePerTip( TipContainer tipContainer){
        int nrContainerePerTip = 0;
        switch (tipContainer) {
            case Mic_10mc:
                nrContainerePerTip = this.nrContainere[0];
                break;
            case Mediu_25mc:
                nrContainerePerTip = this.nrContainere[1];
                break;
            case Mare_50mc:
                nrContainerePerTip = this.nrContainere[2];
                break;
            case Jumbo_100mc:
                nrContainerePerTip = this.nrContainere[3];
                break;
            default:
                throw new RuntimeException("Nu stiu sa setez nr de containere pentru PortContainerul " +
                        this.getEticheta().toString());

        }
        return nrContainerePerTip;
    }
    @Override
    public String toString() {
        return
        this.getClass().getName() + "," +
                toStringFromCharArray(this.eticheta) + "," +
                toStringFromTipContainerArray(this.tipContainer) +
                toStringFromIntArray(this.nrContainere);
    }
    String toStringFromCharArray(char[] eticheta){
        StringBuilder sb = new StringBuilder();
        for(char c : eticheta)
            sb.append(c);
        return sb.toString();
    }
    String toStringFromTipContainerArray(TipContainer[] tipContainer){
        StringBuilder sb = new StringBuilder();
        for(TipContainer tip : tipContainer){
            sb.append(tip);
            sb.append(",");
        }
        return sb.toString();
    }
    String toStringFromIntArray(int[] nrContainere) {
        StringBuilder sb = new StringBuilder();
        for (int nr : nrContainere) {
            sb.append(nr);
            sb.append(",");
        }
        return sb.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        //return super.clone();
        return new PortContainer(this.eticheta,this.tipContainer,this.nrContainere);
    }


    @Override
    public int getCapacitate() {
     //presupunem ca ordinea containerelor este cea din masivul tipContainer [Mic_10mc,Mediu_25mc,Mare_50mc,Jumbo_100mc];
        return this.nrContainere[0]* 10 + this.nrContainere[1] * 25 + this.nrContainere[2] * 50 + this.nrContainere[3] * 100;
    }
    public Object dinString(String linie, String separator) {
        Scanner lineScanner = new Scanner(linie);
        lineScanner.useDelimiter(separator);

        String numeClasa = lineScanner.next();
        System.out.println("Clasa citita " + numeClasa);

        PortContainer local = new PortContainer();
        local.setEticheta(lineScanner.next().toCharArray());
        TipContainer[] tipContainer = new TipContainer[4];
        for(int i = 0;i<tipContainer.length;i++)
            tipContainer[i] = TipContainer.valueOf(lineScanner.next());
        local.setTipContainer(tipContainer);
        int[] nrContainere = new int[4];
        for(int i = 0;i< nrContainere.length;i++)
            nrContainere[i] = lineScanner.nextInt();
        local.setNrContainere(nrContainere);
        return local;
    }
}
