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
public class BangDiemDanhNV {
    private String maCCNV;
    private int coMat;
    private double phat;
    private String ghiChu;
    private NhanVien nhanVien;
    private NgayLamViec ngayLamViec;

    public BangDiemDanhNV(String maCCNV) {
        this.maCCNV = maCCNV;
    }

    public BangDiemDanhNV(String maCCNV, int coMat, double phat, String ghiChu, NhanVien nhanVien, NgayLamViec ngayLamViec) {
        this.maCCNV = maCCNV;
        this.coMat = coMat;
        this.phat = phat;
        this.ghiChu = ghiChu;
        this.nhanVien = nhanVien;
        this.ngayLamViec = ngayLamViec;
    }

    public String getMaCCNV() {
        return maCCNV;
    }

    public void setMaCCNV(String maCCNV) {
        this.maCCNV = maCCNV;
    }

    public int getCoMat() {
        return coMat;
    }

    public void setCoMat(int coMat) {
        this.coMat = coMat;
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

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public NgayLamViec getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(NgayLamViec ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    @Override
    public String toString() {
        return "BangChamCongNV{" + "maCCNV=" + maCCNV + ", coMat=" + coMat + ", phat=" + phat + ", ghiChu=" + ghiChu + ", nhanVien=" + nhanVien + ", ngayLamViec=" + ngayLamViec + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.maCCNV);
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
        final BangDiemDanhNV other = (BangDiemDanhNV) obj;
        return Objects.equals(this.maCCNV, other.maCCNV);
    }
    
    
}
