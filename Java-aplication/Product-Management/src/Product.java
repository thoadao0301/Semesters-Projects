/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class Product {
    private String productID, PName, catID;
    private double price;
    private int quantity;
    private double amount;
    private int orderquantity;

    public Product(String productID) {
        this.productID = productID;
    }

    public Product() {
    }

    public Product(String productID, String PName, String catID, double price, int quantity) {
        this.productID = productID;
        this.PName = PName;
        this.catID = catID;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String PName, double price, int orderquantity) {
        this.PName = PName;
        this.price = price;
        this.orderquantity = orderquantity;
        
    }
    
    

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        if (!productID.equals("")) {
          this.productID = productID;  
        }
        
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        if(!PName.equalsIgnoreCase(""))
            this.PName = PName;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        if(!catID.equalsIgnoreCase(""))
             this.catID = catID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        if(!price.equals(""))
            this.price = Double.parseDouble(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        if(!quantity.equalsIgnoreCase(""))
              this.quantity = Integer.parseInt(quantity);
    }

    public double getAmount() {
        return amount = this.price*orderquantity;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOrderquantity() {
        return orderquantity;
    }

    public void setOrderquantity(int orderquantity) {
        this.orderquantity = orderquantity;
    }
    
    

    @Override
    public String toString() {
        return  productID + ", " + PName + 
                ", " + catID + ", " + price + ", " + quantity ;
    }
    
    public String toString2()
    {
        return(this.PName + "      " + this.orderquantity + "       "
                + this.price + "     " + this.amount);
    }
    
    public boolean equals(Object obj)
    {
        return this.productID.equalsIgnoreCase(((Product)obj).productID);
    }
}
