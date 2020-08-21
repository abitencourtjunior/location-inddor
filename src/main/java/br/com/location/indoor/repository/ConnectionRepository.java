package br.com.location.indoor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.location.indoor.model.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

}
