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
public class BangDiemDanhCN {
    private String maCCCN;
    private int coMAt;
    private double phat;
    private String ghiCHu;
    private BangPhanCong bangPhanCong;
    private NgayLamViec ngayLamViec;

    public BangDiemDanhCN(String maCCCN) {
        this.maCCCN = maCCCN;
    }

    public BangDiemDanhCN(String maCCCN, int coMAt, double phat, String ghiCHu, BangPhanCong bangPhanCong, NgayLamViec ngayLamViec) {
        this.maCCCN = maCCCN;
        this.coMAt = coMAt;
        this.phat = phat;
        this.ghiCHu = ghiCHu;
        this.bangPhanCong = bangPhanCong;
        this.ngayLamViec = ngayLamViec;
    }

    public String getMaCCCN() {
        return maCCCN;
    }

    public void setMaCCCN(String maCCCN) {
        this.maCCCN = maCCCN;
    }

    public int getCoMAt() {
        return coMAt;
    }

    public void setCoMAt(int coMAt) {
        this.coMAt = coMAt;
    }


    public double getPhat() {
        return phat;
    }

    public void setPhat(double phat) {
        this.phat = phat;
    }

    public String getGhiCHu() {
        return ghiCHu;
    }

    public void setGhiCHu(String ghiCHu) {
        this.ghiCHu = ghiCHu;
    }

    public BangPhanCong getBangPhanCong() {
        return bangPhanCong;
    }

    public void setBangPhanCong(BangPhanCong bangPhanCong) {
        this.bangPhanCong = bangPhanCong;
    }

    public NgayLamViec getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(NgayLamViec ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    @Override
    public String toString() {
        return "BagnChamCongCN{" + "maCCCN=" + maCCCN + ", coMAt=" + coMAt + ", phat=" + phat + ", ghiCHu=" + ghiCHu + ", bangPhanCong=" + bangPhanCong + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.maCCCN);
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
        final BangDiemDanhCN other = (BangDiemDanhCN) obj;
        return Objects.equals(this.maCCCN, other.maCCCN);
    }

}
