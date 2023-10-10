package com.springboot.web.quizzeerr.handleRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.web.quizzeerr.Handler.UpdateHandler;
import com.springboot.web.quizzeerr.entity.Subject;
import com.springboot.web.quizzeerr.repo.subjectRepoService;

@RestController
public class HandleRequest {
	
	@Autowired
	subjectRepoService subService;
	
	@GetMapping("/")
	public String testApi() {
		return "Working Fine";
	}
	
	@PostMapping("/addSubject")
	public ResponseEntity createSubject(@RequestBody JsonNode newDetails) {
		try {
//			ObjectMapper mapper = new ObjectMapper();
//			JsonNode newDetails = mapper.readTree(sub);	
			UpdateHandler up = new UpdateHandler(newDetails);
			Subject newSub = up.createNewSubject();
			subService.save(newSub);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(newDetails);
		} catch(Exception ex) {
			System.out.println("error while parsing Json for new subject - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
		}
	}
	
	@GetMapping("/getSubject")
	public ResponseEntity getSubject(@RequestBody JsonNode param, @RequestHeader("User") String header) {
		try {
			System.out.println(header);
			String subName = param.get("subject").asText();
			int count = param.get("count").asInt();
			Subject sub = subService.findByName(subName);
			UpdateHandler up = new UpdateHandler();
			JsonNode subDetails = up.getSubjectDetails(sub, count);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(subDetails);
		}catch(Exception ex) {
			System.out.println("error while parsing Json for new subject - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
		}
	}
}
