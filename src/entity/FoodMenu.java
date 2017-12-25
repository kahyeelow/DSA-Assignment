package entity;

//Author: Low Kah Yee
import java.text.SimpleDateFormat;

public class FoodMenu {
    SimpleDateFormat ft= new SimpleDateFormat("dd/MM/yyyy");
    private String itemId ;
    private int total = 0;
    private String itemName;
    private String desc;
    private String category;
    private String status;
    private String itemPrice;
    private String disRate;
    private String addDate;

    public FoodMenu(String itemId,String addDate,
            String itemName, String desc, String category, 
            String status, String itemPrice, String disRate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.desc = desc;
        this.category = category;
        this.status = status;
        this.itemPrice = itemPrice;
        this.disRate = disRate;
        this.addDate = addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

       public void setItemId(String itemId) {
        this.itemName = itemId;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setDisRate(String disRate) {
        this.disRate = disRate;
    }

    public int getTotal(){
        return total++;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getDisRate() {
        return disRate;
    }

    public String getAddDate() {
        return addDate;
    }

    @Override
    public String toString() {
        String str = "";
        str+= String.format("%-8s %-12s %-19s %-22s %-10s %-15s %-10s %-9s ", 
                itemId,addDate,itemName,desc,category,status,itemPrice,disRate);
       return str;  
    }  
}
