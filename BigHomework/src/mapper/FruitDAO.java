package mapper;

import products.Fruit;


import products.Fruit;
import products.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FruitDAO extends ProductDAO<Fruit> {
    public FruitDAO() throws SQLException {
        super();
    }

    @Override
    public void insert(Fruit fruit) throws SQLException {
        //插入Product表
        insertProductTable(fruit);

        String sql = "INSERT INTO fruit(id,color, weight, unit, origin, ripeness,purchday,unitPrice,totalPrice) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,fruit.getID());
        ps.setString(2, fruit.getColor());
        ps.setDouble(3, fruit.getWeight());
        ps.setString(4, fruit.getUnit());
        ps.setString(5, fruit.getOrigin());
        ps.setString(6, fruit.getMaturity());
        java.sql.Date sqlDate = java.sql.Date.valueOf(fruit.getDate());
        ps.setDate(7,sqlDate);
        ps.setDouble(8, fruit.getUnitPrice());
        ps.setDouble(9, fruit.getTotalPrice());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public boolean update(Fruit fruit) throws SQLException {
        // 1. 更新product表
        boolean productUpdated = updateTable(fruit);

        // 2. 更新fruit表
        boolean fruitUpdated = update1(fruit);

        return productUpdated && fruitUpdated;
    }

    public boolean updateTable(Fruit fruit) throws SQLException {
        String sql = "UPDATE product SET name = ?, type = ?, price = ?, description = ?, stock = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, fruit.getName());
            ps.setString(2, fruit.getType());
            ps.setDouble(3, fruit.getTotalPrice());
            ps.setString(4, fruit.getDescription());
            ps.setInt(5, getStock(fruit));
            ps.setInt(6, fruit.getID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean update1(Fruit fruit) throws SQLException {

        String url1 = "UPDATE fruit SET color = ? ,weight = ?, unit = ?, origin = ?, ripeness = ? , pMonth = ?, purchday = ?,purchday = ?WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(url1);


        ps.setString(1, fruit.getColor());
        ps.setDouble(2, fruit.getWeight());
        ps.setString(3, fruit.getUnit());
        ps.setString(4, fruit.getOrigin());
        ps.setString(5, fruit.getMaturity());
        ps.setDate(6, java.sql.Date.valueOf(fruit.getDate()));
        ps.setDouble(7, fruit.getUnitPrice());
        ps.setDouble(8, fruit.getTotalPrice());

        ps.setInt(9, fruit.getID());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    }

    public Fruit findById(int id) throws SQLException {
        String sql = "SELECT p.*, f.color, f.weight, f.unit, f.origin, f.ripeness, "
                + "f.purchday, f.unitPrice, f.totalPrice "
                + "FROM product p "
                + "JOIN fruit f ON p.id = f.id "
                + "WHERE p.id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    // 创建并填充Fruit对象
                    Fruit fruit = new Fruit(
                            rs.getString("name"),
                            id,
                            rs.getString("color"),
                            rs.getDouble("weight"),
                            rs.getString("unit"),
                            rs.getString("origin"),
                            rs.getDate("purchday").toLocalDate(),
                            rs.getDouble("unitPrice")
                    );

                    // 设置其他属性
                    fruit.setType(rs.getString("type"));
                    fruit.setDescription(rs.getString("description"));

                    return fruit;
                }
            }
        }
        return null;
    }

    private int getStock(Fruit fruit) {
        // 这里需要从 Inventory 中获取库存数量
        return 0; // 暂时返回0，需要根据实际情况修改
    }
}