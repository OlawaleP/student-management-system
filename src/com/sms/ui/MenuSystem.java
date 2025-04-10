package com.sms.ui;

public package com.sms.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * A generic menu system that can be used to create and display menus
 */
public class MenuSystem {
    private Map<Integer, MenuItem> menuItems;
    private String menuTitle;
    private Scanner scanner;
    
    public MenuSystem(String menuTitle) {
        this.menuTitle = menuTitle;
        this.menuItems = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }
    
    public void addMenuItem(int key, String description, Runnable action) {
        menuItems.put(key, new MenuItem(description, action));
    }
    
    public void display() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n===== " + menuTitle + " =====");
            
            for (Map.Entry<Integer, MenuItem> entry : menuItems.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
            }
            
            System.out.print("\nEnter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                MenuItem selectedItem = menuItems.get(choice);
                
                if (selectedItem != null) {
                    selectedItem.execute();
                    if (choice == menuItems.size()) {  // Assuming the last option is for exit
                        exit = true;
                    }
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private class MenuItem {
        private String description;
        private Runnable action;
        
        public MenuItem(String description, Runnable action) {
            this.description = description;
            this.action = action;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void execute() {
            action.run();
        }
    }
} {
  
}
