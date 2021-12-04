package com.example.application.views.mainview;

import com.example.application.MainView;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("MainView")
@Route(value = "mainview", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MainViewView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public MainViewView() {
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            String str = name.getValue();
            try {
               // Notification.show(String.valueOf(MainView.zScore));
                MainView test = new MainView(str);
                Notification.show(String.valueOf(test.zScore));
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //Notification.show("Hello " + name.getValue());
        });

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(name, sayHello);
    }

}
