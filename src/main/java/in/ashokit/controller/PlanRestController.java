package in.ashokit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.constants.AppConstants;
import in.ashokit.entity.Plan;
import in.ashokit.entity.PlanCategory;
import in.ashokit.props.AppProperties;
import in.ashokit.service.PlanService;

@RestController
public class PlanRestController {

	
	@Autowired
	private PlanService planService;
	
	Map<String, String> messages;
	
	public PlanRestController(PlanService planService, AppProperties appProps) {
		this.planService = planService;
		this.messages=appProps.getMessages();
	}
	
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getPlanCategories() {

		Map<Integer, String> planCategories = planService.getPlanCategories();

		return new ResponseEntity<>(planCategories, HttpStatus.OK);
	}

	@PostMapping("/savePlan")
	public ResponseEntity<String> savedPlan(@RequestBody Plan plan) {
		String resMsg = AppConstants.EMPTY_STRING;
		boolean saved = planService.savePlan(plan);
		if (saved) {
			resMsg = messages.get(AppConstants.SAVE_SUCC);
		} else {
			resMsg = messages.get(AppConstants.SAVE_FAIL);
		}
		return new ResponseEntity<>(resMsg, HttpStatus.CREATED);

	}

	@PostMapping("/saveCategory")
	public ResponseEntity<String> savePlanCaetgory(@RequestBody PlanCategory planCategory) {
		String resMsg = "";
		boolean saved = planService.saveCategory(planCategory);
		if (saved) {
			resMsg = messages.get(AppConstants.SAVE_CATSUCC);
		} else {
			resMsg = messages.get(AppConstants.SAVE_CATFAIL);
		}
		return new ResponseEntity<>(resMsg, HttpStatus.CREATED);

	}
	@GetMapping("/Plans")
	public ResponseEntity<List<Plan>> plans() {

		List<Plan> plans = planService.getPlans();

		return new ResponseEntity<>(plans, HttpStatus.OK);
	}

	@GetMapping("/Plan/{planId}")
	public ResponseEntity<Plan> getPlan(@PathVariable Integer planId) {

		Plan plan = planService.getPlanById(planId);

		return new ResponseEntity<>(plan, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		String msg="";
		boolean deleted = planService.deletePlanById(planId);
		
		if(deleted) {
		msg= messages.get(AppConstants.DELETE_SUCC);
		
	    }else msg = messages.get(AppConstants.DELETE_FAIL);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/updatePlan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		String msg="";
		boolean updated = planService.updatePlan(plan);
		if(updated) {
		msg=messages.get(AppConstants.UPDATE_SUCC);
		}else msg = messages.get(AppConstants.UPDATE_FAIL);
	    return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PutMapping("/planStatus/{planId}/{activeSw}")
	public ResponseEntity<String> planStatus(@PathVariable Integer planId, @PathVariable String activeSw) {
        String msg ="";
		boolean planStats = planService.softDelete(planId, activeSw);
		if(planStats) {
			msg= messages.get(AppConstants.STATUS_SUCC);
		    }else msg = messages.get(AppConstants.STATUS_FAIL);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		
	}

}
