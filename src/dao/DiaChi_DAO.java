/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import connectDB.ConnectDB;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class DiaChi_DAO {
    public ArrayList<String> getTinhTP(){
	Connection con = null;
	ArrayList<String> listTinh = new ArrayList<>();
	Statement stm;
	try {
            ConnectDB.getInstance().connect();
            con =ConnectDB.getConnection();
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select tinhTP from DiaChi" 
                    + " group by tinhTP");
            while (rs.next()) {
		listTinh.add(rs.getString("tinhTP").trim());
            }
	con.close();
	} catch (SQLException e) {
            e.printStackTrace();
        }
        return listTinh;
    }
    public  ArrayList<String> getQuanHuyen(String tinhTP) {
	Connection con = null;
	PreparedStatement stm;
	ArrayList<String> listQuanHuyen = new ArrayList<>();
	try {
            ConnectDB.getInstance().connect();
            con =ConnectDB.getConnection();
            stm = con.prepareStatement("select quanHuyen from DiaChi" 
                    + " where tinhTP = ?"
                    + " group by quanHuyen");
            stm.setString(1, tinhTP);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
		listQuanHuyen.add(rs.getString("quanHuyen").trim());
            }
            con.close();
	} catch (SQLException e) {
            return null;
	}
	return listQuanHuyen;	
    }
    public  ArrayList<String> getXaPhuong(String tinhTP, String quanHuyen) {
	Connection con = null;
	PreparedStatement stm;
	ArrayList<String> listPhuong = new ArrayList<>();
	try {
            ConnectDB.getInstance().connect();
            con =ConnectDB.getConnection();
            stm = con.prepareStatement("select xaPhuongTT from DiaChi" 
                    + " where tinhTP = ?"
                    + " and quanHuyen = ?");
            stm.setString(1, tinhTP);
            stm.setString(2, quanHuyen);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
		listPhuong.add(rs.getString("xaPhuongTT").trim());
            }
            con.close();
	} catch (SQLException e) {
            return null;
	}
	return listPhuong;	
    }
}

