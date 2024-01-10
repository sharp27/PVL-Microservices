package de.mse.boerse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/evaluate")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;
    
    private EvaluationData dataForApp1 = null;
    private EvaluationData dataForApp2 = null;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/data")
    public String getData(Model model, @RequestParam(required = false) String symbol, @RequestParam(required = false) String service) {
        if (symbol != null && service != null) {
            if ("app1".equals(service)) {
                dataForApp1 = evaluationService.evaluateDataForService(symbol, service);
            } else if ("app2".equals(service)) {
                dataForApp2 = evaluationService.evaluateDataForService(symbol, service);
            }
        }

        model.addAttribute("data1", dataForApp1);
        model.addAttribute("data2", dataForApp2);

        return "index";
    }
    
}

