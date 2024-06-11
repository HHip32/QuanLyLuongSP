/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.BangChamCongSP;
import entity.BangDiemDanhCN;
import entity.BangLuongCN;
import entity.BangPhanCong;
import entity.CongDoan;
import entity.CongNhan;
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
public class BangChamCongSP_DAO {

    public boolean chamCongSanPham(BangChamCongSP ccSP) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Insert BangChamCongSP values (?,?,?,?,?,?)");
            stm.setString(1, ccSP.getMaCCSP());
            stm.setInt(2, ccSP.getCaLam());
            stm.setInt(3, ccSP.getSoLuongSP());
            stm.setDouble(4, ccSP.getPhat());
            stm.setString(5, ccSP.getGhiChu());
            stm.setString(6, ccSP.getBangDiemDanhCN().getMaCCCN());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaChamCongSanPham(BangChamCongSP ccSP) {
        int n = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Update BangChamCongSP\n"
                    + "Set soLuongSP =?,phat=?,ghiChu=?\n"
                    + "Where maCCSP = ?");
            stm.setInt(1, ccSP.getSoLuongSP());
            stm.setDouble(2, ccSP.getPhat());
            stm.setString(3, ccSP.getGhiChu());
            stm.setString(4, ccSP.getMaCCSP());
            n = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<BangChamCongSP> getAllDsChuaCCSP(LocalDate ngayCC, int caLam) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangChamCongSP> dsDDCN = new ArrayList<BangChamCongSP>();
        Statement stmMa = null;
        String maCC = "";
        String maMoi = "";
        int ma;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();

            stmMa = con.createStatement();
            ResultSet rsMa = stmMa.executeQuery("Select maCCSP From BangChamCongSP");
            while (rsMa.next()) {
                maCC = rsMa.getString("maCCSP");
            }
            if (maCC == "") {
                maCC = "CC00000000";
            }
            ma = Integer.parseInt(maCC.substring(2));

