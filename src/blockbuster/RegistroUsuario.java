package blockbuster;

public class RegistroUsuario {

    private int[] ubicacionIndex = new int[15];
    private Usuario[] usuarios = new Usuario[15];
    private int cantUsuarios = 0;

    public Usuario getUsuario(int cedula) {
        int inferior = 0, superior = cantUsuarios, mitad;

        while (inferior <= superior) {
            mitad = (inferior + superior) / 2;

            if (ubicacionIndex[mitad] > cedula) {
                superior = mitad;
                mitad = (inferior + superior)/2;
            }
            if (ubicacionIndex[mitad] < cedula) {
                inferior = mitad;
                mitad = (superior + inferior) / 2;
            }
            if (ubicacionIndex[mitad] == cedula) {
                return usuarios[mitad];
            }
        }
        return null;
    }

    private boolean primerDato = true;
    public void putUsuario(Usuario usuario) {
        // Esto ubica la posicion en la que va el usuario
        int posicion = 0, j = 0;
        cantUsuarios++;
        if (primerDato) {
            ubicacionIndex[0] = usuario.getCedula();
            usuarios[0] = usuario;
            primerDato = false;
        } else {
            while (ubicacionIndex[j] < usuario.getCedula() && ubicacionIndex[j] != 0) {
                j++;
                posicion++;
            }
            // Se traslada la posicion de los elementos que van detras de la cédula
            for(int i = (15-cantUsuarios); i >= posicion; i--) {
                ubicacionIndex[i+1] = ubicacionIndex[i];
                usuarios[i+1] = usuarios[i];
            }
            usuarios[posicion] = usuario;
            ubicacionIndex[posicion] = usuario.getCedula();
        }
    }

    public void setUsuario(Usuario usuario, int cedulaAntigua) {
        int posicion = 0;
        while (ubicacionIndex[posicion] != cedulaAntigua) {
            posicion++;
        }
        for (int i = posicion; i < 14; i++) {
            ubicacionIndex[i] = ubicacionIndex[i+1];
            usuarios[i] = usuarios[i+1];
        }
        putUsuario(usuario);
    }

    public boolean existeUsuario(int cedula) {
        int inferior = 0, superior = cantUsuarios, mitad;

        while (inferior <= superior) {
            mitad = (inferior + superior) / 2;

            if (ubicacionIndex[mitad] > cedula) {
                superior = mitad;
                mitad = (inferior + superior)/2;
            }
            if (ubicacionIndex[mitad] < cedula) {
                inferior = mitad;
                mitad = (superior + inferior) / 2;
            }
            if (ubicacionIndex[mitad] == cedula) {
                return true;
            }
        }
        return false;
    }
}