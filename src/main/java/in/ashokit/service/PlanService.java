package in.ashokit.service;

import java.util.List;
import java.util.Map;

import in.ashokit.entity.Plan;
import in.ashokit.entity.PlanCategory;

public interface PlanService {

	public Map<Integer, String> getPlanCategories();

	public List<Plan> getPlans();

	public boolean savePlan(Plan plan);

	public boolean deletePlanById(Integer planId);

	public Plan getPlanById(Integer planId);

	public boolean softDelete(Integer planId, String activeSw);

	public boolean updatePlan(Plan plan);
	
	public boolean saveCategory(PlanCategory planCategory);

}
