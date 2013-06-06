package net.lampetty.samples.spring.mvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import net.lampetty.samples.spring.mvc.form.UserForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FormとValidationのサンプル
 */
@Controller
public class FormController {

    // 入力画面
    @RequestMapping(value = "/form/input", method = GET)
    public String index(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "/form/input";
    }
    
    // バリデーションを行う
    @RequestMapping(value = "/form/process", method = POST)
    public String process(
            Model model,
            @Valid UserForm userForm,
            BindingResult result) {
        
        if (result.hasErrors()) {
            // エラーがある場合は入力画面を表示する
            model.addAttribute("userForm", userForm);
            return "/form/input";
        }
        
        // データベースの更新とか
        
        // 終わったら完了画面へリダイレクト
        return "redirect:/form/complete";
    }
    
    // 完了画面
    @RequestMapping(value = "/form/complete", method = GET)
    public String complete(Model model) {
        return "/form/complete";
    }
}
