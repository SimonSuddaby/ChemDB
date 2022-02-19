import chemaxon.naming.util.IO;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import jdk.swing.interop.SwingInterOpUtils;
import org.antlr.v4.misc.OrderedHashMap;
import org.checkerframework.checker.units.qual.A;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//static holder class for IO Operations

public class Entry {

    private static ArrayList<String> compoundOut;
    private static ArrayList<Atom> atomOut;
    private static ArrayList<Bond> bondList;

    public Entry(ArrayList<String> compoundOut, ArrayList<Atom> atomOut, ArrayList<Bond> bondList) {
        this.compoundOut = compoundOut;
        this.atomOut = atomOut;
        this.bondList = bondList;
    }

    public ArrayList<String> getCompoundOut() {
        return compoundOut;
    }

    public ArrayList<Atom> getAtomOut() {
        return atomOut;
    }

    public ArrayList<Bond> getBondList() {
        return bondList;
    }

    public static void writeCompoundToFile(String fileLoc) throws IOException {

        String line;
     /*   Pattern pattern = Pattern.compile("\\$$$$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(Files.readString(Path.of(fileLoc)));*/

        BufferedReader br = new BufferedReader(new FileReader(fileLoc));
        BufferedWriter bw = new BufferedWriter(new FileWriter("compounds_for_DB.csv"));

        List<String> compound = new ArrayList<>();
        ArrayList<String> atom = new ArrayList<>();
        ArrayList<Double> atoms = new ArrayList<>();
        ArrayList<String> bond = new ArrayList<>();
        ArrayList<Double> bonds = new ArrayList<>();

        while ((line = br.readLine()) != null) {

            if (line.startsWith("(")) {
                compound.add(line);
            }

            if (line.contains("999")) {
                atom.add(line);
                bond.add(line);
            }

        }


        List<String> distinctCompound = compound.stream()
                .distinct()
                .collect(Collectors.toList());

        for (String s : atom) {

            String[] st = s.split(" ");
            String[] clean = Arrays.stream(st).filter(c -> !c.isEmpty()).toArray(String[]::new);

            for (String sn : clean) {
                atoms.add(Double.parseDouble(clean[0]));
                bonds.add(Double.parseDouble(clean[1]));

            }
        }


        atoms.removeIf(i -> i % 1 != 0);

        int skip = 8;
        int limit = atoms.size() / skip + Math.min(atoms.size() % skip, 1);

        List<Double> atomList = Stream.iterate(0, i -> i + skip)
                .limit(limit)
                .map(atoms::get)
                .collect(Collectors.toList());


        atom.clear();

        for (Double d : atomList) {
            atom.add("" + d.intValue());
        }

        bonds.removeIf(i -> i % 1 != 0);
        //System.out.println(bonds);


        List<Double> bondList = Stream.iterate(0, i -> i + skip)
                .limit(limit)
                .map(bonds::get)
                .collect(Collectors.toList());


        bond.clear();

        for (Double di : bondList) {
            bond.add("" + di.intValue());
        }

        int compoundID = 1;

        for (int i = 0; i < 100; i++) {
            bw.write(compoundID + "," + "'" + distinctCompound.get(i) + "'" + "," + atom.get(i) + "," + bond.get(i));
            bw.newLine();
            bw.flush();
            compoundID++;
        }

        bw.close();


    }


