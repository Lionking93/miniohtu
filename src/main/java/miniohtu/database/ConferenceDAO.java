
package miniohtu.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import miniohtu.entry.Conference;

public class ConferenceDAO extends BaseDAO<Conference> {

    public ConferenceDAO(Database db) {
        super(db);
    }
    
    @Override
    public void add(Conference entry) throws SQLException {
        String sql = "INSERT INTO CONFERENCE VALUES ("
                + s(entry.getCitationKey()) + ", "
                + s(entry.getAuthor()) + ", "
                + s(entry.getTitle()) + ", "
                + s(entry.getBookTitle()) + ", "
                + entry.getYear() + ", "
                + s(entry.getEditor()) + ", "
                + s(entry.getPages()) + ", "
                + s(entry.getOrganization()) + ", "
                + s(entry.getPublisher()) + ", "
                + s(entry.getAddress()) + ", "
                + entry.getMonth() + ", "
                + s(entry.getNote()) + ", "
                + s(entry.getKey()) + " );";
        
        this.db.update(sql);
    }

    private String s(String s) {
        if (s == null) return null;
        return "'" + s + "'";
    }
    
    @Override
    public List<Conference> findAll() throws SQLException {
        return db.queryAndCollect("SELECT * FROM CONFERENCE", (ResultSet rs) -> {
           return new Conference(
                rs.getString("citationKey"),
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("bookTitle"),
                rs.getInt("year"),
                rs.getString("editor"),
                rs.getString("pages"),
                rs.getString("organization"),
                rs.getString("publisher"),
                rs.getString("address"),
                rs.getInt("month"),
                rs.getString("note"),
                rs.getString("key")); 
        });
    }

    @Override
    public Conference find(String citationKey) throws SQLException {
        List<Conference> matches = db.queryAndCollect("SELECT * FROM CONFERENCE WHERE CITATIONKEY='" + citationKey + "'", rs -> {
           return new Conference(
                rs.getString("citationKey"),
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("bookTitle"),
                rs.getInt("year"),
                rs.getString("editor"),
                rs.getString("pages"),
                rs.getString("organization"),
                rs.getString("publisher"),
                rs.getString("address"),
                rs.getInt("month"),
                rs.getString("note"),
                rs.getString("key")); 
        });
        
        return matches.isEmpty() ? null : matches.get(0);
    }
}
