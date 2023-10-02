package com.splitwies.service.validator;

import com.splitwies.model.SplitType;

public abstract class ExpenseValidatorFactory {

	public static ExpenseValidator getExpenseValidater(SplitType splitType) {
		
		if(splitType == SplitType.EQUAL) return new EqualValidator();
		if(splitType == SplitType.UNEQUAL) return new UnequalValidator();
		if(splitType == SplitType.PERCENTAGE) return new PercentageValidator();
		
		return null;
	}
	
}
