package com.splitwies.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balancesheet {
	private double amount;
	private Map<Long, Double> passbook = new HashMap<>();

}
