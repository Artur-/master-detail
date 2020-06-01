package com.example.application.views.masterdetailviewdesigner;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.data.CrudServiceDataProvider;
import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.templatemodel.TemplateModel;

@Route(value = "master-detail-view-designer")
@PageTitle("master-detail-view-designer")
@CssImport("views/masterdetailviewdesigner/masterdetailviewdesigner-view.css")
@JsModule("./views/masterdetailviewdesigner/masterdetailviewdesigner-view.js")
@Tag("masterdetailviewdesigner-view")
public class MasterdetailviewdesignerView extends PolymerTemplate<TemplateModel>  {

    @Id
    private Grid<Person> grid;
    
        @Id
    private TextField profilePicture;
    @Id
    private TextField firstName;
    @Id
    private TextField lastName;
    @Id
    private TextField email;
    @Id
    private TextField occupation;

    
    @Id
    private Button cancel;
    @Id
    private Button save;

    private Binder<Person> binder;
    private PersonService entityService;

    public MasterdetailviewdesignerView(@Autowired PersonService entityService) {
        this.entityService = entityService;
        binder = new Binder<>(Person.class);
        binder.bindInstanceFields(this);

                grid.addColumn(TemplateRenderer.<Person>of("<img class='profilePicture' src=[[item.url]]>").withProperty("url", Person::getProfilePicture)).setAutoWidth(true);
        grid.addColumn(Person::getFirstName).setHeader("First Name").setAutoWidth(true);
        grid.addColumn(Person::getLastName).setHeader("Last Name").setAutoWidth(true);
        grid.addColumn(Person::getEmail).setHeader("Email").setAutoWidth(true);
        grid.addColumn(Person::getOccupation).setHeader("Occupation").setAutoWidth(true);


        grid.setDataProvider(new CrudServiceDataProvider<Person, Void>(entityService));
        grid.asSingleSelect().addValueChangeListener(event -> gridItemSelected(event.getValue()));

        cancel.addClickListener(e -> this.clearForm());
        save.addClickListener(e -> {
            save();
        });

    }

    private void gridItemSelected(Person item) {
        // Value can be null as well, that clears the form
        binder.setBean(item);
    }

    private void save() {
        Person entity = binder.getBean();
        try {
            entityService.update(entity);
            this.clearForm();
            Notification.show("Person saved");
        } catch (Exception e) {
            Notification.show("Unable to save: " + e.getMessage());
        }
    }

    private void clearForm() {
        // This deslelects the item, which in turn clears the fields
        grid.asSingleSelect().clear();
    }
}