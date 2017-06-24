/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {

	
	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	
    	Integer year = boxAnno.getValue();
    	
    	if (year == null){
    		txtResult.appendText("ERRORE : SELEZIONA UN ANNO\n");
    	}
    	
    	try {
    	model.creaGrafo(year);
    	txtResult.appendText("Grafo creato!\n");
    	
    	txtResult.appendText("Grafo connesso? " + model.isStronglyConnected() + "\n");
    	
    	txtResult.appendText(model.getMostraPiuCool());
    	}
    	
    	catch(RuntimeException e){
    		txtResult.appendText("OOOPS, qualcosa è andato storto nella creazione del grafo\n");
    	}

    }

    @FXML
    void handleSimula(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	Integer year = boxAnno.getValue();
    	
    	if (year == null){
    		txtResult.appendText("ERRORE : SELEZIONA UN ANNO\n");
    	}
    	
    	String stringa = txtFieldStudenti.getText();
    	int numero = 0 ;
    	
    	try{
    		numero = Integer.parseInt(stringa);
    		
    	} 
    	catch(NumberFormatException e){
    		txtResult.appendText(e.getMessage());
    	}
    	
    	model.simula(numero, year);
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getYears());
		
	}
}
