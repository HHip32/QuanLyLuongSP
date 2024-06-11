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
public class CongDoan {

    private String maCD;
    private String tenCD;
    private double gia;
    private int soLuong;
    private int thuTu;
    private boolean trangThai;
    private SanPham sanPham;

    public CongDoan(String maCD) {
        this.maCD = maCD;
    }

    public CongDoan(String maCD, String tenCD, double gia, int soLuong, int thuTu, boolean trangThai, SanPham sanPham) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.gia = gia;
        this.soLuong = soLuong;
        this.thuTu = thuTu;
        this.trangThai = trangThai;
        this.sanPham = sanPham;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThuTu() {
        return thuTu;
    }

    public void setThuTu(int thuTu) {
        this.thuTu = thuTu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public String toString() {
        return "CongDoan{" + "maCD=" + maCD + ", tenCD=" + tenCD + ", gia=" + gia + ", soLuong=" + soLuong + ", thuTu=" + thuTu + ", trangThai=" + trangThai + ", sanPham=" + sanPham + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.maCD);
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
        final CongDoan other = (CongDoan) obj;
        return Objects.equals(this.maCD, other.maCD);
    }

}
