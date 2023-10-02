package com.splitwies.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwies.model.Expense;
import com.splitwies.model.Group;
import com.splitwies.model.User;

@Service
public class GroupManager {
	
	private List<Group> groups = new ArrayList<>();
	private static Long GROUP_ID_CTR = 1L;

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private BalancesheetManager balancesheetManager;

//	@Autowired
//	private GroupRepo groupRepo;

	public Group addGroup(String groupName, List<Long> userIds) {

		List<User> users = userManager.getUsers(userIds);

		Group group = new Group();
		group.setGroupId(GROUP_ID_CTR++);
		group.setGroupName(groupName);
		group.setUsers(users);
		group.setExpenses(new ArrayList<>());
		
		groups.add(group);
		
		balancesheetManager.initializeBalanceSheet(userIds);
		
		return group;

//		return groupRepo.save(group);
	}

	public Group getGroupById(Long groupId) {
//		return groupRepo.findById(groupId).orElse(null);
		return groups.stream().filter(group -> group.getGroupId() == groupId).findFirst().orElse(null);
	}

	public Group addExpenseToGroup(Long groupId, Expense newExpense) {
		Group group = getGroupById(groupId);
		group.getExpenses().add(newExpense);
		return group;
//		return groupRepo.save(group);
	}

	public List<Group> gettAllGroups() {
		return groups;
	}

}
