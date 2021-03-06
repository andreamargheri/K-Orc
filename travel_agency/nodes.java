/*
  File nodes.java

  Generated by xklaim compiler
*/

import java.io.* ;
import Klava.* ;

class __hotel_1 extends KlavaProcess {
  // variables
  protected LogicalLocality compensator = new LogicalLocality( "compensator" ) ;
  protected LogicalLocality prenotazione = new LogicalLocality( "prenotazione" ) ;
  // used definition names

  public __hotel_1() {}

  public void execute() throws KlavaException {
    out( new KString( "doppia" ), new KInteger( 2 ), self ) ;
    out( new KString( "singola" ), new KInteger( 2 ), self ) ;
  }
}
class __volo_1 extends KlavaProcess {
  // variables
  protected LogicalLocality compensator = new LogicalLocality( "compensator" ) ;
  protected LogicalLocality prenotazione = new LogicalLocality( "prenotazione" ) ;
  // used definition names

  public __volo_1() {}

  public void execute() throws KlavaException {
    out( new KString( "milano" ), new KInteger( 2 ), self ) ;
    out( new KString( "roma" ), new KInteger( 2 ), self ) ;
  }
}
class __prenotazione_1 extends KlavaProcess {
  // variables
  protected KInteger count;
  protected KInteger gcount;
  protected KInteger numero;
  protected KInteger id;
  protected KInteger x;
  protected KString stringa;
  protected Locality dloc;
  protected KBoolean _while_exp_1;
  protected Tuple __tuple_1;
  protected Tuple __tuple_2;
  protected KInteger __backup_1_count;
  protected KBoolean __to_1;
  // used definition names

  public __prenotazione_1() {}

  public void execute() throws KlavaException {
    gcount = new KInteger( 0 ) ;
    _while_exp_1 = new KBoolean( true ) ;
    while ( _while_exp_1.booleanValue() ) {
      id = new KInteger() ;
      stringa = new KString() ;
      numero = new KInteger() ;
      dloc = new PhysicalLocality() ;
      in( id, stringa, numero, dloc, self ) ;
      __tuple_1 = new Tuple() ;
      __tuple_1.add( gcount ) ;
      __tuple_1.add( id ) ;
      __tuple_1.add( stringa ) ;
      __tuple_1.add( numero ) ;
      __tuple_1.add( dloc ) ;
      out( __tuple_1, self ) ;
      gcount = new KInteger( gcount.intValue() + 1 ) ;
      __tuple_2 = new Tuple() ;
      __tuple_2.add( id ) ;
      __backup_1_count = count ;
      count = new KInteger() ;
      __tuple_2.add( count ) ;
      __to_1 = new KBoolean( in_nb( __tuple_2, self ) ) ;
      if ( ! __to_1.booleanValue() ) {
        count = __backup_1_count ;
      }
      if ( __to_1.booleanValue() ) {
        out( new KString( "prenotato" ), id, new KString( "volo e hotel prenotati" ), self ) ;
      } else {
        out( id, new KInteger( 1 ), self ) ;
      }
      _while_exp_1 = new KBoolean( true ) ;
    }
  }
}
class __compensator_1 extends KlavaProcess {
  // variables
  protected KString stringa;
  protected KInteger numero;
  protected KInteger id;
  protected KInteger count;
  protected KInteger gcount;
  protected KInteger num;
  protected Locality dloc;
  protected KBoolean _while_exp_2;
  protected Tuple __tuple_3;
  protected KInteger __backup_2_gcount;
  protected KString __backup_3_stringa;
  protected KInteger __backup_4_numero;
  protected Locality __backup_5_dloc;
  protected KBoolean __to_2;
  protected KBoolean _forall_exp_1;
  protected LogicalLocality volo = new LogicalLocality( "volo" ) ;
  protected LogicalLocality hotel = new LogicalLocality( "hotel" ) ;
  protected LogicalLocality prenotazione = new LogicalLocality( "prenotazione" ) ;
  // used definition names

  public __compensator_1() {}

