package cn.wolfcode.shop.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JSONResult {
    private boolean success = true;
    private String message;

    private Object result;

    public void setMessage(String message) {
        this.success = false;
        this.message = message;
    }
}
