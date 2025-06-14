import products.Product;

public class SalesRecord {
    private String productName;
    private int CustomersID;
    private int productNums;
    private double productPrice;
    private Product product;


    public SalesRecord(Product product, int CustomersID, int productNums, double productPrice) {
        this.CustomersID = CustomersID;
        this.productNums = productNums;
        this.productPrice = productPrice;
        this.product = product;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getCustomersID() {
        return CustomersID;

    }
    public void setCustomersID(int CustomersID) {
        this.CustomersID = CustomersID;
    }
    public int getProductNums() {
        return productNums;
    }
    public void setProductNums(int productNums) {
        this.productNums = productNums;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public String toString()
    {
        return CustomersID  + "\t" + productNums + "\t" + productPrice + "\t" + product.toString();
    }
}
