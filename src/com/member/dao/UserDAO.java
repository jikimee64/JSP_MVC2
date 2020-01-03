package com.member.dao;

import com.member.dto.UserDTO;
import com.util.DatabaseUtil;

import java.sql.*;

public class UserDAO {
    // +-------------싱글톤 패턴 적용-------------+
    private static UserDAO userDAO;
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private int result;

    private UserDAO(){

    }

    public static synchronized UserDAO getInstance() {
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        return userDAO;
    }
    // +-------------싱글톤 패턴 적용-------------+

    // JDBC 자원 닫기
    public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if(con != null){
            try{
                con.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(pstmt != null){
            try{
                pstmt.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(rs != null){
            try{
                rs.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public int join(UserDTO userDTO){
        con = DatabaseUtil.getConnection();
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO user").append(" VALUES (?, ?, ?, ?)");
        try{
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, userDTO.getUserID());
            pstmt.setString(2, userDTO.getUserPassword());
            pstmt.setString(3, userDTO.getUserName());
            pstmt.setString(4, userDTO.getUserEmail());
            result = pstmt.executeUpdate();
        } catch(SQLException e){
            System.err.println("join SQLException error");
        } finally {
            this.close(con, pstmt, null);
        }
        return result;
    }



}
