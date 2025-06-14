//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import mapper.FruitDAO;
import mapper.HerbDAO;
import mapper.ProductDAO;
import products.*;


public class Main {


    int w = 0;
    private static Inventory inventory = new Inventory();

    private static Scanner scanner = new Scanner(System.in);




    // 正确的 main 方法签名
    public static void main(String[] args) throws SQLException {



        LocalDate purchdate = LocalDate.of(2024, 1, 1);
        FruitDAO fruitDAO = new FruitDAO();
        Fruit fruit = new Fruit("jjj",2 ,"红色",2,"1","si",purchdate,4);
        //fruitDAO.insert(fruit);
        inventory.setProducts(fruitDAO.loadProductTable());
        while (true) {
            System.out.println("\n==== 农产品库存管理系统 ====");
            System.out.println("0. 添加产品");
            System.out.println("1. 查看库存产品总数");
            System.out.println("2. 出售产品");
            System.out.println("3. 查看各类产品所有产品");
            System.out.println("4. 修改数据");
            System.out.println("5. 退出");
            System.out.print("请选择操作: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0:
                    addProduct();
                    break;
                case 1:
                    System.out.println("木头有:"+inventory.getWoodStock()+'\n');
                    System.out.println("草药有:"+inventory.getHerbStock()+'\n');
                    System.out.println("水果有:"+inventory.getFruitStock()+'\n');
                    System.out.println("共有:"+inventory.getStock()+'\n');
                    break;
                case 2:
                    sellProduct();
                    break;
                case 3:
                    System.out.println(inventory.getAllProducts()+"\n");
                    break;
                case 4:
                    Change();
                    break;
                case 5:
                    System.out.println("感谢使用，再见！");
                    scanner.close();
                    return; // 退出程序

            }
        }
    }

    // 商品添加方法
    private static void addProduct() throws SQLException {
        System.out.println("请输入产品类型（水果/树木/草药）:");
        String productType = scanner.nextLine();

        switch (productType) {
            case "树木":
                addWood();
                int num = 0;

                break;
            case "水果":
                // 添加水果逻辑
                addFruit();
                break;
            case "草药":
                // 添加草药逻辑
                addHerb();
                break;
            default:
                System.out.println("不支持的产品类型");
                break;
        }

    }
    private static void addWood() throws SQLException {
        System.out.println("请输入树木名称:");
        String name = scanner.nextLine();

        System.out.println("ID:");
        int id = Integer.parseInt(scanner.nextLine()); // 避免换行符问题

        System.out.println("价格：");
        int price = Integer.parseInt(scanner.nextLine());


        System.out.println("长度:");
        double length = Double.parseDouble(scanner.nextLine());

        System.out.println("宽度:");
        double width = Double.parseDouble(scanner.nextLine());

        System.out.println("厚度:");
        double thickness = Double.parseDouble(scanner.nextLine());


        System.out.println("时间");
        String input = scanner.nextLine().trim();  // trim用于去空格
        // 创建自定义格式器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy M d");
        // 使用格式器解析日期
        LocalDate date = LocalDate.parse(input, formatter);
        System.out.println("产地");
        String in = scanner.nextLine().trim();

        System.out.println("Stock?0");
        Integer inn = Integer.parseInt(scanner.nextLine());
        Wood wood = new Wood(name, id,price, length, width, thickness,date,in,inn);
        inventory.Add(wood,wood.getStock());

        System.out.println("添加成功！\n" + inventory.getWood(wood.getID()) + "\t" + inventory.getWoodStock());
        System.out.println(inventory.getWood(wood.getID()).getWaterness());
    }
    public static void addFruit() throws SQLException {
        System.out.println("请输入水果名称:");
        String name = scanner.nextLine();

        System.out.println("ID:");
        int ID = Integer.parseInt(scanner.nextLine()); // 避免换行符问题


        System.out.println("颜色:");
        String color = scanner.nextLine();

        System.out.println("重量:");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.println("单位:");
        String unit = scanner.nextLine();

        System.out.println("原产地:");
                String origin = scanner.nextLine();

        System.out.println("收获时间");

            String date = scanner.nextLine().trim();



        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy M d"));

        System.out.println("单位价格");
        double unitprice = Double.parseDouble(scanner.nextLine());


        Fruit fruit = new Fruit(name,ID,color,weight,unit,origin,date1,unitprice);
        inventory.Add(fruit,fruit.getStock());
        System.out.println("添加成功！\n" + inventory.getFruit(fruit.getID()) + "\t" + inventory.getFruitStock());
        System.out.println("新鲜度为:"+inventory.getFruit(fruit.getID()).getMaturity() );
        FruitDAO fruitDAO = new FruitDAO();
        fruitDAO.insert(fruit);

    }
    public static void addHerb() throws SQLException {
        System.out.println("请输入草药名称:");
        String name = scanner.nextLine();

        System.out.println("ID:");
        int ID = Integer.parseInt(scanner.nextLine()); // 避免换行符问题


        System.out.println("采摘季节:");
        String pSeason = scanner.nextLine();


        System.out.println("原产地:");
        String origin = scanner.nextLine();

        System.out.println("保质期");
        int pMonth = Integer.parseInt(scanner.nextLine());

        System.out.println("性质");
        String property = scanner.nextLine();

        System.out.println("收获时间");

        String date = scanner.nextLine().trim();
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy M d"));

        System.out.println("价格");
        Double price = Double.parseDouble(scanner.nextLine());

        System.out.println("数量");
        int stock = Integer.parseInt(scanner.nextLine());
        Herb herb = new Herb(name,ID,origin,pSeason,property,date1,price,stock);

        inventory.Add(herb,2);
        System.out.println("添加成功！\n" + inventory.getHerb(herb.getID()) + "\t" + inventory.getHerbStock());
        System.out.println("保存方法为:"+inventory.getHerb(herb.getID()).preserve() );
        HerbDAO herbDAO = new HerbDAO();
        herbDAO.insert(herb);
    }

