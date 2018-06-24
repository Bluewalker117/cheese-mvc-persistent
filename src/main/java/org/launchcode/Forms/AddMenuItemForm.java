package org.launchcode.Forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private Integer menuId;

    @NotNull
    private Integer cheeseId;



    public AddMenuItemForm() { }

    public AddMenuItemForm(Iterable<Cheese> cheeses, Menu menu){
        this.cheeses = cheeses;
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(Integer cheeseId) {
        this.cheeseId = cheeseId;
    }
}
