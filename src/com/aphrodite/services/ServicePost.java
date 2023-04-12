package com.aphrodite.services;

import com.aphrodite.entities.Post;
import com.aphrodite.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicePost implements IService<Post> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Post t) {
        String req = "INSERT INTO post (author_id, title, slug, summary, content, published_at) "
                + "VALUES ('" + t.getauthor_id() + "','" + t.getTitle() + "','" + t.getSlug() + "','"
                + t.getSummary() + "','" + t.getContent() + "','" + t.getpublished_at() + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Post ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Post t) {
        String req = "UPDATE post SET author_id='" + t.getauthor_id() + "', title='" + t.getTitle() + "', slug='"
                + t.getSlug() + "', summary='" + t.getSummary() + "', content='" + t.getContent() + "', published_at='"
                + t.getpublished_at() + "' WHERE id=" + t.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Post modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Post t) {
        String req = "DELETE FROM post WHERE id=" + t.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Post supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Post> afficher() {
        List<Post> list = new ArrayList();

        String req = "SELECT * FROM post";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
LocalDateTime publishedAt = LocalDateTime.parse(rs.getString("published_at"), formatter);
                list.add(new Post(rs.getInt("id"), rs.getInt("author_id"), rs.getString("title"),
                        rs.getString("slug"), rs.getString("summary"), rs.getString("content"),rs.getTimestamp("published_at")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}