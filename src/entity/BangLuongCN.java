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
public class BangLuongCN {

    private String maLCN;
    private int thang;
    private int nam;
    private int soNgayCong;
    private double luongSanPham;
//    private int soSPTangCa;
    private double luongTangCa;
    private int soNgayNghiKoPhep;
    private double thuong;
    private double phuCap;
    private int thamNien;
    private double phat;
    private boolean bHXH;
    private double tongLuong;
    private CongNhan congNhan;

    public BangLuongCN(String maLCN) {
        this.maLCN = maLCN;
    }

    public BangLuongCN(String maLCN, int thang, int nam, int soNgayCong, double luongSanPham, double luongTangCa, int soNgayNghiKoPhep, double phuCap, int thamNien, double phat, boolean bHXH, CongNhan congNhan) {
        this.maLCN = maLCN;
        this.thang = thang;
        this.nam = nam;
        this.soNgayCong = soNgayCong;
        this.luongSanPham = luongSanPham;
        this.luongTangCa = luongTangCa;
        this.soNgayNghiKoPhep = soNgayNghiKoPhep;
        this.phuCap = phuCap;
        this.thamNien = thamNien;
        setPhat(phat);
        this.bHXH = bHXH;
        this.congNhan = congNhan;
        setThuong();
        setTongLuong();
    }

    public String getMaLCN() {
        return maLCN;
    }

    public void setMaLCN(String maLCN) {
        this.maLCN = maLCN;
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

    public double getLuongSanPham() {
        return luongSanPham;
    }

    public void setLuongSanPham(double luongSanPham) {
        this.luongSanPham = luongSanPham;
        setTongLuong();
    }

    public void setLuongSanPham(int soLuong, double gia, NgayLamViec ngayLV) {
        if (ngayLV.isLoaiNgay()) {
            luongSanPham += soLuong * gia * 3;
        } else {
            if (ktraNgayCN(ngayLV.getNgayLam())) {
                luongSanPham += soLuong * gia * 2;
            } else {
                luongSanPham += soLuong * gia;
            }
        }
        setTongLuong();
    }

    public double getLuongTangCa() {
        return luongTangCa;
    }

    public void setLuongTangCa(double luongTangCa) {
        this.luongTangCa = luongTangCa;
        setTongLuong();
    }

    public void setLuongTangCa(int soLuong, double gia, NgayLamViec ngayLV) {
        if (ngayLV.isLoaiNgay()) {
            luongTangCa += soLuong * gia * 4;
        } else {
            if (ktraNgayCN(ngayLV.getNgayLam())) {
                luongTangCa += soLuong * gia * 3;
            } else {
                luongTangCa += soLuong * gia * 2;
            }
        }
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

    public void setSoNgayLam(int coMAt, NgayLamViec ngayLV) {
        if (coMAt == 2) {
            if (ngayLV.isLoaiNgay()) {
                soNgayCong += 3;
            } else {
                if (ktraNgayCN(ngayLV.getNgayLam())) {
                    soNgayCong += 2;
                } else {
                    soNgayCong++;
                }
            }
        }
        if (coMAt == 0) {
            soNgayNghiKoPhep++;
        }
        setTongLuong();

    }

    public int getSoNgayNghiKoPhep() {
        return soNgayNghiKoPhep;
    }

    public void setSoNgayNghiKoPhep(int soNgayNghiKoPhep) {
        this.soNgayNghiKoPhep = soNgayNghiKoPhep;
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
        return (double) thamNien / 100 * luongSanPham;
    }

    public void setThamNien(int thamNien) {
        this.thamNien = thamNien;
        setTongLuong();
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

    public void setThemPhat(double phat) {
        this.phat += phat;
        setTongLuong();
    }

    public boolean isbHXH() {
        return bHXH;
    }

    public void setbHXH(boolean bHXH) {
        this.bHXH = bHXH;
        setTongLuong();
    }

    public double getBHXH() {
        return bHXH ? 0.08 * 3500000 : 0;
    }

    public double getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong() {
        double giaBHXH = getBHXH();
        this.tongLuong = luongSanPham + luongTangCa + thuong + phuCap + (double) thamNien / 100 * luongSanPham - giaBHXH - getTongPhat();
        if (this.tongLuong < 0) {
            this.tongLuong = 0;
        }
    }

    public CongNhan getCongNhan() {
        return congNhan;
    }

    public void setCongNhan(CongNhan congNhan) {
        this.congNhan = congNhan;
    }

    @Override
    public String toString() {
        return "BangLuongCN{" + "maLCN=" + maLCN + ", thang=" + thang + ", nam=" + nam + ", luongSanPham=" + luongSanPham + ", luongTangCa=" + luongTangCa + ", soNgayNghiKoPhep=" + soNgayNghiKoPhep + ", thuong=" + thuong + ", phuCap=" + phuCap + ", thamNien=" + thamNien + ", phat=" + phat + ", bHXH=" + bHXH + ", tongLuong=" + tongLuong + ", congNhan=" + congNhan + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.maLCN);
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
        final BangLuongCN other = (BangLuongCN) obj;
        return Objects.equals(this.maLCN, other.maLCN);
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

    public boolean ktraNgayCN(LocalDate ngay) {
        if (ngay.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue()) {
            return true;
        }
        return false;
    }

}
