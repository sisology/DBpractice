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

public class Application5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 부서 아이디 입력 : ");
        String deleteId = sc.nextLine();

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/department-query.xml"));
            String query = prop.getProperty("deleteDept");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, deleteId);

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
            System.out.println("부서 삭제 완료");
        } else {
            System.out.println("부서 삭제 실패");
        }
    }
}
