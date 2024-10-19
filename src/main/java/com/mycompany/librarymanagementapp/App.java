package com.mycompany.librarymanagementapp;

/**
 * Christopher Conquest
 * Programming II, Semester Project
 * Library Management System Application using JavaFX
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    
    Button exitButton = new Button("Egress");
    
    //Book menu
    ListView bookList = new ListView();
    TextField bookTitle = new TextField();
    TextField bookAuthor = new TextField();
    Button addBook = new Button("Add Book");
    Button deleteBook = new Button("Remove Book");
    Button checkOutBook = new Button("Check Out These Books");
    ComboBox<String> cardHolderCombo = new ComboBox();
    
    //Card Holder menu
    ListView cardList = new ListView();
    TextField cardFirstName = new TextField();
    TextField cardLastName = new TextField();
    Button addCardHolder = new Button("Add Card Holder");
    Button removeCardHolder = new Button("Remove Card Holder");
    
    //Check in/out menu
    ListView checkOutBooks = new ListView();
    Button checkInButton = new Button("Check In");
    
    //Main Menu Buttons
    Button bookMenu = new Button("Manage Books");
    Button cardMenu = new Button("Manage Card Holders");
    Button checkInOut = new Button("Check Books In/Out");
    
    //Back buttons
    Button bookBack = new Button("Back");
    Button cardBack = new Button("Back");
    Button checkBack = new Button("Back");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Wizard's Tower - Library Management System");
        
        //Main Menu
        GridPane mainGP = new GridPane();
        mainGP.setAlignment(Pos.CENTER);
        mainGP.setPadding(new Insets(25, 25, 25, 25));
        mainGP.setHgap(10);
        mainGP.setVgap(10);

        Scene mainScene = new Scene(mainGP, 800, 800);

        Label mainHeader = new Label("W I Z A R D ' S  T O W E R");
        
            //Box for menu buttons
        VBox menuButtons = new VBox(15);
        menuButtons.getChildren().add(bookMenu);
        menuButtons.getChildren().add(cardMenu);
        menuButtons.getChildren().add(checkInOut);
        menuButtons.getChildren().add(exitButton);

        mainGP.add(mainHeader, 0, 0);
        mainGP.add(menuButtons,0, 1);
        
            //Exit button
        exitButton.setOnAction(event -> {
            exit(stage);
        });
        
        
        //Book management menu
        
        GridPane bookGP = new GridPane();
        bookGP.setAlignment(Pos.CENTER);
        bookGP.setPadding(new Insets(25, 25, 25, 25));
        bookGP.setHgap(10);
        bookGP.setVgap(10);
        
        Scene bookScene = new Scene(bookGP, 800, 800);
        
        Label bookHeader = new Label("B O O K  M A N A G E R");
        Label titleLabel = new Label("Title: ");
        titleLabel.setMinWidth(50);
        Label authorLabel = new Label("Author: ");
        authorLabel.setMinWidth(50);
        
        //Combo box for card holders
        cardHolderCombo.setPromptText("Select A Card Holder");
        
        //Add names from text file
        File cardHolders = new File("cardHolders.txt");
        try{
            Scanner scanner = new Scanner(cardHolders);
            while (scanner.hasNextLine()){
                String name = scanner.nextLine();
                if (!name.equals(""))
            cardHolderCombo.getItems().add(name);
        }
        }catch (Exception e) {
            System.out.println("File Error.");
        }
        
            //Book list
        bookList.setPrefWidth(500);
        bookList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        File booksOnHand = new File("booksOnHand.txt");
        try{
            Scanner scanner = new Scanner(booksOnHand);
            while (scanner.hasNextLine()){
                String book = scanner.nextLine();
                if (!book.equals(""))
            bookList.getItems().add(book);
        }
        }catch (Exception e) {
            System.out.println("File Error.");
        }
        
        
        HBox titleBox = new HBox(15);
        titleBox.getChildren().add(titleLabel);
        titleBox.getChildren().add(bookTitle);
        
        HBox authorBox = new HBox(15);
        authorBox.getChildren().add(authorLabel);
        authorBox.getChildren().add(bookAuthor);
        
        HBox checkOutBox = new HBox(15);
        checkOutBox.getChildren().add(checkOutBook);
        checkOutBox.getChildren().add(cardHolderCombo);
        
            //Book menu text boxes
        VBox newBookText = new VBox(15);
        newBookText.getChildren().add(titleBox);
        newBookText.getChildren().add(authorBox);
        
            //Book menu buttons
        HBox addRemoveBook = new HBox(15);
        addRemoveBook.getChildren().add(addBook);
        addRemoveBook.getChildren().add(deleteBook);
        
        bookGP.add(bookHeader, 0, 0);
        bookGP.add(bookBack, 0, 1);
        bookGP.add(bookList, 0, 2);
        bookGP.add(newBookText, 0, 6);
        bookGP.add(addRemoveBook, 0, 7);
        bookGP.add(checkOutBox, 0, 8);
        
            //Add and remove book buttons
        addBook.setOnAction(event -> {
            addBook();
        });
        
        deleteBook.setOnAction(event -> {
            removeItem(bookList, "booksOnHand.txt");
        });
        
        checkOutBook.setOnAction(event -> {
            checkOut();
        });
        

        //Card Holder Menu
        
        GridPane cardGP = new GridPane();
        cardGP.setAlignment(Pos.CENTER);
        cardGP.setPadding(new Insets(25, 25, 25, 25));
        cardGP.setHgap(10);
        cardGP.setVgap(10);
        
        Scene cardScene = new Scene(cardGP, 800, 800);
        
        Label cardHeader = new Label("C A R D  H O L D E R  M A N A G E R");
        Label cardFirstLabel = new Label("First Name: ");
        cardFirstLabel.setMinWidth(50);
        Label cardLastLabel = new Label("Last Name: ");
        cardLastLabel.setMinWidth(50);
        
        
        HBox firstBox = new HBox(15);
        firstBox.getChildren().add(cardFirstLabel);
        firstBox.getChildren().add(cardFirstName);
        
        HBox lastBox = new HBox(15);
        lastBox.getChildren().add(cardLastLabel);
        lastBox.getChildren().add(cardLastName);
        
            //Card holder menu text boxes
        VBox newCardHolderText = new VBox(15);
        newCardHolderText.getChildren().add(firstBox);
        newCardHolderText.getChildren().add(lastBox);
        
            //Card holder menu buttons
        HBox addRemoveCard = new HBox(15);
        addRemoveCard.getChildren().add(addCardHolder);
        addRemoveCard.getChildren().add(removeCardHolder);
        
        //Card holder text file
        cardList.setPrefWidth(500);
        cardList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            Scanner scanner = new Scanner(cardHolders);
            while (scanner.hasNextLine()){
                String name = scanner.nextLine();
                if (!name.equals(""))
            cardList.getItems().add(name);
        }
        }catch (Exception e) {
            System.out.println("File Error.");
        }

        cardGP.add(cardHeader, 0, 0);
        cardGP.add(cardBack, 0, 1);
        cardGP.add(cardList, 0, 2);
        cardGP.add(newCardHolderText, 0, 6);
        cardGP.add(addRemoveCard, 0, 7);
        
            //Add/remove card holder button functions
        addCardHolder.setOnAction(event -> {
            addCardHolder();
        });
        removeCardHolder.setOnAction(event -> {
            removeItem(cardList, "cardHolders.txt");
        });
        
        //Check in / out Scene
        
        GridPane checkInOutGP = new GridPane();
        checkInOutGP.setAlignment(Pos.CENTER);
        checkInOutGP.setPadding(new Insets(25, 25, 25, 25));
        checkInOutGP.setHgap(10);
        checkInOutGP.setVgap(10);
        
        Scene checkInOutScene = new Scene(checkInOutGP, 800, 800);
        
        Label checkInOutHeader = new Label("M A N A G E  C H E C K  I N  /  O U T");
        
            //Books checked out list
        checkOutBooks.setPrefWidth(500);
        checkOutBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        File booksCheckedOut = new File("booksCheckedOut.txt");
        try{
            Scanner scanner = new Scanner(booksCheckedOut);
            while (scanner.hasNextLine()){
                String book = scanner.nextLine();
                if (!book.equals(""))
            checkOutBooks.getItems().add(book);
        }
        }catch (Exception e) {
            System.out.println("File Error.");
        }

        checkInOutGP.add(checkInOutHeader, 0, 0);
        checkInOutGP.add(checkBack, 0, 1);
        checkInOutGP.add(checkOutBooks, 0, 2);
        checkInOutGP.add(checkInButton,0,3);
        
        checkInButton.setOnAction(event -> {
            checkIn();
        });
        
        
        
        //SCENE BUTTON CONTROLS
        
        //Main Menu -> Book Menu button
        bookMenu.setOnAction(event -> {
            changeScene(stage, bookScene);
        });
        
        //Main Menu -> Card Holder Menu button
        cardMenu.setOnAction(event -> {
            changeScene(stage, cardScene);
        });
        
        //Main Menu -> Check In/Out Menu button
        checkInOut.setOnAction(event -> {
            changeScene(stage, checkInOutScene);
        });
        
        //Back to main menu button
        bookBack.setOnAction(event -> {
            changeScene(stage, mainScene);
        });
        cardBack.setOnAction(event -> {
            changeScene(stage, mainScene);
        });
        checkBack.setOnAction(event -> {
            changeScene(stage, mainScene);
        });
        
        
        stage.setScene(mainScene);
        stage.show();
    }
    
    //Exit method
    public void exit(Stage stage) {
        stage.close();
    }
    
    //Method for changing scenes
    public void changeScene(Stage st, Scene sc){
        st.setScene(sc);
        st.show();
    }
    
    //Method for checking out books
    public void checkOut() {
        if (cardHolderCombo.getSelectionModel().getSelectedItem() == null || bookList.getSelectionModel().getSelectedItem() == null) {
                Alert emptyComboAlert = new Alert(Alert.AlertType.ERROR);
                emptyComboAlert.setContentText("Please select a card holder and a book from the list...");
                emptyComboAlert.showAndWait();
            }else {
                ArrayList bookListArray = new ArrayList(bookList.getSelectionModel().getSelectedItems());
                for (Object book : bookListArray) {

                    checkOutBooks.getItems().add("[" + book + "] has been checked out by " + cardHolderCombo.getSelectionModel().getSelectedItem());
                    String filename = "booksCheckedOut.txt";
                    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));){
                        out.println("[" + book + "] has been checked out by " + cardHolderCombo.getSelectionModel().getSelectedItem());
                    }catch (IOException e){
                        System.out.println(filename + " cannot be found.");
                    }   
                }removeItem(bookList, "booksOnHand.txt");
        }
    }
    
    //Method for checking in
    public void checkIn() {
        ArrayList checkOutBookArray = new ArrayList(checkOutBooks.getSelectionModel().getSelectedItems());
        for (Object book : checkOutBookArray) {
            int startIndex = String.valueOf(book).indexOf('[');
            int endIndex = String.valueOf(book).indexOf(']');
            String bookFixed = String.valueOf(book).substring(startIndex +  1, endIndex);
            bookList.getItems().add(bookFixed);
            String filename = "booksOnHand.txt";
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));){
                out.println(bookFixed);
            }catch (IOException e){
                System.out.println(filename + " cannot be found.");
            }
        }removeItem(checkOutBooks, "booksCheckedOut.txt");
    }
    
    //Method for adding books
    public void addBook(){
        boolean duplicateBook = false;
        if (!bookTitle.getText().equals("") && !bookAuthor.getText().equals("")) {
            String newBook = '"' + bookTitle.getText() + '"' + " by " + bookAuthor.getText();
            ObservableList<String> list = bookList.getItems();
            for (String book : list) {
                if (newBook.equalsIgnoreCase(book)){
                    //Alert for duplicates and reset the text boxes
                     duplicateBook = true;
                     Alert dupAlert = new Alert(Alert.AlertType.ERROR);
                     dupAlert.setContentText("This tomb already exists in our records...");
                     dupAlert.showAndWait();
                     bookTitle.setText("");
                     bookAuthor.setText("");
                }else {
                    duplicateBook = false;
                }
            }
            
            if (!duplicateBook) {
                bookTitle.setText("");
                bookAuthor.setText("");
                bookList.getItems().add(newBook);
                String filename = "booksOnHand.txt";
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));){
                        out.println(newBook);
                    }catch (IOException e){
                        System.out.println(filename + " cannot be found.");
                    }
                }
            }else {
                    //Alert for errors and reset the text boxes
                    Alert emptyStringAlert = new Alert(Alert.AlertType.ERROR);
                    emptyStringAlert.setContentText("All tombs must have a title and an author...");
                    emptyStringAlert.showAndWait();
                    bookTitle.setText("");
                    bookAuthor.setText("");
                }
        }
    
    //Method for adding card holders
    public void addCardHolder(){
        boolean duplicate = false;
        if (!cardFirstName.getText().equals("") && !cardLastName.getText().equals("")) {
            String newCard = cardFirstName.getText() + " " + cardLastName.getText();
            ObservableList<String> list = cardList.getItems();
            for (String name : list) {
                if (newCard.equals(name)){
                    //Alert for duplicates and reset the text boxes
                     duplicate = true;
                     Alert dupAlert = new Alert(Alert.AlertType.ERROR);
                     dupAlert.setContentText("This fellow already exists in our records...");
                     dupAlert.showAndWait();
                     cardFirstName.setText("");
                     cardLastName.setText("");
                }else {
                    duplicate = false;
                }
            }
            
            if (!duplicate) {
                cardFirstName.setText("");
                cardLastName.setText("");
                cardList.getItems().add(newCard);
                String filename = "cardHolders.txt";
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));){
                        out.println(newCard);
                    }catch (IOException e){
                        System.out.println(filename + " cannot be found.");
                    }
                }
            }else {
                    //Alert for errors and reset the text boxes
                    Alert emptyStringAlert = new Alert(Alert.AlertType.ERROR);
                    emptyStringAlert.setContentText("All card holders must have a first and last name...");
                    emptyStringAlert.showAndWait();
                    cardFirstName.setText("");
                    cardLastName.setText("");
                }
    }
    
    //Method for removing items from listview and from text file
    //Please clean this up
    public void removeItem(ListView listview, String filePath) {
        ArrayList itemsToRemove = new ArrayList(listview.getSelectionModel().getSelectedItems());
        for (Object item : itemsToRemove){
            String itemToRemove = String.valueOf(item);
            listview.getItems().remove(itemToRemove);

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.replace(itemToRemove, "");
                    writer.write(line);
                    writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("File Error");
        }

        new File(filePath).delete();
        new File("temp.txt").renameTo(new File(filePath));
        }
    }
    
    //Launch
    public static void main(String[] args) {
        launch();
    }

}