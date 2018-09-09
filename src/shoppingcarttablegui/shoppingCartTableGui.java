/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcarttablegui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.omg.IOP.TransactionService;
/**
 *
 * @author vangu
 */
public class shoppingCartTableGui extends JPanel{
    private static JFrame frame;
    private JList List;
    private DefaultListModel ListModel;
    private JTextField Item;
    private JTextField ItemName;
    private JTextField ItemPrice;
    private JTextField ItemSerialNumber;
    private JLabel ItemNameLabel;
    private JLabel ItemPriceLabel;
    private JLabel ItemSerialNumberLabel;
    private DefaultTableModel newTable;
    private JTable table;
    private DefaultListModel shoppingCartListModel;
    private JList shoppingCartList;
    private JDialog checkOutDialog;
    private String[] columnNames = {"Name","Price","Serial Number"};
    
    public shoppingCartTableGui(){
        super(new BorderLayout());
         /* First you need to create the Model that the list is going to be */
         ListModel = new DefaultListModel();

         /* Now you can create the JList object with the Model as the argument. You are also 
         now able to edit the List to allow different settings and let how many ever
         elements be visible */
         

         List = new JList(ListModel);
         List.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
         List.setSelectedIndex(0);
         List.setVisibleRowCount(5);
         List.setSize(100, 100);
         
         newTable = new DefaultTableModel(columnNames,0);
         
         table = new JTable(newTable);

         
         
         
         /* adds the list to the scroll panel */
         JScrollPane ScrollPanel = new JScrollPane(table);
         JScrollPane ScrollPane2 = new JScrollPane(List);
         
         table.setFillsViewportHeight(true);
         
         /* adds the scroll panel to the frame */
         add(ScrollPanel, BorderLayout.NORTH);
         
         /* creating the button that allows users to add items to grocery list
         still needs to use java to check the list for conditions */
         
         /* another reminder is that the button won't appear until placed on a panel */
         JButton AddButton = new JButton("Add Item");
         AddListener addListener = new AddListener(AddButton);
         AddButton.setEnabled(false);
         AddButton.setActionCommand("Add Item");
         
         /* This is attaching the an AddListener object to the button to tell the
         application what to do when this button is clicked */
         AddListener AddListener = new AddListener(AddButton);
         AddButton.addActionListener(AddListener);
         
         JButton RemoveButton = new JButton("Remove Item");
         RemoveListener removeListener = new RemoveListener(RemoveButton);
         RemoveButton.setEnabled(true);
         RemoveButton.setActionCommand("Remove Item");
         RemoveButton.addActionListener(removeListener);
         
         JButton PrintButton = new JButton("Print Items");
         PrintListener printListener = new PrintListener(PrintButton);
         PrintButton.setEnabled(true);
         PrintButton.setActionCommand("Print Items");
         PrintButton.addActionListener(printListener);
         
         JButton ClearButton = new JButton("Clear log");
         clearListener clearListener = new clearListener(ClearButton);
         ClearButton.setEnabled(true);
         ClearButton.setActionCommand("Clear logs");
         ClearButton.addActionListener(clearListener);
         
         add(ScrollPane2, BorderLayout.EAST);
         Item = new JTextField(20);
         Item.addActionListener(addListener);
         
         ItemPrice = new JTextField(20);

         ItemSerialNumber = new JTextField(20);

         ItemPrice.addActionListener(addListener);
         ItemSerialNumber.addActionListener(addListener);

                  
         Item.getDocument().addDocumentListener(addListener);
         
         ItemPrice.getDocument().addDocumentListener(addListener);
         ItemSerialNumber.getDocument().addDocumentListener(addListener);
         
         ItemNameLabel = new JLabel();
         ItemPriceLabel = new JLabel();
         ItemSerialNumberLabel = new JLabel();
         
         ItemNameLabel.setText("Item Name: ");
         ItemPriceLabel.setText("Item Price: ");
         ItemSerialNumberLabel.setText("Item Serial Number: ");
         

         
         /* creating the panel where we attach our buttons to */
         JPanel Panel1 = new JPanel();
         Panel1.setLayout(new GridLayout(6,2,5,5));
         Panel1.add(AddButton);
         Panel1.add(RemoveButton);

         //Panel1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
         Panel1.add(ItemNameLabel);
         Panel1.add(Item);
         Panel1.add(ItemPriceLabel);
         Panel1.add(ItemPrice);
         Panel1.add(ItemSerialNumberLabel);
         Panel1.add(ItemSerialNumber);
         
         Panel1.add(PrintButton);
         Panel1.add(ClearButton);
         
         JButton addToCart = new JButton();
         addToCartListener add1 = new addToCartListener(addToCart);
         addToCart.addActionListener(add1);
         addToCart.setEnabled(true);
         
         JButton removeFromCart = new JButton();
         removeItemFromCart remove1 = new removeItemFromCart(removeFromCart);
         removeFromCart.addActionListener(remove1);
         removeFromCart.setEnabled(true);
         
         JButton checkOut = new JButton();
         checkOutListener checkOut1 = new checkOutListener(checkOut);
         checkOut.addActionListener(checkOut1);
         checkOut.setEnabled(true);
         /* adds the Panel to the frame */  
         
         shoppingCartListModel = new DefaultListModel();
         shoppingCartList = new JList(shoppingCartListModel);
         shoppingCartList.setBounds(145,0,400,400);
         
         JPanel Panel3 = new JPanel();
         Panel3.setLayout(null);
         addToCart.setBounds(10,10,115,25);
         addToCart.setText("Add item");
         
         removeFromCart.setBounds(10, 45,115,25);
         removeFromCart.setText("Remove item");
         
         checkOut.setBounds(10,80,115,25);
         checkOut.setText("Check out");
         
         Panel3.add(addToCart);
         Panel3.add(removeFromCart);
         Panel3.add(checkOut);
         Panel3.add(shoppingCartList);
         JTabbedPane tabbedPane = new JTabbedPane();
         tabbedPane.add("Shopping Cart",Panel3);
         tabbedPane.add("Catalog", Panel1);
         add(tabbedPane);
         
         // essentially what I have done is added all of the catalog section onto a tabbed pane that I created. It looks neater and easier to use
         // I will later try to create the shopping cart section of the tabbed pane to allow for adding of items as well as quantities
         // the above code pretty much just creates the catalog pane and doesn't touch into the shopping cart aspect of it yet
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Shopping Cart");
        frame.setLocation(600,300);
        frame.setPreferredSize(new Dimension(800,700));
        frame.setResizable(false);
        //frame.setLayout(new GridLayout(2,2,5,5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new shoppingCartTableGui();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        //Display the window.
        frame.pack();
        
        // inserting the login dialogPane here
        loginPane newLoginPane = new loginPane(frame);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /* will most likely move these Listener classes to another java file or either create inner classes specific to each button */
    
    class addToCartListener implements ActionListener{
        public JButton button;
        
        private addToCartListener(JButton addToCart) {
            this.button = addToCart;
        }
        public void actionPerformed(ActionEvent e) {
            if(table.getSelectedRow() >= 0 && table.getSelectedRow() < table.getRowCount()){
                Merchandise newCartItem = new Merchandise();
                for(int iterator = 0; iterator < 3; iterator++ ){
                    if(iterator ==0){
                        newCartItem.setItemName((String)table.getValueAt(table.getSelectedRow(),iterator));
                    }
                    if(iterator ==1){
                        newCartItem.setPrice(Double.parseDouble((String)table.getValueAt(table.getSelectedRow(),iterator)));
                    }
                    if(iterator ==2){
                        newCartItem.setSerialNumber((String)table.getValueAt(table.getSelectedRow(),iterator));
                    }
                }
                shoppingCartListModel.addElement(newCartItem);
            }
        }
        
    }
    
    class checkOutListener implements ActionListener{
        public JButton button;
        
        private checkOutListener(JButton checkOutListener) {
            this.button = checkOutListener;
        }
        public void actionPerformed(ActionEvent e) {
            if(shoppingCartListModel.size() == 0){
                return;
            }
            JFrame dialogFrame = new JFrame();
            checkOutDialog = new JDialog(dialogFrame,"checkout", true);
            checkOutDialog.setSize(500,300);
            checkOutDialog.setLocation(500,300);
            checkOutDialog.setResizable(false);
            checkOutDialog.setLayout(null);

            JButton finishTransaction = new JButton();
            finishTransaction.setLocation(100, 220);
            finishTransaction.setSize(100,25);
            finishTransaction.setText("Save");
            
            JButton cancelTransaction = new JButton();
            cancelTransaction.setLocation(300, 220);
            cancelTransaction.setSize(100,25);
            cancelTransaction.setText("Cancel");
            
            

            
            int size = shoppingCartListModel.size()-1;
            int size2 = size;

            ArrayList<Merchandise> itemListing = new ArrayList();
            
            Object[][] transactionItems = new  Object[size+1][];
            
            while(size >= 0){
                itemListing.add((Merchandise)shoppingCartListModel.getElementAt(size));
                size--;
            }
            
            int itemTotal = 0;
            for(int iterator =  0; iterator < size2; iterator++){
                Object[] items = {itemListing.get(iterator).getItemName(),itemListing.get(iterator).getPrice(),itemListing.get(iterator).getSerialNumber()};
                itemTotal += itemListing.get(iterator).getPrice();
                transactionItems[iterator] = items;
            }
            Object[] subTotal = {"Total",itemTotal,null};
            transactionItems[size2] = subTotal;
            DefaultTableModel transctions = new DefaultTableModel(transactionItems, columnNames);
            JTable transactionTable = new JTable(transctions); 
            //transactionTable.setFillsViewportHeight(true);
            transactionTable.setLocation(0,0);
            transactionTable.setSize(500, 210);
            
            checkOutDialog.add(finishTransaction);
            checkOutDialog.add(transactionTable);
            checkOutDialog.add(cancelTransaction);
            checkOutDialog.setVisible(true);
        }
        
    }
    
    class removeItemFromCart implements ActionListener{
        public JButton button;
        
        private removeItemFromCart(JButton removeItemFromCart) {
            this.button = removeItemFromCart;
        }
        public void actionPerformed(ActionEvent e) {
            int index = shoppingCartList.getSelectedIndex();
            int size = shoppingCartListModel.size();
            if(size != 0 && (index >= 0 && index < size)){
                int[] selectedIndices = shoppingCartList.getSelectedIndices();
                
                for(int i = 0; i < selectedIndices.length / 2; i++){
                    int temp = selectedIndices[i];
                    selectedIndices[i] = selectedIndices[selectedIndices.length - i - 1];
                    selectedIndices[selectedIndices.length - i - 1] = temp;
                }
                
                for(int section: selectedIndices){
                    // might not work depending on if we remove items in the middle or beginning of the list
                    shoppingCartListModel.removeElementAt(section);
                }
            }

            //Select an index.
            index--;
            if(index < 0){
                index++;
            }
            if(shoppingCartListModel.size() != 0){
            shoppingCartList.setSelectedIndex(index);
            }
            
        }
        
    }
    
    class clearListener implements ActionListener{
        public JButton button;
         
        public clearListener(JButton PrintButton){
            this.button = PrintButton;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ListModel.getSize()!= 0){
                ListModel.clear();
            }
        }
    }
    
