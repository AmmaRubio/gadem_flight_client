package hello.world.demo.flightui;

import hello.world.demo.flightclient.Flight;
import hello.world.demo.flightclient.WebClientFlightClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


@Component
public class MenuController implements Consumer<Flight> {
    private WebClientFlightClient webClientFlightClient;

    public MenuController(WebClientFlightClient webClientFlightClient) {
        this.webClientFlightClient = webClientFlightClient;
    }
    @FXML
    private TableView tableView;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private VBox dataContainer;
    ObservableList<Flight> masterData;


    @FXML
    private void initialize() {
        searchButton.setText("Search");
      //  searchButton.setOnAction(event -> loadData()); TODO: implement working button
        searchButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");


        initTable();
    }
    private void initTable() {
        masterData= FXCollections.observableArrayList(webClientFlightClient.flightsFor("flight"));
        tableView = new TableView<>(FXCollections.observableList(masterData));
        TableColumn id = new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<Flight,String>("id"));
        TableColumn  company = new TableColumn("company");
        company.setCellValueFactory(new PropertyValueFactory<Flight,String>("company"));
        TableColumn departure = new TableColumn("departure");
        departure.setCellValueFactory(new PropertyValueFactory<Flight,String>("departure"));
        TableColumn destination = new TableColumn("destination");
        destination.setCellValueFactory(new PropertyValueFactory<Flight,String>("destination"));
        TableColumn date = new TableColumn("date");
        date.setCellValueFactory(new PropertyValueFactory<Flight,String>("date"));
        TableColumn departureTime = new TableColumn("departure time");
        departureTime.setCellValueFactory(new PropertyValueFactory<Flight,String>("departureTime"));
        TableColumn  destinationTime= new TableColumn("destination time");
        destinationTime.setCellValueFactory(new PropertyValueFactory<Flight,String>("destinationTime"));
        TableColumn duration = new TableColumn("duration");
        duration.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightDuration"));

        tableView.getColumns().addAll(id, company, departure, destination, date, departureTime, destinationTime, duration);
        dataContainer.getChildren().add(tableView);
        tableView.setItems(masterData);
    }

    @Override
    public void accept(Flight flight) {

    }
}
