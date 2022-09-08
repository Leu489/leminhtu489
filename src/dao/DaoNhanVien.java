/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.NguoiDung;
import entity.NhanVien;
import entity.ChucVu;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maverllous
 */
public class DaoNhanVien {
    // get ALL
    public static List<NhanVien> getAllNV() throws SQLException, ClassNotFoundException
    {
        List<NhanVien> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from NhanVien";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            NhanVien nv1 = new NhanVien();
            nv1.setManv(rs.getInt("manv"));
            nv1.setTennv(rs.getString("tennv"));
            nv1.setGioitinh(rs.getString("gioitinh"));
            nv1.setDiachi(rs.getString("diachi"));
            nv1.setSdt(rs.getString("sdt"));
            nv1.setCmnd(rs.getString("cmnd"));
            nv1.setMacv(rs.getInt("macv"));
            nv1.setLuong(rs.getFloat("luong"));
            nv1.setCalam(rs.getString("calam"));
            lstKetqua.add(nv1);
        }
        return lstKetqua;
        
    }
    // get One
    public static List<NhanVien> getOneNV(int _manv) throws SQLException, ClassNotFoundException
    {
        List<NhanVien> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from NhanVien where manv = '"+_manv+"'";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            NhanVien nv1 = new NhanVien();
            nv1.setManv(rs.getInt("manv"));
            nv1.setTennv(rs.getString("tennv"));
            nv1.setGioitinh(rs.getString("gioitinh"));
            nv1.setDiachi(rs.getString("diachi"));
            nv1.setSdt(rs.getString("sdt"));
            nv1.setCmnd(rs.getString("cmnd"));
            nv1.setMacv(rs.getInt("macv"));
            nv1.setLuong(rs.getFloat("luong"));
            nv1.setCalam(rs.getString("calam"));
            lstKetqua.add(nv1);
        }
        return lstKetqua;
        
    }
    
    public static List<NhanVien> getFind(int _manv) throws SQLException, ClassNotFoundException
    {
        List<NhanVien> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from NhanVien where manv = "+_manv;
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            NhanVien nv1 = new NhanVien();
            nv1.setManv(rs.getInt("manv"));
            nv1.setTennv(rs.getString("tennv"));
            nv1.setGioitinh(rs.getString("gioitinh"));
            nv1.setDiachi(rs.getString("diachi"));
            nv1.setSdt(rs.getString("sdt"));
            nv1.setCmnd(rs.getString("cmnd"));
            nv1.setMacv(rs.getInt("macv"));
            nv1.setLuong(rs.getFloat("luong"));
            nv1.setCalam(rs.getString("calam"));
            lstKetqua.add(nv1);
        }
        return lstKetqua;
        
    }
    // Delete One   
    public static void DeleteNV(String _manv) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        String sql = "delete from NhanVien where manv = "+_manv;
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
    // Update One
    public static void UpdateNV(NhanVien nv1) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        String sql = "update NhanVien set tennv = N'"+nv1.getTennv()+"',gioitinh= N'"+nv1.getGioitinh()+"',diachi= N'"+nv1.getDiachi()+"',sdt= N'"+nv1.getSdt()+"',cmnd= N'"+nv1.getCmnd()+"',macv= "+nv1.getMacv()+",calam=N'"+nv1.getCalam()+"' where manv= "+nv1.getManv();
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
    // Insert One
    public static void InsertNV(NhanVien nv1) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        //-- Xác định câu lệnh truy vấn
        String sql = "insert into NhanVien(manv,tennv,gioitinh,diachi,sdt,cmnd,macv,calam) values ("+nv1.getManv()+",N'"+nv1.getTennv()+"',N'"+nv1.getGioitinh()+"',N'"+nv1.getDiachi()+"',N'"+nv1.getSdt()+"',N'"+nv1.getCmnd()+"',"+nv1.getMacv()+",N'"+nv1.getCalam()+"')";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        // đóng kết nối
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
}
