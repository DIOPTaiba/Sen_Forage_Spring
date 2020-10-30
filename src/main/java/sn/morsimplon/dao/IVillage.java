package sn.morsimplon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.morsimplon.entities.Village;

@Repository
public interface IVillage extends JpaRepository<Village, String> {
	
	

}
