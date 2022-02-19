package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Main extends Application {

    private ObservableList<ObservableList> summaryData;
    @FXML
    private TableView summaryTable;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DatabaseMainWindowNew.fxml"));
        primaryStage.setTitle("HitCompoundDB");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
    }


    public static void main(String[] args) throws ClassNotFoundException {



        launch(args);



    }

    public void buildSummary() {

        Connection c;
        summaryData = FXCollections.observableArrayList();

        try {
            c = DatabaseConnection.connect();
            String SQL = "SELECT Compound.CompoundID, Compound.Compound_name, Molecular_Mass.Formula, Molecular_Mass.Molecular_Mass," +
                    "Chemical_properties.Number_Acidic_Groups, Chemical_properties.Rotatable_bonds, Chemical_properties.Ring_Count," +
                    "Chemical_properties.Molar_Refractivity, Chemical_properties.Surface_area " +
                    " FROM COMPOUND" +
                    " INNER JOIN Molecular_Mass ON Compound.CompoundID = Molecular_Mass.CompoundID, " +
                    " INNER JOIN Chemical_properties ON Compound.CompoundID = Chemical_properties.CompoundID";

            System.out.println(SQL);

            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/

            while(rs.next()){




            }




            }


            catch(SQLException e){
                e.printStackTrace();
                System.out.println("ERROR");
            }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }




    }
}
