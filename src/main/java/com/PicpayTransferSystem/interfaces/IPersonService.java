package com.PicpayTransferSystem.interfaces;

import com.PicpayTransferSystem.dtos.PersonDTO;

public interface IPersonService {
    Long createNewPerson(PersonDTO personDTO);
    
}