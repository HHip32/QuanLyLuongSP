/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class SanPham {

    private String maSP;
    private String tenSP;
    private LocalDate ngayBatDau;
    private LocalDate ngayHoanThanh;
    private int soLuong;
    private int soCongDoan;
    private boolean trangThai;

    public SanPham(String maSP) {
        this.maSP = maSP;
    }

    public SanPham(String maSP, String tenSP, LocalDate ngayBatDau, LocalDate ngayHoanThanh, int soLuong, int soCongDoan, boolean trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.ngayBatDau = ngayBatDau;
        this.ngayHoanThanh = ngayHoanThanh;
        this.soLuong = soLuong;
        this.soCongDoan = soCongDoan;
        this.trangThai = trangThai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(LocalDate ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoCongDoan() {
        return soCongDoan;
    }

    public void setSoCongDoan(int soCongDoan) {
        this.soCongDoan = soCongDoan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", ngayBatDau=" + ngayBatDau + ", ngayHoanThanh=" + ngayHoanThanh + ", soLuong=" + soLuong + ", soCongDoan=" + soCongDoan + ", trangThai=" + trangThai + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.maSP);
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
        final SanPham other = (SanPham) obj;
        return Objects.equals(this.maSP, other.maSP);
    }

}
