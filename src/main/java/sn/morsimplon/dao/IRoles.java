package sn.morsimplon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sn.morsimplon.entities.Roles;

@Repository
public interface IRoles extends JpaRepository<Roles, Integer> {

	@Query("SELECT r FROM Roles r WHERE r.id=:id")
	public List<Roles> getRoleById(@Param("id") int id);
	
	@Query("SELECT r FROM Roles r WHERE r.nom=:ADMIN OR r.nom=:COMMERCIAL OR r.nom=:CLIENTEL OR r.nom=:COMPTEUR")
	public List<Roles> getRolesByNames(@Param("ADMIN") String ADMIN, @Param("COMMERCIAL") 
					String COMMERCIAL, @Param("CLIENTEL") String CLIENTEL, @Param("COMPTEUR") String COMPTEUR);
}
