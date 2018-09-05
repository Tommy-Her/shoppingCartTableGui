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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import shoppingcarttablegui.Size;
/**
 *
 * @author vangu
 */
public class inputHelper {
    public static void main (String[] args){
        String testString = inputHelper.getInput("hello");
        System.out.println(testString);
    }
    
    
    public static String getInput(String prompt){
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println(prompt);
        System.out.flush();
        
        try{
            return stdin.readLine();
        }
        catch(Exception E){
            return "Error" + E.getMessage();
        }
            
    }
    
    public static Double getDouble(String prompt) throws NumberFormatException {
        String input = getInput(prompt);
        return Double.parseDouble(input);
    }
    
    public static Integer getInteger(String prompt) throws NumberFormatException{
        String input = getInput(prompt);
        return Integer.parseInt(input);
    }
    
    public static Size getSize(String prompt){
        String input = toLowerCase(getInput(prompt));
        switch(input){
            case "extra small":
                return Size.extra_small;
            case "1":
                return Size.extra_small;
            case "small":
                return Size.small;
            case "2":
                return Size.small;
            case "medium":
                return Size.medium;
            case "3":
                return Size.medium;
            case "large":
                return Size.large;
            case "4":
                return Size.large;
            case "extra large":
                return Size.extra_large;
            case "5":
                return Size.extra_large;
            default:
                System.out.println("Sorry but that is not a size");
                return null;
        }
    }
    // I am not sure this is a good idea leaving so much code outside of the main class. I think it is a bit unncessary
    // and should be left in the main class (might change this later)
}