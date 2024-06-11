/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Asus
 */
public class DiaChi {
    private String tinhTP;
    private String quanHuyen;
    private String xaPhuongTT;

    public DiaChi(String tinhTP, String quanHuyen, String xaPhuongTT) {
        this.tinhTP = tinhTP;
        this.quanHuyen = quanHuyen;
        this.xaPhuongTT = xaPhuongTT;
    }

    public String getTinhTP() {
        return tinhTP;
    }

    public void setTinhTP(String tinhTP) {
        this.tinhTP = tinhTP;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getXaPhuongTT() {
        return xaPhuongTT;
    }

    public void setXaPhuongTT(String xaPhuongTT) {
        this.xaPhuongTT = xaPhuongTT;
    }

    @Override
    public String toString() {
        return "DiaChi{" + "tinhTP=" + tinhTP + ", quanHuyen=" + quanHuyen + ", xaPhuongTT=" + xaPhuongTT + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.tinhTP);
        hash = 59 * hash + Objects.hashCode(this.quanHuyen);
        hash = 59 * hash + Objects.hashCode(this.xaPhuongTT);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiaChi other = (DiaChi) obj;
        if (!Objects.equals(this.tinhTP, other.tinhTP)) {
            return false;
        }
        if (!Objects.equals(this.quanHuyen, other.quanHuyen)) {
            return false;
        }
        return Objects.equals(this.xaPhuongTT, other.xaPhuongTT);
    }   

}
