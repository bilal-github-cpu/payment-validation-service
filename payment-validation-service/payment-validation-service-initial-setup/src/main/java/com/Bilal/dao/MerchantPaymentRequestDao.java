package com.Bilal.dao;

import com.Bilal.constant.MerchantReqUpdate;

public interface MerchantPaymentRequestDao {

	public MerchantReqUpdate insertMerchantPaymentRequest(String endUserID, 
            String merchantTransactionReference, 
            String transactionRequest);
	
	public int getUserPaymentAttemptsInLastXMinutes(String endUserId, int durationInMins);
	
}
