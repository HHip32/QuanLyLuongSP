/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import entity.NhanVien;
import connectDB.ConnectDB;
import entity.DiaChi;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class NhanVien_DAO {

//    private ConnectDB con = new ConnectDB();
//    public NhanVien_DAO() {
//        try {
//            con.getInstance().connect();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from NhanVien");
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan,
                        lienHe, rs.getString("soDT"), rs.getString("cMND"), rs.getString("matKhau"), ngaySinh,
                        rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"),
                        rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"),
                        rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public ArrayList<NhanVien> getAllNhanVienConLam() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from NhanVien "
                    + "where trangThai = 1");
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan,
                        lienHe, rs.getString("soDT"), rs.getString("cMND"), rs.getString("matKhau"), ngaySinh,
                        rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"),
                        rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"),
                        rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public ArrayList<NhanVien> getAllNhanVienNghiLam() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from NhanVien"
                    + " where trangThai = 0");
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan,
                        lienHe, rs.getString("soDT"), rs.getString("cMND"), rs.getString("matKhau"), ngaySinh,
                        rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"),
                        rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"),
                        rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public String getMaNVCuoi() {
        Connection con = null;
        String maNhanVien = "";
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select maNV from NhanVien");
            while (rs.next()) {
                maNhanVien = rs.getString("maNV");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNhanVien;
    }

    public boolean themNhanVien(NhanVien nv) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("insert into NhanVien(maNV,hoTen,gioiTinh,tinhTP_QQ,quanHuyen_QQ,xaPhTT_QQ,tinhTP_DCLH,quanHuyen_DCLH,xaPhTT_DCLH,soDT,cMND,matKhau,ngaySinh,hinhAnh,email,ngayVaoLam,bHXH,luongCB,hsLuong,trinhDo,chucVu,phuCap,thamNien,trangThai)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
            stm.setString(1, nv.getMaNV());
            stm.setString(2, nv.getTenNV());
            stm.setBoolean(3, nv.isGioiTinh());
            stm.setString(4, nv.getQueQuan().getTinhTP());
            stm.setString(5, nv.getQueQuan().getQuanHuyen());
            stm.setString(6, nv.getQueQuan().getXaPhuongTT());
            stm.setString(7, nv.getDiaChiLienHe().getTinhTP());
            stm.setString(8, nv.getDiaChiLienHe().getQuanHuyen());
            stm.setString(9, nv.getDiaChiLienHe().getXaPhuongTT());
            stm.setString(10, nv.getSoDT());
            stm.setString(11, nv.getcMND());
            stm.setString(12, nv.getMatKhau());
            stm.setDate(13, Date.valueOf(nv.getNgaySinh()));
            stm.setBytes(14, nv.getHinhAnh());
            stm.setString(15, nv.getEmail());
            stm.setDate(16, Date.valueOf(nv.getNgayVaoLam()));
            stm.setBoolean(17, nv.isbHXH());
            stm.setDouble(18, nv.getLuongCB());
            stm.setFloat(19, nv.getHsLuong());
            stm.setInt(20, nv.getTrinhDo());
            stm.setBoolean(21, nv.isChucVu());
            stm.setDouble(22, nv.getPhuCap());
            stm.setInt(23, nv.getLuongThamNien());
            stm.setBoolean(24, nv.isTrangThai());
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean xoaNhanVien(String ma) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("update NhanVien"
                    + " set trangThai = 0"
                    + " where maNV = ?");
            stm.setString(1, ma);
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean suaNhanVien(NhanVien nv) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("update NhanVien"
                    + " set hoTen = ?,gioiTinh = ?,tinhTP_QQ = ?,quanHuyen_QQ = ?,xaPhTT_QQ = ?,tinhTP_DCLH = ?,quanHuyen_DCLH = ?,xaPhTT_DCLH = ?,soDT = ?,cMND = ?,matKhau = ?,ngaySinh = ?,hinhAnh = ?,email = ?,ngayVaoLam = ?,bHXH = ?,luongCB = ?,hsLuong = ?,trinhDo = ?,chucVu = ?,phuCap = ?, ThamNien = ?"
                    + " where maNV = ?");

            stm.setString(1, nv.getTenNV());
            stm.setBoolean(2, nv.isGioiTinh());
            stm.setString(3, nv.getQueQuan().getTinhTP());
            stm.setString(4, nv.getQueQuan().getQuanHuyen());
            stm.setString(5, nv.getQueQuan().getXaPhuongTT());
            stm.setString(6, nv.getDiaChiLienHe().getTinhTP());
            stm.setString(7, nv.getDiaChiLienHe().getQuanHuyen());
            stm.setString(8, nv.getDiaChiLienHe().getXaPhuongTT());
            stm.setString(9, nv.getSoDT());
            stm.setString(10, nv.getcMND());
            stm.setString(11, nv.getMatKhau());
            stm.setDate(12, Date.valueOf(nv.getNgaySinh()));
            stm.setBytes(13, nv.getHinhAnh());
            stm.setString(14, nv.getEmail());
            stm.setDate(15, Date.valueOf(nv.getNgayVaoLam()));
            stm.setBoolean(16, nv.isbHXH());
            stm.setDouble(17, nv.getLuongCB());
            stm.setFloat(18, nv.getHsLuong());
            stm.setInt(19, nv.getTrinhDo());
            stm.setBoolean(20, nv.isChucVu());
            stm.setDouble(21, nv.getPhuCap());
            stm.setInt(22, nv.getLuongThamNien());
            stm.setString(23, nv.getMaNV());
            int res = stm.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<NhanVien> timNhanVienTheoMa(String ma) {
        Connection con = null;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from NhanVien"
                    + " where maNV like N'%" + ma + "%'");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV").trim(), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT").trim(), rs.getString("cMND").trim(), rs.getString("matKhau").trim(), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email").trim(), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public NhanVien tim1NhanVienTheoMa(String ma) {
        Connection con = null;
        NhanVien nhanVien = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from NhanVien"
                    + " where maNV = ?");
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                nhanVien = new NhanVien(rs.getString("maNV").trim(), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT").trim(), rs.getString("cMND").trim(), rs.getString("matKhau").trim(), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email").trim(), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getBoolean("trangThai"));
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }

    public ArrayList<NhanVien> timNhanVienTheoTen(String ten) {
        Connection con = null;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from NhanVien"
                    + " where hoTen like N'%" + ten + "%'");
