package com.api;

import com.domain.Role;
import com.services.RoleService;
import com.shared.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/role")
public class RoleController {
    private final DataUtil dataUtil = new DataUtil();

    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ResponseEntity<Role> createRole(@RequestParam(value = "name") String name) {
        Role savedRole = roleService.save(new Role(name, dataUtil.currentDate()));
        return new ResponseEntity<Role>(savedRole, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") Long id){
        return new ResponseEntity<Role>(roleService.getRoleById(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> allRoles(){
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }
}
