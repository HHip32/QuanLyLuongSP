/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.BangLuongCN;
import entity.BangLuongNV;
import entity.CongNhan;
import entity.DiaChi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class BangLuongCN_DAO {

    public ArrayList<BangLuongCN> getAllDsBLCN(int thang, int nam) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangLuongCN> dsBLCN = new ArrayList<BangLuongCN>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select maLCN,soNgayCong,BL.luongSanPham,luongTangCa,soNgayNghiKhongPhep,BL.phat,thuong,BL.phuCap,BL.thamNien,BL.bHXH,BL.tongLuong, CN.maCN, CN.hoTen \n"
                    + "	From BangLuongCN BL, CongNhan CN\n"
                    + "	Where BL.maCN = CN.maCN AND thang=? AND nam = ?");
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            CongNhan cn;
            while (rs.next()) {
                cn = new CongNhan(rs.getString("maCN"));
                cn.setTenCN(rs.getString("hoTen"));
                dsBLCN.add(new BangLuongCN(rs.getString("maLCN"), thang, nam, rs.getInt("soNgayCong"), 0, 0, rs.getInt("soNgayNghiKhongPhep"), rs.getDouble("phuCap"), rs.getInt("thamNien"), 0, rs.getBoolean("bHXH"), cn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsBLCN;
    }

    public BangLuongCN timBangLCN(String ma) {
        Connection con = null;
        PreparedStatement stm = null;
        BangLuongNV bangLuongNV = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select * From BangLuongCN BL\n"
                    + "	Where maLCN=?");
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new BangLuongCN(ma, rs.getInt("thang"), rs.getInt("nam"), rs.getInt("soNgayCong"), rs.getFloat("luongSanPham"), rs.getFloat("luongTangCa"), rs.getInt("soNgayNghiKhongPhep"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getDouble("phat"), rs.getBoolean("bHXH"), new CongNhan(rs.getString("maCN")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BangLuongCN taoBangLuongCN(String maCN, int thang, int nam) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select * From CongNhan \n"
                    + "	Where maCN=?");
            stm.setString(1, maCN);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                CongNhan congNhan = new CongNhan(rs.getString("maCN"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan, lienHe, rs.getString("soDT"), rs.getString("cMND"), ngaySinh, rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("phuCap"), rs.getInt("thamNien"),rs.getBoolean("trangThai"));
                String maMoi = String.format("LCN%04d%2d%04d", Integer.parseInt(maCN.substring(2)), thang, nam);
                BangLuongCN bangLuongCN = new BangLuongCN(maMoi, thang, nam, 0, 0, 0, 0, congNhan.getPhuCap(), congNhan.getThamNien(), 0, congNhan.isbHXH(), congNhan);
                themBLCN(bangLuongCN);
                return bangLuongCN;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themBLCN(BangLuongCN bangLuongCN) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Insert BangLuongCN values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, bangLuongCN.getMaLCN());
            stm.setInt(2, bangLuongCN.getThang());
            stm.setInt(3, bangLuongCN.getNam());
            stm.setInt(4, bangLuongCN.getSoNgayCong());
            stm.setDouble(5, bangLuongCN.getLuongSanPham());
            stm.setDouble(6, bangLuongCN.getLuongTangCa());
            stm.setInt(7, bangLuongCN.getSoNgayNghiKoPhep());
            stm.setDouble(8, bangLuongCN.getPhat());
            stm.setDouble(9, bangLuongCN.getThuong());
            stm.setDouble(10, bangLuongCN.getPhuCap());
            stm.setInt(11, bangLuongCN.getThamNien());
            stm.setBoolean(12, bangLuongCN.isbHXH());
            stm.setString(13, bangLuongCN.getCongNhan().getMaCN());
            stm.setDouble(14, bangLuongCN.getTongLuong());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaBLCN(BangLuongCN bangLuongCN) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Update BangLuongCN\n"
                    + "Set soNgayCong =?,soNgayNghiKhongPhep=?,luongTangCa=?,luongSanPham=?,phat=?,thuong=?,tongLuong=?\n"
                    + "Where maLCN = ?");
            stm.setInt(1, bangLuongCN.getSoNgayCong());
            stm.setInt(2, bangLuongCN.getSoNgayNghiKoPhep());
            stm.setDouble(3, bangLuongCN.getLuongTangCa());
            stm.setDouble(4, bangLuongCN.getLuongSanPham());
            stm.setDouble(5, bangLuongCN.getTongPhat());
            stm.setDouble(6, bangLuongCN.getThuong());
            stm.setDouble(7, bangLuongCN.getTongLuong());
            stm.setString(8, bangLuongCN.getMaLCN());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<Integer> getAllNam() {
        Connection con = null;
        Statement stm = null;
        ArrayList<Integer> dsNam = new ArrayList<Integer>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select distinct nam=YEAR(ngayLam) From BangDiemDanhCN ");
            while (rs.next()) {
                dsNam.add(rs.getInt("nam"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNam;
    }

    public BangLuongCN timBangLNV(String ma) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select * From BangLuongCN \n"
                    + "	Where maLCN=?");
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new BangLuongCN(ma, rs.getInt("thang"), rs.getInt("nam"), 0, 0, 0, 0, rs.getFloat("phuCap"), rs.getInt("thamNien"), 0, rs.getBoolean("bHXH"), new CongNhan(rs.getString("maCN")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
