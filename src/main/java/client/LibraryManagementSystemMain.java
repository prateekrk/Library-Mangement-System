package client;

import presentation.LibraryManagementPresentation;
import presentation.LibraryManagementPresentationImpl;

import java.util.Scanner;

public class LibraryManagementSystemMain {
    public static void main(String args[]){
        LibraryManagementPresentation libraryManagementPresentation=new LibraryManagementPresentationImpl();
        Scanner sc=new Scanner(System.in);
        while (true){
            libraryManagementPresentation.showMenu();
            System.out.println("Enter choice");
            int choice=sc.nextInt();
            libraryManagementPresentation.perfomMenu(choice);

        }
    }
}
