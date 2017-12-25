package client;
//Author: Low Kah Yee

import adtllist.LinkedList;
import adtllist.LinkedListInterface;
import entity.FoodMenu;
import entity.Restaurant;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantRegistration {

    public static String foodId = "F004";
    public static String regId = "R002";
    public static SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    public static Date date = new Date();
    public static LinkedListInterface<Restaurant> Res = new LinkedList<Restaurant>();

    public static String generateRegId() {
        //auto generate Registration id
        int nextRegId = Integer.parseInt(regId.substring(1)) + 1;
        if (nextRegId < 10) {
            regId = "R00" + nextRegId;
        } else if (nextRegId < 100) {
            regId = "R0" + nextRegId;
        } else if (nextRegId < 1000) {
            regId = "R" + nextRegId;
        }
        return regId;
    }

    public static String generateFoodId() {
        //auto generate food id
        int nextRegId = Integer.parseInt(foodId.substring(1)) + 1;
        if (nextRegId < 10) {
            foodId = "F00" + nextRegId;
        } else if (nextRegId < 100) {
            foodId = "F0" + nextRegId;
        } else if (nextRegId < 1000) {
            foodId = "F" + nextRegId;
        }
        return foodId;
    }

    public static void main(String[] args) {
        LinkedListInterface<FoodMenu> Menu1 = new LinkedList<>();
        LinkedListInterface<FoodMenu> Menu2 = new LinkedList<>();
        Scanner scan = new Scanner(System.in);

        FoodMenu food1 = new FoodMenu("F001", "22/12/2017", "Dave's Roasted Chicken", "Roasted with garlic", "Food", "Available", "15.60", "0");
        FoodMenu food2 = new FoodMenu("F002", "23/12/2017", "Dave's Smoked Duck", "Smoked with rosemary", "Food", "Promotional", "18.70", "0.2");
        Menu1.add(food1);
        Menu1.add(food2);

        FoodMenu food3 = new FoodMenu("F003", "24/12/2017", "Chicken Pan Mee", "Cooked with pork", "Food", "Promotional", "9.80", "0.2");
        FoodMenu food4 = new FoodMenu("F004", "24/12/2017", "Dry Pan Mee", "Cooked with pork", "Food", "Limited", "10.80", "0");
        Menu2.add(food3);
        Menu2.add(food4);

        Restaurant res1 = new Restaurant("R001", "22/12/2017", "Dave's Deli", "AH12306", "Husin", "One Utama", "03-62723165", "dd@gmail.com", Menu1);
        Restaurant res2 = new Restaurant("R002", "24/12/2017", "Yummy Pan Mee", "SN12353", "Tang US", "Setapak", "03-45123693", "yummy@yahoo.com", Menu2);
        Res.add(res1);
        Res.add(res2);

        do {
            switch (title()) {
                case "1": { //Owner Registration
                    Restaurant newRes = registration(); //call registration method
                    Res.add(newRes);//return contructor, add newRes in Res list
                    System.out.println("\n\n**********REGISTERED SUCCESSFULLY**********");
                    System.out.println("************************************************");
                    System.out.println("\nRegistration ID   Registration Date  Restaurant Name          Company Number      Owner           Address         Fax No.     Email");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                    int a = Res.getLength();
                    System.out.println(String.format("%-137s", Res.getEntry(a).toString())); //display Registered restaurant info
                }
                break;
                case "2": {//Add new item into menu
                    boolean found = false; //for find restaurant id purpose
                    System.out.print("\nPlease enter restaurant registration ID: ");
                    String regID = scan.nextLine();

                    for (int i = 1; i <= Res.getLength(); i++) {
                        if (regID.equals(Res.getEntry(i).getRegId())) {//check whether id equals to id in Res
                            //display food item exist in Restaurant
                            System.out.println("\n==========================");
                            System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                            System.out.println("==========================");
                            System.out.println("\nFood ID   Add Date    Item Name           Description          Category      Status      Price(RM)   Discount Rate");
                            System.out.println("--------------------------------------------------------------------------------------------------------------------");
                            for (int j = 1; j <= Res.getEntry(i).ResMenu.getLength(); j++) {
                                System.out.println(String.format("%-112s", Res.getEntry(i).ResMenu.getEntry(j).toString()));
                            }
                            found = true; //if id found, set it to true
                            LinkedListInterface<FoodMenu> foodm = addnewItem(); //call addnewItem method, return Food menu list

                            for (int k = 1; k <= foodm.getLength(); k++) {
                                Res.getEntry(i).ResMenu.add(foodm.getEntry(k));//add new added item into the back of the list
                            }
                            //to display back the added food menu 
                            System.out.println("\n==========================");
                            System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                            System.out.println("==========================");
                            System.out.println("\nFood ID   Add Date    Item Name           Description          Category      Status      Price(RM)   Discount Rate");
                            System.out.println("---------------------------------------------------------------------------------------------------------------------");

                            for (int j = 1; j <= Res.getEntry(i).ResMenu.getLength(); j++) {
                                System.out.println(Res.getEntry(i).ResMenu.getEntry(j).toString());
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Record does not exist");
                    }
                }
                break;
                case "3": {//update item
                    updateItem(); //call updateItem method
                }
                break;
                case "4": {//remove food menu from restaurant
                    removeItem();
                }
                break;
                case "5": {//choose menu display order
                    chooseMenuDisplay();
                }
                break;
                default: {
                    System.out.println("\nSelection entered out of range. Please enter from 1-5 ");
                }
            }
        } while (askContinue() == true);
    }

    public static String title() {
        Scanner scan = new Scanner(System.in);
        String selection;
        System.out.println("\n************Fastest Deliveryman**************");
        System.out.println("---------------------------------------------");
        System.out.println();
        System.out.println("1. Register restaurant as an affiliate");
        System.out.println("2. Add new item into menu");
        System.out.println("3. Update item from menu");
        System.out.println("4. Remove item from menu");
        System.out.println("5. Choose menu display order");
        System.out.print("Please select a module that you want to perform(1-5): ");
        selection = scan.next();
        return selection;
    }

    public static Restaurant registration() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\n--------------Fastest Deliveryman-------------");
        System.out.println("************Restaurant Registration**************");
        System.out.println("-------------------------------------------------");
        System.out.println();

        regId = generateRegId(); //get auto generate id
        //prompt user input for Registration
        System.out.println("Registration ID: " + regId);
        System.out.println("Registration Date: " + ft.format(date));
        String newDt = ft.format(date);
        System.out.print("Restaurant Name: ");
        String resName = scan.nextLine();
        System.out.print("Company Number: ");
        String compNum = scan.nextLine();
        System.out.print("Company Owner: ");
        String compOwner = scan.nextLine();
        System.out.print("Company Address: ");
        String compAddr = scan.nextLine();
        System.out.print("Company Fax Number: ");
        String fax = scan.nextLine();
        System.out.print("Company Email: ");
        String email = scan.nextLine();

        //if a restaurant owner wants to register, 
        //he/she must add at least one food item in menu first
        System.out.println("\n\nLet's create your food menu!!");

        LinkedListInterface<FoodMenu> newfoodList = addnewItem();//call addnewItem method, return list

        //last constructor - newfoodList is the returned list
        return (new Restaurant(regId, newDt, resName, compNum, compOwner, compAddr, fax, email, newfoodList));//return to main method
    }

    public static LinkedListInterface<FoodMenu> addnewItem() {
        Scanner scan = new Scanner(System.in);
        LinkedListInterface<FoodMenu> newfoodList = new LinkedList<>();
        FoodMenu newFood;
        String selection;
        char con = 'Y';
        do {
            System.out.println("\n\n--------------Fastest Deliveryman-------------");
            System.out.println("**************Add New Item in Menu**************");
            System.out.println("-------------------------------------------------");
            System.out.println();

            foodId = generateFoodId(); //get auto generated food id
            //prompt user input for add new item in menu
            System.out.println("Item ID: " + foodId);
            System.out.println("Add Item Date: " + ft.format(date));
            String newdt = ft.format(date);
            System.out.print("Item Name: ");
            String itemName = scan.nextLine();
            System.out.print("Item Description: ");
            String desc = scan.nextLine();
            System.out.print("Category: ");
            String cat = scan.nextLine();
            System.out.print("Status: ");
            String status = scan.nextLine();
            System.out.print("Item Price(RM): ");
            String price = scan.nextLine();
            System.out.print("Discount Rate: ");
            String disRate = scan.nextLine();
            System.out.print("\nConfirm add item into menu? (Y/N): ");
            selection = scan.nextLine();

            switch (selection) {
                case "Y":
                case "y": {//if confirm add item
                    newFood = new FoodMenu(foodId, newdt, itemName, desc, cat, status, price, disRate); //after confirm add, create constructor
                    newfoodList.add(newFood); //add into food menu list

                    //display the added food item2
                    System.out.println("\nFood ID   Add Date    Item Name           Description          Category      Status      Price(RM)   Discount Rate");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------");
                    for (int i = 1; i <= newfoodList.getLength(); i++) {
                        System.out.println(String.format("%-112s", newfoodList.getEntry(i).toString()));
                    }
                    //ask whether to continue adding next item
                    System.out.print("\nContinue add item? (y/n): ");
                    String option = scan.nextLine();
                    con = option.charAt(0);
                }
                break;
            }
        } while (con == 'y' || con == 'Y');
        return newfoodList;
    }

    public static void updateItem() {
        Scanner scan = new Scanner(System.in);
        String selection;
        String select1;
        boolean found = false; //for find restaurant id purpose
        System.out.print("\nPlease enter restaurant registration ID: ");
        String regID = scan.nextLine();

        for (int i = 1; i <= Res.getLength(); i++) {
            if (regID.equals(Res.getEntry(i).getRegId())) {//check whether id equals to id in Res
                //display food item exist in Restaurant
                System.out.println("\n==========================");
                System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                System.out.println("==========================");
                System.out.println("\nFood ID   Add Date    Item Name           Description          Category      Status      Price(RM)   Discount Rate");
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                for (int j = 1; j <= Res.getEntry(i).ResMenu.getLength(); j++) {
                    System.out.println(String.format("%-112s", Res.getEntry(i).ResMenu.getEntry(j).toString()));
                }

                found = true; //if id found, set it to true
                System.out.print("\n\nEnter Food Id to update details: ");
                selection = scan.nextLine();

                System.out.println("\n============================");
                System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                System.out.println("Selected Food Item's Details");
                System.out.println("============================");
                int position = 0;
                //display the selected item's details, get from Res-->ResMenu-->Item
                for (int k = 1; k <= Res.getEntry(i).ResMenu.getLength(); k++) {
                    if (selection.equals(Res.getEntry(i).ResMenu.getEntry(k).getItemId())) {
                        System.out.println("Food ID: " + Res.getEntry(i).ResMenu.getEntry(k).getItemId());
                        System.out.println("Add Date: " + Res.getEntry(i).ResMenu.getEntry(k).getAddDate());
                        System.out.println("Item Name: " + Res.getEntry(i).ResMenu.getEntry(k).getItemName());
                        System.out.println("Description: " + Res.getEntry(i).ResMenu.getEntry(k).getDesc());
                        System.out.println("Category: " + Res.getEntry(i).ResMenu.getEntry(k).getCategory());
                        System.out.println("Status: " + Res.getEntry(i).ResMenu.getEntry(k).getStatus());
                        System.out.println("Price(RM): " + Res.getEntry(i).ResMenu.getEntry(k).getItemPrice());
                        System.out.println("Discount Rate: " + Res.getEntry(i).ResMenu.getEntry(k).getDisRate());
                        position = k;
                    }
                }
                boolean valid = true; 
                do {
                    System.out.println("\n==============================");
                    System.out.println("1. Item's Price");
                    System.out.println("2. Item's Status");
                    System.out.println("3. Promotional Information");
                    System.out.print("Please select one to update item's detail(1-3): ");
                    select1 = scan.nextLine();
                    switch (select1) {
                        case "1": {//to update item's price
                            System.out.print("\nItem Price(RM): ");
                            String newPrice = scan.nextLine();
                            Res.getEntry(i).ResMenu.getEntry(position).setItemPrice(newPrice);
                            valid = true;
                        }
                        break;
                        case "2": {//to update status
                            System.out.print("\nStatus: ");
                            String newStatus = scan.nextLine();
                            if (newStatus.equals("Promotional")) {//if status-->promotional,prompt for discount rate
                                System.out.print("Discount Rate: ");
                                String newDisRate = scan.nextLine();
                                Res.getEntry(i).ResMenu.getEntry(position).setDisRate(newDisRate);
                                valid = true;
                            }
                            Res.getEntry(i).ResMenu.getEntry(position).setStatus(newStatus);
                        }
                        break;
                        case "3": {//to update discount rate
                            valid = (Res.getEntry(i).ResMenu.getEntry(position).getStatus()).equals("Promotional");
                            if (valid) {//if selected food's status is promotional
                                System.out.print("\nDiscount Rate: ");
                                String newDisRate = scan.nextLine();
                                Res.getEntry(i).ResMenu.getEntry(position).setDisRate(newDisRate);
                                valid = true;
                            } else {//if the status is not promotional
                                System.out.println("\nYour food item is not promotional. You need to change status first.");
                                valid = false;
                                break;
                            }
                        }
                        break;
                        default:
                            System.out.print("\nSelection out of range. Please select between 1-3");
                            break;
                    }
                    //while select1 is not 1/2/3/valid is false
                } while ((!((select1.equals("1")) || (select1.equals("2")) || (select1.equals("3")))) || valid == false);

                System.out.println("\n============================");
                System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                System.out.println("Updated Successfully!");
                System.out.println("Updated Food Item's Details");
                System.out.println("============================");
                for (int m = 1; m <= Res.getEntry(i).ResMenu.getLength(); m++) {
                    if (selection.equals(Res.getEntry(i).ResMenu.getEntry(m).getItemId())) {
                        System.out.println("Food ID: " + Res.getEntry(i).ResMenu.getEntry(m).getItemId());
                        System.out.println("Add Date: " + Res.getEntry(i).ResMenu.getEntry(m).getAddDate());
                        System.out.println("Item Name: " + Res.getEntry(i).ResMenu.getEntry(m).getItemName());
                        System.out.println("Description: " + Res.getEntry(i).ResMenu.getEntry(m).getDesc());
                        System.out.println("Category: " + Res.getEntry(i).ResMenu.getEntry(m).getCategory());
                        System.out.println("Status: " + Res.getEntry(i).ResMenu.getEntry(m).getStatus());
                        System.out.println("Price(RM): " + Res.getEntry(i).ResMenu.getEntry(m).getItemPrice());
                        System.out.println("Discount Rate: " + Res.getEntry(i).ResMenu.getEntry(m).getDisRate());
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Restaurant Id does not exist.");
        }
    }
    
    public static void removeItem() {
        Scanner scan = new Scanner(System.in);
        String selection;
        boolean found = false; //for find restaurant id purpose
        System.out.print("\nPlease enter restaurant registration ID: ");
        String regID = scan.nextLine();

        for (int i = 1; i <= Res.getLength(); i++) {
            if (regID.equals(Res.getEntry(i).getRegId())) {//check whether id equals to id in Res
                //display food items exist in Restaurant
                System.out.println("\n==========================");
                System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                System.out.println("==========================");
                System.out.println("\nFood ID   Add Date    Item Name           Description          Category      Status      Price(RM)   Discount Rate");
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                for (int j = 1; j <= Res.getEntry(i).ResMenu.getLength(); j++) {
                    System.out.println(String.format("%-112s", Res.getEntry(i).ResMenu.getEntry(j).toString()));
                }

                System.out.print("\nEnter Food Id to remove from menu: ");
                selection = scan.nextLine();

                for (int k = 1; k <= Res.getEntry(i).ResMenu.getLength(); k++) {
                    if (selection.equals(Res.getEntry(i).ResMenu.getEntry(k).getItemId())) {
                        Res.getEntry(i).ResMenu.remove(k);//remove selected food item 
                        
                        if (Res.getEntry(i).ResMenu.isEmpty()) {//after removed, if the food menu is empty
                            System.out.println("\n============================");
                            System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                            System.out.println("============================");
                            System.out.println("Your food menu is empty now.");
                        } else {
                            //after removed, if the food menu is not empty, displays again the removed food menu
                            System.out.println("\n==========================");
                            System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                            System.out.println("Removed successfully!");
                            System.out.println("Updated Food Menu");
                            System.out.println("==========================");
                            System.out.println("\nFood ID   Add Date    Item Name           Description          Category      Status      Price(RM)   Discount Rate");
                            System.out.println("---------------------------------------------------------------------------------------------------------------------");
                            for (int n = 1; n <= Res.getEntry(i).ResMenu.getLength(); n++) {
                                System.out.println(String.format("%-112s", Res.getEntry(i).ResMenu.getEntry(n).toString()));
                            }
                        }
                    }
                }
                found = true;  //found registration record
            }
        }
        if (!found) {
            System.out.println("The Restaurent ID does not exist.");
        }
    }

    public static void chooseMenuDisplay() {
        Scanner scan = new Scanner(System.in);
        String selection;
        String select1;
        boolean found = false; //for find restaurant id purpose
        System.out.print("\nPlease enter restaurant registration ID: ");
        String regID = scan.nextLine();

        for (int i = 1; i <= Res.getLength(); i++) {
            if (regID.equals(Res.getEntry(i).getRegId())) {//check whether id equals to id in Res
                found = true; //found registration record
                do {
                    System.out.println("\n==========================");
                    System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                    System.out.println("==========================");
                    System.out.println("\nDisplay menu order according to: ");
                    System.out.println("1. Newest Items");
                    System.out.println("2. Promotional Items");
                    System.out.print("Please select one menu order to display: ");
                    select1 = scan.nextLine();

                    switch (select1) {
                        case "1": {//display menu according to the newest items
                            System.out.println("\n======================================");
                            System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                            System.out.println("Menu Display According to Newest Items");
                            System.out.println("======================================");
                            System.out.println("\nFood ID   Add Date    Item Name           Description          Category      Status      Price(RM)   Discount Rate");
                            System.out.println("---------------------------------------------------------------------------------------------------------------------");
                            for (int p = Res.getEntry(i).ResMenu.getLength(); p >= 1; p--) {
                                System.out.println(String.format("%-112s", Res.getEntry(i).ResMenu.getEntry(p).toString()));
                            }
                            System.out.println("Do you wish to continue? (Y/N): ");
                            selection = scan.nextLine();
                        }
                        break;
                        case "2": {//display menu according to promotional items
                            System.out.println("\n======================================");
                            System.out.println("Restaurant --> " + Res.getEntry(i).getResName());
                            System.out.println("Menu Display According to Promotional Items");
                            System.out.println("======================================");
                            System.out.println("\nFood ID   Add Date    Item Name           Description          Category      Status      Price(RM)   Discount Rate");
                            System.out.println("---------------------------------------------------------------------------------------------------------------------");

                            //this loop is to display promotional items first
                            for (int a = 1; a <= Res.getEntry(i).ResMenu.getLength(); a++) {
                                if ((Res.getEntry(i).ResMenu.getEntry(a).getStatus()).equals("Promotional")) {
                                    System.out.println(String.format("%-112s", Res.getEntry(i).ResMenu.getEntry(a).toString()));
                                }
                            }
                            //this loop is to display normal items
                            for (int a = 1; a <= Res.getEntry(i).ResMenu.getLength(); a++) {
                                if (!(Res.getEntry(i).ResMenu.getEntry(a).getStatus().equals("Promotional"))) {
                                    System.out.println(String.format("%-112s", Res.getEntry(i).ResMenu.getEntry(a).toString()));
                                }
                            }
                            System.out.print("\nDo you wish to continue? (Y/N): ");
                            selection = scan.nextLine();
                        }
                        break;
                        default: {
                            System.out.println("Selection entered out of range. Please select from 1-2.");
                            System.out.print("\nDo you wish to continue? (Y/N): ");
                            selection = scan.nextLine();
                        }
                    }
                } while ((selection.equals("Y")) || (selection.equals("y")));
            }
        }
        if (!found) {
            System.out.println("Restaurant ID does not exist.");
        }
    }

    public static boolean askContinue() {
        Scanner scan = new Scanner(System.in);
        String option;
        boolean cont = false;
        System.out.print("\nDo you wish to continue to main menu? (Y/N): ");
        option = scan.nextLine();
        switch (option) {
            case "Y":
            case "y":
                cont = true;
                break;
            case "N":
            case "n":{
                cont = false;
                System.out.println("Thank you for using the system. Good day:D");
            }    
                break;
            default:
                System.out.println("Please enter (Y=yes, N=no).");
                break;
        }
        return cont;
    }

}
