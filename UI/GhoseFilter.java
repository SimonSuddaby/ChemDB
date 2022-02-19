package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GhoseTable {

    private SimpleIntegerProperty compoundID = new SimpleIntegerProperty();
    private SimpleStringProperty compoundName = new SimpleStringProperty();
    private SimpleDoubleProperty molecularMass = new SimpleDoubleProperty();
    private SimpleDoubleProperty logP = new SimpleDoubleProperty();
    private SimpleDoubleProperty molecularRefractivity = new SimpleDoubleProperty();
    private SimpleIntegerProperty totalAtoms = new SimpleIntegerProperty();

    public int getCompoundID() {
        return compoundID.get();
    }

    public SimpleIntegerProperty compoundIDProperty() {
        return compoundID;
    }

    public void setCompoundID(int compoundID) {
        this.compoundID.set(compoundID);
    }

    public String getCompoundName() {
        return compoundName.get();
    }

    public SimpleStringProperty compoundNameProperty() {
        return compoundName;
    }

    public void setCompoundName(String compoundName) {
        this.compoundName.set(compoundName);
    }

    public double getMolecularMass() {
        return molecularMass.get();
    }

    public SimpleDoubleProperty molecularMassProperty() {
        return molecularMass;
    }

    public void setMolecularMass(double molecularMass) {
        this.molecularMass.set(molecularMass);
    }

    public double getLogP() {
        return logP.get();
    }

    public SimpleDoubleProperty logPProperty() {
        return logP;
    }

    public void setLogP(double logP) {
        this.logP.set(logP);
    }

    public double getMolecularRefractivity() {
        return molecularRefractivity.get();
    }

    public SimpleDoubleProperty molecularRefractivityProperty() {
        return molecularRefractivity;
    }

    public void setMolecularRefractivity(double molecularRefractivity) {
        this.molecularRefractivity.set(molecularRefractivity);
    }

    public int getTotalAtoms() {
        return totalAtoms.get();
    }

    public SimpleIntegerProperty totalAtomsProperty() {
        return totalAtoms;
    }

    public void setTotalAtoms(int totalAtoms) {
        this.totalAtoms.set(totalAtoms);
    }
}
