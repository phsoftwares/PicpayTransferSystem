package com.PicpayTransferSystem.interfaces;

import com.PicpayTransferSystem.dtos.PersonDTO;
import com.PicpayTransferSystem.entities.PersonEntity;

public interface IPersonService {
    Long createNewPerson(PersonDTO personDTO);
    PersonEntity getById(Long personId);
}