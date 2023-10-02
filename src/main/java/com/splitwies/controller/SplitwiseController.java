package com.splitwies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitwies.model.Expense;
import com.splitwies.model.Group;
import com.splitwies.model.User;
import com.splitwies.model.dto.ExpenseDto;
import com.splitwies.model.dto.GroupDto;
import com.splitwies.service.ExpenseManager;
import com.splitwies.service.GroupManager;
import com.splitwies.service.UserManager;

@RestController
@RequestMapping("/splitwise")
public class SplitwiseController {

	@Autowired
	private UserManager userManager;
	@Autowired
	private GroupManager groupManager;
	@Autowired
	private ExpenseManager expenseManager;

	@PostMapping("/user")
	public ResponseEntity<?> addNewUser(@RequestBody String userName) {
		User newUser = userManager.addNewUser(userName);
		return ResponseEntity.ok(newUser);
	}

	@PostMapping("/group")
	public ResponseEntity<?> addNewGroup(@RequestBody GroupDto groupDto) {
		Group newGroup = groupManager.addGroup(groupDto.getGroupName(), groupDto.getUserIds());
		return ResponseEntity.ok(newGroup);
	}

	@PostMapping("/group/{groupId}/expense")
	public ResponseEntity<?> addNewExpense(@PathVariable Long groupId, @RequestBody ExpenseDto expenseDto) {
		Expense newExpense = expenseManager.addExpense(expenseDto.getDescription(), expenseDto.getAmount(),
				expenseDto.getSplitType(), expenseDto.getPaidByUserId(), expenseDto.getSplits());

		Group group = groupManager.addExpenseToGroup(groupId, newExpense);

		return ResponseEntity.ok(group);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userManager.gettAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/groups")
	public ResponseEntity<?> getAllGroups() {
		List<Group> groups = groupManager.gettAllGroups();
		return ResponseEntity.ok(groups);
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Hello");
	}

}
