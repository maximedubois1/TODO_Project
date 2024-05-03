package com.sp.controller;

import com.sp.model.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RequestCrt {

    private static String messageLocal = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";

    @Autowired
    CardDao cardDao;
    @Value("${welcome.message}")
    private String message;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("messageLocal", messageLocal);
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"/addCard"}, method = RequestMethod.GET)
    public String addCard(Model model) {
        CardDTO cardDTO = new CardDTO();
        model.addAttribute("newCardForm", cardDTO);

        return "newCardForm";
    }

    @RequestMapping(value = {"/addCard"}, method = RequestMethod.POST)
    public String addCard(Model model, @ModelAttribute("newCardform") CardDTO cardDTO) throws IOException {
        CardDTO c = cardDao.addCard(cardDTO);
        model.addAttribute("card", c);

        return "cardView";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String showCard(Model model, @PathVariable("id") int id) throws IOException {
        CardDTO cardDTO = cardDao.getCardById(id);
        model.addAttribute("card", cardDTO);

        return "cardView";
    }
}
