/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javapoe;

/**
 *
 * @author avuyi
 */
import java.util.ArrayList;
import java.util.Scanner;
public class JavaPOE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);

// === REGISTRATION PHASE ===
System.out.println("=== QuickChat Registration ===");
System.out.print("Enter First Name: ");
String firstName = sc.nextLine();
System.out.print("Enter Last Name: ");
String lastName = sc.nextLine();

// Create a Login object with the user's name
Login login = new Login(firstName, lastName);

// Collect registration details
System.out.print("Enter Username: ");
String username = sc.nextLine();
System.out.print("Enter Password: ");
String password = sc.nextLine();
System.out.print("Enter Cell Number (+27...): ");
String cellNumber = sc.nextLine();

// Validate and register the user
String regStatus = login.registerUser(username, password, cellNumber);
System.out.println(regStatus);

// If registration fails, exit the program
if (!regStatus.equals("User registered successfully.")) {
System.out.println("Registration failed. Exiting...");
return;
}

// === LOGIN PHASE ===
System.out.println("\n=== QuickChat Login ===");
System.out.print("Enter Username: ");
String loginUser = sc.nextLine();
System.out.print("Enter Password: ");
String loginPass = sc.nextLine();

// Check login credentials
boolean success = login.loginUser(loginUser, loginPass);
System.out.println(login.returnLoginStatus(success));

// If login fails, exit the program
if (!success) {
System.out.println("Exiting...");
return;
}

// === MENU PHASE ===
ArrayList<Message> messages = new ArrayList<>();
System.out.println("\nWelcome to QuickChat!");

int choice;
do {
// Display menu options
System.out.println("\nMenu:");
System.out.println("1) Send Message");
System.out.println("2) Show Recently Sent Messages");
System.out.println("3) Quit");
System.out.print("Choose an option: ");
choice = sc.nextInt();
sc.nextLine(); // consume newline

switch (choice) {
case 1:
// Collect message details
System.out.print("Enter recipient number: ");
String recipient = sc.nextLine();
System.out.print("Enter message text: ");
String msgText = sc.nextLine();

// Validate message length
if (msgText.length() > 250) {
System.out.println("Message exceeds 250 characters by " +
(msgText.length() - 250) + " please reduce the size.");
break;
}

// Create a new message object
Message msg = new Message(recipient, msgText);

// Validate recipient number and display message details
System.out.println(msg.checkRecipientCell());
System.out.println(msg.printMessage());

// Ask user what to do with the message
System.out.println("Choose action: 1) Send 2) Disregard 3) Store");
int action = sc.nextInt();
sc.nextLine();

if (action == 1) {
// Send message
System.out.println(msg.sendMessage(true));
messages.add(msg);
} else if (action == 2) {
// Discard message
System.out.println("Press 0 to delete the message");
} else if (action == 3) {
// Store message (JSON storage to be implemented)
System.out.println("Message successfully stored");
}
break;

case 2:
// Show all sent messages
if (messages.isEmpty()) {
System.out.println("Coming Soon.");
} else {
for (Message m : messages) {
System.out.println(m.printMessage());
}
System.out.println("Total messages sent: " + Message.returnTotalMessages());
}
break;

case 3:
// Exit program
System.out.println("Goodbye!");
break;

default:
System.out.println("Invalid option.");
}
} while (choice != 3);

sc.close();
       
    }
    
}
