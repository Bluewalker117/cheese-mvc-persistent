package org.launchcode.controllers;


import org.launchcode.Forms.AddMenuItemForm;
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

    @RequestMapping(value ="view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId){
        Menu single = menuDao.findOne(menuId);
        model.addAttribute("title", single.getName());
        model.addAttribute("menuItems", single.getCheeses());
        model.addAttribute("menuId", single.getId());
        return "menu/view";
    }

    @RequestMapping(value ="add_item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId){
        Menu menu = menuDao.findOne(menuId);

        AddMenuItemForm form = new AddMenuItemForm(cheeseDao.findAll(), menu);
        model.addAttribute("title", "Add an Item to This Menu:" + menu.getName());
        model.addAttribute("form", form);
        return "menu/add_item";
        }

    @RequestMapping(value = "add_item", method = RequestMethod.POST)
    public String addItem (Model model, @ModelAttribute @Valid AddMenuItemForm newForm, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("form", newForm);
            return "menu/add_item";
        }

        Cheese aCheese = cheeseDao.findOne(newForm.getCheeseId());
        Menu aMenu = menuDao.findOne(newForm.getMenuId());
        aMenu.addItem(aCheese);
        menuDao.save(aMenu);
        return "redirect:/menu/view/" + aMenu.getId();
    }
}
