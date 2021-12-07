package com.example.application.views.mainview;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import com.example.application.MainView;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Class")
@Route(value = "mainview", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MainViewView extends VerticalLayout {

    private TextField name;
    private Button btn;
    private TextArea ta, ta1, ta2, ta3, ta4, ta5, ta6, ta7;
    private NumberFormat form = new DecimalFormat("#0.000");
    //private static Integer counter = 0;

    public MainViewView() throws Exception{
        name = new TextField("Enter Class 1-9");
        btn = new Button("GO");
        ta = new TextArea("Z Score");
        ta1 = new TextArea("Sample");
        ta2 = new TextArea("Population");
        ta3 = new TextArea("Sample Mean");
        ta4 = new TextArea("Population Mean");
        ta5 = new TextArea("Significance");
        ta6 = new TextArea("Total Sample");
        ta7 = new TextArea("Total Population");
        Span c1 = new Span("1 = ComSc234_01.SEC");
        Span c2 = new Span("2 = ComSc330_01.SEC");
        Span c3 = new Span("3 = ComSc330_02.SEC");
        Span c4 = new Span("4 = ComSc335_01.SEC");
        Span c5 = new Span("5 = ComSc440_01.SEC");
        Span c6 = new Span("6 = ComSc450_01.SEC");
        Span c7 = new Span("7 = ComSc490_01.SEC");
        Span c8 = new Span("8 = ComSc492_01.SEC");
        Span c9 = new Span("9 = ComSc492_02.SEC");

        ta.setReadOnly(true);
        ta1.setReadOnly(true);
        ta3.setReadOnly(true);
        ta2.setReadOnly(true);
        ta4.setReadOnly(true);
        ta5.setReadOnly(true);
        ta6.setReadOnly(true);
        ta7.setReadOnly(true);
        
        MainView run = new MainView();
        btn.addClickListener(e -> {
            String str = name.getValue();
            try {
                MainView test = new MainView(str);
                
                ta.setValue(String.valueOf(form.format(test.zScore)));
                ta7.setValue(String.valueOf(test.totalP));
                ta6.setValue(String.valueOf(test.totalS));
                ta5.setValue(test.sig);
                ta1.setValue(test.SampleMap.toString());
                ta2.setValue(test.PopulationMap.toString());
                ta3.setValue(String.valueOf(form.format(test.sampleMean)));
                ta4.setValue(String.valueOf(form.format(test.popMean)));
                test.PopulationMap.clear();
                test.SampleMap.clear();
                
            } catch (Exception e1) {
                
                e1.printStackTrace();
            }
            
        });

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.START, name, btn, ta, ta1, ta2, ta3, ta4, ta5, ta6, ta7);
        VerticalLayout content = new VerticalLayout(c1, c2, c3, c4, c5, c6, c7, c8, c9);
        content.setSpacing(false);
        content.setPadding(false);
        Details details = new Details("Classes", content);
        details.setOpened(false);
        VerticalLayout v = new VerticalLayout(ta6, ta7);
        VerticalLayout v2 = new VerticalLayout(name, details);
        HorizontalLayout classL = new HorizontalLayout(v2, v);
        HorizontalLayout hMap = new HorizontalLayout(ta1, ta2);
        HorizontalLayout hMean = new HorizontalLayout(ta3, ta4);
        HorizontalLayout hScore = new HorizontalLayout(ta, ta5);
        
        add(classL, btn, hScore, hMap, hMean);
    }
}
