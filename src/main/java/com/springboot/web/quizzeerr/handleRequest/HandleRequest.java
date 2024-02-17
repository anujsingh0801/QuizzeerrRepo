package com.springboot.web.quizzeerr.handleRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.web.quizzeerr.Handler.UpdateHandler;
import com.springboot.web.quizzeerr.entity.Subject;
import com.springboot.web.quizzeerr.repo.subjectRepoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/API")
public class HandleRequest {

	@Autowired
	subjectRepoService subService;

	@GetMapping("/test")
	public String testApi(@RequestParam(value="firstName", required=false) String fn,
			@RequestParam(value="lastName", required=false) String ln) {

		return "Working Fine - added to test the merge " + fn + " " + ln;
	}

	@PostMapping("/addSubject")
	public ResponseEntity createSubject(@RequestBody JsonNode newDetails) {
		try {
			UpdateHandler up = new UpdateHandler(newDetails);
			Subject newSub = up.createNewSubject();
			subService.save(newSub);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(newDetails);
		} catch(Exception ex) {
			System.out.println("error while parsing Json for new subject - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/addQuestion")
	public ResponseEntity addQuestion(@RequestBody JsonNode newQues) {
		try {
			UpdateHandler up = new UpdateHandler(newQues);
			String subName = newQues.get("name").asText();
			Subject sub = subService.findByName(subName);
			if(sub == null) {
				return createSubject(newQues);
			} else {
				int status = up.addQuestion(sub);
				if(status == 1) {
					subService.save(sub);
					return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
				}
				else
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception ex) {
			System.out.println("error while parsing Json for adding new questions - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/getSubject")
	public ResponseEntity getSubject(@RequestParam String subName,
            @RequestParam int count) {
		try {
//			String subName = param.get("subject").asText();
//			int count = param.get("count").asInt();
			Subject sub = subService.findByName(subName);
			UpdateHandler up = new UpdateHandler();
			JsonNode subDetails = up.getSubjectDetails(sub, count);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(subDetails);
		}catch(Exception ex) {
			System.out.println("error while parsing Json for new subject - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/getAllSubject")
	public ResponseEntity gettAllSubject() {

		try {
			List<Subject> subs = subService.findAll();
			UpdateHandler up = new UpdateHandler();
			JsonNode subJson = up.getAllSubjects(subs);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(subJson);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
