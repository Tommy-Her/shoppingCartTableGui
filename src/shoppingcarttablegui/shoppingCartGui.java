/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcarttablegui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
/**
 *
 * @author vangu
 */
public class shoppingCartGui extends JPanel{
    
    private JList List;
    private DefaultListModel ListModel;
    private JTextField Item;
    private JTextField ItemName;
    private JTextField ItemPrice;
    private JTextField ItemSerialNumber;
    private JLabel ItemNameLabel;
    private JLabel ItemPriceLabel;
    private JLabel ItemSerialNumberLabel;
    
    public shoppingCartGui(){
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
         
         /* adds the list to the scroll panel */
         JScrollPane ScrollPanel = new JScrollPane(List);
         
         /* adds the scroll panel to the frame */
         add(ScrollPanel, BorderLayout.CENTER);
         
         /*
         ListModel2 = new DefaultListModel();
         List2 = new JList(ListModel2);
         List2.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
         List2.setSelectedIndex(0);
         List2.setVisibleRowCount(5);  
         JScrollPane ScrollPanel2 = new JScrollPane(List2);
         add(ScrollPanel2, BorderLayout.CENTER);
         */
         
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
         Panel1.setLayout(new GridLayout(2,4,5,5));
         Panel1.add(AddButton);
         Panel1.add(RemoveButton);

         //Panel1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
         Panel1.add(ItemNameLabel);
         Panel1.add(Item);
         Panel1.add(ItemPriceLabel);
         Panel1.add(ItemPrice);
         Panel1.add(ItemSerialNumberLabel);
         Panel1.add(ItemSerialNumber);
         
         /* adds the Panel to the frame */         
         add(Panel1, BorderLayout.PAGE_END);
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Tommy's Grocery List");
        frame.setLocation(600,400);
        //frame.setLayout(new GridLayout(2,2,5,5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new shoppingCartGui();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
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

    class RemoveListener implements ActionListener {
        private JButton button;  
        
        public RemoveListener(JButton RemoveButton) {
            this.button = RemoveButton;
        }
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            if(ListModel.getSize() == 0){
                Toolkit.getDefaultToolkit().beep();
                return;
                }
            
            int index = List.getSelectedIndex();
            ListModel.remove(index);

            int size = ListModel.getSize();

            //Select an index.
                if (index == ListModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                List.setSelectedIndex(index);
                List.ensureIndexIsVisible(index);
            
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
            Merchandise NewMerchandiseItem = null;
            try{
                if(Double.valueOf(GroceryPrice) != null){
                    NewMerchandiseItem = new Merchandise(GroceryItem, GrocerySerialNumber, Double.parseDouble(GroceryPrice));
                }
            }
            catch(NumberFormatException E) {
                System.out.println("Invalid input\nPlease type in a number for the price");
                ItemPrice.setText("");
                return;
            }
            if(List.getSelectedIndex() == -1){
                ListModel.add(0, NewMerchandiseItem);
                Item.requestFocusInWindow();
                Item.setText("");    
                ItemPrice.setText("");
                ItemSerialNumber.setText("");
                List.setSelectedIndex(List.getSelectedIndex()+1);
                return;
            }
            
            ListModel.add(List.getSelectedIndex()+1, NewMerchandiseItem);
            Item.requestFocusInWindow();
            Item.setText("");
            ItemPrice.setText("");
            ItemSerialNumber.setText("");
            List.setSelectedIndex(List.getSelectedIndex()+1);
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