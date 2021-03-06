/*
  File FindItem.java

  Generated by xklaim compiler
*/

import java.io.* ;
import Klava.* ;

public class FindItem extends KlavaProcess {
  // parameters
  protected KString itemId;
  protected KInteger maxPrice;
  protected KString reqId;
  // variables
  protected Locality locstart;
  // used definition names
  protected Find __dummy_Find;

  public FindItem(
    KString itemId,
    KInteger maxPrice,
    KString reqId
  ) {
    // parameters
    this.itemId = itemId ;
    this.maxPrice = maxPrice ;
    this.reqId = reqId ;
  }

  public void execute() throws KlavaException {
    locstart = new PhysicalLocality() ;
    read( new KString( "start" ), locstart, self ) ;
    eval(  new Find( itemId, maxPrice, reqId ), locstart ) ;
  }
}
