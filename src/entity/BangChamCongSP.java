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
public class BangChamCongSP {
    private String maCCSP;
    private int caLam;
    private int soLuongSP;
    private double phat;
    private String ghiChu;
    private BangDiemDanhCN bangDiemDanhCN;

    public BangChamCongSP(String maCCSP) {
        this.maCCSP = maCCSP;
    }

    public BangChamCongSP(String maCCSP, int caLam, int soLuongSP, double phat, String ghiChu, BangDiemDanhCN bangDiemDanhCN) {
        this.maCCSP = maCCSP;
        this.caLam = caLam;
        this.soLuongSP = soLuongSP;
        this.phat = phat;
        this.ghiChu = ghiChu;
        this.bangDiemDanhCN = bangDiemDanhCN;
    }

    public String getMaCCSP() {
        return maCCSP;
    }

    public void setMaCCSP(String maCCSP) {
        this.maCCSP = maCCSP;
    }

    public int getCaLam() {
        return caLam;
    }

    public void setCaLam(int caLam) {
        this.caLam = caLam;
    }

    public BangDiemDanhCN getBangDiemDanhCN() {
        return bangDiemDanhCN;
    }

    public void setBangDiemDanhCN(BangDiemDanhCN bangDiemDanhCN) {
        this.bangDiemDanhCN = bangDiemDanhCN;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
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
        return "BangChamCongSP{" + "maCCSP=" + maCCSP + ", caLam=" + caLam + ", soLuongSP=" + soLuongSP + ", phat=" + phat + ", ghiChu=" + ghiChu + ", bangDiemDanhCN=" + bangDiemDanhCN + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.maCCSP);
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
        final BangChamCongSP other = (BangChamCongSP) obj;
        return Objects.equals(this.maCCSP, other.maCCSP);
    }

   

    
}
