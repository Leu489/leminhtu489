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
import entity.HoaDon;
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
public class DaoHoaDon {
    public static List<HoaDon> getAllHD() throws SQLException, ClassNotFoundException
    {
        List<HoaDon> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from HoaDon";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            HoaDon hd1 = new HoaDon();
            hd1.setMahd(rs.getString("mahd"));
            hd1.setManv(rs.getInt("manv"));
            hd1.setTongsotien(rs.getFloat("tongsotien"));
            hd1.setNgay(rs.getString("ngay"));
            lstKetqua.add(hd1);
        }
        return lstKetqua;
        
    }
    public static List<HoaDon> getOneHD(String _mahd) throws SQLException, ClassNotFoundException
    {
        List<HoaDon> lstKetqua = new ArrayList<>();      
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from HoaDon where mahd ="+_mahd;
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            HoaDon hd1 = new HoaDon();
            hd1.setMahd(rs.getString("mahd"));
            hd1.setManv(rs.getInt("manv"));
            hd1.setTongsotien(rs.getFloat("tongsotien"));
            hd1.setNgay(rs.getString("ngay"));
            lstKetqua.add(hd1);
        }
        return lstKetqua;
        
    }
   
    public static void Delete() throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        String sql = "delete from HoaDon";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
    public static void InsertHD(HoaDon hd1) throws SQLException, ClassNotFoundException
    {
        // Mở kết nối - xác định câu lệnh truy vấn -> thực thi
        Connection conn = tienich.TienIch.getConnection();
        //-- Xác định câu lệnh truy vấn
        String sql = "insert into HoaDon(mahd,manv,tongsotien) values (N'"+hd1.getMahd()+"',"+hd1.getManv()+","+hd1.getTongsotien()+")";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.executeUpdate();
        // đóng kết nối
        tienich.TienIch.closePreparedStatement(pStmt);
        tienich.TienIch.closeConnection(conn);
    }
}