    class PrintListener implements ActionListener{
        public JButton button;
         
        public PrintListener(JButton PrintButton){
            this.button = PrintButton;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String toBePrinted = new String();
            if(newTable.getRowCount() != 0){
                for(int iterator1 = 0; iterator1 < newTable.getRowCount(); iterator1++){
                    for(int iterator2 =  0; iterator2 < newTable.getColumnCount(); iterator2++){
                        
                        toBePrinted = toBePrinted + table.getValueAt(iterator1, iterator2) + " ";
                    }
                    toBePrinted += "\n";
                    ListModel.add(0, toBePrinted);
                    toBePrinted = "";
                }
            }
        }
        
    }
    
    class RemoveListener implements ActionListener {
        private JButton button;  
        
        public RemoveListener(JButton RemoveButton) {
            this.button = RemoveButton;
        }
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            if(newTable.getRowCount() == 0){
                Toolkit.getDefaultToolkit().beep();
                return;
                }
            int index = table.getSelectedRow();
            int size = newTable.getRowCount();
            if(index > size || index < 0){
                table.setRowSelectionInterval(0,0);
                index = table.getSelectedRow();
            }
            newTable.removeRow(index);
           

            //Select an index.
            index--;
            if(index < 0){
                index++;
            }
            if(newTable.getRowCount() != 0){
            table.setRowSelectionInterval(index, index);
            }
        }
    }
    
    /* This is the AddListener class that we are implementing. It essentially does all of the
    checking and inserting into the List that we made earlier*/
    class AddListener implements ActionListener, DocumentListener{
        private boolean IsEnabled = false;
        private JButton Button;
        
        public AddListener(JButton E) {
            this.Button = E;
        }

        @Override
        public void actionPerformed(ActionEvent e) throws NumberFormatException{
            String GroceryItem = Item.getText();
            String GroceryPrice = ItemPrice.getText();
            String GrocerySerialNumber = ItemSerialNumber.getText();
            try{
                if(Double.valueOf(GroceryPrice) != null){
                    // just checks if groceryprice is a double
                }
            }
            catch(NumberFormatException E) {
                System.out.println("Invalid input\nPlease type in a number for the price");
                ItemPrice.setText("");
                return;
            }
            Object[] newItem = {GroceryItem,GroceryPrice,GrocerySerialNumber};
            newTable.addRow(newItem);
            Item.requestFocusInWindow();
            Item.setText("");
            ItemPrice.setText("");
            ItemSerialNumber.setText("");
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if(IsEnabled == false){
                Button.setEnabled(true);
            }
            
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if(Item.getText().equals("") || ItemPrice.getText().equals("") || ItemSerialNumber.equals("")){
                Button.setEnabled(false);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }

    
}