/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.CongDoan;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Asus
 */
public class CongDoan_DAO {

    public ArrayList<CongDoan> getAllCongDoan() {
        ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from CongDoan");
            CongDoan congDoan = null;
            while (rs.next()) {
                congDoan = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"), rs.getFloat("donGia"), rs.getInt("soLuong"), rs.getInt("thuTu"), rs.getBoolean("trangThai"), new SanPham(rs.getString("maSP")));
                dsCongDoan.add(congDoan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongDoan;
    }

    public ArrayList<CongDoan> getAllCongDoanChuaHT(String maSP) {
        ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from CongDoan Where maSP = ? and trangThai = 0");
            stm.setString(1, maSP);
            ResultSet rs = stm.executeQuery();
            CongDoan congDoan = null;
            while (rs.next()) {
                congDoan = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"), rs.getFloat("donGia"), rs.getInt("soLuong"), rs.getInt("thuTu"), rs.getBoolean("trangThai"), new SanPham(rs.getString("maSP")));
                dsCongDoan.add(congDoan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongDoan;
    }

    public ArrayList<CongDoan> getAllCongDoanTheoMaSP(String maSP) {
        ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select * from CongDoan Where maSP = ?");
            stm.setString(1, maSP);
            ResultSet rs = stm.executeQuery();
            CongDoan congDoan = null;
            while (rs.next()) {
                congDoan = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"), rs.getFloat("donGia"), rs.getInt("soLuong"), rs.getInt("thuTu"), rs.getBoolean("trangThai"), new SanPham(rs.getString("maSP")));
                dsCongDoan.add(congDoan);
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCongDoan;
    }

    public int getSLSPDaLamTheoMaCD(String maCD) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Select sl=SUM(CC.soLuongSP) \n"
                    + "	From CongDoan CD,BangPhanCong PC,BangDiemDanhCN DD, BangChamCongSP CC\n"
                    + "	where CD.maCD = ? And CD.maCD = PC.maCD AND PC.maPC = DD.maPC\n"
                    + "		AND DD.maCCCN = CC.maCCCN");
            stm.setString(1, maCD);
            ResultSet rs = stm.executeQuery();
            CongDoan congDoan = null;
            while (rs.next()) {
                n = rs.getInt("sl");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public int getSLSPChuaHoanThanhTheoMaCD(String maCD) {
        int n = 0;
        int sl = getSLSPDaLamTheoMaCD(maCD);
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Select soLuong From CongDoan CD where CD.maCD =?");
            stm.setString(1, maCD);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt("soLuong");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n - sl;
    }

    public int getSLSPHoanThanhTheoMaCD(String maCD) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Select sl=SUM(CC.soLuongSP)\n"
                    + "                From CongDoan CD,BangPhanCong PC,BangDiemDanhCN DD, BangChamCongSP CC\n"
                    + "            where CD.maCD =? And CD.maCD = PC.maCD AND PC.maPC = DD.maPC\n"
                    + "           AND DD.maCCCN = CC.maCCCN\n"
                    + "          Group by CD.maCD,soLuong");
            stm.setString(1, maCD);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n = rs.getInt("sl");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public boolean capNhatTrangThaiCD(String maCD, boolean b) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Update CongDoan\n"
                    + "Set trangThai = ?\n"
                    + "Where maCD = ?");
            stm.setBoolean(1, b);
            stm.setString(2, maCD);
            n = stm.executeUpdate();
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public double getAllTongGiaTheoMaSP(String maSP) {
        double gia = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("select Tong=Sum(donGia) from CongDoan Where maSP = ?");
            stm.setString(1, maSP);
            ResultSet rs = stm.executeQuery();
            CongDoan congDoan = null;
            while (rs.next()) {
                gia = rs.getDouble("Tong");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gia;
    }

    public boolean themCongDoan(CongDoan congDoan) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Insert CongDoan Values (?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, congDoan.getMaCD());
            statement.setString(2, congDoan.getTenCD());
            statement.setInt(3, congDoan.getSoLuong());
            statement.setDouble(4, congDoan.getGia());
            statement.setInt(5, congDoan.getThuTu());
            statement.setBoolean(6, congDoan.isTrangThai());
            statement.setString(7, congDoan.getSanPham().getMaSP());
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaCongDoan(CongDoan congDoan) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Update CongDoan\n"
                    + "Set tenCD = ?,soLuong=?,donGia=?,thuTu=?\n"
                    + "Where maCD=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, congDoan.getTenCD());
            statement.setInt(2, congDoan.getSoLuong());
            statement.setDouble(3, congDoan.getGia());
            statement.setInt(4, congDoan.getThuTu());
            statement.setString(5, congDoan.getMaCD());
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean suaSoLuongCongDoan(String maSP, int soLuong, boolean flag) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Update CongDoan \n"
                    + "Set soLuong=?,trangThai = ?\n"
                    + "Where maSP = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, soLuong);
            statement.setBoolean(2, flag);
            statement.setString(3, maSP);
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean xoaCongDoan(String maCD) {
        int n = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "Delete CongDoan Where maCD = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maCD);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public CongDoan timCongDoan(String maCD) {
        CongDoan congDoan = null;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from CongDoan Where maSP = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, maCD);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                congDoan = new CongDoan(rs.getString("maCD"), rs.getString("tenCD"), rs.getFloat("donGia"), rs.getInt("soLuong"), rs.getInt("thuTu"), rs.getBoolean("trangThai"), new SanPham(rs.getString("maSP")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return congDoan;
    }

    public String getMaNVCuoi(String maSP) {
        String maCD = "";
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Select maCD from CongDoan Where maSP = ?");
            stm.setString(1, maSP);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                maCD = rs.getString("maCD");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maCD;
    }

    public int getThuTuCuoi(String maSP) {
        int thuTu = 0;
        try {
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = con.prepareStatement("Select thuTu=MAX(thuTu) from CongDoan Where maSP = ?");
            stm.setString(1, maSP);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                thuTu = rs.getInt("thuTu");
            }
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thuTu == 0 ? 1 : thuTu + 1;
    }

}
