package com.trip.service;

/**
* Users Service Interface 
*
* @author  Ashish Kumar
* @version 1.0
* @since   2015-08-19 
*/
import java.util.List;

import com.trip.dto.ResponseStatusTO;
import com.trip.model.Users;

public interface UsersEJBIf {

	/**
	 * Add an Users to Database
	 * @param users
	 * @return boolean
	 * @throws Exception
	 */
	public ResponseStatusTO addUsers(Users users) throws Exception;
	
	/**
	 * Update an Users to Database
	 * @param users
	 * @return boolean
	 * @throws Exception
	 */
	public ResponseStatusTO updateUsers(Users users) throws Exception;
	
	/**
	 * Get an Users by primary key
	 * @param id
	 * @return Users
	 * @throws Exception
	 */
	public Users getUsersById(Integer id) throws Exception;
	
	/**
	 * Filter Users by various Attributes
	 * @param users
	 * @return List<Users>
	 * @throws Exception
	 */
	public List<Users> getUsersByFilter(Users users) throws Exception;
	
	/**
	 * Delete an Users from Database
	 * @param id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deleteUsers(Users users) throws Exception;
	
	public Boolean checkLogin(String username, String password);
	
	public Users getUserByName(String username);
	
	
}
