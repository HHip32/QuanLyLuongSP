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
public class BangPhanCong {
    private String maPC;
    private LocalDate ngayPC;
    private CongNhan congNhan;
    private CongDoan congDoan;

    public BangPhanCong(String maPC) {
        this.maPC = maPC;
    }

    public BangPhanCong(String maPC, LocalDate ngayPC, CongNhan congNhan, CongDoan congDoan) {
        this.maPC = maPC;
        this.ngayPC = ngayPC;
        this.congNhan = congNhan;
        this.congDoan = congDoan;
    }

    public String getMaPC() {
        return maPC;
    }

    public void setMaPC(String maPC) {
        this.maPC = maPC;
    }

    public LocalDate getNgayPC() {
        return ngayPC;
    }

    public void setNgayPC(LocalDate ngayPC) {
        this.ngayPC = ngayPC;
    }

    public CongNhan getCongNhan() {
        return congNhan;
    }

    public void setCongNhan(CongNhan congNhan) {
        this.congNhan = congNhan;
    }

    public CongDoan getCongDoan() {
        return congDoan;
    }

    public void setCongDoan(CongDoan congDoan) {
        this.congDoan = congDoan;
    }

    @Override
    public String toString() {
        return "BangPhanCong{" + "maPC=" + maPC + ", ngayPC=" + ngayPC + ", congNhan=" + congNhan + ", congDoan=" + congDoan + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.maPC);
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
        final BangPhanCong other = (BangPhanCong) obj;
        return Objects.equals(this.maPC, other.maPC);
    }
    
}
