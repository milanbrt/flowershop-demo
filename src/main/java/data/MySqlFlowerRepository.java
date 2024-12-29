package data;

import data.util.MySqlConnection;
import domain.Flower;
import util.SocialsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlFlowerRepository implements FlowerRepository {
    private static final Logger LOGGER = Logger.getLogger(MySqlFlowerRepository.class.getName());

    private static final String SQL_GET_FLOWERS = "SELECT * FROM flowers";


    @Override
    public List<Flower> getFlowers() {
        List<Flower> flowers = new ArrayList<>();
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_GET_FLOWERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Flower flower = new Flower(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
                flowers.add(flower);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve flowers from database.", ex);
            throw new SocialsException("Unable to retrieve flowers");
        }
        return flowers;
    }

}
