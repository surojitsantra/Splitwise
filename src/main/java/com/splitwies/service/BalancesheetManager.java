package com.splitwies.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwies.model.Balancesheet;
import com.splitwies.model.Split;
import com.splitwies.model.User;

@Service
public class BalancesheetManager {

	@Autowired
	private UserManager userManager;

	public void userBalancesheetManage(Double bill, Long paidByUserId, List<Split> splits) {
		
		User paidByUser = userManager.getUserById(paidByUserId);
		paidByUser.getBalancesheet().setAmount(paidByUser.getBalancesheet().getAmount() +bill);
		
		for (Split split : splits) {
			Long owedUserId = split.getUserId();
			Double owedAmount = split.getAmount();

			if (owedUserId == paidByUserId) {
				paidByUser.getBalancesheet().setAmount(paidByUser.getBalancesheet().getAmount() -owedAmount);
				continue;
			}

			User owedUser = userManager.getUserById(owedUserId);
			Balancesheet owedUserBalanceSheet = owedUser.getBalancesheet();
			owedUserBalanceSheet.setAmount(owedUserBalanceSheet.getAmount() - owedAmount);
			Double currOwe = owedUserBalanceSheet.getPassbook().getOrDefault(paidByUserId, 0D);
			owedUserBalanceSheet.getPassbook().put(paidByUserId, currOwe -owedAmount);
			
			Map<Long, Double> paidUserPassbook = paidByUser.getBalancesheet().getPassbook();
			paidUserPassbook.put(owedUserId, paidUserPassbook.getOrDefault(owedUserId, 0D) +owedAmount);

		}
	}

	public void initializeBalanceSheet(List<Long> userIds) {
		
		for(Long userId :userIds) {
			for(Long friendUserId :userIds) {
				if(userId == friendUserId) continue;
				userManager.getUserById(userId).getBalancesheet().getPassbook().putIfAbsent(friendUserId, 0D);
			}
		}
		
	}

}
