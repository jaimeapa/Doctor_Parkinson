package Pojos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Signal {
    private List<Integer> values;
    private String patientName;
    private LocalDate beginDate;

    private String signalFilename;
    private SignalType signalType;
    public static final int samplingrate = 100;

    public enum SignalType {
        EMG,
        EDA
    }

    public Signal(List<Integer> values, String patientName, LocalDate beginDate, String SignalFilename, SignalType signalType) {
        this.values = values;
        this.patientName = patientName;
        this.beginDate = beginDate;
        this.signalFilename = SignalFilename;
        this.signalType = signalType;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }



    public String getSignalFilename() {
        return signalFilename;
    }

    public void setSignalFilename(String SignalFilename) {
        this.signalFilename = SignalFilename;
    }

    public SignalType getSignalType() {
        return signalType;
    }

    public void setSignalType(SignalType signalType) {
        this.signalType = signalType;
    }

    private String createFilename() {
        Calendar c = Calendar.getInstance();
        String day = Integer.toString(c.get(Calendar.DATE));
        String month = Integer.toString(c.get(Calendar.MONTH));
        String year = Integer.toString(c.get(Calendar.YEAR));
        String hour = Integer.toString(c.get(Calendar.HOUR));
        String minute = Integer.toString(c.get(Calendar.MINUTE));
        String second = Integer.toString(c.get(Calendar.SECOND));
        String millisecond = Integer.toString(c.get(Calendar.MILLISECOND));

        String signalPrefix = signalType == SignalType.EMG ? "EMG" : "EDA";
        return patientName + signalPrefix + day + month + year + "_" + hour + minute + second + millisecond + ".txt";
    }

    public void storeSignalInFile() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String ruta = "MeasurementsBitalino\\" + signalFilename;
            String contenido = getValues(samplingrate).toString();

            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(contenido);

        } catch (IOException ex) {
            Logger.getLogger(Signal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Signal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String toString() {
        return "Signal{" +
                "values=" + values +
                ", patientName='" + patientName + '\'' +
                ", beginDate=" + beginDate +
                ", SignalFilename='" + signalFilename + '\'' +
                ", signalType=" + signalType +
                '}';
    }
}



