/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.CongDoan;
import entity.NgayLamViec;
import entity.SanPham;
import java.sql.Connection;
import java.sql.Date;
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
public class NgayLamViec_DAO {

    public ArrayList<NgayLamViec> getAllNgayLamViec() {
        ArrayList<NgayLamViec> dsNgayLamViec = new ArrayList<NgayLamViec>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from NgayLamViec");
            NgayLamViec lamViec = null;
            while (rs.next()) {
                lamViec = new NgayLamViec(rs.getDate("ngayLam").toLocalDate(), rs.getBoolean("loaiNgay"));
                dsNgayLamViec.add(lamViec);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNgayLamViec;
    }

    public boolean themNgayLamViec(NgayLamViec ngayLam) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Insert NgayLamViec Values (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(ngayLam.getNgayLam()));
            statement.setBoolean(2, ngayLam.isLoaiNgay());
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaLoaiNgayLam(NgayLamViec ngayLam) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Update NgayLamViec\n"
                    + "Set loaiNgay = ?\n"
                    + "Where ngayLam=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBoolean(1,ngayLam.isLoaiNgay());
            statement.setDate(2, Date.valueOf(ngayLam.getNgayLam()));
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public NgayLamViec timNgayLamViec(LocalDate ngayLam) {
        NgayLamViec ngayLV = null;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from NgayLamViec Where ngayLam = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(ngayLam));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ngayLV = new NgayLamViec(ngayLam, rs.getBoolean("loaiNgay"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ngayLV;
    }
}
