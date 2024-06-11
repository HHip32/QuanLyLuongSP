/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.BangChamCongTangCa;
import entity.BangDiemDanhNV;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class BangChamCongTangCa_DAO {
    
    public ArrayList<BangChamCongTangCa> getAllDsChuaCCTC(LocalDate ngayCC) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangChamCongTangCa> dsDDNV = new ArrayList<BangChamCongTangCa>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select DD.maCCNV,NV.maNV,hoTen From NhanVien NV, BangDiemDanhNV DD\n"
                    + "	Where NV.maNV = DD.maNV AND coMat = 2 AND ngayLam = ? \n"
                    + "		AND DD.maCCNV not in (Select CC.maCCNV From  BangChamCongTangCa CC \n"
                    + "					Where CC.maCCNV = DD.maCCNV AND ngayLam =?)");
            stm.setDate(1, Date.valueOf(ngayCC));
            stm.setDate(2, Date.valueOf(ngayCC));
            NhanVien nv;
            BangDiemDanhNV bangDiemDanhNV;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString("maNV"));
                nv.setTenNV(rs.getString("hoTen"));
                bangDiemDanhNV = new BangDiemDanhNV(rs.getString("maCCNV"));
                bangDiemDanhNV.setNhanVien(nv);
                dsDDNV.add(new BangChamCongTangCa(bangDiemDanhNV));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDNV;
    }
    
    public ArrayList<BangChamCongTangCa> getAllDsCCSP(LocalDate ngayCC) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangChamCongTangCa> dsDDNV = new ArrayList<BangChamCongTangCa>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select CC.maCCNV,NV.maNV,hoTen,CC.soGioTangCa,CC.phat,CC.ghiChu \n"
                    + "	From BangChamCongTangCa CC, BangDiemDanhNV DD,NhanVien NV\n"
                    + "	Where CC.maCCNV = DD.maCCNV AND DD.maNV = NV.maNV AND ngayLam = ?");
            stm.setDate(1, Date.valueOf(ngayCC));
            NhanVien nv;
            BangDiemDanhNV bangDiemDanhNV;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString("maNV"));
                nv.setTenNV(rs.getString("hoTen"));
                bangDiemDanhNV = new BangDiemDanhNV(rs.getString("maCCNV"));
                bangDiemDanhNV.setNhanVien(nv);
                dsDDNV.add(new BangChamCongTangCa(bangDiemDanhNV, rs.getInt("soGioTangCa"), rs.getDouble("phat"), rs.getString("ghiChu")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDNV;
    }
    
    public ArrayList<BangChamCongTangCa> getAllDsDD_CCSP(LocalDate ngayCC) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangChamCongTangCa> dsDDNV = new ArrayList<BangChamCongTangCa>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select DD.maCCNV,NV.maNV,hoTen,DD.coMat,CC.soGioTangCa,CC.phat,CC.ghiChu\n"
                    + "     From BangDiemDanhNV DD left join BangChamCongTangCa CC on CC.maCCNV = DD.maCCNV ,NhanVien NV\n"
                    + "      Where DD.maNV = NV.maNV AND ngayLam = ?");
            stm.setDate(1, Date.valueOf(ngayCC));
            NhanVien nv;
            BangDiemDanhNV bangDiemDanhNV;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getString("maNV"));
                nv.setTenNV(rs.getString("hoTen"));
                bangDiemDanhNV = new BangDiemDanhNV(rs.getString("maCCNV"));
                bangDiemDanhNV.setNhanVien(nv);
                bangDiemDanhNV.setCoMat(rs.getInt("coMat"));
                dsDDNV.add(new BangChamCongTangCa(bangDiemDanhNV, rs.getInt("soGioTangCa"), rs.getDouble("phat"), rs.getString("ghiChu")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDNV;
    }
    
    public boolean chamCongTangCaNV(BangChamCongTangCa ccNV) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Insert BangChamCongTangCa values (?,?,?,?)");
            stm.setString(1, ccNV.getBangDiemDanhNV().getMaCCNV());
            stm.setInt(2, ccNV.getSoGioTangCa());
            stm.setDouble(3, ccNV.getPhat());
            stm.setString(4, ccNV.getGhiChu());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public boolean suaCCTangCaNV(BangChamCongTangCa ccNV) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Update BangChamCongTangCa\n"
                    + "SET soGioTangCa=?,phat=?,ghiChu=?\n"
                    + "Where maCCNV = ?");
            stm.setInt(1, ccNV.getSoGioTangCa());
            stm.setDouble(2, ccNV.getPhat());
            stm.setString(3, ccNV.getGhiChu());
            stm.setString(4, ccNV.getBangDiemDanhNV().getMaCCNV());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public boolean xoaCCTangCaNV(String maCCNV) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Delete BangChamCongTangCa Where maCCNV = ?");
            stm.setString(1, maCCNV);
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
