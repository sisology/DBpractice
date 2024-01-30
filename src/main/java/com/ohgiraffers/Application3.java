package com.ohgiraffers;

import com.ohgiraffers.model.DepartmentDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("부서 아이디 입력 : ");
        String deptId = sc.nextLine();
        System.out.print("부서명 입력 : ");
        String deptTitle = sc.nextLine();
        System.out.print("로케이션 번호 입력 : ");
        String locationId = sc.nextLine();

        DepartmentDTO newDept = new DepartmentDTO();
        newDept.setId(deptId);
        newDept.setTitle(deptTitle);
        newDept.setLocationId(locationId);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/department-query.xml"));
            String query = prop.getProperty("insertDept");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newDept.getId());
            pstmt.setString(2, newDept.getTitle());
            pstmt.setString(3, newDept.getLocationId());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("부서 등록 완료");
        } else {
            System.out.println("부서 등록 실패");
        }
    }
}
