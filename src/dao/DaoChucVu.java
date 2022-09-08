/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ChucVu;
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
public class DaoChucVu {
    public static List<ChucVu> getAllChucVu() throws SQLException, ClassNotFoundException
    {
        List<ChucVu> lstLopHoc = new ArrayList<>();
        //- mở kết nối ; xác định câu lệnh truy vấn ; thực thi câu lệnh truy vấn
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from ChucVu";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {
            ChucVu cv1 = new ChucVu();
            cv1.setMacv(rs.getInt("macv"));
            cv1.setTencv(rs.getString("tencv"));
            cv1.setHsluong(rs.getFloat("hsluong"));
            cv1.setSogiolam(rs.getFloat("sogiolam"));
            lstLopHoc.add(cv1);
        }
        return lstLopHoc;
    }
    public static ChucVu getOneChucVu(String _macv) throws SQLException, ClassNotFoundException
    {
        ChucVu _chucvu = new ChucVu();
        //- mở kết nối ; xác định câu lệnh truy vấn ; thực thi câu lệnh truy vấn
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from ChucVu where macv = '"+_macv+"'";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next())
        {            
            _chucvu.setMacv(rs.getInt("macv"));
            _chucvu.setTencv(rs.getString("tencv"));  
            _chucvu.setHsluong(rs.getFloat("hsluong"));
            _chucvu.setSogiolam(rs.getFloat("sogiolam"));       
        }
        return _chucvu;
    }
}
