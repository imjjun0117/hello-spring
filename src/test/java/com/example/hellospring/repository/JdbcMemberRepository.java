package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getName());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                member.setId(rs.getLong(1));
            }else{
                throw new SQLException("id 조회 실패");
            }//end else

            return  member;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(conn, pstmt, rs);
        }
    }

    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs){

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
