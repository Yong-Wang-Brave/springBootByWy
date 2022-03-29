package com.wy.demo.controller;


import lombok.extern.log4j.Log4j2;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.JsonRuleDefinitionReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Component
public class JSONController {
private Set<Rule> setRules;
    @Value("${rules.path.manageModel}")
    // @Value不能使用static修饰
    //引用需要@Autowired注入
private  String rulesPath;
private  MVELRuleFactory ruleFactory;
public JSONController(){this.ruleFactory=new MVELRuleFactory(new JsonRuleDefinitionReader());}

    // @Scheduled(cron="* * * * * *")
    public void LoadFromProperties() {
        Resource resource = new ClassPathResource(rulesPath);
        if (!resource.isFile()) {
            return;
        }
        File root=null;
        try {
            root =resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null==root) {
            log.error("读取配置文件失败，配置不存在");
            return;
        }
        if (!root.isDirectory()) {
            log.error("目录不存在");
            return;
        }
        for (File file : root.listFiles()) {
            readRuleFromFile(file);
        }


    }

    private  void readRuleFromFile(File disease) {
        if (!disease.isDirectory()) {
            return;
        }


        String population = disease.getName();
        Rules rules=null;

        for (File model : disease.listFiles()) {
            String modelName = model.getName().replace(".json", "");

           String ruleGroupName= population+"-"+ modelName;

            try {
                rules=ruleFactory.createRules(new FileReader(model));
                rules.register();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setRules=new HashSet<>();

        for (Rule rule : rules) {


        }
    }

}
