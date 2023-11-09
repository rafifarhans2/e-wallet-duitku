package com.enigma.duitku.service;

import com.enigma.duitku.model.request.AuthRequest;
import com.enigma.duitku.model.request.BeneficiaryRequest;
import com.enigma.duitku.model.response.BeneficiaryResponse;

public interface BeneficiaryService {

    BeneficiaryResponse addBeneficiary(BeneficiaryRequest request );
}
