package ui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Member;

public class ShoppingCartUI extends JFrame {
    private Members cart;
    // 用于展示购物车商品列表
    private JList<String> cartList;
    private DefaultListModel<String> listModel;

    public ShoppingCartUI(Members cart) {
        this.cart = cart;
        setTitle("购物车界面");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭时不退出程序
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // 初始化列表模型
        listModel = new DefaultListModel<>();
        cartList = new JList<>(listModel);
        // 加载购物车已有商品到列表（示例遍历，实际可优化格式）
        cart.getCartMap().forEach((k, v) -> {
            listModel.addElement(v.getKey().getName() + "  数量：" + v.getValue()
                    + "  单价：" + v.getKey().getunitprice());
        });
        JScrollPane scrollPane = new JScrollPane(cartList);
        container.add(scrollPane, BorderLayout.CENTER);

        // 底部显示总价和操作按钮
        JPanel bottomPanel = new JPanel();
        JLabel totalPriceLabel = new JLabel("总价：" + cart.getName());
        JButton checkoutBtn = new JButton("结算");
        JButton continueShopBtn = new JButton("继续购物");

        bottomPanel.add(totalPriceLabel);
        bottomPanel.add(checkoutBtn);
        bottomPanel.add(continueShopBtn);
        container.add(bottomPanel, BorderLayout.SOUTH);
    }

    // 启动购物车界面
    public static void showCartUI(Members cart) {
        ShoppingCartUI cartUI = new ShoppingCartUI(cart);
        cartUI.setVisible(true);
    }

    // 方便后续更新列表（比如添加商品后刷新）
    public void refreshCartList() {
        listModel.clear();
        cart.getCartMap().forEach((k, v) -> {
            listModel.addElement(v.getKey().getName() + "  数量：" + v.getValue()
                    + "  单价：" + v.getKey().getunitprice());
        });
    }
}
