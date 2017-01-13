package cz.vse.entity;

import com.fasterxml.jackson.annotation.JsonView;
import cz.vse.jsonview.Views;

import java.util.List;

/**
 * Created by pcejk on 12.01.2017.
 */
    public class AjaxResponseBody {

        @JsonView(Views.Public.class)
        String msg;

        @JsonView(Views.Public.class)
        String code;

        @JsonView(Views.Public.class)
        List<User> result;

        //getters and setters

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
