package com.company.commands;

import com.company.catalog.Catalog;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command{

    private Catalog catalog;
    private static Configuration cfg;

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        try {
            cfg.setDirectoryForTemplateLoading(new File("/Users/cosminpasat/Desktop/PA/Laborator5/templates/"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public ReportCommand(Catalog newCatalog){

        catalog = newCatalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        try{
            report();
        } catch (Exception e){
            System.out.println("Unexpected error writing the file!");
            e.printStackTrace();
        }
    }

    private void report() throws Exception {
        Map root = new HashMap<>();
        Desktop desktop = Desktop.getDesktop();
        File file;
        root.put("name", catalog.getName());
        root.put("items", catalog.getItems());
        Template temp = cfg.getTemplate("report.ftl");
        Writer out = new FileWriter("./report.html");
        temp.process(root, out);
        out.close();
        file = new File("./report.html");
        desktop.open(file);
    }
}
