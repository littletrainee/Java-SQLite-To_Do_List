import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlite {
    // ! 連線字串
    private String ConnectString = "jdbc:sqlite:ToDOList.db";
    private Connection Connection = null;

    public void SelectItem() {
        try {
            this.Connection = DriverManager.getConnection(this.ConnectString);
            String query = "SELECT COUNT(*) FROM TO_DO_LIST;";
            System.out.println("Connection to SQLite has been established.");
            Statement statement = this.Connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int Totalquantity = resultSet.getInt(1);

            query = "SELECT * FROM TO_DO_LIST;";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("ID") + "|" + resultSet.getString("TITLE")
                        + "|");
                switch (resultSet.getInt("STATUS")) {
                    case 0:
                        System.out.println("TODO");
                        break;
                    case 1:
                        System.out.println("DONE");
                        break;
                }
            }
            System.out.println("Total Row Is: " + Totalquantity);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (this.Connection != null) {
                this.Connection = null;
            }
        }
    }

    public int AddItem(String Title) {
        int count = 0;
        try {
            this.Connection = DriverManager.getConnection(this.ConnectString);
            String Query = "INSERT INTO TO_DO_LIST (TITLE) VALUES (?);";
            PreparedStatement preparedStatement = this.Connection.prepareStatement(Query);
            preparedStatement.setString(1, Title);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (this.Connection != null) {
                this.Connection = null;
            }
        }
        return count;
    }

    public int ChangeStatus(int index) {
        int count = 0;
        try {
            this.Connection = DriverManager.getConnection(this.ConnectString);
            String Query = "UPDATE TO_DO_LIST SET STATUS = 1 WHERE ID = ?";
            PreparedStatement preparedStatement = this.Connection.prepareStatement(Query);
            preparedStatement.setInt(1, index);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (this.Connection != null) {
                this.Connection = null;
            }
        }
        return count;
    }

    public int ChangeTitle(int index, String newTitle) {
        int count = 0;
        try {
            this.Connection = DriverManager.getConnection(this.ConnectString);
            String Query = "UPDATE TO_DO_LIST SET TITLE = ? WHERE ID = ?;";
            PreparedStatement preparedStatement = this.Connection.prepareStatement(Query);
            preparedStatement.setString(1, newTitle);
            preparedStatement.setInt(2, index);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (this.Connection != null) {
                this.Connection = null;
            }
        }
        return count;
    }

    public int DeleteTitle(int index) {
        int count = 0;
        try {
            this.Connection = DriverManager.getConnection(this.ConnectString);
            String Query = "DELETE FROM TO_DO_LIST WHERE ID = ?;";
            PreparedStatement preparedStatement = this.Connection.prepareStatement(Query);
            preparedStatement.setInt(1, index);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (this.Connection != null) {
                this.Connection = null;
            }
        }
        return count;
    }
}
