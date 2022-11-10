package edu.jsu.mcis.cs310.tas_fa22.dao;

import edu.jsu.mcis.cs310.tas_fa22.*;
import java.sql.*;
import java.util.HashMap;

public class ShiftDAO {

    private static final String QUERY_FIND = "SELECT * FROM shift WHERE id = ?";
    private static final String QUERY_FIND_BY_BADGE = "SELECT s.id, s.description, s.shiftstart, s.shiftstop,\n" +
            "s.roundinterval, s.graceperiod, s.dockpenalty, s.lunchstart, \n" +
            "s.lunchstop, s.lunchthreshold\n" +
            "from badge b\n" +
            "join employee e on b.id = e.badgeid\n" +
            "join shift s on s.id = e.shiftid\n" +
            "where b.id = ?";

    HashMap<String, String> map = new HashMap<>();

    private final DAOFactory daoFactory;

    ShiftDAO(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    public Shift find(int id){

        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)){

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);

                boolean hasResults = ps.execute();

                if (hasResults) {

                    rs = ps.getResultSet();

                    while (rs.next()){

                        map.put("id", rs.getString("id"));
                        map.put("description", rs.getString("description"));
                        map.put("shiftStart", rs.getString("shiftStart"));
                        map.put("shiftStop", rs.getString("shiftStop"));
                        map.put("roundInterval", rs.getString("roundInterval"));
                        map.put("gracePeriod", rs.getString("gracePeriod"));
                        map.put("dockPenalty", rs.getString("dockPenalty"));
                        map.put("lunchStart", rs.getString("lunchStart"));
                        map.put("lunchStop", rs.getString("lunchStop"));
                        map.put("lunchThreshold", rs.getString("lunchThreshold"));
                    }

                    shift = new Shift(map);
            }
        }
    }
        catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }
        return shift;
    }

    public Shift find(Badge badge){
        Shift shift = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)){
                ps = conn.prepareStatement(QUERY_FIND_BY_BADGE);
                ps.setString(1, badge.getId());

                boolean hasResults = ps.execute();

                if (hasResults){
                    rs = ps.getResultSet();

                    while (rs.next()){
                        map.put("id", rs.getString("id"));
                        map.put("description", rs.getString("description"));
                        map.put("shiftStart", rs.getString("shiftStart"));
                        map.put("shiftStop", rs.getString("shiftStop"));
                        map.put("roundInterval", rs.getString("roundInterval"));
                        map.put("gracePeriod", rs.getString("gracePeriod"));
                        map.put("dockPenalty", rs.getString("dockPenalty"));
                        map.put("lunchStart", rs.getString("lunchStart"));
                        map.put("lunchStop", rs.getString("lunchStop"));
                        map.put("lunchThreshold", rs.getString("lunchThreshold"));
                    }
                    //create a shift class with this hash map
                    shift = new Shift(map);
                }
            }
        }
        catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }
        return shift;
    }

}