package Pojos;

import java.util.LinkedList;
import java.util.List;

public class Signal {
    private List<Integer> values;
    private String filename;
    private SignalType signalType;
    public static final int samplingrate = 100;

    public enum SignalType {
        EMG,
        EDA
    }

    public Signal(SignalType signalType) {
        this.values = new LinkedList<>();
        this.signalType = signalType;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValuesEMG(String stringEMG) {
        this.values = stringToValues(stringEMG);
    }
    public void setValuesEDA(String stringEDA) {
        this.values = stringToValues(stringEDA);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String Filename) {
        this.filename = Filename;
    }

    public SignalType getSignalType() {
        return signalType;
    }

    public void setSignalType(SignalType signalType) {
        this.signalType = signalType;
    }

    public  List<Integer> stringToValues(String str) {
        values.clear(); // Limpiamos la lista antes de agregar nuevos valores.
        String[] tokens = str.split(" "); // Dividimos el String por el espacio.

        int size = tokens.length;
        if(size>2) {
            for (int i = 0; i < size; i++) {
                try {
                    values.add(Integer.parseInt(tokens[i])); // Convertimos cada fragmento a Integer y lo agregamos a la LinkedList.
                } catch (NumberFormatException e) {
                    // Manejo de error si algún valor no es un Integer válido.
                    System.out.println("Error al convertir el valor: " + tokens[i]);
                }
            }
        }

        return values;
    }
    public String valuesToString() {
        StringBuilder message = new StringBuilder();
        String separator = " ";

        for (int i = 0; i < values.size(); i++) {
            message.append(values.get(i));
            if (i < values.size() - 1) {
                message.append(separator);
            }
        }

        return message.toString();
    }

    /*private String createFilename() {
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
            String contenido = getSignalValues(samplingrate).toString(); //

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
    public LinkedList<Integer> getSignalValues(int samplingRate) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int j = 0; j < samplingRate; j++) {
            int blockSize = samplingRate;
            // Si necesitas esta información visual, puedes guardarla en otro lugar.
            for (int i = 0; i < blockSize; i++) {
                int value = j * blockSize + i;
                result.add(values.get(value));  // Agregar los valores a la lista.
            }
        }
        return result;
    }*/

    @Override
    public String toString() {
        return "Signal{" +
                "values=" + values +
                ", Filename='" + filename + '\'' +
                '}';
    }
}



