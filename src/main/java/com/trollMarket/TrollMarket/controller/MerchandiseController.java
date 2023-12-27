package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.product.ProductUpsertDTO;
import com.trollMarket.TrollMarket.service.ProductService;
import com.trollMarket.TrollMarket.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/merchandise")
public class MerchandiseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/index")
    public String getProductRow(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = true) String username,
                                Model model){
        var rows = productService.getProductMerchandiseRow(username,page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("username",username);
        return "merchandise/merchandise-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = true) String username,@RequestParam(required = false) Long id, Model model){
        var dto = new ProductUpsertDTO();
        if(id != null){
            dto =productService.findOne(id);
        }
        model.addAttribute("dto", dto);
        model.addAttribute("username",username);
        return "merchandise/merchandise-form";
    }
    @PostMapping("/upsert")
    public String insert(@Valid @ModelAttribute("dto") ProductUpsertDTO dto, BindingResult bindingResult,
                         @RequestParam(required = true) String username, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() == false){
            var sellerId = sellerService.getIdByUsername(username);
            dto.setSellerId(sellerId);
            if(dto.getDiscontinued()==null){
                dto.setDiscontinued(false);
            }
            productService.save(dto);
            redirectAttributes.addAttribute("username",username);
            return "redirect:/merchandise/index";
        }
        return "merchandise/merchandise-form";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(@RequestParam(required = true) String username,@RequestParam(required = true) Long id, Model model,
                         RedirectAttributes redirectAttributes){
        var dependencies = productService.depandencies(id);
        if (dependencies == 0){
            productService.delete(id);
            redirectAttributes.addAttribute("username",username);
            return "redirect:/merchandise/index";}
        model.addAttribute("dependencies", dependencies);
        return "merchandise/merchandise-delete";
    }
    @GetMapping("/discontinued")
    public String discontinued(@RequestParam(required = true) String username,@RequestParam(required = true) Long id, Model model,
                               RedirectAttributes redirectAttributes){
        productService.discontinued(id);
        redirectAttributes.addAttribute("username",username);
        return "redirect:/merchandise/index";
    }
}
