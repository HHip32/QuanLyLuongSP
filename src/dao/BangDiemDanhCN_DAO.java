/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.BangDiemDanhCN;
import entity.BangPhanCong;
import entity.CongNhan;
import entity.NgayLamViec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class BangDiemDanhCN_DAO {

    public ArrayList<BangDiemDanhCN> getAllDsDDCN(LocalDate ngayCC) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangDiemDanhCN> dsDDCN = new ArrayList<BangDiemDanhCN>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select maCCCN, coMat,ngayLam,PC.maPC,ghiChu,phat,hoTen,CN.maCN From BangDiemDanhCN DD, BangPhanCong PC, CongNhan CN\n"
                    + "	Where DD.maPC = PC.maPC AND PC.maCN = CN.maCN AND ngayLam = ?");
            stm.setDate(1, Date.valueOf(ngayCC));
            BangPhanCong bangPhanCong;
            NgayLamViec ngayLamViec;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                bangPhanCong = new BangPhanCong(rs.getString("maPC"));
                bangPhanCong.setCongNhan(new CongNhan(rs.getString("maCN")));
                bangPhanCong.getCongNhan().setTenCN(rs.getString("hoTen"));
                ngayLamViec = new NgayLamViec(rs.getDate("ngayLam").toLocalDate());
                dsDDCN.add(new BangDiemDanhCN(rs.getString("maCCCN"), rs.getInt("coMat"), rs.getDouble("phat"), rs.getString("ghiChu"), bangPhanCong, ngayLamViec));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDCN;
    }
    
    public ArrayList<BangDiemDanhCN> getAllDsDDCN(int thang, int nam) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangDiemDanhCN> dsDDCN = new ArrayList<BangDiemDanhCN>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select maCCCN, coMat,ngayLam,PC.maPC,ghiChu,phat,hoTen,CN.maCN From BangDiemDanhCN DD, BangPhanCong PC, CongNhan CN\n"
                    + "	Where DD.maPC = PC.maPC AND PC.maCN = CN.maCN AND MONTH(DD.ngayLam) = ? AND YEAR(DD.ngayLam) = ?");
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            BangPhanCong bangPhanCong;
            NgayLamViec ngayLamViec;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                bangPhanCong = new BangPhanCong(rs.getString("maPC"));
                bangPhanCong.setCongNhan(new CongNhan(rs.getString("maCN")));
                bangPhanCong.getCongNhan().setTenCN(rs.getString("hoTen"));
                ngayLamViec = new NgayLamViec(rs.getDate("ngayLam").toLocalDate());
                dsDDCN.add(new BangDiemDanhCN(rs.getString("maCCCN"), rs.getInt("coMat"), rs.getDouble("phat"), rs.getString("ghiChu"), bangPhanCong, ngayLamViec));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDCN;
    }

    public ArrayList<BangDiemDanhCN> getAllDsChuaDDCN(LocalDate ngayCC) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangDiemDanhCN> dsDDCN = new ArrayList<BangDiemDanhCN>();
        Statement stmMa = null;
        String maCC = "CC0000000";
        String maMoi = "";
        int ma;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();

            stmMa = con.createStatement();
            ResultSet rsMa = stmMa.executeQuery("Select maCCCN from BangDiemDanhCN");
            while (rsMa.next()) {
                maCC = rsMa.getString("maCCCN");
            }
            ma = Integer.parseInt(maCC.substring(2));

            stm = con.prepareStatement("Select CN.maCN,hoTen,PC.maPC From CongNhan CN, BangPhanCong PC\n"
                    + "      Where CN.maCN= PC.maCN AND ngayPC = ? AND \n"
                    + "		CN.maCN not in (Select maCN From BangPhanCong PC, BangDiemDanhCN DD\n"
                    + "							Where PC.maPC = DD.maPC AND ngayLam = ?)");
            stm.setDate(1, Date.valueOf(ngayCC));
            stm.setDate(2, Date.valueOf(ngayCC));
            ResultSet rs = stm.executeQuery();
            CongNhan cn;
            BangPhanCong bangPhanCong;
            BangDiemDanhCN bangDiemDanhCN;

            while (rs.next()) {
                maMoi = String.format("DD%07d", ++ma);
                cn = new CongNhan(rs.getString("maCN"));
                cn.setTenCN(rs.getString("hoTen"));
                bangPhanCong = new BangPhanCong(rs.getString("maPC"), ngayCC, cn, null);
                bangDiemDanhCN = new BangDiemDanhCN(maMoi, 2, 0, "", bangPhanCong, null);
                dsDDCN.add(bangDiemDanhCN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDCN;
    }

//    public ArrayList<BangDiemDanhCN> getAllDsChuaCCSP(LocalDate ngayCC, int caLam) {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ArrayList<BangDiemDanhCN> dsDDCN = new ArrayList<BangDiemDanhCN>();
//        try {
//            ConnectDB.getInstance().connect();
//            con = ConnectDB.getConnection();
//            stm = con.prepareStatement("Select  DD.maCCCN,SP.maSP,SP.tenSP,CD.maCD,CD.tenCD,thuTu,CN.maCN,hoTen,PC.maPC From CongNhan CN, BangPhanCong PC, CongDoan CD, SanPham SP, BangDiemDanhCN DD\n"
//                    + "	Where CN.maCN = PC.maCN AND PC.maCD = CD.maCD AND CD.maSP = SP.maSP AND PC.maPC = DD.maPC AND coMat = 2\n"
//                    + "		AND ngayLam = ? AND caLam =? AND DD.maCCCN not in (Select maCCCN From BangChamCongSP CCSP Where CCSP.maCCCN = DD.maCCCN AND ngayLam = ? AND caLam =?)");
//            stm.setDate(1, Date.valueOf(ngayCC));
//            stm.setInt(2, caLam);
//            stm.setDate(3, Date.valueOf(ngayCC));
//            stm.setInt(4, caLam);
//            CongNhan cn;
//            CongDoan cd;
//            SanPham sp;
//            BangPhanCong bangPC;
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                cn = new CongNhan(rs.getString("maCN"));
//                cn.setTenCN(rs.getString("hoTen"));
//                sp = new SanPham(rs.getString("maSP"));
//                sp.setTenSP(rs.getString("tenSP"));
//                cd = new CongDoan(rs.getString("maCD"));
//                cd.setTenCD(rs.getString("tenCD"));
//                cd.setThuTu(rs.getInt("thuTu"));
//                cd.setSanPham(sp);
//                bangPC = new BangPhanCong(rs.getString("maPC"), ngayCC, cn, cd);
//                dsDDCN.add(new BangDiemDanhCN(rs.getString("maCCCN"), 0, 0, 0, "", bangPC, null));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dsDDCN;
//    }
//    public ArrayList<BangChamCongSP> getAllDsChuaCCSP(LocalDate ngayCC, int caLam) {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ArrayList<BangChamCongSP> dsDDCN = new ArrayList<BangChamCongSP>();
//        try {
//            ConnectDB.getInstance().connect();
//            con = ConnectDB.getConnection();
//            stm = con.prepareStatement("Select  DD.maCCCN,SP.maSP,SP.tenSP,CD.maCD,CD.tenCD,thuTu,CN.maCN,hoTen,PC.maPC From CongNhan CN, BangPhanCong PC, CongDoan CD, SanPham SP, BangDiemDanhCN DD\n"
//                    + "	Where CN.maCN = PC.maCN AND PC.maCD = CD.maCD AND CD.maSP = SP.maSP AND PC.maPC = DD.maPC AND coMat = 2\n"
//                    + "		AND ngayLam = ? AND DD.maCCCN not in (Select maCCCN From BangChamCongSP CCSP Where CCSP.maCCCN = DD.maCCCN AND ngayLam = ? AND caLam =?)");
//            stm.setDate(1, Date.valueOf(ngayCC));
//            stm.setDate(3, Date.valueOf(ngayCC));
//            stm.setInt(4, caLam);
//            CongNhan cn;
//            CongDoan cd;
//            SanPham sp;
//            BangPhanCong bangPC;
//            BangDiemDanhCN bangDiemDanhCN;
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                cn = new CongNhan(rs.getString("maCN"));
//                cn.setTenCN(rs.getString("hoTen"));
//                sp = new SanPham(rs.getString("maSP"));
//                sp.setTenSP(rs.getString("tenSP"));
//                cd = new CongDoan(rs.getString("maCD"));
//                cd.setTenCD(rs.getString("tenCD"));
//                cd.setThuTu(rs.getInt("thuTu"));
//                cd.setSanPham(sp);
//                bangPC = new BangPhanCong(rs.getString("maPC"), ngayCC, cn, cd);
//                bangDiemDanhCN = new BangDiemDanhCN(rs.getString("maCCCN"), 0, 0, 0, "", bangPC, null);
//                dsDDCN.add(new BangChamCongSP(bangDiemDanhCN));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dsDDCN;
//    }
    public boolean chamCongDiemDanhCN(BangDiemDanhCN ccCN) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Insert BangDiemDanhCN values (?,?,?,?,?,?)");
            stm.setString(1, ccCN.getMaCCCN());
            stm.setInt(2, ccCN.getCoMAt());
            stm.setDate(3, Date.valueOf(ccCN.getNgayLamViec().getNgayLam()));
            stm.setDouble(4, ccCN.getPhat());
            stm.setString(5, ccCN.getBangPhanCong().getMaPC());
            stm.setString(6, ccCN.getGhiCHu());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaCCDiemDanhCN(BangDiemDanhCN ccCN) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Update BangDiemDanhCN\n"
                    + "Set coMat = ?, phat =?, ghiChu = ?\n"
                    + "where maCCCN = ?");
            stm.setInt(1, ccCN.getCoMAt());
            stm.setDouble(2, ccCN.getPhat());
            stm.setString(3, ccCN.getGhiCHu());
            stm.setString(4, ccCN.getMaCCCN());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public String getMaNVCuoi() {
        String maCC = "";
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select maCCCN from BangDiemDanhCN");
            while (rs.next()) {
                maCC = rs.getString("maCCCN");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maCC == "" ? "CC0000000" : maCC;
    }
}
