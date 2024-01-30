package com.ohgiraffers;

import com.ohgiraffers.model.DepartmentDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        DepartmentDTO selectedDept = null;
        List<DepartmentDTO> deptList = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 부서의 아이디 숫자를 입력해주세요 (D_) : ");
        int deptId = sc.nextInt();

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/department-query.xml"));
            String query = prop.getProperty("selectDeptId");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, deptId);

            rset = pstmt.executeQuery();

            deptList = new ArrayList<>();

            while (rset.next()) {
                selectedDept = new DepartmentDTO();

                selectedDept.setId(rset.getString("dept_id"));
                selectedDept.setTitle(rset.getString("dept_title"));

                deptList.add(selectedDept);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        for (DepartmentDTO departmentDTO : deptList) {
            System.out.println(departmentDTO);
        }

    }
}
