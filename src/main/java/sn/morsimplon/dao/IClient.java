package sn.morsimplon.dao;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.morsimplon.entities.Client;

@Repository
public interface IClient extends JpaRepository<Client, Integer> {

	//public Page<Client> findByMotCle(String motcle, Pageable pageable);
}
