/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util.exception;

/**
 *
 * @author songhan
 */
public class SalesOrderAlreadyFulfilledException extends Exception{
    private static final long serialVersionUID = 1L;

    public SalesOrderAlreadyFulfilledException() {
        super();
    }
    
    public SalesOrderAlreadyFulfilledException(String msg) {
        super(msg);
    }
}
