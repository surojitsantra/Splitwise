package com.splitwies.service.validator;

import java.util.List;

import com.splitwies.model.Split;

public class EqualValidator implements ExpenseValidator {

	@Override
	public boolean validate(Double amount, List<Split> splits) {

		int users = splits.size();
		Double distribution = amount / users;
		Double epsilon = 1e-10;

		for (Split spilt : splits) {
			if (Math.abs(spilt.getAmount() - distribution) >= epsilon)
				return false;
		}

		return true;
	}

}
