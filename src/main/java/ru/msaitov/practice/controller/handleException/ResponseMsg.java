package ru.msaitov.practice.controller.handleException;

/**
 * Сообщение исключения
 */
public class ResponseMsg {

    private String error;

    /**
     * @param msg
     */
    public ResponseMsg(String msg) {
        this.error = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
