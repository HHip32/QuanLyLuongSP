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
public class BangChamCongTangCa {

    private BangDiemDanhNV bangDiemDanhNV;
    private int soGioTangCa;
    private double phat;
    private String ghiChu;

    public BangChamCongTangCa(BangDiemDanhNV bangDiemDanhNV) {
        this.bangDiemDanhNV = bangDiemDanhNV;
    }

    public BangChamCongTangCa(BangDiemDanhNV bangDiemDanhNV, int soGioTangCa, double phat, String ghiChu) {
        this.bangDiemDanhNV = bangDiemDanhNV;
        this.soGioTangCa = soGioTangCa;
        this.phat = phat;
        this.ghiChu = ghiChu;
    }

    public BangDiemDanhNV getBangDiemDanhNV() {
        return bangDiemDanhNV;
    }

    public void setBangDiemDanhNV(BangDiemDanhNV bangDiemDanhNV) {
        this.bangDiemDanhNV = bangDiemDanhNV;
    }

    public int getSoGioTangCa() {
        return soGioTangCa;
    }

    public void setSoGioTangCa(int soGioTangCa) {
        this.soGioTangCa = soGioTangCa;
    }

    public double getPhat() {
        return phat;
    }

    public void setPhat(double phat) {
        this.phat = phat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "BangChamCongTangCa{" + "bangDiemDanhNV=" + bangDiemDanhNV + ", soGioTangCa=" + soGioTangCa + ", phat=" + phat + ", ghiChu=" + ghiChu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.bangDiemDanhNV);
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
        final BangChamCongTangCa other = (BangChamCongTangCa) obj;
        return Objects.equals(this.bangDiemDanhNV, other.bangDiemDanhNV);
    }

    

}
