package com.generation.mondolibri.service.implementation;

import com.generation.mondolibri.exceptions.checked.RoleNotFoundException;
import com.generation.mondolibri.model.entity.Role;

import com.generation.mondolibri.repository.jpa.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractService<Role,Integer> {
    public RoleService(RoleRepository repository) {
        super(repository);
    }

    public Role getUSer() throws RoleNotFoundException {
        return retrieveById(2);
    }

    public Role getAdmin() throws RoleNotFoundException {
        return retrieveById(1);
    }

    protected Role retrieveById(int i) throws RoleNotFoundException {
        return repository.
                findById(i).
                orElseThrow(()-> new RoleNotFoundException("role not available"));
    }
}