//            stm.setString(1, ten);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV").trim(), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT").trim(), rs.getString("cMND").trim(), rs.getString("matKhau").trim(), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email").trim(), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public NhanVien getNhanVienCoTK(String maNV) {
        Connection con = null;
        NhanVien nv = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from NhanVien" + " where maNV = ? and matKhau != '' and trangThai = 1");
            stm.setString(1, maNV);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getString("matKhau").trim());
                nv.setChucVu(rs.getBoolean("chucVu"));
            }

            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }

    public NhanVien getNhanVienCoTKMK(String maNV, String email, String sdt) {
        Connection con = null;
        NhanVien nv = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from NhanVien" + " where maNV = ? and email = ? and soDT = ? and matKhau != ''");
            stm.setString(1, maNV);
            stm.setString(2, email);
            stm.setString(3, sdt);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString("maNV"), rs.getString("email"), rs.getString("soDT"), rs.getString("matKhau"));
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }

    public boolean capNhatTaiKhoan(String maNV, String matKhau) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("update NhanVien" + " set matKhau = ? where maNV = ?");

            stm.setString(1, matKhau);
            stm.setString(2, maNV);

            int res = stm.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<NhanVien> getAllNhanVienChuaCoTK() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from NhanVien" + " where matKhau = '' and trangThai = 1");
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), rs.getString("matKhau"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public ArrayList<NhanVien> timNhanVienKhongCoTKTheoMa(String ma) {
        Connection con = null;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from NhanVien"
                    + " where maNV like N'%" + ma + "%' and matKhau = '' ");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV").trim(), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT").trim(), rs.getString("cMND").trim(), rs.getString("matKhau").trim(), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email").trim(), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public ArrayList<NhanVien> timNhanVienCoTKTheoMa(String ma) {
        Connection con = null;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from NhanVien"
                    + " where maNV like N'%" + ma + "%' and matKhau !='' ");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV").trim(), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT").trim(), rs.getString("cMND").trim(), rs.getString("matKhau").trim(), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email").trim(), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public ArrayList<NhanVien> getAllNhanVienCoTK() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from NhanVien"
                    + " where matKhau != '' and trangThai = 1");
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), rs.getString("matKhau"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public boolean themTKChoNV(String maNV, String matKhau) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("update NhanVien"
                    + " set matKhau = ?"
                    + " where maNV = ?");
            stm.setString(1, matKhau);
            stm.setString(2, maNV);
            int res = stm.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaTKCuaNV(String maNV) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("update NhanVien"
                    + " set matKhau = '' "
                    + " where maNV = ?");
            stm.setString(1, maNV);
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean capNhatTKCuaNV(String maNV, String mk) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("update NhanVien"
                    + " set matKhau = ? "
                    + " where maNV = ?");
            stm.setString(1, mk);
            stm.setString(2, maNV);
            int res = stm.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<NhanVien> timTKTheoMa(String ma) {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("select * from NhanVien"
                    + " where maNV like N'%" + ma + "%' and matKhau != ''");
//            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), rs.getString("matKhau"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getBoolean("trangThai"));
                dsNhanVien.add(nhanVien);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

}
