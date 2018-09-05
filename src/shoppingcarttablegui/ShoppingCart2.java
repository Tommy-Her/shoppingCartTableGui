/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcarttablegui;

import java.util.ArrayList;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import shoppingcarttablegui.inputHelper;
import shoppingcarttablegui.Size;

/**
 *
 * @author vangu
 */
public class ShoppingCart2 {
    private ArrayList<Merchandise> Merchandise = new ArrayList();
    // This ArrayList is essentially going to be the thing that contains all of our items (merchandise)
    // we will manipulate this ArrayList by adding to it, popping items off, and printing from it
    // eventually we will actually create the objects that go into this list but for the time being I will use the generic
    // <String> so that we can insert basic item names into it
    
    private static boolean Close = false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        String Response;
        String ItemManipulated;
        System.out.println("Hello you are running the ShoppingCart1 application\n");
        ShoppingCart2 shoppingcart = new ShoppingCart2();
        // this creates the ShoppingCart1 object within our main function
        // since we did not specify a constructor (we didn't create a constructor)
        // Java automatically creates a default constructor for us.
        while(Close == false){
            Response = inputHelper.getInput("Here are your choices of action:\n"
                    + "1) Add Item -- go ahead and type, 'add' and press enter. You will then be prompted for the item's name you would like to add\n"
                    + "2) Remove Item -- go ahead and type, 'remove' and press enter. You will then be prompted for item's name you would like removed\n"
                    + "3) Print Shopping -- go ahead and type, 'print' to see all of your items in the cart\n"
                    + "4) Close -- this closes the application");
            Response = toLowerCase(Response);
            switch(Response){
                case "add":
                    ItemManipulated = inputHelper.getInput("What is the name of the item you would like added?");
                    if (shoppingcart.addItem(ItemManipulated)){
                        System.out.println("You have added " + ItemManipulated);
                    }
                    Thread.sleep(1000);
                    break;
                case "1":
                    ItemManipulated = inputHelper.getInput("What is the name of the item you would like added?");
                    if (shoppingcart.addItem(ItemManipulated)){
                        System.out.println("You have added " + ItemManipulated);
                    }
                    Thread.sleep(1000);
                    break;
                case "remove":
                    ItemManipulated = inputHelper.getInput("What is the name of the item you would like removed?");            
                    shoppingcart.removeItem(ItemManipulated);
                    System.out.println("You have successfully removed " + ItemManipulated);
                        // this looks a little tricky because I don't have curly braces but all it is doing is that it runs whatever is in the 'If' statement
                        // and if that is true it will run ONLY the next line which in this case is the println statement
                    Thread.sleep(1000);
                    break;
                case "2":
                    ItemManipulated = inputHelper.getInput("What is the name of the item you would like removed?");            
                    shoppingcart.removeItem(ItemManipulated);
                    System.out.println("You have successfully removed " + ItemManipulated);
                    Thread.sleep(1000);
                    break;
                case "print":
                    shoppingcart.printList();
                    Thread.sleep(2000);
                    break;
                case "3":
                    shoppingcart.printList();
                    Thread.sleep(2000);
                    break;
                case "close":
                    Close = true;
                    break;
                case "4":
                    Close = true;
                    break;
                default: 
                    System.out.println("Sorry, I didn't quite get that");
                    Thread.sleep(2000);
            
        }
        }
        System.out.println("Closing ShoppingCart1 Application");
    }
    
    private boolean addItem(String Item){
        String Response = inputHelper.getInput("What type of item is this?\n"
                + "1) Apparel\n"
                + "2) Produce\n");
        int quantity;
        // found a bug with the quantity question. If represented with a float or double it will crash the application. I will restrict their answers
        // later or possibly modify the inputHelper class to accommedate for this
        double price;
        switch(Response){
            case "Apparel":
                quantity = inputHelper.getInteger("How many copies of this item did you want to add?");
                price = inputHelper.getDouble("How much did this item cost?");
                addApparel(Item, quantity, price);
                return true;
            case "1":
                quantity = inputHelper.getInteger("How many copies of this item did you want to add?");
                price = inputHelper.getDouble("How much did this item cost?");
                addApparel(Item, quantity, price);
                return true;
            case "Produce":
                quantity = inputHelper.getInteger("How many copies of this item did you want to add?");
                price = inputHelper.getDouble("How much did this item cost?");
                addProduce(Item, quantity, price);
                return true;
            case "2":
                quantity = inputHelper.getInteger("How many copies of this item did you want to add?");
                price = inputHelper.getDouble("How much did this item cost?");
                addProduce(Item, quantity, price);
                return true;
            default:
                System.out.println("That type of item doesn't seem to exist yet, sorry.");
                return false;
                // I will eventually make this a loop that continues to ask the user what the type of item it is. Currently it will ask the user
                // what type of item it is but if the person doesn't not response with a correct item type it will kick them back to the main loop
        }
        // this is very simple in that all we are doing is using ArrayList's default add function to add the item
        // to the list. In the future we will want to add checks to this AddItem() function that will check
        // for duplicate items and then if it finds duplicates we will want to increment the quantity instead
        // of adding a duplicate item to the cart
    }
    
    private void addApparel(String Item, int quantity, double price){
        if(isItemInCart(Item)){
            increaseItem(Item, quantity);
            return;
        }
        // If the item already exists in the cart then just increment the quantity of it by one
        else{
            Apparel newItem = new Apparel();
            newItem.setItemName(Item);
            newItem.setQuantity(quantity);
            newItem.setPrice(price);
            Size size = inputHelper.getSize("What size of apparel?\n"
                    + "1) extra small\n"
                    + "2) small\n"
                    + "3) medium\n"
                    + "4) large\n"
                    + "5) extra large\n");
            if(size != null){
                newItem.setSize(size);
                Merchandise.add(newItem);
                System.out.println("You have successfully added " + Item);
            }
            else{
                System.out.println("Looks like we're going to have to restart now huh?");
                // This is another example of where I want to continue the loop until they want to close out or if they pick a correct size
        }
        }
    }
    
    private void addProduce(String Item, int quantity, double price){
        if(isItemInCart(Item)){
            increaseItem(Item, quantity);
            return;
        }
        else{
            Produce newItem = new Produce();
            newItem.setItemName(Item);
            newItem.setQuantity(quantity);
            newItem.setPrice(price);
            Merchandise.add(newItem);
        }
    }
    
    private void increaseItem(String Item, int quantity){
        for(Merchandise item: Merchandise){
            if(item.getItemName() == Item){
                while(quantity > 0){
                    item.incrementQuantity();
                    quantity--;
                }
            }
        }
    }
    
    private boolean removeItem(String Item){
        for(Merchandise item: Merchandise){
            if(item.getItemName().compareTo(Item) == 0){
                return Merchandise.remove(item);
            }
        }
            // this is also very simple since we are using the default remove() function from the ArrayList.
            // We are returning the item with this function to later display to the user which item was removed
            // from the ShoppingCart
        System.out.println("Sorry bud, but that item isn't in your shopping cart");
        return false;
        
    }
    
    private void printList(){
        if(Merchandise.isEmpty()){
            System.out.println("Sorry bud, but your shopping cart is empty");
            return;
            // not sure if I want to have return here or a break (I assume return though)
        }
        
        double total = 0;
        System.out.println("Item Name : Price : Quantity");
        for(Merchandise item: Merchandise){
            System.out.println(item);
            // this will iterate through the list of items in the shopping cart and will print each one out
            // if you have questions regarding a FOR loop do not hesitate to ask me
            total = total + item.getPrice() * item.getQuantity();
        }
        System.out.println("Total price of items: " + total);
    }
    
    private boolean isItemInCart(String Item){
        for(Merchandise item: Merchandise){
            if(item.getItemName().compareTo(Item) == 0){
                return true;
            }
        }
        return false;
    }
    // this is just a helper function that looks for the Item in the cart and if the item is in the cart
    // it returns true
}
