package com.makhnyov.creditscoringapp.controller;

import com.makhnyov.creditscoringapp.model.*;
import com.makhnyov.creditscoringapp.service.*;
import com.makhnyov.creditscoringapp.service.scoring.MonthlyPaymentService;
import com.makhnyov.creditscoringapp.service.scoring.Scoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@Controller
@SessionAttributes({"client", "credit"})
@RequestMapping("/scoring")
public class ScoringController {

    private final GenderService genderService;
    private final EducationService educationService;
    private final CreditTermService creditTermService;
    private final PositionService positionService;
    private final ClientService clientService;
    private final RateService rateService;
    private final MonthlyPaymentService monthlyPaymentService;
    private final Scoring scoring;

    @Autowired
    public ScoringController(CreditTermService creditTermService, MonthlyPaymentService monthlyPaymentService, Scoring scoring, GenderService genderService,
                             EducationService educationService, PositionService positionService, ClientService clientService, RateService rateService) {
        this.creditTermService = creditTermService;
        this.monthlyPaymentService = monthlyPaymentService;
        this.scoring = scoring;
        this.genderService = genderService;
        this.educationService = educationService;
        this.positionService = positionService;
        this.clientService = clientService;
        this.rateService = rateService;
    }

    //отображение формы выбора параметров кредита
    @GetMapping("/loan_parameters")
    public String showLoanParametersPage(Model model) {
        //получение из базы возможных сроков кредитования
        List<Term> creditTerms = creditTermService.getAllCreditTerm();
        //получение из базы процентной ставки
        Rate rate = rateService.getRateById(1);
        model.addAttribute("credit", new Credit());
        model.addAttribute("creditTerm", creditTerms);
        model.addAttribute("rt", rate);
        model.addAttribute("client", new Client());
        return "loan_parameters";
    }

    //отображение формы ввода контактных данных
    @GetMapping("/contact_details")
    public String showContactDetailsPage(Model model) {
        //проверка того, что клиент уже ввёл параметры кредита,
        // иначе возращается на форму ввода параметров кредита
        if (model.getAttribute("credit") == null) {
            return "redirect:/scoring/loan_parameters";
        }
        model.addAttribute("client");
        return "contact_details";
    }

    //отображение формы ввода паспортных данных
    @GetMapping("/passport_details")
    public String showPassportDetailsPage(Model model) {
        //проверка того, что клиент уже ввёл контактные данные,
        // иначе возращается на форму ввода контактных данных
        Client client = (Client) model.getAttribute("client");
        if (client == null || client.getFullName().isEmpty()) {
            return "redirect:/scoring/contact_details";
        }
        model.addAttribute("client");
        //получение из базы возможные значения гендера
        List<Gender> genders = genderService.getAllGenders();
        model.addAttribute("gen", genders);
        return "passport_details";
    }

    //отображение формы ввода данных о работе
    @GetMapping("/work_place")
    public String showWorkPlacePage(Model model) {
        //проверка того, что клиент уже ввёл паспортные данные,
        // иначе возращается на форму ввода паспортных данных
        Client client = (Client) model.getAttribute("client");
        if (client == null || client.getPassportSeries().isEmpty()) {
            return "redirect:/scoring/passport_details";
        }
        model.addAttribute("client");
        //получение из базы возможные значения образования
        List<Education> educations = educationService.getAllEducations();
        model.addAttribute("edu", educations);
        //получение из базы возможные занимаемые должности
        List<Position> positions = positionService.getAllPosition();
        model.addAttribute("pos", positions);
        return "work_place";
    }

    //отображение формы ввода дополнительной информации
    @GetMapping("/additional_inf")
    public String showAdditionalInfPage(Model model) {
        //проверка того, что клиент уже ввёл данные о работе,
        // иначе возращается на форму ввода данных о работе
        Client client = (Client) model.getAttribute("client");
        if (client == null || client.getWorkPlace().isEmpty()) {
            return "redirect:/scoring/work_place";
        }
        model.addAttribute("client");
        return "additional_inf";
    }