            stm = con.prepareStatement("Select  DD.maCCCN,SP.maSP,SP.tenSP,CD.maCD,CD.tenCD,thuTu,CN.maCN,hoTen,PC.maPC From CongNhan CN, BangPhanCong PC, CongDoan CD, SanPham SP, BangDiemDanhCN DD\n"
                    + "	Where CN.maCN = PC.maCN AND PC.maCD = CD.maCD AND CD.maSP = SP.maSP AND PC.maPC = DD.maPC AND coMat = 2\n"
                    + "		AND ngayLam = ? AND DD.maCCCN not in (Select maCCCN From BangChamCongSP CCSP Where CCSP.maCCCN = DD.maCCCN AND ngayLam = ? AND caLam =?)");
            stm.setDate(1, Date.valueOf(ngayCC));
            stm.setDate(2, Date.valueOf(ngayCC));
            stm.setInt(3, caLam);
            CongNhan cn;
            CongDoan cd;
            SanPham sp;
            BangPhanCong bangPC;
            BangDiemDanhCN bangDiemDanhCN;
            ResultSet rs = stm.executeQuery();
            BangChamCongSP bangChamCongSP;
            while (rs.next()) {
                maMoi = String.format("CC%07d", ++ma);
                cn = new CongNhan(rs.getString("maCN"));
                cn.setTenCN(rs.getString("hoTen"));
                sp = new SanPham(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                cd = new CongDoan(rs.getString("maCD"));
                cd.setTenCD(rs.getString("tenCD"));
                cd.setThuTu(rs.getInt("thuTu"));
                cd.setSanPham(sp);
                bangPC = new BangPhanCong(rs.getString("maPC"), ngayCC, cn, cd);
                bangDiemDanhCN = new BangDiemDanhCN(rs.getString("maCCCN"));
                bangDiemDanhCN.setBangPhanCong(bangPC);
                bangChamCongSP = new BangChamCongSP(maMoi);
                bangChamCongSP.setBangDiemDanhCN(bangDiemDanhCN);
                dsDDCN.add(bangChamCongSP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDCN;
    }

    public ArrayList<BangChamCongSP> getAllDsCCSP(LocalDate ngayCC, int caLam) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangChamCongSP> dsDDCN = new ArrayList<BangChamCongSP>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select CC.maCCSP,CC.maCCCN,soLuongSP, CC.phat,CC.ghiChu,PC.maPC,CN.maCN,CN.hoTen,SP.maSP,SP.tenSP,CD.maCD,CD.tenCD,CD.soLuong,thuTu \n"
                    + "	From BangDiemDanhCN DD, BangPhanCong PC, CongNhan CN, CongDoan CD, SanPham SP ,BangChamCongSP CC\n"
                    + "        Where ngayLam = ? AND caLam = ? AND CC.maCCCN = DD.maCCCN AND DD.maPC = PC.maPC \n"
                    + "			AND PC.maCN = CN.maCN AND PC.maCD = CD.maCD AND CD.maSP = SP.maSP ");
            stm.setDate(1, Date.valueOf(ngayCC));
            stm.setInt(2, caLam);
            CongNhan cn;
            CongDoan cd;
            SanPham sp;
            BangPhanCong bangPhanCong;
            BangDiemDanhCN bangDiemDanhCN;
            BangChamCongSP bangChamCongSP;
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cn = new CongNhan(rs.getString("maCN"));
                cn.setTenCN(rs.getString("hoTen"));
                sp = new SanPham(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                cd = new CongDoan(rs.getString("maCD"));
                cd.setTenCD(rs.getString("tenCD"));
                cd.setSoLuong(rs.getInt("soLuong"));
                cd.setSanPham(sp);
                bangPhanCong = new BangPhanCong(rs.getString("maPC"));
                bangPhanCong.setCongNhan(cn);
                bangPhanCong.setCongDoan(cd);
                bangDiemDanhCN = new BangDiemDanhCN(rs.getString("maCCCN"));
                bangDiemDanhCN.setBangPhanCong(bangPhanCong);
                bangChamCongSP = new BangChamCongSP(rs.getString("maCCSP"), caLam, rs.getInt("soLuongSP"), rs.getDouble("phat"), rs.getString("ghiChu"), bangDiemDanhCN);
                dsDDCN.add(bangChamCongSP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDDCN;
    }


    public double getPhatTHeoMaCCCN(String maCCDD) {
        Connection con = null;
        PreparedStatement stm = null;
        double phat = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select tongPhat=SUM(phat) From BangChamCongSP \n"
                    + "	Where maCCCN = ?");
            stm.setString(1, maCCDD);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                phat = rs.getFloat("tongPhat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phat;
    }

    public boolean xoaCCSPTheoMaCCCN(String ma) {
        Connection con = null;
        PreparedStatement stm = null;
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Delete BangChamCongSP  Where maCCCN = ?");
            stm.setString(1, ma);
            n = stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return n > 0;
    }

    public String getMaNVCuoi() {
        String maCC = "";
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select maCCSP From BangChamCongSP");
            while (rs.next()) {
                maCC = rs.getString("maCCSP");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maCC == "" ? "CC00000000" : maCC;
    }

    public ArrayList<BangChamCongSP> getAllDsCCSP(int thang, int nam) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangChamCongSP> dsCCSP = new ArrayList<BangChamCongSP>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select SP.maSP,SP.tenSP, CD.maCD,CD.tenCD,CD.donGia, CN.maCN, CN.hoTen,CN.ngayVaoLam, PC.maPC,LV.ngayLam,LV.loaiNgay,\n"
                    + "	DD.maCCCN ,DD.coMat,phatDD=DD.phat,CC.maCCSP,CC.caLam,CC.soLuongSP,phatCC=CC.phat\n"
                    + "		From SanPham SP, CongDoan CD, BangPhanCong PC, CongNhan CN, NgayLamViec LV, BangDiemDanhCN DD, BangChamCongSP CC\n"
                    + "		Where SP.maSP = CD.maSP AND CD.maCD = PC.maCD AND CN.maCN = PC.maCN  AND LV.ngayLam = DD.ngayLam\n"
                    + "			AND PC.maPC = DD.maPC AND DD.maCCCN = CC.maCCCN\n"
                    + "			AND MONTH(DD.ngayLam) = ? AND YEAR(DD.ngayLam) = ?");
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            SanPham sp;
            CongDoan cd;
            CongNhan cn;
            BangPhanCong bangPC;
            NgayLamViec ngayLV;
            BangDiemDanhCN bangDD;
            BangChamCongSP bangCCSP;
            String maCCSP;
            while (rs.next()) {
                sp = new SanPham(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                cd = new CongDoan(rs.getString("maCD"));
                cd.setTenCD(rs.getString("tenCD"));
                cd.setGia(rs.getFloat("donGia"));
                cd.setSanPham(sp);
                cn = new CongNhan(rs.getString("maCN"));
                cn.setTenCN(rs.getString("hoTen"));
                cn.setThamNien(rs.getDate("ngayVaoLam").toLocalDate());
                bangPC = new BangPhanCong(rs.getString("maPC"));
                bangPC.setCongDoan(cd);
                bangPC.setCongNhan(cn);
                ngayLV = new NgayLamViec(rs.getDate("ngayLam").toLocalDate(), rs.getBoolean("loaiNgay"));
                bangDD = new BangDiemDanhCN(rs.getString("maCCCN"), rs.getInt("coMat"), rs.getFloat("phatDD"), "", bangPC, ngayLV);
                maCCSP = rs.getString("maCCSP");
                if (maCCSP == null) {
                    maCCSP = "0";
                }
                bangCCSP = new BangChamCongSP(maCCSP, rs.getInt("caLam"), rs.getInt("soLuongSP"), rs.getFloat("phatCC"), "", bangDD);
                dsCCSP.add(bangCCSP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCCSP;
    }
    
    public ArrayList<BangChamCongSP> getAllDsCCSP(String maSP) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangChamCongSP> dsCCSP = new ArrayList<BangChamCongSP>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select SP.maSP,SP.tenSP,SP.soLuong,SP.soCongDoan, CD.maCD,CD.tenCD,CD.donGia, CN.maCN, CN.hoTen,CN.ngayVaoLam, PC.maPC,LV.ngayLam,LV.loaiNgay,\n"
                    + "	DD.maCCCN ,DD.coMat,phatDD=DD.phat,CC.maCCSP,CC.caLam,CC.soLuongSP,phatCC=CC.phat\n"
                    + "		From SanPham SP, CongDoan CD, BangPhanCong PC, CongNhan CN, NgayLamViec LV, BangDiemDanhCN DD, BangChamCongSP CC\n"
                    + "		Where SP.maSP = CD.maSP AND CD.maCD = PC.maCD AND CN.maCN = PC.maCN  AND LV.ngayLam = DD.ngayLam\n"
                    + "			AND PC.maPC = DD.maPC AND DD.maCCCN = CC.maCCCN\n"
                    + "			AND SP.maSP = ?");
            stm.setString(1, maSP);
            ResultSet rs = stm.executeQuery();
            SanPham sp;
            CongDoan cd;
            CongNhan cn;
            BangPhanCong bangPC;
            NgayLamViec ngayLV;
            BangDiemDanhCN bangDD;
            BangChamCongSP bangCCSP;
            String maCCSP;
            while (rs.next()) {
                sp = new SanPham(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setSoLuong(rs.getInt("soLuong"));
                sp.setSoCongDoan(rs.getInt("soCongDoan"));
                cd = new CongDoan(rs.getString("maCD"));
                cd.setTenCD(rs.getString("tenCD"));
                cd.setGia(rs.getFloat("donGia"));
                cd.setSanPham(sp);
                cn = new CongNhan(rs.getString("maCN"));
                cn.setTenCN(rs.getString("hoTen"));
                cn.setThamNien(rs.getDate("ngayVaoLam").toLocalDate());
                bangPC = new BangPhanCong(rs.getString("maPC"));
                bangPC.setCongDoan(cd);
                bangPC.setCongNhan(cn);
                ngayLV = new NgayLamViec(rs.getDate("ngayLam").toLocalDate(), rs.getBoolean("loaiNgay"));
                bangDD = new BangDiemDanhCN(rs.getString("maCCCN"), rs.getInt("coMat"), rs.getFloat("phatDD"), "", bangPC, ngayLV);
                maCCSP = rs.getString("maCCSP");
                if (maCCSP == null) {
                    maCCSP = "0";
                }
                bangCCSP = new BangChamCongSP(maCCSP, rs.getInt("caLam"), rs.getInt("soLuongSP"), rs.getFloat("phatCC"), "", bangDD);
                dsCCSP.add(bangCCSP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCCSP;
    }

    public ArrayList<BangChamCongSP> getAllDsCCSP(LocalDate ngayLVtim) {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<BangChamCongSP> dsCCSP = new ArrayList<BangChamCongSP>();
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select SP.maSP,SP.tenSP, CD.maCD,CD.tenCD,CD.donGia,CD.soLuong, CN.maCN, CN.hoTen,CN.ngayVaoLam, PC.maPC,LV.ngayLam,LV.loaiNgay,\n"
                    + "	DD.maCCCN ,DD.coMat,phatDD=DD.phat,CC.maCCSP,CC.caLam,CC.soLuongSP,phatCC=CC.phat\n"
                    + "		From SanPham SP, CongDoan CD, BangPhanCong PC, CongNhan CN, NgayLamViec LV, BangDiemDanhCN DD, BangChamCongSP CC\n"
                    + "		Where SP.maSP = CD.maSP AND CD.maCD = PC.maCD AND CN.maCN = PC.maCN  AND LV.ngayLam = DD.ngayLam\n"
                    + "			AND PC.maPC = DD.maPC AND DD.maCCCN = CC.maCCCN\n"
                    + "			AND DD.ngayLam = ?");
            stm.setDate(1, Date.valueOf(ngayLVtim));
            ResultSet rs = stm.executeQuery();
            SanPham sp;
            CongDoan cd;
            CongNhan cn;
            BangPhanCong bangPC;
            NgayLamViec ngayLV;
            BangDiemDanhCN bangDD;
            BangChamCongSP bangCCSP;
            String maCCSP;
            while (rs.next()) {
                sp = new SanPham(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                cd = new CongDoan(rs.getString("maCD"));
                cd.setTenCD(rs.getString("tenCD"));
                cd.setGia(rs.getFloat("donGia"));
                cd.setSoLuong(rs.getInt("soLuong"));
                cd.setSanPham(sp);
                cn = new CongNhan(rs.getString("maCN"));
                cn.setTenCN(rs.getString("hoTen"));
                cn.setThamNien(rs.getDate("ngayVaoLam").toLocalDate());
                bangPC = new BangPhanCong(rs.getString("maPC"));
                bangPC.setCongDoan(cd);
                bangPC.setCongNhan(cn);
                ngayLV = new NgayLamViec(rs.getDate("ngayLam").toLocalDate(), rs.getBoolean("loaiNgay"));
                bangDD = new BangDiemDanhCN(rs.getString("maCCCN"), rs.getInt("coMat"), rs.getFloat("phatDD"), "", bangPC, ngayLV);
                maCCSP = rs.getString("maCCSP");
                if (maCCSP == null) {
                    maCCSP = "0";
                }
                bangCCSP = new BangChamCongSP(maCCSP, rs.getInt("caLam"), rs.getInt("soLuongSP"), rs.getFloat("phatCC"), "", bangDD);
                dsCCSP.add(bangCCSP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCCSP;
    }

    public boolean ktraCoTangCa(String ma, LocalDate ngayLam) {
        Connection con = null;
        PreparedStatement stm = null;
        int i = 0;
        try {
            ConnectDB.getInstance().connect();
            con = ConnectDB.getConnection();
            stm = con.prepareStatement("Select soCa=COUNT(*)\n"
                    + "	From BangPhanCong PC, CongNhan CN, BangDiemDanhCN DD, BangChamCongSP CC\n"
                    + "	Where CN.maCN = PC.maCN AND PC.maPC = DD.maPC AND DD.maCCCN = CC.maCCCN\n"
                    + "		AND DD.ngayLam=? AND CN.maCN=?");
            stm.setDate(1, Date.valueOf(ngayLam));
            stm.setString(2, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                i = rs.getInt("soCa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i >= 3;
    }

}
