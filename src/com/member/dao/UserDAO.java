package com.member.dao;

import com.member.dto.UserDTO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class UserDAO {
    // +-------------싱글톤 패턴 적용-------------+
    private static UserDAO userDAO;
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    DataSource dataSource;
    private int result;

    public UserDAO(){
        try{
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/amado");
        }catch(Exception e){
            e.printStackTrace();
        }
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
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO user").append(" VALUES (?, ?, ?, ?)");
        try{
            con = dataSource.getConnection();
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

    public int registerCheck(String userID) {
        StringBuffer query = new StringBuffer();
        query.append("SELECT *").append(" FROM user").append(" WHERE userID = ?");
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();
            if (rs.next() || userID == null || userID.equals("")) {
                return 0; //이미 존재하는 회원
            } else {
                return 1; //가입 가능한 회원 아이디
            }
        } catch(SQLException e){
            System.err.println("registerCheck error");
        } finally {
            this.close(con, pstmt, null);
        }
        return -1;
    }

    public int login(String userID, String userPassword){
        StringBuffer query = new StringBuffer();
        query.append("SELECT userPassword").append(" FROM user").append(" WHERE userID = ?");
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();

            if(rs.next()){
                if(rs.getString("userPassword").equals(userPassword)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch(SQLException e){
            System.err.println("login SQLException error");
        } finally {
            this.close(con, pstmt, null);
        }
        return -1;
    }

    public int modify(UserDTO userDTO){
        StringBuffer query = new StringBuffer();
        query.append("UPDATE user").append(" SET userPassword = ?, userName = ?, userEmail = ? ").append(" WHERE userID = ?");
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, userDTO.getUserPassword());
            pstmt.setString(2, userDTO.getUserName());
            pstmt.setString(3, userDTO.getUserEmail());
            pstmt.setString(4, userDTO.getUserID());
            result = pstmt.executeUpdate();
        } catch(SQLException e){
            System.err.println("modify SQLException error");
        } finally {
            this.close(con, pstmt, null);
        }
        return result;
    }

    public UserDTO getMember(String userID){
        UserDTO userDTO = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT *").append(" FROM user").append(" WHERE userID = ?");
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();
            if(rs.next()){
                userDTO = new UserDTO();
                userDTO.setUserID(rs.getString("userID"));
                userDTO.setUserPassword(rs.getString("userPassword"));
                userDTO.setUserName(rs.getString("userName"));
                userDTO.setUserEmail(rs.getString("userEmail"));
            }
        } catch(SQLException e){
            System.err.println("getMember SQLException error");
        } finally {
            this.close(con, pstmt, null);
        }
        return userDTO;
    }

}
