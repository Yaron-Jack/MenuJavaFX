/**
 * Class controller to initialize menu with reading the file contents and buttons to get user choice
 */
package com.example.maman13part2real;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerMenu {
    //exception variable for drop down menu
    private Exception badChoice = new Exception("ERROR IN DROPDOWN");
    //booleans to check buttons
    private static boolean firstBeenPressed = false;
    private static boolean secondBeenPressed = false;
    private static boolean thirdBeenPressed = false;
    private static Order customerOrder = new Order();//create new order  for client order
    private MenuFromDoc doc = new MenuFromDoc();//create instance MenuFromDoc to get the menu from document

    private static String menu;

    private static String selectedAmount;
    private static int amount;
    private static int dishSelected;
    private static int choiceCourseSelected = 0;

    @FXML
    private CheckBox firstDish;
    @FXML
    private CheckBox secondDish;
    @FXML
    private CheckBox thirdDish;

    @FXML
    private TextArea textMenu;

    @FXML
    private Button menuBtn;

    @FXML
    private ComboBox<String> dropDownCourseBtn;

    @FXML
    private ComboBox<String> amountDropDownBtn;

    @FXML
    private Label welcomeText;

    public ControllerMenu() throws Exception {
    }

    /**
     * Initialize function to start the menu on board
     * @throws Exception
     */
    @FXML
    void initialize() throws Exception {
        // Initialize the dropdown courseList
        ObservableList<String> courseList = FXCollections.observableArrayList("First course", "Main course","Dessert");
        dropDownCourseBtn.setItems(courseList);
        // Initialize the dropdown amount
        ObservableList<String> amountList = FXCollections.observableArrayList("1", "2","3");
        amountDropDownBtn.setItems(amountList);
        // Initialize the menu from document
        String menu = doc.printCourseMenu(0);
        textMenu.setText(menu);


    }

    /**
     * main drop down button for courses with three choices - "First course", "Main course","Dessert"
     * @param event - course selected
     */
    @FXML
    void dropDownCourseBtnPressed(ActionEvent event) {
        selectedAmount = dropDownCourseBtn.getSelectionModel().getSelectedItem();

        switch(selectedAmount) {
            case "First course":
                menu = doc.printCourseMenu(1);
                choiceCourseSelected =1;
                textMenu.setText(menu);
                break;
            case "Main course":
                menu = doc.printCourseMenu(2);
                choiceCourseSelected = 2;
                textMenu.setText(menu);
                break;
            case "Dessert":
                menu = doc.printCourseMenu(3);
                choiceCourseSelected = 3;
                textMenu.setText(menu);
                break;
            default://none selected
                Exception badChoice;
                break;
        }
    }

    /**
     * amount drop down button for selecting amount of dish with three choices - "1", "2","3"
     * @param event - amount selected
     */
    @FXML
    void amountDropDownBtnPressed(ActionEvent event) {
        selectedAmount = amountDropDownBtn.getSelectionModel().getSelectedItem();

        switch(selectedAmount) {
            case "1":
             amount = 1;
                break;
            case "2":
                amount = 2;
                break;
            case "3":
               amount = 3;
                break;
            default://none chosen
                Exception badChoice;
                break;
        }
    }

    /**
     * first dish selected or unselected by customer
     * function will update order only if course and amount and dish is selected
     * @param event - first dish selected or unselected
     */
    @FXML
    void firstDishPressed(ActionEvent event) {
        if (!firstBeenPressed)
        {
            dishSelected = 0;
            if (choiceCourseSelected != 0 && amount != 0) {
                customerOrder = new Order(doc.dishPressedPrice(choiceCourseSelected, dishSelected), amount, doc.dishPressedName(choiceCourseSelected, dishSelected));
                customerOrder.AddToLinkedList(customerOrder);
            }
            firstBeenPressed = true;
            secondBeenPressed = false;
            thirdBeenPressed = false;
        }
    }
    /**
     * second dish selected or unselected by customer
     * function will update order only if course and amount and dish is selected
     * @param event - first dish selected or unselected
     */
    @FXML
    void secondDishPressed(ActionEvent event) {
        if (!secondBeenPressed) {
            dishSelected = 1;
            if (choiceCourseSelected != 0 && amount != 0) {
                customerOrder = new Order(doc.dishPressedPrice(choiceCourseSelected, dishSelected), amount, doc.dishPressedName(choiceCourseSelected, dishSelected));
                customerOrder.AddToLinkedList(customerOrder);
            }
            secondBeenPressed = true;
            firstBeenPressed = false;
            thirdBeenPressed = false;
        }
    }
    /**
     * third dish selected or unselected by customer
     * function will update order only if course and amount and dish is selected
     * @param event - first dish selected or unselected
     */
    @FXML
    void thirdDishPressed(ActionEvent event) {
        if (!thirdBeenPressed) {
            dishSelected = 2;
            if (choiceCourseSelected!= 0 && amount != 0) {
                customerOrder = new Order(doc.dishPressedPrice(choiceCourseSelected, dishSelected), amount, doc.dishPressedName(choiceCourseSelected, dishSelected));
                customerOrder.AddToLinkedList(customerOrder);
            }
            firstBeenPressed = false;
            secondBeenPressed = false;
        }
    }

    /**
     * menu button to proceed with order
     * three options will be shown to user to select from: "Approve", "Delete and Update", "Cancel"
     * "Approve" - user will be asked for name and ID
     * both will be used as name of document created and contents will be his order
     * "Delete and Update" - order will be cancelled and user will be back in main menu
     *"Cancel" - user will be back in main menu
     * @param event - menu button clicked
     */
    @FXML
    void menuBtnClicked(ActionEvent event) {
        Object[] optionsSelect = {"Approve", "Delete and Update", "Cancel"};

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("How would you like to proceed with the order: "));
        for (String line : customerOrder.toString().split("\n")) {
            panel.add(new JLabel(line));
        }
        JTextField textField = new JTextField(10);
        panel.add(textField);

        int result = JOptionPane.showOptionDialog(null, panel, "Proceeding choice",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, optionsSelect, null);

        switch (result) {
            case JOptionPane.YES_OPTION:
                String userId = JOptionPane.showInputDialog(null, "Please enter your ID:");
                String userName = JOptionPane.showInputDialog(null, "Please enter your name:");
                String message = "Hello " + userName + ", your ID is " + userId + ". You selected 'Approve'.";
                JOptionPane.showMessageDialog(null, message);
                File file = makeFile(userId, userName,customerOrder.toString());
                customerOrder.deleteLinkedList();
                break;

            case JOptionPane.NO_OPTION:
                customerOrder = new Order();
                customerOrder.deleteLinkedList();
                JOptionPane.showMessageDialog(null, "You selected 'Update'");
                break;

            case JOptionPane.CANCEL_OPTION:
                JOptionPane.showMessageDialog(null, "You selected 'Cancel'");
                break;

            default:
                JOptionPane.showMessageDialog(null, "You didn't select any option");
                break;
        }
    }

    /**
     * make file of user's order
     * @param userId - client ID
     * @param userName - client name
     * @param menu - order of client
     * @return
     */
    private File makeFile(String userId, String userName, String menu) {
        System.out.println(menu);
        String fileName = "Order of " + userId + "_" + userName + ".txt";
        String fileCreate = "./" + fileName;  // put the directory you would like the file to be in
        File file = new File(fileCreate);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(menu);
                bufferedWriter.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
        return file;
    }



}
