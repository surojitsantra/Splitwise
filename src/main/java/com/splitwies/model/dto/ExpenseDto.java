package com.splitwies.model.dto;

import java.util.List;

import com.splitwies.model.Split;
import com.splitwies.model.SplitType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
	private String description;
	private Double amount;
	private SplitType splitType;
	private Long paidByUserId;
	private List<Split> splits;
}
