package com.coocaa.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UrlController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/person")
    public String person(){
        return "person";
    }
    @RequestMapping("/project")
    public String project(){
        return "project";
    }
    @RequestMapping("/efficiency")
    public String efficiency(){
        return "efficiency";
    }
    @RequestMapping("/workload")
    public String workload(){
        return "workload";
    }
    @RequestMapping("/task_graph")
    public String taskGraph(){
        return "task_graph";
    }
    @RequestMapping("/remainder_workReport")
    public String remainderWorkReport(){
        return "remainder_workReport";
    }
    @RequestMapping("/comparison_diagram")
    public String comparisonDiagram(){
        return "comparison_diagram";
    }
    @RequestMapping("/person_workload")
    public String personWorkload(){
        return "person_workload";
    }
    @RequestMapping("/person_efficiency")
    public String personEfficienc(){
        return "person_efficiency";
    }
    @RequestMapping("/team_efficiency")
    public String teamEfficiency(){
        return "team_efficiency";
    }
    @RequestMapping("/team_capacity")
    public String teamCapacity(){
        return "team_capacity";
    }
}
