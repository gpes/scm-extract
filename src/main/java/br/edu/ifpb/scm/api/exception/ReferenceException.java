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

    public ReferenceException(String msg, IOException e) {
    }

}
