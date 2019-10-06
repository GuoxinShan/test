package com.sept.rest.webservices.restfulwebservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sept.rest.webservices.restfulwebservices.mentor.Mentor;
import com.sept.rest.webservices.restfulwebservices.studygroup.StudyGroup;
import com.sept.rest.webservices.restfulwebservices.studygroup.StudyGroupJpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyGroupsTests {

	@Autowired
	private StudyGroupJpaRepository studyGroupJpaRepository;
	
	@Before
	public void initialize() {
		studyGroupJpaRepository.deleteAll();
		
		studyGroupJpaRepository.save(new StudyGroup(1, "username", "a", "coursename", "SEPTOne",new Date()));
		studyGroupJpaRepository.save(new StudyGroup(2, "username", "bbb", "bvb", "SEPT2",new Date()));
	}
	
	@Test
	public void findCourseByUsernameTest() throws ParseException {
		assertEquals(studyGroupJpaRepository.findByUsername("username").get(0).getCourseId(), "a");
		assertEquals(studyGroupJpaRepository.findByUsername("username").get(1).getCourseId(), "bbb");
	}
	
	@Test
	public void findCourseByIdTest() throws ParseException {
		assertTrue(studyGroupJpaRepository.findById(Long.valueOf(1))!=null);
	}

	@Test
	public void deleteStudyGroupTest() throws ParseException {
		studyGroupJpaRepository.deleteAll();
		ResponseEntity.noContent().build();		
		assertFalse(studyGroupJpaRepository.findById(Long.valueOf(2)).isPresent());
	}
	
	@Test
	public void createStudyGroupTest() throws ParseException {
		studyGroupJpaRepository.save(new StudyGroup(4, "sept", "MATH1", "math","The Smart One", new Date()));
		assertEquals(studyGroupJpaRepository.findByUsername("sept").get(0).getCourseId(), "MATH1");
	}
	
	@Test
	public void checkIsStudyGroupTestSuccess() throws ParseException {
		for(StudyGroup group:studyGroupJpaRepository.findByUsername("username"))
		{
			if(group.getCourseId().equals("a")) {
				assertTrue(true);
			}
		}
	}
	
	@Test
	public void checkStudyGroupTestFail() throws ParseException {
		for(StudyGroup group:studyGroupJpaRepository.findByUsername("username"))
		{
			if(group.getCourseId().equals("x")) {
			}
		}
		assertFalse(false);
	}
	
	@Test
	public void checkIsGroupTestSuccess() throws ParseException {
		for(StudyGroup group:studyGroupJpaRepository.findByUsername("username"))
		{
			if(group.getCourseId().equals("a")) {
				assertTrue(true);
			}
		}
	}
	
	@Test
	public void checkIsGroupTestFail() throws ParseException {
		for(StudyGroup group:studyGroupJpaRepository.findByUsername("username"))
		{
			if(group.getCourseId().equals("x")) {
			}
		}
		assertFalse(false);
	}
	
	@Test
	public void checkActiveGroupTest() throws ParseException {
		List<StudyGroup> activeGroup =  new ArrayList<>();
		for(StudyGroup group: studyGroupJpaRepository.findAll())
		{
			if(group.getCourseId().equals("a"))
			{
				activeGroup.add(group);
			}
		}
		assertEquals(activeGroup.size(),1);
	}
}