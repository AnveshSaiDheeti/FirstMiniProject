package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Plan;
import in.ashokit.entity.PlanCategory;
import in.ashokit.repo.PlanCategoryRepo;
import in.ashokit.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	public PlanCategoryRepo planCatRepo;

	@Autowired
	public PlanRepo planRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {

		List<PlanCategory> planCategories = planCatRepo.findAll();

		Map<Integer, String> categMap = new HashMap();

		planCategories.forEach(planCateg -> {

			categMap.put(planCateg.getCategoryId(), planCateg.getCategoryName());
		});

		return categMap;
	}

	@Override
	public List<Plan> getPlans() {
		List<Plan> allPlans = planRepo.findAll();

		return allPlans;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepo.save(plan);
		if(saved.getPlanId()!=null) {
			return true;
		}else
			return false;
			

		//return saved.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return status;
	}

	@Override
	public Plan getPlanById(Integer planId) {

		Optional<Plan> findById = planRepo.findById(planId);

		if(findById.isPresent()) {
			return findById.get();
		}
		return null;

	}

	@Override
	public boolean softDelete(Integer planId, String activeSw) {

		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
		Plan plan = findById.get();
		plan.setActiveSW(activeSw);
		planRepo.save(plan);
        return true;
	}
		return false;
		

	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan save = planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean saveCategory(PlanCategory planCategory) {
		PlanCategory save = planCatRepo.save(planCategory);
		return save.getCategoryId()!=null;
	}

}
