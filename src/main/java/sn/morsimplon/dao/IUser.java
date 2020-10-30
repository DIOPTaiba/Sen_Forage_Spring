package sn.morsimplon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sn.morsimplon.entities.User;

@Repository
public interface IUser extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.email=:e AND u.password=:p")
	public User getUserByEmailPassword(@Param("e") String email, @Param("p") String password);

} 
