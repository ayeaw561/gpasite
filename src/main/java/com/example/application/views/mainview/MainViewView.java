package com.example.application.views.mainview;

import java.util.ArrayList;
import java.util.List;

import com.example.application.MainView;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("MainView")
@Route(value = "mainview", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MainViewView extends VerticalLayout {

    private TextField name;
    private Button sayHello;
    private TextArea ta, ta1, ta2;

    public MainViewView() throws Exception{
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        ta = new TextArea("");
        ta1 = new TextArea("");
        ta2 = new TextArea("");
        MainView run = new MainView();
        sayHello.addClickListener(e -> {
            String str = name.getValue();
            try {
               // Notification.show(String.valueOf(MainView.zScore));
                MainView test = new MainView(str);
    
                ta.setValue(String.valueOf(test.zScore));
                ta1.setValue(test.SampleMap.toString());
                ta2.setValue(test.PopulationMap.toString());
                //Notification.show();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //Notification.show("Hello " + name.getValue());
        });

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.END, name, sayHello, ta, ta1, ta2);

        add(name, sayHello, ta, ta1, ta2);
     
    }

}
