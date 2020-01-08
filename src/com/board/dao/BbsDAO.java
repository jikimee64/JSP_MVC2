package com.board.dao;

import com.board.dto.BbsDTO;
import com.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BbsDAO {
    public static BbsDAO bbsDAO = new BbsDAO();
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private int result = 0;

    private BbsDAO() {
        super();
    }

    public static BbsDAO getInstance() {
        return bbsDAO;
    }

    public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int nextval() {
        con = DatabaseUtil.getConnection();
        StringBuffer query = new StringBuffer();
        query.append("SELECT MAX(bbsID) ").append("FROM bbs");

        try {
            pstmt = con.prepareStatement(query.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("MAX(bbsID)");
            }
        } catch (SQLException e) {
            System.err.println("write SQLException error");
        } finally {
            close(con, pstmt, rs);
        }
        return result;
    }


    public int write(BbsDTO bbsDTO){
        con = DatabaseUtil.getConnection();
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO bbs ");
        query.append("(bbsID, bbsTitle, bbsContent, bbsDate, bbsHit, userID) ");
        query.append("VALUES (?, ?, ?, NOW(), 0, ?)");
        try{
            pstmt = con.prepareStatement(query.toString());
            pstmt.setInt(1, bbsDTO.getBbsID());
            pstmt.setString(2, bbsDTO.getBbsTitle());
            pstmt.setString(3, bbsDTO.getBbsContent());
            pstmt.setString(4, bbsDTO.getUserID());
            result = pstmt.executeUpdate();
        } catch(SQLException e){
            System.err.println("write SQLException error");
        } finally {
            close(con, pstmt, null);
        }
        return result;
    }

    public List<BbsDTO> selectList() {
        List<BbsDTO> list = new ArrayList<>();

        try{
            con = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM bbs order by bbsID desc";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                BbsDTO bbsDTO = new BbsDTO();
                bbsDTO.setBbsID(rs.getInt("bbsID"));
                bbsDTO.setBbsTitle(rs.getString("bbsTitle"));
                bbsDTO.setBbsContent(rs.getString("bbsContent"));
                bbsDTO.setBbsDate(rs.getString("bbsDate"));
                bbsDTO.setBbsHit(rs.getInt("bbsHit"));
                bbsDTO.setUserID(rs.getString("userID"));
                list.add(bbsDTO);
            }
        } catch (Exception e) {
            System.err.println("selectList SQLException error");
        } finally {
            close(con, pstmt, rs);
        }
        return list;
    }
}
