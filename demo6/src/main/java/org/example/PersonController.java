package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PersonController implements Initializable {

    @FXML
    private TextField fnameText;

    @FXML
    private TextField lnameText;

    @FXML
    private TextArea noteText;

    @FXML
    private Button createButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<Person> listView;

    private final ObservableList<Person> personList = FXCollections.observableArrayList(Person.extractor);
    private Person selectedPerson;
    private final BooleanProperty modifiedProperty = new SimpleBooleanProperty(false);
    private ChangeListener<Person> personChangeListener;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
        updateButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull()
                .or(modifiedProperty.not())
                .or(fnameText.textProperty().isEmpty())
                .or(lnameText.textProperty().isEmpty())
        );
        createButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull()
                .or(fnameText.textProperty().isEmpty())
                .or(lnameText.textProperty().isEmpty())
        );

        SampleData.fillSampleData(personList);

        //Sort by lastname, firstname
        SortedList<Person> sortedList = new SortedList<>(personList);
        sortedList.setComparator((p1, p2) -> {
            int result = p1.getLastname().compareToIgnoreCase(p2.getLastname());
            if (result == 0) {
                result = p1.getFirstname().compareToIgnoreCase(p2.getFirstname());
            }
            return result;
        });
        listView.setItems(sortedList);
        listView.getSelectionModel().selectedItemProperty().addListener(
                personChangeListener = ((observable, oldValue, newValue) -> {
                    System.out.println("Selected item: " + newValue);
                    selectedPerson = newValue;
                    modifiedProperty.setValue(false);
                    if (newValue != null) {
                        fnameText.setText(selectedPerson.getFirstname());
                        lnameText.setText(selectedPerson.getLastname());
                        noteText.setText(selectedPerson.getNotes());
                    } else {
                        fnameText.setText("");
                        lnameText.setText("");
                        noteText.setText("");
                    }
                })
        );
        listView.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleKeyAction(KeyEvent keyEvent) {
        modifiedProperty.set(true);
    }

    @FXML
    private void createButtonAction(ActionEvent actionEvent) {
        System.out.println("Create");
        var person = new Person(fnameText.getText(), lnameText.getText(), noteText.getText());
        personList.add(person);
        listView.getSelectionModel().select(person);
    }

    @FXML
    private void deleteButtonAction(ActionEvent actionEvent) {
        System.out.println("Delete " + selectedPerson);
        personList.remove(selectedPerson);
    }

    @FXML
    private void updateButtonAction(ActionEvent actionEvent) {
        System.out.println("Update person: " + selectedPerson);
        var person = listView.getSelectionModel().getSelectedItem();
        listView.getSelectionModel().selectedItemProperty().removeListener(personChangeListener);
        person.setFirstname(fnameText.getText());
        person.setLastname(lnameText.getText());
        person.setNotes(noteText.getText());
        listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener);
        modifiedProperty.setValue(false);
    }

}
