package Utils;

import Main.Collection;
import MusicBand.MusicBand;
import MusicBand.MusicBandBuilder;
import MusicBand.MusicGenre;

import java.sql.*;
import java.time.LocalDateTime;

public class DataBaseManager {
    public static Connection connection;

    public static void connectToDataBase() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String password = "8864";
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
            connection = DriverManager.getConnection(url,login,password);
            System.out.println("Database is successfully connected.");
        } catch (ClassNotFoundException | InstantiationException | SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createCollectionDB() throws SQLException {
        String createTableSQL = "CREATE TABLE Collection("
                + "sequenceid BIGINT NOT NULL PRIMARY KEY, "
                + "name VARCHAR NOT NULL, "
                + "x INTEGER NOT NULL, "
                + "y BIGINT NOT NULL, "
                + "creationdate VARCHAR NOT NULL, "
                + "numberofparticipants INTEGER, "
                + "genre VARCHAR, "
                + "namealbum VARCHAR NOT NULL, "
                + "lengthalbum BIGINT NOT NULL,"
                + "username VARCHAR NOT NULL)";
        Statement statement = connection.createStatement();
        statement.execute(createTableSQL);
    }

    public static void createSequenceForId() throws SQLException {
        String createSequence = "CREATE SEQUENCE sequenceid " +
                "START WITH 1";
        Statement statement = connection.createStatement();
        statement.execute(createSequence);
    }


    public static void createUsersDB() throws SQLException {
        String createTableSQL = "CREATE TABLE users("
                + "username VARCHAR NOT NULL PRIMARY KEY,"
                + "password VARCHAR NOT NULL)";
        Statement statement = connection.createStatement();
        statement.execute(createTableSQL);
    }

    public static void insertIntoTheCollection(MusicBand musicBand) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO collection" +
                "(sequenceid, name, x, y, creationdate, numberofparticipants, genre, namealbum," +
                "lengthalbum, username) VALUES (nextval('sequenceid'), ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, musicBand.getName());
        preparedStatement.setInt(2, musicBand.getCoordinates().getX());
        preparedStatement.setLong(3, musicBand.getCoordinates().getY());
        preparedStatement.setString(4, musicBand.getCreationDate().toString());
        preparedStatement.setInt(5, musicBand.getNumberOfParticipants());
        if (musicBand.getGenre()==null) preparedStatement.setString(6, null);
        else preparedStatement.setString(6, musicBand.getGenre().toString());
        preparedStatement.setString(7, musicBand.getBestAlbum().getName());
        preparedStatement.setLong(8, musicBand.getBestAlbum().getLength());
        preparedStatement.setString(9, musicBand.getUsername());
        preparedStatement.execute();
    }

    public static void insertIntoUsers(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO" +
                " users (username, password) VALUES (?,?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
    }

    public static Boolean isExistUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(" SELECT * FROM users WHERE username = ?");
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }

    public static Boolean isExistUser(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(" SELECT * FROM users WHERE username = ?");
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) return rs.getString(1).equals(username)&&rs.getString(2).equals(password);
        return false;
    }

    public static Long getId(MusicBand musicBand, String username) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(" SELECT * FROM collection WHERE username = ?");
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        Long id = null;
        while (rs.next()) id = rs.getLong(1);
        return id;
    }


    public static Boolean updateById(MusicBand musicBand, String username, Long id) throws SQLException{
        if (checkUsername(username, musicBand.getId())) {
            PreparedStatement ps = connection.prepareStatement("UPDATE collection SET" +
                    " name = ?, x = ?, y = ?, creationdate = ?, numberofparticipants = ?," +
                    " genre = ?, namealbum = ?, lengthalbum = ?, username = ? " +
                    "WHERE sequenceid = " + id);
            ps.setString(1, musicBand.getName());
            ps.setInt(2, musicBand.getCoordinates().getX());
            ps.setLong(3, musicBand.getCoordinates().getY());
            ps.setString(4, musicBand.getCreationDate().toString());
            ps.setInt(5, musicBand.getNumberOfParticipants());
            ps.setString(6, musicBand.getGenre().toString());
            ps.setString(7, musicBand.getBestAlbum().getName());
            ps.setLong(8, musicBand.getBestAlbum().getLength());
            ps.setString(9, username);
            ps.execute();
            return true;
        } else {
            return false;
        }
    }

    public static Boolean checkUsername(String username, Long id) throws  SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(" SELECT * FROM collection WHERE sequenceid = ? AND username = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, username);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }


    public static void clear() throws  SQLException{
        String clearTheCollection = "DELETE FROM collection";
        Statement statement = connection.createStatement();
        statement.execute(clearTheCollection);
    }


    public static Boolean removeById(Long id, String username) throws  SQLException{
        if (checkUsername(username, id)) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM collection WHERE sequenceid = ? AND username = ?");
            ps.setLong(1, id);
            ps.setString(2, username);
            ps.execute();
            return true;
        } else {
            return false;
        }
    }

    public static void fillTheMemory() throws SQLException, IllegalStateException {
        String data = " SELECT * FROM collection";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(data);
        while (rs.next()) {
            MusicBandBuilder musicBandBuilder = new MusicBandBuilder()
                    .setId(rs.getLong(1))
                    .setName(rs.getString(2))
                    .setCoordinate(rs.getInt(3), rs.getLong(4))
                    .setCreationDate(LocalDateTime.parse(rs.getString(5)))
                    .setNumberOfParticipants(rs.getInt(6))
                    .setGenre(MusicGenre.valueOf(rs.getString(7)))
                    .setAlbum(rs.getString(8), rs.getLong(9))
                    .setUsername(rs.getString(10));
            Collection.add(musicBandBuilder.build());
        }
    }

}
