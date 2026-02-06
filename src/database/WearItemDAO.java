package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WearItemDAO {

    // ---------- CREATE (Week 7) ----------
    public boolean insertTShirt(TShirt t) {
        String sql = "INSERT INTO wear_item (item_type, name, price, size) VALUES ('TSHIRT', ?, ?, ?)";
        return executeInsert(sql, ps -> {
            ps.setString(1, t.getName());
            ps.setDouble(2, t.getPrice());
            ps.setString(3, t.getSize());
        });
    }

    public boolean insertJacket(Jacket j) {
        String sql = "INSERT INTO wear_item (item_type, name, price, material, hooded) VALUES ('JACKET', ?, ?, ?, ?)";
        return executeInsert(sql, ps -> {
            ps.setString(1, j.getName());
            ps.setDouble(2, j.getPrice());
            ps.setString(3, j.getMaterial());
            ps.setBoolean(4, j.isHooded());
        });
    }

    public boolean insertPants(Pants p) {
        String sql = "INSERT INTO wear_item (item_type, name, price, fit, length_cm) VALUES ('PANTS', ?, ?, ?, ?)";
        return executeInsert(sql, ps -> {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getFit());
            ps.setInt(4, p.getLengthCm());
        });
    }

    // ---------- READ (Week 7) ----------
    public List<Wear> getAllItems() {
        String sql = "SELECT * FROM wear_item ORDER BY item_id";
        return executeSelectMany(sql, ps -> {}, this::fromRow);
    }

    public Wear getItemById(int id) {
        String sql = "SELECT * FROM wear_item WHERE item_id = ?";
        List<Wear> list = executeSelectMany(sql, ps -> ps.setInt(1, id), this::fromRow);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Wear> getByType(String type) {
        String sql = "SELECT * FROM wear_item WHERE item_type = ? ORDER BY item_id";
        return executeSelectMany(sql, ps -> ps.setString(1, type), this::fromRow);
    }

    // ---------- UPDATE (Week 8) ----------
    public boolean updateTShirt(TShirt t) {
        String sql = "UPDATE wear_item SET name = ?, price = ?, size = ? " +
                "WHERE item_id = ? AND item_type = 'TSHIRT'";
        return executeUpdate(sql, ps -> {
            ps.setString(1, t.getName());
            ps.setDouble(2, t.getPrice());
            ps.setString(3, t.getSize());
            ps.setInt(4, t.getId());
        });
    }

    public boolean updateJacket(Jacket j) {
        String sql = "UPDATE wear_item SET name = ?, price = ?, material = ?, hooded = ? " +
                "WHERE item_id = ? AND item_type = 'JACKET'";
        return executeUpdate(sql, ps -> {
            ps.setString(1, j.getName());
            ps.setDouble(2, j.getPrice());
            ps.setString(3, j.getMaterial());
            ps.setBoolean(4, j.isHooded());
            ps.setInt(5, j.getId());
        });
    }

    public boolean updatePants(Pants p) {
        String sql = "UPDATE wear_item SET name = ?, price = ?, fit = ?, length_cm = ? " +
                "WHERE item_id = ? AND item_type = 'PANTS'";
        return executeUpdate(sql, ps -> {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getFit());
            ps.setInt(4, p.getLengthCm());
            ps.setInt(5, p.getId());
        });
    }

    // ---------- DELETE (Week 8) ----------
    public boolean deleteItem(int id) {
        String sql = "DELETE FROM wear_item WHERE item_id = ?";
        return executeUpdate(sql, ps -> ps.setInt(1, id));
    }

    // ---------- SEARCH (Week 8) ----------
    public List<Wear> searchByName(String namePart) {
        String sql = "SELECT * FROM wear_item WHERE name ILIKE ? ORDER BY name";
        return executeSelectMany(sql, ps -> ps.setString(1, "%" + namePart + "%"), this::fromRow);
    }

    public List<Wear> searchByPriceRange(double min, double max) {
        String sql = "SELECT * FROM wear_item WHERE price BETWEEN ? AND ? ORDER BY price DESC";
        return executeSelectMany(sql, ps -> {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
        }, this::fromRow);
    }

    public List<Wear> searchByMinPrice(double min) {
        String sql = "SELECT * FROM wear_item WHERE price >= ? ORDER BY price DESC";
        return executeSelectMany(sql, ps -> ps.setDouble(1, min), this::fromRow);
    }

    // ---------- helpers ----------
    private interface StatementFiller { void fill(PreparedStatement ps) throws SQLException; }
    private interface RowMapper<T> { T map(ResultSet rs) throws SQLException; }

    private boolean executeInsert(String sql, StatementFiller filler) {
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            filler.fill(ps);
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("❌ INSERT failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    private boolean executeUpdate(String sql, StatementFiller filler) {
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            filler.fill(ps);
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("❌ UPDATE/DELETE failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    private <T> List<T> executeSelectMany(String sql, StatementFiller filler, RowMapper<T> mapper) {
        List<T> result = new ArrayList<>();
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return result;

        try {
            PreparedStatement ps = c.prepareStatement(sql);
            filler.fill(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) result.add(mapper.map(rs));
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("❌ SELECT failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(c);
        }
        return result;
    }

    private Wear fromRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("item_id");
        String type = rs.getString("item_type");
        String name = rs.getString("name");
        double price = rs.getDouble("price");

        switch (type) {
            case "TSHIRT":
                return new TShirt(id, name, price, rs.getString("size"));
            case "JACKET":
                return new Jacket(id, name, price, rs.getString("material"), rs.getBoolean("hooded"));
            case "PANTS":
                return new Pants(id, name, price, rs.getString("fit"), rs.getInt("length_cm"));
            default:
                return null;
        }
    }
}
