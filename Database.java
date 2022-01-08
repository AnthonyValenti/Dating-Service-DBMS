/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author anthonyvalenti
 */
public class Database {

    private int currUserId;

    public Database() {

    }

    public void setCurrUserId(String name, String password) {
        Connection conn1 = null;
        int temp = 0;
        try {
            String dbURL1 = "jdbc:oracle:thin:alvalent/06128260@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
            conn1 = DriverManager.getConnection(dbURL1);
            String query = "select user_id from USERDB WHERE userdb.name='" + name + "'and userdb.password='" + password + "'";
            try (Statement stmt = conn1.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    temp = rs.getInt("USER_ID");
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        this.currUserId = temp;
    }

    public ArrayList<String> getLoginInfo() {
        Connection conn1 = null;

        try {
            String dbURL1 = "jdbc:oracle:thin:alvalent/06128260@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
            conn1 = DriverManager.getConnection(dbURL1);
            String query = "select NAME,PASSWORD from USERDB";

            try (Statement stmt = conn1.createStatement()) {
                ArrayList<String> loginInfo = new ArrayList<String>();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String name = rs.getString("NAME");
                    String password = rs.getString("PASSWORD");
                    loginInfo.add(name + "," + password);
                }
                return loginInfo;

            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    public void createAccount(String name, String password, int age, String gender, String school, String postal) throws Exception {
        Connection conn1 = null;
        int userId = 0;
        try {
            String dbURL1 = "jdbc:oracle:thin:alvalent/06128260@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
            conn1 = DriverManager.getConnection(dbURL1);
            try (Statement stmt = conn1.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM userDB");
                while (rs.next()) {
                    userId = 1 + rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
            String insertUser = "INSERT INTO userDB (user_id, password, name, age, gender, school, postal_code) VALUES ('" + userId + "', '" + password + "', '" + name + "', '" + age + "', '" + gender + "', '" + school + "', '" + postal + "')";
            try (Statement stmt = conn1.createStatement()) {
                stmt.executeUpdate(insertUser);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addMatchingReq(String gender_pref, int maxKm, int minAge, int maxAge) throws Exception {
        Connection conn1 = null;
        int userId = 0;
        try {
            String dbURL1 = "jdbc:oracle:thin:alvalent/06128260@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
            conn1 = DriverManager.getConnection(dbURL1);
            try (Statement stmt = conn1.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM userDB");
                while (rs.next()) {
                    userId = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
            String insertMatchReq = "INSERT INTO MATCHING_REQUIREMENTS (USER_ID, GENDER_PREF, MAX_KM, MIN_AGE, MAX_AGE) VALUES (" + userId + ", '" + gender_pref + "', " + maxKm + ", " + minAge + ", " + maxAge + ")";
            try (Statement stmt = conn1.createStatement()) {
                stmt.executeUpdate(insertMatchReq);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void editMatchingReq(String gender_pref, int maxKm, int minAge, int maxAge) throws Exception {
        Connection conn1 = null;
        try {
            String dbURL1 = "jdbc:oracle:thin:alvalent/06128260@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
            conn1 = DriverManager.getConnection(dbURL1);
            String insertMatchReq = "UPDATE MATCHING_REQUIREMENTS SET GENDER_PREF='" + gender_pref + "', MAX_KM=" + maxKm + ", MIN_AGE=" + minAge + ", MAX_AGE=" + maxAge + " WHERE user_id= " + currUserId + "";
            try (Statement stmt = conn1.createStatement()) {
                stmt.executeUpdate(insertMatchReq);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addMatchingComp(String favMovie, String favMusicArtist, String favSport, String favHobby, String favSong) throws Exception {
        Connection conn1 = null;
        int userId = 0;
        try {
            String dbURL1 = "jdbc:oracle:thin:alvalent/06128260@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
            conn1 = DriverManager.getConnection(dbURL1);
            try (Statement stmt = conn1.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM userDB");
                while (rs.next()) {
                    userId = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
            String insertMatchComp = "INSERT INTO matching_compatibility (user_id, favourite_movie, favourite_musicArtist, favourite_sport, favourite_hobby, favourite_song) VALUES (" + userId + ", '" + favMovie + "', '" + favMusicArtist + "', '" + favSport + "', '" + favHobby + "', '" + favSong + "')";
            try (Statement stmt = conn1.createStatement()) {
                stmt.executeUpdate(insertMatchComp);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void findMatches() {
        Connection conn1 = null;
        String genderPref = "";
        int minAge = 0;
        int maxAge = 0;
        try {
            String dbURL1 = "jdbc:oracle:thin:alvalent/06128260@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database
            conn1 = DriverManager.getConnection(dbURL1);
            String getReq = "SELECT gender_pref, min_age, max_age FROM MATCHING_REQUIREMENTS WHERE user_id=" + currUserId + "";
            try (Statement stmt = conn1.createStatement()) {
                ResultSet rs = stmt.executeQuery(getReq);
                while (rs.next()) {
                    genderPref = rs.getString("gender_pref");
                    minAge = rs.getInt("min_age");
                    maxAge = rs.getInt("max_age");
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
            String possibleMatch = "SELECT name, age FROM USERDB WHERE userdb.gender='" + genderPref + "'and userdb.age<='" + maxAge + "' and userdb.age>='" + minAge + "' ";
            try (Statement stmt = conn1.createStatement()) {
                ResultSet rs = stmt.executeQuery(possibleMatch);
                while (rs.next()) {
                    mainScreen.addItem(new Person(rs.getString("name"), rs.getInt("age")));
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void deleteAcc() {
        Connection conn1 = null;
        try {
            String dbURL1 = "jdbc:oracle:thin:alvalent/06128260@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database
            conn1 = DriverManager.getConnection(dbURL1);
            String deleteReq = "DELETE FROM MATCHING_REQUIREMENTS WHERE user_id=" + currUserId + "";
            String deleteComp = "DELETE FROM MATCHING_COMPATIBILITY WHERE user_id=" + currUserId + "";
            String deleteUser = "DELETE FROM USERDB WHERE user_id=" + currUserId + "";

            try (Statement stmt = conn1.createStatement()) {
                stmt.executeUpdate(deleteReq);
                stmt.executeUpdate(deleteComp);
                stmt.executeUpdate(deleteUser);

            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) {
                    conn1.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
