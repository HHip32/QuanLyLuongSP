/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.SanPham;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Date;

/**
 *
 * @author Asus
 */
public class SanPham_DAO {

    public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from SanPham");
            SanPham sanPham = null;
            while (rs.next()) {
                sanPham = new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getDate("ngayBatDau").toLocalDate(), rs.getDate("ngayHoanThanh").toLocalDate(), rs.getInt("soLuong"), rs.getInt("soCongDoan"), rs.getBoolean("trangThai"));
                dsSanPham.add(sanPham);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }

    public ArrayList<SanPham> getAllSanPhamChuaHT() {
        ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from SanPham Where ngayHoanThanh >= CONVERT(date,GETDATE()) AND trangThai = 0 ");
            SanPham sanPham = null;
            while (rs.next()) {
                sanPham = new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getDate("ngayBatDau").toLocalDate(), rs.getDate("ngayHoanThanh").toLocalDate(), rs.getInt("soLuong"), rs.getInt("soCongDoan"), rs.getBoolean("trangThai"));
                dsSanPham.add(sanPham);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }

    public boolean themSanPham(SanPham sanPham) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Insert SanPham Values (?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, sanPham.getMaSP());
            statement.setString(2, sanPham.getTenSP());
            statement.setDate(3, Date.valueOf(sanPham.getNgayBatDau()));
            statement.setDate(4, Date.valueOf(sanPham.getNgayHoanThanh()));
            statement.setInt(5, sanPham.getSoLuong());
            statement.setInt(6, sanPham.getSoCongDoan());
            statement.setBoolean(7, sanPham.isTrangThai());
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaSanPham(SanPham sanPham) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Update SanPham\n"
                    + "Set tenSP = ?,ngayHoanThanh=?,soLuong=?,soCongDoan=?\n"
                    + "Where maSP=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, sanPham.getTenSP());
            statement.setDate(2, Date.valueOf(sanPham.getNgayHoanThanh()));
            statement.setInt(3, sanPham.getSoLuong());
            statement.setInt(4, sanPham.getSoCongDoan());
            statement.setString(5, sanPham.getMaSP());
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean xoaSanPham(String maSP) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Delete SanPham Where maSP = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maSP);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public SanPham timSanPham(String maSP) {
        SanPham sp = null;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from SanPham Where maSP = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maSP);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                sp = new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getDate("ngayBatDau").toLocalDate(), rs.getDate("ngayHoanThanh").toLocalDate(), rs.getInt("soLuong"), rs.getInt("soCongDoan"), rs.getBoolean("trangThai"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sp;
    }

    public String getMaNVCuoi() {
        String maSP = "";
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select maSP from SanPham");
            while (rs.next()) {
                maSP = rs.getString("maSP");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maSP;
    }

    public boolean capNhatTrangThaiSP(String maSP, boolean b) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Update SanPham\n"
                    + "Set trangThai = ?\n"
                    + "Where maSP = ?");
            stm.setBoolean(1, b);
            stm.setString(2, maSP);
            n = stm.executeUpdate();
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean ktraTrangThaiSP(String maSP) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Select sl=COUNT(*) From SanPham SP, CongDoan CD\n"
                    + "	Where CD.maSP=SP.maSP AND SP.maSP = ? AND CD.trangThai = 0");
            stm.setString(1, maSP);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt("sl");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n == 0;
    }

}
