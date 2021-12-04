package com.example.application.views.mainview;

import com.example.application.MainView;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
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
    private TextArea ta;

    public MainViewView() throws Exception{
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        ta = new TextArea("");
        MainView run = new MainView();
        Chart chart = new Chart();
        chart.getConfiguration().getChart().setType(ChartType.COLUMN);
        sayHello.addClickListener(e -> {
            String str = name.getValue();
            try {
               // Notification.show(String.valueOf(MainView.zScore));
                MainView test = new MainView(str);
                ta.setValue(String.valueOf(test.zScore));

                //Notification.show();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //Notification.show("Hello " + name.getValue());
        });

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello, ta);

        add(name, sayHello, ta, chart);
     
    }

}
