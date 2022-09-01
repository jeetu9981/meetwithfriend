package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entities.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity,Integer>{
	public UserEntity findByEmailAndPassword(@Param("email") String email,@Param("password") String password);
	
	@Modifying
	@Query(value="update user set login_first=true where user_id=:id",nativeQuery=true)
	public int updateById(@Param("id") int id);
	
	@Modifying
	@Query(value="update user set favourit_books =:fb,favourite_places =:fp,favourite_songs =:fs,image =:img where user_id =:id",nativeQuery = true)
	public int updateUserDeatil(@Param("fb") String favouritBooks,@Param("fp") String favouritPlaces,@Param("fs") String favouritSongs,@Param("img") String image,@Param("id") int id);


//	@Query(value="select * from user where name like )
	public List<UserEntity> findByName(@Param("name") String name);
	
	
	@Modifying
	@Query(value="update user set favourit_books =:fb,favourite_places =:fp,favourite_songs =:fs,image =:img,email=:email,name=:name where user_id =:id",nativeQuery = true)
	public int updateUser(@Param("fb") String favouritBooks,@Param("fp") String favouritPlaces,@Param("fs") String favouritSongs,@Param("img") String image,@Param("email") String email,@Param("name") String name,@Param("id") int id);
}
