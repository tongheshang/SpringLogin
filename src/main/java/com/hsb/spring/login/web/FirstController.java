package com.hsb.spring.login.web;import com.hsb.spring.login.dao.IUserDao;import com.hsb.spring.login.entity.UserEntity;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import java.util.List;/* * Copyright &copy;2011-2016 hsb */@Controllerpublic class FirstController {    @Autowired    private transient IUserDao userDao;    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)    public String showUser(@PathVariable("id")Integer userId, ModelMap modelMap) {        UserEntity userEntity = userDao.findOne(userId);        modelMap.addAttribute("user", userEntity);        return "admin/userDetail";    }    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)    public String forwardUpdateUser(@PathVariable("id")Integer userId, ModelMap modelMap) {        UserEntity userEntity = userDao.findOne(userId);        modelMap.addAttribute("user", userEntity);        return "admin/userUpdate";    }    @RequestMapping(value = "/admin/users/delete", method = RequestMethod.POST)    public String deleteUser(@ModelAttribute("id")Integer userId) {        userDao.delete(userId);        userDao.flush();        return "redirect:/admin/users";    }    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)    public String getUsers(ModelMap modelMap) {        List<UserEntity> userEntities = userDao.findAll();        modelMap.addAttribute("userList", userEntities);        return "/admin/users";    }    @RequestMapping(value = "/admin/users/addUser", method = RequestMethod.POST)    public String addUser(@ModelAttribute("user")UserEntity userEntity){        System.out.println(userEntity.getNickname());        userDao.saveAndFlush(userEntity);        return "redirect:/admin/users";    }    @RequestMapping(value = "/admin/users/update", method = RequestMethod.POST)    public String updateUser(@ModelAttribute("user")UserEntity userEntity){        System.out.print("updateUser");        userDao.updateUser(userEntity.getNickname(),userEntity.getFirstName(),userEntity.getLastName(),userEntity.getPassword(),userEntity.getId());        userDao.flush();        return "redirect:/admin/users";    }    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)    public String  forwardAddUser(){        return "admin/addUser";    }    @RequestMapping(value = "/", method = RequestMethod.GET)    public String index() {        return "index";    }}