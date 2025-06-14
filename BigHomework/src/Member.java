public class Member {
    private String name;
    private int CustomersID;
    private int Level;
    private double Discount;

    public Member(String name, int CustomersID, int Level, double Discount) {
        this.name = name;
        this.CustomersID = CustomersID;
        this.Level = Level;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCustomersID() {
        return CustomersID;
    }
    public void setCustomersID(int CustomersID) {
        this.CustomersID = CustomersID;
    }
    public int getLevel() {
        return Level;
    }
    public void setLevel(int Level) {
        this.Level = Level;
    }
    public double getDiscount() {
        return Discount;
    }
    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }
    public boolean Verify(int CustomersID,String name)
    {
        if(this.CustomersID==CustomersID && this.name.equals(name))
            return true;
        return false;
    }
}
