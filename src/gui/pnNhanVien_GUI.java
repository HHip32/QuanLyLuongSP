/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.DiaChi_DAO;
import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.DiaChi;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class pnNhanVien_GUI extends javax.swing.JPanel {

    /**
     * Creates new form pnNhanVien_GUI
     */
    private NhanVien user;

    private DefaultTableModel model;
    private String filename = null;
    private byte[] person_img = null;
    private ArrayList<NhanVien> listNhanVien;
    private NhanVien_DAO nhanVien_DAO;
    private DiaChi_DAO diaChi_DAO;
    private ArrayList<String> tinhTP;

    public pnNhanVien_GUI() {
        nhanVien_DAO = new NhanVien_DAO();
        diaChi_DAO = new DiaChi_DAO();
        
        listNhanVien = new ArrayList<NhanVien>();
        tinhTP = new ArrayList<String>();

        listNhanVien = nhanVien_DAO.getAllNhanVien();
        initComponents();
        user = MenuChinh.getUser();
        if (!user.isChucVu()) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            loadTableArrayList(nhanVien_DAO.timNhanVienTheoMa(user.getMaNV()));
        } else {
            clearTable();
            loadTableArrayList(nhanVien_DAO.getAllNhanVienConLam());
        }
        table.setRowHeight(20);
//        model = (DefaultTableModel) table.getModel();
//        try {
//            Thread.sleep(35);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(pnNhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        tinhTP = diaChi_DAO.getTinhTP();
//        for (String dc : tinhTP) {
//            cboTinhQQ.addItem(dc);
//            cboTinhDC.addItem(dc);
//        }
    }

    private void moKhoaControls(boolean b) {
        btnThem.setEnabled(b);
        btnXoa.setEnabled(b);
        btnSua.setEnabled(b);
        btnLuu.setEnabled(b);
        btnXoaTrang.setEnabled(b);
        btnThoat.setEnabled(b);
    }

    private void moKhoaTextfields(boolean b) {
        txtHoTen.setEditable(b);
        cboGioiTinh.setEnabled(b);
        txtCMND.setEditable(b);
        txtLuongCanBan.setEditable(b);
        txtSDT.setEditable(b);
        dateChooserNgaySinh.setEnabled(b);
        txtPhuCap.setEditable(b);
        dateChooserNgayVaoLam.setEnabled(b);
        cboTrinhDo.setEnabled(b);
        cboChucVu.setEnabled(b);
        chkBHXH.setEnabled(b);
        txtEmail.setEnabled(b);
        cboTinhDC.setEnabled(b);
        cboTinhQQ.setEnabled(b);
        btnChonAnh.setEnabled(b);
    }
    //ClearTable

    public void clearTable() {
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();
        model1.setRowCount(0);
    }

    //LoadTable
    public void loadTable() {
        listNhanVien = nhanVien_DAO.getAllNhanVien();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (NhanVien nv : listNhanVien) {
            String gt = "";
            if (nv.isGioiTinh()) {
                gt += "Nam";
            } else {
                gt += "Nữ";
            }
            String bHXH = "";
            if (nv.isbHXH()) {
                bHXH += "Có tham gia";
            } else {
                bHXH += "Không tham gia";
            }
//            String diaChi_QQ = "";
//            diaChi_QQ += nv.getQueQuan().getXaPhuongTT() + "-" + nv.getQueQuan().getQuanHuyen() + "-" + nv.getQueQuan().getTinhTP();
//            String diaChi_DCLH = "";
//            diaChi_DCLH += nv.getDiaChiLienHe().getXaPhuongTT() + "-" + nv.getDiaChiLienHe().getQuanHuyen() + "-" + nv.getDiaChiLienHe().getTinhTP();
            model.addRow(new Object[]{nv.getMaNV(), nv.getTenNV(), gt, nv.getHsLuong(), nv.getSoDT(), dateFormat.format(nv.getNgaySinh()), nv.getLuongCB(), dateFormat.format(nv.getNgayVaoLam()), bHXH, nv.getQueQuan().getTinhTP(), nv.getDiaChiLienHe().getTinhTP()});
        }
    }

    public void loadTableArrayList(ArrayList<NhanVien> listNhanVien) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (NhanVien nv : listNhanVien) {
            String gt = "";
            if (nv.isGioiTinh()) {
                gt += "Nam";
            } else {
                gt += "Nữ";
            }
            String bHXH = "";
            if (nv.isbHXH()) {
                bHXH += "Có tham gia";
            } else {
                bHXH += "Không tham gia";
            }

//            String diaChi_QQ = "";
//            diaChi_QQ += nv.getQueQuan().getXaPhuongTT() + "-" + nv.getQueQuan().getQuanHuyen() + "-" + nv.getQueQuan().getTinhTP();
//            String diaChi_DCLH = "";
//            diaChi_DCLH += nv.getDiaChiLienHe().getXaPhuongTT() + "-" + nv.getDiaChiLienHe().getQuanHuyen() + "-" + nv.getDiaChiLienHe().getTinhTP();
            model.addRow(new Object[]{nv.getMaNV(), nv.getTenNV(), gt, nv.getHsLuong(), nv.getSoDT(), dateFormat.format(nv.getNgaySinh()), nv.getLuongCB(), dateFormat.format(nv.getNgayVaoLam()), bHXH, nv.getQueQuan().getTinhTP(), nv.getDiaChiLienHe().getTinhTP()});
        }
    }

    private void napDLVaoTextField() {
//        int n = table.getSelectedRow();
//        if (n == -1) {
//            return;
//        }
//        txtMaNV.setText(model.getValueAt(n, 0).toString());
//        txtHoTen.setText(nhanVien_DAO.getAllNhanVien().get(n).getTenNV());
//        cboGioiTinh.setSelectedItem("model.getValueAt(n, 2).toString()");
//        txtCMND.setText(nhanVien_DAO.getAllNhanVien().get(n).getcMND());
//        txtSDT.setText(model.getValueAt(n, 4).toString());
//        if (nhanVien_DAO.getAllNhanVien().get(n).getTrinhDo() == 1) {
//            cboTrinhDo.setSelectedIndex(0);
//        } else if (nhanVien_DAO.getAllNhanVien().get(n).getTrinhDo() == 2) {
//            cboTrinhDo.setSelectedIndex(1);
//        } else {
//            cboTrinhDo.setSelectedIndex(2);
//        }
//
//        txtPhuCap.setText(String.valueOf(nhanVien_DAO.getAllNhanVien().get(n).getPhuCap()));
//        txtEmail.setText(nhanVien_DAO.getAllNhanVien().get(n).getEmail());
//        txtLuongThamNien.setText(String.valueOf(nhanVien_DAO.getAllNhanVien().get(n).getLuongThamNien()));
//        Calendar cal = Calendar.getInstance();
//        dateChooserNgaySinh.setEnabled(true);
//        try {
//            cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(model.getValueAt(n, 5).toString()));
//            dateChooserNgaySinh.setSelectedDate(cal);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        dateChooserNgaySinh.setEnabled(false);
//        txtLuongCanBan.setText(String.valueOf(nhanVien_DAO.getAllNhanVien().get(n).getLuongCB()));
//        dateChooserNgayVaoLam.setEnabled(true);
//        try {
//            cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(model.getValueAt(n, 7).toString()));
//            dateChooserNgayVaoLam.setSelectedDate(cal);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        dateChooserNgayVaoLam.setEnabled(false);
//        if (model.getValueAt(n, 8).toString().equalsIgnoreCase("Có tham gia")) {
//            chkBHXH.setSelected(true);
//        } else {
//            chkBHXH.setSelected(false);
//        }
//        cboTinhQQ.setSelectedItem(nhanVien_DAO.getAllNhanVien().get(n).getQueQuan().getTinhTP());
//        cboHuyenQQ.setSelectedItem(nhanVien_DAO.getAllNhanVien().get(n).getQueQuan().getQuanHuyen());
//        cboXaQQ.setSelectedItem(nhanVien_DAO.getAllNhanVien().get(n).getQueQuan().getXaPhuongTT());
//        cboTinhDC.setSelectedItem(nhanVien_DAO.getAllNhanVien().get(n).getDiaChiLienHe().getTinhTP());
//        cboHuyenDC.setSelectedItem(nhanVien_DAO.getAllNhanVien().get(n).getDiaChiLienHe().getQuanHuyen());
//        cboXaDC.setSelectedItem(nhanVien_DAO.getAllNhanVien().get(n).getDiaChiLienHe().getXaPhuongTT());
//        cboTinhDC.setEnabled(false);
//        cboTinhQQ.setEnabled(false);
//        cboHuyenDC.setEnabled(false);
//        cboHuyenQQ.setEnabled(false);
//        cboXaDC.setEnabled(false);
//        cboXaQQ.setEnabled(false);
//        if (nhanVien_DAO.getAllNhanVien().get(n).getHinhAnh() == null) {
//            lbl_img.setIcon(new ImageIcon());
//        } else {
//            byte[] imgg = (nhanVien_DAO.getAllNhanVien().get(n).getHinhAnh());
//            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgg).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
//            lbl_img.setIcon(imageIcon);
//        }
        int n = table.getSelectedRow();
        if (n == -1) {
            return;
        }
        if (tinhTP.isEmpty()) {
            tinhTP = diaChi_DAO.getTinhTP();
            for (String dc : tinhTP) {
                cboTinhQQ.addItem(dc);
                cboTinhDC.addItem(dc);
            }
        }
        ArrayList<NhanVien> listNhanVien1 = new ArrayList<>();
        listNhanVien1 = nhanVien_DAO.timNhanVienTheoMa(model.getValueAt(n, 0).toString());
        for (NhanVien nv : listNhanVien1) {
            txtMaNV.setText(model.getValueAt(n, 0).toString());
            txtHoTen.setText(nv.getTenNV());
            cboGioiTinh.setSelectedItem(model.getValueAt(n, 2).toString());
            txtCMND.setText(nv.getcMND());
            txtSDT.setText(model.getValueAt(n, 4).toString());
            if (nv.getTrinhDo() == 1) {
                cboTrinhDo.setSelectedIndex(0);
            } else if (nv.getTrinhDo() == 2) {
                cboTrinhDo.setSelectedIndex(1);
            } else {
                cboTrinhDo.setSelectedIndex(2);
            }
            if(nv.isChucVu()){
                cboChucVu.setSelectedIndex(1);
            }else{
                cboChucVu.setSelectedIndex(0);
            }
            txtPhuCap.setText(String.valueOf((int) nv.getPhuCap()));
            txtEmail.setText(nv.getEmail());
            txtLuongThamNien.setText(String.valueOf(nv.getLuongThamNien()));
            Calendar cal = Calendar.getInstance();
            dateChooserNgaySinh.setEnabled(true);
            try {
                cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(model.getValueAt(n, 5).toString()));
                dateChooserNgaySinh.setSelectedDate(cal);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dateChooserNgaySinh.setEnabled(false);
            txtLuongCanBan.setText(String.valueOf((int) nv.getLuongCB()));
            txtHeSoLuong.setText(nv.getHsLuong() + "");
            dateChooserNgayVaoLam.setEnabled(true);
            try {
                cal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(model.getValueAt(n, 7).toString()));
                dateChooserNgayVaoLam.setSelectedDate(cal);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dateChooserNgayVaoLam.setEnabled(false);
            if (model.getValueAt(n, 8).toString().equalsIgnoreCase("Có tham gia")) {
                chkBHXH.setSelected(true);
            } else {
                chkBHXH.setSelected(false);
            }
            cboTinhQQ.setSelectedItem(nv.getQueQuan().getTinhTP());
            cboHuyenQQ.setSelectedItem(nv.getQueQuan().getQuanHuyen());
            cboXaQQ.setSelectedItem(nv.getQueQuan().getXaPhuongTT());
            cboTinhDC.setSelectedItem(nv.getDiaChiLienHe().getTinhTP());
            cboHuyenDC.setSelectedItem(nv.getDiaChiLienHe().getQuanHuyen());
            cboXaDC.setSelectedItem(nv.getDiaChiLienHe().getXaPhuongTT());
            cboTinhDC.setEnabled(false);
            cboTinhQQ.setEnabled(false);
            cboHuyenDC.setEnabled(false);
            cboHuyenQQ.setEnabled(false);
            cboXaDC.setEnabled(false);
            cboXaQQ.setEnabled(false);
            if (nv.getHinhAnh() == null) {
                lbl_img.setIcon(new ImageIcon());
            } else {
                byte[] imgg = (nv.getHinhAnh());
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgg).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
                lbl_img.setIcon(imageIcon);
            }
        }

    }

    public String taoMaMoi() {
        String maCuoi = null;
        try {
            maCuoi = nhanVien_DAO.getMaNVCuoi().trim().substring(2);
        } catch (Exception e) {
            if (maCuoi == null) {
                return "NV001";
            }
        }

        int ma = Integer.parseInt(maCuoi);
        String maMoi = String.format("NV%03d", ma + 1);
        return maMoi;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Date convertToDateViaInstant(LocalDate date) {
        return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private void sh_Mes(String mes, JTextField txt) {
        JOptionPane.showMessageDialog(null, mes);
        if (txt == null) {
            return;
        }
        txt.requestFocus();
    }

    public boolean kiemTraDuLieu() {
        if (txtHoTen.getText().trim().equals("")) {
            sh_Mes("Mời nhập họ tên nhân viên!", txtHoTen);
            txtHoTen.requestFocus();
            return false;
        }
        if (!(txtHoTen.getText().trim().matches("\\p{L}+(\\s\\p{L}+)+"))) {
            sh_Mes("Tên nhân viên không được có kí tự đặc biệt và tối thiểu 2 từ!", txtHoTen);
            txtHoTen.requestFocus();
            return false;
        }
        if (!(txtCMND.getText().trim().matches("[0-9]{9}|[0-9]{12}"))) {
            sh_Mes("CMND phải 9 số hoặc 12 số!", txtCMND);
            txtCMND.requestFocus();
            return false;
        }
        if (Double.parseDouble(txtLuongCanBan.getText().trim()) < 3500000) {
            sh_Mes("Lương căn bản phải lớn hơn hoặc bằng 3500000!", txtLuongCanBan);
            txtLuongCanBan.requestFocus();
            return false;
        }
        if (!(txtSDT.getText().trim().matches("0[1-9][0-9]{8}|0[1-9][0-9]{9}"))) {
            sh_Mes("Số điện thoại phải có 10 số hoặc 11 số và bắt đầu bằng số 0 và số thứ 2 khác 0!", txtSDT);
            txtSDT.requestFocus();
            return false;
        }

        LocalDate ngaySinh = convertToLocalDateViaInstant(dateChooserNgaySinh.getSelectedDate().getTime());
        if (LocalDate.now().getYear() - ngaySinh.getYear() < 18) {
            sh_Mes("nhân viên phải đủ 18 tuổi trỡ lên!", txtSDT);
            return false;
        }

        LocalDate ngayVaoLam = convertToLocalDateViaInstant(dateChooserNgayVaoLam.getSelectedDate().getTime());
        if (LocalDate.now().isBefore(ngayVaoLam)) {
            sh_Mes("Ngày vào làm phải trước hoặc bằng ngày hiện tại!", null);
            return false;
        }
        if (txtPhuCap.getText().equals("")) {
            sh_Mes("Phụ cấp không được rỗng!", txtPhuCap);
            txtPhuCap.requestFocus();
            return false;
        }
        if (Integer.parseInt(txtPhuCap.getText().trim()) < 0) {
            sh_Mes("Phụ cấp phải lớn hơn hoặc bằng 0!", txtPhuCap);
            txtPhuCap.requestFocus();
            return false;
        }
        if (txtEmail.getText().trim().equals("")) {
            sh_Mes("Email không được để trống!", txtEmail);
            txtEmail.requestFocus();
            return false;
        }
        if (!(txtEmail.getText().trim().matches("[A-Za-z0-9]{3,}@gmail.com"))) {
            sh_Mes("Tên email phải theo cú pháp tên(tối thiểu 3 kí tự)@gmail.com", txtEmail);
            txtEmail.requestFocus();
            return false;
        }
        if (cboTinhDC.getSelectedIndex() == 0) {
            sh_Mes("Địa chỉ không được để trống! ", null);
            return false;
        }
        if (cboHuyenDC.getSelectedIndex() == 0) {
            sh_Mes("Địa chỉ không được để trống!", null);
            return false;
        }
        if (cboXaDC.getSelectedIndex() == 0) {
            sh_Mes("Địa chỉ không được để trống!", null);
            return false;
        }
        if (cboTinhQQ.getSelectedIndex() == 0) {
            sh_Mes("Quê quán không được để trống! ", null);
            return false;
        }
        if (cboHuyenQQ.getSelectedIndex() == 0) {
            sh_Mes("Quê quán không được để trống!", null);
            return false;
        }
        if (cboXaQQ.getSelectedIndex() == 0) {
            sh_Mes("Quê quán không được để trống!", null);
            return false;
        }
        return true;
    }

    private void moKhoaDCQQ(boolean b) {
        cboHuyenQQ.setEnabled(b);
        cboXaQQ.setEnabled(b);
        cboHuyenDC.setEnabled(b);
        cboXaDC.setEnabled(b);
    }

    public void themNhanVien() {
        String ma = txtMaNV.getText().trim();
        String hoTen = txtHoTen.getText().trim();
        Boolean gioiTinh;
        if (cboGioiTinh.getSelectedItem().equals("Nam")) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }
        String tinhQQ = cboTinhQQ.getSelectedItem().toString().trim();
        String huyenQQ = cboHuyenQQ.getSelectedItem().toString().trim();
        String xaQQ = cboXaQQ.getSelectedItem().toString().trim();
        String tinhDC = cboTinhDC.getSelectedItem().toString().trim();
        String huyenDC = cboHuyenDC.getSelectedItem().toString().trim();
        String xaDC = cboXaDC.getSelectedItem().toString().trim();
        String soDT = txtSDT.getText().trim();
        String cmnd = txtCMND.getText().trim();
        String matKhau = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dNgaySinh = dateChooserNgaySinh.getSelectedDate().getTime();
        LocalDate ngaySinh = dNgaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String email = txtEmail.getText().trim();
        Date dNgayVaoLam = dateChooserNgayVaoLam.getSelectedDate().getTime();
        LocalDate ngayVaoLam = dNgayVaoLam.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        boolean bhxh = chkBHXH.isSelected();
//        if (chkBHXH.isSelected()) {
//            bhxh = true;
//        } else {
//            bhxh = false;
//        }
        double luongCB = Double.parseDouble(txtLuongCanBan.getText().trim());
        float hsLuong = Float.parseFloat(txtHeSoLuong.getText().trim());
        int trinhDo = cboTrinhDo.getSelectedIndex() + 1;
//        if (cboTrinhDo.getSelectedIndex() == 0) {
//            trinhDo = 1;
//        } else if (cboTrinhDo.getSelectedIndex() == 1) {
//            trinhDo = 2;
//        } else if (cboTrinhDo.getSelectedIndex() == 2) {
//            trinhDo = 3;
//        }
        boolean chucVu;
        if (cboChucVu.getSelectedItem().equals("Nhân viên")) {
            chucVu = true;
        } else {
            chucVu = false;
        }
        double phuCap = Double.parseDouble(txtPhuCap.getText().trim());
        DiaChi dc_QQ = new DiaChi(tinhQQ, huyenQQ, xaQQ);
        DiaChi dc_LH = new DiaChi(tinhDC, huyenDC, xaDC);
//        int luongThamNien = Period.between(ngayVaoLam, LocalDate.now()).getYears();;
//        int ltn;
//        if (luongThamNien < 5) {
//            ltn = 0;
//        } else if (luongThamNien <= 20) {
//            ltn = luongThamNien;
//        } else {
//            ltn = 20;
//        }
        NhanVien nv = new NhanVien(ma, hoTen, gioiTinh, dc_QQ, dc_LH, soDT, cmnd, matKhau, ngaySinh, person_img, email, ngayVaoLam, bhxh, luongCB, hsLuong, trinhDo, chucVu, phuCap, true);

        if (nhanVien_DAO.themNhanVien(nv)) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
            clearTable();
            loadTableArrayList(nhanVien_DAO.getAllNhanVienConLam());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
        }
    }

    public void xoaNhanVien() {
        int row = table.getSelectedRow();
        if (row == -1) {
            sh_Mes("Chọn nhân viên cần xóa!", null);
        } else {
            String maNV = table.getValueAt(row, 0).toString();
            int op = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "delete", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                if (maNV.equals(user.getMaNV())) {
                    sh_Mes("Không thể xóa chính mình!", null);
                } else {
                    if (nhanVien_DAO.xoaNhanVien(maNV)) {
                        sh_Mes("Xóa nhân viên thành công!", null);
                        clearTable();
                        loadTableArrayList(nhanVien_DAO.getAllNhanVienConLam());
                    } else {
                        sh_Mes("Xóa nhân viên thất bại!", null);
                        return;
                    }
                }

            }
        }
    }

    public void suaThongTinNhanVien() {
        int op = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin nhân viên?", "update", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            String ma = txtMaNV.getText().trim();
            String hoTen = txtHoTen.getText().trim();
            Boolean gioiTinh;
            if (cboGioiTinh.getSelectedItem().equals("Nam")) {
                gioiTinh = true;
            } else {
                gioiTinh = false;
            }
            String tinhQQ = cboTinhQQ.getSelectedItem().toString().trim();
            String huyenQQ = cboHuyenQQ.getSelectedItem().toString().trim();
            String xaQQ = cboXaQQ.getSelectedItem().toString().trim();
            String tinhDC = cboTinhDC.getSelectedItem().toString().trim();
            String huyenDC = cboHuyenDC.getSelectedItem().toString().trim();
            String xaDC = cboXaDC.getSelectedItem().toString().trim();
            String soDT = txtSDT.getText().trim();
            String cmnd = txtCMND.getText().trim();
            String matKhau = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dNgaySinh = dateChooserNgaySinh.getSelectedDate().getTime();
            LocalDate ngaySinh = dNgaySinh.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String email = txtEmail.getText().trim();
            Date dNgayVaoLam = dateChooserNgayVaoLam.getSelectedDate().getTime();
            LocalDate ngayVaoLam = dNgayVaoLam.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            boolean bhxh;
            if (chkBHXH.isSelected()) {
                bhxh = true;
            } else {
                bhxh = false;
            }
            double luongCB = Double.parseDouble(txtLuongCanBan.getText().trim());
            float hsLuong = Float.parseFloat(txtHeSoLuong.getText().trim());
            int trinhDo = 0;
            if (cboTrinhDo.getSelectedIndex() == 0) {
                trinhDo = 1;
            } else if (cboTrinhDo.getSelectedIndex() == 1) {
                trinhDo = 2;
            } else if (cboTrinhDo.getSelectedIndex() == 2) {
                trinhDo = 3;
            }
            boolean chucVu;
            if (cboChucVu.getSelectedItem().equals("Nhân viên")) {
                chucVu = true;
            } else {
                chucVu = false;
            }
            double phuCap = Double.parseDouble(txtPhuCap.getText().trim());
            DiaChi dc_QQ = new DiaChi(tinhQQ, huyenQQ, xaQQ);
            DiaChi dc_LH = new DiaChi(tinhDC, huyenDC, xaDC);
//            int luongThamNien = Period.between(ngayVaoLam, LocalDate.now()).getYears();;
//            int ltn;
//            if (luongThamNien < 5) {
//                ltn = 0;
//            } else if (luongThamNien <= 20) {
//                ltn = luongThamNien;
//            } else {
//                ltn = 20;
//            }
            NhanVien nv = new NhanVien(ma, hoTen, gioiTinh, dc_QQ, dc_LH, soDT, cmnd, matKhau, ngaySinh, person_img, email, ngayVaoLam, bhxh, luongCB, hsLuong, trinhDo, chucVu, phuCap);
            if (nhanVien_DAO.suaNhanVien(nv)) {
                sh_Mes("Sửa thông tin nhân viên thành công!", null);
                clearTable();
                loadTableArrayList(nhanVien_DAO.getAllNhanVienConLam());
            } else {
                sh_Mes("Sửa thông tin nhân viên thất bại!", null);
            }
        }
    }

    public void timNhanVienTheoMa() {
        clearTable();
        String maNV = txtTim.getText().trim();
        ArrayList<NhanVien> listNhanVien = null;
        listNhanVien = nhanVien_DAO.timNhanVienTheoMa(maNV);
        loadTableArrayList(listNhanVien);
    }

    public void timNhanVienTheoTen() {
        clearTable();
        loadTableArrayList(nhanVien_DAO.timNhanVienTheoTen(txtTim.getText().trim()));
    }

    public void clearTextFields() {
        txtHoTen.setText("");
        cboGioiTinh.setSelectedIndex(0);
        txtCMND.setText("");
        cboChucVu.setSelectedIndex(0);
        txtSDT.setText("");
        dateChooserNgaySinh.setEnabled(true);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new Date());
            dateChooserNgaySinh.setSelectedDate(cal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtPhuCap.setText("");
        dateChooserNgayVaoLam.setEnabled(true);
        try {
            cal.setTime(new Date());
            dateChooserNgayVaoLam.setSelectedDate(cal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtLuongCanBan.setText("3500000");
        cboTrinhDo.setSelectedIndex(0);
        txtEmail.setText("");
        txtLuongThamNien.setText("");
        chkBHXH.setSelected(true);
        cboTinhDC.setSelectedIndex(0);
        cboHuyenDC.setSelectedIndex(0);
        cboXaDC.setSelectedIndex(0);
        cboTinhQQ.setSelectedIndex(0);
        cboHuyenQQ.setSelectedIndex(0);
        cboXaQQ.setSelectedIndex(0);
        cboHuyenDC.setEnabled(false);
        cboXaDC.setEnabled(false);
        cboHuyenQQ.setEnabled(false);
        cboXaQQ.setEnabled(false);
        lbl_img.setIcon(new ImageIcon());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelThongTin = new javax.swing.JPanel();
        btnChonAnh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLuongCanBan = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtPhuCap = new javax.swing.JTextField();
        cboTrinhDo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtHeSoLuong = new javax.swing.JTextField();
        cboChucVu = new javax.swing.JComboBox<>();
        chkBHXH = new javax.swing.JCheckBox();
        txtEmail = new javax.swing.JTextField();
        lbl_img = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtLuongThamNien = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        cboTinhQQ = new javax.swing.JComboBox<>();
        cboHuyenQQ = new javax.swing.JComboBox<>();
        cboXaQQ = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        cboTinhDC = new javax.swing.JComboBox<>();
        cboXaDC = new javax.swing.JComboBox<>();
        cboHuyenDC = new javax.swing.JComboBox<>();
        dateChooserNgaySinh = new datechooser.beans.DateChooserCombo();
        dateChooserNgayVaoLam = new datechooser.beans.DateChooserCombo();
        panelChucNang = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(209, 209, 209));

        panelThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));
        panelThongTin.setPreferredSize(new java.awt.Dimension(958, 290));

        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.setEnabled(false);
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setText("Mã nhân viên:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setText("Họ và tên:");

        txtHoTen.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setText("Giới tính:");

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel6.setText("CMND:");

        txtCMND.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel7.setText("Lương căn bản:");

        txtLuongCanBan.setEditable(false);
        txtLuongCanBan.setText("3500000");

        txtMaNV.setEditable(false);
        txtMaNV.setMinimumSize(new java.awt.Dimension(70, 22));
        txtMaNV.setPreferredSize(new java.awt.Dimension(200, 30));
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("Số điện thoại:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel9.setText("Ngày sinh:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel10.setText("Phụ cấp:");
        jLabel10.setPreferredSize(new java.awt.Dimension(76, 16));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel11.setText("Ngày vào làm:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel12.setText("Trình độ:");
        jLabel12.setPreferredSize(new java.awt.Dimension(76, 16));

        txtSDT.setEditable(false);

        txtPhuCap.setEditable(false);

        cboTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phổ Thông", "Cao Đẳng", "Đại Học" }));
        cboTrinhDo.setEnabled(false);
        cboTrinhDo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTrinhDoItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setText("Hệ số lương:");
        jLabel4.setPreferredSize(new java.awt.Dimension(95, 16));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setText("Chức vụ:");
        jLabel5.setPreferredSize(new java.awt.Dimension(95, 16));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel13.setText("BHXH:");
        jLabel13.setPreferredSize(new java.awt.Dimension(95, 16));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setText("Email:");
        jLabel14.setPreferredSize(new java.awt.Dimension(95, 16));

        txtHeSoLuong.setEditable(false);
        txtHeSoLuong.setText("1.86");

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lí" }));
        cboChucVu.setEnabled(false);

        chkBHXH.setSelected(true);
        chkBHXH.setText("Có tham gia");
        chkBHXH.setEnabled(false);

        txtEmail.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel16.setText("Lương thâm niên:");
        jLabel16.setPreferredSize(new java.awt.Dimension(72, 16));

        txtLuongThamNien.setEditable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quê quán"));
        jPanel1.setPreferredSize(new java.awt.Dimension(782, 53));

        cboTinhQQ.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboTinhQQ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tỉnh/Thành phố" }));
        cboTinhQQ.setEnabled(false);
        cboTinhQQ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhQQItemStateChanged(evt);
            }
        });

        cboHuyenQQ.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboHuyenQQ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quận/Huyện" }));
        cboHuyenQQ.setEnabled(false);
        cboHuyenQQ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHuyenQQItemStateChanged(evt);
            }
        });
        cboHuyenQQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHuyenQQActionPerformed(evt);
            }
        });

        cboXaQQ.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboXaQQ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xã/Phường" }));
        cboXaQQ.setEnabled(false);
        cboXaQQ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboXaQQItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(cboTinhQQ, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboHuyenQQ, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboXaQQ, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTinhQQ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHuyenQQ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboXaQQ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));
        jPanel2.setPreferredSize(new java.awt.Dimension(782, 53));

        cboTinhDC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboTinhDC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tỉnh/Thành phố" }));
        cboTinhDC.setEnabled(false);
        cboTinhDC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhDCItemStateChanged(evt);
            }
        });
        cboTinhDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTinhDCActionPerformed(evt);
            }
        });

        cboXaDC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboXaDC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Xã/Phường" }));
        cboXaDC.setEnabled(false);
        cboXaDC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboXaDCItemStateChanged(evt);
            }
        });

        cboHuyenDC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboHuyenDC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quận/Huyện" }));
        cboHuyenDC.setEnabled(false);
        cboHuyenDC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHuyenDCItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTinhDC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboXaDC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboHuyenDC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(cboTinhDC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboHuyenDC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboXaDC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        dateChooserNgaySinh.setCalendarPreferredSize(new java.awt.Dimension(400, 200));
        dateChooserNgaySinh.setEnabled(false);
        dateChooserNgaySinh.setLocale(new java.util.Locale("vi", "VN", ""));

        dateChooserNgayVaoLam.setCalendarPreferredSize(new java.awt.Dimension(400, 200));
        dateChooserNgayVaoLam.setEnabled(false);
        dateChooserNgayVaoLam.setLocale(new java.util.Locale("vi", "VN", ""));

        javax.swing.GroupLayout panelThongTinLayout = new javax.swing.GroupLayout(panelThongTin);
        panelThongTin.setLayout(panelThongTinLayout);
        panelThongTinLayout.setHorizontalGroup(
            panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinLayout.createSequentialGroup()
                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_img, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnChonAnh)))
                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThongTinLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel8))
                            .addGroup(panelThongTinLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongTinLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLuongCanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLuongThamNien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhuCap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelThongTinLayout.createSequentialGroup()
                                .addComponent(chkBHXH, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addComponent(dateChooserNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );
        panelThongTinLayout.setVerticalGroup(
            panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinLayout.createSequentialGroup()
                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLuongCanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLuongThamNien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(dateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(4, 4, 4)
                                .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel6)
                                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateChooserNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelThongTinLayout.createSequentialGroup()
                                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPhuCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel11))))
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelThongTinLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkBHXH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(panelThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_img, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnChonAnh)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        panelChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThem.setBackground(new java.awt.Color(255, 204, 204));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-user.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editing.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoaTrang.setBackground(new java.awt.Color(255, 204, 204));
        btnXoaTrang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        btnXoaTrang.setText("Xóa trắng");
        btnXoaTrang.setEnabled(false);
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(255, 204, 204));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Tìm kiếm:");

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(255, 204, 204));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/diskette.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setEnabled(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 204));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete-user.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setText("Trạng thái:");

        cboTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm", "Nghỉ việc" }));
        cboTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTrangThaiItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelChucNangLayout = new javax.swing.GroupLayout(panelChucNang);
        panelChucNang.setLayout(panelChucNangLayout);
        panelChucNangLayout.setHorizontalGroup(
            panelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaTrang)
                .addGap(18, 18, 18)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelChucNangLayout.setVerticalGroup(
            panelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChucNangLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTrang)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Quản lí nhân viên");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(547, 547, 547)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        String[] heads= {"Mã nhân viên", "Họ và tên", "Giới tính", "Hệ số lương", "SĐT", "Ngày sinh", "Lương căn bản", "Ngày vào làm", "BHXH", "Quê quán", "Địa chỉ"};
        model = new DefaultTableModel(heads, 0);
        table.setModel(model);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, 1244, Short.MAX_VALUE)
                    .addComponent(panelChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboTinhDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTinhDCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTinhDCActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        try {
            filename = f.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
            lbl_img.setIcon(imageIcon);
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int n; (n = fis.read(buf)) != -1;) {
                bos.write(buf, 0, n);
            }
            person_img = bos.toByteArray();
        } catch (Exception e) {
            sh_Mes("Chọn ảnh không thành công!", null);
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void cboTinhQQItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTinhQQItemStateChanged
        // TODO add your handling code here:
        cboHuyenQQ.setEnabled(true);
        for (int i = cboHuyenQQ.getItemCount() - 1; i > 0; i--) {
            cboHuyenQQ.removeItemAt(i);
        }
        ArrayList<String> listHuyen = diaChi_DAO.getQuanHuyen(cboTinhQQ.getSelectedItem().toString().trim());
        for (String huyen : listHuyen) {
            cboHuyenQQ.addItem(huyen);
        }
    }//GEN-LAST:event_cboTinhQQItemStateChanged

    private void cboHuyenQQItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHuyenQQItemStateChanged
        // TODO add your handling code here:
        cboXaQQ.setEnabled(true);
        for (int i = cboXaQQ.getItemCount() - 1; i > 0; i--) {
            cboXaQQ.removeItemAt(i);
        }
        ArrayList<String> listXa = diaChi_DAO.getXaPhuong(cboTinhQQ.getSelectedItem().toString().trim(), cboHuyenQQ.getSelectedItem().toString().trim());
        for (String xa : listXa) {
            cboXaQQ.addItem(xa);
        }
    }//GEN-LAST:event_cboHuyenQQItemStateChanged

    private void cboTinhDCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTinhDCItemStateChanged
        // TODO add your handling code here:
        cboHuyenDC.setEnabled(true);
        for (int i = cboHuyenDC.getItemCount() - 1; i > 0; i--) {
            cboHuyenDC.removeItemAt(i);
        }
        ArrayList<String> listHuyen = diaChi_DAO.getQuanHuyen(cboTinhDC.getSelectedItem().toString().trim());
        for (String huyen : listHuyen) {
            cboHuyenDC.addItem(huyen);
        }
    }//GEN-LAST:event_cboTinhDCItemStateChanged

    private void cboHuyenDCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHuyenDCItemStateChanged
        // TODO add your handling code here:
        cboXaDC.setEnabled(true);
        for (int i = cboXaDC.getItemCount() - 1; i > 0; i--) {
            cboXaDC.removeItemAt(i);
        }
        ArrayList<String> listXa = diaChi_DAO.getXaPhuong(cboTinhDC.getSelectedItem().toString(), cboHuyenDC.getSelectedItem().toString());
        for (String xa : listXa) {
            cboXaDC.addItem(xa);
        }
    }//GEN-LAST:event_cboHuyenDCItemStateChanged

    private void cboXaQQItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboXaQQItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cboXaQQItemStateChanged

    private void cboXaDCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboXaDCItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cboXaDCItemStateChanged


    private void cboTrinhDoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTrinhDoItemStateChanged
        // TODO add your handling code here:
        if (cboTrinhDo.getSelectedItem().equals(cboTrinhDo.getItemAt(0)))
            txtHeSoLuong.setText("1.86");
        else if (cboTrinhDo.getSelectedItem().equals(cboTrinhDo.getItemAt(1)))
            txtHeSoLuong.setText("2.08");
        else
            txtHeSoLuong.setText("2.34");
    }//GEN-LAST:event_cboTrinhDoItemStateChanged

    private void cboHuyenQQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHuyenQQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboHuyenQQActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        napDLVaoTextField();
    }//GEN-LAST:event_tableMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
