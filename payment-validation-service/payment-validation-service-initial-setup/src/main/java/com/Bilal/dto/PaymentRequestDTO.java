package com.Bilal.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
	
	private UserDTO user;
	private PaymentDTO payment;

}
