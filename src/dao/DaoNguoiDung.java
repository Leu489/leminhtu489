/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.NguoiDung;
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
public class DaoNguoiDung {
    // get toàn bộ dữ liệu liệu bảng người dùng
    public static List<NguoiDung> getAllND() throws SQLException, ClassNotFoundException
    {
        List<NguoiDung> lstNguoiDung = new ArrayList<>();
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from NguoiDung";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        //-- Xử lý kết quả trả về
        while(rs.next())
        {
            NguoiDung ndx = new NguoiDung();
            ndx.setId(rs.getInt("id"));
            ndx.setTaikhoan(rs.getString("taikhoan"));
            ndx.setMatkhau(rs.getString("matkhau"));
            lstNguoiDung.add(ndx);
        }
        return lstNguoiDung;
    }
    // get 1 người dùng
    public static List<NguoiDung> getOneND(int _id, String _taikhoan, String _pass) throws SQLException, ClassNotFoundException
    {
        List<NguoiDung> lstNguoiDung = new ArrayList<>();
        //--- Kết nối CSDL, lấy dữ liệu
        Connection cnn = tienich.TienIch.getConnection();
        //-- Xây dựng câu lệnh truy vấn
        String sql = "select * from NguoiDung where id ="+_id +" and taikhoan = N'"+_taikhoan+"' and matkhau = N'"+_pass+"'";
        // -- Thực thi câu lệnh truy vấn
        PreparedStatement pStmt = cnn.prepareStatement(sql);
        ResultSet rs = pStmt.executeQuery();
        //-- Xử lý kết quả trả về
        while(rs.next())
        {
            NguoiDung ndx = new NguoiDung();
            ndx.setId(rs.getInt("id"));
            ndx.setTaikhoan(rs.getString("taikhoan"));
            ndx.setMatkhau(rs.getString("matkhau"));
            lstNguoiDung.add(ndx);
        }
        return lstNguoiDung;
    }
    // cập nhật 1 người dùng
    
    // thêm mới 1 người dùng
    
    // xóa 1 người dùng
    
}
