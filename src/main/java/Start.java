import db.DBH2Connection;

public class Start {
    public static void main(String[] args) {
        DBH2Connection dbh2Connection = new DBH2Connection();
        dbh2Connection.getConnection();
    }
}