    //出售（删除）
    public static void sellProduct() throws SQLException {
        System.out.println("请输入产品类型（水果/树木/草药）:");
        String productType = scanner.nextLine();

        switch (productType) {
            case "树木":
                System.out.println("请输入ID");
                int ID = Integer.parseInt(scanner.nextLine());
                sellWood(ID);
                break;
            case "水果":
                System.out.println("请输入ID");
                int ID1 = Integer.parseInt(scanner.nextLine());
                sellFruit(ID1);
                break;
            case "草药":
                System.out.println("请输入ID");
                int ID2 = Integer.parseInt(scanner.nextLine());
                sellHerb(ID2);
                break;
            default:
                System.out.println("不支持的产品类型");
                break;

        }
    }
    private static void sellWood(int ID) throws SQLException {
        inventory.sell(ID,1);//需要对getWood进行修改？？
    }
    private static void sellFruit(int id) throws SQLException {
        inventory.sell(id,1);
    }
    private static void sellHerb(int id) throws SQLException {
        inventory.sell(id,1);
    }


    /// 修改功能
    public static void Change() throws SQLException {
        System.out.println("请输入产品类型（水果/树木/草药）:");
        String productType = scanner.nextLine();

        switch (productType) {
            case "树木":
                System.out.println("请输入ID");
                int ID = Integer.parseInt(scanner.nextLine());
                changeWood(ID);
                break;
            case "水果":
                System.out.println("请输入ID");
                int ID1 = Integer.parseInt(scanner.nextLine());
                sellFruit(ID1);
                break;
            case "草药":
                System.out.println("请输入ID");
                int ID2 = Integer.parseInt(scanner.nextLine());
                sellHerb(ID2);
                break;
            default:
                System.out.println("不支持的产品类型");
                break;
        }
    }
    public static void changeWood(int id) {

    }
    public static void chageFruit(int id) {


    }
    public static void chageHerb(int id) {

    }
}