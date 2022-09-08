/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.DaoHoaDon;
import dao.DaoNhanVien;
import dao.DaoSanPham;
import dao.DaoSanPhamMua;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;
import entity.SanPhamMua;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author llong
 */
public class Excel {

    public Excel() {
    }
    
    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    private void NhanVien(HSSFWorkbook workbook, HSSFSheet sheet){
        try {
            int rownum = 0;
            Cell cell;
            Row row;
            HSSFCellStyle style = createStyleForTitle(workbook);
            row = sheet.createRow(rownum);
            //head
            String []ColsName = {"Mã nhân viên", "Tên nhân viên", "Giới tính", "Địa chỉ", "SDT", "CMND", "Mã chức vụ","Ca làm","Lương"};
            List<NhanVien> rs = DaoNhanVien.getAllNV();
            for (int i = 0; i < ColsName.length ; i ++) {
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(ColsName[i]);
                cell.setCellStyle(style);
            }
            for (NhanVien r : rs) {
                rownum++;
                row = sheet.createRow(rownum);
                // A
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(r.getManv());
                // B
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(r.getTennv());
                // C
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(r.getGioitinh());
                // D
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(r.getDiachi());
                // E
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(r.getSdt());
                // F
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(r.getCmnd());
                //G
                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(r.getMacv());
                //H
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(r.getCalam());
                //I
                cell = row.createCell(8, CellType.NUMERIC);
                cell.setCellValue(r.getLuong());
            }
            
            row = sheet.createRow(rownum+2);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Tổng Lương Nhân Viên:");
            // công thức tính tổng bla bla trong excel
            String formula = "SUM(I2:I"+(rownum + 1)+")";
            cell = row.createCell(8, CellType.FORMULA);
            cell.setCellFormula(formula);
        } catch (SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // cols name = tiêu đề của cột
    private void SanPham(HSSFWorkbook workbook, HSSFSheet sheet){
        try {
            int rownum = 0;
            Cell cell;
            Row row;
            HSSFCellStyle style = createStyleForTitle(workbook);
            row = sheet.createRow(rownum);
            //head
            String []ColsName = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Loại", "Giá bán","Giá nhập","Ngày nhập"};
            List<SanPham> rs = DaoSanPham.getAllSP();
            for (int i = 0; i < ColsName.length ; i ++) {
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(ColsName[i]);
                cell.setCellStyle(style);
            }
            for (SanPham r : rs) {
                rownum++;
                row = sheet.createRow(rownum);
                // A
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(r.getMasp());
                // B
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(r.getTensp());
                // C
                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(r.getSoluong());
                // D
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(r.getLoai());
                // E
                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(r.getGia());
                //F
                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(r.getGianhap());
                //G
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(r.getNgay());
            }
          
            row = sheet.createRow(rownum+2);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Tổng số tiền nhập :");
            // công thức tính tổng bla bla trong excel
            String formula = "SUM(F2:F"+(rownum + 1)+")";
            cell = row.createCell(5, CellType.FORMULA);
            cell.setCellFormula(formula);
        } catch (SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void SanPhamMua(HSSFWorkbook workbook, HSSFSheet sheet){
        try {
            int rownum = 0;
            Cell cell;
            Row row;
            HSSFCellStyle style = createStyleForTitle(workbook);
            row = sheet.createRow(rownum);
            //head
            String []ColsName = {"Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lượng đặt", "Giá","Ngày"};
            List<SanPhamMua> rs = DaoSanPhamMua.getAllSPM();
            for (int i = 0; i < ColsName.length ; i ++) {
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(ColsName[i]);
                cell.setCellStyle(style);
            }
            for (SanPhamMua r : rs) {
                rownum++;
                row = sheet.createRow(rownum);
                // A
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(r.getMasp());
                // B
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(r.getTensp());
                // C
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(r.getLoai());
                // D
                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(r.getSoluongdat());
                // E
                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(r.getGia());
                //F
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(r.getNgay());
            }
            
            row = sheet.createRow(rownum+2);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tổng tiền thanh toán :");
            // công thức tính tổng bla bla trong excel
            String formula = "SUMPRODUCT(D2:D"+(rownum + 1)+",E2:E"+(rownum + 1)+")";
            cell = row.createCell(4, CellType.FORMULA);
            cell.setCellFormula(formula);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void HoaDon(HSSFWorkbook workbook, HSSFSheet sheet){
        try {
            int rownum = 0;
            Cell cell;
            Row row;
            HSSFCellStyle style = createStyleForTitle(workbook);
            row = sheet.createRow(rownum);
            //head
            String []ColsName = {"Mã hóa đơn", "Mã nhân viên phụ trách", "Tổng số tiền","Ngày"};
            List<HoaDon> rs = DaoHoaDon.getAllHD();
            for (int i = 0; i < ColsName.length ; i ++) {
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(ColsName[i]);
                cell.setCellStyle(style);
            }
            for (HoaDon r : rs) {
                rownum++;
                row = sheet.createRow(rownum);
                // A
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(r.getMahd());
                // B
                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(r.getManv());
                // C
                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(r.getTongsotien());
                // D
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(r.getNgay());
            }
            
            row = sheet.createRow(rownum+2);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tổng thu nhập trong ngày :");
            // công thức tính tổng bla bla trong excel
            String formula = "SUM(C2:C"+(rownum + 1)+")";
            cell = row.createCell(2, CellType.FORMULA);
            cell.setCellFormula(formula);
        } catch (SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void WriteExcel(String Excel_Name, String name) throws Exception{
            FileOutputStream outFile = null;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Báo Cáo");
            if(name == "NhanVien"){
                NhanVien(workbook, sheet);
            }
            if(name == "SanPham"){
                SanPham(workbook, sheet);
            }
            if(name == "SanPhamMua"){
                SanPhamMua(workbook, sheet);
            }
            if(name == "HoaDon"){
                HoaDon(workbook, sheet);
            }
            // địa chỉ xuất excel
            File file = new File("C:\\Users\\user\\OneDrive\\Bureau\\java_excel\\"+Excel_Name+".xls");
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());
    }
}
