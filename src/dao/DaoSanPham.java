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
public class DaoSanPham {
    public static List<SanPham> getAllSP() throws SQLException, ClassNotFoundException
    {
        List<SanPham> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from SanPham";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            SanPham sp1 = new SanPham();
            sp1.setMasp(rs.getInt("masp"));
            sp1.setTensp(rs.getString("tensp"));
            sp1.setLoai(rs.getString("loai"));
            sp1.setSoluong(rs.getInt("soluong"));
            sp1.setGia(rs.getFloat("gia"));
            sp1.setGianhap(rs.getFloat("gianhap"));
            sp1.setNgay(rs.getString("ngay"));
            lstKetqua.add(sp1);
        }
        return lstKetqua;
        
    }
    // get One
    public static List<SanPham> getOneSP(String _loai) throws SQLException, ClassNotFoundException
    {
        List<SanPham> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from SanPham where loai =N'"+_loai+"'";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            SanPham sp1 = new SanPham();
            sp1.setMasp(rs.getInt("masp"));
            sp1.setTensp(rs.getString("tensp"));
            sp1.setLoai(rs.getString("loai"));
            sp1.setSoluong(rs.getInt("soluong"));
            sp1.setGia(rs.getFloat("gia"));
            sp1.setGianhap(rs.getFloat("gianhap"));
            sp1.setNgay(rs.getString("ngay"));
            lstKetqua.add(sp1);
        }
        return lstKetqua;
    }
    public static List<SanPham> getFind(String _tensp) throws SQLException, ClassNotFoundException
    {
        List<SanPham> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from SanPham where tensp like N'%"+_tensp+"%'";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            SanPham sp1 = new SanPham();
            sp1.setMasp(rs.getInt("masp"));
            sp1.setTensp(rs.getString("tensp"));
            sp1.setLoai(rs.getString("loai"));
            sp1.setSoluong(rs.getInt("soluong"));
            sp1.setGia(rs.getFloat("gia"));
            sp1.setGianhap(rs.getFloat("gianhap"));
            sp1.setNgay(rs.getString("ngay"));
            lstKetqua.add(sp1);
        }
        return lstKetqua;
        
    }
   
    public static List<SanPham> getGia(float _gia1,float _gia2) throws SQLException, ClassNotFoundException
    {
        List<SanPham> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from SanPham where gia between "+_gia1+" and "+_gia2;
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            SanPham sp1 = new SanPham();
            sp1.setMasp(rs.getInt("masp"));
            sp1.setTensp(rs.getString("tensp"));
            sp1.setLoai(rs.getString("loai"));
            sp1.setSoluong(rs.getInt("soluong"));
            sp1.setGia(rs.getFloat("gia"));
            sp1.setGianhap(rs.getFloat("gianhap"));
            sp1.setNgay(rs.getString("ngay"));
            lstKetqua.add(sp1);
        }
        return lstKetqua;
        
    }
    // Delete One   
    public static void DeleteSP(String _masp) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        String sql = "delete from SanPham where masp = "+_masp;
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
    // Update One
    public static void UpdateSP(SanPham sp1) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        String sql = "update SanPham set tensp = N'"+sp1.getTensp()+"',loai = N'"+sp1.getLoai()+"',soluong= "+sp1.getSoluong()+",gia= "+sp1.getGia()+",gianhap= "+sp1.getGianhap()+",ngay = N'"+sp1.getNgay()+"' where masp= "+sp1.getMasp();
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
    // Insert One
    public static void InsertSP(SanPham sp1) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        //-- Xác định câu lệnh truy vấn
        String sql = "insert into SanPham(masp,tensp,loai,soluong,gia,gianhap,ngay) values ("+sp1.getMasp()+",N'"+sp1.getTensp()+"',N'"+sp1.getLoai()+"',"+sp1.getSoluong()+","+sp1.getGia()+","+sp1.getGianhap()+",N'"+sp1.getNgay()+"')";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        // đóng kết nối
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
}
