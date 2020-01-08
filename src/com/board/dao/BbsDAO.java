package com.board.dao;

import com.board.dto.BbsDTO;
import com.util.DatabaseUtil;

import javax.xml.crypto.Data;
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


    public int write(BbsDTO bbsDTO) {
        con = DatabaseUtil.getConnection();
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO bbs ");
        query.append("(bbsID, bbsTitle, bbsContent, bbsDate, bbsHit, userID) ");
        query.append("VALUES (?, ?, ?, NOW(), 0, ?)");
        try {
            pstmt = con.prepareStatement(query.toString());
            pstmt.setInt(1, bbsDTO.getBbsID());
            pstmt.setString(2, bbsDTO.getBbsTitle());
            pstmt.setString(3, bbsDTO.getBbsContent());
            pstmt.setString(4, bbsDTO.getUserID());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("write SQLException error");
        } finally {
            close(con, pstmt, null);
        }
        return result;
    }

    public List<BbsDTO> selectList() {
        List<BbsDTO> list = new ArrayList<>();

        try {
            con = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM bbs order by bbsID desc";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
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

    public int hitUpdate(String bbsID) {
        con = DatabaseUtil.getConnection();
        String sql = "UPDATE bbs SET bbsHit = bbsHit + 1 WHERE bbsID = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bbsID);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("hitUpdate SQLException error");
        } finally {
            close(con, pstmt, null);
        }
        return result;
    }

    public BbsDTO selectbyID(String bbsID) {
        BbsDTO bbsDTO = new BbsDTO();
        con = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM bbs WHERE bbsID = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bbsID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                bbsDTO.setBbsID(rs.getInt("bbsID"));
                bbsDTO.setBbsTitle(rs.getString("bbsTitle"));
                bbsDTO.setBbsContent(rs.getString("bbsContent"));
                bbsDTO.setBbsDate(rs.getString("bbsDate"));
                bbsDTO.setBbsHit(rs.getInt("bbsHit"));
                bbsDTO.setUserID(rs.getString("userID"));
            }
        } catch (SQLException e) {
            System.err.println("selectbyID SQLException error");
        } finally {
            close(con, pstmt, rs);
        }
        return bbsDTO;
    }

    public int del(int bbsID) {
        con = DatabaseUtil.getConnection();
        String sql = "DELETE FROM bbs where bbsID = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bbsID);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("delete SQLException error");
        } finally {
            close(con, pstmt, rs);
        }
        return result;
    }

    public int update(BbsDTO bbsDTO) {
        con = DatabaseUtil.getConnection();
        StringBuffer query = new StringBuffer();
        query.append("UPDATE bbs SET bbsTitle = ?, ");
        query.append("bbsContent = ? ");
        query.append("WHERE bbsID = ?");

        try {
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, bbsDTO.getBbsTitle());
            pstmt.setString(2, bbsDTO.getBbsContent());
            pstmt.setInt(3, bbsDTO.getBbsID());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, null);
        }
        return result;
    }


}
