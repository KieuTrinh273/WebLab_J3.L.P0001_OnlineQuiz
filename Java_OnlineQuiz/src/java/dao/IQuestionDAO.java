/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Question;

/**
 *
 * @author DELL
 */
public interface IQuestionDAO {
//    Check if a question already exists in database. 
//    If exists, return true. Otherwise, return false.
    boolean checkExisted(Question question) throws Exception;
    
//    Count then return number of questions in database. 
    int countQuestion() throws SQLException;
    
//    insert a new question into database
    void insertQuestion(Question question) throws Exception;
    
//    return list of all questions in database
    List<Question> getAllQues() throws Exception;
    
//    return numOfQues random questions from database 
    List<Question> getRandomQuesion(int numOfQues) throws Exception;
    
//    Returns the list of pageSize questions contained in the page that index = pageIndex
    List<Question> pagingForQues(int pageIndex, int pageSize) throws Exception;
    
//    delete question has id = id from database
    void deleteQuesByID(int id) throws Exception;
    
}
