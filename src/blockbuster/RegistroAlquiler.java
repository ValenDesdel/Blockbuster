package blockbuster;

public class RegistroAlquiler {

    private int[] ubicacionIndex = new int[15];
    private Alquiler[] alquileres = new Alquiler[15];
    private int cantAlquileres = 0;

    public Alquiler getAlquiler(int cedula) {
        int inferior = 0, superior = cantAlquileres, mitad;

        while (inferior <= superior && cantAlquileres != 0) {
            mitad = (inferior + superior) / 2;

            if (ubicacionIndex[mitad] > cedula) {
                superior = mitad;
                mitad = (inferior + superior)/2;
            }
            if (ubicacionIndex[mitad] < cedula) {
                inferior = mitad;
                mitad = (superior + inferior) / 2;
            }
            if (ubicacionIndex[mitad] == 0) {
                return null;
            }
            if (ubicacionIndex[mitad] == cedula) {
                return alquileres[mitad];
            }
        }
        return null;
    }

    private boolean primerDato = true;
    public void putAlquiler(Alquiler alquiler) {
        // Esto ubica la posicion en la que va el usuario
        int posicion = 0, j = 0;
        cantAlquileres++;
        if (primerDato) {
            ubicacionIndex[0] = alquiler.getCedulaAlquiler();
            alquileres[0] = alquiler;
            primerDato = false;
        } else {
            while (ubicacionIndex[j] < alquiler.getCedulaAlquiler() && ubicacionIndex[j] != 0) {
                j++;
                posicion++;
            }
            // Se traslada la posicion de los elementos que van detras de la cédula
            for(int i = (15-cantAlquileres); i >= posicion; i--) {
                ubicacionIndex[i+1] = ubicacionIndex[i];
                alquileres[i+1] = alquileres[i];
            }
            alquileres[posicion] = alquiler;
            ubicacionIndex[posicion] = alquiler.getCedulaAlquiler();
        }
    }

    public void setAlquiler(Alquiler alquiler, int cedula) {
        int posicion = 0;

        while (ubicacionIndex[posicion] != cedula && posicion < 15) {
            posicion++;
        }
        for (int i = posicion; i < 14; i++) {
            ubicacionIndex[i] = ubicacionIndex[i+1];
            alquileres[i] = alquileres[i+1];
        }
        putAlquiler(alquiler);
    }

    public void reordenar() {
        for (int i = 0; i < cantAlquileres; i++) {
            if (ubicacionIndex[i] == ubicacionIndex[i+1]) {
                alquileres[i].addPeliculasAlquilada(Integer.parseInt(alquileres[i+1].getPeliculasAlquiladas().get(0)));
                cantAlquileres--;
                for (int j = i+1; j < 14; j++) {
                    ubicacionIndex[j] = ubicacionIndex[j+1];
                    alquileres[j] = alquileres[j+1];
                }
            }
        }
    }


}
