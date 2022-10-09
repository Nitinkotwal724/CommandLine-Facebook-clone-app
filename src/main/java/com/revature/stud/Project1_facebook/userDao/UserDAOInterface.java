package com.revature.stud.Project1_facebook.userDao;

import java.util.List;

import com.revature.stud.Project1_facebook.user.User;

//create interface for 100% abstraction and loose coupling
public interface UserDAOInterface
{
	//Different methods 
	void updateProfile(String email);

	void deleteProfile(String email);

	void viewProfile(String email);

	List<User> viewAllProfile(String email);

	void searchProfileByName(String name);

	void createPost(String email);

	void seeTimeline(String email);

	void seeOthersPosts(String email);

	List<User> viewTimelinePosts(String email);

}
