package edu.jsu.mcis.cs310.tas_fa22.dao;

import com.mysql.cj.jdbc.ConnectionImpl;
import edu.jsu.mcis.cs310.tas_fa22.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PunchDAO {

    private static final String QUERY_FIND = "SELECT * FROM event WHERE id = ?";

    private final DAOFactory daoFactory;

    PunchDAO(DAOFactory daoFactory) {this.daoFactory = daoFactory;}

    public Punch find(int id){

        Punch punch = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)){
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);

                boolean hasResult = ps.execute();

                if (hasResult){

                    rs = ps.getResultSet();

                    while (rs.next()){
                        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
                        int terminalId = rs.getInt("terminalId");
                        Badge badge = badgeDAO.find(rs.getString("badgeId"));
                        LocalDateTime originalTimeStamp = LocalDateTime.parse(rs.getString("timestamp"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        EventType punchType = EventType.values()[rs.getInt("eventTypeId")];

                        punch = new Punch(id, terminalId, badge,originalTimeStamp,punchType);
                    }
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
        return punch;
    }
}
