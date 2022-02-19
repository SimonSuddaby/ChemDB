package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private TableView<SummaryTable> Summ = new TableView<SummaryTable>();
    @FXML
    private TableColumn<SummaryTable, Integer> compoundID;
    @FXML
    private TableColumn<SummaryTable, String> compoundName;
    @FXML
    private TableColumn<SummaryTable, String> formula;
    @FXML
    private TableColumn<SummaryTable, Double> molecularMass;
    @FXML
    private TableColumn<SummaryTable, Integer> numberOfAcidicGroups;
    @FXML
    private TableColumn<SummaryTable, Integer> rotatableBonds;
    @FXML
    private TableColumn<SummaryTable, Integer> ringCount;
    @FXML
    private TableColumn<SummaryTable, Double> molecularRefractivity;
    @FXML
    private TableColumn<SummaryTable, Double> surfaceArea;
    @FXML
    private Button summarySave = new Button();
    @FXML
    private TextField summarySearch = new TextField();



    @FXML
    private TableView<LipinskiTable> lipinskiTable = new TableView<>();

    @FXML
    private TableColumn<LipinskiTable, Integer> LipinskicompoundID;

    @FXML
    private TableColumn<LipinskiTable, String> LipinskiName;

    @FXML
    private TableColumn<LipinskiTable, Double> LipinskiMass;

    @FXML
    private TableColumn<LipinskiTable, Integer> hydrogenAcceptor;

    @FXML
    private TableColumn<LipinskiTable, Integer> hydrogenDonor;

    @FXML
    private TableColumn<LipinskiTable, Double> logP;

    @FXML
    private Button lipinskiSave = new Button();

    @FXML
    private TextField lipinskiSearch = new TextField();


    @FXML
    private TableView<GhoseTable> ghoseTable = new TableView<>();

    @FXML
    private TableColumn<GhoseTable, Integer> ghoseCompoundID;

    @FXML
    private TableColumn<GhoseTable, String> ghoseCompoundName;

    @FXML
    private TableColumn<GhoseTable, Double> ghoseMolecularMass;

    @FXML
    private TableColumn<GhoseTable, Double> GhoselogP;

    @FXML
    private TableColumn<GhoseTable, Double> ghoseMolecularRefractivity;

    @FXML
    private TableColumn<GhoseTable, Integer> ghoseTotalAtoms;

    @FXML
    private Button ghoseSave = new Button();

    @FXML
    private TextField ghoseSearch = new TextField();



    @FXML
    private TableView<bbbTable> bbbTable = new TableView<>();

    @FXML
    private TableColumn<bbbTable, Integer> bbbCompoundID;

    @FXML
    private TableColumn<bbbTable, String> bbbCompoundName;

    @FXML
    private TableColumn<bbbTable, Integer> bbbhydrogenBonds;

    @FXML
    private TableColumn<bbbTable, Double> bbbmolecularMass;


    @FXML
    private TableColumn<bbbTable, Integer> bbbNumberAcidic;

    @FXML
    private Button bbbSave = new Button();

    @FXML
    private TextField bbbSearch = new TextField();



    @FXML
    private TableView<QEDTable> qedTableView = new TableView<>();

    @FXML
    private TableColumn<QEDTable, Integer> qedCompoundID;

    @FXML
    private TableColumn<QEDTable, String> qedCompoundName;


    @FXML
    private TableColumn<QEDTable, Double> qedmolecularMass;

    @FXML
    private TableColumn<QEDTable, Double> AlogP;

    @FXML
    private TableColumn<QEDTable, Integer> qedhydrogenAcceptor;

    @FXML
    private TableColumn<QEDTable, Integer> qedhydrogenDonor;

    @FXML
    private TableColumn<QEDTable, Integer> qedrotatableBonds;

    @FXML
    private TableColumn<QEDTable, Double> qedsurfaceArea;

    @FXML
    private TableColumn<QEDTable, Integer> qedringCount;

    @FXML
    private TableColumn<QEDTable, Double> wQED;

    @FXML
    private Button wQEDSave = new Button();

    @FXML
    private TextField wQEDSearch = new TextField();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Init called");

        assert Summ != null : "fx:id=\"tableview\" was not injected: check your FXML file 'UserMaster.fxml'.";
        compoundID.setCellValueFactory(
                new PropertyValueFactory<SummaryTable, Integer>("compoundID"));
        compoundName.setCellValueFactory(
                new PropertyValueFactory<SummaryTable, String>("compoundName"));
        formula.setCellValueFactory(
                new PropertyValueFactory<SummaryTable, String>("formula"));
        molecularMass.setCellValueFactory(
                new PropertyValueFactory<SummaryTable, Double>("molecularMass"));
        numberOfAcidicGroups.setCellValueFactory(new PropertyValueFactory<SummaryTable, Integer>("acidicGroups"));
        rotatableBonds.setCellValueFactory(new PropertyValueFactory<SummaryTable, Integer>("rotatableBonds"));
        ringCount.setCellValueFactory(new PropertyValueFactory<SummaryTable, Integer>("ringCount"));
        molecularRefractivity.setCellValueFactory(new PropertyValueFactory<SummaryTable, Double>("molecularRefractivity"));
        surfaceArea.setCellValueFactory(new PropertyValueFactory<SummaryTable, Double>("surfaceArea"));


        LipinskicompoundID.setCellValueFactory(new PropertyValueFactory<LipinskiTable, Integer>("compoundID"));
        LipinskiName.setCellValueFactory(new PropertyValueFactory<LipinskiTable, String>("compoundName"));
        LipinskiMass.setCellValueFactory(new PropertyValueFactory<LipinskiTable, Double>("molecularMass"));
        hydrogenAcceptor.setCellValueFactory(new PropertyValueFactory<LipinskiTable, Integer>("hydrogenAcceptor"));
        hydrogenDonor.setCellValueFactory(new PropertyValueFactory<LipinskiTable, Integer>("hydrogenDonor"));
        logP.setCellValueFactory(new PropertyValueFactory<LipinskiTable, Double>("logP"));


        ghoseCompoundID.setCellValueFactory(new PropertyValueFactory<GhoseTable, Integer>("compoundID"));
        ghoseCompoundName.setCellValueFactory(new PropertyValueFactory<GhoseTable, String>("compoundName"));
        ghoseMolecularMass.setCellValueFactory(new PropertyValueFactory<GhoseTable, Double>("molecularMass"));
        GhoselogP.setCellValueFactory(new PropertyValueFactory<GhoseTable, Double>("logP"));
        ghoseMolecularRefractivity.setCellValueFactory(new PropertyValueFactory<GhoseTable, Double>("molecularRefractivity"));
        ghoseTotalAtoms.setCellValueFactory(new PropertyValueFactory<GhoseTable, Integer>("totalAtoms"));


        bbbCompoundID.setCellValueFactory(new PropertyValueFactory<bbbTable, Integer>("compoundID"));
        bbbCompoundName.setCellValueFactory(new PropertyValueFactory<bbbTable, String>("compoundName"));
        bbbhydrogenBonds.setCellValueFactory(new PropertyValueFactory<bbbTable, Integer>("hydrogenBonds"));
        bbbmolecularMass.setCellValueFactory(new PropertyValueFactory<bbbTable, Double>("molecularMass"));
        bbbNumberAcidic.setCellValueFactory(new PropertyValueFactory<bbbTable, Integer>("numberAcidicGroup"));

        qedCompoundID.setCellValueFactory(new PropertyValueFactory<QEDTable, Integer>("compoundID"));
        qedCompoundName.setCellValueFactory(new PropertyValueFactory<QEDTable, String>("compoundName"));
        qedmolecularMass.setCellValueFactory(new PropertyValueFactory<QEDTable, Double>("molecularMass"));
        AlogP.setCellValueFactory(new PropertyValueFactory<QEDTable, Double>("AlogP"));
        qedhydrogenAcceptor.setCellValueFactory(new PropertyValueFactory<QEDTable, Integer>("hydrogenAcceptor"));
        qedhydrogenDonor.setCellValueFactory(new PropertyValueFactory<QEDTable, Integer>("hydrogenDonor"));
        qedrotatableBonds.setCellValueFactory(new PropertyValueFactory<QEDTable, Integer>("rotatableBonds"));
        qedsurfaceArea.setCellValueFactory(new PropertyValueFactory<QEDTable, Double>("surfaceArea"));
        qedringCount.setCellValueFactory(new PropertyValueFactory<QEDTable, Integer>("ringCount"));
        wQED.setCellValueFactory(new PropertyValueFactory<QEDTable, Double>("wQED"));

        summarySave.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                summarybuttonShow();

            }
        });

        lipinskiSave.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                lipinskiButtonShow();

            }
        });

        ghoseSave.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                ghoseButtonShow();

            }
        });

        bbbSave.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                bbbButtonShow();

            }
        });

        wQEDSave.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                wQEDButtonShow();

            }
        });

        summarySearch.setPromptText("Compound name");
        lipinskiSearch.setPromptText("Compound name");
        ghoseSearch.setPromptText("Compound name");
        bbbSearch.setPromptText("Compound name");
        wQEDSearch.setPromptText("Compound name");


        Summ.setEditable(true);
        lipinskiTable.setEditable(true);
        ghoseTable.setEditable(true);
        bbbTable.setEditable(true);
        qedTableView.setEditable(true);

        summarySearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                summaryDynamicSearch();
            }
        });

        lipinskiSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lipinskiDynamicSearch();
            }
        });

        ghoseSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ghoseDynamicSearch();
            }
        });

        bbbSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bbbDynamicSearch();
            }
        });

        wQEDSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                wQEDDynamicSearch();
            }
        });

        DatabaseConnection db = new DatabaseConnection();

        try {
            Connection con = DatabaseConnection.connect();
            buildData();
            buildLipinskiTable();
            buildGhoseTable();
            buildBBBTable();
            buildQEDTable();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } catch (SQLException ce) {
            ce.printStackTrace();
        }


    }

    private ObservableList<SummaryTable> data;


    public void buildData() throws ClassNotFoundException {


        data = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT Compound.CompoundID, Compound.Compound_name, Molecular_Mass.Formula, Molecular_Mass.Molecular_Mass,\n" +
                    "Chemical_properties.Number_Acidic_Groups, Chemical_properties.Rotatable_bonds, Chemical_properties.Ring_Count, \n" +
                    "Chemical_properties.Molar_Refractivity, Chemical_properties.Surface_area\n" +
                    "FROM COMPOUND \n" +
                    " INNER JOIN Molecular_Mass ON Compound.CompoundID = Molecular_Mass.CompoundID\n" +
                    " INNER JOIN Chemical_properties ON Compound.CompoundID = Chemical_properties.CompoundID";


            ResultSet rs = DatabaseConnection.connect().createStatement().executeQuery(SQL);


            while (rs.next()) {

                SummaryTable st = new SummaryTable();

                st.setCompoundID(rs.getInt("compoundID"));
                st.setCompoundName(rs.getString("Compound_name"));
                st.setFormula(rs.getString("Formula"));
                st.setMolecularMass(rs.getDouble("Molecular_Mass"));
                st.setAcidicGroups(rs.getInt("Number_Acidic_Groups"));
                st.setRotatableBonds(rs.getInt("Rotatable_Bonds"));
                st.setRingCount(rs.getInt("Ring_Count"));
                st.setMolecularRefractivity(rs.getDouble("Molar_Refractivity"));
                st.setSurfaceArea(rs.getDouble("Surface_Area"));

                data.add(st);


            }

            Summ.setItems(data);

            System.out.println(data.size());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private ObservableList<LipinskiTable> lipinksiData;

    public void buildLipinskiTable() throws ClassNotFoundException, SQLException {

        lipinksiData = FXCollections.observableArrayList();

        String SQL = " SELECT Compound.CompoundID, Compound.Compound_name, Hydrogen_Ad.Hydrogen_acceptor, Hydrogen_ad.Hydrogen_donor, Molecular_Mass.Molecular_Mass, LOGP.LOGP\n" +
                " FROM Compound\n" +
                "\n" +
                " INNER JOIN Hydrogen_Ad ON Compound.CompoundID = Hydrogen_Ad.CompoundID\n" +
                " INNER JOIN Molecular_Mass ON Compound.CompoundID = Molecular_Mass.CompoundID\n" +
                " INNER JOIN LOGP ON Compound.CompoundID = LogP.Compound_ID\n" +
                " INNER JOIN LIPINSKI ON Compound.CompoundID = Lipinski.CompoundID\n" +
                " WHERE Molecular_Mass.Molecular_Mass < 500" +
                " AND Hydrogen_Ad.Hydrogen_acceptor <= 10 " +
                " AND Hydrogen_Ad.Hydrogen_donor <= 5" +
                " AND LOGP.LOGP < 5";


        ResultSet rs = DatabaseConnection.connect().createStatement().executeQuery(SQL);

        while (rs.next()) {

            LipinskiTable lipinskiTable = new LipinskiTable();

            lipinskiTable.setCompoundID(rs.getInt("compoundID"));
            lipinskiTable.setCompoundName(rs.getString("Compound_name"));
            lipinskiTable.setHydrogenAcceptor(rs.getInt("Hydrogen_acceptor"));
            lipinskiTable.setHydrogenDonor(rs.getInt("Hydrogen_donor"));
            lipinskiTable.setMolecularMass(rs.getDouble("Molecular_Mass"));
            lipinskiTable.setLogP(rs.getDouble("LOGP"));

            lipinksiData.add(lipinskiTable);


        }
        lipinskiTable.setItems(lipinksiData);


    }

    private ObservableList<GhoseTable> ghoseData;

    public void buildGhoseTable() throws ClassNotFoundException, SQLException {

        ghoseData = FXCollections.observableArrayList();

        String SQL = "SELECT Compound.CompoundID, Compound.Compound_name, LOGP.LOGP,  Molecular_Mass.Molecular_Mass, Chemical_properties.Molar_Refractivity, total_atom.TotalAtom\n" +
                "FROM Compound\n" +
                "\n" +
                "INNER JOIN LogP ON Compound.CompoundID = LogP.Compound_ID\n" +
                "INNER JOIN Molecular_Mass ON Compound.CompoundID = Molecular_Mass.CompoundID\n" +
                "INNER JOIN Chemical_properties ON Compound.CompoundID = Chemical_properties.CompoundID\n" +
                "INNER JOIN total_atom ON Compound.CompoundID = total_atom.CompoundID\n" +
                "INNER JOIN Ghose_filter ON Compound.CompoundID = Ghose_filter.CompoundID\n" +
                "\n" +
                "WHERE Molecular_Mass.Molecular_Mass BETWEEN 160 AND 480 AND " +
                " Chemical_properties.Molar_Refractivity BETWEEN 40 AND 130 AND " +
                "total_atom.TotalAtom BETWEEN 20 AND 70";


        ResultSet rs = DatabaseConnection.connect().createStatement().executeQuery(SQL);

        while (rs.next()) {

            GhoseTable ghoseTable = new GhoseTable();

            ghoseTable.setCompoundID(rs.getInt("compoundID"));
            ghoseTable.setCompoundName(rs.getString("Compound_name"));
            ghoseTable.setLogP(rs.getDouble("LOGP"));
            ghoseTable.setMolecularMass(rs.getDouble("Molecular_Mass"));
            ghoseTable.setMolecularRefractivity(rs.getDouble("Molar_Refractivity"));
            ghoseTable.setTotalAtoms(rs.getInt("TotalAtom"));


            ghoseData.add(ghoseTable);


        }
        ghoseTable.setItems(ghoseData);


    }

    private ObservableList<bbbTable> bbbData;

    public void buildBBBTable() throws ClassNotFoundException, SQLException {

        bbbData = FXCollections.observableArrayList();

        String SQL = "\n" +
                "SELECT Compound.CompoundID, Compound.Compound_name, Total_Hbond.total, Molecular_Mass.Molecular_Mass, Chemical_properties.Number_Acidic_Groups\n" +
                "FROM Compound\n" +
                "\n" +
                "INNER JOIN Total_Hbond ON Compound.CompoundID = Total_Hbond.CompoundID\n" +
                "INNER JOIN Molecular_Mass ON Compound.CompoundID = Molecular_Mass.CompoundID\n" +
                "INNER JOIN Chemical_properties ON Compound.CompoundID = Chemical_properties.CompoundID\n" +
                "INNER JOIN bbb_likeness ON Compound.CompoundID = bbb_likeness.CompoundID\n" +
                "\n" +
                "WHERE Total_Hbond.total BETWEEN 8 AND 10 " +
                " AND Molecular_Mass.Molecular_Mass BETWEEN 400 AND 500 AND " +
                " Chemical_properties.Number_Acidic_Groups < 2";


        ResultSet rs = DatabaseConnection.connect().createStatement().executeQuery(SQL);

        while (rs.next()) {

            bbbTable bbbTable = new bbbTable();

            bbbTable.setCompoundID(rs.getInt("compoundID"));
            bbbTable.setCompoundName(rs.getString("Compound_name"));
            bbbTable.setHydrogenBonds(rs.getInt("Total"));
            bbbTable.setMolecularMass(rs.getDouble("Molecular_Mass"));
            bbbTable.setNumberAcidicGroup(rs.getInt("Number_Acidic_Groups"));


            bbbData.add(bbbTable);


        }
        bbbTable.setItems(bbbData);
    }


    private ObservableList<QEDTable> qedData;

    public void buildQEDTable() throws SQLException, ClassNotFoundException{
        qedData = FXCollections.observableArrayList();

        String SQL = "           SELECT Compound.CompoundID, Compound.Compound_name, Molecular_Mass.Molecular_Mass, QED.AlogP,  Hydrogen_Ad.Hydrogen_acceptor, Hydrogen_ad.Hydrogen_donor, Chemical_properties.Rotatable_bonds,\n" +
                "Chemical_properties.Surface_area, Chemical_properties.Ring_Count, QED.wQED\n" +
                "FROM COMPOUND\n" +
                "INNER JOIN Molecular_Mass ON Compound.CompoundID = Molecular_Mass.CompoundID\n" +
                "INNER JOIN Chemical_properties ON Compound.CompoundID = Chemical_properties.CompoundID\n" +
                "INNER JOIN Hydrogen_Ad ON Compound.CompoundID = Hydrogen_Ad.CompoundID\n" +
                "INNER JOIN QED ON Compound.CompoundID = QED.CompoundID                ";


        ResultSet rs = DatabaseConnection.connect().createStatement().executeQuery(SQL);

        while (rs.next()){

            QEDTable qedTableData = new QEDTable();

            qedTableData.setCompoundID(rs.getInt("compoundID"));
            qedTableData.setCompoundName(rs.getString("Compound_name"));
            qedTableData.setMolecularMass(rs.getDouble("Molecular_Mass"));
            qedTableData.setAlogP(rs.getDouble("AlogP"));
            qedTableData.setHydrogenAcceptor(rs.getInt("Hydrogen_acceptor"));
            qedTableData.setHydrogenDonor(rs.getInt("Hydrogen_donor"));
            qedTableData.setRotatableBonds(rs.getInt("Rotatable_Bonds"));
            qedTableData.setSurfaceArea(rs.getDouble("Surface_Area"));
            qedTableData.setRingCount(rs.getInt("Ring_Count"));
            qedTableData.setwQED(rs.getDouble("wQED"));


            qedData.add(qedTableData);





        }

        qedTableView.setItems(qedData);






    }

    public void summarybuttonShow(){


        System.out.println(Summ.getSelectionModel().getSelectedItems().get(0).getCompoundID());

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Compound.txt", true));


            bufferedWriter.write("CompoundID : " + Summ.getSelectionModel().getSelectedItems().get(0).getCompoundID() + "\n"
                    + "Compound Name : " + Summ.getSelectionModel().getSelectedItems().get(0).getCompoundName() + "\n"
                    + "Formula: " + Summ.getSelectionModel().getSelectedItems().get(0).getFormula() + "\n"
                    + "Molecular Mass : " + Summ.getSelectionModel().getSelectedItems().get(0).molecularMassProperty() + "\n"
                    + "Number of Acidic Groups: " + Summ.getSelectionModel().getSelectedItems().get(0).getAcidicGroups() + "\n" +
                    "Number of Rotatable Bonds:  " + Summ.getSelectionModel().getSelectedItems().get(0).getAcidicGroups() + "\n" +
                    "Ring Count :  " + Summ.getSelectionModel().getSelectedItems().get(0).getAcidicGroups() + "\n" +
                    "Molecular Refractivity : " + Summ.getSelectionModel().getSelectedItems().get(0).getAcidicGroups() + "\n" +
                    "Surface Area :  " + Summ.getSelectionModel().getSelectedItems().get(0).getAcidicGroups() + "\n");


            bufferedWriter.newLine();

            bufferedWriter.flush();

        }

        catch(IOException e){
            e.printStackTrace();
        }



    /*
        SummaryTable summaryTable = new SummaryTable();

        List<List<String>> arrayList = new ArrayList<>();

        for(int i = 0; i < 2; i++){
            summaryTable = Summ.getItems().get(i);
            arrayList.add(new ArrayList<>());
            arrayList.get(i).add(""+ summaryTable.getCompoundID());
            arrayList.get(i).add("" + summaryTable.getCompoundName());
            arrayList.get(i).add("" + summaryTable.getFormula());

        }

        for(int i = 0; i < arrayList.size(); i++){
            for(int j = 0; j < arrayList.get(i).size(); j++){
                System.out.println(arrayList.get(i).get(j));

            }
        }*/

    }



    public void lipinskiButtonShow() {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Lipinski_Compliant_Compound.txt", true));


            bufferedWriter.write("CompoundID : " + lipinskiTable.getSelectionModel().getSelectedItems().get(0).getCompoundID() + "\n"
                    + "Compound Name : " + lipinskiTable.getSelectionModel().getSelectedItems().get(0).getCompoundName() + "\n"
                    + "Hydrogen Acceptors : " + lipinskiTable.getSelectionModel().getSelectedItems().get(0).getHydrogenAcceptor() + "\n"
                    + "Hydrogen Donor : " + lipinskiTable.getSelectionModel().getSelectedItems().get(0).getHydrogenDonor() + "\n"
                    + "Molecular Mass " + lipinskiTable.getSelectionModel().getSelectedItems().get(0).getMolecularMass() + "\n" +
                    "LogP :  " + lipinskiTable.getSelectionModel().getSelectedItems().get(0).getLogP() + "\n");



            bufferedWriter.newLine();

            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ghoseButtonShow() {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Ghose_Compliant_Compound.txt", true));


            bufferedWriter.write("CompoundID : " + ghoseTable.getSelectionModel().getSelectedItems().get(0).getCompoundID() + "\n"
                    + "Compound Name : " + ghoseTable.getSelectionModel().getSelectedItems().get(0).getCompoundName() + "\n"
                    + "LogP : " + ghoseTable.getSelectionModel().getSelectedItems().get(0).getLogP() + "\n"
                    + "Molecular Mass : " + ghoseTable.getSelectionModel().getSelectedItems().get(0).getMolecularMass() + "\n"
                    + "Molecular Refractivity " + ghoseTable.getSelectionModel().getSelectedItems().get(0).getMolecularRefractivity() + "\n" +
                    "Total Atoms :  " + ghoseTable.getSelectionModel().getSelectedItems().get(0).getTotalAtoms() + "\n");



            bufferedWriter.newLine();

            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bbbButtonShow() {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("BBB_Compliant_Compound.txt", true));


            bufferedWriter.write("CompoundID : " + bbbTable.getSelectionModel().getSelectedItems().get(0).getCompoundID() + "\n"
                    + "Compound Name : " + bbbTable.getSelectionModel().getSelectedItems().get(0).getCompoundName() + "\n"
                    + "Number of Hydrogen Bonds " + bbbTable.getSelectionModel().getSelectedItems().get(0).getHydrogenBonds() + "\n"
                    + "Molecular Mass : " + bbbTable.getSelectionModel().getSelectedItems().get(0).getMolecularMass() + "\n"
                    + "Number of Acidic Groups " + bbbTable.getSelectionModel().getSelectedItems().get(0).getNumberAcidicGroup() + "\n");



            bufferedWriter.newLine();

            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void wQEDButtonShow() {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("wQED_Compliant_Compound.txt", true));


            bufferedWriter.write("CompoundID : " + qedTableView.getSelectionModel().getSelectedItems().get(0).getCompoundID() + "\n"
                    + "Compound Name : " + qedTableView.getSelectionModel().getSelectedItems().get(0).getCompoundName() + "\n"
                    + "Molecular Mass : " + qedTableView.getSelectionModel().getSelectedItems().get(0).getMolecularMass() + "\n"
                    + "AlogP  : " + qedTableView.getSelectionModel().getSelectedItems().get(0).getAlogP() + "\n"
                    + "Number of Hydrogen Acceptors : " + qedTableView.getSelectionModel().getSelectedItems().get(0).getHydrogenAcceptor() + "\n"
            + "Number of Hydrogen Donors : " + qedTableView.getSelectionModel().getSelectedItems().get(0).getHydrogenDonor() + "\n"
            + "Number of Rotatable Bonds : " + qedTableView.getSelectionModel().getSelectedItems().get(0).getRotatableBonds() + "\n"
            + "Surface Area :  " + qedTableView.getSelectionModel().getSelectedItems().get(0).getSurfaceArea() + "\n"
                    + "Ring Count :  " + qedTableView.getSelectionModel().getSelectedItems().get(0).getRingCount() + "\n"
                    + " weighted QED : " + qedTableView.getSelectionModel().getSelectedItems().get(0).getwQED() + "\n");

            bufferedWriter.newLine();

            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void summaryDynamicSearch(){

        FilteredList<SummaryTable> summaryFilter = new FilteredList(data, p -> true);

        Summ.setItems(summaryFilter);

        summarySearch.textProperty().addListener((obs, oldValue, newValue) -> {
            summaryFilter.setPredicate(p -> p.getCompoundName().toLowerCase().contains(newValue.toLowerCase().trim()));
        });

    }

    public void lipinskiDynamicSearch(){

        FilteredList<LipinskiTable> lipinskiFilter = new FilteredList(lipinksiData, p -> true);

        lipinskiTable.setItems(lipinskiFilter);

        lipinskiSearch.textProperty().addListener((obs, oldValue, newValue) -> {
            lipinskiFilter.setPredicate(p -> p.getCompoundName().toLowerCase().contains(newValue.toLowerCase().trim()));
        });

    }

    public void ghoseDynamicSearch(){

        FilteredList<GhoseTable> ghoseFilter = new FilteredList(ghoseData, p -> true);

        ghoseTable.setItems(ghoseFilter);

        ghoseSearch.textProperty().addListener((obs, oldValue, newValue) -> {
            ghoseFilter.setPredicate(p -> p.getCompoundName().toLowerCase().contains(newValue.toLowerCase().trim()));
        });

    }

    public void bbbDynamicSearch(){

        FilteredList<bbbTable> bbbFilter = new FilteredList(bbbData, p -> true);

        bbbTable.setItems(bbbFilter);


        ghoseSearch.textProperty().addListener((obs, oldValue, newValue) -> {
            bbbFilter.setPredicate(p -> p.getCompoundName().toLowerCase().contains(newValue.toLowerCase().trim()));
        });

    }

    public void wQEDDynamicSearch(){

        FilteredList<QEDTable> qedFilter = new FilteredList(qedData, p -> true);

        qedTableView.setItems(qedFilter);

        wQEDSearch.textProperty().addListener((obs, oldValue, newValue) -> {
            qedFilter.setPredicate(p -> p.getCompoundName().toLowerCase().contains(newValue.toLowerCase().trim()));
        });
    }

}
