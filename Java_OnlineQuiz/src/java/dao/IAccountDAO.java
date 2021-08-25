/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Account;

/**
 *
 * @author DELL
 */
public interface IAccountDAO {    
//    Check if username or email already exists in database. 
//    If username/email exists, return true. Otherwise, return false.
    boolean checkExisted(String username, String email) throws Exception;
    
//    return account have correspond username and password
    Account getAccByLoginInfor(String username, String password) throws Exception;
    
//    insert new account to database
    void insertAccount(Account account) throws Exception;
    
}
