package utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for handling bet data files and their containing directories.
 * <p>
 * This class manages creation, deletion, and data insertion in text files that store bet information.
 * It also provides methods for retrieving file contents and checking file existence.
 * </p>
 * 
 * <p>The class assumes that each bet record contains timestamp, sport, event, bet type, odds, and amount.</p>
 */
public class DataFileUtils {

    private final String dataDirectoryName;
    private final String dataFileName;

    /**
     * Constructs a DataFileUtils instance with the given directory and file name.
     * Throws IllegalArgumentException if any of the parameters are null or empty.
     *
     * @param dataDirectoryName Name of the data folder
     * @param dataFileName Name of the file to read/write bet data
     * @throws IllegalArgumentException if dataDirectoryName or dataFileName is null or empty
     * @throws RuntimeException if directory or file creation fails
     */
    public DataFileUtils(String dataDirectoryName, String dataFileName) {
        if (dataDirectoryName == null || dataDirectoryName.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_DATA_DIRECTORY_NAME_EMPTY);
        }
        if (dataFileName == null || dataFileName.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_DATA_FILE_NAME_EMPTY);
        }
        this.dataDirectoryName = dataDirectoryName;
        this.dataFileName = dataFileName;
        createDataDirectory();
        createDataFile();
    }

    /**
     * Creates the data directory if it does not already exist.
     *
     * @throws RuntimeException if directory creation fails
     */
    public void createDataDirectory() {
        if (dataDirectoryExists()) {
            return;
        }
        File directory = new File(getDataDirectoryPath());
        try {
            directory.mkdir();
            return;
        } catch (SecurityException se) {
            throw new RuntimeException(Constants.ERROR_CREATE_DATA_DIRECTORY, se);
        }
    }

    /**
     * Creates the data file if it does not already exist.
     *
     * @throws RuntimeException if file creation fails
     */
    public void createDataFile() {
        if (dataFileExists()) {
            return;
        }
        File dataFile = new File(getDataFilePath());
        try {
            dataFile.createNewFile();
            return;
        } catch (IOException | SecurityException e) {
            throw new RuntimeException(Constants.ERROR_CREATE_DATA_FILE, e);
        }
    }

    /**
     * Returns the absolute path of the data directory.
     *
     * @return full path of the data directory
     */
    public String getDataDirectoryPath() {
        return System.getProperty("user.dir") + File.separator + dataDirectoryName;
    }

    /**
     * Returns the absolute path of the data file.
     *
     * @return full path of the data file
     */
    public String getDataFilePath() {
        return getDataDirectoryPath().concat(File.separator).concat(dataFileName);
    }

    /**
     * Checks if the data directory exists.
     *
     * @return true if the directory exists; false otherwise
     */
    public boolean dataDirectoryExists() {
        File dataFolder = new File(getDataDirectoryPath());
        return dataFolder.exists() && dataFolder.isDirectory();
    }

    /**
     * Checks if the data file exists.
     *
     * @return true if the file exists; false otherwise
     */
    public boolean dataFileExists() {
        File dataFile = new File(getDataFilePath());
        return dataFile.exists() && dataFile.isFile();
    }

    /**
     * Deletes the data directory if it exists.
     * 
     * @throws RuntimeException if directory deletion fails
     */
    public void deleteDataFolderIfEmpty() {
        if (!dataDirectoryExists()) {
            return;
        }
        File dataFolder = new File(getDataDirectoryPath());
        try {
            if (dataFolder.delete()) {
                return;
            }
            else {
                throw new RuntimeException(Constants.ERROR_DELETE_DATA_DIRECTORY);
            }
        } catch (Exception e) {
            throw new RuntimeException(Constants.ERROR_DELETE_DATA_DIRECTORY, e);
        }
    }

    /**
     * Deletes the data file if it exists.
     *
     * @throws RuntimeException if file deletion fails
     */
    public void deleteDataFile() {
        if (!dataFileExists()) {
            return;
        }
        File fFile = new File(getDataFilePath());
        try {
            fFile.delete();
        } catch (Exception e) {
            throw new RuntimeException(Constants.ERROR_DELETE_DATA_FILE, e);
        }
    }

    /**
     * Reads and returns the full contents of the bet data file as a String.
     *
     * @return a string containing all bet records from the file
     * @throws IllegalStateException if the data file does not exist
     * @throws RuntimeException if an I/O error occurs while reading the file
     */
    public String getInfoFromDataFileIntoString() {
        if (!dataFileExists()) {
            throw new IllegalStateException(Constants.ERROR_DATA_FILE_MISSING);
        }
        String content = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getDataFilePath()));
            String line;
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(Constants.ERROR_SHOWING_BETS, e);
        }
        return content;
    }

    /**
     * Inserts a string into the data file.
     * <p>
     * The method validates the input content and appends it to the file with a newline.
     * </p>
     *
     * @param content the string content to insert (must not be null or empty)
     * @return true if the content was successfully inserted
     * @throws IllegalArgumentException if content is null or empty
     * @throws IllegalStateException if the data file does not exist
     * @throws RuntimeException if an I/O error occurs while writing to the file
     */
    public boolean insertStringIntoDataFile(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_INVALID_INPUT);
            }
        if (!dataFileExists()) {
            throw new IllegalStateException(Constants.ERROR_DATA_FILE_MISSING); }
        String filePath = getDataFilePath();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(content).append("\n");

            bw.write(buffer.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(Constants.ERROR_INSERTING_BET, e);
        }
        return true;
    }
    
    /**
     * Inserts a new bet record into the data file, prepending a timestamp.
     * <p>
     * The method validates input parameters and writes the bet data in CSV format.
     * </p>
     *
     * @param sport      the sport name (must not be null or empty)
     * @param event      the event name (must not be null or empty)
     * @param betType    the type of bet (must not be null or empty)
     * @param odds       the betting odds (must be positive)
     * @param amount     the bet amount (must be positive)
     * @return true if the bet was successfully inserted
     * @throws IllegalArgumentException if any parameter is null, empty, or invalid
     * @throws IllegalStateException if the data file does not exist
     * @throws RuntimeException if an I/O error occurs while writing to the file
     */
    public boolean insertBetIntoDataFile(String sport, String event, String betType, float odds,
                                            float amount) {
        if (sport == null || sport.isEmpty() ||
            event == null || event.isEmpty() ||
            betType == null || betType.isEmpty() ||
            odds <= 0 || amount <= 0) {
            throw new IllegalArgumentException(Constants.ERROR_INVALID_BET_INPUT);
            }

        if (!dataFileExists()) {
            throw new IllegalStateException(Constants.ERROR_DATA_FILE_MISSING); }

        String filePath = getDataFilePath();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

            StringBuilder buffer = new StringBuilder();
            buffer.append(now.format(formatter)).append(",")
                    .append(sport).append(",")
                    .append(event).append(",")
                    .append(betType).append(",")
                    .append(odds).append(",")
                    .append(amount).append("\n");

            bw.write(buffer.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(Constants.ERROR_INSERTING_BET, e);
        }
        return true;
    }
}