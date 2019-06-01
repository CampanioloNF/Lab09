package it.polito.tdp.borders;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

    private static final int MAX_YEAR = 2006;
    private static final int MIN_YEAR = 1816;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<Country> boxCountries;

    @FXML
    private TextArea txtResult;

	private Model model;
	
	
	
    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	
    	txtResult.clear();
    	
    	String input = this.txtAnno.getText();
    	
    	if(input.matches("[0-9]+")) {
    	
    	  if(Integer.parseInt(input)>=MIN_YEAR && Integer.parseInt(input)<=MAX_YEAR ) {
    		
    	   Map<Country, Integer> mappaStati = model.getCountryDegree(Integer.parseInt(input));
    	   
    	   for( Entry<Country, Integer> entry : mappaStati.entrySet()) {
    		   
    		   txtResult.appendText(entry.getKey()+"   : "+entry.getValue()+"\n" );
    	   }
    	   
    	  txtResult.appendText("\nComponenti connesse: "+model.componenetiConnesse());
    	   
    	   boxCountries.setItems(FXCollections.observableList(model.getVertici()));
    	
    	}
    	  else
    	    txtResult.appendText("Errore, inserire un anno compreso tra :" +MIN_YEAR+ " e " +MAX_YEAR);
    
      } 	
    	else
    	  txtResult.appendText("Errore, inserire un anno (numero) compreso tra :" +MIN_YEAR+ " e " +MAX_YEAR);
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {

    	txtResult.clear();
    	
    	if(this.boxCountries.getValue()!=null) {
    		
    		txtResult.appendText("I paesi confinanti con "+boxCountries.getValue()+" sono:\n");
    		
    		for(Country c : model.getRaggiungibili(boxCountries.getValue())) {
    			txtResult.appendText(c+"\n");
    		}
    		
    	}
    	
    }

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxCountries != null : "fx:id=\"boxCountries\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		
	}
}
