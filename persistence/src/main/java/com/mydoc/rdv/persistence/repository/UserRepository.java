package com.mydoc.rdv.persistence.repository;

import com.mydoc.rdv.persistence.model.Ta_User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Ta_User, Long> {

    List<Ta_User> findAllByLastConnectionIsNull();

}
