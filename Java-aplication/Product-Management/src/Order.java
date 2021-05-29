/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class Order {
    private ProductList orderPLst;
    private double total;
    private String name;

    public Order() {
    }

    public Order(ProductList orderPLst, double total, String name) {
        this.orderPLst = orderPLst;
        this.total = total;
        this.name = name;
    }

    public ProductList getOrderPLst() {
        return orderPLst;
    }

    public void setOrderPLst(ProductList orderPLst) {
        this.orderPLst = orderPLst;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