    public static void writeAtomToFile(String fileLoc) {


        HashMap<Integer, Atom> atomBlock = new HashMap<>();
        OrderedHashMap<Integer, BondList> bondBlock = new OrderedHashMap<>();
        int compoundID = 1;

        ArrayList<Atom> totalAtom = new ArrayList<>();
        ArrayList<Bond> totalBond = new ArrayList<>();

        ArrayList<Integer> atomsPerMol = new ArrayList<>();


        AtomList atomList = null;

        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLoc));
            BufferedWriter atomWriter = new BufferedWriter(new FileWriter("atomtable_for_DB.csv"));
            BufferedWriter bondWriter = new BufferedWriter(new FileWriter("bondtable_for_DB.csv"));
            BufferedWriter atomPerMol = new BufferedWriter(new FileWriter("total_atoms.txt"));


            while ((line = br.readLine()) != null) {

                BondList bondList = new BondList();


                if (line.contains("999 V2000")) {

                    StringTokenizer tr = new StringTokenizer(line);
                    int numberOfAtoms = Integer.parseInt(tr.nextToken());
                    atomsPerMol.add(numberOfAtoms);
                    int numberOfBonds = Integer.parseInt(tr.nextToken());
                    //System.out.println(numberOfAtoms);
                    atomList = new AtomList();
                    for (int i = 0; i < numberOfAtoms; i++) {


                        StringTokenizer st = new StringTokenizer(br.readLine());
                        String xString = st.nextToken();
                        Double xDouble = Double.valueOf(xString);
                        double xcoord = xDouble.doubleValue();

                        String yString = st.nextToken();
                        Double yDouble = Double.valueOf(yString);
                        double ycoord = yDouble.doubleValue();

                        String zString = st.nextToken();
                        Double zDouble = Double.valueOf(zString);
                        double zcoord = zDouble.doubleValue();

                        String atomType = st.nextToken();

                        //System.out.println("X: " + xcoord + " Y: " + ycoord + " Z: " + zcoord);

                        Atom atom = new Atom(i + 1, atomType, xcoord, ycoord, zcoord, compoundID);
                        atomList.addAtom(atom);
                        totalAtom.add(atom);



                    }

                    for (int j = 0; j < numberOfBonds; j++) {
                        StringTokenizer bondToken = new StringTokenizer(br.readLine());

                            String a1String = bondToken.nextToken();
                            //System.out.println(a1String);
                            int atom1number = Integer.parseInt(a1String);

                            String a2String = bondToken.nextToken();
                            int atom2number = Integer.parseInt(a2String);

                            String oString = bondToken.nextToken();
                            int order = Integer.parseInt(oString);

                            Bond bond = new Bond(j + 1, atom1number, atom2number, order, compoundID);

                            //System.out.println(bond.getAtom1number());
                            totalBond.add(bond);


                        }

                    compoundID++;

                }


            }

            System.out.println(atomsPerMol);


            for(Integer i : atomsPerMol){
                atomPerMol.write(""+ i);
                atomPerMol.newLine();
                atomPerMol.flush();
            }


            for(Atom atom : totalAtom){
                atomWriter.write(atom.getCompoundID() + "," + atom.getAtomNumber() + "," + atom.getAtomType() + ","
                        + atom.getXcoord() + "," + atom.getYcoord() + "," + atom.getZcoord() + "\n");

            }
            atomWriter.flush();

            for(Bond bond : totalBond){
                bondWriter.write(bond.getCompoundID() + "," + bond.getBondNumber() + "," + bond.getAtom1number() + "," + bond.getAtom2number() + "," + bond.getBondOrder());
                bondWriter.newLine();
            }
            bondWriter.flush();


            System.out.println(totalBond);

        } catch (IOException e) {
            e.printStackTrace();

        }
        //System.out.println(bondBlock);




    }






    public static void writeBondToFile(String fileLoc) {


        BondList bondList = new BondList();
        String line;
        int numberOfBonds = 0;
        int compoundID = 1;
        OrderedHashMap<Integer,BondList> bondBlock = new OrderedHashMap<>();

        int testCounter = 0;


        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLoc));
            BufferedWriter bw = new BufferedWriter(new FileWriter("bondtable_for_DB.csv"));


            while ((line = br.readLine()) != null) {


                if(line.contains("999 V2000")) {
                    StringTokenizer tr = new StringTokenizer(line);
                    int numberOfAtoms = Integer.parseInt(tr.nextToken());
                    numberOfBonds = Integer.parseInt(tr.nextToken());

                }

                if (line.startsWith("")) {
                    testCounter++;
                    for (int j = 0; j < numberOfBonds; j++) {
                        StringTokenizer st = new StringTokenizer(line);

                        String a1String = st.nextToken();
                        //System.out.println(a1String);
                        int atom1number = Integer.parseInt(a1String);

                        String a2String = st.nextToken();
                        int atom2number = Integer.parseInt(a2String);

                        String oString = st.nextToken();
                        int order = Integer.parseInt(oString);


                        Bond bd = new Bond(j+1, atom1number, atom2number, order, compoundID);
                        bondList.addBond(bd);


                    }
                }

                bondBlock.put(compoundID, bondList);


                compoundID++;



            }

        }
        catch(IOException e){
            e.printStackTrace();
        }

        //System.out.println(bondBlock.size());
        System.out.println(testCounter);
    }



}
