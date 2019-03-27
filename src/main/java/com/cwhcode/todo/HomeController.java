package com.cwhcode.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listItems(Model model) {
        model.addAttribute("items", toDoRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        model.addAttribute("item", new Item());
        return "additem";
    }

    @PostMapping("/process")
    public String processItem(@Valid Item item, BindingResult result)
    {
        if (result.hasErrors()) {
            return "additem";
        }
        toDoRepository.save(item);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showItem(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("item", toDoRepository.findById(id).get());
        return "showitem";
    }

    @RequestMapping("/edit/{id}")
    public String editItem(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("item", toDoRepository.findById(id).get());
        return "additem";
    }

    @RequestMapping("/delete/{id}")
    public String delItem(@PathVariable("id") long id)
    {
        toDoRepository.deleteById(id);
        return "redirect:/";
    }

}
