/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class CongNhan {

    private String maCN;
    private String tenCN;
    private boolean gioiTinh;
    private DiaChi queQuan;
    private DiaChi diaChiLienHe;
    private String soDT;
    private String cMND;
    private LocalDate ngaySinh;
    private byte[] hinhAnh;
    private String email;
    private LocalDate ngayVaoLam;
    private boolean bHXH;
    private double phuCap;
    private int thamNien;
    private boolean trangThai;

    public CongNhan() {
    }

    public CongNhan(String maCN) {
        this.maCN = maCN;
    }

    public CongNhan(String maCN, String tenCN, boolean gioiTinh, DiaChi queQuan, DiaChi diaChiLienHe, String soDT, String cMND, LocalDate ngaySinh, byte[] hinhAnh, String email, LocalDate ngayVaoLam, boolean bHXH, double phuCap, int luongThamNien, boolean trangThai) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.diaChiLienHe = diaChiLienHe;
        this.soDT = soDT;
        this.cMND = cMND;
        this.ngaySinh = ngaySinh;
        this.hinhAnh = hinhAnh;
        this.email = email;
        this.ngayVaoLam = ngayVaoLam;
        this.bHXH = bHXH;
        this.phuCap = phuCap;
        int res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        if (res < 5) {
            res = 0;
        } else if (res <= 20) {
            res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        } else {
            res = 20;
        }
        this.thamNien = res;
        this.trangThai = trangThai;
    }

    public CongNhan(String maCN, String tenCN, boolean gioiTinh, DiaChi queQuan, DiaChi diaChiLienHe, String soDT, String cMND, LocalDate ngaySinh, byte[] hinhAnh, String email, LocalDate ngayVaoLam, boolean bHXH, double phuCap, boolean trangThai) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.diaChiLienHe = diaChiLienHe;
        this.soDT = soDT;
        this.cMND = cMND;
        this.ngaySinh = ngaySinh;
        this.hinhAnh = hinhAnh;
        this.email = email;
        this.ngayVaoLam = ngayVaoLam;
        this.bHXH = bHXH;
        this.phuCap = phuCap;
        int res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        if (res < 5) {
            res = 0;
        } else if (res <= 20) {
            res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        } else {
            res = 20;
        }
        this.thamNien = res;
        this.trangThai = trangThai;
    }

    public CongNhan(String maCN, String tenCN, boolean gioiTinh, DiaChi queQuan, DiaChi diaChiLienHe, String soDT, String cMND, LocalDate ngaySinh, byte[] hinhAnh, String email, LocalDate ngayVaoLam, boolean bHXH, double phuCap) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.diaChiLienHe = diaChiLienHe;
        this.soDT = soDT;
        this.cMND = cMND;
        this.ngaySinh = ngaySinh;
        this.hinhAnh = hinhAnh;
        this.email = email;
        this.ngayVaoLam = ngayVaoLam;
        this.bHXH = bHXH;
        this.phuCap = phuCap;
        int res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        if (res < 5) {
            res = 0;
        } else if (res <= 20) {
            res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        } else {
            res = 20;
        }
        this.thamNien = res;
    }

    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public String getTenCN() {
        return tenCN;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public DiaChi getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(DiaChi queQuan) {
        this.queQuan = queQuan;
    }

    public DiaChi getDiaChiLienHe() {
        return diaChiLienHe;
    }

    public void setDiaChiLienHe(DiaChi diaChiLienHe) {
        this.diaChiLienHe = diaChiLienHe;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getcMND() {
        return cMND;
    }

    public void setcMND(String cMND) {
        this.cMND = cMND;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public boolean isbHXH() {
        return bHXH;
    }

    public void setbHXH(boolean bHXH) {
        this.bHXH = bHXH;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public int getThamNien() {
        return thamNien;
    }

    public void setThamNien(LocalDate ngayVaoLam) {
        int res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        if (res < 5) {
            res = 0;
        } else if (res <= 20) {
            res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        } else {
            res = 20;
        }
        this.thamNien = res;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "CongNhan{" + "maCN=" + maCN + ", tenCN=" + tenCN + ", gioiTinh=" + gioiTinh + ", queQuan=" + queQuan + ", diaChiLienHe=" + diaChiLienHe + ", soDT=" + soDT + ", cMND=" + cMND + ", ngaySinh=" + ngaySinh + ", hinhAnh=" + hinhAnh + ", email=" + email + ", ngayVaoLam=" + ngayVaoLam + ", bHXH=" + bHXH + ", phuCap=" + phuCap + ", luongThamNien=" + thamNien + ", trangThai=" + trangThai + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.maCN);
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
        final CongNhan other = (CongNhan) obj;
        return Objects.equals(this.maCN, other.maCN);
    }

}
