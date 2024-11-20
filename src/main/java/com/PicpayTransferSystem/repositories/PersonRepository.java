package com.PicpayTransferSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.PicpayTransferSystem.entities.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}