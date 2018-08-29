package it.polito.tdp.formulaone;

import java.io.DataOutput;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

import it.polito.tdp.formulaone.model.Driver;
import it.polito.tdp.formulaone.model.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormulaOneController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> boxAnno;

    @FXML
    private TextField textInputK;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	try {
    	
    	Year anno = Year.of(this.boxAnno.getValue());
    	
    	if(anno==null) {
    		  this.txtResult.appendText("\nErrore. scegliere una stagione\n");
    		  return;

    	}
  
    	
    	model.createGraph(anno);
		Driver best = model.getBestDriverOf();
				
		this.txtResult.setText(String.format("Il miglior pilota della stagione %d è %s %s (%s)", 
				this.boxAnno.getValue(), best.getForename(), best.getSurname(), best.getNationality()));
    	} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println("Errorre di connessione al DB");
			txtResult.setText("Errore di connessine al DB!");
		}
    }

    @FXML
    void doTrovaDreamTeam(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert textInputK != null : "fx:id=\"textInputK\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FormulaOne.fxml'.";

    }
    
    public void setModel(Model model){
    	this.model = model;
    	
    	this.boxAnno.getItems().addAll(model.getAnni());
    }
}
