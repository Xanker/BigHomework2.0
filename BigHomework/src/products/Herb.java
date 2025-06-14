package products;

import java.time.LocalDate;

public class Herb extends Product {
    private String Herbname;
    private String Origin;
    private String pSeason;
    private String type;
    private int pMonth;
    private String  property;
    private LocalDate date;
    private int stock;

    // 构造函数、getter和setter方法、glow()方法
    public Herb(String Herbname, int id, String Origin, String pSeason,  String property,LocalDate date,double price,int stock) {
        this.Herbname = Herbname;
        super.setID(id);
        this.Origin = Origin;
        this.pSeason = pSeason;
        pMonth = date.getMonthValue();
        this.property = property;
        this.date = date;
        this.stock = stock;
        super.setUnitprice(price);
        super.setTotalpricePrice(stock*price);
        super.setDate(this.date);
        setType();
        super.setStock(stock);

    }

    private void calculate() {
    }

    //  getter方法
    public String getHerbname(){
        return Herbname;
    }
    public String getOrigin(){
        return Origin;
    }
    public String getSeason(){
        return pSeason;
    }
    public int getMonth(){
        return pMonth;
    }
    public String getProperty(){
        return property;
    }

    //setter方法
    public void setType(){
        type = getClass().getSimpleName();
    }
    public void setHerbname(String Herbname){
        this.Herbname = Herbname;
    }
    public void setOrigin(String Origin){
        this.Origin = Origin;
    }
    public void setSeason(String pSeason){
        this.pSeason = pSeason;
    }
    public void setMonth(int pMonth){
        this.pMonth = pMonth;
    }
    public void setProperty(String property){
        this.property = property;
    }
    public String getType(){
        return type;
    }
    public String preserve()
    {
         switch(property)
         {
             case "寒性":

                 String str1 = "存放于温度≤18℃、湿度≤50%的环境中，避免厨房、阳台等湿热或阳光直射区域";
                 super.setDescription(str1);
                 return str1;
             case "凉性":
                 String str2 =  "存放于温度≤20℃、湿度≤60%的环境中，避免厨房、阳台等潮湿或阳光直射区域";
                 super.setDescription(str2);
                 return str2;
             case "热性":
                 String str3 =  "存放于温度≤27℃、湿度≤50%的干燥环境中，远离热源（如厨房、暖气）和阳光直射";
                 super.setDescription(str3);
                 return str3;
             case "温性":
                 String str4 =  "存放于温度≤25℃、湿度≤60%的环境中，避免厨房、阳台等湿热或阳光直射区域";
                 super.setDescription(str4);
                 return str4;

             default:
                 String str5 = "未知方法";
                 super.setDescription(str5);
                 return str5;

         }
    }

    public LocalDate getPurchday() {
        return date;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPurchday(LocalDate newDate) {
        this.date = newDate;
    }
}
