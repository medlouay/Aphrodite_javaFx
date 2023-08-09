/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.appointment.model.services;

import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.User;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue.ValueType;

/**
 *
 * @author Lou
 */
public class UserService implements IService<User> {

    Connection cnx = DatabaseHandler.getInstance().getCnx();

    @Override
    public void ajouter(User user) {
        
    String roleString = setRole(user);

    String req = "INSERT INTO user (full_name, username, email, password, roles) "
        + "VALUES ('" + user.getFullName() + "','" + user.getUsername() + "','" + user.getEmail() + "','" + user.getPassword()+ "','" +roleString + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String setRole(User user) {
        String role;
        if (user.isAdmin()) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }   // Convert the role to a JSON array
        JsonArray roleArray = Json.createArrayBuilder()
                .add(role)
                .build();
        String roleString = roleArray.toString();
        return roleString;
    }
    private boolean isUserAdmin(String roleString) {
    JsonReader jsonReader = Json.createReader(new StringReader(roleString));
    JsonStructure jsonStructure = jsonReader.read();

    if (jsonStructure.getValueType() == ValueType.ARRAY) {
        // Get the first element from the array
        String role = ((JsonArray) jsonStructure).getString(0);

        // Check if the role is "ROLE_ADMIN"
        return role.equals("ROLE_ADMIN");
    } else if (jsonStructure.getValueType() == ValueType.OBJECT) {
        // Get the "role" property from the object
        String role = ((JsonObject) jsonStructure).getString("role");

        // Check if the role is "ROLE_ADMIN"
        return role.equals("ROLE_ADMIN");
    } else {
        // The JSON structure is not valid
        throw new JsonException("Invalid JSON structure");
    }
}



    @Override
    public void modifier(User user) {
        String req = "UPDATE user SET full_name='" + user.getFullName() + "', username='" + user.getUsername()
                + "', email='" + user.getEmail() + "', password='" + user.getPassword() + "', isAdmin='" + user.isAdmin() + "' WHERE id=" + user.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(User user) {
        String req = "DELETE FROM user WHERE id = " + user.getId();
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficher() {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setIsAdmin(isUserAdmin(resultSet.getString("roles")));
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching users from database: " + ex.getMessage());
        }

        return userList;
    }
}
