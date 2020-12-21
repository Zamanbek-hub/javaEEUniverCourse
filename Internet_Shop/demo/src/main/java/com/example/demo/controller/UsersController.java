package com.example.demo.controller;

import com.example.demo.entities.Category;
import com.example.demo.entities.Users;
import com.example.demo.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller(value = "/user")
public class UsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.avatar.viewPath}")
    private String viewPath;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @Value("${file.avatar.defaultPicture}")
    private String defaultPicture;

    @PostMapping(value = "/user/update_profile")
    public String update_profile(@RequestParam(name = "email") String email,
                             @RequestParam(name = "full_name") String full_name){


        Users user = getUserData();
        assert user != null;
        String old_email = user.getEmail();
        user.setEmail(email);
        user.setFullname(full_name);
        if(userService.updateUserProfile(user, old_email) != null){
            return "redirect:/profile?success";

        }

        return "redirect:/profile?error";
    }


    @PostMapping(value = "/user/update_password")
    public String update_password(@RequestParam(name = "old_password") String old_password,
                             @RequestParam(name = "new_password") String new_password,
                             @RequestParam(name = "retype_new_password") String retype_new_password){


        Users user = getUserData();
        assert user != null;
        System.out.println("old_password =" + old_password + "\nold_pass_enc =" + user.getPassword());
        if(bCryptPasswordEncoder.matches(old_password, user.getPassword())) {
            System.out.println("STEP 1");
            if(new_password.equals(retype_new_password)) {
                System.out.println("STEP 2");
                user.setPassword(new_password);
                if (userService.updateUserPassword(user) != null) {
                    System.out.println("STEP 3");
                    return "redirect:/profile?success";
                }
            }
        }

        return "redirect:/profile?error";
    }



    @PostMapping(value = "/user/update_picture")
    @PreAuthorize("isAuthenticated()")
    public String uploadAvatar(@RequestParam(name="avatar") MultipartFile file){
//        if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
//            System.out.println("Type right");
        try {
            Users currentUser = getUserData();
            String picname = DigestUtils.sha1Hex("avatar_"+currentUser.getId()+"_!Picture");
            System.out.println("I am here");
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadPath + picname+".jpg");
            Files.write(path, bytes);

            currentUser.setUser_avatar(picname);
            userService.saveUser(currentUser);
            return "redirect:/profile?success";
        } catch (Exception e) {
            e.printStackTrace();
            }
//        }
//        System.out.println("Type wrong");

        return "redirect:/profile?error";
    }


    @GetMapping(value = "/viewphoto/{url}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody byte[] viewProfilePhoto(@PathVariable(name = "url") String url) throws IOException {
        String pictureURL = viewPath+defaultPicture;
        if(url!=null){
            pictureURL = viewPath+url+".jpg";
        }

        InputStream in;

        try{
            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();
        }
        catch (Exception e){
            ClassPathResource resource = new ClassPathResource(viewPath+defaultPicture);
            in = resource.getInputStream();
            e.printStackTrace();
        }

        return IOUtils.toByteArray(in);
    }


    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            return userService.getUserByEmail(secUser.getUsername());
        }

        return null;
    }






}
