package org.launchcode.controllers;


import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.awt.SystemColor.menu;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping("")
    public String index(Model model){

        model.addAttribute("title", "List of Created Menus");
        model.addAttribute("menuItems", menuDao.findAll());
        return "menu/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addMenu(Model model){
        model.addAttribute("title", "Create a New Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processMenu(@ModelAttribute @Valid Menu newMenu,
                              Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute("title", "Create a New Menu");
            return "menu/add";
        }

        menuDao.save(newMenu);
        Integer number = newMenu.getId();
        return "redirect:view/" + number;
    }

    @RequestMapping(value ="view", method = RequestMethod.POST)
    public String viewMenu(Model model, int id){
        Menu single = menuDao.findOne(id);
        model.addAttribute("name", single.getName());
        return "menu/view";
    }


}
