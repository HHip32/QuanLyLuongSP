/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.NhanVien_DAO;
import entity.NhanVien;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class pnTaiKhoan_GUI extends javax.swing.JPanel {

    /**
     * Creates new form pnThongTinTaiKhoan_GUI
     */
    private NhanVien user;
    private NhanVien_DAO nhanVien_DAO;

    DefaultTableModel modelDSTK;
    DefaultTableModel modelDSNV;

    ArrayList<NhanVien> listNVChuaCoTK = new ArrayList<>();
    ArrayList<NhanVien> listNVCoTK = new ArrayList<>();

    public pnTaiKhoan_GUI() {
        initComponents();

        nhanVien_DAO = new NhanVien_DAO();
        user = MenuChinh.getUser();

        modelDSTK = (DefaultTableModel) tableDSTK.getModel();
        modelDSNV = (DefaultTableModel) tableDSNV.getModel();

        if (!user.isChucVu()) {
            clearTableNV();
            loadTableArrList(nhanVien_DAO.timNhanVienCoTKTheoMa(user.getMaNV()));
        } else {
            clearTableNV();
            loadTableNVChuaCoTK();
            clearTableTK();
            loadTableNVCoTKArr(nhanVien_DAO.getAllNhanVienCoTK());
        }

    }

    public void clearTableTK() {
        while (tableDSTK.getRowCount() > 0) {
            modelDSTK.removeRow(0);
        }
    }

    public void clearTableNV() {
        while (tableDSNV.getRowCount() > 0) {
            modelDSNV.removeRow(0);
        }
    }

    public void clearTextFields() {
        txtTenTaiKhoan.setText("");
        pwdMatKhau.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
    }

    public void loadTableArrList(ArrayList<NhanVien> listNhanVien) {
        for (NhanVien nv : listNhanVien) {
            modelDSTK.addRow(new Object[]{nv.getMaNV(), nv.getMatKhau(), nv.getSoDT(), nv.getEmail()});
        }
    }

    public void loadTableNVChuaCoTK() {
        listNVChuaCoTK = nhanVien_DAO.getAllNhanVienChuaCoTK();
        for (NhanVien nv : listNVChuaCoTK) {
            modelDSNV.addRow(new Object[]{nv.getMaNV(), nv.getSoDT(), nv.getEmail()});
        }
    }

    public void loadTableNVCoTK() {
        listNVCoTK = nhanVien_DAO.getAllNhanVienCoTK();
        for (NhanVien nv : listNVCoTK) {
            modelDSTK.addRow(new Object[]{nv.getMaNV(), nv.getMatKhau(), nv.getSoDT(), nv.getEmail()});
        }
    }

    public void loadTableNVCoTKArr(ArrayList<NhanVien> nhanVien) {
        for (NhanVien nv : nhanVien) {
            modelDSTK.addRow(new Object[]{nv.getMaNV(), nv.getMatKhau(), nv.getSoDT(), nv.getEmail()});
        }
    }

    public void themDLVaoTextFieldsDSNV() {
        int n = tableDSNV.getSelectedRow();
        txtTenTaiKhoan.setText(nhanVien_DAO.getAllNhanVienChuaCoTK().get(n).getMaNV());
        pwdMatKhau.setText(nhanVien_DAO.getAllNhanVienChuaCoTK().get(n).getMatKhau().trim());
        txtSDT.setText(nhanVien_DAO.getAllNhanVienChuaCoTK().get(n).getSoDT());
        txtEmail.setText(nhanVien_DAO.getAllNhanVienChuaCoTK().get(n).getEmail());
    }

    public void themDLVaoTextFieldsDSTK() {
        int n = tableDSTK.getSelectedRow();
        ArrayList<NhanVien> listTK = new ArrayList<>();
        listTK = nhanVien_DAO.timNhanVienCoTKTheoMa(modelDSTK.getValueAt(n, 0).toString());
        for (NhanVien tk : listTK) {
            txtTenTaiKhoan.setText(tk.getMaNV());
            pwdMatKhau.setText(tk.getMatKhau().trim());
            txtSDT.setText(tk.getSoDT());
            txtEmail.setText(tk.getEmail());
        }

    }

    private void sh_Mes(String mes, JTextField txt) {
        JOptionPane.showMessageDialog(null, mes);
        if (txt == null) {
            return;
        }
        txt.requestFocus();
    }

    public void moKhoaTextFields(boolean b) {
        pwdMatKhau.setEditable(b);
    }

    public void moKhoaControls(boolean b) {
        btnThem.setEnabled(b);
        btnLuu.setEnabled(b);
        btnSua.setEnabled(b);
        btnXoa.setEnabled(b);
        btnXoaTrang.setEnabled(b);
        btnThoat.setEnabled(b);
    }

    public void themTKChoNV() {
        String mk = pwdMatKhau.getText().trim();
        String maNV = txtTenTaiKhoan.getText().trim();
        if (nhanVien_DAO.themTKChoNV(maNV, mk)) {
            sh_Mes("Tạo tài khoản thành công!", null);

        } else {
            sh_Mes("Tạo tài khoản thất bại!", null);
        }
    }

    public void xoaTKCuaNV() {
        int op = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa tài khoản?", "delete", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {

            String maNV = txtTenTaiKhoan.getText().trim();
            if (maNV.equals(user.getMaNV())) {
                sh_Mes("Không thể xóa tài khoản của chính mình!", null);
            } else {
                if (nhanVien_DAO.xoaTKCuaNV(maNV)) {
                    sh_Mes("Xóa thành công!", null);
                    clearTableNV();
                    clearTableTK();
                    loadTableNVChuaCoTK();
                    loadTableNVCoTK();
                } else {
                    sh_Mes("Xóa thất bại!", null);
                    return;
                }
            }

        }
    }

    public void suaTKCuaNV() {
        int op = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa mật khẩu?", "update", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            if (nhanVien_DAO.capNhatTKCuaNV(txtTenTaiKhoan.getText().trim(), pwdMatKhau.getText().trim())) {
                sh_Mes("Sửa thành công!", null);
                clearTableTK();
                loadTableNVCoTK();
            }
        }
    }

    public void timTKNV() {
        String maNV = txtTimKiem.getText().trim();
        clearTableTK();
        loadTableNVCoTKArr(nhanVien_DAO.timTKTheoMa(maNV));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenTaiKhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        pwdMatKhau = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDSNV = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDSTK = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(209, 209, 209));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tên tài khoản:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mật khẩu:");

        txtTenTaiKhoan.setEditable(false);
        txtTenTaiKhoan.setPreferredSize(new java.awt.Dimension(71, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Số điện thoại:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Email:");

        txtSDT.setEditable(false);
        txtSDT.setPreferredSize(new java.awt.Dimension(71, 30));

        txtEmail.setEditable(false);
        txtEmail.setPreferredSize(new java.awt.Dimension(71, 30));

        pwdMatKhau.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(pwdMatKhau))
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pwdMatKhau)
                        .addGap(4, 4, 4)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        String[] hds = {"Mã nhân viên","Số điện thoại","Email"};
        modelDSNV = new DefaultTableModel(hds,0);
        tableDSNV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        tableDSNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Số điện thoại", "Email"
            }
        ));
        tableDSNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDSNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDSNV);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        String[] heads = {"Tên tài khoản","Mật khẩu","Số điện thoại","Email"};
        modelDSTK = new DefaultTableModel(heads,0);
        tableDSTK.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        tableDSTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên tài khoản", "Mật khẩu", "Số điện thoại", "Email"
            }
        ));
        tableDSTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDSTKMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableDSTK);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        btnThem.setBackground(new java.awt.Color(255, 204, 204));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setEnabled(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(255, 204, 204));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/diskette.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setEnabled(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setEnabled(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 204));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash-bin.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setEnabled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXoaTrang.setBackground(new java.awt.Color(255, 204, 204));
        btnXoaTrang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clean.png"))); // NOI18N
        btnXoaTrang.setText("Xóa trắng");
        btnXoaTrang.setEnabled(false);
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(255, 204, 204));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tìm kiếm:");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tài Khoản");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(539, 539, 539)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        xoaTKCuaNV();
        clearTextFields();
        moKhoaControls(false);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        // TODO add your handling code here:
        pwdMatKhau.setText("");
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        MenuChinh.getPnSlider().removeAll();
        MenuChinh.getPnSlider().repaint();
        MenuChinh.getPnSlider().revalidate();

        MenuChinh.getPnSlider().add(new pnManHinhChinh());
        MenuChinh.getPnSlider().repaint();
        MenuChinh.getPnSlider().revalidate();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void tableDSNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDSNVMouseClicked
        // TODO add your handling code here:
        themDLVaoTextFieldsDSNV();
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoaTrang.setEnabled(false);
        btnXoa.setEnabled(false);
        tableDSTK.clearSelection();
    }//GEN-LAST:event_tableDSNVMouseClicked

    private void tableDSTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDSTKMouseClicked
        // TODO add your handling code here:

        themDLVaoTextFieldsDSTK();
        btnSua.setEnabled(true);
        btnThem.setEnabled(false);
        btnXoa.setEnabled(true);
        tableDSNV.clearSelection();
        if(btnSua.getText().equals("Hủy")){
            btnXoa.setEnabled(false);
        }
    }//GEN-LAST:event_tableDSTKMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        timTKNV();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if (user.isChucVu()) {
            if (btnThem.getText().trim().equals("Hủy")) {
                themTKChoNV();
                moKhoaTextFields(false);
                moKhoaControls(true);
                btnLuu.setEnabled(false);
                btnSua.setEnabled(false);
                btnXoaTrang.setEnabled(false);
                btnThem.setText("Thêm");
                clearTextFields();
                clearTableNV();
                loadTableNVChuaCoTK();
                clearTableTK();
                loadTableNVCoTK();
                moKhoaControls(false);
            } else if (btnSua.getText().trim().equals("Hủy")) {
                suaTKCuaNV();
                moKhoaTextFields(false);
                moKhoaControls(false);
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
                btnThoat.setEnabled(true);
                btnSua.setText("Sửa");
                clearTextFields();
                clearTableTK();
                loadTableNVCoTK();
                moKhoaControls(false);
            }
        } else {
            if (btnThem.getText().trim().equals("Hủy")) {
                themTKChoNV();
                moKhoaTextFields(false);
                moKhoaControls(true);
                btnLuu.setEnabled(false);
                btnSua.setEnabled(false);
                btnXoaTrang.setEnabled(false);
                btnThem.setText("Thêm");
                clearTextFields();
                clearTableNV();
                loadTableNVChuaCoTK();
                clearTableTK();
                loadTableNVCoTK();
                moKhoaControls(false);
            } else if (btnSua.getText().trim().equals("Hủy")) {
                suaTKCuaNV();
                moKhoaTextFields(false);
                moKhoaControls(false);
                btnSua.setEnabled(true);
//                btnXoa.setEnabled(true);
//                btnThoat.setEnabled(true);
                btnSua.setText("Sửa");
                clearTextFields();
                clearTableTK();
                loadTableArrList(nhanVien_DAO.timNhanVienCoTKTheoMa(user.getMaNV()));
                moKhoaControls(false);

            }
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        if (user.isChucVu()) {
            if (btnSua.getText().trim().equals("Sửa")) {
                moKhoaTextFields(true);
                moKhoaControls(false);
                btnSua.setEnabled(true);
                btnXoaTrang.setEnabled(true);
                btnLuu.setEnabled(true);
                btnSua.setText("Hủy");
                pwdMatKhau.requestFocus();

            } else if (btnSua.getText().trim().equals("Hủy")) {
                moKhoaControls(true);
                moKhoaTextFields(false);
                btnLuu.setEnabled(false);
                btnThem.setEnabled(false);
                btnXoaTrang.setEnabled(false);
                btnXoa.setEnabled(true);
                btnSua.setText("Sửa");
//                pwdMatKhau.setText("");
                themDLVaoTextFieldsDSTK();
            }
        } else {
            if (btnSua.getText().trim().equals("Sửa")) {
                moKhoaTextFields(true);
                moKhoaControls(false);
                btnSua.setEnabled(true);
                btnXoaTrang.setEnabled(true);
                btnXoa.setEnabled(false);
                btnLuu.setEnabled(true);
                btnSua.setText("Hủy");
                pwdMatKhau.requestFocus();
            } else if (btnSua.getText().trim().equals("Hủy")) {
                moKhoaControls(true);
                moKhoaTextFields(false);
                btnLuu.setEnabled(false);
                btnXoaTrang.setEnabled(false);
                btnThem.setEnabled(false);
                btnXoa.setEnabled(false);
                btnSua.setText("Sửa");
                themDLVaoTextFieldsDSTK();

            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (btnThem.getText().equals("Thêm")) {
            moKhoaTextFields(true);
            moKhoaControls(false);
            btnThem.setEnabled(true);
            btnLuu.setEnabled(true);
            pwdMatKhau.requestFocus();
            btnThem.setText("Hủy");
        } else if (btnThem.getText().equals("Hủy")) {
            moKhoaTextFields(false);
            moKhoaControls(true);
            btnLuu.setEnabled(false);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnXoaTrang.setEnabled(false);
            btnThem.setText("Thêm");
        }
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField pwdMatKhau;
    private javax.swing.JTable tableDSNV;
    private javax.swing.JTable tableDSTK;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenTaiKhoan;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
