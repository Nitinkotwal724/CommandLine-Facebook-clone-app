package com.revature.stud.Project1_facebook.userDao;

import java.sql.*;
import java.util.*;

import com.revature.stud.Project1_facebook.App;
import com.revature.stud.Project1_facebook.user.User;
import com.revature.stud.Project1_facebook.util.ConnectionUtil;

public class UserDAO implements UserDAOInterface
{
	private static Connection con = null;
	
	static 
	{
		try 
		{
			con = ConnectionUtil.getConnection();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Method = For Sign Up 
		public static boolean signUp() 
		{
			try 
			{		
				//Scanner to take input
				Scanner scan = new Scanner(System.in);
			
				System.out.println();
				
				System.out.print("Enter name: ");
				String name= scan.nextLine();
			
				System.out.print("Enter Address: ");
				String Address =null;
				Address = scan.nextLine();
			
				System.out.print("Enter Gender: ");
				String gender = scan.next();
			
				System.out.print("Enter Age: ");
				int age = scan.nextInt();
			
				System.out.print("Enter Email: ");
				scan.nextLine();
				String Email= scan.nextLine();
			
				System.out.print("Enter Password: ");
				String password = scan.nextLine();
					
				//Create object of user_entity class
				User user = new User();
			
				//set value for user object 
				user.setUser_name(name);
				user.setUser_age(age);
				user.setUser_email(Email);
				user.setUser_address(Address);
				user.setUser_gender(gender);
				user.setUser_password(password);
		
				//Run query for insert data in database
				PreparedStatement stmt = con.prepareStatement("insert into userData values(?,?,?,?,?,?)");

			
				stmt.setString(1, user.getUser_name());
				stmt.setInt(2,user.getUser_age());
				stmt.setString(3,user.getUser_email());
				stmt.setString(4,Address);
				stmt.setString(5, user.getUser_gender());
				stmt.setString(6, user.getUser_password());
				
				//Execute the query and check whether it inserted or not
				if(stmt.executeUpdate()==1)
				{
					return true;
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			return false;
		}
		
		
		//Method for check mail in database for login
		public static boolean LogIn(String mail)
		{
			try 
			{			
				//preapare Query 
				PreparedStatement stmt = con.prepareStatement("select user_email from userData where user_email = ?");
				stmt.setString(1, mail);

				ResultSet rs = stmt.executeQuery();
				
				String temp = null;
				
				if(rs.next()==false)
				{
					System.out.println("Wrong Username try again");
					System.out.println();
					return false;
				}
				
				else
				{
					temp = rs.getString(1);
					
					//Check entered mail and present mail in database are equal
					if(temp.equals(mail))
					{				
						return true;
					}
				}	
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			return false;
			
		}

		//Method for check password in database for login
		public static boolean getPassword_Login(String password2) 
		{	
			try
			{
				//Preapare Query
				PreparedStatement stmt2 = con.prepareStatement("select user_password from userData where user_password = ?");
				stmt2.setString(1, password2);
			
				ResultSet rs = stmt2.executeQuery();
			
				if(rs.next()==false)
				{
					System.out.println("Wrong Password try again");
					System.out.println();
					return false;
				}
			
				else
				{
					String temp = rs.getString(1);
				
					//check entered password and present password in database is same
					if(temp.equals(password2))
					{				
						return true;
					}
				
				}	
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return false;
		}

		//Method for Update Profile
		public void updateProfile(String email) 
		{
			try 
			{
				Scanner scan = new Scanner(System.in);
				
				System.out.println();
			
				System.out.println("Enter name: ");
				String name= scan.nextLine();
			
				System.out.println("Enter Address: ");
				String Address =null;
				Address = scan.nextLine();
			
				System.out.println("Enter Age: ");
				int age = scan.nextInt();
				
				//Taking query
				PreparedStatement ps = con.prepareStatement("update userData set user_name =?,user_Address = ?,user_age = ? where user_email = ?");
				
				ps.setString(1, name);
				ps.setString(2, Address);
				ps.setInt(3, age);
				ps.setString(4, email);
				
				//Execute query
				if(ps.executeUpdate()==1)
				{
					System.out.println();
					System.out.println("     Updated Successfully     ");
				} 

			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}

		//Method for delete Profile
		public void deleteProfile(String email)
		{
			try 
			{	
				System.out.println();
				
				Scanner scan = new Scanner(System.in);
				
				System.out.print("Do you really want to delete your profile(yes/no) : ");
				String ch= scan.nextLine();
				
				if(ch.equalsIgnoreCase("yes"))
				{
					PreparedStatement ps = con.prepareStatement("delete from userData where user_email = ?");	
					ps.setString(1, email);
				
					//Execute query
					if(ps.executeUpdate()==1)
					{
						System.out.println();
						System.out.println("     Successfully Deleted");
						App.main(null);
					}
				}
			}
			catch (Exception e)
			{
			System.out.println(e.getMessage());
			}
			
		}

		//Method for view profile
		public void viewProfile(String email) 
		{
			try
			{			
			PreparedStatement ps = con.prepareStatement("select user_name,user_Address,user_email,user_age,user_gender from userData where user_email = ? ");
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			//Print all results
			while(rs.next())
			{
				System.out.println();
				
				String name = rs.getString(1);
				System.out.println("User Name : "+name);
				String address = rs.getString(2);
				System.out.println("User Address : "+address);
				String mail = rs.getString(3);
				System.out.println("User Email : "+ email);
				int age = rs.getInt(4);
				System.out.println("User Age : "+age);
				String gender = rs.getString(5);
				System.out.println("User Gender : "+gender);
				System.out.println();
				System.out.println("--------------------");
				System.out.println();
			}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		//Method for see all profiles
		public void viewAllProfile1(String email)
		{
			try 
			{
				PreparedStatement ps = con.prepareStatement("select user_name,user_Address,user_email,user_age,user_gender from userData where user_email != ?  order by user_id");
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				
				//print all results
				while(rs.next())
				{
					
					String name = rs.getString(1);
					System.out.println("User Name : "+name);
					String address = rs.getString(2);
					System.out.println("User Address : "+address);
					String mail = rs.getString(3);
					System.out.println("User Email : "+ mail);
					int age = rs.getInt(4);
					System.out.println("User Age : "+age);
					String gender = rs.getString(5);
					System.out.println("User Gender : "+gender);
					System.out.println();
					System.out.println();
				}

			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}

		//Method for see all profile by taking list
		public List<User> viewAllProfile(String email)
		{
			//create list of object
			List<User> list1 = new ArrayList<User>(); 
			try 
			{	
				PreparedStatement ps = con.prepareStatement("select user_name,user_Address,user_email,user_age,user_gender from userData where user_email != '"+email+"'  order by user_name");
//				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					//create object of user class
					User u1 = new User();
					
					String name = rs.getString(1);
					String address = rs.getString(2);
					String mail = rs.getString(3);
					int age = rs.getInt(4);
					String gender = rs.getString(5);

					u1.setUser_name(name);
					u1.setUser_address(address);
					u1.setUser_email(mail);
					u1.setUser_age(age);
					u1.setUser_gender(gender);
					
					//Adding elements to list
					list1.add(u1);
				}

			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
			return list1;
		}

		//Method for search profile by name or character
		public void searchProfileByName(String name)
		{
			try
			{
			PreparedStatement ps = con.prepareStatement("select user_name,user_Address,user_email,user_age,user_gender from userData where user_name like ? ");
			ps.setString(1, name+"%");
			
			ResultSet rs = ps.executeQuery();
			
			//Print all results
			while(rs.next())
			{
				System.out.println();
				
				String name1 = rs.getString(1);
				System.out.println("\tUser Name : "+name1);
				String address = rs.getString(2);
				System.out.println("\tUser Address : "+address);
				String mail = rs.getString(3);
				System.out.println("\tUser Email : "+ mail);
				int age = rs.getInt(4);
				System.out.println("\tUser Age : "+age);
				String gender = rs.getString(5);
				System.out.println("\tUser Gender : "+gender);
				System.out.println();
				System.out.println("\t--------------------");
				System.out.println();
			}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}		
		}

		//Method for create post
		public void createPost(String email)
		{
			try 
			{	
				Scanner scan = new Scanner(System.in);
				
				System.out.print("Write Post : ");
				String post = scan.nextLine();
				
				PreparedStatement ps = con.prepareStatement("insert into postData(email,post,Date1,time) values(?,?,current_date(),current_time())");
				ps.setString(1, email);
				ps.setString(2, post);
				
				if(ps.executeUpdate()==1)
				{
					System.out.println();
					System.out.println("     Successfully Created Post");
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		//Method for see posts of self posts
		public void seeTimeline(String email) 
		{
			try 
			{
				PreparedStatement ps = con.prepareStatement("select post,Date1,time from postData where email = ?");
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()==false)
				{
					System.out.println("     Not posted yet");
				}
				else
				{
					//print results
					while(rs.next())
					{
						System.out.println();
						
						String post1 = rs.getString(1);
						System.out.println("Post : "+post1);
						String Date1 = rs.getString(2);
						System.out.println("Date : "+ Date1);
						String time = rs.getString(3);
						System.out.println("Time  : "+time);
						System.out.println();
						System.out.println("--------------------");
						System.out.println();
					}
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		
		public List<User> viewTimelinePosts(String email)
		{
			List<User> list1 = new ArrayList<User>(); 
			try 
			{	
				PreparedStatement ps = con.prepareStatement("select post_id,post,Date1,time,likes,dislike from postData where email = ?  order by date1,time");
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				
					while(rs.next())
					{
						User u1 = new User();
						
						System.out.println();
						
						int post_id = rs.getInt(1);
						String post1 = rs.getString(2);
						String Date1 = rs.getString(3);
						String time = rs.getString(4);
						int like = rs.getInt(5);
						int dislike = rs.getInt(6);
						
						u1.setPost_id(post_id);
						u1.setPost(post1);
						u1.setDate1(Date1);
						u1.setTime(time);
						u1.setLikes(like);
						u1.setDislike(dislike);
						
						list1.add(u1);
					}
				 
			}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
			return list1;
				
		}
		
		//Method for see others posts
		public void seeOthersPosts(String email) 
		{
			try
			{
				PreparedStatement ps = con.prepareStatement("select u.user_name,p.email,p.post_id,p.post,p.Date1,p.time,p.likes,p.dislike from userData as u inner join postData as p on u.user_email = p.email where u.user_email != ? order by p.Date1,p.time ");
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()==false)
				{
					System.out.println("No One posted posts");
				}
				
				else
				{
					//Print all result
					do
					{
						System.out.println();
						String name = rs.getString(1);
						System.out.println("\tUser Name : "+name);
						String mail = rs.getString(2);
						System.out.println("\tEmail Id : "+mail);
						int postid = rs.getInt(3);
						System.out.println("\tPost Id : "+postid);
						String post1 = rs.getString(4);
						System.out.println("\tPost : "+post1);
						String Date = rs.getString(5);
						System.out.print("\tDate : "+Date);
						String time = rs.getString(6);
						System.out.println("\t Time : "+time);
						int like = rs.getInt(7);
						System.out.print("\t\tLike : "+like);
						int dislike = rs.getInt(8);		
						System.out.print("\tDislike : "+dislike);
						System.out.println();
						System.out.print("\t--------------------");
						System.out.println("-------------------------");
						System.out.println();
					}while(rs.next());
					
					String ch = null;
					
					Scanner scan = new Scanner(System.in);
					
					System.out.print("Do you want to like or dislike to post (yes/no) : ");
					ch = scan.nextLine();	
					if(ch.equalsIgnoreCase("yes"))
					{
						System.out.print("Enter Post Id: ");
						int postid = scan.nextInt();
						System.out.println();
						System.out.println("\t1.Like \t 2.Dislike");
						int option = scan.nextInt();
						switch (option) 
						{
							case 1:
								UserDAO.addLike(postid);
								break;
							
							case 2:
								UserDAO.addDislike(postid);
								break;
							
							default:
								System.out.println("Wrong input");
								break;
						}
					}
				}			
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}


		private static void addDislike(int postid) throws SQLException 
		{
			PreparedStatement ps = con.prepareStatement("update postData set dislike = dislike + 1 where post_id = ?");
			ps.setInt(1, postid);
			
			if(ps.executeUpdate()==1)
			{
				System.out.println("\tSuccessfully Disliked to post");
			}
		}


		private static void addLike(int postid) throws SQLException 
		{	
			PreparedStatement ps = con.prepareStatement("update postData set likes = likes + 1 where post_id = ?");
			ps.setInt(1, postid);
			
			if(ps.executeUpdate()==1)
			{
				System.out.println("\tSuccessfully Liked to post");
			}
			
		}

		
}
