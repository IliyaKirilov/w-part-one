package softuni.service;

import softuni.model.service.RoleServiceModel;

public interface RoleService {
    RoleServiceModel findByName(String name);
}
