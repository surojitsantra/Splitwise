package com.splitwies.service.validator;

import java.util.List;

import com.splitwies.model.Split;

public interface ExpenseValidator {
	boolean validate(Double amount, List<Split> splits);

}
