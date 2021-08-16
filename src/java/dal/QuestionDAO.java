/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Question;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class QuestionDAO {

    public boolean checkExisted(Question question) throws Exception {
        MyDAO myDAO = new MyDAO();
        try {
            String sql = "SELECT [ID],[question] "
                    + "FROM [Question] "
                    + "WHERE [question] = ? "
                    + "AND [option1] = ? "
                    + "AND [option2] = ? "
                    + "AND [option3] = ? "
                    + "AND [option4] = ?";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.prestm.setString(1, question.getQuestion());
            myDAO.prestm.setString(2, question.getOption1());
            myDAO.prestm.setString(3, question.getOption2());
            myDAO.prestm.setString(4, question.getOption3());
            myDAO.prestm.setString(5, question.getOption4());
            myDAO.rs = myDAO.prestm.executeQuery();
            if (myDAO.rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            myDAO.closeConnection();
        }
        return false;
    }

    public void insertQuestion(Question question) throws Exception {
        MyDAO myDAO = new MyDAO();
        try {
            String sql = "INSERT INTO [Question] ([question],[option1],[option2],[option3],[option4],[date],[answer]) "
                    + "VALUES(?,?,?,?,?,?,?)";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.prestm.setString(1, question.getQuestion());
            myDAO.prestm.setString(2, question.getOption1());
            myDAO.prestm.setString(3, question.getOption2());
            myDAO.prestm.setString(4, question.getOption3());
            myDAO.prestm.setString(5, question.getOption4());
            myDAO.prestm.setString(6, question.getDate());
            myDAO.prestm.setString(7, question.getAnswer());
            myDAO.prestm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            myDAO.closeConnection();
        }
    }

    public List<Question> getAllQues() throws Exception {
        MyDAO myDAO = new MyDAO();
        try {
            List<Question> questions = new ArrayList<>();

            String sql = "SELECT [ID],[question],[option1],[option2],[option3],[option4],"
                    + "FORMAT([date],'dd-MMM-yyyy') AS [date],[answer] "
                    + "FROM [Question]"
                    + "ORDER BY [ID]";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.rs = myDAO.prestm.executeQuery();
            while (myDAO.rs.next()) {                
                Question question = new Question();
                question.setId(myDAO.rs.getInt("ID"));
                question.setQuestion(myDAO.rs.getString("question"));
                question.setOption1(myDAO.rs.getString("option1"));
                question.setOption2(myDAO.rs.getString("option2"));
                question.setOption3(myDAO.rs.getString("option3"));
                question.setOption4(myDAO.rs.getString("option4"));
                question.setDate(myDAO.rs.getString("date"));
                question.setAnswer(myDAO.rs.getString("answer"));
                questions.add(question);
            }
            return questions;
        } catch (Exception ex) {
            throw ex;
        } finally{
            myDAO.closeConnection();
        }
    }
    
    public List<Question> pagingForQues(int pageIndex, int pageSize) throws Exception {
        MyDAO myDAO = new MyDAO();
        try {
            List<Question> questions = new ArrayList<>();

            String sql = "SELECT * FROM "
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY ID) AS [No.], "
                    + "[ID],[question],[option1],[option2], [option3],[option4],"
                    + "FORMAT([date],'dd-MMM-yyyy') AS [date],[answer] "
                    + "FROM [Question] ) as x  "
                    + "WHERE [No.] BETWEEN ?*?-? AND ?*?";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.prestm.setInt(1, pageIndex);
            myDAO.prestm.setInt(2, pageSize);
            myDAO.prestm.setInt(3, pageSize-1);
            myDAO.prestm.setInt(4, pageIndex);
            myDAO.prestm.setInt(5, pageSize);
            myDAO.rs = myDAO.prestm.executeQuery();
            while (myDAO.rs.next()) {                
                Question question = new Question();
                question.setId(myDAO.rs.getInt("ID"));
                question.setQuestion(myDAO.rs.getString("question"));
                question.setOption1(myDAO.rs.getString("option1"));
                question.setOption2(myDAO.rs.getString("option2"));
                question.setOption3(myDAO.rs.getString("option3"));
                question.setOption4(myDAO.rs.getString("option4"));
                question.setDate(myDAO.rs.getString("date"));
                question.setAnswer(myDAO.rs.getString("answer"));
                questions.add(question);
            }
            return questions;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public int countQuestion() throws SQLException{
        MyDAO myDAO = new MyDAO();
        try {
            String sql = "SELECT COUNT(*) AS Number FROM [Question]";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.rs = myDAO.prestm.executeQuery();
            if(myDAO.rs.next()){
                return myDAO.rs.getInt("Number");
            }
            return -1;
        } catch (SQLException ex) {
            throw ex;
        }finally{
            myDAO.closeConnection();
        }
    }
    public List<Question> getRandomQuesion(int numOfQues) throws Exception{
        MyDAO myDAO = new MyDAO();
        try {
            List<Question> questions = new ArrayList<>();
            String sql = "SELECT TOP "+numOfQues+" [ID],[question],[option1],[option2],"
                    + "[option3],[option4],[date],[answer] "
                    + "FROM [Question] "
                    + "ORDER BY NEWID()";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.rs = myDAO.prestm.executeQuery();
            while (myDAO.rs.next()){
                Question question = new Question();
                question.setId(myDAO.rs.getInt("ID"));
                question.setQuestion(myDAO.rs.getString("question"));
                question.setOption1(myDAO.rs.getString("option1"));
                question.setOption2(myDAO.rs.getString("option2"));
                question.setOption3(myDAO.rs.getString("option3"));
                question.setOption4(myDAO.rs.getString("option4"));
                question.setDate(myDAO.rs.getString("date"));
                question.setAnswer(myDAO.rs.getString("answer"));
                questions.add(question);
            }
            return questions;
        } catch (Exception ex) {
            throw ex;
        }finally{
            myDAO.closeConnection();
        }
    }
    public void deleteQuesByID(int id) throws Exception{
        MyDAO myDAO = new MyDAO();
        try {
            String sql = "DELETE FROM [Question] WHERE ID = ?";
            myDAO.prestm = myDAO.connection.prepareStatement(sql);
            myDAO.prestm.setInt(1, id);
            myDAO.prestm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }finally{
            myDAO.closeConnection();
        }
    }
}
