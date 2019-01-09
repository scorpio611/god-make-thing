package com.codegym.controller;


import com.codegym.model.Thing;
import com.codegym.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ThingController {
    @Autowired
    private ThingService thingService;


    @GetMapping("/thing/s")
    public ModelAndView listThings(Pageable pageable) {
        Page<Thing> things = thingService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("thing", things);
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView Things(Pageable pageable) {
        Page<Thing> things = thingService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("thing", things);
        return modelAndView;
    }

    @GetMapping("/thing")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("thing", new Thing());
        return modelAndView;
    }

    @PostMapping("/thing")
    public ModelAndView saveCustomer(@ModelAttribute("thing") Thing thing){
        thingService.save(thing);

        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("thing", new Thing());
        modelAndView.addObject("message", "New thing created successfully");
        return modelAndView;
    }

    @GetMapping("/thing/{id}")
    public ModelAndView showThings(@PathVariable Long id) {
        Thing thing = thingService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("thing", thing);
        return modelAndView;
    }

    @GetMapping("/edit-thing/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Thing thing = thingService.findById(id);
        if(thing != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("thing", thing);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-thing")
    public String updateCustomer(@ModelAttribute("thing") Thing thing){
        thingService.save(thing);
        return "redirect:/";
    }

    @GetMapping("/delete-thing/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Thing thing = thingService.findById(id);
        if(thing != null) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("thing", thing);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-thing")
    public String deleteCustomer(@ModelAttribute("thing") Thing thing){
        thingService.remove(thing.getId());
        return "redirect:/";
    }
}


