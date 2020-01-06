package net.hanhaa.dev.helpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.swing.text.Style;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceObject {

    private Integer i;
    private String j;
    private String p;

    public DeviceObject() {}

    public Integer getI() {
        return this.i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getJ() {
        return this.j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public String getP() {
        return this.p;
    }

    @Override
    public String toString() {
        return "DeviceObject{" +
                "i=" + i +
                ", j='" + j + '\'' +
                ", p='" + p + '\'' +
                '}';
    }
}
