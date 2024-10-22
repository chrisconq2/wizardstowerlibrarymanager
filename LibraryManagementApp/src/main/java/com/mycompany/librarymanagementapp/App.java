package com.mycompany.librarymanagementapp;

/**
 * W I Z A R D ' S   T O W E R
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    Button checkInOut = new Button("Manage Checked Out Books");
    
    //Back buttons
    Button bookBack = new Button("Back");
    Button cardBack = new Button("Back");
    Button checkBack = new Button("Back");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Wizard's Tower - Library Management System");
        
        //FONT
        Font wizFontXL = Font.loadFont(getClass().getResourceAsStream("/fonts/GoudyMediaevalRegular.ttf"), 48);
        
        //STYLING
        bookMenu.setFont(Font.font("Times New Roman", 24));
        bookMenu.setStyle("-fx-background-color: white;" 
        + "-fx-border-color: black;"              
        + "-fx-border-width: 2px;"           
        + "-fx-border-radius: 25px;"                
        + "-fx-background-radius: 25px;"  
        + "-fx-padding: 10px 20px;");
        
        cardMenu.setFont(Font.font("Times New Roman", 24));
        cardMenu.setStyle("-fx-background-color: white;" 
        + "-fx-border-color: black;"              
        + "-fx-border-width: 2px;"           
        + "-fx-border-radius: 25px;"                
        + "-fx-background-radius: 25px;"  
        + "-fx-padding: 10px 20px;"); 
        
        checkInOut.setFont(Font.font("Times New Roman", 24));
        checkInOut.setStyle("-fx-background-color: white;" 
        + "-fx-border-color: black;"              
        + "-fx-border-width: 2px;"           
        + "-fx-border-radius: 25px;"                
        + "-fx-background-radius: 25px;"  
        + "-fx-padding: 10px 20px;");
        
        exitButton.setFont(Font.font("Times New Roman", 16));
        exitButton.setStyle("-fx-background-color: white;" 
        + "-fx-border-color: black;"              
        + "-fx-border-width: 2px;"           
        + "-fx-border-radius: 25px;"                
        + "-fx-background-radius: 25px;"  
        + "-fx-padding: 10px 20px;");
        
        bookBack.setFont(Font.font("Times New Roman", 24));
        bookBack.setStyle("-fx-background-color: white;" 
        + "-fx-border-color: black;"              
        + "-fx-border-width: 2px;"           
        + "-fx-border-radius: 25px;"                
        + "-fx-background-radius: 25px;"  
        + "-fx-padding: 10px 20px;");
        
        cardBack.setFont(Font.font("Times New Roman", 24));
        cardBack.setStyle("-fx-background-color: white;" 
        + "-fx-border-color: black;"              
        + "-fx-border-width: 2px;"           
        + "-fx-border-radius: 25px;"                
        + "-fx-background-radius: 25px;"  
        + "-fx-padding: 10px 20px;");
        
        checkBack.setFont(Font.font("Times New Roman", 24));
        checkBack.setStyle("-fx-background-color: white;" 
        + "-fx-border-color: black;"              
        + "-fx-border-width: 2px;"           
        + "-fx-border-radius: 25px;"                
        + "-fx-background-radius: 25px;"  
        + "-fx-padding: 10px 20px;");
        
        checkInButton.setFont(Font.font("Times New Roman", 24));
        checkInButton.setStyle("-fx-background-color: white;" 
        + "-fx-border-color: black;"              
        + "-fx-border-width: 2px;"           
        + "-fx-border-radius: 25px;"                
        + "-fx-background-radius: 25px;"  
        + "-fx-padding: 10px 20px;");
        
        //Main Menu
        GridPane mainGP = new GridPane();
        mainGP.setStyle("-fx-background-color: #32578B;");
        mainGP.setAlignment(Pos.TOP_CENTER);
        mainGP.setPadding(new Insets(25, 25, 25, 25));
        mainGP.setHgap(10);
        mainGP.setVgap(10);
        
        //Set initial main scene
        Scene mainScene = new Scene(mainGP, 800 , 800);
        
        Text mainHeader = new Text();
        mainHeader.setText("WIZARD'S TOWER");
        mainHeader.setFont(wizFontXL);
        
        Line border = new Line(0, 0, 200, 0);
        border.setStrokeWidth(2);
        border.setStroke(javafx.scene.paint.Color.BLACK); 
        
        Text mainSubHeader = new Text();
        mainSubHeader.setText("Library Management System");
        mainSubHeader.setFont(Font.font("Times New Roman", 24));
        
        //Menu image
        Image wizard = new Image("/wizardStudying.jpg");
        ImageView wizardImage = new ImageView(wizard);
        wizardImage.setFitWidth(500);
        wizardImage.setFitHeight(825);
        
        HBox imageBox = new HBox(15);
        imageBox.getChildren().add(wizardImage);
        
            //Box for menu buttons
        VBox menuButtons = new VBox(15);
        menuButtons.getChildren().add(mainHeader);
        menuButtons.getChildren().add(border);
        menuButtons.getChildren().add(mainSubHeader);
        menuButtons.getChildren().add(bookMenu);
        menuButtons.getChildren().add(cardMenu);
        menuButtons.getChildren().add(checkInOut);
        menuButtons.getChildren().add(exitButton);
        menuButtons.setAlignment(Pos.CENTER);
        
        mainGP.add(imageBox, 0,0);
        mainGP.add(menuButtons,2, 0);
        
            //Exit button
        exitButton.setOnAction(event -> {
            exit(stage);
        });
        
        
        //Book management menu
        
        GridPane bookGP = new GridPane();
        bookGP.setAlignment(Pos.CENTER);
        bookGP.setStyle("-fx-background-color: #32578B;");
        bookGP.setPadding(new Insets(25, 25, 25, 25));
        bookGP.setHgap(10);
        bookGP.setVgap(10);
        
        Text bookHeader = new Text();
        bookHeader.setText("B O O K  M A N A G E R");
        bookHeader.setFont(Font.font("Times New Roman", 48));
        
        Label titleLabel = new Label("Title: ");
        titleLabel.setMinWidth(50);
        Label authorLabel = new Label("Author: ");
        authorLabel.setMinWidth(50);
        
        //Combo box for card holders
        cardHolderCombo.setPromptText("Select A Card Holder");
        
        //Add names from text file to the Combo Box used for checking out books
        try{
            Scanner scanner = new Scanner("cardHolders.txt");
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
        readFile("booksOnHand", bookList);
        
        //Header and back button
        HBox headerBackBook = new HBox(25);
        headerBackBook.setAlignment(Pos.CENTER);
        headerBackBook.getChildren().add(bookBack);
        headerBackBook.getChildren().add(bookHeader);
        
        //Title textbox and label
        HBox titleBox = new HBox(15);
        titleBox.getChildren().add(titleLabel);
        titleBox.getChildren().add(bookTitle);
        
        //Author textbox and label
        HBox authorBox = new HBox(15);
        authorBox.getChildren().add(authorLabel);
        authorBox.getChildren().add(bookAuthor);
        
        //Check out button and combo box
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
        
        bookGP.add(headerBackBook, 0, 0);
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
        cardGP.setStyle("-fx-background-color: #32578B;");
        cardGP.setPadding(new Insets(25, 25, 25, 25));
        cardGP.setHgap(10);
        cardGP.setVgap(10);

        //Scene cardScene = new Scene(cardGP, 800 , 800);
        
        Text cardHeader = new Text();
        cardHeader.setText("C A R D  H O L D E R S");
        cardHeader.setFont(Font.font("Times New Roman", 48));
        
        Label cardFirstLabel = new Label("First Name: ");
        cardFirstLabel.setMinWidth(50);
        Label cardLastLabel = new Label("Last Name: ");
        cardLastLabel.setMinWidth(50);
        
        //Header and back button
        HBox headerBackCard = new HBox(25);
        headerBackCard.setAlignment(Pos.CENTER);
        headerBackCard.getChildren().add(cardBack);
        headerBackCard.getChildren().add(cardHeader);
        
        //First name textbox and label
        HBox firstBox = new HBox(15);
        firstBox.getChildren().add(cardFirstLabel);
        firstBox.getChildren().add(cardFirstName);
        
        //Last name textbox and label
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
        cardList.setMaxWidth(500);
        cardList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        readFile("cardHolders", cardList);

        cardGP.add(headerBackCard, 0, 0);
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
        checkInOutGP.setStyle("-fx-background-color: #32578B;");
        checkInOutGP.setPadding(new Insets(25, 25, 25, 25));
        checkInOutGP.setHgap(10);
        checkInOutGP.setVgap(10);
        
        //Scene checkInOutScene = new Scene(checkInOutGP, 800 , 800);
        
        Text checkInOutHeader = new Text();
        checkInOutHeader.setText("M A N A G E  C H E C K  I N  /  O U T");
        checkInOutHeader.setFont(Font.font("Times New Roman", 48));
        
        
            //Books checked out list
        checkOutBooks.setPrefWidth(600);
        checkOutBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        readFile("booksCheckedOut", checkOutBooks);
        
        //Header and back button
        HBox headerBackCheckOut = new HBox(25);
        headerBackCheckOut.setAlignment(Pos.CENTER);
        headerBackCheckOut.getChildren().add(checkBack);
        headerBackCheckOut.getChildren().add(checkInOutHeader);

        checkInOutGP.add(headerBackCheckOut, 0, 0);
        checkInOutGP.add(checkOutBooks, 0, 2);
        checkInOutGP.add(checkInButton,0,3);
        
        checkInButton.setOnAction(event -> {
            checkIn();
        });
        
        
        
        //SCENE BUTTON CONTROLS
        
        //Main Menu -> Book Menu button
        bookMenu.setOnAction(event -> {
            changeScene(stage, bookGP);
        });
        
        //Main Menu -> Card Holder Menu button
        cardMenu.setOnAction(event -> {
            changeScene(stage, cardGP);
        });
        
        //Main Menu -> Check In/Out Menu button
        checkInOut.setOnAction(event -> {
            changeScene(stage, checkInOutGP);
        });
        
        //Back to main menu button
        bookBack.setOnAction(event -> {
            changeScene(stage, mainGP);
        });
        cardBack.setOnAction(event -> {
            changeScene(stage, mainGP);
        });
        checkBack.setOnAction(event -> {
            changeScene(stage, mainGP);
        });
        
        stage.setMaximized(true);
        stage.setScene(mainScene);
        stage.show();
    }
    
    //Exit method
    public void exit(Stage stage) {
        stage.close();
    }
    
    //Method for changing scenes
    public void changeScene(Stage st, GridPane gp){
        st.getScene().setRoot(gp);
    }
    
    //Reading from a file into a listview
    public void readFile(String fileName, ListView listview){
        File file = new File(fileName + ".txt");
        try{
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String item = scanner.nextLine();
                if (!item.equals(""))
            listview.getItems().add(item);
        }
        }catch (Exception e) {
            System.out.println("File Error.");
        }
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
                     duplicateBook = true;
                     break;
                }
            }
            
            if (duplicateBook){
                //Alert for duplicates and reset the text boxes
                Alert dupAlert = new Alert(Alert.AlertType.ERROR);
                     dupAlert.setContentText("This tomb already exists in our records...");
                     dupAlert.showAndWait();
                     bookTitle.setText("");
                     bookAuthor.setText("");
            }else {
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
                    //stop the loop if a duplicate is found
                     duplicate = true;
                     break;
                }
            }
            
            if (duplicate) {
                //Alert for duplicates and reset the text boxes
                Alert dupAlert = new Alert(Alert.AlertType.ERROR);
                dupAlert.setContentText("This fellow already exists in our records...");
                dupAlert.showAndWait();
                cardFirstName.setText("");
                cardLastName.setText("");
            }else {
                cardFirstName.setText("");
                cardLastName.setText("");
                cardList.getItems().add(newCard);
                cardHolderCombo.getItems().add(newCard);
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
        if (!itemsToRemove.isEmpty()){
            for (Object item : itemsToRemove){
            String itemToRemove = String.valueOf(item);
            listview.getItems().remove(itemToRemove);
            cardHolderCombo.getItems().remove(itemToRemove);

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.replace(itemToRemove, "");
                        writer.write(line);
                        writer.newLine();
                    }
            }catch (IOException e) {
                System.out.println("File Error");
            }
            
            //Delete old file, rename temp file to correct filename
            new File(filePath).delete();
            new File("temp.txt").renameTo(new File(filePath));
            }
        }else {
            //Show alert if user hasn't selected an item from the ListView
            Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
            noneSelectedAlert.setContentText("Please select at least 1 item from the list...");
            noneSelectedAlert.showAndWait();
        }
    }
    
    //Launch
    public static void main(String[] args) {
        launch();
    }

}