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
    private TextArea ta, ta1, ta2, ta3, ta4, ta5;
    private NumberFormat form = new DecimalFormat("#0.000");

    public MainViewView() throws Exception{
        name = new TextField("Enter Class 1-9");
        btn = new Button("GO");
        ta = new TextArea("Z Score");
        ta1 = new TextArea("Sample");
        ta2 = new TextArea("Population");
        ta3 = new TextArea("Sample Mean");
        ta4 = new TextArea("Population Mean");
        ta5 = new TextArea("Significance");
        Span c1 = new Span("1 = ComSc234_01.SEC");
        Span c2 = new Span("2 = ComSc330_01.SEC");
        Span c3 = new Span("3 = ComSc330_02.SEC");
        Span c4 = new Span("4 = ComSc335_01.SEC");
        Span c5 = new Span("5 = ComSc440_01.SEC");
        Span c6 = new Span("6 = ComSc450_01.SEC");
        Span c7 = new Span("7 = ComSc490_01.SEC");
        Span c8 = new Span("8 = ComSc492_01.SEC");
        Span c9 = new Span("9 = ComSc492_02.SEC");
        
        MainView run = new MainView();
        btn.addClickListener(e -> {
            String str = name.getValue();
            try {
               // Notification.show(String.valueOf(MainView.zScore));
                MainView test = new MainView(str);
                //MainViewView grp = new MainViewView(str);

                ta.setValue(String.valueOf(form.format(test.zScore)));
                ta5.setValue(test.sig);
                ta1.setValue(test.SampleMap.toString());
                ta2.setValue(test.PopulationMap.toString());
                ta3.setValue(String.valueOf(form.format(test.sampleMean)));
                ta4.setValue(String.valueOf(form.format(test.popMean)));
                test.PopulationMap.clear();
                test.SampleMap.clear();
                //Notification.show();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //Notification.show("Hello " + name.getValue());
        });

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.START, name, btn, ta, ta1, ta2, ta3, ta4);
        VerticalLayout content = new VerticalLayout(c1, c2, c3, c4, c5, c6, c7, c8, c9);
        content.setSpacing(false);
        content.setPadding(false);
        Details details = new Details("Classes", content);
        details.setOpened(false);
        HorizontalLayout classL = new HorizontalLayout(name, details);
        HorizontalLayout hl = new HorizontalLayout(ta1, ta2);
        HorizontalLayout hL = new HorizontalLayout(ta3, ta4);
        HorizontalLayout h = new HorizontalLayout(ta, ta5);
        add(classL, btn, h, hl, hL);
    }
}
