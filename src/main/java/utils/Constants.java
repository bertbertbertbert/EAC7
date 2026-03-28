package utils;
public class Constants {


    public static final String DEFAULT_FILE_NAME = "bets.txt";
    public static final String DEFAULT_DATA_DIRECTORY = "data";
    public static final int DEFAULT_COLUMN_NUMBER = 7;
    public static final String MESSAGE_DEFAULT_ASK_STRING = "Introdueixi una cadena de text";
    public static final String MESSAGE_DEFAULT_ERROR_STRING = "S'ha introduït un text buit";
    public static final String MESSAGE_DEFAULT_ASK_INTEGER = "Introdueixi un valor enter";
    public static final String MESSAGE_DEFAULT_ERROR_INTEGER = "El valor introduït no correspon a un enter";
    public static final String MESSAGE_DEFAULT_ASK_FLOAT = "Introdueixi un valor amb decimals per l'opció";
    public static final String MESSAGE_DEFAULT_ERROR_FLOAT = "El valor introduït no correspon a un nombre amb decimals";

    public static final String ERROR_DATA_DIRECTORY_NAME_EMPTY = "El nom del directori de dades no pot ser nul o buit";
    public static final String ERROR_DATA_FILE_NAME_EMPTY = "El nom de l'arxiu de dades no pot ser nul o buit";
    public static final String ERROR_CREATE_DATA_DIRECTORY = "No s'ha pogut crear el directori de dades a causa de restriccions de seguretat.";
    public static final String ERROR_CREATE_DATA_FILE = "No s'ha pogut crear l'arxiu de dades.";
    public static final String ERROR_DELETE_DATA_DIRECTORY = "No s'ha pogut esborrar el directori de dades.";
    public static final String ERROR_DATA_FILE_MISSING = "L'arxiu de dades no existeix.";
    public static final String ERROR_INVALID_BET_INPUT = "Paràmetres d'entrada no vàlids per inserir l'aposta a l'arxiu de dades.";
    public static final String ERROR_SHOW_MESSAGE_EMPTY = "El títol i el text principal no poden ser nuls o buits.";
    public static final String ERROR_BET_LIST_EMPTY = "La llista d'apostes no pot ser nul·la o buida.";
    public static final String MESSAGE_SEPARATOR = "--------------------------------------------------------------------------------------------------------------";
    public static final String START_MENU_HEADER = "BET IOC!";
    public static final String START_MENU = """
                                    1) Afegir un usuari.
                                    2) Afegir una desenvolupament.
                                    3) Afegir una aposta a un desenvolupament.
                                    4) Veure el llistat de desenvolupaments.
                                    5) Importar dades des d'un fitxer.
                                    6) Exportar dades a un fitxer.
                                    0) Sortir.
                                    """;
    public final static String LIST_HEADER = "DATETIME     SPORT       DESENVOLUPAMENT        JUGADOR           TIPUS                      QUOTES    IMPORT";
    public final static String TEMP_LIN = "-------------------------------------------------------------------------------------------------------------";
    public final static String BET_BOARD_FORMAT = "%-12s %-11s %-18s %-18s %-25s %7s %9s";
    public static final String EMPTY_SPACE = "";
    public static final String ERROR_HEADER = "ERROR";   
    public static final String INFO_HEADER = "INFO";   
    public static final String DATETIME_FORMAT = "yyyyMMddHHmm";
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String ERROR_INSERTING_BET = "Error en inserir l'aposta: ";
    public static final String ERROR_SHOWING_BETS = "Error en mostrar les apostes: ";
    public static final String ERROR_DELETE_DATA_FILE = "No s'ha pogut esborrar l'arxiu de dades.";

    public static final String ERROR_BET_BETTOR_NAME_EMPTY = "El nom de l'apostant no pot ser nul o buit";
    public static final String ERROR_BET_DESCRIPTION_EMPTY = "La descripció de l'aposta no pot ser nul·la o buida";
    public static final String ERROR_BET_ODDS_POSITIVE = "Les quotes han de ser un valor positiu";
    public static final String ERROR_BET_AMOUNT_POSITIVE = "L'import ha de ser un valor positiu";
    public static final String BET_TO_STRING_FORMAT = "%s,%s,%.2f,%.2f";

    public static final String ERROR_EVENT_TYPE_NAME_EMPTY = "El tipus i el nom no poden ser nuls o buits";
    public static final String ERROR_EVENT_TIMESTAMP_NULL = "La marca de temps no pot ser nul·la";
    public static final String ERROR_EVENT_TIMESTAMP_FUTURE = "La marca de temps ha de ser en el futur";
    public static final String ERROR_EVENT_BETTOR_OR_DESCRIPTION_EMPTY = "L'apostant o la descripció no poden ser nuls o buits";
    public static final String ERROR_EVENT_BETTOR_NOT_FOUND = "L'apostant no s'ha trobat";
    public static final String ERROR_EVENT_BETTOR_ALREADY_EXISTS = "Ja existeix una aposta per aquest apostant";
    public static final String ERROR_EVENT_BET_AMOUNT_POSITIVE = "L'import de l'aposta ha de ser un valor positiu";
    public static final String NEWLINE = "\n";
    public static final String SEPARATOR = ",";
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String FLOAT_TWO_DECIMALS = "%.2f";
    public static final String EVENT_TO_STRING_FORMAT = "%s,%s,%s";

    public static final String ERROR_EVENTLIST_EVENT_NULL = "L'desenvolupament no pot ser nul";
    public static final String ERROR_EVENTLIST_DUPLICATE_NAME = "Ja existeix un desenvolupament amb el mateix nom";
    public static final String ERROR_EVENTLIST_EVENT_NOT_FOUND = "No existeix cap desenvolupament amb el nom indicat";

       public static final String ERROR_USER_NULL = "User no pot ser null";
       public static final String ERROR_PREFERENCES_NULL = "Preferences no pot ser null";
       public static final String ERROR_USERSTATS_NULL = "UserStats no pot ser null";
    
    public static final String ERROR_INVALID_INPUT = "Entrada no vàlida.";

}
