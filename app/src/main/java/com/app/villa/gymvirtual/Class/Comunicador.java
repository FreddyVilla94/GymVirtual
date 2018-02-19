package com.app.villa.gymvirtual.Class;

/**
 * Created by Freddy on 24/5/2017.
 */

public class Comunicador {

    private static User objeto = null;

    public static void setObjeto(User newObjeto) {
        objeto = newObjeto;
    }

    public static User getObjeto() {
        return objeto;
    }
}