//        timNhanVienTheoMa();
        timNhanVienTheoTen();
    }//GEN-LAST:event_txtTimKeyReleased

    private void cboTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTrangThaiItemStateChanged
        // TODO add your handling code here:
        if (cboTrangThai.getSelectedIndex() == 0) {
            clearTable();
            clearTextFields();
            txtMaNV.setText("");
            dateChooserNgayVaoLam.setEnabled(false);
            dateChooserNgaySinh.setEnabled(false);
            loadTableArrayList(nhanVien_DAO.getAllNhanVienConLam());
            moKhoaControls(true);
            btnLuu.setEnabled(false);
        } else {
            clearTable();
            clearTextFields();
            txtMaNV.setText("");
            dateChooserNgayVaoLam.setEnabled(false);
            dateChooserNgaySinh.setEnabled(false);
            loadTableArrayList(nhanVien_DAO.getAllNhanVienNghiLam());
            moKhoaControls(false);
        }
    }//GEN-LAST:event_cboTrangThaiItemStateChanged

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        // TODO add your handling code here:
        clearTextFields();
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaNhanVien();
        clearTextFields();
        txtMaNV.setText("");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (user.isChucVu()) {
            if (btnSua.getText().trim().equals("Sửa")) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    sh_Mes("Chọn công nhân cần sửa!", null);
                } else {
                    moKhoaTextfields(true);
                    moKhoaControls(false);
                    btnLuu.setEnabled(true);
                    btnSua.setEnabled(true);
                    btnXoaTrang.setEnabled(true);
                    btnSua.setText("Hủy");
                    cboHuyenDC.setEnabled(true);
                    cboHuyenQQ.setEnabled(true);
                    cboXaDC.setEnabled(true);
                    cboXaQQ.setEnabled(true);
                    if (tinhTP.isEmpty()) {
                        tinhTP = diaChi_DAO.getTinhTP();
                        for (String dc : tinhTP) {
                            cboTinhQQ.addItem(dc);
                            cboTinhDC.addItem(dc);
                        }
                    }
                }

            } else if (btnSua.getText().trim().equals("Hủy")) {
                moKhoaTextfields(false);
                moKhoaControls(true);
                btnLuu.setEnabled(false);
                btnXoaTrang.setEnabled(false);
                btnSua.setText("Sửa");
                cboHuyenDC.setEnabled(false);
                cboHuyenQQ.setEnabled(false);
                cboXaDC.setEnabled(false);
                cboXaQQ.setEnabled(false);

            }
        } else {
            if (btnSua.getText().trim().equals("Sửa")) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    sh_Mes("Chọn công nhân cần sửa!", null);
                } else {
                    moKhoaTextfields(true);
                    moKhoaControls(false);
                    btnLuu.setEnabled(true);
                    btnSua.setEnabled(true);
                    btnXoaTrang.setEnabled(true);
                    btnSua.setText("Hủy");
                    cboHuyenDC.setEnabled(true);
                    cboHuyenQQ.setEnabled(true);
                    cboXaDC.setEnabled(true);
                    cboXaQQ.setEnabled(true);
                    if (tinhTP.isEmpty()) {
                        tinhTP = diaChi_DAO.getTinhTP();
                        for (String dc : tinhTP) {
                            cboTinhQQ.addItem(dc);
                            cboTinhDC.addItem(dc);
                        }
                    }
                }

            } else if (btnSua.getText().trim().equals("Hủy")) {
                moKhoaTextfields(false);
                moKhoaControls(true);
                btnLuu.setEnabled(false);
                btnXoaTrang.setEnabled(false);
                btnThem.setEnabled(false);
                btnXoa.setEnabled(false);
                btnSua.setText("Sửa");
                cboHuyenDC.setEnabled(false);
                cboHuyenQQ.setEnabled(false);
                cboXaDC.setEnabled(false);
                cboXaQQ.setEnabled(false);

            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (btnThem.getText().trim().equalsIgnoreCase("Thêm")) {
            txtMaNV.setText(taoMaMoi());
            txtHoTen.requestFocus();
            moKhoaControls(false);
            moKhoaTextfields(true);
            clearTextFields();
            btnThem.setEnabled(true);
            btnLuu.setEnabled(true);
            btnThem.setText("Hủy");
            txtMaNV.setEditable(false);
            if (tinhTP.isEmpty()) {
                tinhTP = diaChi_DAO.getTinhTP();
                for (String dc : tinhTP) {
                    cboTinhQQ.addItem(dc);
                    cboTinhDC.addItem(dc);
                }
            }

        } else if (btnThem.getText().trim().equalsIgnoreCase("Hủy")) {
            txtMaNV.setText("");
            moKhoaTextfields(false);
            moKhoaControls(true);
            btnLuu.setEnabled(false);
            btnThem.setText("Thêm");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if (btnThem.getText().trim().equals("Hủy")) {
            if (kiemTraDuLieu()) {
                themNhanVien();
                moKhoaTextfields(false);
                clearTextFields();
                moKhoaControls(true);
                btnLuu.setEnabled(false);
                btnLuu.setText("Lưu(F5)");
                btnThem.setText("Thêm");
                txtMaNV.setText("");
            }
        } else if (btnSua.getText().trim().equals("Hủy")) {
            if (kiemTraDuLieu()) {
                suaThongTinNhanVien();
                moKhoaTextfields(false);
                clearTextFields();
                moKhoaControls(true);
                btnLuu.setEnabled(false);
                btnXoaTrang.setEnabled(false);
                btnLuu.setText("Lưu(F5)");
                btnSua.setText("Sửa");
                txtMaNV.setText("");
            }

        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        MenuChinh.getPnSlider().removeAll();
        MenuChinh.getPnSlider().repaint();
        MenuChinh.getPnSlider().revalidate();

        MenuChinh.getPnSlider().add(new pnManHinhChinh());
        MenuChinh.getPnSlider().repaint();
        MenuChinh.getPnSlider().revalidate();
    }//GEN-LAST:event_btnThoatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboHuyenDC;
    private javax.swing.JComboBox<String> cboHuyenQQ;
    private javax.swing.JComboBox<String> cboTinhDC;
    private javax.swing.JComboBox<String> cboTinhQQ;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboTrinhDo;
    private javax.swing.JComboBox<String> cboXaDC;
    private javax.swing.JComboBox<String> cboXaQQ;
    private javax.swing.JCheckBox chkBHXH;
    private datechooser.beans.DateChooserCombo dateChooserNgaySinh;
    private datechooser.beans.DateChooserCombo dateChooserNgayVaoLam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_img;
    private javax.swing.JPanel panelChucNang;
    private javax.swing.JPanel panelThongTin;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHeSoLuong;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuongCanBan;
    private javax.swing.JTextField txtLuongThamNien;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtPhuCap;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables

}
