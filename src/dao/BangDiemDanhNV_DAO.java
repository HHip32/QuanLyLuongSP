/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.BangDiemDanhCN;
import entity.BangDiemDanhNV;
import entity.BangPhanCong;
import entity.CongNhan;
import entity.NgayLamViec;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class BangDiemDanhNV_DAO {

    public boolean chamCongDiemDanhNV(BangDiemDanhNV ccNV) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Insert BangDiemDanhNV values (?,?,?,?,?,?)");
            stm.setString(1, ccNV.getMaCCNV());
            stm.setString(2, ccNV.getNhanVien().getMaNV());
            stm.setInt(3, ccNV.getCoMat());
            stm.setDate(4, Date.valueOf(ccNV.getNgayLamViec().getNgayLam()));
            stm.setDouble(5, ccNV.getPhat());
            stm.setString(6, ccNV.getGhiChu());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaCCDiemDanhNV(BangDiemDanhNV ccNV) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Update BangDiemDanhNV\n"
                    + "Set coMat = ?, phat =?, ghiChu = ?\n"
                    + "where maCCNV = ?");
            stm.setInt(1, ccNV.getCoMat());
            stm.setDouble(2, ccNV.getPhat());
            stm.setString(3, ccNV.getGhiChu());
            stm.setString(4, ccNV.getMaCCNV());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<BangDiemDanhNV> getAllDsChuaDDNV(LocalDate ngayCC) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangDiemDanhNV> dsDDNV = new ArrayList<BangDiemDanhNV>();
        BangDiemDanhNV bangDiemDanhNV;
        Statement stmMa = null;
        String maCC = "";
        String maMoi = "";
        int ma;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stmMa = con.createStatement();
            ResultSet rsMa = stmMa.executeQuery("Select maCCNV from BangDiemDanhNV");
            while (rsMa.next()) {
                maCC = rsMa.getString("maCCNV");
            }
            if (maCC == "") {
                maCC = "CC0000000";
            }
            ma = Integer.parseInt(maCC.substring(2));
            
            //            Lấy danh sách
            stm = con.prepareStatement("Select NV.maNV,hoTen From NhanVien NV\n"
                    + "        Where NV.trangThai=1 AND NV.maNV not in (Select maNV From  BangDiemDanhNV DD\n"
                    + "         Where ngayLam = ?)");
            stm.setDate(1, Date.valueOf(ngayCC));
            NhanVien nv;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                maMoi = String.format("CC%07d", ++ma);
                nv = new NhanVien(rs.getString("maNV"));
                nv.setTenNV(rs.getString("hoTen"));
                bangDiemDanhNV = new BangDiemDanhNV(maMoi, 2, 0, "", nv, null);
                dsDDNV.add(bangDiemDanhNV);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDNV;
    }

    public ArrayList<BangDiemDanhNV> getAllDsDDNV(LocalDate ngayCC) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangDiemDanhNV> dsDDNV = new ArrayList<BangDiemDanhNV>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select maCCNV, coMat,ngayLam,ghiChu,phat,hoTen,NV.maNV From NhanVien NV, BangDiemDanhNV DD\n"
                    + "	Where NV.maNV =DD.maNV AND ngayLam = ?");
            stm.setDate(1, Date.valueOf(ngayCC));
            NgayLamViec ngayLamViec;
            NhanVien nv;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString("maNV"));
                nv.setTenNV(rs.getString("hoTen"));
                ngayLamViec = new NgayLamViec(rs.getDate("ngayLam").toLocalDate());
                dsDDNV.add(new BangDiemDanhNV(rs.getString("maCCNV"), rs.getInt("coMat"), rs.getDouble("phat"), rs.getString("ghiChu"), nv, ngayLamViec));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDNV;
    }

    public String getMaNVCuoi() {
        Connection con = null;
        Statement stm = null;
        String maCC = "";
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select maCCNV from BangDiemDanhNV");
            while (rs.next()) {
                maCC = rs.getString("maCCNV");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maCC == "" ? "CC" + "0000000" : maCC;
    }
}
