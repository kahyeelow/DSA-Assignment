package entity;
//Auhtor: Low Kah Yee

import adtllist.LinkedList;
import adtllist.LinkedListInterface;

public class Restaurant{
    private String RegId;
    private String ResName;
    private String compNumber;
    private String Regdate;
    private String compOwner;
    private String compAddress;
    private String fax;
    private String email;
    public LinkedListInterface<FoodMenu> ResMenu = new LinkedList<>();

    public Restaurant(String RegId, String date, String ResName, String compNumber,
            String compOwner, String compAddress, String fax, String email,
            LinkedListInterface<FoodMenu> ResMenu) {
        this.ResName = ResName;
        this.compNumber = compNumber;
        this.compOwner = compOwner;
        this.compAddress = compAddress;
        this.fax = fax;
        this.email = email;
        this.Regdate = date;
        this.RegId = RegId;
        this.ResMenu = ResMenu;
    }

    public void setRegId(String RegId) {
        this.ResName = RegId;
    }

    public void setResName(String ResName) {
        this.ResName = ResName;
    }

    public void setRegdate(String Regdate) {
        this.Regdate = Regdate;
    }

    public void setCompNumber(String compNumber) {
        this.compNumber = compNumber;
    }

    public void setCompOwner(String compOwner) {
        this.compOwner = compOwner;
    }

    public void setCompAddress(String compAddress) {
        this.compAddress = compAddress;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return Regdate;
    }

    public String getRegId() {
        return RegId;
    }

    public String getResName() {
        return ResName;
    }

    public String getCompNumber() {
        return compNumber;
    }

    public String getCompOwner() {
        return compOwner;
    }

    public String getCompAddress() {
        return compAddress;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        String str = "";
        str+= String.format("%-18s %-19s %-23s %-15s  %-15s %-16s %-8s %-10s",
                RegId,Regdate,ResName,compNumber,compOwner,compAddress,fax,email);
        return str;
    }
}
