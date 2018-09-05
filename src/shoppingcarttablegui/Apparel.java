/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcarttablegui;

import java.util.Date;
import shoppingcarttablegui.Size;

/**
 *
 * @author vangu
 */
public class Apparel extends Merchandise{
    private Size size;
    private Date returnPeriod;

    public Apparel(String GroceryItem, String GrocerySerialNumber, double parseDouble) {
        super(GroceryItem, GrocerySerialNumber, parseDouble);
    }

    Apparel() {
        super();
    }

    public Date getReturnPeriod() {
        return returnPeriod;
    }

    public void setReturnPeriod(Date returnPeriod) {
        this.returnPeriod = returnPeriod;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    
    public String toString(){
        return size + "                     " + super.getItemName() + "                     " + super.getPrice() + "                   " + super.getQuantity();
    }
}
