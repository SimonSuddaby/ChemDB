import chemaxon.formats.MolImporter;
import chemaxon.marvin.calculations.HBDAPlugin;
import chemaxon.marvin.plugin.PluginException;
import chemaxon.struc.Molecule;

import java.io.IOException;

public class Tets {

    public static void main(String[] args) {



    }

    public static void testHAD() throws IOException, PluginException {

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
        MolImporter mi = new MolImporter("target.mol");
        Molecule mol = mi.read();
        mi.close();

        // set target molecule
        plugin.setMolecule(mol);

        // run the calculation
        plugin.run();

        // get results

        // average acceptor/donor counts by microspecies distributions at given pH-s
        double[] pHs = plugin.getpHs();
        double[] msacc = plugin.getMsAcceptorCounts();
        double[] msdon = plugin.getMsDonorCounts();

        System.out.println("pH\tmsacc\tmsdon");
        for (int i = 0; i < pHs.length; ++i) {
            System.out.println("" + plugin.format(pHs[i]) + "\t" + plugin.format(msacc[i]) + "\t"
                    + plugin.format(msdon[i]));
        }

        // molecular data
        // with multiplicity
        int molecularAcceptorCount = plugin.getAcceptorCount();
        int molecularDonorCount = plugin.getDonorCount();
        // without multiplicity
        int molecularAcceptorAtomCount = plugin.getAcceptorAtomCount();
        int molecularDonorAtomCount = plugin.getDonorAtomCount();
        System.out.println();
        System.out.println("Acceptor count with multiplicity: " + molecularAcceptorCount);
        System.out.println("Donor count with multiplicity: " + molecularDonorCount);
        System.out.println("Acceptor count without multiplicity: " + molecularAcceptorAtomCount);
        System.out.println("Donor count without multiplicity: " + molecularDonorAtomCount);

        // atomic data
        System.out.println();
        System.out.println("Atom\tAcceptor count\tDonor count");
        int count = mol.getAtomCount();
        for (int i = 0; i < count; ++i) {

            // atomic acceptor/donor count with multiplicity
            int atomicAcceptorCount = plugin.getAcceptorCount(i);
            int atomicDonorCount = plugin.getDonorCount(i);

            if (atomicAcceptorCount != -1) { // -1 means H atom
                System.out.println("" + (i + 1) + "\t" + atomicAcceptorCount + "\t" + atomicDonorCount);
            }
        }


    }
}
