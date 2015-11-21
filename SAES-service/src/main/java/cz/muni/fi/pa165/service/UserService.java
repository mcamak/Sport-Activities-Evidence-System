package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Jan S.
 */
@Service
public interface UserService {
    
    public Long createUser(User user);
    public void removeUser(User user);
    public User findUser(Long id);    
    public List<User> findAll();
    public void updateUser(User user);
    public List<User> getUsersByFilter(UserFilter filter);
}
