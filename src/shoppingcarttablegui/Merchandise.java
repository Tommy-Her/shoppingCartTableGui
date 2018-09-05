/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcarttablegui;

/**
 *
 * @author vangu
 */
public class Merchandise {
    private String itemName;
    private int quantity = 0;
    private String serialNumber;
    private double price = 0;

    public Merchandise(String GroceryItem, String GrocerySerialNumber, double parseDouble) {

        itemName = GroceryItem;
        serialNumber = GrocerySerialNumber;
        price = parseDouble;

    }
    
    public Merchandise(){
        
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        // when you use the 'this.' it explicitly talks about the current objects class variable, NOT the local variable.
        // the difference is that the class variable exists within the object while the local variable only exists within the
        // function. You will run into issues with this in the future but just keep in mind the spaces these variables can exist in.
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    public void setItemName(String Name){
        itemName = Name;
        // If you don't name the items the same name you do not have the explicitly use 'this.' since we know which variables
        // are the class variables and the local variables.
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public void setQuantity(int newQuantity){
        quantity = newQuantity;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void incrementQuantity(){
        quantity += 1;
    }
    
    public String toString(){
        return itemName + "                 " + price + "                   " + serialNumber;
    }
    
}
