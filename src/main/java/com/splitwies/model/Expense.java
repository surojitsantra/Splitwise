package com.splitwies.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
	private Long expenseId;
	private String description;
	private ExpenseStatus status;
	private Double amount;
	private SplitType splitType;
	
    private Long paidByUserId;

    private List<Split> splits;
	
}
