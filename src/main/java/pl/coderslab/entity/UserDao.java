package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DBUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String UPDATE_USER = "update users set email = ?, set userName = ?, set password = ? where id = ? ";


    private static final String SELECT_USER_BYID = "select * from users where  id = ?";

    private static final String DELETE_USER_BYID = "delete * from users where  id = ?";

    private static final String SHOW_ALL_USERS = "select * from users ";

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User read(int userId){
        try (Connection conn = DBUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(SELECT_USER_BYID);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new User(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("email"), resultSet.getString("password"));
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void update(User user){
        try( Connection conn = DBUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4,user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            user.setId(0);
            e.printStackTrace();
        }

    }
    public void delete(int userId){
        try(Connection conn = DBUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_BYID);
            statement.setInt(1,userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean deleteWithConfirmation(int userId){
        try(Connection conn = DBUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_BYID);
            statement.setInt(1,userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private User[] addToArray(User u, User[] users){
        User[] tmpUsersArray = Arrays.copyOf(users, users.length + 1);
        tmpUsersArray[users.length] = u;
        return tmpUsersArray;
    }
    public User[] findAll(){
        try ( Connection conn = DBUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(SHOW_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            User[] users = new User[0];
            while (resultSet.next()){
                User user = new User(resultSet.getInt("id"),resultSet.getString("username"), resultSet.getString("email"),resultSet.getString("password") );
                users = addToArray( user,users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}

