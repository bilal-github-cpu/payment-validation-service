package com.Bilal.service.impl.validators;

import org.springframework.http.HttpStatus;
import java.util.Map;
import com.Bilal.cache.ValidationRulesCache;
import com.Bilal.constant.ErrorCodeEnum;
import com.Bilal.constant.ValidatorEnum;
import com.Bilal.dao.MerchantPaymentRequestDao;
import com.Bilal.dto.PaymentRequestDTO;
import com.Bilal.exception.ValidationException;
import com.Bilal.service.interfaces.Validator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class PaymentAttemptThresholdCheck implements Validator {

	private MerchantPaymentRequestDao merchantPaymentRequestDao;

	private ValidationRulesCache validationRulesCache;

	public PaymentAttemptThresholdCheck(
			MerchantPaymentRequestDao merchantPaymentRequestDao,
			ValidationRulesCache validationRulesCache) {
		this.merchantPaymentRequestDao = merchantPaymentRequestDao;
		this.validationRulesCache = validationRulesCache;
	}

	private static final String DURATION_IN_MINS = "durationInMins";
	private static final String MAX_PAYMENT_THRESHOLD = "maxPaymentThreshold";

	@Override
	public void validate(PaymentRequestDTO paymentRequestDTO) {

		log.info("Validating payment request: {}", paymentRequestDTO);

		Map<String, String> params = validationRulesCache.getValidationRulesParams(
				ValidatorEnum.PAYMENT_ATTEMPT_THRESHOLD_CHECK.name());

		//TODO null or empty check for params. And throw ValidationException

		int durationInMins = Integer.parseInt(params.get(DURATION_IN_MINS));
		int maxPaymentThreshold = Integer.parseInt(params.get(MAX_PAYMENT_THRESHOLD));


		int count = merchantPaymentRequestDao.getUserPaymentAttemptsInLastXMinutes(
				paymentRequestDTO.getUser().getEndUserID(), durationInMins);

		log.info("Payment attempts in last {} minutes: {} | endUserId:{}", durationInMins, count, 
				paymentRequestDTO.getUser().getEndUserID());

		if (count > maxPaymentThreshold) {
			log.error("Payment attempts exceeded threshold in last {} minutes", durationInMins);
			// throw exception

			throw new ValidationException(
					ErrorCodeEnum.PAYMENT_ATTEMPT_THRESHOLD_EXCEEDED.getErrorCode(),
					ErrorCodeEnum.PAYMENT_ATTEMPT_THRESHOLD_EXCEEDED.getErrorMessage(), 
					HttpStatus.BAD_REQUEST);
		}

		log.info("PaymentAttemptThresholdCheck passed for endUserId: {}", 
				paymentRequestDTO.getUser().getEndUserID());
	}

}

