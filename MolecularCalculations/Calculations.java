import chemaxon.calculations.*;
import chemaxon.descriptors.scalars.LogP;
import chemaxon.formats.MolImporter;
import chemaxon.jep.function.In;
import chemaxon.marvin.calculations.*;
import chemaxon.marvin.plugin.PluginException;
import chemaxon.struc.Molecule;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.poi.hpsf.Array;
import org.checkerframework.checker.units.qual.A;
import org.jcamp.math.Range;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {

    private static String FILE_PATH = "./molecules1.sdf";
    private TreeMap<Integer, Boolean> lipinskiValues = new TreeMap<>();
    private TreeMap<Integer, Boolean> ghoseFilter = new TreeMap<>();
    private TreeMap<Integer, Boolean> bbbLikeness = new TreeMap<>();
    private TreeMap<Integer, Boolean> wQED = new TreeMap<>();

    //Hbond donor acceptor

    private static ArrayList<Integer> HbondAcceptor = new ArrayList<>();
    private static ArrayList<Integer> HbondDonor = new ArrayList<>();

    private static ArrayList<Double> massList = new ArrayList<>();

    private static SortedMap<Integer, Double> LogPmap = new TreeMap<>();
    private static ArrayList<Double> refractivityList = new ArrayList<>();
    private static ArrayList<Integer> totalHbond = new ArrayList<>();
    private static ArrayList<Integer> acidicGroup = new ArrayList<>();


    public static void main(String[] args) throws IOException, PluginException {

        writeOutHAD();
        calculateMolecularMass();
        logPCalculator();
        TPSAcalculator();

        lipinskyCalculator();
        ghoseFilterCalculator();
        bbbLikeness();

    }


    public static SortedMap<Integer, Double> logPCalculator() throws IOException, PluginException {
        // fill parameters
        Properties params = new Properties();
        params.put("type", "logP");

        // create plugin
        logPPlugin plugin = new logPPlugin();

        // set logP calculation method
        plugin.setlogPMethod(logPPlugin.METHOD_WEIGHTED);

        // set method weights
        plugin.setWeightOfMethods(1, 2, 1, 0);

        // set parameters
        plugin.setCloridIonConcentration(0.2);
        plugin.setNaKIonConcentration(0.2);

        // set result types
        plugin.setUserTypes("logPTrue,logPMicro");

        // for each input molecule run the calculation and display the results
        System.out.println("molecule\tlogP");
        MolImporter mi = new MolImporter(".\molecules1.sdf");
        Molecule target = null;
        int compoundID = 1;
        while ((target = mi.read()) != null) {

            // set the input molecule
            plugin.setMolecule(target);

            // run the calculation
            plugin.run();

            // get the overal logP value
            double logp = plugin.getlogPTrue();
            double logpm = plugin.getlogPMicro();

            LogPmap.put(compoundID, logp);

            System.out.println("True logP : " + logp);
            System.out.println("Micro logP: " + logpm);
            System.out.println();
            compoundID++;



            }

        mi.close();

   return LogPmap;


    }

    public static void writeOutLogP() throws IOException, PluginException {

        SortedMap<Integer, Double> writeOut = logPCalculator();

        BufferedWriter br = new BufferedWriter(new FileWriter("LogP.csv"));

        for (Map.Entry<Integer, Double> entry : writeOut.entrySet()) {
            br.write(entry.getKey() + "," + entry.getValue());
            br.newLine();

        }
        br.flush();


    }

    public static ArrayList<Integer> writeOutHAD() throws IOException, PluginException {

        //FIRST 100 - ACCEPTORS, LAST 100 - DONORS

        int compoundIdentifier = 1;

        BufferedWriter bw = new BufferedWriter(new FileWriter("HAD.csv"));
        BufferedWriter totHBond = new BufferedWriter(new FileWriter("TotalHbond.csv"));

        ArrayList<Molecule> test = new ArrayList<>();

        int molecularAcceptorCount = 0;
        int molecularDonorCount = 0;

        // create plugin
        HBDAPlugin plugin = new HBDAPlugin();

        // set plugin parameters
        plugin.setDoublePrecision(2);
        plugin.setpHLower(2.0);
        plugin.setpHUpper(12.0);
        plugin.setpHStep(2.0);

        // optional: take major microspecies at pH=7.4
        // skip this if you want to calculate HBDA for the input molecule as it is
        plugin.setpH(7.4);

        // read target molecule
        MolImporter mi = new MolImporter(".\molecules1.sdf");


        Molecule target = null;

        while ((target = mi.read()) != null) {

            plugin.setMolecule(target);
            plugin.run();
            test.add(target);

            molecularAcceptorCount = plugin.getAcceptorCount();
            molecularDonorCount = plugin.getDonorCount();
            // without multiplicity
            int molecularAcceptorAtomCount = plugin.getAcceptorAtomCount();
            int molecularDonorAtomCount = plugin.getDonorAtomCount();

            bw.write(compoundIdentifier + "," + molecularAcceptorCount + "," + molecularDonorCount + "," +
                    molecularAcceptorAtomCount + "," + molecularDonorAtomCount);
            bw.newLine();
            bw.flush();

            compoundIdentifier++;

            HbondAcceptor.add(molecularAcceptorCount);
            HbondDonor.add(molecularDonorCount);



            }
        for(int i = 0; i < HbondAcceptor.size(); i++){
            int totalBond = HbondAcceptor.get(i) + HbondDonor.get(i);
            totalHbond.add(totalBond);

            totHBond.write(i + 1 + "," + totalBond);

            totHBond.newLine();
            totHBond.flush();

        }

        mi.close();

        System.out.println(HbondAcceptor);
        System.out.println(HbondDonor);

        return HbondAcceptor;

    }


    public static ArrayList<Double> calculateMolecularMass() throws IOException, PluginException{

        MolImporter mi = new MolImporter(FILE_PATH);
        Molecule target = null;

        ElementalAnalyserPlugin plugin = new ElementalAnalyserPlugin();

        Properties params = new Properties();
        params.put("precision", "3");
        plugin.setParameters(params);

        int compoundIdentifier = 1;
        BufferedWriter bw = new BufferedWriter(new FileWriter("molecular_mass.csv"));
        BufferedWriter compositionWriter = new BufferedWriter(new FileWriter("composition.csv"));

        while((target = mi.read()) != null){

            plugin.setMolecule(target);
            plugin.run();


            double exactMass = plugin.getExactMass();
            double mass = plugin.getMass();
            int atomCount1 = plugin.getAtomCount(8); // oxygen atom count
            int atomCount2 = plugin.getAtomCount(8, 0); // non-isotope oxygen count
            int atomCount3 = plugin.getAtomCount(8, 16); // oxygen isotope count with massno=16
            String formula = plugin.getFormula();
            String isotopeFormula = plugin.getIsotopeFormula();
            String composition = plugin.getComposition();
            String isotopeComposition = plugin.getIsotopeComposition();

            bw.write(compoundIdentifier + "," + mass + "," + formula);
            compositionWriter.write(compoundIdentifier + "," + composition);

            bw.newLine();
            compositionWriter.newLine();

            compoundIdentifier++;

            bw.flush();
            compositionWriter.flush();

            massList.add(mass);




        }
        mi.close();

        // create plugin


        // set plugin parameters

        // set target molecule


        // run the calculation


        // get results

        return massList;

    }


        public static TreeMap<Integer, Integer> lipinskyCalculator() throws IOException, PluginException {



            //if it meets lipinsky criteria, put true, else false
            // no greater than 5 Hbond donors
            // no more than 10 hbond acceptors
            //MM less than 500
            //LogP does not exceed 5

            BufferedWriter bw = new BufferedWriter(new FileWriter("Lipinski_TF.csv"));

            TreeMap<Integer, Integer> lipinksiMap = new TreeMap<>();

            int compoundID = 1;

            for(int i = 0; i <= 99; i++) {
                if ((HbondAcceptor.get(i) <= 10) && (HbondDonor.get(i) <= 5) && (massList.get(i) < 500) && (LogPmap.get(i + 1) <= 5)) {
                    System.out.println(HbondAcceptor.get(i) + HbondDonor.get(i) + massList.get(i) + LogPmap.get(i + 1));
                    lipinksiMap.put(compoundID, 1);
                } else {
                    lipinksiMap.put(i, 0);

                }
            }
                lipinksiMap.put(100, 1);

            for (Map.Entry<Integer, Integer> entry : lipinksiMap.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
                bw.flush();
            }

            return lipinksiMap;

        }

        public static void ghoseFilterCalculator() throws IOException {

        //logp between -0.4 and 5.6
            // MW between 160 and 480 inclusive
            //molecular refractivity between 40 and 130
            //total atoms between 20 and 70

            BufferedWriter bw = new BufferedWriter(new FileWriter("ghose_filter.csv"));
            BufferedWriter totalAtom = new BufferedWriter(new FileWriter("totalAtom.csv"));

        TreeMap<Integer, Integer> ghoseMap = new TreeMap<>();

        int[] atomsInMolecule = {22, 43, 27, 25, 21, 14, 25, 11, 17, 16, 13, 45, 17, 13, 11, 11, 12, 12, 20, 18,
                18, 13, 15, 23, 27, 23, 12, 13, 16, 15, 20, 8, 11, 35, 19, 45, 13, 23, 30, 23, 22, 19, 20, 16, 14, 26, 27, 28, 22, 16,
                23, 24, 18, 11, 21, 12, 16, 23, 22, 43, 28, 24, 23, 29, 35, 33, 34, 31, 39, 25, 11, 21, 23, 15, 38, 20, 21, 27,
                18, 22, 9, 20, 17, 24, 28, 33, 22, 25, 15, 22, 11, 26, 16, 18, 25, 15, 22, 15, 15, 18};

            for(int i = 1; i <= 99; i++){
                if((LogPmap.get(i) >= -0.4 && LogPmap.get(i) <= 5.6) && (massList.get(i) >= 160 && massList.get(i) <= 480)
                        && (refractivityList.get(i) >= 40 && refractivityList.get(i) <= 130) && (atomsInMolecule[i] >= 20 && atomsInMolecule[i] <= 70)){
                    ghoseMap.put(i, 1);
                }
                else{
                    ghoseMap.put(i, 0);
                }
                ghoseMap.put(100, 0);
            }

            for (Map.Entry<Integer, Integer> entry : ghoseMap.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
                bw.flush();
            }

            for(int i = 0; i < 100; i++){

                totalAtom.write(i + 1 + "," + atomsInMolecule[i]);
                totalAtom.newLine();
                totalAtom.flush();
            }

        }


        public static void bbbLikeness() throws IOException {

        //hbond 8-10
        //MW of 400-500
        //number of acid groups < 2

            TreeMap<Integer, Integer> bbbMap = new TreeMap<>();

            BufferedWriter bw = new BufferedWriter(new FileWriter("bbb_likeness.csv"));


            for(int i = 1; i <= 99; i++){
                if((totalHbond.get(i) >= 8 && totalHbond.get(i) <= 10) && (massList.get(i) >= 400 && massList.get(i) <= 500)
                        && (acidicGroup.get(i) == 0)){
                    bbbMap.put(i, 1);
                }

                else{
                    bbbMap.put(i, 0);
                }

                bbbMap.put(100, 0);
            }

            for (Map.Entry<Integer, Integer> entry : bbbMap.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
                bw.flush();
            }

            bw.close();
        }



        public static void TPSAcalculator() throws IOException, PluginException{
        // read input molecule
            MolImporter mi = new MolImporter(FILE_PATH);
            int compoundID = 1;
            Molecule target = null;
            TPSAPlugin plugin = new TPSAPlugin();
            TopologyAnalyserPlugin topologyPlugin = new TopologyAnalyserPlugin();
            RefractivityPlugin refractivityPlugin = new RefractivityPlugin();
            refractivityPlugin.setDoublePrecision(3);

            double area = 0;
            int ringCount = 0;
            int rotatableBond = 0;
            double refractivity = 0;

            pKaPlugin pKaPlugin = new pKaPlugin();

            pKaPlugin.setMaxIons(6);
            pKaPlugin.setBasicpKaLowerLimit(-5.0);
            pKaPlugin.setAcidicpKaUpperLimit(25.0);
            pKaPlugin.setpHLower(3.0); // for ms distr
            pKaPlugin.setpHUpper(6.0); // for ms distr
            pKaPlugin.setpHStep(1.0);  // for ms distr

            BufferedWriter bw = new BufferedWriter(new FileWriter("TPSA.csv"));
            BufferedWriter acidicGroupWriter = new BufferedWriter(new FileWriter("Acidic_Groups.csv"));

            while((target = mi.read()) != null){


                plugin.setpH(7.4);
                plugin.setMolecule(target);
                topologyPlugin.setMolecule(target);
                refractivityPlugin.setMolecule(target);
                pKaPlugin.setMolecule(target);

                plugin.run();
                topologyPlugin.run();
                refractivityPlugin.run();
                pKaPlugin.run();

                // get result
                area = plugin.getSurfaceArea();
                ringCount = topologyPlugin.getRingCount();
                rotatableBond = topologyPlugin.getRotatableBondCount();
                refractivity = refractivityPlugin.getRefractivity();

                refractivityList.add(refractivity);

                double[] acidicPkaValues = pKaPlugin.getMacropKaValues(chemaxon.marvin.calculations.pKaPlugin.ACIDIC);
                double[] basicPkaValues = pKaPlugin.getMacropKaValues(chemaxon.marvin.calculations.pKaPlugin.BASIC);

                System.out.println("Molecular refractivity: " + refractivity);

                if(acidicPkaValues != null){
                    acidicGroup.add(acidicPkaValues.length);
                    System.out.println(acidicGroup);
                }

                else{
                    acidicGroup.add(0);
                }

                bw.write(rotatableBond + "," + ringCount + "," + refractivity + "," + area );
                bw.newLine();
                bw.flush();

                compoundID++;

            }

            mi.close();

            for(int i = 0; i < 100; i++) {

                acidicGroupWriter.write( i + 1 + "," +acidicGroup.get(i));
                acidicGroupWriter.newLine();
                acidicGroupWriter.flush();

            }


            // create plugin


            // optional: take major microspecies at pH=7.4


            // set target molecule


            // run the calculation



        }


    }

