/*
	Class to model atom from .sd file (MOL file format)
	DPL 10.09.14
*/

public class Atom
{
	private int atomNumber;				// numbering of atom in file starting from 1 (implicit in sequential list of atoms in file)
	private String atomType;			// N, C, ZN etc.
	private double xcoord;				// orthogonal x coordinate
	private double ycoord;				// orthogonal y coordinate
	private double zcoord;				// orthogonal z coordinate
	private int compoundID;			// compound identifier (ZINC00...)
	
	public Atom(int atomNumber, String atomType, double xcoord, double ycoord, double zcoord, int compoundID)
	{
		this.atomNumber = atomNumber;
		this.atomType = atomType;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.zcoord = zcoord;
		this.compoundID = compoundID;
	}

	// Write line into SQL file for upload into DB
	public String getFormatted()
	{
		return (atomNumber + "\t" + atomType + "\t" + xcoord + "\t" + ycoord + "\t" + zcoord  + "\t" +  compoundID);
	}	
	
	//-------------- accessor methods (not actually used) ------------//
	
	public int getAtomNumber()
	{
		return atomNumber;
	}
	
	public String getAtomType()
	{
		return atomType;
	}
	
	public double getXcoord()
	{
		return xcoord;
	}
	
	public double getYcoord()
	{
		return ycoord;
	}
	
	public double getZcoord()
	{
		return zcoord;
	}
	
	public int getCompoundID()
	{
		return compoundID;
	}


	@Override
	public String toString() {

		//return "A" + getCompoundID();
		return "" + getXcoord() + " , " + getYcoord() + " , " + getZcoord() + " , " + getCompoundID();
	}
}
