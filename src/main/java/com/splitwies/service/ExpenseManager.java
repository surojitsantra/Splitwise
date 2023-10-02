package com.splitwies.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwies.model.Expense;
import com.splitwies.model.ExpenseStatus;
import com.splitwies.model.Split;
import com.splitwies.model.SplitType;
import com.splitwies.service.validator.ExpenseValidator;
import com.splitwies.service.validator.ExpenseValidatorFactory;

@Service
public class ExpenseManager {
	
	private List<Expense> expenses = new ArrayList<>();
	private static Long EXPENSE_ID_CTR = 1L;
	
	@Autowired
	private BalancesheetManager balancesheetManager;

//	@Autowired
//	private ExpenseRepo expenseRepo;

	public Expense addExpense(String description, Double amount, SplitType splitType, Long paidByUserId,
			List<Split> splits) {

		ExpenseValidator expenseValidtor = ExpenseValidatorFactory.getExpenseValidater(splitType);
		if (!expenseValidtor.validate(amount, splits)) {
			throw new RuntimeException("Splits are not matching with amont");
		}
		
		Expense expense = new Expense();
		
		expense.setExpenseId(EXPENSE_ID_CTR++);
		
		expense.setDescription(description);
		expense.setStatus(ExpenseStatus.ACTIVE);
		expense.setAmount(amount);
		expense.setSplitType(splitType);
		expense.setPaidByUserId(paidByUserId);
		expense.setSplits(splits);
		
		expenses.add(expense);
		
		balancesheetManager.userBalancesheetManage(amount, paidByUserId, splits);
		
		return expense;

//		return expenseRepo.save(expense);
	}
}
