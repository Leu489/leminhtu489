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
import entity.SanPhamMua;
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
public class DaoSanPhamMua {
    public static List<SanPhamMua> getAllSPM() throws SQLException, ClassNotFoundException
    {
        List<SanPhamMua> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from SanPhamMua";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            SanPhamMua spm1 = new SanPhamMua();
            spm1.setMasp(rs.getInt("masp"));
            spm1.setTensp(rs.getString("tensp"));
            spm1.setLoai(rs.getString("loai"));
            spm1.setSoluongdat(rs.getInt("soluongdat"));
            spm1.setGia(rs.getFloat("gia"));
            spm1.setNgay(rs.getString("ngay"));
            lstKetqua.add(spm1);
        }
        return lstKetqua;
        
    }
    public static List<SanPhamMua> getOneSPM(String _loai) throws SQLException, ClassNotFoundException
    {
        List<SanPhamMua> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from SanPhamMua where loai =N'"+_loai+"'";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            SanPhamMua spm1 = new SanPhamMua();
            spm1.setMasp(rs.getInt("masp"));
            spm1.setTensp(rs.getString("tensp"));
            spm1.setLoai(rs.getString("loai"));
            spm1.setSoluongdat(rs.getInt("soluongdat"));
            spm1.setGia(rs.getFloat("gia"));
            spm1.setNgay(rs.getString("ngay"));
            lstKetqua.add(spm1);
        }
        return lstKetqua;
        
    }
    public static void DeleteSPM(String _masp) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        String sql = "delete from SanPhamMua where masp = "+_masp;
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
    public static void Delete() throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        String sql = "delete from SanPhamMua";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
    public static void InsertSPM(SanPhamMua spm1) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        //-- Xác định câu lệnh truy vấn
        String sql = "insert into SanPhamMua(masp,tensp,loai,soluongdat,gia) values ("+spm1.getMasp()+",N'"+spm1.getTensp()+"',N'"+spm1.getLoai()+"',"+spm1.getSoluongdat()+","+spm1.getGia()+")";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        // đóng kết nối
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
    public static void UpdateSPM(SanPhamMua spm1) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        String sql = "update SanPhamMua set tensp = N'"+spm1.getTensp()+"',loai = N'"+spm1.getLoai()+"',soluongdat= "+spm1.getSoluongdat()+",gia= "+spm1.getGia()+" where masp= "+spm1.getMasp();
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
}