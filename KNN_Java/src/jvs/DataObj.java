package jvs;

public class DataObj {
    private String location;
    private long ap1;
    private long ap2;
    private long ap3;

    public DataObj(String location, long ap1, long ap2, long ap3) {
        this.location = location;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.ap3 = ap3;
    }

    public String getLocation() {
        return location;
    }

    public long getAp1() {
        return ap1;
    }

    public long getAp2() {
        return ap2;
    }

    public long getAp3() {
        return ap3;
    }
}
