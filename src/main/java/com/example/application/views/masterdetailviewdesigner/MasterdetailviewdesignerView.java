package com.example.application.views.masterdetailviewdesigner;

import com.example.application.data.CrudServiceDataProvider;
import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;


import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "master-detail-view-designer")
@PageTitle("master-detail-view-designer")
@JsModule("./views/masterdetailviewdesigner/masterdetailviewdesigner-view.js")
@Tag("masterdetailviewdesigner-view")
public class MasterdetailviewdesignerView extends PolymerTemplate<TemplateModel> {

    // This is the Java companion file of a design
    // You can find the design file in 
    // /frontend/src/views/views/masterdetailviewdesigner/masterdetailviewdesigner-view.js
    // The design can be easily edited by using Vaadin Designer (vaadin.com/designer)

    // Grid is created here so we can pass the class to the constructor
    private Grid<Person> grid = new Grid<>(Person.class);

    @Id
    private TextField firstName;
    @Id
    private TextField lastName;
    @Id
    private TextField email;
    @Id
    private PasswordField password;
    
    @Id
    private Button cancel;
    @Id
    private Button save;

    private Binder<Person> binder;

    public MasterdetailviewdesignerView(@Autowired PersonService personService) {
        setId("masterdetailviewdesigner-view");
        grid.setColumns("firstName", "lastName", "email");
        grid.setDataProvider(new CrudServiceDataProvider<Person, Void>(personService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();
        // Add to the `<slot name="grid">` defined in the template
        grid.getElement().setAttribute("slot", "grid");
        getElement().appendChild(grid.getElement());
        
        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> populateForm(event.getValue()));

        // Configure Form
        binder = new Binder<>(Person.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.bindInstanceFields(this);
        // note that password field isn't bound since that property doesn't exist in
        // Person

        // the grid valueChangeEvent will clear the form too
        cancel.addClickListener(e -> grid.asSingleSelect().clear());

        save.addClickListener(e -> {
            Notification.show("Not implemented");
        });
    }

    private void populateForm(Person value) {
        // Value can be null as well, that clears the form
        binder.readBean(value);

        // The password field isn't bound through the binder, so handle that
        password.setValue("");
    }
}
