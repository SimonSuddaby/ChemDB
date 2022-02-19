package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LipinskiTable {

    private SimpleIntegerProperty compoundID = new SimpleIntegerProperty();
    private SimpleStringProperty compoundName = new SimpleStringProperty();
    private SimpleIntegerProperty hydrogenAcceptor = new SimpleIntegerProperty();
    private SimpleIntegerProperty hydrogenDonor = new SimpleIntegerProperty();
    private SimpleDoubleProperty molecularMass = new SimpleDoubleProperty();
    private SimpleDoubleProperty logP = new SimpleDoubleProperty();


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

    public int getHydrogenAcceptor() {
        return hydrogenAcceptor.get();
    }

    public SimpleIntegerProperty hydrogenAcceptorProperty() {
        return hydrogenAcceptor;
    }

    public void setHydrogenAcceptor(int hydrogenAcceptor) {
        this.hydrogenAcceptor.set(hydrogenAcceptor);
    }

    public int getHydrogenDonor() {
        return hydrogenDonor.get();
    }

    public SimpleIntegerProperty hydrogenDonorProperty() {
        return hydrogenDonor;
    }

    public void setHydrogenDonor(int hydrogenDonor) {
        this.hydrogenDonor.set(hydrogenDonor);
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
}
