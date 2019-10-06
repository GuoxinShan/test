package com.sept.rest.webservices.restfulwebservices.studygroup;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class StudyGroupJpaResource {

	@Autowired
	private StudyGroupHardcodedService studyGroupService;
	
	@Autowired
	private StudyGroupJpaRepository studyGroupJpaRepository;
	
	//get all groups joined based on username
	@GetMapping("/jpa/users/{username}/groups")
	public List<StudyGroup> getAllGroups(@PathVariable String username){
		return studyGroupJpaRepository.findByUsername(username);
		//return studyGroupService.findAll();
	}

	//get a particular group joined by id
	@GetMapping("/jpa/users/{username}/groups/{id}")
	public StudyGroup getGroup(@PathVariable String username, @PathVariable long id){
		return studyGroupJpaRepository.findById(id).get();
		//return studyGroupService.findById(id);
	}
	
	// delete a group by id
	@DeleteMapping("/jpa/users/{username}/groups/{id}")
	public ResponseEntity<Void> deleteGroup(
			@PathVariable String username, @PathVariable long id) {
		
		studyGroupJpaRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	
	//Edit/Update a group
	@PutMapping("/jpa/users/{username}/groups/{id}")
	public ResponseEntity<StudyGroup> updateGroup(
			@PathVariable String username,
			@PathVariable long id, @RequestBody StudyGroup studygroup){
			
		studygroup.setUsername(username);
			
		StudyGroup studygroupUpdated = studyGroupJpaRepository.save(studygroup);
			
		return new ResponseEntity<StudyGroup>(studygroup, HttpStatus.OK);
	}
		
	//create/join a new group entity
	@PostMapping("/jpa/users/{username}/groups")
	public ResponseEntity<Void> createGroup(
			@PathVariable String username, @RequestBody StudyGroup studygroup){
		
		studygroup.setUsername(username);
		
		StudyGroup createdStudyGroup = studyGroupJpaRepository.save(studygroup);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdStudyGroup.getId()).toUri();
			
		return ResponseEntity.created(uri).build();
	}
	
	//check if user is already in the study group
	@GetMapping("/jpa/users/{username}/groups/{id}/isJoined")
	public boolean isJoined(@PathVariable String username, @PathVariable long id) {
		
		//if(studyGroupJpaRepository.findById(id).get().getId().equals(id)){
		//	return true;
		//}
		
		//return false;
		
		return studyGroupJpaRepository.findById(id).get().getUsersList().contains(username);
	}
	
	//Getting the size of the user array list
	@GetMapping("jpa/users/{username}/studygroups/{id}/getNumberOfUsers")
	public int getNumberOfUsers(@PathVariable String username, @PathVariable long id) {
		return studyGroupJpaRepository.findById(id).get().getUsersList().size();
	}
	
//	// Adding user to array list
//	@PutMapping("/jpa/users/{username}/groups/{id}/addUserToStudyGroup")
//	public void addUserToGroup(@PathVariable String username, @PathVariable long id, String user) {
//		studyGroupJpaRepository.findById(id).get().getUsersList().add(user);
//		studyGroupJpaRepository.findById(id).get().setNumberOfMember();
//	}
//	
//	// Removing user from array list
//	@PutMapping("/jpa/users/{username}/groups/{id}/removeUserFromStudyGroup")
//	public void removeUserFromGroup(@PathVariable String username, @PathVariable long id, String user) {
//		studyGroupJpaRepository.findById(id).get().getUsersList().remove(user);
//	}
	
	//get all groups avalible based on the course id
	@GetMapping("/jpa/users/groups/{courseId}/getActiveGroups")
	public List<StudyGroup> getActiveGroups(@PathVariable String courseId) {
		List<StudyGroup> activeGroup=  new ArrayList<>();
		for(StudyGroup group: studyGroupJpaRepository.findAll())
		{
			if(group.getCourseId().equals(courseId))
			{
				if(activeGroup.size()>0) {
					for(StudyGroup x: activeGroup) {
						if(!group.getGroupName().equals(x.getGroupName())) {
							activeGroup.add(group);
						}
					}
				}
				else {
					activeGroup.add(group);
				}
			}
		}
		return activeGroup;	
	}
	
}
