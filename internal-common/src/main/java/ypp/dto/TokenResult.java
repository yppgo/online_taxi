package ypp.dto;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
@Data
public class TokenResult {
    private String phone;
    private String identity;
}
