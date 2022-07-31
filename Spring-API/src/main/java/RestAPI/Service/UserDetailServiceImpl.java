package RestAPI.Service;

import RestAPI.DAO.UserRepository;
import RestAPI.DO.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserModel user= userRepository.findByUsername(username);
       if (user ==null){
           throw new UsernameNotFoundException(username);
       }
       UserDetails admin = User.withUsername(user.getEmail()).password(user.getPassword()).authorities("ADMIN").build();
        return admin;
    }
}