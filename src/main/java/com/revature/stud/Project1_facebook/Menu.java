package com.revature.stud.Project1_facebook;

import java.util.List;
import java.util.Scanner;

import com.revature.stud.Project1_facebook.user.User;
import com.revature.stud.Project1_facebook.userDao.UserDAO;
import com.revature.stud.Project1_facebook.userDao.UserDAOInterface;


public class Menu 
{
	
	Scanner scan = new Scanner(System.in);
	
	//For printing user details
	private void printDetails(User u)
	{
		System.out.println("\tUser Name : "+u.getUser_name());
		System.out.println("\tUser Address : "+u.getUser_address());
		System.out.println("\tUser Email : "+ u.getUser_email());
		System.out.println("\tUser Age : "+u.getUser_age());
		System.out.println("\tUser Gender : "+u.getUser_gender());
		System.out.println();
		System.out.print("\t--------------------");
		System.out.println("-------------------------");
		System.out.println();
	}
	
	private void printposts(User u1)
	{
		
		
		System.out.println();
		
		System.out.println("Post Id : "+u1.getPost_id());
		System.out.println("Post : "+u1.getPost());
		System.out.print("Date : "+ u1.getDate1());
		System.out.println("\tTime  : "+u1.getTime());
		System.out.print("Like : "+u1.getLikes());
		System.out.println("\tDislike : "+u1.getDislike());
		System.out.println();
		System.out.println("--------------------");
		System.out.println();
	}
	
	//Create private variables to achieve encapsulation
	private String email,password;

	//create getter setter method for private variables
	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
	//Create getMenu method for display list of operations
	public void getMenu()
	{
		String choice =null;
		boolean logout = false;
		
		do 
		{
			Scanner sc = new Scanner(System.in);
			
			System.out.println();
			//List of operations
			System.out.println("\t-------Menu-------- "
				+ "\n \t 1. Update Profile"
    			+ "\n \t 2. Delete Profile"
    			+ "\n \t 3. View Profile"
    			+ "\n \t 4. View All Profile"
    			+ "\n \t 5. Search Profile"
    			+ "\n \t 6. Create a Post"
    			+ "\n \t 7. Show Timeline"
    			+ "\n \t 8. See Posts created by other users"
    			+ "\n \t 9. Log out");
    	
			System.out.println();
			//Taking option from user for do operations
			System.out.print("Enter your option : ");
			int option = sc.nextInt();
			System.out.println();
    	
			//to access the methods inside UserDAO class
			//I'm creating an object for that
			UserDAOInterface dao = new UserDAO();
		
			switch(option)
			{
			case 1:
				//Using method for Update Profile
				dao.updateProfile(getEmail());
				break;
    	
			case 2:
				//Using method for delete Profile
				dao.deleteProfile(getEmail());
				break;
		
			case 3:
				//Using method for view profile Profile
				dao.viewProfile(getEmail());
				break;
    	
			case 4:
				//Use method to see all profiles
//				dao.viewAllProfile(getEmail());
				
				List<User> uList= dao.viewAllProfile(getEmail());
        		
				System.out.println("---Listing all the Students---");
				System.out.println();

        		for(User user : uList)
        		{
        			printDetails(user);
        		}
				break;
    		
			case 5:
				//Method for search by name or character
				System.out.print("Enter name of person that you want to search : ");
				String name = scan.next();
				dao.searchProfileByName(name);
				break;
 
			case 6:
				//For create post
				dao.createPost(getEmail());
				break;
				
			case 7:
				//See self posts
//				dao.seeTimeline(getEmail());
				List<User> pList= dao.viewTimelinePosts(getEmail());
        		
				if(pList.isEmpty())
				{
					System.out.println("\t No Post is present");
				}
				else
				{
					System.out.println("---Listing all the Posts---");
					System.out.println();
        		
					for(User user : pList)
					{
						printposts(user);
					}
				}

				break;
				
			case 8:
				//see post of others users
				dao.seeOthersPosts(getEmail());
				break;
				
			case 9:
				//Logtout method
				logout = true;
				System.out.println("Logout Successfull");
				break;
			
    	
			default : 
				System.out.println("Enter correct option");
				Menu menu = new Menu();
				menu.setEmail(email);
				menu.setPassword(password);
				menu.getMenu();
				break;
			}
				if(logout==true)
				{
					break;
				}
			
			System.out.println();
			System.out.print("Do you want to continue to get Menu List(yes/no) : ");
			choice = sc.next();
			System.out.println();
   
		}while(choice.equalsIgnoreCase("yes"));

	}
}
