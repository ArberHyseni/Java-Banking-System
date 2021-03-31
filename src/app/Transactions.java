package app;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Language;
import utils.Session;

public class Transactions {
	private TableColumn user;
	private TableColumn transaction;
	private TableColumn transactionsum;
	private TableColumn transactiondate;
	private Label title;
	
	private TableView<Transaction> table = new TableView();
	
	public void getStage() {
		Stage TransactionStage = new Stage();
		BorderPane pane = new BorderPane();
		title = Language.getLabel("label6");
		user = Language.getTblColumn("label15");
		transaction = Language.getTblColumn("label16");
		transactionsum = Language.getTblColumn("label17");
		transactiondate = Language.getTblColumn("label18");
		user.setCellValueFactory(new PropertyValueFactory("user"));
		transaction.setCellValueFactory(new PropertyValueFactory("transactionsum"));
		transactionsum.setCellValueFactory(new PropertyValueFactory("transactionType"));
		transactiondate.setCellValueFactory(new PropertyValueFactory("transactiondate"));
		
		user.setPrefWidth(150);
		transaction.setPrefWidth(150);
		transactionsum.setPrefWidth(150);
		transactiondate.setPrefWidth(150);
		
		//add above later
		table.getColumns().addAll(user,transaction, transactionsum, transactiondate);
		table.setPrefHeight(300);
		table.setPrefWidth(600);
		Pane tablePane = new Pane();
		tablePane.getChildren().add(table);
		pane.setRight(tablePane);
		
		Scene scene = new Scene(pane,600,600);
		TransactionStage.setTitle(title.getText());
		TransactionStage.setScene(scene);
		showTransactions();
		TransactionStage.show();
		
	}
	private void showTransactions()
	{
		List<Transaction> transactions = Transaction.gettransaction(Session.getId());
		ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
		for (int i = 0; i < transactions.size(); i++) {
			transactionList.add(transactions.get(i));
		}
		table.setItems(transactionList);
	}
}
