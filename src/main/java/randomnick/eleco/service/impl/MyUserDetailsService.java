package randomnick.eleco.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import randomnick.eleco.mapper.UserMapper;
import randomnick.eleco.model.entity.LoginUser;
import randomnick.eleco.model.entity.User;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        User user=userMapper.selectOne(wrapper);

        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        else {
            return new LoginUser(user);
        }
    }
}