    //отображение формы вывода результата
    @GetMapping("/result")
    public String showResultPage(Model model) {
        //проверка того, что клиент уже ввёл дополнительную информацию,
        // иначе возращается на форму ввода дополнительной информации
        Client client = (Client) model.getAttribute("client");
        Credit credit = (Credit) model.getAttribute("credit");
        if (client == null || client.getWorkPlace().isEmpty()) {
            return "redirect:/scoring/additional_inf";
        }
        model.addAttribute("credit");
        assert credit != null;
        model.addAttribute("result",
                 credit.isApproved() ? "Кредит одобрен" : "Кредит не одобрен" );
        return "result";
    }

    //добавление кредитных данных
    @PostMapping("/add_loan_parameters")
    public String addLoanParameters(@ModelAttribute @Valid Credit credit, BindingResult bindingResult,
                                    Model model) {
        //валидация формы
        if (bindingResult.hasErrors()) {
            List<Term> creditTerms = creditTermService.getAllCreditTerm();
            Rate rate = rateService.getRateById(1);
            model.addAttribute("credit");
            model.addAttribute("creditTerm", creditTerms);
            model.addAttribute("rt", rate);
            return "loan_parameters";
        }
        return "redirect:/scoring/contact_details";
    }

    //добавление контактных данных
    @PostMapping("/add_contact_details")
    public String addContactDetails(@ModelAttribute @Valid Client client, BindingResult bindingResult,
                                    Model model) {
        //валидация формы
        if (bindingResult.hasFieldErrors("phone") || bindingResult.hasFieldErrors("fullName") || bindingResult.hasFieldErrors("email")) {
            model.addAttribute("client");
            return "contact_details";
        }
        return "redirect:/scoring/passport_details";
    }

    //добавление пасспортных данных
    @PostMapping("/add_passport_details")
    public String addPassportDetails(@ModelAttribute @Valid Client client, BindingResult bindingResult,
                                     Model model) {
        //валидация формы
        if (bindingResult.hasFieldErrors("passportSeries") || bindingResult.hasFieldErrors("passportId") || bindingResult.hasFieldErrors("registration")
        || bindingResult.hasFieldErrors("dob")) {
            model.addAttribute("client");
            List<Gender> genders = genderService.getAllGenders();
            model.addAttribute("gen", genders);
            return "passport_details";
        }
        return "redirect:/scoring/work_place";
    }

    //добавление данных о работе
    @PostMapping("/add_work_place")
    public String addWorkPlace(@ModelAttribute @Valid  Client client, BindingResult bindingResult,
                               Model model) {
        //валидация формы
        if (bindingResult.hasFieldErrors("workPlace") || bindingResult.hasFieldErrors("experience") || bindingResult.hasFieldErrors("income")) {
            model.addAttribute("client");
            //получение из базы возможные значения образования
            List<Education> educations = educationService.getAllEducations();
            model.addAttribute("edu", educations);
            //получение из базы возможные занимаемые должности
            List<Position> positions = positionService.getAllPosition();
            model.addAttribute("pos", positions);
            return "work_place";
        }
        return "redirect:/scoring/additional_inf";
    }

    //добавление дополнительной информации
    @PostMapping("/add_additional_inf")
    public String addAdditionalInf(@ModelAttribute("client") @Valid Client client, @ModelAttribute("credit") Credit credit,
                                   BindingResult bindingResult, Model model) {
        //валидация формы
        if (bindingResult.hasErrors()) {
            model.addAttribute("client");
            return "additional_inf";
        }
        //установка ежемесячного платежа по кредиту
        credit.setMonthlyPayment(monthlyPaymentService.getMonthlyPayment(credit.getAmount(), credit.getTerm().getMonthsTerm(), credit.getRate().getRate()));
        credit.setApproved(scoring.isApproved(client, credit));
        //проверка существования клиента в базе с такими серией и номером паспорта
        Client existedClient = clientService.findClientByPassportSeriesAndPassportId(client.getPassportSeries(),client.getPassportId());
        //если клиент с такими серией и номером паспорта существует,
        //то обновляес его, иначе просто добавляем нового клиента
        if (existedClient != null) {
            List<Credit> credits = existedClient.getCredits();
            credits.add(credit);
            client.setCredits(credits);
            client.setId(existedClient.getId());
        } else
            client.setCredits(List.of(credit));

        clientService.saveClient(client);
        return "redirect:/scoring/result";
    }
}
