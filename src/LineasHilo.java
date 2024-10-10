import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class LineasHilo extends Thread {
    int threadId;
    Vector<String> vectorLineas;
    ConcurrentHashMap<String, Integer> chmCuentaPalabras;
    int numHebras;

    public LineasHilo(int threadId, ConcurrentHashMap<String, Integer> chmCuentaPalabras, int numHebras, Vector<String> vectorLineas) {
        this.threadId = threadId;
        this.chmCuentaPalabras = chmCuentaPalabras;
        this.numHebras = numHebras;
        this.vectorLineas = vectorLineas;
    }

    @Override
    public void run() {
        for (int i = threadId; i < vectorLineas.size(); i+= numHebras) {
            String[] palabras = vectorLineas.get( i ).split( "\\W+" );
            for( int j = 0; j < palabras.length; j++ ) {
                // Procesa cada palabra de la linea "i", si es distinta de blancos.
                String palabraActual = palabras[ j ].trim();
                if( palabraActual.length() > 0 ) {
                    PruebaConcurrentHashMap.contabilizaPalabra( chmCuentaPalabras, palabraActual );
                }
            }
        }
    }
}
