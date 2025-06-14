package mapper;

import products.Fruit;
import products.Wood;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WoodDAO extends ProductDAO<Wood> {
    public WoodDAO() throws SQLException {
        super();
    }

    public void insert(Wood wood) throws SQLException {

        String sql = "INSERT INTO wood (id, wood_type, origin, grade, length, width, height, purchday) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";


        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, wood.getID());
        ps.setString(2, wood.getType());
        ps.setString(3, wood.getOrigin());
        ps.setDouble(5, wood.getWaterness());
        ps.setDouble(6, wood.getLength());
        ps.setDouble(7, wood.getWidth());
        ps.setDouble(8, wood.getThickness());
        ps.setDate(9, Date.valueOf(wood.getDate()));

        ps.executeUpdate();
    }

    @Override
    public boolean update(Wood wood) throws SQLException {
        // 1. 更新product表
        boolean productUpdated = updateTable(wood);

        // 2. 更新fruit表
        boolean fruitUpdated = update1(wood);

        return productUpdated && fruitUpdated;
    }

    public boolean updateTable(Wood wood) throws SQLException {
        String sql = "UPDATE product SET name = ?, type = ?, price = ?, description = ?, stock = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, wood.getName());
            ps.setString(2, wood.getType());
            ps.setDouble(3, wood.getTotalPrice());
            ps.setString(4, wood.getDescription());
            ps.setInt(5, getStock(wood));
            ps.setInt(6, wood.getID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean update1(Wood wood) throws SQLException {

        String url1 = "UPDATE fruit SET wood_type = ? ,origin = ?, grade = ?, length = ?, width = ? , thickness = ?, purchday = ?,price =? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(url1);


        ps.setString(1, wood.getType());
        ps.setString(2,wood.getOrigin());
        ps.setDouble(3, wood.getWaterness());
        ps.setDouble(4, wood.getLength());
        ps.setDouble(5, wood.getWidth());
        ps.setDouble(6, wood.getThickness());
        ps.setDate(6, java.sql.Date.valueOf(wood.getDate()));
        ps.setDouble(7, wood.getTotalPrice());


        ps.setInt(8, wood.getID());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    }

    private int getStock(Wood wood) {
        // 这里需要从 Inventory 中获取库存数量
        return 0; // 暂时返回0，需要根据实际情况修改
    }
}


