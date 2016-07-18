package br.edu.ifpb.scm.api.exception;

import java.io.IOException;

/**
 *
 * @author  Anderson Souza
 */
public class ReferenceException extends RuntimeException {

    public ReferenceException(Exception e) {
        super();
    }

    public ReferenceException(String ocorreu_um_erro_tente_novamente_mais_tard, IOException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
