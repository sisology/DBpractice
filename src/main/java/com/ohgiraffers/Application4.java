package com.ohgiraffers;

import com.ohgiraffers.model.DepartmentDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 부서 아이디 입력 : D");
        String changeId = sc.nextLine();
        System.out.print("변경할 부서명 입력 : ");
        String changeTitle = sc.nextLine();
        System.out.print("변경할 로케이션 번호 입력 : L");
        String changeLocationId = sc.nextLine();

        DepartmentDTO changeDept = new DepartmentDTO();
        changeDept.setId(changeId);
        changeDept.setTitle(changeTitle);
        changeDept.setLocationId(changeLocationId);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/department-query.xml"));
            String query = prop.getProperty("updateDept");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, changeDept.getTitle());
            pstmt.setString(2, changeDept.getLocationId());
            pstmt.setString(3, changeDept.getId());

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
            System.out.println("부서 수정 완료");
        } else {
            System.out.println("부서 수정 실패");
        }
    }
}
