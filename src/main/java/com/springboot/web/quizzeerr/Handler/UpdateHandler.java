package com.springboot.web.quizzeerr.Handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springboot.web.quizzeerr.entity.Answer;
import com.springboot.web.quizzeerr.entity.Choice;
import com.springboot.web.quizzeerr.entity.Question;
import com.springboot.web.quizzeerr.entity.Subject;
import com.springboot.web.quizzeerr.repo.subjectRepoService;

public class UpdateHandler {
	private JsonNode SubNode;
	@Autowired
	subjectRepoService subService;

	public UpdateHandler(JsonNode SubNode) {
		super();
		this.SubNode = SubNode;
	}
	
	public UpdateHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("deprecation")
	public JsonNode getSubjectDetails(Subject sub, int count) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode subDetails = mapper.readTree("{}");
		
			String subName = sub.getName();
			int subId = sub.getId();
			ArrayNode question = (ArrayNode) mapper.readTree("[]");
			
			List<Question> questions = sub.getQuestion();
			for(Question ques : questions) {
				JsonNode quesNode = mapper.readTree("{}");
				String quesValue = ques.getQues();
				int quesId = ques.getId();
				Choice choice = ques.getChoice();
				int choiceId = choice.getId();
				
				JsonNode choiceNode = mapper.readTree("{}");
				((ObjectNode) choiceNode).put("id", choiceId);
				((ObjectNode) choiceNode).put("1", choice.getChoice1());
				((ObjectNode) choiceNode).put("2", choice.getChoice2());
				((ObjectNode) choiceNode).put("3", choice.getChoice3());
				((ObjectNode) choiceNode).put("4", choice.getChoice4());
				
				Answer ans = ques.getAnswer();
				int ansId = ans.getId();
				
				((ObjectNode) quesNode).put("quesId", quesId);
				((ObjectNode) quesNode).put("ques", quesValue);
				((ObjectNode) quesNode).put("choices", choiceNode);
				((ObjectNode) quesNode).put("answer", ans.getCorrectAnswer());
				question.add(quesNode);
				count--;
				if(count == 0)
					break;
			}	
			
			((ObjectNode) subDetails).put("Sub Id", subId);
			((ObjectNode) subDetails).put("name", subName);
			((ObjectNode) subDetails).put("question", question);
			return subDetails;
		} catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	public Subject createNewSubject() {
		String subName = SubNode.get("name").asText();
		Subject newSub = new Subject();
		List<Question> question = new ArrayList<>();
		
		if(SubNode.has("question")) {
			for(JsonNode quesNode : SubNode.get("question")) {
				Question ques = new Question();
				Choice choice = new Choice();
				Answer ans = new Answer();
				
				ques.setQues(quesNode.get("ques").asText());
				
				JsonNode choiceNode = quesNode.get("choices");
				choice.setChoice1(choiceNode.get("1").asText());
				choice.setChoice2(choiceNode.get("2").asText());
				choice.setChoice3(choiceNode.get("3").asText());
				choice.setChoice4(choiceNode.get("4").asText());
				choice.setQues(ques);
				
				ans.setCorrectAnswer(quesNode.get("answer").asText());
				ans.setQues(ques);
				ques.setChoice(choice);
				ques.setAnswer(ans);
				question.add(ques);
				
				ques.setSub(newSub);
			}
		}
		
		newSub.setName(subName);
		newSub.setQuestion(question);
		return newSub;
	}

	public int addQuestion(Subject sub) {
		 try {
			 List<Question> questionList = sub.getQuestion();
				if(SubNode.has("question")) {
					for(JsonNode quesNode : SubNode.get("question")) {
						Question ques = new Question();
						Choice choice = new Choice();
						Answer ans = new Answer();
						
						ques.setQues(quesNode.get("ques").asText());
						
						JsonNode choiceNode = quesNode.get("choices");
						choice.setChoice1(choiceNode.get("1").asText());
						choice.setChoice2(choiceNode.get("2").asText());
						choice.setChoice3(choiceNode.get("3").asText());
						choice.setChoice4(choiceNode.get("4").asText());
						choice.setQues(ques);
						
						ans.setCorrectAnswer(quesNode.get("answer").asText());
						ans.setQues(ques);
						ques.setChoice(choice);
						ques.setAnswer(ans);
						questionList.add(ques);
						
						ques.setSub(sub);
				}
			}
				return 1;
		} catch (Exception ex) {
			return 0;
		}
	}

	public JsonNode getAllSubjects(List<Subject>subs) {
		
		try {
			ObjectMapper Obj = new ObjectMapper();
			JsonNode jsonSub = Obj.readTree("{}");
			JsonNode subArray = Obj.readTree("[]");
			for(Subject sub : subs) {
				JsonNode subject = Obj.readTree("{}");
				((ObjectNode) subject).put("name", sub.getName());
				((ArrayNode) subArray).add(subject);
			}
			((ObjectNode) jsonSub).put("subjects", subArray);
			return jsonSub;
		}catch (Exception ex) {
			System.out.println("Error in gettAllSubject : " + ex);
			return null;
		}
		
	}
}
