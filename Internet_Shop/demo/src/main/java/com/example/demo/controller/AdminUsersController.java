package com.example.demo.controller;

import com.example.demo.entities.Category;
import com.example.demo.entities.Item;
import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "/admin/users")
public class AdminUsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminCategories(Model model){
        Users currentUser = getUserData();
        List<Users> users = userService.getAllUsers();

        Roles role = roleService.getRolesByName("ROLE_ADMIN");
        users.removeIf(user -> user.getRoles().contains(role));

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("users", users);
        return "admin/users_table";
    }


    @GetMapping(value="/admin/users/profile")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String profile(Model model, @RequestParam(name = "email") String email){
        Users currentUser = getUserData();
        Users user = userService.getUserByEmail(email);
        List<Roles> roles = roleService.getAllRoles();
        roles.removeAll(user.getRoles());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "admin/userDetailAdmin";
    }


    @PostMapping(value = "/admin/users/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String createUsers(@RequestParam(name = "email") String email,
                              @RequestParam(name = "password") String password,
                              @RequestParam(name = "retype_password") String re_password,
                              @RequestParam(name = "full_name") String full_name){

        if(password.equals(re_password)){
            Users user = new Users(email, password, full_name);
            if(userService.createUser(user) != null){
                return "redirect:/admin/users?success";

            }
        }
        return "redirect:/admin/users?error";
    }

    @PostMapping(value = "/admin/users/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String updateUsers(@RequestParam(name = "old_email") String old_email,
                                 @RequestParam(name = "email") String email,
                                 @RequestParam(name="full_name") String full_name){
        Users user = userService.getUserByEmail(old_email);
        if(user != null) {
            user.setEmail(email);
            user.setFullname(full_name);

            userService.updateUserProfile(user, old_email);
            return "redirect:/admin/users/profile?email="+user.getEmail();
        }

        return "error";
    }


    @PostMapping(value="/admin/users/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deleteUser(@RequestParam(name = "email") String email){
        Users user = userService.getUserByEmail(email);
        if(user != null) {
            userService.deleteUser(user);
            return "redirect:/admin/users";
        }
        return "error";
    }



    @PostMapping(value="/admin/users/assignRole")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String assignRole(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "role_id") Long role_id){

        Roles role = roleService.getRolesById(role_id);
        System.out.println("I am here");
        if(role != null) {
            System.out.println("role = " + role);
            Users user = userService.getUserByEmail(email);
            if (user != null) {
                System.out.println("user = " + user);

                List<Roles> roles = user.getRoles();

                if(roles == null)
                    roles = new ArrayList<>();

                roles.add(role);
                user.setRoles(roles);
                userService.saveUser(user);
                return "redirect:/admin/users/profile?email=" + user.getEmail();
            }
        }

        return "error";
    }

    @PostMapping(value="/admin/users/revokeRole")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String revokeRole(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "role_id") Long role_id){

        Roles role = roleService.getRole(role_id);
        if(role != null) {
            Users user = userService.getUserByEmail(email);
            if (user != null) {
                List<Roles> roles = user.getRoles();

                if(roles == null)
                    roles = new ArrayList<>();

                roles.remove(role);
                user.setRoles(roles);
                userService.saveUser(user);
                return "redirect:/admin/users/profile?email=" + user.getEmail();
            }
        }

        return "error";
    }


    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }

        return null;
    }
}
