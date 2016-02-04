import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;
    // Colecci�n de todos los items que no tienen ninguna puja
    private ArrayList<Lot> lotsSinPujas;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        lotsSinPujas = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        Lot pipas = new Lot(nextLotNumber, description);
        lots.add(pipas);
        lotsSinPujas.add(pipas);
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            lotsSinPujas.remove(selectedLot);
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
                                   
            }
            else {
                // Report which bid is higher.
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                  selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Lot selectedLot = lots.get(lotNumber - 1);
            // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) {
                System.out.println("Internal error: Lot number " +
                                   selectedLot.getNumber() +
                                   " was returned instead of " +
                                   lotNumber);
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                               " does not exist.");
            return null;
        }
    }
    
    /**
     * Este m�todo muestra por pantalla los detalles de todos los items que se est�n subastando actualmente. 
     * De aquellos por los que haya habido pujas indica el nombre de la persona que ha hecho la puja m�s alta y el valor de dicha puja;
     * del resto indica que no ha habido pujas..
     */
    public void close()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
            if (lot.getHighestBid() !=null) {
                System.out.println("La persona que ha hecho la puja m�s alta es " + lot.getHighestBid().getBidder().getName());
                System.out.println("Y el valor de la puja es: " + lot.getHighestBid().getValue());
            }
            else {
                System.out.println("No ha habido ninguna puja por este item.");
            }
        }
    }
    
    /**
     * 
     * Partiendo del c�digo terminado de la actividad 0060, a�ade un m�todo denominado getUnsold a la clase Auction
     * que devuelva una colecci�n de todos los items por los que no habido ninguna puja en este momento; este m�todo no debe imprimir nada por pantalla.
     * Testea los cambios y haz un commit.
     * Sube el proyecto a GitHub e indica la URL de este commit.
     */
    public ArrayList<Lot> getUnsold()
    {
        return lotsSinPujas;
    }
}
