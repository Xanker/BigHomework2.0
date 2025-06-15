package ui;

import javax.swing.*;
import java.awt.*;

public class ManagerUI extends JFrame {
    public ManagerUI() {
        setTitle("管理员界面");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // 顶部标题
        JLabel titleLabel = new JLabel("管理员操作界面", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        container.add(titleLabel, BorderLayout.NORTH);

        // 中间内容区域，后续可加表格、按钮等管理商品、订单的组件
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        JButton manageProductBtn = new JButton("管理商品");
        JButton manageOrderBtn = new JButton("管理订单");
        centerPanel.add(manageProductBtn);
        centerPanel.add(manageOrderBtn);
        container.add(centerPanel, BorderLayout.CENTER);

        // 底部退出等按钮
        JPanel bottomPanel = new JPanel();
        JButton logoutBtn = new JButton("退出登录");
        bottomPanel.add(logoutBtn);
        container.add(bottomPanel, BorderLayout.SOUTH);
    }

    // 启动管理员界面
    public static void showManagerUI() {
        ManagerUI managerUI = new ManagerUI();
        managerUI.setVisible(true);
    }
}
