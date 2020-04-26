package sdu.mmmi.ubi2findlocation;

public class SignalVector {
    private String location;
    private int ap1;
    private int ap2;
    private int ap3;

    public SignalVector(String location, int ap1, int ap2, int ap3) {
        this.location = location;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.ap3 = ap3;
    }

    public String getLocation() {
        return location;
    }

    public int getAp1() {
        return ap1;
    }

    public int getAp2() {
        return ap2;
    }

    public int getAp3() {
        return ap3;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
