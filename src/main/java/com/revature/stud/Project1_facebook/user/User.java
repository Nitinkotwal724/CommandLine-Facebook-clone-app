package com.revature.stud.Project1_facebook.user;

public class User
{
	private int user_age,likes,dislike,post_id;
	private String user_name , user_email,user_address,user_gender,user_password,email,post,date1,time;
	
	
	public int getUser_age() 
	{
		return user_age;
	}
	
	public void setUser_age(int user_age) 
	{
		this.user_age = user_age;
	}
	
	public String getUser_name() 
	{
		return user_name;
	}
	
	public void setUser_name(String user_name) 
	{
		this.user_name = user_name;
	}
	
	public String getUser_email() 
	{
		return user_email;
	}
	
	public void setUser_email(String user_email) 
	{
		this.user_email = user_email;
	}
	
	public String getUser_address() 
	{
		return user_address;
	}
	
	public void setUser_address(String user_address) 
	{
		this.user_address = user_address;
	}
	
	public String getUser_gender() 
	{
		return user_gender;
	}
	
	public void setUser_gender(String user_gender) 
	{
		this.user_gender = user_gender;
	}
	
	public String getUser_password() 
	{
		return user_password;
	}
	
	public void setUser_password(String user_password) 
	{
		this.user_password = user_password;
	}

	public int getLikes()
	{
		return likes;
	}

	public void setLikes(int likes) 
	{
		this.likes = likes;
	}

	public int getDislike() 
	{
		return dislike;
	}

	public void setDislike(int dislike)
	{
		this.dislike = dislike;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPost()
	{
		return post;
	}

	public void setPost(String post1) 
	{
		this.post = post1;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
