package com.Bilal.controller;
import org.modelmapper.ModelMapper;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bilal.constant.Endpoints;
import com.Bilal.dto.PaymentRequestDTO;
import com.Bilal.dto.PaymentResponseDTO;
import com.Bilal.pojo.PaymentRequest;
import com.Bilal.pojo.PaymentResponse;
//import com.Bilal.service.impl.PaymentServiceImpl;
import com.Bilal.service.interfaces.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Endpoints.V1_PAYMENTS)
@Slf4j
@Service
public class PaymentController {

	// create logger object with slf4j with logback for this class
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
 
	private PaymentService paymentService;

	private ModelMapper modelMapper;
//	private PaymentServiceImpl paymentServiceImpl;

	public PaymentController(PaymentService paymentService,
			ModelMapper modelMapper) {
		this.paymentService = paymentService;
		this.modelMapper = modelMapper;
	}

	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {

		System.out.println("Payment request received: " + paymentRequest);

		log.trace("Payment request received: {}", paymentRequest);
		log.debug("Payment request received: {}", paymentRequest);

		log.info("Payment request received: {}", paymentRequest);
		log.warn("Payment request received: {}", paymentRequest);
		log.error("Payment request received: {}", paymentRequest);
      
		PaymentRequestDTO paymentRequestDTO = modelMapper.map(paymentRequest, PaymentRequestDTO.class);

		PaymentResponseDTO response = paymentService.validateAndProcessPayment(
				paymentRequestDTO);

		PaymentResponse paymentRes = modelMapper.map(response, PaymentResponse.class);

		log.info("Returning from controller paymentRes: {}", paymentRes);

		// use http status code 201 create & prepare Response entity wrapping up paymentRes object
		//return ResponseEntity.ok(paymentRes);

		return new ResponseEntity<>(
				paymentRes, HttpStatus.CREATED);
	}
}

