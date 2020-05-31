package softuni.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.constants.GlobalConstants;
import softuni.model.entity.User;
import softuni.model.service.UserServiceModel;
import softuni.repository.UserRepository;
import softuni.service.RoleService;
import softuni.service.UserService;

import static softuni.constants.GlobalConstants.ADMIN_CONST;
import static softuni.constants.GlobalConstants.USER_CONST;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {


        userServiceModel.setRole(this.roleService.findByName(
                this.userRepository.count() == 0 ? ADMIN_CONST : USER_CONST));
        User user = this.modelMapper.map(userServiceModel, User.class);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }
}
