package com.sept.rest.webservices.restfulwebservices.studygroup;

import java.util.Date;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudyGroup {
	
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String courseName;
	private String courseId;
	private String groupName;
	private int numberOfMember;
	private Date startDate;
	private ArrayList<String> usersList = new ArrayList<String>();
	
	public StudyGroup() {
		
	}
	//constructor
	public StudyGroup(long id, String username, String courseId, String courseName, String groupName, Date startDate) {
		super();
		this.id = id;
		this.username = username;
		this.courseName = courseName;
		this.courseId = courseId;
		this.groupName = groupName;
		this.startDate = startDate;
		this.usersList.add(username);
	}
	
	//getter and setter for id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//getter and setter for username (dunno if necessary tho)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	//getter and setter for course name
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	//getter and setter for course id
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	//getter and setter for group name
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	//getter and setter for start date
	public Date getStudyGroupStartDate() {
		return startDate;
	}

	public void setStudyGroupStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	//getter for group members list
	public ArrayList<String> getUsersList() {
		return usersList;
	}
	
	//getter for number of member
	public int getNumberOfMember() {
		return numberOfMember;
	}
	
//	public void setNumberOfMember() {
//		numberOfMember=usersList.size();
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudyGroup other = (StudyGroup) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
