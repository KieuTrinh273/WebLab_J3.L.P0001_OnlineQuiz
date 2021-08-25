/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.DBContext;
import dao.IQuestionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Question;

/**
 *
 * @author DELL
 */
public class QuestionDAO extends DBContext implements IQuestionDAO{

    @Override
    public boolean checkExisted(Question question) throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            String sql = "SELECT [option1],[option2],[option3],[option4],[ques] FROM [Question]"
                    + "WHERE [ques] = ? "
                    + "AND [option1] = ? "
                    + "AND [option2] = ? "
                    + "AND [option3] = ? "
                    + "AND [option4] = ?";
            prestm = connection.prepareStatement(sql);
            prestm.setString(1, question.getQuestion());
            prestm.setString(2, question.getOption1());
            prestm.setString(3, question.getOption2());
            prestm.setString(4, question.getOption3());
            prestm.setString(5, question.getOption4());
            rs = prestm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, prestm, connection);
        }
        return false;
    }

    @Override
    public int countQuestion() throws SQLException {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            String sql = "SELECT COUNT(*) AS Number FROM [Question]";
            prestm = connection.prepareStatement(sql);
            rs = prestm.executeQuery();
            if(rs.next()){
                return rs.getInt("Number");
            }
            return -1;
        } catch (SQLException ex) {
            throw ex;
        }finally{
            closeConnection(rs, prestm, connection);
        }
    }

    @Override
    public void insertQuestion(Question question) throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO [Question] ([ques],[option1],[option2],[option3],[option4],[date],[correctOpt]) "
                    + "VALUES(?,?,?,?,?,GETDATE(),?)";
            prestm = connection.prepareStatement(sql);
            prestm.setString(1, question.getQuestion());
            prestm.setString(2, question.getOption1());
            prestm.setString(3, question.getOption2());
            prestm.setString(4, question.getOption3());
            prestm.setString(5, question.getOption4());
            prestm.setString(6, question.getAns());
            prestm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(rs, prestm, connection);
        }
    }

    @Override
    public List<Question> getAllQues() throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            List<Question> questions = new ArrayList<>();

            String sql = "SELECT [ques_id],[option1],[option2],[option3],[option4],[correctOpt],[ques],[date]"
                    + "FROM [Question]"
                    + "ORDER BY [ques_id]";
            prestm = connection.prepareStatement(sql);
            rs = prestm.executeQuery();
            while (rs.next()) {                
                Question question = new Question();
                question.setId(rs.getInt("ques_id"));
                question.setQuestion(rs.getString("ques"));
                question.setOption1(rs.getString("option1"));
                question.setOption2(rs.getString("option2"));
                question.setOption3(rs.getString("option3"));
                question.setOption4(rs.getString("option4"));
                question.setDate(rs.getDate("date"));
                question.setAns(rs.getString("correctOpt"));
                questions.add(question);
            }
            return questions;
        } catch (Exception ex) {
            throw ex;
        } finally{
            closeConnection(rs, prestm, connection);
        }
    }

    @Override
    public List<Question> getRandomQuesion(int numOfQues) throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            List<Question> questions = new ArrayList<>();
            String sql = "SELECT TOP "+numOfQues+" [ques_id],[ques],[option1],[option2],"
                    + "[option3],[option4],[date],[correctOpt] "
                    + "FROM [Question] "
                    + "ORDER BY NEWID()";
            prestm = connection.prepareStatement(sql);
            rs = prestm.executeQuery();
            while (rs.next()){
                Question question = new Question();
                question.setId(rs.getInt("ques_id"));
                question.setQuestion(rs.getString("ques"));
                question.setOption1(rs.getString("option1"));
                question.setOption2(rs.getString("option2"));
                question.setOption3(rs.getString("option3"));
                question.setOption4(rs.getString("option4"));
                question.setDate(rs.getDate("date"));
                question.setAns(rs.getString("correctOpt"));
                questions.add(question);
            }
            return questions;
        } catch (Exception ex) {
            throw ex;
        }finally{
            closeConnection(rs, prestm, connection);
        }
    }

    @Override
    public List<Question> pagingForQues(int pageIndex, int pageSize) throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection =getConnection();
            List<Question> questions = new ArrayList<>();

            String sql = "SELECT * FROM "
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY ques_id) AS [No.], "
                    + "[ques_id],[ques],[option1],[option2], [option3],[option4],"
                    + "[date],[correctOpt] "
                    + "FROM [Question] ) as x  "
                    + "WHERE [No.] BETWEEN ?*?-? AND ?*?";
            prestm = connection.prepareStatement(sql);
            prestm.setInt(1, pageIndex);
            prestm.setInt(2, pageSize);
            prestm.setInt(3, pageSize-1);
            prestm.setInt(4, pageIndex);
            prestm.setInt(5, pageSize);
            rs = prestm.executeQuery();
            while (rs.next()) {                
                Question question = new Question();
                question.setId(rs.getInt("ques_id"));
                question.setQuestion(rs.getString("ques"));
                question.setOption1(rs.getString("option1"));
                question.setOption2(rs.getString("option2"));
                question.setOption3(rs.getString("option3"));
                question.setOption4(rs.getString("option4"));
                question.setDate(rs.getDate("date"));
                question.setAns(rs.getString("correctOpt"));
                questions.add(question);
            }
            return questions;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void deleteQuesByID(int id) throws Exception {
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            String sql = "DELETE FROM [Question] WHERE ques_id = ?";
            prestm = connection.prepareStatement(sql);
            prestm.setInt(1, id);
            prestm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }finally{
            closeConnection(rs, prestm, connection);
        }
    }
    
}
