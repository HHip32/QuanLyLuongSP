/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.BangLuongNV;
import entity.DiaChi;
import entity.NhanVien;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class BangLuongNV_DAO {

    public ArrayList<BangLuongNV> getAllDsBLNV(int thang, int nam) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangLuongNV> dsDDNV = new ArrayList<BangLuongNV>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select maLNV,BL.luongCB,BL.hsLuong,soNgayCong,BL.soGioTangCa,soNgayNghiKoPhep,BL.phat,thuong,BL.phuCap,BL.thamNien,BL.bHXH,BL.tongLuong,NV.maNV,NV.hoTen From BangLuongNV BL, NhanVien NV\n"
                    + "	Where thang=? AND nam =? AND NV.maNV = BL.maNV");
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            NhanVien nv;
            while (rs.next()) {
                nv = new NhanVien(rs.getString("maNV"));
                nv.setTenNV(rs.getString("hoTen"));
//                dsDDNV.add(new BangLuongNV(rs.getString("maLNV")));
                dsDDNV.add(new BangLuongNV(rs.getString("maLNV"), thang, nam, rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("soNgayCong"), rs.getInt("soGioTangCa"), rs.getInt("soNgayNghiKoPhep"), rs.getDouble("thuong"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getDouble("phat"), rs.getBoolean("bHXH"), nv));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDNV;
    }

    public BangLuongNV timBangLNV(String ma) {
        Connection con = null;
        PreparedStatement stm = null;
        BangLuongNV bangLuongNV = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select * From BangLuongNV \n"
                    + "	Where maLNV=?");
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new BangLuongNV(rs.getString("maLNV"), rs.getInt("thang"), rs.getInt("nam"), rs.getDouble("luongCB"), rs.getFloat("hsLuong"), rs.getInt("soNgayCong"), rs.getInt("soGioTangCa"), rs.getInt("soNgayNghiKoPhep"), rs.getDouble("thuong"), rs.getDouble("phuCap"), rs.getInt("thamNien"), rs.getDouble("phat"), rs.getBoolean("bHXH"), new NhanVien(rs.getString("maNV")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BangLuongNV taoBangLuongNV(String maNV, int thang, int nam) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select * From NhanVien \n"
                    + "	Where maNV=?");
            stm.setString(1, maNV);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DiaChi queQuan = new DiaChi(rs.getString("tinhTP_QQ"), rs.getString("quanHuyen_QQ"), rs.getString("xaPhTT_QQ"));
                DiaChi lienHe = new DiaChi(rs.getString("tinhTP_DCLH"), rs.getString("quanHuyen_DCLH"), rs.getString("xaPhTT_DCLH"));
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayVaoLam = rs.getDate("ngayVaoLam").toLocalDate();
                NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTen"), rs.getBoolean("gioiTinh"), queQuan,
                        lienHe, rs.getString("soDT"), rs.getString("cMND"), rs.getString("matKhau"), ngaySinh,
                        rs.getBytes("hinhAnh"), rs.getString("email"), ngayVaoLam, rs.getBoolean("bHXH"), rs.getDouble("luongCB"),
                        rs.getFloat("hsLuong"), rs.getInt("trinhDo"), rs.getBoolean("chucVu"), rs.getDouble("phuCap"));
                String maMoi = String.format("LNV%03d%2d%04d", Integer.parseInt(maNV.substring(2)), thang, nam);
                return new BangLuongNV(maMoi, thang, nam, nv.getLuongCB(), nv.getHsLuong(), 0, 0, 0, 0, nv.getPhuCap(), nv.getLuongThamNien(), 0, nv.isbHXH(), nv);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themBLNV(BangLuongNV bangLuongNV) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Insert BangLuongNV values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, bangLuongNV.getMaLNV());
            stm.setString(2, bangLuongNV.getNhanVien().getMaNV());
            stm.setInt(3, bangLuongNV.getThang());
            stm.setInt(4, bangLuongNV.getNam());
            stm.setDouble(5, bangLuongNV.getLuongCB());
            stm.setFloat(6, bangLuongNV.getHsLuong());
            stm.setInt(7, bangLuongNV.getSoNgayCong());
            stm.setInt(8, bangLuongNV.getSoGioTangCa());
            stm.setInt(9, bangLuongNV.getSoNgayNghiKoPhep());
            stm.setDouble(10, bangLuongNV.getPhat());
            stm.setDouble(11, bangLuongNV.getThuong());
            stm.setDouble(12, bangLuongNV.getPhuCap());
            stm.setInt(13, bangLuongNV.getThamNien());
            stm.setBoolean(14, bangLuongNV.isbHXH());
            stm.setDouble(15, bangLuongNV.getTongLuong());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaBLNV(BangLuongNV bangLuongNV) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Update BangLuongNV\n"
                    + "Set soNgayCong =?,soNgayNghiKoPhep=?,soGioTangCa=?,phat=?,thuong=?,tongLuong=?\n"
                    + "Where maLNV = ?");
            stm.setInt(1, bangLuongNV.getSoNgayCong());
            stm.setInt(2, bangLuongNV.getSoNgayNghiKoPhep());
            stm.setInt(3, bangLuongNV.getSoGioTangCa());
            stm.setDouble(4, bangLuongNV.getPhat());
            stm.setDouble(5, bangLuongNV.getThuong());
            stm.setDouble(6, bangLuongNV.getTongLuong());
            stm.setString(7, bangLuongNV.getMaLNV());
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
            ResultSet rs = stm.executeQuery("select distinct nam=YEAR(ngayLam) From BangDiemDanhNV");
            while (rs.next()) {
                dsNam.add(rs.getInt("nam"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNam;
    }

}
