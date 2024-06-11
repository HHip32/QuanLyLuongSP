/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import entity.CongNhan;
import connectDB.ConnectDB;
import entity.DiaChi;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author admin
 */
public class CongNhan_DAO {

    public ArrayList<CongNhan> getAllCongNhan() {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from CongNhan");
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getBoolean("gioiTinh")
                        , queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email")
                        , ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("phuCap")
                        , rs.getInt("thamNien"),rs.getBoolean("trangThai"));
                dsCongNhan.add(congNhan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongNhan;
    }
    public ArrayList<CongNhan> getAllCongNhanConLam(){
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from CongNhan" 
                    + " where trangThai = 1");
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getBoolean("gioiTinh")
                        , queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email")
                        , ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("phuCap")
                        , rs.getInt("thamNien"),rs.getBoolean("trangThai"));
                dsCongNhan.add(congNhan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongNhan;
    }
    public ArrayList<CongNhan> getAllCongNhanNghiLam(){
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        Connection con = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from CongNhan" 
                    +  " where trangThai = 0");
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getBoolean("gioiTinh")
                        , queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email")
                        , ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("phuCap")
                        , rs.getInt("thamNien"),rs.getBoolean("trangThai"));
                dsCongNhan.add(congNhan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongNhan;
    }
    public String getMaCNCuoi() {
        Connection con = null;
        String maCongNhan = "";
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select maCN from CongNhan");
            while (rs.next()) {
                maCongNhan = rs.getString("maCN");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maCongNhan;
    }

    public boolean themCongNhan(CongNhan cn) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("insert into CongNhan "
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setString(1, cn.getMaCN());
            stm.setString(2, cn.getTenCN());
            stm.setBoolean(3, cn.isGioiTinh());
            stm.setString(4, cn.getQueQuan().getTinhTP());
            stm.setString(5, cn.getQueQuan().getQuanHuyen());
            stm.setString(6, cn.getQueQuan().getXaPhuongTT());
            stm.setString(7, cn.getDiaChiLienHe().getTinhTP());
            stm.setString(8, cn.getDiaChiLienHe().getQuanHuyen());
            stm.setString(9, cn.getDiaChiLienHe().getXaPhuongTT());
            stm.setString(10, cn.getSoDT());
            stm.setString(11, cn.getcMND());
            stm.setDate(12, Date.valueOf(cn.getNgaySinh()));
            stm.setBytes(13, cn.getHinhAnh());
            stm.setString(14, cn.getEmail());
            stm.setDate(15, Date.valueOf(cn.getNgayVaoLam()));
            stm.setBoolean(16, cn.isbHXH());
            stm.setDouble(17, cn.getPhuCap());
            stm.setInt(18, cn.getThamNien());
            stm.setBoolean(19, cn.isTrangThai());
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean xoaCongNhan(String ma) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("update CongNhan" 
                    + " set trangThai = 0" 
                    + " where maCN = ?");
            stm.setString(1, ma);
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean suaCongNhan(CongNhan cn) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("update CongNhan"
                    + " set hoTen = ?,gioiTinh = ?,tinhTP_QQ = ?,quanHuyen_QQ = ?,xaPhTT_QQ = ?,tinhTP_DCLH = ?,quanHuyen_DCLH = ?,xaPhTT_DCLH = ?,soDT = ?,cMND = ?,ngaySinh = ?,hinhAnh = ?,email = ?,ngayVaoLam = ?,bHXH = ?,phuCap = ?, luongThamNien = ?"
                    + " where maCN = ?");

            stm.setString(1, cn.getTenCN());
            stm.setBoolean(2, cn.isGioiTinh());
            stm.setString(3, cn.getQueQuan().getTinhTP());
            stm.setString(4, cn.getQueQuan().getQuanHuyen());
            stm.setString(5, cn.getQueQuan().getXaPhuongTT());
            stm.setString(6, cn.getDiaChiLienHe().getTinhTP());
            stm.setString(7, cn.getDiaChiLienHe().getQuanHuyen());
            stm.setString(8, cn.getDiaChiLienHe().getXaPhuongTT());
            stm.setString(9, cn.getSoDT());
            stm.setString(10, cn.getcMND());
            stm.setDate(11, Date.valueOf(cn.getNgaySinh()));
            stm.setBytes(12, cn.getHinhAnh());
            stm.setString(13, cn.getEmail());
            stm.setDate(14, Date.valueOf(cn.getNgayVaoLam()));
            stm.setBoolean(15, cn.isbHXH());
            stm.setDouble(16, cn.getPhuCap());
            stm.setInt(17, cn.getThamNien());
            stm.setString(18, cn.getMaCN());
            int res = stm.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<CongNhan> timCongNhanTheoMa(String ma) {
        Connection con = null;
        ArrayList<CongNhan> dsCongNhan = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from CongNhan"
                    + " where maCN like N'%" + ma + "%'");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("phuCap"), rs.getInt("thamNien"),rs.getBoolean("trangThai"));
                dsCongNhan.add(congNhan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongNhan;
    }

    public ArrayList<CongNhan> timCongNhanTheoMavaTen(String maCN, String tenCN) {
        Connection con = null;
        ArrayList<CongNhan> dsCongNhan = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from CongNhan"
                    + " where maCN like N'%" + maCN + "%'" + " or tenCN like N'%" + tenCN + "%'");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("phuCap"), rs.getInt("luongThamNien"),rs.getBoolean("trangThai"));
                dsCongNhan.add(congNhan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongNhan;
    }

    public ArrayList<CongNhan> timCongNhanTheoTen(String ten) {
        Connection con = null;
        ArrayList<CongNhan> dsCongNhan = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from CongNhan"
                    + " where hoTen like N'%" + ten + "%'");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("phuCap"), rs.getInt("luongThamNien"),rs.getBoolean("trangThai"));
                dsCongNhan.add(congNhan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongNhan;
    }

    public ArrayList<CongNhan> getAllCongNhanChuaPC(LocalDate ngayPC) {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Select * From CongNhan CN\n"
                    + "	Where trangThai=1 and maCN not in (Select maCN From SanPham SP\n"
                    + "	Join CongDoan CD on  SP.maSP = CD.maSP\n"
                    + "	JOIN BangPhanCong PC on CD.maCD = PC.maCD\n"
                    + "	Where ngayHoanThanh >= CONVERT(date,GETDATE()) AND SP.trangThai = 0\n"
                    + "	AND CD.trangThai = 0 AND PC.ngayPC = ?)");
            stm.setDate(1, Date.valueOf(ngayPC));
            ResultSet rs = stm.executeQuery();
            CongNhan congNhan = null;
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ").trim(), rs.getString("quanHuyen_QQ").trim(), rs.getString("xaPhTT_QQ").trim());
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH").trim(), rs.getString("quanHuyen_DCLH").trim(), rs.getString("xaPhTT_DCLH").trim());
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
//                NhanVien nhanVien = new NhanVien(rs.getString("maNV").trim(), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT").trim(), rs.getString("cMND").trim(), rs.getString("matKhau").trim(), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email").trim(), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"), rs.getInt("luongThamNien"));
                congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("phuCap"), rs.getInt("thamNien"),rs.getBoolean("trangThai"));
                dsCongNhan.add(congNhan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongNhan;
    }
}
