/*
 Project Name : Command Line Social Media  - Facebook app
 Developer    : Nitin Kotwal
 Description  : This is fondational project. You can create profile, update profile,view profiles,create posts ,see posts,liked or dislike posts,etc
 				operations you can do.
 				
 */

package com.revature.stud.Project1_facebook;

import java.util.*;
import com.revature.stud.Project1_facebook.userDao.UserDAO;

public class App 
{   	    
	//Main Method
    public static void main( String[] args )
    	{
    		//Scanner to take input
    			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
    			
    		//choice which is given to do-while loop for home look
    		String choice = null;
    			
    		//Mail and password are used to get correct mail,password and pass to menu
    		String mail,password;
    			
    		do
    		{
    			//Home UI
    			System.out.println();
    			System.out.println("---------- Welcome To Social Media App-----------");
   				System.out.println("\t\t @Facebook@\t\t");
   				System.out.println();
   	   	        System.out.println("\t 1.Sign Up \t 2.Log in");
   	    		System.out.println();
    	    		
    	    	//Takng choice for login or  signup
    	        int choice1 = scan.nextInt();
    	    		  
    	    	if(choice1==1)
    	        {
    	    	    //Create profile of user by signup method 
    	   	   		if(UserDAO.signUp())
    	   		    {
    	   	   			System.out.println();
    	   	   			System.out.println("     You successfully registered ");     		    		   
    	   		    }
    	        }
    	       	else if(choice1==2)
    	   	    {
    	   	   		//using do while loop for valid email checking
    	   	   		int ct = 1;
    	   	   		do {
       	    			System.out.print("Enter Email : ");
    	   	    			
   	   	    			if(ct==1)
   	   	    			{
   	   	    				scan.nextLine();
    	   	    			ct = 0;
    	   	    		}
    	   	    			
     	   		  	   	mail = scan.nextLine();
     	   		  	   		
    	   	    	}while(UserDAO.LogIn(mail)==false);
    	   		   	   
    	    	   	//If email is correct check it password
    	   	   		if(UserDAO.LogIn(mail))
    	   	   		{   	
    	   
    	   	   			do {
    	   	   				System.out.print("Enter Password : ");
    	   	   				password = scan.nextLine();
    	   	   			}while(UserDAO.getPassword_Login(password)==false);
    	   	    		   
    	   	   			//If password is correct it gives menu
    	   	   			if(UserDAO.getPassword_Login(password))
    	   	   			{
    	   	   				//Create object of menu class after getting correct mail-id and password
    	   	   				System.out.println();
    	   	   				System.out.println("\tCurrent User is : "+mail);    	
    	   	   				
    	   	   				Menu menu = new Menu();
    	   	   				
    	   	   				menu.setEmail(mail);
    	   	   				menu.setPassword(password);
    	   	    				
    	   	   				//getmenu method return list of operations 
    	   	   				menu.getMenu();
    	   	   			}   
    	   	   		}
    	   	   		
    	   	    }    	   		       
    	       	else
    	  		{
    	       	   System.out.println("Wrong Input");
    	  		}
    	   	     
    	    //To go home UI it gives choice if go then enter "yes"
    	    System.out.println();
    		System.out.print("Do you want go Home Page of Facebook (yes/no) : ");
    		choice = scan.next();
    	         
    	    }while(choice.equalsIgnoreCase("yes"));
    			
    			System.out.println();
    			System.out.println();
    			System.out.println("\t*****  Thank you for visiting our Facebook Platform !!!  *****");
    			
    	}
}

