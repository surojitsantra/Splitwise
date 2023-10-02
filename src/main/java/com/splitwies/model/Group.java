package com.splitwies.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
	private Long groupId;
	private String groupName;
	private List<User> users;
	private List<Expense> expenses;
}
