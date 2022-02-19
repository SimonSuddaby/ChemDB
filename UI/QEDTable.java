package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class QEDTable {

    private SimpleIntegerProperty compoundID = new SimpleIntegerProperty();
    private SimpleStringProperty compoundName = new SimpleStringProperty();
    private SimpleDoubleProperty molecularMass = new SimpleDoubleProperty();
    private SimpleDoubleProperty AlogP = new SimpleDoubleProperty();
    private SimpleIntegerProperty hydrogenAcceptor = new SimpleIntegerProperty();
    private SimpleIntegerProperty hydrogenDonor = new SimpleIntegerProperty();
    private SimpleIntegerProperty rotatableBonds = new SimpleIntegerProperty();
    private  SimpleIntegerProperty ringCount = new SimpleIntegerProperty();
    private SimpleDoubleProperty surfaceArea = new SimpleDoubleProperty();
    private SimpleDoubleProperty wQED = new SimpleDoubleProperty();

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

    public double getAlogP() {
        return AlogP.get();
    }

    public SimpleDoubleProperty alogPProperty() {
        return AlogP;
    }

    public void setAlogP(double alogP) {
        this.AlogP.set(alogP);
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

    public int getRotatableBonds() {
        return rotatableBonds.get();
    }

    public SimpleIntegerProperty rotatableBondsProperty() {
        return rotatableBonds;
    }

    public void setRotatableBonds(int rotatableBonds) {
        this.rotatableBonds.set(rotatableBonds);
    }

    public int getRingCount() {
        return ringCount.get();
    }

    public SimpleIntegerProperty ringCountProperty() {
        return ringCount;
    }

    public void setRingCount(int ringCount) {
        this.ringCount.set(ringCount);
    }

    public double getSurfaceArea() {
        return surfaceArea.get();
    }

    public SimpleDoubleProperty surfaceAreaProperty() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea.set(surfaceArea);
    }

    public double getwQED() {
        return wQED.get();
    }

    public SimpleDoubleProperty wQEDProperty() {
        return wQED;
    }

    public void setwQED(double wQED) {
        this.wQED.set(wQED);
    }
}
