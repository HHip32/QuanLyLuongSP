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
public class NhanVien {

    private String maNV;
    private String tenNV;
    private boolean gioiTinh;
    private DiaChi queQuan;
    private DiaChi diaChiLienHe;
    private String soDT;
    private String cMND;
    private String matKhau;
    private LocalDate ngaySinh;
    private byte[] hinhAnh;
    private String email;
    private LocalDate ngayVaoLam;
    private boolean bHXH;
    private double luongCB;
    private float hsLuong;
    private int trinhDo;
    private boolean chucVu;
    private double phuCap;
    private int luongThamNien;
    private boolean trangThai;

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV, String tenNV, boolean gioiTinh, DiaChi queQuan, DiaChi diaChiLienHe, String soDT, String cMND, String matKhau, LocalDate ngaySinh, byte[] hinhAnh, String email, LocalDate ngayVaoLam, boolean bHXH, double luongCB, float hsLuong, int trinhDo, boolean chucVu, double phuCap, boolean trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.diaChiLienHe = diaChiLienHe;
        this.soDT = soDT;
        this.cMND = cMND;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.hinhAnh = hinhAnh;
        this.email = email;
        this.ngayVaoLam = ngayVaoLam;
        this.bHXH = bHXH;
        this.luongCB = luongCB;
        this.trinhDo = trinhDo;
        this.chucVu = chucVu;
        this.phuCap = phuCap;
        setHsLuong();
        int res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        if (res < 5) {
            res = 0;
        } else if (res <= 20) {
            res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        } else {
            res = 20;
        }
        this.luongThamNien = res;
        this.trangThai = trangThai;
    }

    public NhanVien(String maNV, String tenNV, boolean gioiTinh, DiaChi queQuan, DiaChi diaChiLienHe, String soDT, String cMND, String matKhau, LocalDate ngaySinh, byte[] hinhAnh, String email, LocalDate ngayVaoLam, boolean bHXH, double luongCB, float hsLuong, int trinhDo, boolean chucVu, double phuCap) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.diaChiLienHe = diaChiLienHe;
        this.soDT = soDT;
        this.cMND = cMND;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.hinhAnh = hinhAnh;
        this.email = email;
        this.ngayVaoLam = ngayVaoLam;
        this.bHXH = bHXH;
        this.luongCB = luongCB;
        this.trinhDo = trinhDo;
        this.chucVu = chucVu;
        this.phuCap = phuCap;
        setHsLuong();
        int res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        if (res < 5) {
            res = 0;
        } else if (res <= 20) {
            res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        } else {
            res = 20;
        }
        this.luongThamNien = res;
    }
//LocalDate ngayVaoLam = dateChooserNgayVaoLam.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    public NhanVien(String maNV, String tenNV, boolean gioiTinh, DiaChi queQuan, DiaChi diaChiLienHe, String soDT, String cMND, String matKhau,
            LocalDate ngaySinh, byte[] hinhAnh, String email, LocalDate ngayVaoLam, boolean bHXH, double luongCB, float hsLuong, int trinhDo, boolean chucVu, double phuCap,
            int luongThamNien, boolean trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.diaChiLienHe = diaChiLienHe;
        this.soDT = soDT;
        this.cMND = cMND;
        this.matKhau = matKhau;
        this.ngaySinh = ngaySinh;
        this.hinhAnh = hinhAnh;
        this.email = email;
        this.ngayVaoLam = ngayVaoLam;
        this.bHXH = bHXH;
        this.luongCB = luongCB;
        this.trinhDo = trinhDo;
        this.chucVu = chucVu;
        this.phuCap = phuCap;
        setHsLuong();
        int res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        if (res < 5) {
            res = 0;
        } else if (res <= 20) {
            res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        } else {
            res = 20;
        }
        this.luongThamNien = res;
    }

    public NhanVien(String maNV, String tenNV, String matKhau) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.matKhau = matKhau;
    }

    public NhanVien(String maNV, String email, String soDT, String matKhau) {
        this.maNV = maNV;
        this.email = email;
        this.soDT = soDT;
        this.matKhau = matKhau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
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

    public double getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(double luongCB) {
        this.luongCB = luongCB;
    }

    public float getHsLuong() {
        return hsLuong;
    }

    public void setHsLuong() {
        int res = Period.between(ngayVaoLam, LocalDate.now()).getYears();
        int hsLuong_tam = trinhDo + res / 3;
        if (hsLuong_tam > 6) {
           hsLuong_tam = 6;
        } 
        switch (hsLuong_tam) {
            case 1:
                this.hsLuong = (float) 1.86;
                break;
            case 2:
                this.hsLuong = (float) 2.08;
                break;
            case 3:
                this.hsLuong = (float) 2.34;
                break;
            case 4:
                this.hsLuong = (float) 2.62;
                break;
            case 5:
                this.hsLuong = (float) 2.94;
                break;
            default:
                 this.hsLuong = (float) 3.26;
                break;
        }
    }

    public int getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(int trinhDo) {
        this.trinhDo = trinhDo;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public int getLuongThamNien() {
        return luongThamNien;
    }

    public void setLuongThamNien(int luongThamNien) {
        this.luongThamNien = luongThamNien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", tenNV=" + tenNV + ", gioiTinh=" + gioiTinh + ", queQuan=" + queQuan + ", diaChiLienHe=" + diaChiLienHe + ", soDT=" + soDT + ", cMND=" + cMND + ", matKhau=" + matKhau + ", ngaySinh=" + ngaySinh + ", hinhAnh=" + hinhAnh + ", email=" + email + ", ngayVaoLam=" + ngayVaoLam + ", bHXH=" + bHXH + ", luongCB=" + luongCB + ", hsLuong=" + hsLuong + ", trinhDo=" + trinhDo + ", chucVu=" + chucVu + ", phuCap=" + phuCap + ", luongThamNien=" + luongThamNien + ", trangThai=" + trangThai + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.maNV);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNV, other.maNV);
    }

}
