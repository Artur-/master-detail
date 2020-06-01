package com.example.application.views.masterdetailviewjava;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.data.CrudServiceDataProvider;
import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@Route(value = "master-detail-view-java")
@RouteAlias(value = "")
@PageTitle("master-detail-view-java")
@CssImport("views/masterdetailviewjava/masterdetailviewjava-view.css")
public class MasterdetailviewjavaView extends SplitLayout  {

    private Grid<Person> grid;

        private TextField profilePicture;
    private TextField firstName;
    private TextField lastName;
    private TextField email;
    private TextField occupation;


    private Button cancel;
    private Button save;

    private Binder<Person> binder;
    private PersonService entityService;

    public MasterdetailviewjavaView(@Autowired PersonService entityService) {
        this.entityService = entityService;
        constructUI();
        binder = new Binder<>(Person.class);
        binder.bindInstanceFields(this);

        grid.setDataProvider(new CrudServiceDataProvider<Person, Void>(entityService));
        grid.asSingleSelect().addValueChangeListener(event -> gridItemSelected(event.getValue()));

        cancel.addClickListener(e -> this.clearForm());
        save.addClickListener(e -> {
            save();
        });

    }

    private void constructUI() {
        addClassName("full-size");

        Div gridWrapper = new Div();
        gridWrapper.setClassName("grid-wrapper");
        grid = new Grid<>();
        grid.setId("grid");
        grid.addClassName("full-size");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

                grid.addColumn(TemplateRenderer.<Person>of("<img class='profilePicture' src=[[item.url]]>").withProperty("url", Person::getProfilePicture)).setAutoWidth(true);
        grid.addColumn(Person::getFirstName).setHeader("First Name").setAutoWidth(true);
        grid.addColumn(Person::getLastName).setHeader("Last Name").setAutoWidth(true);
        grid.addColumn(Person::getEmail).setHeader("Email").setAutoWidth(true);
        grid.addColumn(Person::getOccupation).setHeader("Occupation").setAutoWidth(true);


        gridWrapper.add(grid);

        Div editorLayout = new Div();
        editorLayout.setId("editor-layout");
        FormLayout formLayout = new FormLayout();

                profilePicture = new TextField("Profile Picture");
        profilePicture.setClassName("full-width");
        formLayout.add(profilePicture);
        firstName = new TextField("First Name");
        firstName.setClassName("full-width");
        formLayout.add(firstName);
        lastName = new TextField("Last Name");
        lastName.setClassName("full-width");
        formLayout.add(lastName);
        email = new TextField("Email");
        email.setClassName("full-width");
        formLayout.add(email);
        occupation = new TextField("Occupation");
        occupation.setClassName("full-width");
        formLayout.add(occupation);


        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setSpacing(true);

        save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        cancel = new Button("Cancel");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonLayout.add(cancel, save);

        editorLayout.add(formLayout);
        editorLayout.add(buttonLayout);

        addToPrimary(gridWrapper);
        addToSecondary(editorLayout);
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