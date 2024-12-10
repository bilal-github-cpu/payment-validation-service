package com.Bilal.service.interfaces;

import com.Bilal.dto.PaymentRequestDTO;
import com.Bilal.dto.PaymentResponseDTO;

public interface PaymentService {
	
	public PaymentResponseDTO validateAndProcessPayment(PaymentRequestDTO paymentRequest);

}
