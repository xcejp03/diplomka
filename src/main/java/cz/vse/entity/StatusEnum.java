package cz.vse.entity;

/**
 * Created by pcejka on 21.09.2016.
 */

public enum StatusEnum {
    NORUN ("norun", 3),
    NOTCOMPLETED ("not-completed",2),
    BLOCKED ("blocked", 1),
    PASSED ("passed", 4),
    FAILED ("failed", 0),
    TESTOVACU ("tewsatovani", 10);

    private String statusText;
    private int priorityOrder;

    StatusEnum(String statusText, int priorityOrder) {
        this.statusText = statusText;
        this.priorityOrder = priorityOrder;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public int getPriorityOrder() {
        return priorityOrder;
    }

    public void setPriorityOrder(int priorityOrder) {
        this.priorityOrder = priorityOrder;
    }
}



