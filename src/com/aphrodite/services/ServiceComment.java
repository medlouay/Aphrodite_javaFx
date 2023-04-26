package com.aphrodite.services;

import com.aphrodite.entities.Comment;
import com.aphrodite.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceComment implements IService<Comment> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Comment c) {
/*        String req = "INSERT INTO comment (post_id, author_id, content, published_at) VALUES ('" + c.getpost_id() + "','" + c.getauthor_id() + "','" + c.getContent() + "','" + c.getpublished_at() + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Comment ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
                String reqa = "INSERT INTO comment (post_id, author_id, content, published_at) VALUES( ?, ? ,? ,? );";
        try {
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(reqa);

            pst.setInt(1, c.getpost_id());
            pst.setInt(2, c.getauthor_id());
            pst.setString(3,c.getContent());
            pst.setTimestamp(4, c.getpublished_at());
            pst.executeUpdate();
            System.out.println("review ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Comment c) {
        String req = "UPDATE comment SET post_id='" + c.getpost_id() + "', author_id='" + c.getauthor_id() + "', content='" + c.getContent() + "', published_at='" + c.getpublished_at() + "' WHERE id=" + c.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Comment modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public boolean update(Comment c) {
        String req = "UPDATE comment SET post_id='" + c.getpost_id() + "', author_id='" + c.getauthor_id() + "', content='" + c.getContent() + "', published_at='" + c.getpublished_at() + "' WHERE id=" + c.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Comment modifié !");
            return true ;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false ;
    }

    @Override
    public void supprimer(Comment c) {
        String req = "DELETE FROM comment WHERE id=" + c.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Comment supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimer(int  id)throws SQLException {
 
    PreparedStatement ps = cnx.prepareStatement("DELETE FROM Comment WHERE id = ?");
    ps.setInt(1, id);
    ps.executeUpdate();
    }
    
    @Override
    public List<Comment> afficher() {
        List<Comment> list = new ArrayList<>();

        String req = "SELECT * FROM comment";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                list.add(new Comment(rs.getInt("id"), rs.getInt("post_id"), rs.getInt("author_id"), rs.getString("content"), rs.getTimestamp("published_at")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public Comment getCommentId(String text){
   Comment c = new Comment() ;
    
        String req = "SELECT * FROM comment WHERE content='" +text+"';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                 c.setId(rs.getInt("id"));
                 c.setContent(rs.getString("content"));
                 c.setauthor_id(rs.getInt("author_id"));
                 c.setpost_id(rs.getInt("post_id"));
                 c.setpublished_at(rs.getTimestamp("published_at"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return c ;
    }

    public List<Comment> ShowByPost( int id  ) {
        List<Comment> list = new ArrayList<>();

        String req = "SELECT * FROM comment WHERE post_id =" + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                list.add(new Comment(rs.getInt("id"), rs.getInt("post_id"), rs.getInt("author_id"), rs.getString("content"), rs.getTimestamp("published_at")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
 public void deleteComment(int commentId) throws SQLException {
 
    PreparedStatement ps = cnx.prepareStatement("DELETE FROM Comment WHERE id = ?");
    ps.setInt(1, commentId);
    ps.executeUpdate();
}

    public Comment getById(int commentId) {
  Comment c = new Comment() ;
    
        String req = "SELECT * FROM comment WHERE id=" +commentId+";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                 c.setId(rs.getInt("id"));
                 c.setContent(rs.getString("content"));
                 c.setauthor_id(rs.getInt("author_id"));
                 c.setpost_id(rs.getInt("post_id"));
                 c.setpublished_at(rs.getTimestamp("published_at"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return c ;    }


}
