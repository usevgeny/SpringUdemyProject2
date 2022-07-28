package hibJpaApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hibJpaApp.models.Person;
import hibJpaApp.services.PersonService;

@Controller
@RequestMapping("/subscribers")
public class PersonController {
    
    @Autowired
    private final PersonService personService;
    private PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("subscribers", personService.index());
        return "subscribers/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("subscriber", personService.show(id));
        model.addAttribute("hiredBooks", personService.showBooks(id));
        return "subscribers/show";
    }
    
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "subscribers/new";
    }
    
    
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        
        
        if (bindingResult.hasErrors())
            return "subscribers/new";

        personService.save(person);
        return "redirect:/subscribers";
    }

    
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        
        if(personService.show(id)==null) {
            return "redirect:/";
        }
        model.addAttribute("person",personService.show(id));
        return ("subscribers/edit");
    }
    
    
    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if(personService.show(id)==null) {
            return "redirect:/";
        }
        //Date convertedDate = DateUtils.formatStringToDate(person.getBirthDate(), "yyyy-MM-dd");
        personService.update(id, person);
        return "redirect:/subscribers";
    }
    

    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/subscribers";
    }
    
}
