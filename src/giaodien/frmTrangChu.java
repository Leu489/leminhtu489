/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;

import Controller.Excel;
import dao.DaoChucVu;
import dao.DaoNhanVien;
import entity.ChucVu;
import entity.HoaDon;
import entity.NguoiDungDangNhap;
import entity.NhanVien;
import entity.SanPham;
import entity.SanPhamMua;
import giaodien.frmSanPham;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Maverllous
 */
public class frmTrangChu extends javax.swing.JPanel {
    Excel ex = new Excel();
    /**
     * Creates new form frmTrangChu
     */
    public frmTrangChu() throws SQLException, ClassNotFoundException {
        initComponents();
        LoadData2Table1();
        LoadData2Table2();
    }
    private void LoadData2Table1() throws SQLException, ClassNotFoundException
    {
            List<SanPham> lstSanPham = null;
                // Lấy danh sách sản phẩm
            switch (cboLoc.getSelectedIndex()) {
                case 1: {
                    lstSanPham = dao.DaoSanPham.getOneSP(cboLoc.getSelectedItem().toString());
                    break;
                }
                case 2: {
                    lstSanPham = dao.DaoSanPham.getOneSP(cboLoc.getSelectedItem().toString());
                    break;
                }
                case 0: {
                    lstSanPham = dao.DaoSanPham.getAllSP();
                    break;
                }
            }
            //--- Thêm các tiêu đề cho cột của bảng hiển thị
            DefaultTableModel tblModel = new DefaultTableModel();
            tblModel.addColumn("Mã sản phẩm");
            tblModel.addColumn("Tên sản phẩm");
            tblModel.addColumn("Loại");
            tblModel.addColumn("Số lượng");
            tblModel.addColumn("Giá");
            //--- add lần lượt các row
            for (SanPham _sanpham : lstSanPham) {
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanpham.getMasp()));
                row.addElement(_sanpham.getTensp());
                row.addElement(_sanpham.getLoai());
                row.addElement(String.valueOf(_sanpham.getSoluong()));
                row.addElement(String.valueOf(_sanpham.getGia()));
                tblModel.addRow(row); 
            }
            tableSanPham.setModel(tblModel);
    }
    float tong;
    private void LoadData2Table2() throws SQLException, ClassNotFoundException
    {
        List<SanPhamMua> lstSanPhamMua = dao.DaoSanPhamMua.getAllSPM();
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Tên sản phẩm");
        tblModel.addColumn("Loại");
        tblModel.addColumn("Số lượng đặt");
        tblModel.addColumn("Giá");
        tblModel.addColumn("Ngày mua");
        tong=0;
        for (SanPhamMua _sanphammua : lstSanPhamMua) {
                tong+=_sanphammua.getSoluongdat()*_sanphammua.getGia();
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanphammua.getMasp()));
                row.addElement(_sanphammua.getTensp());
                row.addElement(_sanphammua.getLoai());
                row.addElement(String.valueOf(_sanphammua.getSoluongdat()));
                row.addElement(String.valueOf(_sanphammua.getGia()));
                row.addElement(_sanphammua.getNgay());
                tblModel.addRow(row); 
            }
            tableSanPhamMua.setModel(tblModel);
            lbThanhtoan.setText(String.valueOf(tong));
    }
    private void LoadData2Control1() throws SQLException, ClassNotFoundException, ParseException
    {
        if (tableSanPham.getSelectedRow() != -1) {
            //-- Xác định hàng đã click
            int i = tableSanPham.getSelectedRow();
            
            //-- Lấy được danh sách sinh viên
            List<SanPham> lstSanPham = null;
            if(!"".equals(txtFind.getText()))
            {
                lstSanPham=dao.DaoSanPham.getFind(txtFind.getText());
            }
            else
            {
                if(radio1.isSelected()==true) 
                    lstSanPham = dao.DaoSanPham.getGia(0,10000);
                else
                    if(radio12.isSelected()==true)
                        lstSanPham = dao.DaoSanPham.getGia(10000,20000);
                    else
                        if(radio23.isSelected()==true)
                            lstSanPham = dao.DaoSanPham.getGia(20000,30000);
                        else
                            if(radio34.isSelected()==true)
                                lstSanPham = dao.DaoSanPham.getGia(30000,40000);
                            else
                                if(radio4.isSelected()==true)
                                    lstSanPham = dao.DaoSanPham.getGia(40000,1000000);
                                else
                                {
                                    switch (cboLoc.getSelectedItem().toString()) 
                                    {
                                        case "Đồ ăn": lstSanPham = dao.DaoSanPham.getOneSP(cboLoc.getSelectedItem().toString());
                                        case "Đồ uống": lstSanPham = dao.DaoSanPham.getOneSP(cboLoc.getSelectedItem().toString());
                                        default: lstSanPham = dao.DaoSanPham.getAllSP();
                                    } 
                                }
                
            }
            //-- Xác định là sinh viên nào đang được click
            
            SanPham sp1 = lstSanPham.get(i);
            //--- Set giá trị cho các control
            lbMasp.setText(String.valueOf(sp1.getMasp()));
            lbTensp.setText(sp1.getTensp());
            lbLoai.setText(sp1.getLoai());
            lbSoluonghienco.setText(String.valueOf(sp1.getSoluong()));
            lbGia.setText(String.valueOf(sp1.getGia()));
        }
    }
    private void LoadData2Control2() throws SQLException, ClassNotFoundException, ParseException
    {
        if (tableSanPhamMua.getSelectedRow() != -1) {
            //-- Xác định hàng đã click
            int i = tableSanPhamMua.getSelectedRow();
            
            //-- Lấy được danh sách sinh viên
            List<SanPhamMua> lstSanPhamMua = dao.DaoSanPhamMua.getAllSPM();
            //-- Xác định là sinh viên nào đang được click
            
            SanPhamMua spm1 = lstSanPhamMua.get(i);
            //--- Set giá trị cho các control
            lbMasp.setText(String.valueOf(spm1.getMasp()));
            lbTensp.setText(spm1.getTensp());
            lbLoai.setText(spm1.getLoai());
            lbGia.setText(String.valueOf(spm1.getGia()));
            spinSoluongdat.setValue(spm1.getSoluongdat());
        }
    }
    private void LoadData2Control3() throws SQLException, ClassNotFoundException, ParseException
    {
        List<NguoiDungDangNhap> lstNguoiDungDangNhap = dao.DaoNguoiDungDangNhap.getAllNDDN();
        NguoiDungDangNhap nddn1=lstNguoiDungDangNhap.get(0);
        lbManv.setText(String.valueOf(nddn1.getManv()));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSanPham = new org.jdesktop.swingx.JXTable();
        jPanelHoaDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbManv = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSanPhamMua = new org.jdesktop.swingx.JXTable();
        btnThanhtoan = new javax.swing.JButton();
        lbThanhtoan = new javax.swing.JLabel();
        btnXuatEX = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        txtMahd = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cboLoc = new javax.swing.JComboBox<>();
        btnTimkiem = new javax.swing.JButton();
        txtFind = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        radio12 = new javax.swing.JRadioButton();
        radio23 = new javax.swing.JRadioButton();
        radio34 = new javax.swing.JRadioButton();
        radio4 = new javax.swing.JRadioButton();
        radio1 = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbMasp = new javax.swing.JLabel();
        lbTensp = new javax.swing.JLabel();
        lbLoai = new javax.swing.JLabel();
        lbGia = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        spinSoluongdat = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        lbSoluonghienco = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnDat = new javax.swing.JButton();
        BtnXoa = new javax.swing.JButton();
        btnThaydoi = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSanPham);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        jPanelHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên phụ trách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel7.setText("Mã nhân viên:");

        lbManv.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lbManvAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbManv, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbManv, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tableSanPhamMua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tableSanPhamMua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSanPhamMuaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSanPhamMua);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThanhtoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/thanhtoan.png"))); // NOI18N
        btnThanhtoan.setText("Thanh toán");
        btnThanhtoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhtoanMouseClicked(evt);
            }
        });

        lbThanhtoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbThanhtoan.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lbThanhtoanAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btnXuatEX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xuatex.png"))); // NOI18N
        btnXuatEX.setText("Xuất Excel");
        btnXuatEX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXuatEXMouseClicked(evt);
            }
        });
        btnXuatEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatEXActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtMahd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMahdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMahd, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelHoaDonLayout = new javax.swing.GroupLayout(jPanelHoaDon);
        jPanelHoaDon.setLayout(jPanelHoaDonLayout);
        jPanelHoaDonLayout.setHorizontalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnXuatEX, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelHoaDonLayout.setVerticalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbThanhtoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThanhtoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(btnXuatEX))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc/Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        cboLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "Đồ ăn", "Đồ uống" }));
        cboLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocActionPerformed(evt);
            }
        });

        btnTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/timkiem.png"))); // NOI18N
        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimkiemMouseClicked(evt);
            }
        });

        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc theo giá"));

        radio12.setText("10000-20000");
        radio12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio12MouseClicked(evt);
            }
        });
        radio12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio12ActionPerformed(evt);
            }
        });

        radio23.setText("20000-30000");
        radio23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio23MouseClicked(evt);
            }
        });
        radio23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio23ActionPerformed(evt);
            }
        });

        radio34.setText("30000-40000");
        radio34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio34MouseClicked(evt);
            }
        });
        radio34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio34ActionPerformed(evt);
            }
        });

        radio4.setText(">40000");
        radio4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio4MouseClicked(evt);
            }
        });
        radio4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio4ActionPerformed(evt);
            }
        });

        radio1.setText("<10000");
        radio1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radio1MouseClicked(evt);
            }
        });
        radio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(radio12)
            .addComponent(radio23)
            .addComponent(radio34)
            .addComponent(radio4)
            .addComponent(radio1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(radio1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radio12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radio23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radio34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radio4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(txtFind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimkiem)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setText("Mã sản phẩm:");

        jLabel3.setText("Tên sản phẩm:");

        jLabel4.setText("Loại:");

        jLabel5.setText("Giá:");

        jLabel1.setText("Số lượng đặt:");

        spinSoluongdat.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinSoluongdat.setAutoscrolls(true);

        jLabel6.setText("Số lượng hiện có:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTensp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lbMasp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinSoluongdat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSoluonghienco, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lbMasp, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lbTensp, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lbGia, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lbSoluonghienco, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(spinSoluongdat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lựa chọn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        btnDat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/them.png"))); // NOI18N
        btnDat.setText("Đặt");
        btnDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDatMouseClicked(evt);
            }
        });

        BtnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/xoa.png"))); // NOI18N
        BtnXoa.setText("Xóa");
        BtnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnXoaMouseClicked(evt);
            }
        });

        btnThaydoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/thaydoi.png"))); // NOI18N
        btnThaydoi.setText("Thay đổi");
        btnThaydoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThaydoiMouseClicked(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/thoat.png"))); // NOI18N
        jButton1.setText("Thoát");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThaydoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btnDat, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThaydoi, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Danh sách sản phẩm\n");
    }// </editor-fold>//GEN-END:initComponents

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        // TODO add your handling code here:
        List<SanPham> lstSanPham = null;
        try {
            lstSanPham = dao.DaoSanPham.getFind(txtFind.getText());
        } catch (SQLException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //--- Thêm các tiêu đề cho cột của bảng hiển thị
            DefaultTableModel tblModel = new DefaultTableModel();
            tblModel.addColumn("Mã sản phẩm");
            tblModel.addColumn("Tên sản phẩm");
            tblModel.addColumn("Loại");
            tblModel.addColumn("Số lượng");
            tblModel.addColumn("Giá");
            //--- add lần lượt các row
            for (SanPham _sanpham : lstSanPham) {
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanpham.getMasp()));
                row.addElement(_sanpham.getTensp());
                row.addElement(_sanpham.getLoai());
                row.addElement(String.valueOf(_sanpham.getSoluong()));
                row.addElement(String.valueOf(_sanpham.getGia()));
                tblModel.addRow(row); 
            }
            tableSanPham.setModel(tblModel);
    }//GEN-LAST:event_txtFindActionPerformed

    private void cboLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocActionPerformed
        try {
            // TODO add your handling code here:
            LoadData2Table1();
        } catch (SQLException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboLocActionPerformed

    private void tableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanPhamMouseClicked
        // TODO add your handling code here:
        try {
            try {
                // TODO add your handling code here:
                LoadData2Control1();
            } catch (ParseException ex) {
                Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableSanPhamMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        int dialogButton = JOptionPane.showConfirmDialog (null, "Bạn muốn thoát chương trình?","WARNING", JOptionPane.YES_NO_OPTION);
        if(dialogButton == JOptionPane.YES_OPTION) 
        {
            try {
                // TODO add your handling code here:
                dao.DaoNguoiDungDangNhap.DeleteNDDN();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex);
                Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
        else
            remove(dialogButton);            
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnDatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDatMouseClicked
        // TODO add your handling code here:
        SanPhamMua _sanphammua = new SanPhamMua();
        _sanphammua.setMasp(Integer.valueOf(lbMasp.getText()));
        _sanphammua.setTensp(lbTensp.getText());
        _sanphammua.setLoai(lbLoai.getText());
        _sanphammua.setSoluongdat((int)spinSoluongdat.getValue());
        _sanphammua.setGia(Float.valueOf(lbGia.getText()));
        if( _sanphammua.getSoluongdat()<=Integer.valueOf(lbSoluonghienco.getText()))
        {
            try {
                dao.DaoSanPhamMua.InsertSPM(_sanphammua);
                LoadData2Table2();
                JOptionPane.showMessageDialog(this, "Thêm vào giỏ hàng thành công!");
                lbThanhtoan.setText(String.valueOf(tong));
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
            } 
            try {
                LoadData2Table1();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
                Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
            spinSoluongdat.setValue(1);
        }
        else
            JOptionPane.showMessageDialog(this,"Số lượng đặt không thỏa mãn");
    }//GEN-LAST:event_btnDatMouseClicked

    private void BtnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnXoaMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            dao.DaoSanPhamMua.DeleteSPM(lbMasp.getText());
            LoadData2Table2();
            lbThanhtoan.setText(String.valueOf(tong));
            LoadData2Table1();
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex);
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        spinSoluongdat.setValue(1);
    }//GEN-LAST:event_BtnXoaMouseClicked

    private void tableSanPhamMuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanPhamMuaMouseClicked
        // TODO add your handling code here:
        try {
            try {
                // TODO add your handling code here:
                LoadData2Control2();
            } catch (ParseException ex) {
                Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableSanPhamMuaMouseClicked

    private void btnThaydoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThaydoiMouseClicked
        // TODO add your handling code here:
        SanPhamMua _sanphammua = new SanPhamMua();
        _sanphammua.setMasp(Integer.valueOf(lbMasp.getText()));
        _sanphammua.setTensp(lbTensp.getText());
        _sanphammua.setLoai(lbLoai.getText());
        _sanphammua.setSoluongdat((int)spinSoluongdat.getValue());
        _sanphammua.setGia(Float.valueOf(lbGia.getText()));
        if(_sanphammua.getSoluongdat()<=Integer.valueOf(lbSoluonghienco.getText()))
        {
            try {
                dao.DaoSanPhamMua.UpdateSPM(_sanphammua);
                LoadData2Table2();
                JOptionPane.showMessageDialog(this, "Thay đổi thành công!");
                lbThanhtoan.setText(String.valueOf(tong));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
                Logger.getLogger(frmSanPham.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
            try {
                    LoadData2Table1();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex);
                    Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex);
                }
            spinSoluongdat.setValue(1);
        }
        else
            JOptionPane.showMessageDialog(this,"Số lượng đặt không thỏa mãn");
    }//GEN-LAST:event_btnThaydoiMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        lbMasp.setText("");
        lbTensp.setText("");
        lbLoai.setText("");
        lbGia.setText("");
        lbSoluonghienco.setText("");
        spinSoluongdat.setValue(1);
        txtFind.setText("");
        cboLoc.setSelectedIndex(0);
        radio1.setSelected(false);
        radio12.setSelected(false);
        radio23.setSelected(false);
        radio34.setSelected(false);
        radio4.setSelected(false);
        try {
            LoadData2Table1();
            LoadData2Table2();
        } catch (SQLException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnResetMouseClicked

    private void radio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio1ActionPerformed
        // TODO add your handling code here:
        radio12.setSelected(false);
        radio23.setSelected(false);
        radio34.setSelected(false);
        radio4.setSelected(false);
    }//GEN-LAST:event_radio1ActionPerformed

    private void radio12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio12ActionPerformed
        // TODO add your handling code here:
        radio1.setSelected(false);
        radio23.setSelected(false);
        radio34.setSelected(false);
        radio4.setSelected(false);
    }//GEN-LAST:event_radio12ActionPerformed

    private void radio23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio23ActionPerformed
        // TODO add your handling code here:
        radio12.setSelected(false);
        radio1.setSelected(false);
        radio34.setSelected(false);
        radio4.setSelected(false);
    }//GEN-LAST:event_radio23ActionPerformed

    private void radio34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio34ActionPerformed
        // TODO add your handling code here:
        radio12.setSelected(false);
        radio23.setSelected(false);
        radio1.setSelected(false);
        radio4.setSelected(false);
    }//GEN-LAST:event_radio34ActionPerformed

    private void radio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio4ActionPerformed
        // TODO add your handling code here:
        radio12.setSelected(false);
        radio23.setSelected(false);
        radio34.setSelected(false);
        radio1.setSelected(false);
    }//GEN-LAST:event_radio4ActionPerformed

    private void btnTimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimkiemMouseClicked
        // TODO add your handling code here:
        List<SanPham> lstSanPham = null;
        try {
            lstSanPham = dao.DaoSanPham.getFind(txtFind.getText());
        } catch (SQLException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //--- Thêm các tiêu đề cho cột của bảng hiển thị
            DefaultTableModel tblModel = new DefaultTableModel();
            tblModel.addColumn("Mã sản phẩm");
            tblModel.addColumn("Tên sản phẩm");
            tblModel.addColumn("Loại");
            tblModel.addColumn("Số lượng");
            tblModel.addColumn("Giá");
            //--- add lần lượt các row
            for (SanPham _sanpham : lstSanPham) {
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanpham.getMasp()));
                row.addElement(_sanpham.getTensp());
                row.addElement(_sanpham.getLoai());
                row.addElement(String.valueOf(_sanpham.getSoluong()));
                row.addElement(String.valueOf(_sanpham.getGia()));
                tblModel.addRow(row); 
            }
            tableSanPham.setModel(tblModel);
    }//GEN-LAST:event_btnTimkiemMouseClicked

    private void radio1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio1MouseClicked
        // TODO add your handling code here:
        List<SanPham> lstSanPham = null;
        try {
            lstSanPham = dao.DaoSanPham.getGia(0,10000);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //--- Thêm các tiêu đề cho cột của bảng hiển thị
            DefaultTableModel tblModel = new DefaultTableModel();
            tblModel.addColumn("Mã sản phẩm");
            tblModel.addColumn("Tên sản phẩm");
            tblModel.addColumn("Loại");
            tblModel.addColumn("Số lượng");
            tblModel.addColumn("Giá");
            //--- add lần lượt các row
            for (SanPham _sanpham : lstSanPham) {
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanpham.getMasp()));
                row.addElement(_sanpham.getTensp());
                row.addElement(_sanpham.getLoai());
                row.addElement(String.valueOf(_sanpham.getSoluong()));
                row.addElement(String.valueOf(_sanpham.getGia()));
                tblModel.addRow(row); 
            }
            tableSanPham.setModel(tblModel);
        if(!radio1.isSelected())
            try {
                LoadData2Table1();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_radio1MouseClicked

    private void radio12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio12MouseClicked
        // TODO add your handling code here:
        List<SanPham> lstSanPham = null;
        try {
            lstSanPham = dao.DaoSanPham.getGia(10000,20000);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //--- Thêm các tiêu đề cho cột của bảng hiển thị
            DefaultTableModel tblModel = new DefaultTableModel();
            tblModel.addColumn("Mã sản phẩm");
            tblModel.addColumn("Tên sản phẩm");
            tblModel.addColumn("Loại");
            tblModel.addColumn("Số lượng");
            tblModel.addColumn("Giá");
            //--- add lần lượt các row
            for (SanPham _sanpham : lstSanPham) {
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanpham.getMasp()));
                row.addElement(_sanpham.getTensp());
                row.addElement(_sanpham.getLoai());
                row.addElement(String.valueOf(_sanpham.getSoluong()));
                row.addElement(String.valueOf(_sanpham.getGia()));
                tblModel.addRow(row); 
            }
            tableSanPham.setModel(tblModel);
        if(!radio12.isSelected())
            try {
                LoadData2Table1();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_radio12MouseClicked

    private void radio23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio23MouseClicked
        // TODO add your handling code here:
        List<SanPham> lstSanPham = null;
        try {
            lstSanPham = dao.DaoSanPham.getGia(20000,30000);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //--- Thêm các tiêu đề cho cột của bảng hiển thị
            DefaultTableModel tblModel = new DefaultTableModel();
            tblModel.addColumn("Mã sản phẩm");
            tblModel.addColumn("Tên sản phẩm");
            tblModel.addColumn("Loại");
            tblModel.addColumn("Số lượng");
            tblModel.addColumn("Giá");
            //--- add lần lượt các row
            for (SanPham _sanpham : lstSanPham) {
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanpham.getMasp()));
                row.addElement(_sanpham.getTensp());
                row.addElement(_sanpham.getLoai());
                row.addElement(String.valueOf(_sanpham.getSoluong()));
                row.addElement(String.valueOf(_sanpham.getGia()));
                tblModel.addRow(row); 
            }
            tableSanPham.setModel(tblModel);
        if(!radio23.isSelected())
            try {
                LoadData2Table1();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_radio23MouseClicked

    private void radio34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio34MouseClicked
        // TODO add your handling code here:
        List<SanPham> lstSanPham = null;
        try {
            lstSanPham = dao.DaoSanPham.getGia(30000,40000);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //--- Thêm các tiêu đề cho cột của bảng hiển thị
            DefaultTableModel tblModel = new DefaultTableModel();
            tblModel.addColumn("Mã sản phẩm");
            tblModel.addColumn("Tên sản phẩm");
            tblModel.addColumn("Loại");
            tblModel.addColumn("Số lượng");
            tblModel.addColumn("Giá");
            //--- add lần lượt các row
            for (SanPham _sanpham : lstSanPham) {
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanpham.getMasp()));
                row.addElement(_sanpham.getTensp());
                row.addElement(_sanpham.getLoai());
                row.addElement(String.valueOf(_sanpham.getSoluong()));
                row.addElement(String.valueOf(_sanpham.getGia()));
                tblModel.addRow(row); 
            }
            tableSanPham.setModel(tblModel);
        if(!radio34.isSelected())
            try {
                LoadData2Table1();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_radio34MouseClicked

    private void radio4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radio4MouseClicked
        // TODO add your handling code here:
        List<SanPham> lstSanPham = null;
        try {
            lstSanPham = dao.DaoSanPham.getGia(40000,1000000);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        //--- Thêm các tiêu đề cho cột của bảng hiển thị
            DefaultTableModel tblModel = new DefaultTableModel();
            tblModel.addColumn("Mã sản phẩm");
            tblModel.addColumn("Tên sản phẩm");
            tblModel.addColumn("Loại");
            tblModel.addColumn("Số lượng");
            tblModel.addColumn("Giá");
            //--- add lần lượt các row
            for (SanPham _sanpham : lstSanPham) {
                Vector<String> row = new Vector<String>();
                row.addElement(String.valueOf(_sanpham.getMasp()));
                row.addElement(_sanpham.getTensp());
                row.addElement(_sanpham.getLoai());
                row.addElement(String.valueOf(_sanpham.getSoluong()));
                row.addElement(String.valueOf(_sanpham.getGia()));
                tblModel.addRow(row); 
            }
            tableSanPham.setModel(tblModel);
        if(!radio4.isSelected())
            try {
                LoadData2Table1();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_radio4MouseClicked

    private void lbThanhtoanAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lbThanhtoanAncestorAdded
        lbThanhtoan.setText(String.valueOf(tong));
    }//GEN-LAST:event_lbThanhtoanAncestorAdded

    private void btnThanhtoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhtoanMouseClicked
        // TODO add your handling code here:
        if(Float.valueOf(lbThanhtoan.getText())>0 && !"".equals(txtMahd.getText()))
        {
            int dialogButton = JOptionPane.showConfirmDialog (null, "Số tiền bạn thanh toán:"+lbThanhtoan.getText(),"Hóa đơn", JOptionPane.YES_NO_OPTION);
            if(dialogButton == JOptionPane.YES_OPTION) 
            {
                HoaDon _hoadon = new HoaDon();
                _hoadon.setMahd(txtMahd.getText());
                _hoadon.setManv(Integer.valueOf(lbManv.getText()));
                _hoadon.setTongsotien(tong);
                try {
                    dao.DaoHoaDon.InsertHD(_hoadon);
                } catch (SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex);
                    Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
                tong=0;
                lbThanhtoan.setText("");
                try {
                    dao.DaoSanPhamMua.Delete();
                    LoadData2Table2();
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtMahd.setText("");
            }
            else
                remove(dialogButton);   
        }
        else
            JOptionPane.showMessageDialog(this, "Thanh toán không thành công!");
    }//GEN-LAST:event_btnThanhtoanMouseClicked

    private void lbManvAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lbManvAncestorAdded
        // TODO add your handling code here:
        try {
            try {
                // TODO add your handling code here:
                LoadData2Control3();
            } catch (ParseException ex) {
                Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbManvAncestorAdded

    private void btnXuatEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatEXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatEXActionPerformed

    private void btnXuatEXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatEXMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            ex.WriteExcel("SPM"+txtMahd.getText(), "SanPhamMua");
        } catch (Exception ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatEXMouseClicked

    private void txtMahdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMahdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMahdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnXoa;
    private javax.swing.JButton btnDat;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThanhtoan;
    private javax.swing.JButton btnThaydoi;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXuatEX;
    private javax.swing.JComboBox<String> cboLoc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelHoaDon;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbGia;
    private javax.swing.JLabel lbLoai;
    private javax.swing.JLabel lbManv;
    private javax.swing.JLabel lbMasp;
    private javax.swing.JLabel lbSoluonghienco;
    private javax.swing.JLabel lbTensp;
    private javax.swing.JLabel lbThanhtoan;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio12;
    private javax.swing.JRadioButton radio23;
    private javax.swing.JRadioButton radio34;
    private javax.swing.JRadioButton radio4;
    private javax.swing.JSpinner spinSoluongdat;
    private org.jdesktop.swingx.JXTable tableSanPham;
    private org.jdesktop.swingx.JXTable tableSanPhamMua;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtMahd;
    // End of variables declaration//GEN-END:variables

}
