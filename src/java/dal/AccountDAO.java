/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Account;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class AccountDAO {

    public Account getAccByLoginInfor(String username, String password) throws Exception {
        MyDAO myDAO = new MyDAO();
        try {
            String sql = "SELECT [id],[username],[password],[role],[email] "
                    + "FROM [Account] "
                    + "WHERE [username]=? AND [password]=?";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.prestm.setString(1, username);
            myDAO.prestm.setString(2, password);
            myDAO.rs = myDAO.prestm.executeQuery();
            if (myDAO.rs.next()){
                Account account = new Account();
                account.setId(myDAO.rs.getInt("id"));
                account.setUsername(username);
                account.setPassword(password);
                account.setRole(myDAO.rs.getString("role"));
                account.setEmail(myDAO.rs.getString("email"));
                return account;
            }
        } catch (Exception ex) {
            throw ex;
        } finally{
            myDAO.closeConnection();
        }
        return null;
    }
    public boolean checkExisted(String username, String email) throws Exception {
        MyDAO myDAO = new MyDAO();
        try {
            String sql = "SELECT [id],[username],[password],[role],[email] "
                    + "FROM [Account] "
                    + "WHERE [username]=? "
                    + "OR [email] =?";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.prestm.setString(1, username);
            myDAO.prestm.setString(2, email);
            myDAO.rs = myDAO.prestm.executeQuery();
            if (myDAO.rs.next()){
                return true;
            }
        } catch (Exception ex) {
            throw ex;
        } finally{
            myDAO.closeConnection();
        }
        return false;
    }
    public void insertAccount(Account account) throws Exception{
        MyDAO myDAO = new MyDAO();
        try {
            String sql = "INSERT INTO [Account]([username],[password],[role],[email])"
                    + "VALUES(?,?,?,?)";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.prestm.setString(1, account.getUsername());
            myDAO.prestm.setString(2, account.getPassword());
            myDAO.prestm.setString(3, account.getRole());
            myDAO.prestm.setString(4, account.getEmail());
            myDAO.prestm.executeUpdate();
        } catch (Exception ex) {
           throw ex;
        }finally{
            myDAO.closeConnection();
        }
    }
}
