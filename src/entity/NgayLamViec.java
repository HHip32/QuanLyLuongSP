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
public class NgayLamViec {
    private LocalDate ngayLam;
    private boolean loaiNgay;

    public NgayLamViec(LocalDate ngayLam) {
        this.ngayLam = ngayLam;
    }
    
    public NgayLamViec(LocalDate ngayLam, boolean loaiNgay) {
        this.ngayLam = ngayLam;
        this.loaiNgay = loaiNgay;
    }

    public LocalDate getNgayLam() {
        return ngayLam;
    }

    public void setNgayLam(LocalDate ngayLam) {
        this.ngayLam = ngayLam;
    }

    public boolean isLoaiNgay() {
        return loaiNgay;
    }

    public void setLoaiNgay(boolean loaiNgay) {
        this.loaiNgay = loaiNgay;
    }

    @Override
    public String toString() {
        return "NgayLamViec{" + "ngayLam=" + ngayLam + ", loaiNgay=" + loaiNgay + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.ngayLam);
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
        final NgayLamViec other = (NgayLamViec) obj;
        return Objects.equals(this.ngayLam, other.ngayLam);
    }

}
