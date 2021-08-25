/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.DBContext;
import dao.IAccountDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

/**
 *
 * @author DELL
 */
public class AccountDAO extends DBContext implements IAccountDAO{

    @Override
    public boolean checkExisted(String username, String email) throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            String sql = "SELECT [acc_id],[username],[password],[isTeacher],[email] "
                    + "FROM [Account] "
                    + "WHERE [username]=? "
                    + "OR [email] =?";
            prestm = connection.prepareStatement(sql);
            prestm.setString(1, username);
            prestm.setString(2, email);
            rs = prestm.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (Exception ex) {
            throw ex;
        } finally{
            closeConnection(rs, prestm, connection);
        }
        return false;
    }

    @Override
    public Account getAccByLoginInfor(String username, String password) throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        
        try {
            connection = getConnection();
            String sql = "SELECT [acc_id],[username],[password],[isTeacher],[email] "
                    + "FROM [Account] "
                    + "WHERE [username]=? AND [password]=?";
            prestm = connection.prepareStatement(sql);
            prestm.setString(1, username);
            prestm.setString(2, password);
            rs = prestm.executeQuery();
            if (rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("acc_id"));
                account.setUsername(username);
                account.setPassword(password);
                account.setIsTeacher(rs.getBoolean("isTeacher"));
                account.setEmail(rs.getString("email"));
                return account;
            }
        } catch (Exception ex) {
            throw ex;
        } finally{
            closeConnection(rs, prestm, connection);
        }
        return null;
    }

    @Override
    public void insertAccount(Account account) throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO [Account] ([username],[password],[isTeacher],[email]) "
                    + "VALUES(?,?,?,?)";
            prestm = connection.prepareStatement(sql);
            prestm.setString(1, account.getUsername());
            prestm.setString(2, account.getPassword());
            prestm.setBoolean(3, account.isIsTeacher());
            prestm.setString(4, account.getEmail());
            prestm.executeUpdate();
        } catch (Exception ex) {
           throw ex;
        }finally{
            closeConnection(rs, prestm, connection);
        }
    }
   
}
