package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SummaryTable {

    private SimpleIntegerProperty compoundID = new SimpleIntegerProperty();
    private SimpleStringProperty compoundName = new SimpleStringProperty();
    private SimpleStringProperty formula = new SimpleStringProperty();
    private SimpleDoubleProperty molecularMass = new SimpleDoubleProperty();
    private SimpleIntegerProperty acidicGroups = new SimpleIntegerProperty();
    private SimpleIntegerProperty rotatableBonds = new SimpleIntegerProperty();
    private  SimpleIntegerProperty ringCount = new SimpleIntegerProperty();
    private SimpleDoubleProperty molecularRefractivity = new SimpleDoubleProperty();
    private SimpleDoubleProperty surfaceArea = new SimpleDoubleProperty();


    public int getCompoundID() {
        return compoundID.get();
    }

    public SimpleIntegerProperty compoundIDProperty() {
        return compoundID;
    }

    public String getCompoundName() {
        return compoundName.get();
    }

    public SimpleStringProperty compoundNameProperty() {
        return compoundName;
    }

    public String getFormula() {
        return formula.get();
    }

    public SimpleStringProperty formulaProperty() {
        return formula;
    }

    public double getMolecularMass() {
        return molecularMass.get();
    }

    public SimpleDoubleProperty molecularMassProperty() {
        return molecularMass;
    }

    public int getAcidicGroups() {
        return acidicGroups.get();
    }

    public SimpleIntegerProperty acidicGroupsProperty() {
        return acidicGroups;
    }

    public int getRotatableBonds() {
        return rotatableBonds.get();
    }

    public SimpleIntegerProperty rotatableBondsProperty() {
        return rotatableBonds;
    }

    public int getRingCount() {
        return ringCount.get();
    }

    public SimpleIntegerProperty ringCountProperty() {
        return ringCount;
    }

    public double getMolecularRefractivity() {
        return molecularRefractivity.get();
    }

    public SimpleDoubleProperty molecularRefractivityProperty() {
        return molecularRefractivity;
    }

    public double getSurfaceArea() {
        return surfaceArea.get();
    }

    public SimpleDoubleProperty surfaceAreaProperty() {
        return surfaceArea;
    }

    public void setCompoundID(int compoundID) {
        this.compoundID.set(compoundID);
    }

    public void setCompoundName(String compoundName) {
        this.compoundName.set(compoundName);
    }

    public void setFormula(String formula) {
        this.formula.set(formula);
    }

    public void setMolecularMass(double molecularMass) {
        this.molecularMass.set(molecularMass);
    }

    public void setAcidicGroups(int acidicGroups) {
        this.acidicGroups.set(acidicGroups);
    }

    public void setRotatableBonds(int rotatableBonds) {
        this.rotatableBonds.set(rotatableBonds);
    }

    public void setRingCount(int ringCount) {
        this.ringCount.set(ringCount);
    }

    public void setMolecularRefractivity(double molecularRefractivity) {
        this.molecularRefractivity.set(molecularRefractivity);
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea.set(surfaceArea);
    }
}
