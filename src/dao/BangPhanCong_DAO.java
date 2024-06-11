/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.BangPhanCong;
import entity.CongDoan;
import entity.CongNhan;
import entity.SanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Asus
 */
public class BangPhanCong_DAO {

    public ArrayList<BangPhanCong> getAllBangPhanCong() {
        ArrayList<BangPhanCong> dsBangPhanCong = new ArrayList<BangPhanCong>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from BangPhanCong");
            BangPhanCong bangPC = null;
            while (rs.next()) {
                bangPC = new BangPhanCong(rs.getString("maPC"), rs.getDate("ngayPC").toLocalDate(), new CongNhan(rs.getString("maCN")), new CongDoan(rs.getString("maCD")));
                dsBangPhanCong.add(bangPC);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsBangPhanCong;
    }

    public ArrayList<BangPhanCong> getAllBangPhanCongTheoNgay(LocalDate ngayPC) {
        ArrayList<BangPhanCong> dsBangPhanCong = new ArrayList<BangPhanCong>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Select maPC,ngayPC,CN.maCN,CN.hoTen,CD.maCD,CD.tenCD,SP.maSP,SP.tenSP From BangPhanCong PC, CongNhan CN, CongDoan CD,SanPham SP\n"
                    + "	Where SP.maSP=CD.maSP AND CN.maCN = PC.maCN AND PC.maCD = CD.maCD AND ngayPC = ?");
            stm.setDate(1, Date.valueOf(ngayPC));
            ResultSet rs = stm.executeQuery();
            BangPhanCong bangPC = null;
            SanPham sanPham =null;
            CongDoan congDoan = null;
            CongNhan congNhan = null;
            while (rs.next()) {
                sanPham = new SanPham(rs.getString("maSP"));
                sanPham.setTenSP(rs.getString("tenSP"));
                congNhan = new CongNhan(rs.getString("maCN"));
                congNhan.setTenCN(rs.getString("hoTen"));
                congDoan = new CongDoan(rs.getString("maCD"));
                congDoan.setTenCD(rs.getString("tenCD"));
                congDoan.setSanPham(sanPham);
                bangPC = new BangPhanCong(rs.getString("maPC"), rs.getDate("ngayPC").toLocalDate(), congNhan, congDoan);
                dsBangPhanCong.add(bangPC);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsBangPhanCong;
    }

    public boolean themBangPC(BangPhanCong bangPC) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Insert BangPhanCong Values (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, bangPC.getMaPC());
            statement.setDate(2, Date.valueOf(bangPC.getNgayPC()));
            statement.setString(3, bangPC.getCongDoan().getMaCD());
            statement.setString(4, bangPC.getCongNhan().getMaCN());
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaBangPC(BangPhanCong bangPC) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Update BangPhanCong\n"
                    + "Set ngayPC = ?,maCD=?,maCN=?\n"
                    + "Where maPC=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(bangPC.getNgayPC()));
            statement.setString(2, bangPC.getCongDoan().getMaCD());
            statement.setString(3, bangPC.getCongNhan().getMaCN());
            statement.setString(4, bangPC.getMaPC());
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean xoaBangPC(String maPC) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Delete BangPhanCong Where maPC = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maPC);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            sh_Mes("Công nhân đã được chấm công nên không thể xóa", null);
            return false;
        }
        return n > 0;
    }

    public BangPhanCong timBangPC(String maPC) {
        BangPhanCong bangPC = null;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from BangPhanCong Where maPC = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maPC);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                bangPC = new BangPhanCong(rs.getString("maPC"), rs.getDate("ngayPC").toLocalDate(), new CongNhan(rs.getString("maCN")), new CongDoan(rs.getString("maCD")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bangPC;
    }
    public String getMaPCCuoi(){
        String maPC = "";
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select maPC From BangPhanCong");
            while (rs.next()) {
                maPC = rs.getString("maPC");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maPC;
    }
    
     private void sh_Mes(String mes, JTextField txt) {
        JOptionPane.showMessageDialog(null, mes);
        if (txt == null) {
            return;
        }
        txt.requestFocus();
    }
}
