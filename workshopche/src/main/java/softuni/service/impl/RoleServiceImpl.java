package softuni.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.model.entity.Role;
import softuni.model.service.RoleServiceModel;
import softuni.repository.RoleRepository;
import softuni.service.RoleService;

import javax.annotation.PostConstruct;

import static softuni.constants.GlobalConstants.ADMIN_CONST;
import static softuni.constants.GlobalConstants.USER_CONST;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @PostConstruct
    public void init(){
        if (this.roleRepository.count() == 0){
            Role admin = new Role(ADMIN_CONST);
            Role user = new Role(USER_CONST);

            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }

    }


    @Override
    public RoleServiceModel findByName(String name) {
        return this.roleRepository.findByName(name)
                .map(role -> this.modelMapper.map(role,RoleServiceModel.class))
                .orElse(null);
    }
}