  public void execute() throws KlavaException {
    _while_exp_2 = new KBoolean( true ) ;
    while ( _while_exp_2.booleanValue() ) {
      id = new KInteger() ;
      in( new KString( "start" ), id, self ) ;
      __tuple_3 = new Tuple() ;
      __tuple_3.setHandleRetrieved(true) ;
      __backup_2_gcount = gcount ;
      gcount = new KInteger() ;
      __tuple_3.add( gcount ) ;
      __tuple_3.add( id ) ;
      __backup_3_stringa = stringa ;
      stringa = new KString() ;
      __tuple_3.add( stringa ) ;
      __backup_4_numero = numero ;
      numero = new KInteger() ;
      __tuple_3.add( numero ) ;
      __backup_5_dloc = dloc ;
      dloc = new PhysicalLocality() ;
      __tuple_3.add( dloc ) ;
      __to_2 = new KBoolean( in_nb( __tuple_3, prenotazione ) ) ;
      if ( ! __to_2.booleanValue() ) {
        gcount = __backup_2_gcount ;
        stringa = __backup_3_stringa ;
        numero = __backup_4_numero ;
        dloc = __backup_5_dloc ;
      }
      _forall_exp_1 = __to_2 ;
      while ( _forall_exp_1.booleanValue() ) {
        num = new KInteger() ;
        in( stringa, num, dloc ) ;
        out( stringa, new KInteger( num.intValue() + numero.intValue() ), dloc ) ;
        __tuple_3.reset() ;
        __backup_2_gcount = gcount ;
        gcount = new KInteger() ;
        __tuple_3.add( gcount ) ;
        __tuple_3.add( id ) ;
        __backup_3_stringa = stringa ;
        stringa = new KString() ;
        __tuple_3.add( stringa ) ;
        __backup_4_numero = numero ;
        numero = new KInteger() ;
        __tuple_3.add( numero ) ;
        __backup_5_dloc = dloc ;
        dloc = new PhysicalLocality() ;
        __tuple_3.add( dloc ) ;
        __to_2 = new KBoolean( in_nb( __tuple_3, prenotazione ) ) ;
        if ( ! __to_2.booleanValue() ) {
          gcount = __backup_2_gcount ;
          stringa = __backup_3_stringa ;
          numero = __backup_4_numero ;
          dloc = __backup_5_dloc ;
        }
        _forall_exp_1 = __to_2 ;
      }
      Print( ( new KString( "compensato".concat( id.toString().toString() ) ) ) ) ;
      _while_exp_2 = new KBoolean( true ) ;
    }
  }
}
class __tryNodes_1 extends KlavaProcess {
  // variables
  protected LogicalLocality volo = new LogicalLocality( "volo" ) ;
  protected LogicalLocality hotel = new LogicalLocality( "hotel" ) ;
  protected LogicalLocality compensator = new LogicalLocality( "compensator" ) ;
  protected LogicalLocality prenotazione = new LogicalLocality( "prenotazione" ) ;
  // used definition names
  protected prenotaHotel __dummy_prenotaHotel;
  protected prenotaVolo __dummy_prenotaVolo;

  public __tryNodes_1() {}

  public void execute() throws KlavaException {
    eval(  new prenotaHotel( new KInteger( 1 ), new KString( "doppia" ), new KInteger( 1 ) ), hotel ) ;
    eval(  new prenotaVolo( new KInteger( 1 ), new KString( "milano" ), new KInteger( 2 ) ), volo ) ;
    eval(  new prenotaHotel( new KInteger( 2 ), new KString( "singola" ), new KInteger( 1 ) ), hotel ) ;
    eval(  new prenotaVolo( new KInteger( 2 ), new KString( "roma" ), new KInteger( 3 ) ), volo ) ;
    eval(  new prenotaHotel( new KInteger( 3 ), new KString( "doppia" ), new KInteger( 2 ) ), hotel ) ;
    eval(  new prenotaVolo( new KInteger( 3 ), new KString( "milano" ), new KInteger( 4 ) ), volo ) ;
  }
}
// main class
public class nodes {
  static public void main( String args[] ) throws IOException, KlavaException {
    KlavaApplication app = new KlavaApplication();
    NetNode hotel = new NetNode( "hotel", 11001 );
    hotel.addToEnv( "compensator", NetUtils.createNodeAddress("localhost:11004") ) ;
    hotel.addToEnv( "prenotazione", NetUtils.createNodeAddress("localhost:11003") ) ;
    app.addNode(hotel) ;
    hotel.addProcess( new __hotel_1() ) ;
    NetNode volo = new NetNode( "volo", 11002 );
    volo.addToEnv( "compensator", NetUtils.createNodeAddress("localhost:11004") ) ;
    volo.addToEnv( "prenotazione", NetUtils.createNodeAddress("localhost:11003") ) ;
    app.addNode(volo) ;
    volo.addProcess( new __volo_1() ) ;
    NetNode prenotazione = new NetNode( "prenotazione", 11003 );
    app.addNode(prenotazione) ;
    prenotazione.addProcess( new __prenotazione_1() ) ;
    NetNode compensator = new NetNode( "compensator", 11004 );
    compensator.addToEnv( "volo", NetUtils.createNodeAddress("localhost:11002") ) ;
    compensator.addToEnv( "hotel", NetUtils.createNodeAddress("localhost:11001") ) ;
    compensator.addToEnv( "prenotazione", NetUtils.createNodeAddress("localhost:11003") ) ;
    app.addNode(compensator) ;
    compensator.addProcess( new __compensator_1() ) ;
    NetNode tryNodes = new NetNode( "tryNodes", 11005 );
    tryNodes.addToEnv( "volo", NetUtils.createNodeAddress("localhost:11002") ) ;
    tryNodes.addToEnv( "hotel", NetUtils.createNodeAddress("localhost:11001") ) ;
    tryNodes.addToEnv( "compensator", NetUtils.createNodeAddress("localhost:11004") ) ;
    tryNodes.addToEnv( "prenotazione", NetUtils.createNodeAddress("localhost:11003") ) ;
    app.addNode(tryNodes) ;
    tryNodes.addProcess( new __tryNodes_1() ) ;
    app.startNodes();
    app.waitForNodesAndTerminate();
  }
}
