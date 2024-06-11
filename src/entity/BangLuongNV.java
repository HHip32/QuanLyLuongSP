/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class BangLuongNV {

    private String maLNV;
    private int thang;
    private int nam;
    private double luongCB;
    private float hsLuong;
    private int soNgayCong;
    private int soGioTangCa;
    private int soNgayNghiKoPhep;
    private double thuong;
    private double phuCap;
    private int thamNien;
    private double phat;
    private boolean bHXH;
    private double tongLuong;
    private NhanVien nhanVien;

    public BangLuongNV(String maLNV) {
        this.maLNV = maLNV;
    }
    
    public BangLuongNV(String maLNV, int thang, int nam, double luongCB, float hsLuong, int soNgayCong, int soGioTangCa, int soNgayNghiKoPhep, double thuong, double phuCap, int thamNien, double phat, boolean bHXH, NhanVien nhanVien) {
        this.maLNV = maLNV;
        this.thang = thang;
        this.nam = nam;
        this.luongCB = luongCB;
        this.hsLuong = hsLuong;
        this.soNgayCong = soNgayCong;
        this.soGioTangCa = soGioTangCa;
        this.soNgayNghiKoPhep = soNgayNghiKoPhep;
        this.phuCap = phuCap;
        setPhat(phat);
        this.thamNien = thamNien;
        this.bHXH = bHXH;
        this.nhanVien = nhanVien;
        setThuong();
        setTongLuong();
    }

    public String getMaLNV() {
        return maLNV;
    }

    public void setMaLNV(String maLNV) {
        this.maLNV = maLNV;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public double getPhat() {
        return phat;
    }

    public double getTongPhat() {
        return phat + soNgayNghiKoPhep * 200000;
    }

    public void setPhat(double phat) {
        this.phat = phat;
        setTongLuong();
    }

    public double getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(double luongCB) {
        this.luongCB = luongCB;
        setTongLuong();
    }

    public float getHsLuong() {
        return hsLuong;
    }

    public void setHsLuong(float hsLuong) {
        this.hsLuong = hsLuong;
        setTongLuong();
    }

    public int getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(int soNgayCong) {
        this.soNgayCong = soNgayCong;
        setThuong();
        setTongLuong();
    }

    public int getSoGioTangCa() {
        return soGioTangCa;
    }

    public void setSoGioTangCa(int soGioTangCa) {
        this.soGioTangCa = soGioTangCa;
        setTongLuong();
    }

    public int getSoNgayNghiKoPhep() {
        return soNgayNghiKoPhep;
    }

    public void setSoNgayNghiKoPhep(int soNgayNghiKoPhep) {
        this.soNgayNghiKoPhep = soNgayNghiKoPhep;
        setThuong();
        setTongLuong();
    }

    public double getThuong() {
        return thuong;
    }

    public void setThuong() {
        if (soNgayCong >= soNgayLamViecTrongThang(thang, nam)) {
            this.thuong = 500000;
        }
        setTongLuong();
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
        setTongLuong();
    }

    public int getThamNien() {
        return thamNien;
    }

    public double getLuongThamNien() {
        return (double) thamNien / 100 * (luongCB * hsLuong);
    }

    public void setThamNien(int thamNien) {
        this.thamNien = thamNien;
        setTongLuong();
    }

    public boolean isbHXH() {
        return bHXH;

    }

    public double getBHXH() {
        return bHXH ? luongCB * 0.08 : 0;
    }

    public void setbHXH(boolean bHXH) {
        this.bHXH = bHXH;
        setTongLuong();
    }

    public double getTongLuong() {
        return tongLuong;
    }

    public double getLuongNgayCong() {
        return luongCB * hsLuong / 26 * soNgayCong;

    }

    public void setTongLuong() {
        double luong1Ngay = luongCB * hsLuong / 26;
        double giaBHXH = bHXH ? luongCB * 0.08 : 0;
        this.tongLuong = luong1Ngay * soNgayCong + soGioTangCa * (luong1Ngay / 8) * 2 + thuong + phuCap + (double) thamNien / 100 * (luongCB * hsLuong) - giaBHXH - getTongPhat();
        if (this.tongLuong < 0) {
            this.tongLuong = 0;
        }
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public String toString() {
        return "BangLuongNV{" + "maLNV=" + maLNV + ", thang=" + thang + ", nam=" + nam + ", luongCB=" + luongCB + ", hsLuong=" + hsLuong + ", soNgayCong=" + soNgayCong + ", soGioTangCa=" + soGioTangCa + ", soNgayNghiKoPhep=" + soNgayNghiKoPhep + ", thuong=" + thuong + ", phuCap=" + phuCap + ", thamNien=" + thamNien + ", bHXH=" + bHXH + ", tongLuong=" + tongLuong + ", nhanVien=" + nhanVien + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.maLNV);
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
        final BangLuongNV other = (BangLuongNV) obj;
        return Objects.equals(this.maLNV, other.maLNV);
    }

    public int soNgayLamViecTrongThang(int thang, int nam) {
        YearMonth yearMonthObject = YearMonth.of(nam, thang);
        int soNgayTrongThang = yearMonthObject.lengthOfMonth();
        int soNgayChuNhat = 0;
        LocalDate ngay = LocalDate.of(nam, thang, 1);
        for (int i = 1; i <= soNgayTrongThang; i++) {
            ngay = LocalDate.of(nam, thang, i);
            DayOfWeek ngayTrongTuan = ngay.getDayOfWeek();
            if (ngayTrongTuan.getValue() == DayOfWeek.SUNDAY.getValue()) {
                soNgayChuNhat++;
            }
        }
        return soNgayTrongThang - soNgayChuNhat;
    }

}
